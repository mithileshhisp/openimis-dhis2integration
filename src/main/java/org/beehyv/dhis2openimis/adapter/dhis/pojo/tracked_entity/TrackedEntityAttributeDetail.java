package org.beehyv.dhis2openimis.adapter.dhis.pojo.tracked_entity;


public class TrackedEntityAttributeDetail {
	private String code;
	private String id;
	private String href;
	private String shortName;
	private String displayName;
	private OptionSet optionSet;
	
	public TrackedEntityAttributeDetail() {
		
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
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public OptionSet getOptionSet() {
		return optionSet;
	}
	public void setOptionSet(OptionSet optionSet) {
		this.optionSet = optionSet;
	}
	
	
}
