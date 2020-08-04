package org.beehyv.dhis2openimis.adapter.dhis.pojo.poster;

import java.util.List;

@SuppressWarnings("rawtypes")
public class TrackedEntityRequest {
	private String trackedEntityType;
	private String orgUnit;
	private List<Attribute> attributes;
	
	public TrackedEntityRequest() {
		
	}
	
	public TrackedEntityRequest(String trackedEntityType, String orgUnit, List<Attribute> attributes) {
		this.trackedEntityType = trackedEntityType;
		this.orgUnit = orgUnit;
		this.attributes = attributes;
	}

	
	public String getTrackedEntityType() {
		return trackedEntityType;
	}
	public void setTrackedEntityType(String trackedEntityType) {
		this.trackedEntityType = trackedEntityType;
	}
	public String getOrgUnit() {
		return orgUnit;
	}
	public void setOrgUnit(String orgUnit) {
		this.orgUnit = orgUnit;
	}
	public List<Attribute> getAttributes() {
		return attributes;
	}
	public void setAttributes(List<Attribute> attributes) {
		this.attributes = attributes;
	}



	@Override
	public String toString() {
		return "{\"trackedEntityType\":\"" + trackedEntityType + "\", \"orgUnit\":\"" + orgUnit + "\", \"attributes\":"
				+ attributes + "}";
	}
	
	
}
