package org.beehyv.dhis2openimis.adapter.openimis.pojo.identifier;

import java.util.List;

public class IdentifierType {
	private List<IdentifierTypeCodeDetail> coding;
	
	public IdentifierType() {
		
	}
	
	public List<IdentifierTypeCodeDetail> getCoding() {
		return coding;
	}
	public void setCoding(List<IdentifierTypeCodeDetail> coding) {
		this.coding = coding;
	}

	@Override
	public String toString() {
		return "IdentifierType [coding=" + coding + "]";
	}
	
}
