package org.beehyv.dhis2openimis.adapter.openimis.cacheService;

import org.beehyv.dhis2openimis.adapter.openimis.pojo.claim.Claim;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ClaimCacheService {

    private Map<String, Claim> map;

    public ClaimCacheService() {
        map = new HashMap<String, Claim>();
    }

    public void save(List<Claim> claims) {
        Map<String, Claim> newClaims = claims.stream()
                .collect(Collectors.toMap(Claim::getId, claim->claim));

        map.putAll(newClaims);
    }

    public Claim getById(String id) {
        return map.get(id);
    }
    
    public void clear() {
    	map.clear();
    }
}
