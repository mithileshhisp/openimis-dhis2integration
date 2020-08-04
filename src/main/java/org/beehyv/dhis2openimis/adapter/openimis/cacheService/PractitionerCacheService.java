package org.beehyv.dhis2openimis.adapter.openimis.cacheService;

import org.beehyv.dhis2openimis.adapter.openimis.pojo.practitioner.Practitioner;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PractitionerCacheService {

    private Map<String, Practitioner> map;

    public PractitionerCacheService() {
        map = new HashMap<String, Practitioner>();
    }

    public void save(List<Practitioner> practitioners) {
        Map<String, Practitioner> newPractitioners = practitioners.stream()
                .collect(Collectors.toMap(Practitioner::getId, location->location));

        map.putAll(newPractitioners);
    }

    public Practitioner getById(String id) {
        return map.get(id);
    }
}
