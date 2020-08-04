package org.beehyv.dhis2openimis.adapter.dhis.claim;

import org.beehyv.dhis2openimis.adapter.dhis.cache.data_element.DataElementCache;
import org.beehyv.dhis2openimis.adapter.dhis.cache.program.ProgramCache;
import org.beehyv.dhis2openimis.adapter.dhis.pojo.poster.DetailsJson;
import org.beehyv.dhis2openimis.adapter.dhis.pojo.ClaimItemOrService;
import org.beehyv.dhis2openimis.adapter.dhis.pojo.ClaimServiceAndItemAdapterReturn;
import org.beehyv.dhis2openimis.adapter.dhis.pojo.poster.DataValue;
import org.beehyv.dhis2openimis.adapter.openimis.pojo.ExtensionItem;
import org.beehyv.dhis2openimis.adapter.openimis.pojo.claim.Claim;
import org.beehyv.dhis2openimis.adapter.openimis.pojo.claim.claimItem.ClaimItem;
import org.beehyv.dhis2openimis.adapter.openimis.pojo.claimResponse.ClaimResponse;
import org.beehyv.dhis2openimis.adapter.openimis.pojo.claimResponse.claimResponseItem.ClaimResponseItem;
import org.beehyv.dhis2openimis.adapter.util.ExtensionUrlUtil;
import org.beehyv.dhis2openimis.adapter.util.exception.InternalException;
import org.beehyv.dhis2openimis.adapter.util.exception.ObjectNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@SuppressWarnings("rawtypes")
@Component
public class ClaimServiceAndItemAdapter {
	
	private static final Logger logger = LoggerFactory.getLogger(ClaimServiceAndItemAdapter.class);
	
    private DataElementCache dataElementCache;
    private ProgramCache programCache;
    
    @Autowired
    public ClaimServiceAndItemAdapter(DataElementCache dataElementCache, ProgramCache programCache) {
        this.dataElementCache = dataElementCache;
        this.programCache = programCache;
    }

    public List<ClaimServiceAndItemAdapterReturn> adapt(ClaimResponse imisClaimResponse, Claim imisClaim) 
    			throws InternalException, ObjectNotFoundException {
    	List<ClaimServiceAndItemAdapterReturn> output = new ArrayList<>();
    	String eventDate = getEventDateString(imisClaim);
    	for(int sequence = 1; sequence <= imisClaim.getItem().size(); sequence++) {
    		ClaimItem claimItem = getClaimItemWithSequenceNumber(sequence, imisClaim);
    		ClaimResponseItem claimResponseItem = getClaimResponseItemWithSequenceNumber(sequence, imisClaimResponse);
    		ClaimServiceAndItemAdapterReturn singleItemOrServiceJson = adaptSingleItemOrService(claimItem, claimResponseItem, eventDate);
    		output.add(singleItemOrServiceJson);
    	}
        return output;
    }
    
    public String getEventDateString(Claim imisClaim) throws ObjectNotFoundException {
		List<ExtensionItem> extensions = imisClaim.getExtension();
		for(ExtensionItem extension : extensions) {
			if(ExtensionUrlUtil.VALIDITY_FROM.equals(extension.getUrl())) {
				//Only the YYYY-mm-DD 10 chars.
				return extension.getValueString().substring(0, 10);
			}
		}
		throw new ObjectNotFoundException("No validityFrom extension found!");
	}
    
    private ClaimItem getClaimItemWithSequenceNumber(int sequence, Claim imisClaim) throws InternalException {
    	for(ClaimItem item : imisClaim.getItem()) {
    		if(item.getSequence() == sequence) {
    			return item;
    		}
    	}
    	logger.error("No item or service found with sequence: " + sequence + " in claim: " + imisClaim.getId());
    	throw new InternalException("No item or service found with sequence: " + sequence + " in claim: " + imisClaim.getId());
    }
    
    private ClaimResponseItem getClaimResponseItemWithSequenceNumber(int sequence, ClaimResponse imisClaimResponse) 
    		throws InternalException {
    	for(ClaimResponseItem item : imisClaimResponse.getItem()) {
    		if(item.getSequenceLinkId() == sequence) {
    			return item;
    		}
    	}
    	logger.error("No item or service found with sequence: " + sequence + " in claim response: " + imisClaimResponse.getId());
    	throw new InternalException("No item or service found with sequence: " + sequence + " in claim: " + imisClaimResponse.getId());
    }
    
    
    private ClaimServiceAndItemAdapterReturn adaptSingleItemOrService(ClaimItem claimItem, ClaimResponseItem claimResponseItem, String eventDate) throws InternalException {
    	ClaimServiceAndItemAdapterReturn output = new ClaimServiceAndItemAdapterReturn();
    	DetailsJson json = new DetailsJson();
        String programId = programCache.getByDisplayName("Claims management").getId();
        json.setProgram(programId);
        json.setStatus("COMPLETED");
        json.setCompletedDate(eventDate);
        json.setEventDate(eventDate);
        
        List<DataValue> dataValues;
        if(getItemOrService(claimItem) == ClaimItemOrService.ITEM) {
        	dataValues = fillItemDetails(claimItem, claimResponseItem);
        	output.setEventType(ClaimItemOrService.ITEM);
        } else {
        	dataValues = fillServiceDetails(claimItem, claimResponseItem);
        	output.setEventType(ClaimItemOrService.SERVICE);
        }
        json.setDataValues(dataValues);
        output.setDetailsJson(json);
        
        return output;
    }
    
