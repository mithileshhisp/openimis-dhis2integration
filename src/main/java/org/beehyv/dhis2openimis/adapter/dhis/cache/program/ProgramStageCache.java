package org.beehyv.dhis2openimis.adapter.dhis.cache.program;

import org.beehyv.dhis2openimis.adapter.dhis.pojo.program.ProgramStage;
import org.beehyv.dhis2openimis.adapter.dhis.pojo.program.ProgramStageBundle;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Vishal
 */
@Service
public class ProgramStageCache {
    private Map<String, String> cache;

    public ProgramStageCache() {
        cache = new HashMap<String, String>();
    }


    public void save(ProgramStageBundle bundle) {
    	cache.clear();
    	
        List<ProgramStage> attributes = bundle.getProgramStages();
        Map<String, String> entries = attributes.stream().collect(Collectors.toMap(
                ProgramStage::getDisplayName, ProgramStage::getId));

        cache.putAll(entries);
    }

    public String getByDisplayName(String displayName) {
        return cache.get(displayName);
    }
}
