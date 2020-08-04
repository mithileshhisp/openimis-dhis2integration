package org.beehyv.dhis2openimis.adapter.dhis.insuree;

import org.beehyv.dhis2openimis.adapter.dhis.cache.TrackedEntityTypeCache;
import org.beehyv.dhis2openimis.adapter.dhis.cache.attribute.TrackedEntityAttributeCache;
import org.beehyv.dhis2openimis.adapter.dhis.cache.org_unit.OrganisationUnitCacheService;
import org.beehyv.dhis2openimis.adapter.dhis.pojo.poster.Attribute;
import org.beehyv.dhis2openimis.adapter.dhis.pojo.poster.TrackedEntityRequest;
import org.beehyv.dhis2openimis.adapter.openimis.pojo.ExtensionItem;
import org.beehyv.dhis2openimis.adapter.openimis.pojo.TelecomDetail;
import org.beehyv.dhis2openimis.adapter.openimis.pojo.patient.Patient;
import org.beehyv.dhis2openimis.adapter.util.ExtensionUrlUtil;
import org.beehyv.dhis2openimis.adapter.util.exception.ObjectNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("rawtypes")
@Component
public class InsureeAdapter {

    private static final Logger logger = LoggerFactory.getLogger(InsureeAdapter.class);

    @Autowired
    private TrackedEntityAttributeCache attributeCache;

    @Autowired
    private TrackedEntityTypeCache entityTypeCache;

    @Autowired
    private OrganisationUnitCacheService orgUnitCache;


    public TrackedEntityRequest adapt(Patient openimisPatient) throws ObjectNotFoundException {

        TrackedEntityRequest dhisInsuree = new TrackedEntityRequest();
        
        List<Attribute> insureeAttributes = new ArrayList<Attribute>();

        fillTrackedEntityType(dhisInsuree);
        fillOrgUnit(dhisInsuree, openimisPatient);

//        fillFamilyIDAttribute(insureeAttributes, openimisPatient);
//        fillCHFIDAttribute(insureeAttributes, openimisPatient);
//        fillPovertyStatusAttribute(insureeAttributes, openimisPatient);
//        fillFamGroupTypeAttribute(insureeAttributes, openimisPatient);
        fillHouseHoldHeadAttribute(insureeAttributes, openimisPatient);
        fillFirstNameAttribute(insureeAttributes, openimisPatient);
        fillLastNameAttribute(insureeAttributes, openimisPatient);
        fillDoBAttribute(insureeAttributes, openimisPatient);
        fillGenderAttribute(insureeAttributes, openimisPatient);
        fillMaritalStatusAttribute(insureeAttributes, openimisPatient);
//        fillInsuranceNumberAttribute(insureeAttributes, openimisPatient);
        fillPhoneNumberAttribute(insureeAttributes, openimisPatient);
//        fillFirstServicePointAttribute(insureeAttributes, openimisPatient);
        fillEducationAttribute(insureeAttributes, openimisPatient);
        fillProfessionAttribute(insureeAttributes, openimisPatient);
//        fillIdAttribute(insureeAttributes, openimisPatient);
//        fillIdTypeAttribute(insureeAttributes, openimisPatient);
        fillInsureeIdAttribute(insureeAttributes, openimisPatient);

        dhisInsuree.setAttributes(insureeAttributes);

        return dhisInsuree;
    }

    private void fillTrackedEntityType(TrackedEntityRequest dhisInsuree) {
        String trackedEntityType = entityTypeCache.getByDisplayName("Person");
        dhisInsuree.setTrackedEntityType(trackedEntityType);
    }

    
    private void fillOrgUnit(TrackedEntityRequest dhisInsuree, Patient openImisPatient) throws ObjectNotFoundException {
        String orgUnit = getOrgUnit(openImisPatient);
        dhisInsuree.setOrgUnit(orgUnit);
    }
    
    
    public String getOrgUnit(Patient openImisPatient) throws ObjectNotFoundException {
    	if (openImisPatient.getExtension() != null) {
            for (ExtensionItem extensionItem : openImisPatient.getExtension()) {
                if (ExtensionUrlUtil.LOCATION_CODE_URL.equals(extensionItem.getUrl())) {
                	String locationCode = extensionItem.getValueString();
                	String orgUnit = orgUnitCache.getByCode(locationCode);
                    return orgUnit;
                }
            }
        } 
    	throw new ObjectNotFoundException("No location code extension found!");
    }

