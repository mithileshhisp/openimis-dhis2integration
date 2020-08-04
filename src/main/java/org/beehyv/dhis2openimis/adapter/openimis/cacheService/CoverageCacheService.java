package org.beehyv.dhis2openimis.adapter.openimis.cacheService;

import org.beehyv.dhis2openimis.adapter.openimis.pojo.coverage.Coverage;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class CoverageCacheService {
    private Map<String, Coverage> map;


    public CoverageCacheService() {
        map = new HashMap<String, Coverage>();
    }

    public void save(List<Coverage> coverages) {
        Map<String, Coverage> newCoverages = coverages.stream()
                .collect(Collectors.toMap(coverage -> coverage.getIdentifier().get(0).getValue(), coverage->coverage));

        map.putAll(newCoverages);
    }

    public Coverage getById(String id) {
        return map.get(id);
    }
    
    public Collection<Coverage> getAllCoverages(){
    	return map.values();
    }
    
    public void clear() {
    	map.clear();
    }
}
