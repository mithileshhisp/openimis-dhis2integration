/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.beehyv.dhis2openimis.adapter;

import java.time.LocalDate;
import org.beehyv.dhis2openimis.adapter.openimis.APICallerOrgUnitSync;

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
    APICallerOrgUnitSync apiCallerOrgUnitSync;


    /**
     * Currently acts as the main function.
     */
    @Value("${app.openimis.locationMasterUrl}")
    String openIMISLegacyDemoURL;

    public void run(){
            final Logger logger = LoggerFactory.getLogger(OrganisationUnitSync.class);
            logger.info("imisLocationUrl " + openIMISLegacyDemoURL + " date - " + LocalDate.of(1990, 1, 1).toString()  );
            apiCallerOrgUnitSync.getLegacyDdemoOpenIMISOrgAndPostToDhis2(openIMISLegacyDemoURL);
	}
}
