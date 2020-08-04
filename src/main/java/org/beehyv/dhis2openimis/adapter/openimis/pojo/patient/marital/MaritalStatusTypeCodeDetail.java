package org.beehyv.dhis2openimis.adapter.openimis.pojo.patient.marital;

public class MaritalStatusTypeCodeDetail {
	private MaritalStatusTypeCodename code;
	private String system;
	
	public MaritalStatusTypeCodeDetail() {
		
	}
	
	public MaritalStatusTypeCodename getCode() {
		return code;
	}
	public void setCode(MaritalStatusTypeCodename code) {
		this.code = code;
	}
	public String getSystem() {
		return system;
	}
	public void setSystem(String system) {
		this.system = system;
	}

	@Override
	public String toString() {
		return "MaritalStatusTypeCodeDetail [code=" + code + ", system=" + system + "]";
	}
	
	
}
