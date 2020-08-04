package org.beehyv.dhis2openimis.adapter.dhis.pojo.poster.event;

public class Event {
	private String trackedEntityInstance;
	private String program;
	private String programStage;
	private String orgUnit;
	private String enrollment;
	private String dueDate;
	private String status;
	
	public Event() {

	}
	
	public String getTrackedEntityInstance() {
		return trackedEntityInstance;
	}
	public void setTrackedEntityInstance(String trackedEntityInstance) {
		this.trackedEntityInstance = trackedEntityInstance;
	}
	public String getProgram() {
		return program;
	}
	public void setProgram(String program) {
		this.program = program;
	}
	public String getProgramStage() {
		return programStage;
	}
	public void setProgramStage(String programStage) {
		this.programStage = programStage;
	}
	public String getOrgUnit() {
		return orgUnit;
	}
	public void setOrgUnit(String orgUnit) {
		this.orgUnit = orgUnit;
	}
	public String getEnrollment() {
		return enrollment;
	}
	public void setEnrollment(String enrollment) {
		this.enrollment = enrollment;
	}
	public String getDueDate() {
		return dueDate;
	}
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "{\"trackedEntityInstance\":\"" + trackedEntityInstance + "\", \"program\":\"" + program + "\", \"programStage\":\""
				+ programStage + "\", \"orgUnit\":\"" + orgUnit + "\", \"enrollment\":\"" + enrollment + "\", \"dueDate\":\"" + dueDate
				+ "\", \"status\":\"" + status + "\"}";
	}
	
	
}