    //Returns true if item, else (i.e. if service) returns false
    private ClaimItemOrService getItemOrService(ClaimItem claimItem) throws InternalException {
		if(claimItem.getCategory().getText().equals("item")) {
			return ClaimItemOrService.ITEM;
		} else if(claimItem.getCategory().getText().equals("service")) {
			return ClaimItemOrService.SERVICE;
		} else {
			throw new InternalException("Couldn't determine whether item or service.");
		}
	}


	private List<DataValue> fillItemDetails(ClaimItem claimItem, ClaimResponseItem claimResponseItem){
    	List<DataValue> dataValues = new ArrayList<>();
    	
    	fillClaimedItem(dataValues, claimItem);
        fillItemQuantity(dataValues, claimItem);
        fillItemPrice(dataValues, claimItem);
//        fillAdjustedAmountItem(dataValues, claimResponseItem);
//        fillApprovedAmountItem(dataValues, claimResponseItem);
//        fillDeductibleAmountItem(dataValues, imisClaim);
//        fillExceedCeilingAmountItem(dataValues, imisClaim);
//        fillRemuneratedAmountItem(dataValues, imisClaim);
//        fillValuatedAmountItem(dataValues, claimResponseItem);
        fillSequenceId(dataValues, claimItem);
        return dataValues;
    }
    
    
    private List<DataValue> fillServiceDetails(ClaimItem claimItem, ClaimResponseItem claimResponseItem){
    	List<DataValue> dataValues = new ArrayList<>();
    	
    	fillClaimedService(dataValues, claimItem);
        fillServiceQuantity(dataValues, claimItem);
        fillServicePrice(dataValues, claimItem);
//        fillAdjustedAmountService(dataValues, claimResponseItem);
//        fillApprovedAmountService(dataValues, claimResponseItem);
//        fillDeductibleAmountService(dataValues, imisClaim);
//        fillExceedCeilingAmountService(dataValues, imisClaim);
//        fillDeductibleAmountService(dataValues, imisClaim);
//        fillRemuneratedAmountService(dataValues, imisClaim);
//        fillValuatedAmountService(dataValues, claimResponseItem);
        fillSequenceId(dataValues, claimItem);
        
        return dataValues;
    }
    
    private void fillClaimedItem(List<DataValue> dataValues, ClaimItem claimItem) {
        String dataElementId = dataElementCache.get("Claimed item");
        String value = claimItem.getService().getText();
        DataValue<String> dataValue = new DataValue<>(dataElementId, value);
        dataValues.add(dataValue);
    }

    private void fillItemQuantity(List<DataValue> dataValues, ClaimItem claimItem) {
        String dataElementId = dataElementCache.get("Item quantity");
        Integer value = claimItem.getQuantity().getValue();
        DataValue<Integer> dataValue = new DataValue<>(dataElementId, value);
        dataValues.add(dataValue);
    }

    private void fillItemPrice(List<DataValue> dataValues, ClaimItem claimItem) {
        String dataElementId = dataElementCache.get("Item price");
        Double value = claimItem.getUnitPrice().getValue();
        DataValue<Double> dataValue = new DataValue<>(dataElementId, value);
        dataValues.add(dataValue);
    }

    private void fillAdjustedAmountItem(List<DataValue> dataValues, ClaimResponseItem claimResponseItem) {
        try{
        	Double value = claimResponseItem.getAdjudication().get(0).getAmount().getValue();
        	String dataElementId = dataElementCache.get("Adjusted amount - Item");
	        DataValue<Double> dataValue = new DataValue<>(dataElementId, value);
	        dataValues.add(dataValue);
        } catch(NullPointerException e) {
        	//Just means no data. Nothing to worry about.
        }
    }

    private void fillApprovedAmountItem(List<DataValue> dataValues, ClaimResponseItem claimResponseItem) {
        try{
        	Double value = claimResponseItem.getAdjudication().get(0).getAmount().getValue();
        	String dataElementId = dataElementCache.get("Approved amount - Item");
	        DataValue<Double> dataValue = new DataValue<>(dataElementId, value);
	        dataValues.add(dataValue);
        } catch(NullPointerException e) {
        	//Just means no data. Nothing to worry about.
        }
    }

    private void fillDeductibleAmountItem(List<DataValue> dataValues, Claim openImisClaim) {
        String dataElementId = dataElementCache.get("Deductible amount - Item");
        //TODO get from openImisClaim then use OptionsCache.
        Integer value = 25;
        DataValue<Integer> dataValue = new DataValue<>(dataElementId, value);
        dataValues.add(dataValue);
    }

