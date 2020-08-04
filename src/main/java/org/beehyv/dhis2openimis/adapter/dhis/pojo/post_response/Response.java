package org.beehyv.dhis2openimis.adapter.dhis.pojo.post_response;

import java.util.List;

public class Response {
	private String responseType;
	private String status;
	private List<ImportSummary> importSummaries;
	
	public Response() {
		
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
	public List<ImportSummary> getImportSummaries() {
		return importSummaries;
	}
	public void setImportSummaries(List<ImportSummary> importSummaries) {
		this.importSummaries = importSummaries;
	}
	
	
}
