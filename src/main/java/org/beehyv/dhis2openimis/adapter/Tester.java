package org.beehyv.dhis2openimis.adapter;

import java.time.LocalDate;
import java.util.Arrays;
import org.beehyv.dhis2openimis.adapter.dhis.fetch.*;
import org.beehyv.dhis2openimis.adapter.openimis.APICaller;

import org.beehyv.dhis2openimis.adapter.util.ParamsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
                
                
                // orgUnit sync code
                /*
                logger.info("Application started with command-line arguments: {}", Arrays.toString(args.getSourceArgs()));
                logger.info("NonOptionArgs: {}", args.getNonOptionArgs());
                
                
                boolean orgUnitSync = args.containsOption("orgUnitSync");
                logger.info("Contains orgUnitSync : " + orgUnitSync );
                
                if( orgUnitSync ){
                    String openIMISLegacyDemoURL = "http://legacy.demo.openimis.org/rest/api/master";
                    System.out.println( "imisLocationUrl -- orgUnit sync " + openIMISLegacyDemoURL + " date - " + LocalDate.of(1900, 1, 1).toString() );
                    apiCaller.getLegacyDdemoOpenIMISOrgAndPostToDhis2(openIMISLegacyDemoURL);
                }
                else{
                    System.out.println( "imisLocationUrl -- data sync " + imisLocationUrl);
                    System.out.println( "imisClaimResponseUrl -- data sync " + imisClaimResponseUrl);
                }                
		*/
                
	}

}
