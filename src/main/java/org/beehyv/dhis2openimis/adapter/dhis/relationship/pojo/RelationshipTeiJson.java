package org.beehyv.dhis2openimis.adapter.dhis.relationship.pojo;

public class RelationshipTeiJson {
	private RelationshipTeiInnerJson trackedEntityInstance;
	
	public RelationshipTeiJson() {
		
	}
	
	public RelationshipTeiJson(String trackedEntityInstance) {
		this.trackedEntityInstance = new RelationshipTeiInnerJson(trackedEntityInstance);
	}

	
	public RelationshipTeiInnerJson getTrackedEntityInstance() {
		return trackedEntityInstance;
	}
	public void setTrackedEntityInstance(RelationshipTeiInnerJson trackedEntityInstance) {
		this.trackedEntityInstance = trackedEntityInstance;
	}

	@Override
	public String toString() {
		return "{\"trackedEntityInstance\":" + trackedEntityInstance.toString() + "}";
	}
	
	
}
