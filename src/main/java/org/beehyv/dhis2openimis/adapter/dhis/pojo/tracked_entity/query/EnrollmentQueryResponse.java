package org.beehyv.dhis2openimis.adapter.dhis.pojo.tracked_entity.query;

import java.util.List;

public class EnrollmentQueryResponse {
	private List<EnrollmentDetail> enrollments;
	
	public EnrollmentQueryResponse() {
		
	}

	public List<EnrollmentDetail> getEnrollments() {
		return enrollments;
	}
	public void setEnrollments(List<EnrollmentDetail> enrollments) {
		this.enrollments = enrollments;
	}
	
	
}
