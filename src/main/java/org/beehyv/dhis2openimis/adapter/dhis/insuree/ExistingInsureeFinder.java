package org.beehyv.dhis2openimis.adapter.dhis.insuree;

import java.util.List;

import org.beehyv.dhis2openimis.adapter.dhis.cache.attribute.TrackedEntityAttributeCache;
import org.beehyv.dhis2openimis.adapter.dhis.pojo.tracked_entity.query.EnrollmentDetail;
import org.beehyv.dhis2openimis.adapter.dhis.pojo.tracked_entity.query.EnrollmentQueryResponse;
import org.beehyv.dhis2openimis.adapter.dhis.util.TrackedEntityQueryMaker;
import org.beehyv.dhis2openimis.adapter.util.exception.InternalException;
import org.beehyv.dhis2openimis.adapter.util.exception.ObjectNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ExistingInsureeFinder {
	private final static Logger logger = LoggerFactory.getLogger(ExistingInsureeFinder.class);
	private TrackedEntityAttributeCache attributeCache;
	private TrackedEntityQueryMaker queryMaker;
	private InsureeUtil util;
	
	@Value("${app.dhis2.api.TrackedEntityInstances.Query}")
	private String teiQueryUrl;
	
	@Autowired
	public ExistingInsureeFinder(TrackedEntityAttributeCache attributeCache, TrackedEntityQueryMaker queryMaker,
			InsureeUtil util) {
		this.attributeCache = attributeCache;
		this.queryMaker = queryMaker;
		this.util = util;
	}

	
	public String getInsureeTrackedEntityId(String orgUnitId, String insureeId) throws ObjectNotFoundException, InternalException {
		String url = constructUrl(orgUnitId, insureeId);
		return queryMaker.queryAndGetTrackedEntityInstanceId(url);
	}

	private String constructUrl(String orgUnitId, String insureeId) {
		StringBuilder url = new StringBuilder(teiQueryUrl);
		url.append("ou=" + orgUnitId);
		url.append("&ouMode=ACCESSIBLE");
		
		String programId= util.getProgramId();
		url.append("&program=" + programId);
				
		String insureeIdAttributeId = attributeCache.get("Insuree ID");
		url.append("&attribute=" + insureeIdAttributeId + ":EQ:" + insureeId);
		
		url.append("&paging=false");
		return url.toString();
	}
	
	
	public String getEnrollmentIdForTeiId(String teiId) throws InternalException {
		EnrollmentQueryResponse enrollmentResponse = queryMaker.getEnrollmentResponseFromApi(teiId);
		return getEnrollmentIdFromEnrollmentResponse(enrollmentResponse);
	}
	
	private String getEnrollmentIdFromEnrollmentResponse(EnrollmentQueryResponse enrollmentResponse) throws InternalException {
		List<EnrollmentDetail> enrollments = enrollmentResponse.getEnrollments();
		String programId = util.getProgramId();
		for(EnrollmentDetail enrollment : enrollments) {
			if(enrollment.getProgram().equals(programId)) {
				return enrollment.getEnrollment();
			}
		}
		throw new InternalException("No enrollment id for the program 'Family/Insuree' found");
	}
	
}
