package org.beehyv.dhis2openimis.adapter.dhis.pojo.tracked_entity;

public class TrackedEntityAttribute {
	private String id;
	private String code;
	private String displayName;
	
	public TrackedEntityAttribute() {
		
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	
}
