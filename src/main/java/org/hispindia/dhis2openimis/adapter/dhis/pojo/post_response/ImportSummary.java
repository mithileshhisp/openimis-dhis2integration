package org.hispindia.dhis2openimis.adapter.dhis.pojo.post_response;

public class ImportSummary {
	private String responseType;
	private String status;
	private String reference;
	private String href;
	
	public ImportSummary() {
		
	}
	
	public String getResponseType() {
		return responseType;
	}
	public void setResponseType(String responseType) {
		this.responseType = responseType;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	
}
