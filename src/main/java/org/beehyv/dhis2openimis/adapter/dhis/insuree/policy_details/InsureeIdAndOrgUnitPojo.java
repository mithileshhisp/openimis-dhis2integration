package org.beehyv.dhis2openimis.adapter.dhis.insuree.policy_details;

public class InsureeIdAndOrgUnitPojo {
	private String insureeId;
	private String orgUnitId;
	
	
	public InsureeIdAndOrgUnitPojo(String insureeId, String orgUnitId) {
		this.insureeId = insureeId;
		this.orgUnitId = orgUnitId;
	}
	
	
	public String getInsureeId() {
		return insureeId;
	}
	public String getOrgUnitId() {
		return orgUnitId;
	}
	
	
	
}
