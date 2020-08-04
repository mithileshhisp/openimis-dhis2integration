package org.beehyv.dhis2openimis.adapter.dhis.insuree;

import org.beehyv.dhis2openimis.adapter.dhis.pojo.poster.TrackedEntityRequest;
import org.beehyv.dhis2openimis.adapter.openimis.pojo.patient.Patient;
import org.beehyv.dhis2openimis.adapter.util.exception.InternalException;
import org.beehyv.dhis2openimis.adapter.util.exception.ObjectNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * The Facade class, any function call from outside this package should ideally only every need this class.
 * @author Shubham Jaiswal
 *
 */
@Service
public class InsureeFacade {
    private static final Logger logger = LoggerFactory.getLogger(InsureeFacade.class);

    @Autowired private InsureeAdapter insureeAdapter;
    @Autowired private InsureePoster insureePoster;
    @Autowired private ExistingInsureeFinder existingInsureeFinder;
    @Autowired private InsureeUtil util;
    
    @Value("${app.dhis2.api.TrackedEntityInstances}")
    private String teiUrl;
    
    public void adaptAndPost(Patient openImisPatient) throws InternalException, ObjectNotFoundException {
    	TrackedEntityRequest patient = insureeAdapter.adapt(openImisPatient);
    	String registrationDate = insureeAdapter.getRegistrationDate(openImisPatient);
    	String insureeId = insureeAdapter.getInsureeId(openImisPatient);
    	String orgUnitId = insureeAdapter.getOrgUnit(openImisPatient);
    	try {
    		String insureeTrackedEntityId = existingInsureeFinder.getInsureeTrackedEntityId(orgUnitId, insureeId);
    		logger.info("Found an active insuree. Updating the same instance");
    		String insureeUpdateUrl = getInsureeUpdateUrl(insureeTrackedEntityId);
    		insureePoster.updateInsureeDetails(insureeUpdateUrl, patient);
    		//programStagePoster.deleteAllEvents(insureeTrackedEntityId);
    	} 
    	catch(ObjectNotFoundException e) {
    		logger.info("No active insuree found! Posting a new insuree");
    		insureePoster.postInsuree(patient, registrationDate);
    	}
    }
    
    
    private String getInsureeUpdateUrl(String insureeTrackedEntityId) {
    	StringBuilder url = new StringBuilder(teiUrl);
    	url.append(insureeTrackedEntityId);
    	
    	String programId = util.getProgramId();
    	url.append("?program=" + programId);
    	
    	return url.toString();
    }
}
