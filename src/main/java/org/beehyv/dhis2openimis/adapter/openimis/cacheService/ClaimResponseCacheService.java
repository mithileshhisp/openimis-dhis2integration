package org.beehyv.dhis2openimis.adapter.openimis.cacheService;

import org.beehyv.dhis2openimis.adapter.openimis.pojo.claimResponse.ClaimResponse;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ClaimResponseCacheService {

    private Map<String, ClaimResponse> map;

    public ClaimResponseCacheService() {
        map = new HashMap<String, ClaimResponse>();
    }

    public void save(List<ClaimResponse> claimResponses) {
        Map<String, ClaimResponse> newClaimResponses = claimResponses.stream()
                .collect(Collectors.toMap(ClaimResponse::getId, claimResponse->claimResponse));

        map.putAll(newClaimResponses);
    }

    public ClaimResponse getById(String id) {
        return map.get(id);
    }
    
    public Collection<ClaimResponse> getAllClaimResponses(){
    	return map.values();
    }
    
    public void clear() {
    	map.clear();
    }
}
