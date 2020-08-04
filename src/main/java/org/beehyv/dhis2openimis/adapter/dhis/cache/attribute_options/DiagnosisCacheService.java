package org.beehyv.dhis2openimis.adapter.dhis.cache.attribute_options;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.beehyv.dhis2openimis.adapter.dhis.pojo.tracked_entity.TrackedEntityAttribute;
import org.springframework.stereotype.Service;

@Service
public class DiagnosisCacheService extends TrackedEntityAttributeOptionsCache{
	
	private Map<String, String> cache;
	
	public DiagnosisCacheService() {
		cache = new HashMap<String, String>();
	}
	
	@Override
	public void save(List<TrackedEntityAttribute> attributes) {
		cache.clear();
		
		for(TrackedEntityAttribute attribute: attributes) {
			String key = attribute.getDisplayName().substring(0, 3);
			String code = attribute.getCode();
			cache.put(key, code);
		}
	}
	
	public String getCodeForDiagnosis(String diagnosis) {
		String key = diagnosis.substring(0, 3);
		return cache.get(key);
	}
}
