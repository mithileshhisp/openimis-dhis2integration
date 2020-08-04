package org.beehyv.dhis2openimis.adapter.openimis.pojo;

import java.util.List;

public class NameDetail {
	private String family;
	private List<String> given;
	private String use;
	
	public NameDetail() {
		
	}
	
	public String getFamily() {
		return family;
	}
	public void setFamily(String family) {
		this.family = family;
	}
	public List<String> getGiven() {
		return given;
	}
	public void setGiven(List<String> given) {
		this.given = given;
	}
	public String getUse() {
		return use;
	}
	public void setUse(String use) {
		this.use = use;
	}

	@Override
	public String toString() {
		return "NameDetail [family=" + family + ", given=" + given + ", use=" + use + "]";
	}
	
}
