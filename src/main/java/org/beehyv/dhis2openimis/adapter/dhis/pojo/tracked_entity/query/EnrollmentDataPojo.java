package org.beehyv.dhis2openimis.adapter.dhis.pojo.tracked_entity.query;

//There are more fields in the response, we just needed enrollmentDate.
public class EnrollmentDataPojo {
	private String enrollment;
	private String program;
	private String enrollmentDate;
	
	
	public EnrollmentDataPojo() {
		super();
	}
	
	
	public String getEnrollment() {
		return enrollment;
	}
	public void setEnrollment(String enrollment) {
		this.enrollment = enrollment;
	}
	public String getProgram() {
		return program;
	}
	public void setProgram(String program) {
		this.program = program;
	}
	public String getEnrollmentDate() {
		return enrollmentDate;
	}
	public void setEnrollmentDate(String enrollmentDate) {
		this.enrollmentDate = enrollmentDate;
	}
	
	
}