    private void fillFamilyIDAttribute(List<Attribute> insureeAttributes, Patient openimisPatient) {
        String attribute = attributeCache.get("Family ID");
        //TODO find what in imis claim maps to visit type
        Integer id = 1;
        insureeAttributes.add(new Attribute<Integer>(attribute, id));
    }

    private void fillCHFIDAttribute(List<Attribute> insureeAttributes, Patient openimisPatient) {
        String attribute = attributeCache.get("CHFID");
        //TODO find what in imis claim maps to visit type
        Integer id = 1;
        insureeAttributes.add(new Attribute<Integer>(attribute, id));
    }

    private void fillPovertyStatusAttribute(List<Attribute> insureeAttributes, Patient openimisPatient) {
        String attribute = attributeCache.get("Poverty status");
        //TODO find what in imis claim maps to visit type
        String status = "Yes";
        insureeAttributes.add(new Attribute<String>(attribute, status));
    }

    private void fillFamGroupTypeAttribute(List<Attribute> insureeAttributes, Patient openimisPatient) {
        String attribute = attributeCache.get("Family/Group type");
        //TODO find what in imis claim maps to visit type
        String status = "Council";
        insureeAttributes.add(new Attribute<String>(attribute, status));
    }

    private void fillHouseHoldHeadAttribute(List<Attribute> insureeAttributes, Patient openimisPatient) {
        String attribute = attributeCache.get("Household head");
        //TODO find what in imis claim maps to visit type

        String value = "";
        Boolean isHead = true;
        if (openimisPatient.getExtension() != null) {
            for (ExtensionItem extensionItem : openimisPatient.getExtension()
            ) {
                if (ExtensionUrlUtil.IS_HEAD_URL.equals(extensionItem.getUrl())) {
                    isHead = extensionItem.getValueBoolean();
                    break;
                }
            }
        } else {
            isHead = false;
        }
        if (isHead) {
            value = "Yes";
        } else {
            value = "No";
        }
        insureeAttributes.add(new Attribute<String>(attribute, value));
    }

    private void fillFirstNameAttribute(List<Attribute> insureeAttributes, Patient openimisPatient) {
        String attribute = attributeCache.get("First name");
        String value;
        try {
        	value = openimisPatient.getName().get(0).getGiven().get(0);
        } catch(NullPointerException | IndexOutOfBoundsException e) {
        	value = "";
        }
        insureeAttributes.add(new Attribute<String>(attribute, value));
    }

    private void fillLastNameAttribute(List<Attribute> insureeAttributes, Patient openimisPatient) {
        String attribute = attributeCache.get("Last name");
        String value = "";
        try {
            value = openimisPatient.getName().get(0).getFamily();
        } catch(NullPointerException | IndexOutOfBoundsException e) {
            value = "";
        }
        insureeAttributes.add(new Attribute<String>(attribute, value));
    }

    private void fillDoBAttribute(List<Attribute> insureeAttributes, Patient openimisPatient) {
        String attribute = attributeCache.get("Date of birth");
        try {
            String value = openimisPatient.getBirthDate();
            insureeAttributes.add(new Attribute<String>(attribute, value));
        } catch(NullPointerException e) {
        	//Do nothing. No need to add this attribute, as a proper value couldn't be determined.
        }
    }

    private void fillGenderAttribute(List<Attribute> insureeAttributes, Patient openimisPatient) {
        String attribute = attributeCache.get("Gender");
        try {
	        String status = openimisPatient.getGender();
	        status = status.substring(0,1).toUpperCase() + status.substring(1).toLowerCase();
	        insureeAttributes.add(new Attribute<String>(attribute, status));
        } catch(NullPointerException | IndexOutOfBoundsException e) {
        	//Do nothing. No need to add this attribute, as a proper value couldn't be determined.
        } 
    }

    private void fillMaritalStatusAttribute(List<Attribute> insureeAttributes, Patient openimisPatient) {
        String attribute = attributeCache.get("Marital status");
        try {
            String code = openimisPatient.getMaritalStatus().getCoding().get(0).getCode().toString();
            String value;
            
            if ("M".equals(code)) {
                value = "Married";
            } else if ("S".equals(code)) {
                value = "Single";
            } else if ("D".equals(code)) {
                value = "Divorced";
            } else if ("W".equals(code)) {
                value = "Widowed";
            } else {
            	logger.debug("Marital status mapping for imis Patient: '" + openimisPatient.getId() + "' doesn't map to any known.");
            	value = "";
            }
            
            if(!value.equals("")) {
            	insureeAttributes.add(new Attribute<String>(attribute, value));
            }
        } catch(NullPointerException | IndexOutOfBoundsException e) {
        	//Do nothing. No need to add this attribute, as a proper value couldn't be determined.
        }
    }

