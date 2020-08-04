package org.beehyv.dhis2openimis.adapter.openimis.pojo.identifier;

public class IdentifierTypeCodeDetail {
	private IdentifierTypeCodename code;
	private String system;
	
	public IdentifierTypeCodeDetail() {
		
	}
	
	public IdentifierTypeCodename getCode() {
		return code;
	}
	public void setCode(IdentifierTypeCodename code) {
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
		return "IdentifierTypeCodeDetail [code=" + code + ", system=" + system + "]";
	}
	
	
}