    private void fillExceedCeilingAmountItem(List<DataValue> dataValues, Claim openImisClaim) {
        String dataElementId = dataElementCache.get("Exceed ceiling amount - Item");
        //TODO get from openImisClaim then use OptionsCache.
        Integer value = 26;
        DataValue<Integer> dataValue = new DataValue<>(dataElementId, value);
        dataValues.add(dataValue);
    }

    private void fillRemuneratedAmountItem(List<DataValue> dataValues, Claim openImisClaim) {
        String dataElementId = dataElementCache.get("Remunerated amount - Item");
        //TODO get from openImisClaim then use OptionsCache.
        Integer value = 27;
        DataValue<Integer> dataValue = new DataValue<>(dataElementId, value);
        dataValues.add(dataValue);
    }

    private void fillValuatedAmountItem(List<DataValue> dataValues, ClaimResponseItem claimResponseItem) {
        try{
        	Double value = claimResponseItem.getAdjudication().get(0).getAmount().getValue();
        	String dataElementId = dataElementCache.get("Valuated amount - Item");
	        DataValue<Double> dataValue = new DataValue<>(dataElementId, value);
	        dataValues.add(dataValue);
        } catch(NullPointerException e) {
        	//Just means no data. Nothing to worry about.
        }
    }
    
    private void fillSequenceId(List<DataValue> dataValues, ClaimItem claimItem) {
    	String dataElementId = dataElementCache.get("Sequence ID");
    	Integer value = claimItem.getSequence();
    	DataValue<Integer> dataValue = new DataValue<>(dataElementId, value);
        dataValues.add(dataValue);
    }
    
    private void fillClaimedService(List<DataValue> dataValues, ClaimItem claimItem) {
        String dataElementId = dataElementCache.get("Claimed service");
        String value = claimItem.getService().getText();
        DataValue<String> dataValue = new DataValue<>(dataElementId, value);
        dataValues.add(dataValue);
    }

    private void fillServiceQuantity(List<DataValue> dataValues, ClaimItem claimItem) {
        String dataElementId = dataElementCache.get("Service quantity");
        Integer value = claimItem.getQuantity().getValue();
        DataValue<Integer> dataValue = new DataValue<>(dataElementId, value);
        dataValues.add(dataValue);
    }

    private void fillServicePrice(List<DataValue> dataValues, ClaimItem claimItem) {
        String dataElementId = dataElementCache.get("Service price");
        Double value = claimItem.getUnitPrice().getValue();
        DataValue<Double> dataValue = new DataValue<>(dataElementId, value);
        dataValues.add(dataValue);
    }

    private void fillAdjustedAmountService(List<DataValue> dataValues, ClaimResponseItem claimResponseItem) {
        try{
        	Double value = claimResponseItem.getAdjudication().get(0).getAmount().getValue();
        	String dataElementId = dataElementCache.get("Adjusted amount - Service");
	        DataValue<Double> dataValue = new DataValue<>(dataElementId, value);
	        dataValues.add(dataValue);
        } catch(NullPointerException e) {
        	//Just means no data. Nothing to worry about.
        }
    }

    private void fillApprovedAmountService(List<DataValue> dataValues, ClaimResponseItem claimResponseItem) {
        try{
        	Double value = claimResponseItem.getAdjudication().get(0).getAmount().getValue();
        	String dataElementId = dataElementCache.get("Approved amount -  Service");
	        DataValue<Double> dataValue = new DataValue<>(dataElementId, value);
	        dataValues.add(dataValue);
        } catch(NullPointerException e) {
        	//Just means no data. Nothing to worry about.
        }
    }

    private void fillDeductibleAmountService(List<DataValue> dataValues, Claim openImisClaim) {
        String dataElementId = dataElementCache.get("Deductible amount - Service");
        //TODO get from openImisClaim then use OptionsCache.
        Integer value = 33;
        DataValue<Integer> dataValue = new DataValue<>(dataElementId, value);
        dataValues.add(dataValue);
    }

    private void fillExceedCeilingAmountService(List<DataValue> dataValues, Claim openImisClaim) {
        String dataElementId = dataElementCache.get("Exceed ceiling amount - Service");
        //TODO get from openImisClaim then use OptionsCache.
        Integer value = 34;
        DataValue<Integer> dataValue = new DataValue<>(dataElementId, value);
        dataValues.add(dataValue);
    }
    
    private void fillRemuneratedAmountService(List<DataValue> dataValues, Claim openImisClaim) {
        String dataElementId = dataElementCache.get("Remunerated amount - Service");
        //TODO replace with proper mapping.
        Integer value = 35;
        DataValue<Integer> dataValue = new DataValue<>(dataElementId, value);
        dataValues.add(dataValue);
    }
    
    private void fillValuatedAmountService(List<DataValue> dataValues, ClaimResponseItem claimResponseItem) {
        try{
        	Double value = claimResponseItem.getAdjudication().get(0).getAmount().getValue();
        	String dataElementId = dataElementCache.get("Valuated amount - Service");
	        DataValue<Double> dataValue = new DataValue<>(dataElementId, value);
	        dataValues.add(dataValue);
        } catch(NullPointerException e) {
        	//Just means no data. Nothing to worry about.
        }
    }
}
