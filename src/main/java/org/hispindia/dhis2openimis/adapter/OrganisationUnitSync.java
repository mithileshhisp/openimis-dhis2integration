/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hispindia.dhis2openimis.adapter;

import java.time.LocalDate;
import org.hispindia.dhis2openimis.adapter.openimis.APICaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *
 * @author Mithilesh Thakur
 */
@Component
public class OrganisationUnitSync {
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

    public void run(){
            final Logger logger = LoggerFactory.getLogger(OrganisationUnitSync.class);

            String openIMISLegacyDemoURL = "http://legacy.demo.openimis.org/rest/api/master";
            System.out.println( "imisLocationUrl -- orgUnit sync " + openIMISLegacyDemoURL + " date - " + LocalDate.of(1990, 1, 1).toString() );
            //apiCaller.getLegacyDdemoOpenIMISOrgAndPostToDhis2(openIMISLegacyDemoURL);
	}
}
