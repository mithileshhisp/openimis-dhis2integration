package org.beehyv.dhis2openimis.adapter.dhis.claim;

import java.util.ArrayList;
import java.util.List;

import org.beehyv.dhis2openimis.adapter.dhis.cache.TrackedEntityTypeCache;
import org.beehyv.dhis2openimis.adapter.dhis.cache.attribute.TrackedEntityAttributeCache;
import org.beehyv.dhis2openimis.adapter.dhis.cache.attribute_options.DiagnosisCacheService;
import org.beehyv.dhis2openimis.adapter.dhis.cache.attribute_options.VisitTypeCacheService;
import org.beehyv.dhis2openimis.adapter.dhis.pojo.poster.Attribute;
import org.beehyv.dhis2openimis.adapter.dhis.pojo.poster.TrackedEntityRequest;
import org.beehyv.dhis2openimis.adapter.openimis.pojo.claim.Claim;
import org.beehyv.dhis2openimis.adapter.util.exception.InternalException;
import org.beehyv.dhis2openimis.adapter.util.exception.ObjectNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@SuppressWarnings("rawtypes")
@Component
public class ClaimAdapter {
	
	private static final Logger logger = LoggerFactory.getLogger(ClaimAdapter.class);
	
	@Autowired
	private TrackedEntityAttributeCache attributeCache;
	
	@Autowired
	private DiagnosisCacheService diagnosisCache;
	
	@Autowired
	private VisitTypeCacheService visitCache;
	
	@Autowired
	private TrackedEntityTypeCache entityTypeCache;
	
	@Autowired
	private ClaimUtil claimUtil;

	
	public String getCreatedDate(Claim openImisClaim) {
		return openImisClaim.getCreated().toString();
	}
	
	public TrackedEntityRequest adapt(Claim openimisClaim) throws ObjectNotFoundException, InternalException {
		TrackedEntityRequest dhisClaim = new TrackedEntityRequest();
		List<Attribute> claimAttributes = new ArrayList<Attribute>();
		
		fillTrackedEntityType(dhisClaim);
		fillOrgUnit(dhisClaim, openimisClaim);
		
		fillDiagnosisAttribute(claimAttributes, openimisClaim);
		fillVisitTypeAttribute(claimAttributes, openimisClaim);
		fillClaimAdminAttribute(claimAttributes, openimisClaim);
		fillClaimNumberAttribute(claimAttributes, openimisClaim);
		fillSecondaryDiagnosis1Attribute(claimAttributes, openimisClaim);
		fillSecondaryDiagnosis2Attribute(claimAttributes, openimisClaim);
		fillSecondaryDiagnosis3Attribute(claimAttributes, openimisClaim);
		fillSecondaryDiagnosis4Attribute(claimAttributes, openimisClaim);
		
		dhisClaim.setAttributes(claimAttributes);
		
		return dhisClaim;
	}
	
	
	//TODO Change this to proper..
	private void fillTrackedEntityType(TrackedEntityRequest dhisClaim) {
		String trackedEntityType = entityTypeCache.getByDisplayName("Person");
		dhisClaim.setTrackedEntityType(trackedEntityType);
	}
	
	private void fillOrgUnit(TrackedEntityRequest dhisClaim, Claim openimisClaim) throws ObjectNotFoundException {
		String orgUnit = claimUtil.getOrgUnitFromImisClaim(openimisClaim);
		dhisClaim.setOrgUnit(orgUnit);
	}
	
	
	private void fillDiagnosisAttribute(List<Attribute> claimAttributes, Claim openimisClaim) {
		String diagnosisAttribute = attributeCache.get("Diagnosis");
		String diagnosisCode;
		try {
			String diagnosisString = openimisClaim.getDiagnosis().get(0).getDiagnosisCodeableConcept().getCoding().get(0).getCode();
			diagnosisCode = diagnosisString.substring(0,3);
			String diagnosisValue = diagnosisCache.getCodeForDiagnosis(diagnosisCode);
			claimAttributes.add(new Attribute<String>(diagnosisAttribute, diagnosisValue));
		} catch(NullPointerException | IndexOutOfBoundsException e) {
			logger.error("No primary diagnosis data found for: " + openimisClaim.getId());
		}
	}
	
	
	private void fillVisitTypeAttribute(List<Attribute> claimAttributes, Claim openimisClaim) throws InternalException {
		String attribute = attributeCache.get("Visit type");
		String imisVisitTypeCode = openimisClaim.getType().getText();
		String typeString;
		if("R".equals(imisVisitTypeCode)){
			typeString = "Referral";
		} else if("O".equals(imisVisitTypeCode)){
			typeString = "Other";
		} else if("E".equals(imisVisitTypeCode)){
			typeString = "Emergency";
		} else {
			throw new InternalException("Visit type couldn't be mapped.");
		}
		String value = visitCache.getCodeForVisitType(typeString);
		claimAttributes.add(new Attribute<String>(attribute, value));
	}
	
