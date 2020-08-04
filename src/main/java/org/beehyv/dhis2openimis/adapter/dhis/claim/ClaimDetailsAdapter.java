package org.beehyv.dhis2openimis.adapter.dhis.claim;

import java.util.ArrayList;
import java.util.List;

import org.beehyv.dhis2openimis.adapter.dhis.cache.data_element.DataElementCache;
import org.beehyv.dhis2openimis.adapter.dhis.cache.program.ProgramCache;
import org.beehyv.dhis2openimis.adapter.dhis.pojo.poster.DetailsJson;
import org.beehyv.dhis2openimis.adapter.dhis.pojo.poster.DataValue;
import org.beehyv.dhis2openimis.adapter.openimis.pojo.ExtensionItem;
import org.beehyv.dhis2openimis.adapter.openimis.pojo.claim.Claim;
import org.beehyv.dhis2openimis.adapter.openimis.pojo.claimResponse.ClaimResponse;
import org.beehyv.dhis2openimis.adapter.util.ExtensionUrlUtil;
import org.beehyv.dhis2openimis.adapter.util.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@SuppressWarnings("rawtypes")
@Component
public class ClaimDetailsAdapter {
	
	private DataElementCache dataElementCache;
	private ProgramCache programCache;
	
	@Autowired
	public ClaimDetailsAdapter(DataElementCache dataElementCache, ProgramCache programCache) {
		this.dataElementCache = dataElementCache;
		this.programCache = programCache;
	}
	

