package org.beehyv.dhis2openimis.adapter.dhis.util;

import java.util.List;

import org.beehyv.dhis2openimis.adapter.dhis.pojo.tracked_entity.query.EnrollmentDataPojo;
import org.beehyv.dhis2openimis.adapter.dhis.pojo.tracked_entity.query.EnrollmentQueryResponse;
import org.beehyv.dhis2openimis.adapter.dhis.pojo.tracked_entity.query.QueryResponse;
import org.beehyv.dhis2openimis.adapter.dhis.pojo.tracked_entity.query.QueryResponseHeader;
import org.beehyv.dhis2openimis.adapter.util.exception.InternalException;
import org.beehyv.dhis2openimis.adapter.util.exception.ObjectNotFoundException;
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
public class TrackedEntityQueryMaker {
	private static final Logger logger = LoggerFactory.getLogger(TrackedEntityQueryMaker.class);
	private HttpEntity<Void> request;
	private RestTemplate restTemplate;
	
	@Value("${app.dhis2.api.TrackedEntityInstances}")
	private String teiUrl;
	
	@Value("${app.dhis2.api.Enrollments}")
	private String enrollmentsUrl;
	
	@Autowired
	public TrackedEntityQueryMaker(@Qualifier("Dhis2") HttpHeaders authHeaders, RestTemplate restTemplate) {
		this.request = new HttpEntity<Void>(authHeaders);
		this.restTemplate = restTemplate;
	}
	
	
	public String queryAndGetTrackedEntityInstanceId(String url) throws ObjectNotFoundException, InternalException {
		QueryResponse queryResponse = makeQueryToApi(url);
		
		if(queryResponse.getRows().size() == 0) {
			throw new ObjectNotFoundException("No tei id found while quering at url: "+ url);
		} else if(queryResponse.getRows().size() == 1) {
			return getTrackedEntityInstanceIdFromQueryResponse(queryResponse);
		} else {
			throw new InternalException("Multiple tracked entity instances found for query url: " + url);
		}
	}
	
	private QueryResponse makeQueryToApi(String url) {
		ResponseEntity<QueryResponse> response = restTemplate.exchange(url, HttpMethod.GET, request, QueryResponse.class);
		logger.debug("Made a tei query at: " + url + " and got the response:" + response.getBody().toString());
		return response.getBody();
	}
	
	private String getTrackedEntityInstanceIdFromQueryResponse(QueryResponse response) {
		int instanceItr = 0;
		List<QueryResponseHeader> headers = response.getHeaders();
		for(QueryResponseHeader header : headers) {
			if(header.getColumn().equals("Instance")) {
				break;
			}
			instanceItr++;
		}
		
		List<List<String>> rows = response.getRows();
		return rows.get(0).get(instanceItr);
	}
	
	
	public EnrollmentQueryResponse getEnrollmentResponseFromApi(String trackedEntityInstanceId) {
		String url = getEnrollmentIdForTrackedEntityInstance(trackedEntityInstanceId);
		ResponseEntity<EnrollmentQueryResponse> response = restTemplate.exchange(
													url, HttpMethod.GET, request, EnrollmentQueryResponse.class);
		return response.getBody();
	}
	
	private String getEnrollmentIdForTrackedEntityInstance(String trackedEntityInstanceId) {
    	return teiUrl + trackedEntityInstanceId + "?fields=enrollments";
    }
	
	public EnrollmentDataPojo getEnrollmentDataFromApi(String enrollmentId) {
		String url = enrollmentsUrl + enrollmentId;
		ResponseEntity<EnrollmentDataPojo> response = restTemplate.exchange(
												url, HttpMethod.GET, request, EnrollmentDataPojo.class);
		return response.getBody();
	}
}
