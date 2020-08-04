package org.beehyv.dhis2openimis.adapter.dhis.pojo.relationship_type;

import java.util.List;

public class RelationshipTypeBundle {
	private List<RelationshipType> relationshipTypes;

	public RelationshipTypeBundle() {
		
	}
	
	public List<RelationshipType> getRelationshipTypes() {
		return relationshipTypes;
	}

	public void setRelationshipTypes(List<RelationshipType> relationshipTypes) {
		this.relationshipTypes = relationshipTypes;
	}
	
	
}
