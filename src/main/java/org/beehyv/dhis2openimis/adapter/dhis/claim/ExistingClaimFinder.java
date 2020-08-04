package org.beehyv.dhis2openimis.adapter.dhis.claim;

import java.util.List;

import org.beehyv.dhis2openimis.adapter.dhis.cache.attribute.TrackedEntityAttributeCache;
import org.beehyv.dhis2openimis.adapter.dhis.cache.program.ProgramCache;
import org.beehyv.dhis2openimis.adapter.dhis.util.CreateEventDataPojo;
import org.beehyv.dhis2openimis.adapter.dhis.util.TrackedEntityQueryMaker;
import org.beehyv.dhis2openimis.adapter.dhis.pojo.tracked_entity.query.EnrollmentDetail;
import org.beehyv.dhis2openimis.adapter.dhis.pojo.tracked_entity.query.EnrollmentQueryResponse;
import org.beehyv.dhis2openimis.adapter.util.exception.InternalException;
import org.beehyv.dhis2openimis.adapter.util.exception.ObjectNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ExistingClaimFinder {
	private static final Logger logger = LoggerFactory.getLogger(ExistingClaimFinder.class);
	private TrackedEntityAttributeCache attributeCache;
	private ProgramCache programCache;
	private TrackedEntityQueryMaker queryMaker;
	
	@Value("${app.dhis2.api.TrackedEntityInstances.Query}")
	private String teiQueryUrl;
	
	@Autowired
	public ExistingClaimFinder(TrackedEntityAttributeCache attributeCache, ProgramCache programCache, TrackedEntityQueryMaker queryMaker) {
		this.attributeCache = attributeCache;
		this.programCache = programCache;
		this.queryMaker = queryMaker;
	}
	
	
	public CreateEventDataPojo getEnrollmentId(String orgUnitId, String claimCode) throws ObjectNotFoundException, InternalException {
		String trackedEntityInstanceId = getClaimTrackedEntityInstanceId(orgUnitId, claimCode);
		EnrollmentQueryResponse enrollmentResponse = queryMaker.getEnrollmentResponseFromApi(trackedEntityInstanceId);
		String enrollmentId = getEnrollmentIdFromEnrollmentResponse(enrollmentResponse);
		
		CreateEventDataPojo claimData = new CreateEventDataPojo(trackedEntityInstanceId, orgUnitId, enrollmentId);
		return claimData;
	}


	private String getClaimTrackedEntityInstanceId(String orgUnitId, String claimCode) throws ObjectNotFoundException, InternalException {
		String url = constructUrl(orgUnitId, claimCode);
		return queryMaker.queryAndGetTrackedEntityInstanceId(url);
	}
	
	
	private String constructUrl(String orgUnitId, String claimCode) {
		String url = teiQueryUrl + "ou=" + orgUnitId;
		url += "&filter=";
		String claimNumberAttributeId = attributeCache.get("Claim number");
		url += claimNumberAttributeId + ":EQ:" + claimCode;
		return url;
	}
	
	
	private String getEnrollmentIdFromEnrollmentResponse(EnrollmentQueryResponse enrollmentResponse) throws InternalException {
		List<EnrollmentDetail> enrollments = enrollmentResponse.getEnrollments();
		String programId = programCache.getByDisplayName("Claims management").getId();
		for(EnrollmentDetail enrollment : enrollments) {
			if(enrollment.getProgram().equals(programId)) {
				return enrollment.getEnrollment();
			}
		}
		throw new InternalException("No enrollment id for the program 'Claim management' found");
	}
}
