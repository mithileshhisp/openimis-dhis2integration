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
public class OrganisationUnitDhis2 {
    
    private String code;
    private String id;
    private String displayName;
    private String level;
    private String shortName;
    private List<OrganisationUnitAttributeValue>  attributeValues;

    public OrganisationUnitDhis2() {

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
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the displayName
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * @param displayName the displayName to set
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    /**
     * @return the level
     */
    public String getLevel() {
        return level;
    }

    /**
     * @param level the level to set
     */
    public void setLevel(String level) {
        this.level = level;
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
     * @return the attributeValues
     */
    public List<OrganisationUnitAttributeValue> getAttributeValues() {
        return attributeValues;
    }

    /**
     * @param attributeValues the attributeValues to set
     */
    public void setAttributeValues(List<OrganisationUnitAttributeValue> attributeValues) {
        this.attributeValues = attributeValues;
    }


    @Override
    public String toString() {
            return "OrganisationUnitDhis2 [code=" + code + ", id=" + id + ", displayName=" + displayName +
                    ", level=" + level + ", shortName=" + shortName + ", attributeValues=" + attributeValues + "]";
    }
}
