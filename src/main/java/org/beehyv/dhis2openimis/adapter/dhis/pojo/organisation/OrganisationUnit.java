package org.beehyv.dhis2openimis.adapter.dhis.pojo.organisation;

public class OrganisationUnit {
	private String code;
	private String id;
	private String displayName;
	
	public OrganisationUnit() {
		
	}
	
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	@Override
	public String toString() {
		return "OrganisationUnit [code=" + code + ", id=" + id + ", displayName=" + displayName + "]";
	}

	
	
	
}