    private void fillInsuranceNumberAttribute(List<Attribute> insureeAttributes, Patient openimisPatient) {
        String attribute = attributeCache.get("Insurance number");
        String value = "";
        if (openimisPatient != null) {
            value = openimisPatient.getId();
            if (value == null) {
                value = "";
            }
        } else {
            value = "";
        }
        insureeAttributes.add(new Attribute(attribute, value));
    }

    private void fillPhoneNumberAttribute(List<Attribute> insureeAttributes, Patient openimisPatient) {
        String attribute = attributeCache.get("Phone number");
        //TODO find what in imis claim maps to visit type
        String value = "";
        if (openimisPatient.getTelecom() != null) {
            for (TelecomDetail telecomDetail : openimisPatient.getTelecom()
            ) {
                if ("Phone".equals(telecomDetail.getSystem())) {
                    value = telecomDetail.getValue();
                    break;
                }
            }
        } else {
            value = "";
        }
        insureeAttributes.add(new Attribute(attribute, value));
    }

    private void fillFirstServicePointAttribute(List<Attribute> insureeAttributes, Patient openimisPatient) {
        String attribute = attributeCache.get("First service point");
        //TODO find what in imis claim maps to visit type
        String status = "";
        insureeAttributes.add(new Attribute(attribute, status));
    }

    private void fillEducationAttribute(List<Attribute> insureeAttributes, Patient openimisPatient) {
        String attribute = attributeCache.get("Education");
        try {
            for (ExtensionItem extensionItem : openimisPatient.getExtension()) {
                if (ExtensionUrlUtil.EDUCATION_CODE_URL.equals(extensionItem.getUrl())) {
                    String value = extensionItem.getValueString();
                    insureeAttributes.add(new Attribute<String>(attribute, value));
                    break;
                }
            }
        } catch(NullPointerException | IndexOutOfBoundsException e) {
        	//Do nothing. No need to add this attribute, as a proper value couldn't be determined.
        }
    }

    private void fillProfessionAttribute(List<Attribute> insureeAttributes, Patient openimisPatient) {
        String attribute = attributeCache.get("Profession");
        try {
            for (ExtensionItem extensionItem : openimisPatient.getExtension()) {
                if (ExtensionUrlUtil.PROFESSION_CODE_URL.equals(extensionItem.getUrl())) {
                    String value = extensionItem.getValueString();
                    insureeAttributes.add(new Attribute<String>(attribute, value));
                    break;
                }
            }
        } catch(NullPointerException | IndexOutOfBoundsException e) {
        	//Do nothing. No need to add this attribute, as a proper value couldn't be determined.
        }
    }

    private void fillIdAttribute(List<Attribute> insureeAttributes, Patient openimisPatient) {
        String attribute = attributeCache.get("Identification number");
        //TODO find what in imis claim maps to visit type
        String value = "";
        insureeAttributes.add(new Attribute(attribute, value));
    }

    private void fillIdTypeAttribute(List<Attribute> insureeAttributes, Patient openimisPatient) {
        String attribute = attributeCache.get("Identification type");
        //TODO find what in imis claim maps to visit type
        String status = "Voter's ID";
        insureeAttributes.add(new Attribute(attribute, status));
    }

    private void fillInsureeIdAttribute(List<Attribute> insureeAttributes, Patient openimisPatient) {
        String attribute = attributeCache.get("Insuree ID");
        String value = getInsureeId(openimisPatient);
        insureeAttributes.add(new Attribute<String>(attribute, value));
    }

    public String getInsureeId(Patient openimisPatient) {
    	//TODO find what in imis claim maps to visit type
        String value = "12345";
        if (openimisPatient != null) {
            value = openimisPatient.getId();
            if (value == null) {
                value = "";
            }
        }
        return value;
    }

	public String getRegistrationDate(Patient openImisPatient) throws ObjectNotFoundException {
		try {
            for (ExtensionItem extensionItem : openImisPatient.getExtension()) {
                if (ExtensionUrlUtil.REGISTRATION_DATE_URL.equals(extensionItem.getUrl())) {
                    String registrationDateTimeString = extensionItem.getValueString();
                    //Format YYYY-MM-DD. 10 chars
                    String registrationDateString = registrationDateTimeString.substring(0, 10);
                    return registrationDateString;
                }
            }
            throw new ObjectNotFoundException("No registration date found.");
        } catch(NullPointerException | IndexOutOfBoundsException e) {
        	throw new ObjectNotFoundException("No registration date found.");
        }
	}
}
