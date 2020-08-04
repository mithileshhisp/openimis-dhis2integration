package org.beehyv.dhis2openimis.adapter.openimis.pojo;

public class TelecomDetail {
	private String system;
	private String use;
	private String value;
	
	public TelecomDetail() {
		
	}
	
	public String getSystem() {
		return system;
	}
	public void setSystem(String system) {
		this.system = system;
	}
	public String getUse() {
		return use;
	}
	public void setUse(String use) {
		this.use = use;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "TelecomDetail [system=" + system + ", use=" + use + ", value=" + value + "]";
	}
	
}
