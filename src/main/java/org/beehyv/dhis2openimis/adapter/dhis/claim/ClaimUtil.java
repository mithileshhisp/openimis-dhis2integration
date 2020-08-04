package org.beehyv.dhis2openimis.adapter.dhis.claim;

import java.time.LocalDate;

import org.beehyv.dhis2openimis.adapter.dhis.cache.org_unit.OrganisationUnitCacheService;
import org.beehyv.dhis2openimis.adapter.dhis.cache.program.ProgramCache;
import org.beehyv.dhis2openimis.adapter.dhis.cache.program.ProgramStageCache;
import org.beehyv.dhis2openimis.adapter.dhis.pojo.poster.event.Event;
import org.beehyv.dhis2openimis.adapter.dhis.util.CreateEventDataPojo;
import org.beehyv.dhis2openimis.adapter.openimis.cacheService.LocationCacheService;
import org.beehyv.dhis2openimis.adapter.openimis.pojo.claim.Claim;
import org.beehyv.dhis2openimis.adapter.util.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClaimUtil {
	
	@Autowired
	private LocationCacheService locationCache;
	
	@Autowired
	private OrganisationUnitCacheService orgUnitCache;
	
	@Autowired
	private ProgramCache programCache;
	
	@Autowired
	private ProgramStageCache stageCache;
	
	public String getClaimCodeFromImisClaim(Claim openImisClaim){
		return openImisClaim.getId();
	}
	
	public String getOrgUnitFromImisClaim(Claim openImisClaim) throws ObjectNotFoundException {
		String facilityReference = openImisClaim.getFacility().getReference();
		//Removing the leading "Location/"
		String facilityId = facilityReference.substring(9);
		String locationCode = locationCache.getCodeById(facilityId);
		String orgUnitId = orgUnitCache.getByCode(locationCode);
		return orgUnitId;
	}
	
	
	/**
	 * Get the Event which can be then used in {@code ProgramStagePoster} to create an event.
	 * @param internalData Read {@code CreateEventDataPojo} and send properly filled info require to create event.
	 * @param programStage Name(displayName) of the Program Stage
	 * @return an event with proper details.
	 */
	public Event getEvent(CreateEventDataPojo internalData, String programStage) {
		Event event = new Event();
		
		String programId = getProgramId();
		event.setProgram(programId);
		String programStageId = getProgramStageId(programStage);
		event.setProgramStage(programStageId);
		
		event.setTrackedEntityInstance(internalData.getTrackedEntityInstance());
		event.setOrgUnit(internalData.getOrgUnit());
		event.setEnrollment(internalData.getEnrollmentId());
		
		
		//TODO fill proper details below.
		event.setStatus("SCHEDULE");
		event.setDueDate(LocalDate.now().toString());
		
		return event;
	}
	
	
	public String getProgramId() {
		String programId = programCache.getIdByDisplayName("Claims management");
		return programId;
	}
	
	public String getProgramStageId(String programStageDisplayName) {
		String programStageId = stageCache.getByDisplayName(programStageDisplayName);
		return programStageId;
	}
	
	
}
