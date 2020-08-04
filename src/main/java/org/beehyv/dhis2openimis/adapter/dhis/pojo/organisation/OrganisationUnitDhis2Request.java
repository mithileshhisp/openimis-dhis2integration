/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.beehyv.dhis2openimis.adapter.dhis.pojo.organisation;

import java.util.List;

/**
 *
 * @author Mithilesh Thakur
 */
public class OrganisationUnitDhis2Request {
    
    private String name;
    private String code;
    private String openingDate;
    private String shortName;
    private OrganisationUnitParentRequest parent;
    
    private List<OrganisationUnitAttributeValueRequest>  attributeValues;

    public OrganisationUnitDhis2Request() {

    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the openingDate
     */
    public String getOpeningDate() {
        return openingDate;
    }

    /**
     * @param openingDate the openingDate to set
     */
    public void setOpeningDate(String openingDate) {
        this.openingDate = openingDate;
    }

    /**
     * @return the shortName
     */
    public String getShortName() {
        return shortName;
    }

    /**
     * @param shortName the shortName to set
     */
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    /**
     * @return the parent
     */
    public OrganisationUnitParentRequest getParent() {
        return parent;
    }

    /**
     * @param parent the parent to set
     */
    public void setParent(OrganisationUnitParentRequest parent) {
        this.parent = parent;
    }

    /**
     * @return the attributeValues
     */
    public List<OrganisationUnitAttributeValueRequest> getAttributeValues() {
        return attributeValues;
    }

    /**
     * @param attributeValues the attributeValues to set
     */
    public void setAttributeValues(List<OrganisationUnitAttributeValueRequest> attributeValues) {
        this.attributeValues = attributeValues;
    }
    

    @Override
    public String toString() {
            return "{\"name\":\"" + name + "\", \"shortName\":\"" + shortName + "\", \"code\":\""
                            + code + "\", \"openingDate\":\"" + openingDate +  "\", \"parent\":\"" + parent + "\", \"attributeValues\":\"" + attributeValues + "\"}";
    }    


}