	//TODO Confirm if the mapping is right.
	private void fillClaimAdminAttribute(List<Attribute> claimAttributes, Claim openimisClaim) {
		String attribute = attributeCache.get("Claim administrator");
		String value = openimisClaim.getEnterer().getReference();
		value = value.substring(13);   //Removing the "Practitioner/" before the id.
		claimAttributes.add(new Attribute<String>(attribute, value));
	}
	
	//TODO Confirm if the mapping is right.
	private void fillClaimNumberAttribute(List<Attribute> claimAttributes, Claim openimisClaim) {
		String attribute = attributeCache.get("Claim number");
		String value = claimUtil.getClaimCodeFromImisClaim(openimisClaim);
		claimAttributes.add(new Attribute<String>(attribute, value));
	}
	
	/*
	 * The openIMIS has a array of diagnosis. I am assuming the first one in that array will be primary diagnosis,
	 * followed by secondary diagnosis #.
	*/
	private void fillSecondaryDiagnosis1Attribute(List<Attribute> claimAttributes, Claim openimisClaim) {
		String diagnosisAttribute = attributeCache.get("Secondary diagnosis 1");
		try {
			String diagnosisString = openimisClaim.getDiagnosis().get(1).getDiagnosisCodeableConcept().getCoding().get(0).getCode();
			String diagnosisCode = diagnosisString.substring(0,3);
			String diagnosisValue = diagnosisCache.getCodeForDiagnosis(diagnosisCode);
			claimAttributes.add(new Attribute<String>(diagnosisAttribute, diagnosisValue));
		} catch(IndexOutOfBoundsException e) {
			logger.debug("No secondary diagnosis 1 found.");
		}
	}
	
	private void fillSecondaryDiagnosis2Attribute(List<Attribute> claimAttributes, Claim openimisClaim) {
		String diagnosisAttribute = attributeCache.get("Secondary diagnosis 2");
		try {
			String diagnosisString = openimisClaim.getDiagnosis().get(2).getDiagnosisCodeableConcept().getCoding().get(0).getCode();
			String diagnosisCode = diagnosisString.substring(0,3);
			String diagnosisValue = diagnosisCache.getCodeForDiagnosis(diagnosisCode);
			claimAttributes.add(new Attribute<String>(diagnosisAttribute, diagnosisValue));
		} catch(IndexOutOfBoundsException e) {
			logger.debug("No secondary diagnosis 2 found.");
		}
	}
	
	private void fillSecondaryDiagnosis3Attribute(List<Attribute> claimAttributes, Claim openimisClaim) {
		String diagnosisAttribute = attributeCache.get("Secondary diagnosis 3");
		try {
			String diagnosisString = openimisClaim.getDiagnosis().get(3).getDiagnosisCodeableConcept().getCoding().get(0).getCode();
			String diagnosisCode = diagnosisString.substring(0,3);
			String diagnosisValue = diagnosisCache.getCodeForDiagnosis(diagnosisCode);
			claimAttributes.add(new Attribute<String>(diagnosisAttribute, diagnosisValue));
		} catch(IndexOutOfBoundsException e) {
			logger.debug("No secondary diagnosis 3 found.");
		}
	}
	
	private void fillSecondaryDiagnosis4Attribute(List<Attribute> claimAttributes, Claim openimisClaim) {
		String diagnosisAttribute = attributeCache.get("Secondary diagnosis 4");
		try {
			String diagnosisString = openimisClaim.getDiagnosis().get(4).getDiagnosisCodeableConcept().getCoding().get(0).getCode();
			String diagnosisCode = diagnosisString.substring(0,3);
			String diagnosisValue = diagnosisCache.getCodeForDiagnosis(diagnosisCode);
			claimAttributes.add(new Attribute<String>(diagnosisAttribute, diagnosisValue));
		} catch(IndexOutOfBoundsException e) {
			logger.debug("No secondary diagnosis 4 found.");
		}
	}
	
}
