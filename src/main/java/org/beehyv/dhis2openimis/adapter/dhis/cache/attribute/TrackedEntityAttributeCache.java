package org.beehyv.dhis2openimis.adapter.dhis.cache.attribute;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.beehyv.dhis2openimis.adapter.dhis.pojo.tracked_entity.TrackedEntityAttributeDetail;
import org.beehyv.dhis2openimis.adapter.dhis.pojo.tracked_entity.TrackedEntityAttributeDetailBundle;
import org.springframework.stereotype.Service;

@Service
public class TrackedEntityAttributeCache {
	
	/**
	 * displayName as key
	 * id as value
	 */
	private Map<String, String> cache;
	
	public TrackedEntityAttributeCache() {
		cache = new HashMap<String, String>();
	}
	
	
	public void save(TrackedEntityAttributeDetailBundle bundle) {
		cache.clear();
		
		List<TrackedEntityAttributeDetail>  attributes = bundle.getTrackedEntityAttributes();
		Map<String, String> entries = attributes.stream().collect(Collectors.toMap(
				TrackedEntityAttributeDetail::getDisplayName, TrackedEntityAttributeDetail::getId));
		
		cache.putAll(entries);
	}
	
	public String get(String displayName) {
		return cache.get(displayName);
	}

}
