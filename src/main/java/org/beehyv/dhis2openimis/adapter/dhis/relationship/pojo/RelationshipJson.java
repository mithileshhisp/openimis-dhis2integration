package org.beehyv.dhis2openimis.adapter.dhis.relationship.pojo;

public class RelationshipJson {
	private String relationshipType;
	private RelationshipTeiJson from;
	private RelationshipTeiJson to;
	
	public RelationshipJson() {
		
	}
	
	public RelationshipJson(String relationshipType, String claimTei, String insureeTei) {
		this.relationshipType = relationshipType;
		this.from = new RelationshipTeiJson(claimTei);
		this.to = new RelationshipTeiJson(insureeTei);
	}

	public String getRelationshipType() {
		return relationshipType;
	}
	public void setRelationshipType(String relationshipType) {
		this.relationshipType = relationshipType;
	}
	public RelationshipTeiJson getFrom() {
		return from;
	}
	public void setFrom(RelationshipTeiJson from) {
		this.from = from;
	}
	public RelationshipTeiJson getTo() {
		return to;
	}
	public void setTo(RelationshipTeiJson to) {
		this.to = to;
	}

	@Override
	public String toString() {
		return "{\"relationshipType\":\"" + relationshipType + "\", \"from\":" + from.toString() + ", \"to\":" + to.toString() + "}";
	}
	
	
}
