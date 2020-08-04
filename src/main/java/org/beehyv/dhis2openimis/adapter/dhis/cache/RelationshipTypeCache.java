package org.beehyv.dhis2openimis.adapter.dhis.cache;

import org.beehyv.dhis2openimis.adapter.dhis.pojo.relationship_type.RelationshipType;
import org.beehyv.dhis2openimis.adapter.dhis.pojo.relationship_type.RelationshipTypeBundle;
import org.beehyv.dhis2openimis.adapter.util.exception.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Vishal
 */
@Service
public class RelationshipTypeCache {

    private Map<String, String> cache;

    public RelationshipTypeCache() {
        cache = new HashMap<String, String>();
    }


    public void save(RelationshipTypeBundle bundle) {
    	cache.clear();
    	
        List<RelationshipType> types = bundle.getRelationshipTypes();
        for(RelationshipType type : types) {
            cache.put(type.getDisplayName(), type.getId());
        }
    }

    public String getByDisplayName(String displayName) throws ObjectNotFoundException {
        String output = cache.get(displayName);
        if(output == null) {
        	throw new ObjectNotFoundException("No relationship type id found for : " + displayName);
        } else {
        	return output;
        }
    }
}
