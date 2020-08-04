package org.beehyv.dhis2openimis.adapter.dhis.claim;

import java.util.List;

import org.beehyv.dhis2openimis.adapter.openimis.cacheService.ClaimCacheService;
import org.beehyv.dhis2openimis.adapter.dhis.pojo.ClaimItemOrService;
import org.beehyv.dhis2openimis.adapter.dhis.pojo.ClaimServiceAndItemAdapterReturn;
import org.beehyv.dhis2openimis.adapter.dhis.pojo.poster.DetailsJson;
import org.beehyv.dhis2openimis.adapter.dhis.pojo.poster.TrackedEntityRequest;
import org.beehyv.dhis2openimis.adapter.dhis.pojo.poster.event.Event;
import org.beehyv.dhis2openimis.adapter.dhis.relationship.RelationshipFacade;
import org.beehyv.dhis2openimis.adapter.dhis.util.CreateEventDataPojo;
import org.beehyv.dhis2openimis.adapter.dhis.util.ProgramStagePoster;
import org.beehyv.dhis2openimis.adapter.openimis.pojo.claim.Claim;
import org.beehyv.dhis2openimis.adapter.openimis.pojo.claimResponse.ClaimResponse;
import org.beehyv.dhis2openimis.adapter.util.exception.InternalException;
import org.beehyv.dhis2openimis.adapter.util.exception.ObjectNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The Facade class, any function call from outside this package should ideally only every need this class.
 * @author Shubham Jaiswal
 *
 */
@Service
public class ClaimFacade {
	
	private static final Logger logger = LoggerFactory.getLogger(ClaimFacade.class);
	
	@Autowired
	private ClaimAdapter claimAdapter;
	
	@Autowired
	private ClaimPoster claimPoster;
	
	@Autowired
	private ClaimDetailsAdapter claimDetailsAdapter;

	@Autowired
	private ClaimServiceAndItemAdapter claimServiceAndItemAdapter;

	@Autowired
	private ProgramStagePoster programStagePoster;
	
	@Autowired
	private ClaimUtil claimUtil;
	
	@Autowired
	private ExistingClaimFinder existingClaimFinder;
	
	@Autowired
	private ClaimCacheService claimCache;
	
	@Autowired
	private RelationshipFacade relationshipFacade;
	
	/**
	 * Adapts an OpenImis ClaimResponse to Dhis Claim, and all its program stages and posts all the data to Dhis2 API.
	 * @param imisClaimResponse 
	 * @throws ObjectNotFoundException
	 * @throws InternalException
	 */
	public void adaptAndPost(ClaimResponse imisClaimResponse) throws ObjectNotFoundException, InternalException {
		Claim imisClaim = getClaimFromClaimResponse(imisClaimResponse);
		
		TrackedEntityRequest dhisClaimJson = claimAdapter.adapt(imisClaim);
		String createdDate = claimAdapter.getCreatedDate(imisClaim);
		CreateEventDataPojo internalData;
		try {
			internalData = getEnrollmentIdForExistingClaim(imisClaim);
			logger.info("Found an existing claim instance. Updating it..");
			claimPoster.updateClaim(dhisClaimJson, internalData.getTrackedEntityInstance());
		} 
		catch (ObjectNotFoundException e) { 
			logger.info("Found no existing claim instance. Adding a new one");
			internalData = claimPoster.postAndGetClaimManagementEnrollmentId(dhisClaimJson, createdDate);
			
			List<ClaimServiceAndItemAdapterReturn> servicesAndItemClaimedJsons = claimServiceAndItemAdapter.adapt(imisClaimResponse, imisClaim);
			postServicesAndItemClaimed(internalData, servicesAndItemClaimedJsons);
			logger.info("Finished adding Services and Items claimed data");
			
			addRelationship(dhisClaimJson, internalData, imisClaim);
		}
		
		DetailsJson claimDetailJson = claimDetailsAdapter.adapt(imisClaimResponse, imisClaim);
		postClaimDetail(internalData, claimDetailJson);
		logger.info("Finished adding Claim Details data");
	}


	private Claim getClaimFromClaimResponse(ClaimResponse imisClaimResponse) {
		String claimId = imisClaimResponse.getRequest().getReference();
		//Removing the initial 'Claim/'
		claimId = claimId.substring(6);
		Claim imisClaim = claimCache.getById(claimId);
		return imisClaim;
	}
	
	
	private CreateEventDataPojo getEnrollmentIdForExistingClaim(Claim openImisClaim) 
			throws InternalException, ObjectNotFoundException {
		String claimCode = claimUtil.getClaimCodeFromImisClaim(openImisClaim);
		String orgUnitId = claimUtil.getOrgUnitFromImisClaim(openImisClaim);
		return existingClaimFinder.getEnrollmentId(orgUnitId, claimCode);
	}
	
	private void postClaimDetail(CreateEventDataPojo internalData, DetailsJson claimDetailJson) {
		Event event = claimUtil.getEvent(internalData, "Claim details");
		programStagePoster.postProgramStageData(event, claimDetailJson);
	}
	
	private void postServicesAndItemClaimed(CreateEventDataPojo internalData, 
				List<ClaimServiceAndItemAdapterReturn> servicesAndItems) throws InternalException {
		for(ClaimServiceAndItemAdapterReturn serviceAndItem : servicesAndItems) {
			Event event;
			if(serviceAndItem.getEventType() == ClaimItemOrService.ITEM) {
				event = claimUtil.getEvent(internalData, "Claim - Items");
			} else{
				event = claimUtil.getEvent(internalData, "Claim - Services");
			} 
			programStagePoster.postProgramStageData(event, serviceAndItem.getDetailsJson());
		}
	}
	
	private void addRelationship(TrackedEntityRequest claimJson, CreateEventDataPojo internalData, Claim imisClaim) throws ObjectNotFoundException, InternalException {
		String insureeId = imisClaim.getPatient().getReference().substring(8);
		String dhisClaimTei = internalData.getTrackedEntityInstance();
		
		try {
			relationshipFacade.addRelationship(claimJson, dhisClaimTei, insureeId);
			logger.info("Relationship successfully added");
		} catch (Exception e) {
			logger.info("Relationship adding failed: " + e.getMessage());
		}
	}
}
