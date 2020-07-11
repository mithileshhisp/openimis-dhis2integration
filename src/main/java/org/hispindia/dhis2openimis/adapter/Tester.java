package org.hispindia.dhis2openimis.adapter;

import java.time.LocalDate;
import java.util.Arrays;

import org.hispindia.dhis2openimis.adapter.openimis.APICaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class Tester implements ApplicationRunner{

	@Autowired
	APICaller apiCaller;

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
        
        @Value("${app.openimis.locationMasterUrl}")
	String openIMISLegacyDemoURL;
        
        
	@Override
	public void run(ApplicationArguments args) throws Exception {
		final Logger logger = LoggerFactory.getLogger(Tester.class);

                // orgUnit sync code
                logger.info("Application started with command-line arguments: {}", Arrays.toString(args.getSourceArgs()));
                logger.info("NonOptionArgs: {}", args.getNonOptionArgs());
                
                
                boolean orgUnitSync = args.containsOption("orgUnitSync");
                logger.info("Contains orgUnitSync : " + orgUnitSync );
                
                if( orgUnitSync ){
                    //String openIMISLegacyDemoURL = "http://legacy.demo.openimis.org/rest/api/master";
                    System.out.println( "openIMISLegacyDemoURL -- orgUnit sync " + openIMISLegacyDemoURL + " date - " + LocalDate.of(1900, 1, 1).toString() );
                    apiCaller.getLegacyDdemoOpenIMISOrgAndPostToDhis2(openIMISLegacyDemoURL);
                }
                else{
                    System.out.println( "imisLocationUrl -- data sync " + imisLocationUrl);
                    System.out.println( "imisClaimResponseUrl -- data sync " + imisClaimResponseUrl);
                }
                //String openIMISLegacyDemoURL = "http://legacy.demo.openimis.org/rest/api/master";
                   
	}

}
