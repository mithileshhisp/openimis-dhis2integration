package org.beehyv.dhis2openimis.adapter.dhis.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.beehyv.dhis2openimis.adapter.dhis.pojo.type.TrackedEntityType;
import org.beehyv.dhis2openimis.adapter.dhis.pojo.type.TrackedEntityTypeBundle;
import org.springframework.stereotype.Service;

@Service
public class TrackedEntityTypeCache {
	
	private Map<String, String> cache;
	
	public TrackedEntityTypeCache() {
		cache = new HashMap<String, String>();
	}
	
	
	public void save(TrackedEntityTypeBundle bundle) {
		cache.clear();
		
		List<TrackedEntityType> types = bundle.getTrackedEntityTypes();
		for(TrackedEntityType type : types) {
			cache.put(type.getDisplayName(), type.getId());
		}
	}
	
	public String getByDisplayName(String displayName) {
		return cache.get(displayName);
	}
}
