package org.beehyv.dhis2openimis.adapter.openimis.pojo.patient;

public class PatientResource {

	private Patient resource;
	private String fullUrl;
	
	public PatientResource() {
		
	}

	public PatientResource(Patient resource, String fullUrl) {
		this.resource = resource;
		this.fullUrl = fullUrl;
	}

	public String getFullUrl() {
		return fullUrl;
	}

	public void setFullUrl(String fullUrl) {
		this.fullUrl = fullUrl;
	}

	public Patient getResource() {
		return resource;
	}

	public void setResource(Patient resource) {
		this.resource = resource;
	}

	@Override
	public String toString() {
		return "PatientResource [fullUrl=" + fullUrl + ", resource=" + resource + "]";
	}
	
}
