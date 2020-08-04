package org.beehyv.dhis2openimis.adapter;

import org.beehyv.dhis2openimis.adapter.dhis.fetch.*;
import org.beehyv.dhis2openimis.adapter.openimis.APICaller;

import org.beehyv.dhis2openimis.adapter.util.ParamsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class Tester{

	@Autowired
	APICaller apiCaller;
	
	@Autowired
	TrackedEntityAttributeAndOptionsFetcher entityAndOptionsFetcher;
	
	@Autowired
	TrackedEntityTypeFetcher entityTypeFetcher;
	
	@Autowired
	ProgramFetcher programFetcher;
	
	@Autowired
	ProgramStageFetcher programStageFetcher;
	
	@Autowired
	DataElementFetcher dataElementFetcher;
	
	@Autowired
	APICaller openImisApiCaller;

	@Autowired
	OrganisationUnitFetcher organisationUnitFetcher;
	
	@Autowired
	RelationshipTypeFetcher relationshipTypeFetcher;
	/**
	 * Currently acts as the main function.
	 */
	
	@Value("${app.openimis.api.Location}")
	String imisLocationUrl;
	@Value("${app.openimis.api.Claim}")
	String imisClaimUrl;
	@Value("${app.openimis.api.ClaimResponse}")
	String imisClaimResponseUrl;
	@Value("${app.openimis.api.Patient}")
	String imisPatientUrl;
	@Value("${app.openimis.api.Coverage}")
	String imisCoverageUrl;
	
	public void run(){
		final Logger logger = LoggerFactory.getLogger(Tester.class);
		
		entityAndOptionsFetcher.fetchAndCache();
		entityTypeFetcher.fetchAndCache();
		programFetcher.fetchAndCache();
		programStageFetcher.fetchAndCache();
		dataElementFetcher.fetchAndCache();
		organisationUnitFetcher.fetchAndCache();
		relationshipTypeFetcher.fetchAndCache();
		
		apiCaller.getLocationBundle(imisLocationUrl);
		
		apiCaller.getPatientBundleAndPostToDhis(imisPatientUrl + "?" + ParamsUtil.REF_DATE_PARAM);
		apiCaller.getCoverageBundle(imisCoverageUrl + "?" + ParamsUtil.REF_DATE_PARAM);	
		
		apiCaller.getClaimAndClaimResponseBundleAndPostToDhis(imisClaimUrl + "?" + ParamsUtil.REF_DATE_PARAM + "&" + ParamsUtil.CLAIM_PAGE_SIZE, 
				imisClaimResponseUrl + "?" + ParamsUtil.REF_DATE_PARAM+ "&" + ParamsUtil.CLAIM_PAGE_SIZE, 105);
		
	}

}
