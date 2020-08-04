package org.beehyv.dhis2openimis.adapter.dhis.cache.attribute_options;

import org.beehyv.dhis2openimis.adapter.dhis.pojo.data_element.DataElement;
import org.beehyv.dhis2openimis.adapter.util.exception.ObjectNotFoundException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Vishal
 */
public class DataElementOptionsCache {

    private Map<String, String> cache;
    
    public DataElementOptionsCache() {
    	cache = new HashMap<String, String>();
    }
    
    public void save(List<DataElement> attributes) {
    	cache.clear();
    	
        for(DataElement attribute: attributes) {
            String key = attribute.getDisplayName();
            String code = attribute.getCode();
            cache.put(key, code);
        }
    }

    public String getCodeFor(String key) throws ObjectNotFoundException {
        String code = cache.get(key);
        if(code == null) {
        	throw new ObjectNotFoundException("No mapping found in DataElementOptionsCache for: " + key);
        }
        else {
        	return code;
        }
    }
}
