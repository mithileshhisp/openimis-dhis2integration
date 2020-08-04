package org.beehyv.dhis2openimis.adapter.openimis.pojo;

public class Address {
	private String text;
	private String type;
	private String use;
	
	public Address() {
		
	}
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUse() {
		return use;
	}
	public void setUse(String use) {
		this.use = use;
	}

	@Override
	public String toString() {
		return "Address [text=" + text + ", type=" + type + ", use=" + use + "]";
	}
	
	
}
