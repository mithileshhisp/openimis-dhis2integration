package org.beehyv.dhis2openimis.adapter.dhis.pojo.tracked_entity.query;

/**
 * 
 * @author Shubham Jaiswal
 * The actual response has other fields, but currently we only cared about getting the enrollment id.
 */
public class EnrollmentDetail {
	private String enrollment;
	private String program;
	
	public EnrollmentDetail() {
		
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
	
	
}
