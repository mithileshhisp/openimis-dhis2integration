package org.beehyv.dhis2openimis.adapter.dhis.relationship;

import org.beehyv.dhis2openimis.adapter.dhis.cache.attribute.TrackedEntityAttributeCache;
import org.beehyv.dhis2openimis.adapter.dhis.cache.program.ProgramCache;
import org.beehyv.dhis2openimis.adapter.dhis.pojo.poster.TrackedEntityRequest;
import org.beehyv.dhis2openimis.adapter.dhis.relationship.pojo.TrackedEntityRequestComplete;
import org.beehyv.dhis2openimis.adapter.dhis.relationship.util.RelationshipPoster;
import org.beehyv.dhis2openimis.adapter.dhis.util.TrackedEntityQueryMaker;
import org.beehyv.dhis2openimis.adapter.util.exception.InternalException;
import org.beehyv.dhis2openimis.adapter.util.exception.ObjectNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RelationshipFacade {
	private static final Logger logger = LoggerFactory.getLogger(RelationshipFacade.class);
	
	@Autowired private RelationshipJsonMaker jsonMaker;
	@Autowired private RelationshipPoster relationshipApiPoster;
	@Autowired private TrackedEntityQueryMaker teQueryMaker;
	@Autowired private ProgramCache programCache;
	@Autowired private TrackedEntityAttributeCache teaCache;
	
	@Value("${app.dhis2.api.TrackedEntityInstances.Query}")
	private String teiQueryUrl;
	
	public RelationshipFacade() {
		
	}
	
	
	public void addRelationship(TrackedEntityRequest claimJson, String claimTei, String insureeId) throws ObjectNotFoundException, InternalException {
		String insureeTeiQueryUrl = getQueryUrlToFetchInsureeTei(insureeId);
		String insureeTei = teQueryMaker.queryAndGetTrackedEntityInstanceId(insureeTeiQueryUrl);
		TrackedEntityRequestComplete completeClaimJson = jsonMaker.getCompleteClaimJson(claimJson, claimTei, insureeTei);
		relationshipApiPoster.post(claimTei, completeClaimJson);
	}


	private String getQueryUrlToFetchInsureeTei(String insureeId) {
		StringBuilder url = new StringBuilder(teiQueryUrl);
		url.append("ouMode=ACCESSIBLE");
		url.append("&");
		
		String familyInsureeProgramId = programCache.getIdByDisplayName("Family/Insuree");
		url.append("program=" + familyInsureeProgramId);
		url.append("&");
		
		String insureeIdAttributeId = teaCache.get("Insuree ID");
		url.append("attribute=" + insureeIdAttributeId + ":EQ:" + insureeId);
		return url.toString();
	}
}
