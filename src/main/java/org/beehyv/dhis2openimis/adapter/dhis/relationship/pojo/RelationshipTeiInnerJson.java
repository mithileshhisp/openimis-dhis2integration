package org.beehyv.dhis2openimis.adapter.dhis.relationship.pojo;

public class RelationshipTeiInnerJson {
	private String trackedEntityInstance;

	public RelationshipTeiInnerJson(String trackedEntityInstance) {
		this.trackedEntityInstance = trackedEntityInstance;
	}
	
	public String getTrackedEntityInstance() {
		return trackedEntityInstance;
	}
	public void setTrackedEntityInstance(String trackedEntityInstance) {
		this.trackedEntityInstance = trackedEntityInstance;
	}

	@Override
	public String toString() {
		return "{\"trackedEntityInstance\":\"" + trackedEntityInstance + "\"}";
	}
	
	
}
