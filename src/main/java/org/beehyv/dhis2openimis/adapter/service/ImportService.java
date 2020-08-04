package org.beehyv.dhis2openimis.adapter.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author Vishal
 */
@Service
public class ImportService {

    private Logger logger = LoggerFactory.getLogger(ImportService.class);

//  uncomment the below annotation to test you apis
//    @Scheduled(cron = "0/5 * * * * ?")
    public void importData() throws IOException {
        //TODO add the Patient and Claim post methods here
    }
}