/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
    @Value("${app.openimis.locationMasterUrl}")
    String openIMISLegacyDemoURL;

    public void run(){
            final Logger logger = LoggerFactory.getLogger(OrganisationUnitSync.class);
            System.out.println( "imisLocationUrl -- orgUnit sync " + openIMISLegacyDemoURL + " date - " + LocalDate.of(1990, 1, 1).toString() );
            apiCaller.getLegacyDdemoOpenIMISOrgAndPostToDhis2(openIMISLegacyDemoURL);
	}
}
