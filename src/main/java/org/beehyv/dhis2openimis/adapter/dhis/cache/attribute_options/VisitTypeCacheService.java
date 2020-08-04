package org.beehyv.dhis2openimis.adapter.dhis.cache.attribute_options;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.beehyv.dhis2openimis.adapter.dhis.pojo.tracked_entity.TrackedEntityAttribute;
import org.springframework.stereotype.Service;

@Service
public class VisitTypeCacheService extends TrackedEntityAttributeOptionsCache{
	
	private Map<String, String> cache;
	
	public VisitTypeCacheService() {
		cache = new HashMap<String, String>();
	}
	
	
	@Override
	public void save(List<TrackedEntityAttribute> attributes) {
		for(TrackedEntityAttribute attribute: attributes) {
			String key = attribute.getDisplayName();
			String code = attribute.getCode();
			cache.put(key, code);
		}
	}
	
	public String getCodeForVisitType(String visitType) {
		return cache.get(visitType);
	}
}
