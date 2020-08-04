package org.beehyv.dhis2openimis.adapter.openimis.pojo.patient.marital;

import java.util.List;

public class MaritalStatusType {
	private List<MaritalStatusTypeCodeDetail> coding;
	
	public MaritalStatusType() {
		
	}
	
	public List<MaritalStatusTypeCodeDetail> getCoding() {
		return coding;
	}
	public void setCoding(List<MaritalStatusTypeCodeDetail> coding) {
		this.coding = coding;
	}

	@Override
	public String toString() {
		return "MaritalStatusType [coding=" + coding + "]";
	}
	
	
	
}
