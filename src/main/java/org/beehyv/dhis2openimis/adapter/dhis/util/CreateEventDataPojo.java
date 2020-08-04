package org.beehyv.dhis2openimis.adapter.dhis.util;


/**
 * This is meant as an internal class. Only to be used as return POJO of posting Tracked Entity Instances, 
 * and forwarding necessary details to EventPoster
 * So some setters and basic constructor are removed, to lessen the chance of anyone else using this class.
 * @author Shubham Jaiswal
 *
 */
public class CreateEventDataPojo {
	private String trackedEntityInstance;
	private String orgUnit;
	private String enrollmentId;
	
	public CreateEventDataPojo(String trackedEntityInstance, String orgUnit, String enrollmentId) {
		this.trackedEntityInstance = trackedEntityInstance;
		this.orgUnit = orgUnit;
		this.enrollmentId = enrollmentId;
	}
	
	public String getTrackedEntityInstance() {
		return trackedEntityInstance;
	}
	public String getOrgUnit() {
		return orgUnit;
	}
	public String getEnrollmentId() {
		return enrollmentId;
	}
	
}
