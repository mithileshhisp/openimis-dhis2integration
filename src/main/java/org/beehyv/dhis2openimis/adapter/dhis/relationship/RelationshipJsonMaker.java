package org.beehyv.dhis2openimis.adapter.dhis.relationship;

import java.util.Collections;

import org.beehyv.dhis2openimis.adapter.dhis.cache.RelationshipTypeCache;
import org.beehyv.dhis2openimis.adapter.dhis.pojo.poster.TrackedEntityRequest;
import org.beehyv.dhis2openimis.adapter.dhis.relationship.pojo.RelationshipJson;
import org.beehyv.dhis2openimis.adapter.dhis.relationship.pojo.TrackedEntityRequestComplete;
import org.beehyv.dhis2openimis.adapter.util.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RelationshipJsonMaker {
	private RelationshipTypeCache relationTypeCache;
	
	@Autowired
	public RelationshipJsonMaker(RelationshipTypeCache relationTypeCache) {
		this.relationTypeCache = relationTypeCache;
	}
	
	
	public TrackedEntityRequestComplete getCompleteClaimJson(TrackedEntityRequest claimJson, String claimTei,
			String insureeTei) throws ObjectNotFoundException {
		TrackedEntityRequestComplete terc = new TrackedEntityRequestComplete(claimJson);
		
		String insureeClaimsRelationId = relationTypeCache.getByDisplayName("Insuree-Claims");
		RelationshipJson relationshipJson = new RelationshipJson(insureeClaimsRelationId, claimTei, insureeTei);
		terc.setRelationships(Collections.singletonList(relationshipJson));
		return terc;
	}
}
