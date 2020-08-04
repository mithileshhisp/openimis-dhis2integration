package org.beehyv.dhis2openimis.adapter.dhis.pojo.poster.enrollment;

public class EnrollmentUpdateBody {
	private String enrollment;
	private String enrollmentDate;
	private String orgUnit;
	private String program;
	private String trackedEntityInstance;
	
	
	public EnrollmentUpdateBody() {
		
	}
	
	public EnrollmentUpdateBody(String enrollment, String enrollmentDate, String orgUnit, String program,
			String trackedEntityInstance) {
		super();
		this.enrollment = enrollment;
		this.enrollmentDate = enrollmentDate;
		this.orgUnit = orgUnit;
		this.program = program;
		this.trackedEntityInstance = trackedEntityInstance;
	}

	public String getEnrollment() {
		return enrollment;
	}
	public void setEnrollment(String enrollment) {
		this.enrollment = enrollment;
	}
	public String getEnrollmentDate() {
		return enrollmentDate;
	}
	public void setEnrollmentDate(String enrollmentDate) {
		this.enrollmentDate = enrollmentDate;
	}
	public String getOrgUnit() {
		return orgUnit;
	}
	public void setOrgUnit(String orgUnit) {
		this.orgUnit = orgUnit;
	}
	public String getProgram() {
		return program;
	}
	public void setProgram(String program) {
		this.program = program;
	}
	public String getTrackedEntityInstance() {
		return trackedEntityInstance;
	}
	public void setTrackedEntityInstance(String trackedEntityInstance) {
		this.trackedEntityInstance = trackedEntityInstance;
	}
	
	
}
