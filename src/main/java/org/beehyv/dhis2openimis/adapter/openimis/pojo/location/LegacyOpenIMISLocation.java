/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.beehyv.dhis2openimis.adapter.openimis.pojo.location;

/**
 *
 * @author Mithilesh Thakur
 */
public class LegacyOpenIMISLocation {
    
    private String locationId;
    private String locationCode;
    private String locationName;
    private String parentLocationId;
    private String locationType;

    
    public LegacyOpenIMISLocation() {
		
	}
    
    /**
     * @return the locationId
     */
    public String getLocationId() {
        return locationId;
    }

    /**
     * @param locationId the locationId to set
     */
    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    /**
     * @return the locationCode
     */
    public String getLocationCode() {
        return locationCode;
    }

    /**
     * @param locationCode the locationCode to set
     */
    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode;
    }

    /**
     * @return the locationName
     */
    public String getLocationName() {
        return locationName;
    }

    /**
     * @param locationName the locationName to set
     */
    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    /**
     * @return the parentLocationId
     */
    public String getParentLocationId() {
        return parentLocationId;
    }

    /**
     * @param parentLocationId the parentLocationId to set
     */
    public void setParentLocationId(String parentLocationId) {
        this.parentLocationId = parentLocationId;
    }

    /**
     * @return the locationType
     */
    public String getLocationType() {
        return locationType;
    }

    /**
     * @param locationType the locationType to set
     */
    public void setLocationType(String locationType) {
        this.locationType = locationType;
    }
    
    
    @Override
	public String toString() {
		return "LegacyOpenIMISLocation [locationId=" + locationId + ", locationCode=" + locationCode + ", locationName=" + locationName + 
                        ", parentLocationId=" + parentLocationId + ", locationType=" + locationType + "]";
	}
     
    
    
}
