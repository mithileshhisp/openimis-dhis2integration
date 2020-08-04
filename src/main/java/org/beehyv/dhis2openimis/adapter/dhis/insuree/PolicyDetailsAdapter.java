package org.beehyv.dhis2openimis.adapter.dhis.insuree;

import org.beehyv.dhis2openimis.adapter.dhis.cache.attribute_options.PolicyStatusCache;
import org.beehyv.dhis2openimis.adapter.dhis.cache.data_element.DataElementCache;
import org.beehyv.dhis2openimis.adapter.dhis.cache.program.ProgramCache;
import org.beehyv.dhis2openimis.adapter.dhis.pojo.poster.DataValue;
import org.beehyv.dhis2openimis.adapter.dhis.pojo.poster.DetailsJson;
import org.beehyv.dhis2openimis.adapter.openimis.pojo.ExtensionItem;
import org.beehyv.dhis2openimis.adapter.openimis.pojo.coverage.Coverage;
import org.beehyv.dhis2openimis.adapter.util.ExtensionUrlUtil;
import org.beehyv.dhis2openimis.adapter.util.exception.ObjectNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("rawtypes")
@Component
public class PolicyDetailsAdapter {
	private static final Logger logger = LoggerFactory.getLogger(PolicyDetailsAdapter.class);
	
    private DataElementCache dataElementCache;
    private ProgramCache programCache;
    private PolicyStatusCache policyStatusCache;
    
    @Autowired
    public PolicyDetailsAdapter(DataElementCache dataElementCache, ProgramCache programCache, PolicyStatusCache policyStatusCache) {
        this.dataElementCache = dataElementCache;
        this.programCache = programCache;
        this.policyStatusCache = policyStatusCache;
    }

    
    public DetailsJson adaptCoverageToPolicyDetails(Coverage coverage) throws ObjectNotFoundException {
    	DetailsJson json  = new DetailsJson();
    	String programId = programCache.getByDisplayName("Family/Insuree").getId();
        json.setProgram(programId);
        json.setStatus("COMPLETED");
        json.setCompletedDate(LocalDate.now().toString());
        
    	String enrollDateString = getEnrollDateString(coverage);
    	json.setEventDate(enrollDateString);
        
		List<DataValue> dataValues = new ArrayList<>();

        fillPolicyId(dataValues, coverage);
        fillProduct(dataValues, coverage);
        fillPolicyStage(dataValues, coverage);
        fillPolicyStatus(dataValues, coverage);
        fillPolicyValue(dataValues, coverage);

        json.setDataValues(dataValues);
        return json;
    }
    
    public String getEnrollDateString(Coverage coverage) throws ObjectNotFoundException {
    	if (coverage.getExtension() != null) {
            for (ExtensionItem extensionItem : coverage.getExtension()) {
                if (ExtensionUrlUtil.ENROLL_DATE_URL.equals(extensionItem.getUrl())) {
                    return extensionItem.getValueDate();
                }
            }
            throw new ObjectNotFoundException("No EnrollDate found in coverage ");
        } else {
        	throw new ObjectNotFoundException("No extension list found in coverage ");
        }
    }
    
    private void fillPolicyId(List<DataValue> dataValues, Coverage coverage) {
    	try {
    		String value = coverage.getIdentifier().get(0).getValue();
    		String dataElementId = dataElementCache.get("Policy ID");
	        DataValue<String> dataValue = new DataValue<>(dataElementId, value);
	        dataValues.add(dataValue);
    	} catch(NullPointerException | IndexOutOfBoundsException e) {
    		//Do nothing as no proper mapping was found.
    	}
    }

    private void fillProduct(List<DataValue> dataValues, Coverage openImisCoverage) {
    	try {
    		String dataElementId = dataElementCache.get("Product");
    		String value = openImisCoverage.getGrouping().getPlan();
    		DataValue<String> dataValue = new DataValue<>(dataElementId, value);
    		dataValues.add(dataValue);
    	} catch(NullPointerException | IndexOutOfBoundsException e) {
    		//Do nothing, as no proper mapping was found.
    	}
    }

    private void fillPolicyStage(List<DataValue> dataValues, Coverage openImisCoverage) {
        String dataElementId = dataElementCache.get("Policy stage");
        String value = "";
        if (openImisCoverage.getExtension() != null) {
            for (ExtensionItem extensionItem : openImisCoverage.getExtension()) {
                if (ExtensionUrlUtil.STAGE_URL.equals(extensionItem.getUrl())) {
                    value = extensionItem.getValueString();
                    break;
                }
            }
        } else {
            value = "";
        }

        if ("N".equals(value)) {
            value = "New policy";
        } else if ("R".equals(value)) {
            value = "Renewed  policy";
        }

        DataValue<String> dataValue = new DataValue<>(dataElementId, value);
        dataValues.add(dataValue);
    }

    private void fillPolicyStatus(List<DataValue> dataValues, Coverage coverage){
        //TODO get from openImisClaim then use OptionsCache.
        try {
        	String imisValue = coverage.getStatus();
        	String upperCaseImisvalue = imisValue.substring(0,1).toUpperCase() + imisValue.substring(1);
        	String value = policyStatusCache.getCodeFor(upperCaseImisvalue);
        	String dataElementId = dataElementCache.get("Policy status");
        	DataValue<String> dataValue = new DataValue<>(dataElementId, value);
            dataValues.add(dataValue);
        } catch(NullPointerException e) {
        	//Do nothing, as no proper mapping was found.
        } catch(ObjectNotFoundException e) {
        	logger.error(e.getMessage());
        }
    }

    private void fillPolicyValue(List<DataValue> dataValues, Coverage coverage) {
        String dataElementId = dataElementCache.get("Policy Value ");
        Double value = null;
        if (coverage.getContract() != null) {
            value = coverage.getContract().get(0).getValuedItem().get(0).getNet().getValue();
        }
        DataValue<Double> dataValue = new DataValue<>(dataElementId, value);
        if(value != null) {
        	dataValues.add(dataValue);
        }
    }
}
