/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hispindia.dhis2openimis.adapter.openimis.pojo.location;

/**
 *
 * @author Mithilesh Thakur
 */
public class LegacyOpenIMISHealthFacility {
 
    private String hfid;
    private String hfCode;
    private String hfName;
    private String locationId;
    private String hfLevel;

    
    public LegacyOpenIMISHealthFacility() {
		
    }

    /**
     * @return the hfid
     */
    public String getHfid() {
        return hfid;
    }

    /**
     * @param hfid the hfid to set
     */
    public void setHfid(String hfid) {
        this.hfid = hfid;
    }

    /**
     * @return the hfCode
     */
    public String getHfCode() {
        return hfCode;
    }

    /**
     * @param hfCode the hfCode to set
     */
    public void setHfCode(String hfCode) {
        this.hfCode = hfCode;
    }

    /**
     * @return the hfName
     */
    public String getHfName() {
        return hfName;
    }

    /**
     * @param hfName the hfName to set
     */
    public void setHfName(String hfName) {
        this.hfName = hfName;
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
     * @return the hfLevel
     */
    public String getHfLevel() {
        return hfLevel;
    }

    /**
     * @param hfLevel the hfLevel to set
     */
    public void setHfLevel(String hfLevel) {
        this.hfLevel = hfLevel;
    }
    
    @Override
	public String toString() {
		return "LegacyOpenIMISHealthFacility [hfid=" + hfid + ", hfCode=" + hfCode + ", hfName=" + hfName + 
                        ", locationId=" + locationId + ", hfLevel=" + hfLevel + "]";
	}
}
