package org.beehyv.dhis2openimis.adapter.dhis.cache.data_element;

import org.beehyv.dhis2openimis.adapter.dhis.pojo.data_element.DataElementDetail;
import org.beehyv.dhis2openimis.adapter.dhis.pojo.data_element.DataElementDetailsBundle;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Vishal
 */
@Service
public class DataElementCache {

    /**
     * displayName as key
     * id as value
     */
    private Map<String, String> cache;

    public DataElementCache() {
        cache = new HashMap<String, String>();
    }


    public void save(DataElementDetailsBundle bundle) {
    	cache.clear();
    	
        List<DataElementDetail> attributes = bundle.getDataElements();
        Map<String, String> entries = attributes.stream().collect(Collectors.toMap(
                DataElementDetail::getDisplayName, DataElementDetail::getId));

        cache.putAll(entries);
    }

    public String get(String id) {
        return cache.get(id);
    }
}
