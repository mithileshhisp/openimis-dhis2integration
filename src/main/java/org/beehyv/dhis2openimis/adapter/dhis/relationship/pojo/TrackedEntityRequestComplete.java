package org.beehyv.dhis2openimis.adapter.dhis.relationship.pojo;

import java.util.List;

import org.beehyv.dhis2openimis.adapter.dhis.pojo.poster.TrackedEntityRequest;

public class TrackedEntityRequestComplete extends TrackedEntityRequest{
	private List<RelationshipJson> relationships;
	
	public TrackedEntityRequestComplete() {
		super();
	}
	
	public TrackedEntityRequestComplete(TrackedEntityRequest trackedEntityRequest) {
		super();
		this.setAttributes(trackedEntityRequest.getAttributes());
		this.setOrgUnit(trackedEntityRequest.getOrgUnit());
		this.setTrackedEntityType(trackedEntityRequest.getTrackedEntityType());
	}
	
	public List<RelationshipJson> getRelationships() {
		return relationships;
	}
	public void setRelationships(List<RelationshipJson> relationships) {
		this.relationships = relationships;
	}

	@Override
	public String toString() {
		return "{\"trackedEntityType\":\"" + this.getTrackedEntityType() + "\", \"orgUnit\":\"" + this.getOrgUnit() + "\", \"attributes\":"
				+ this.getAttributes() + "\", \"relationships\":[ " + relationships.toString() + "]}";
	}

	
	
	
}