	public DetailsJson adapt(ClaimResponse imisClaimResponse, Claim imisClaim) throws ObjectNotFoundException {
		DetailsJson json = new DetailsJson();

		String programId = programCache.getByDisplayName("Claims management").getId();
		json.setProgram(programId);
		json.setStatus("COMPLETED");
		String eventDateString = getEventDateString(imisClaim);
		json.setCompletedDate(eventDateString);
		json.setEventDate(eventDateString);
		
		List<DataValue> dataValues = new ArrayList<>();
		fillClaimStatus(dataValues, imisClaimResponse);
		fillClaimAmount(dataValues, imisClaim);
//		fillAdjustedAmountItem(dataValues, imisClaim);
		fillApprovedAmountItem(dataValues, imisClaimResponse);
//		fillValuatedAmountItem(dataValues, imisClaim);
		fillStatusDate(dataValues, imisClaimResponse);
//		fillDeductibleAmountItem(dataValues, imisClaim);
//		fillExceedCeilingAmountItem(dataValues, imisClaim);
//		fillRemuneratedAmountItem(dataValues, imisClaim);
		
		json.setDataValues(dataValues);
		
		return json;
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
	
	private void fillClaimStatus(List<DataValue> dataValues, ClaimResponse imisClaimResponse) {
		try {
			String value = imisClaimResponse.getOutcome().getText();
			value = value.substring(0, 1).toUpperCase() + value.substring(1);
			String dataElementId = dataElementCache.get("Claim status");
			DataValue<String> dataValue = new DataValue<>(dataElementId, value);
			dataValues.add(dataValue);
		} catch(NullPointerException e) {
			//Do nothing as no proper mapping was found.
		}
	}
	
	
	private void fillClaimAmount(List<DataValue> dataValues, Claim openImisClaim) {
		try {
			Double value = openImisClaim.getTotal().getValue();
			String dataElementId = dataElementCache.get("Claim amount");
			DataValue<Double> dataValue = new DataValue<>(dataElementId, value);
			dataValues.add(dataValue);
		} catch(NullPointerException e) {
			//Do nothing as no proper mapping was found.
		}
	}
	
	//TODO fill proper mapping
	private void fillAdjustedAmountItem(List<DataValue> dataValues, Claim openImisClaim) {
		String dataElementId = dataElementCache.get("Adjusted amount - Item");
		Integer value = 1002;
		DataValue<Integer> dataValue = new DataValue<>(dataElementId, value);
		dataValues.add(dataValue);
	}
	
	private void fillApprovedAmountItem(List<DataValue> dataValues, ClaimResponse imisClaimResponse) {
		try {
			Double value = imisClaimResponse.getTotalBenefit().getValue();
			String dataElementId = dataElementCache.get("Approved amount");
			DataValue<Double> dataValue = new DataValue<>(dataElementId, value);
			dataValues.add(dataValue);
		} catch (NullPointerException e) {
			// Do nothing as no proper mapping was found.
		}
	}
	
	//TODO fill proper mapping
	private void fillValuatedAmountItem(List<DataValue> dataValues, Claim openImisClaim) {
		String dataElementId = dataElementCache.get("Valuated amount - Item");
		Integer value = 1004;
		DataValue<Integer> dataValue = new DataValue<>(dataElementId, value);
		dataValues.add(dataValue);
	}
	
	private void fillStatusDate(List<DataValue> dataValues, ClaimResponse imisClaimResponse) {
		try {
			String status = imisClaimResponse.getOutcome().getText();
			if(status.equals("rejected")) {
				fillRejectionDate(dataValues, imisClaimResponse);
			} else if(status.equals("valuated")) {
				fillValuationDate(dataValues, imisClaimResponse);
			} else if(status.equals("checked")) {
				fillCheckedDate(dataValues, imisClaimResponse);
			} else if(status.equals("processed")) {
				fillProcessedDate(dataValues, imisClaimResponse);
			}
		} catch(NullPointerException e) {
			// Do nothing as no proper mapping was found.
		}
	}
	
	private void fillCheckedDate(List<DataValue> dataValues, ClaimResponse imisClaimResponse) {
		try {
			String dataElementId = dataElementCache.get("Claim checked date");
			String value = imisClaimResponse.getCreated().toString();
			DataValue<String> dataValue = new DataValue<>(dataElementId, value);
			dataValues.add(dataValue);
		} catch (NullPointerException e) {
			// Do nothing as no proper mapping was found.
		}
	}
	
	private void fillValuationDate(List<DataValue> dataValues, ClaimResponse imisClaimResponse) {
		try {
			String dataElementId = dataElementCache.get("Claim valuation date");
			String value = imisClaimResponse.getCreated().toString();
			DataValue<String> dataValue = new DataValue<>(dataElementId, value);
			dataValues.add(dataValue);
		} catch (NullPointerException e) {
			// Do nothing as no proper mapping was found.
		}
	}
	
	private void fillRejectionDate(List<DataValue> dataValues, ClaimResponse imisClaimResponse) {
		try {
			String dataElementId = dataElementCache.get("Claim rejection date");
			String value = imisClaimResponse.getCreated().toString();
			DataValue<String> dataValue = new DataValue<>(dataElementId, value);
			dataValues.add(dataValue);
		} catch (NullPointerException e) {
			// Do nothing as no proper mapping was found.
		}
	}
	
	private void fillProcessedDate(List<DataValue> dataValues, ClaimResponse imisClaimResponse) {
		try {
			String dataElementId = dataElementCache.get("Claim processed date");
			String value = imisClaimResponse.getCreated().toString();
			DataValue<String> dataValue = new DataValue<>(dataElementId, value);
			dataValues.add(dataValue);
		} catch (NullPointerException e) {
			// Do nothing as no proper mapping was found.
		}
	}
	
	//TODO fill proper mapping
	private void fillDeductibleAmountItem(List<DataValue> dataValues, Claim openImisClaim) {
		String dataElementId = dataElementCache.get("Deductible amount - Item");
		Integer value = 1005;
		DataValue<Integer> dataValue = new DataValue<>(dataElementId, value);
		if(value != null) {
			dataValues.add(dataValue);
		}
	}
	
	//TODO fill proper mapping
	private void fillExceedCeilingAmountItem(List<DataValue> dataValues, Claim openImisClaim) {
		String dataElementId = dataElementCache.get("Exceed ceiling amount - Item");
		Integer value = 1006;
		DataValue<Integer> dataValue = new DataValue<>(dataElementId, value);
		if(value != null) {
			dataValues.add(dataValue);
		}
	}
	
	//TODO fill proper mapping
	private void fillRemuneratedAmountItem(List<DataValue> dataValues, Claim openImisClaim) {
		String dataElementId = dataElementCache.get("Remunerated amount - Item");
		Integer value = 1007;
		DataValue<Integer> dataValue = new DataValue<>(dataElementId, value);
		if(value != null) {
			dataValues.add(dataValue);
		}
	}
}
