package org.beehyv.dhis2openimis.adapter.dhis.pojo.post_response;

public class TrackedEntityPostResponse {
	private String httpStatus;
	private Integer httpStatusCode;
	private String status;
	private String message;
	private Response response;
	
	public TrackedEntityPostResponse() {
		
	}
	
	public String getHttpStatus() {
		return httpStatus;
	}
	public void setHttpStatus(String httpStatus) {
		this.httpStatus = httpStatus;
	}
	public Integer getHttpStatusCode() {
		return httpStatusCode;
	}
	public void setHttpStatusCode(Integer httpStatusCode) {
		this.httpStatusCode = httpStatusCode;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Response getResponse() {
		return response;
	}
	public void setResponse(Response response) {
		this.response = response;
	}
	
	
}
