package org.beehyv.dhis2openimis.adapter.dhis.insuree;

import org.beehyv.dhis2openimis.adapter.dhis.pojo.post_response.TrackedEntityPostResponse;
import org.beehyv.dhis2openimis.adapter.dhis.pojo.poster.TrackedEntityRequest;
import org.beehyv.dhis2openimis.adapter.dhis.pojo.poster.enrollment.EnrollmentRequestBody;
import org.beehyv.dhis2openimis.adapter.dhis.util.CreateEventDataPojo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class InsureePoster {
    private static final Logger logger = LoggerFactory.getLogger(InsureePoster.class);
    private RestTemplate restTemplate;
    private HttpHeaders authHeaders;
    private InsureeUtil util;
    
    @Value("${app.dhis2.api.TrackedEntityInstances}")
    private String teiUrl;
    
    @Value("${app.dhis2.api.Enrollments}")
    private String enrollmentsUrl;
    
    @Autowired
    public InsureePoster(RestTemplate restTemplate ,@Qualifier("Dhis2")HttpHeaders authHeaders, InsureeUtil util) {
    	this.restTemplate = restTemplate;
        this.authHeaders = authHeaders;
        this.util = util;
    }

    
    public CreateEventDataPojo postInsuree(TrackedEntityRequest insuree, String registrationDate) {
        TrackedEntityPostResponse insureeResponse = post(insuree);
        logger.debug("Insuree posted!");

        String orgUnit = insuree.getOrgUnit();
        String trackedEntityId = getReferenceId(insureeResponse);
        EnrollmentRequestBody enrollment = buildEnrollmentBody(trackedEntityId, orgUnit, registrationDate);
        TrackedEntityPostResponse enrollmentResponse = post(enrollment);
        logger.debug("Insuree enrollment posted");

        String enrollmentId = getReferenceId(enrollmentResponse);
        
        CreateEventDataPojo response = new CreateEventDataPojo(trackedEntityId, orgUnit, enrollmentId);
        return response;
    }

    
    //TODO Look up if we have to do something with the response.
    public void updateInsureeDetails(String url, TrackedEntityRequest insuree) {
    	HttpEntity<TrackedEntityRequest> request = new HttpEntity<>(insuree, authHeaders);
    	restTemplate.put(url, request);
    	logger.debug("Insuree data updated!");
    }
    
    
    private TrackedEntityPostResponse post(TrackedEntityRequest insuree) {
        HttpEntity<TrackedEntityRequest> request = new HttpEntity<TrackedEntityRequest>(insuree, authHeaders);
        try {
        	ResponseEntity<TrackedEntityPostResponse> response = 
        			restTemplate.exchange(teiUrl, HttpMethod.POST, request, TrackedEntityPostResponse.class);
        	return response.getBody();
        } catch(Exception e) {
        	throw new RuntimeException("Posting " + insuree.toString() + " to url: " + teiUrl);
        }
    }

    private TrackedEntityPostResponse post(EnrollmentRequestBody enrollment) {
        HttpEntity<EnrollmentRequestBody> request = new HttpEntity<>(enrollment, authHeaders);

        ResponseEntity<TrackedEntityPostResponse> response = 
        		restTemplate.exchange(enrollmentsUrl, HttpMethod.POST, request, TrackedEntityPostResponse.class);

        return response.getBody();
    }


    private String getReferenceId(TrackedEntityPostResponse response) {
        return response.getResponse().getImportSummaries().get(0).getReference();
    }


    private EnrollmentRequestBody buildEnrollmentBody(String referenceId, String orgUnit, String registrationDate) {
        EnrollmentRequestBody enrollment = new EnrollmentRequestBody();
        enrollment.setTrackedEntityInstance(referenceId);
        enrollment.setOrgUnit(orgUnit);
        enrollment.setStatus("ACTIVE");
        enrollment.setEnrollmentDate(registrationDate);
        
        String programId = util.getProgramId();
        enrollment.setProgram(programId);

        return enrollment;
    }





}
