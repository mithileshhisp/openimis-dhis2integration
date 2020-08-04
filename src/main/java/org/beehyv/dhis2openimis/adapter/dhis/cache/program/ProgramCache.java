package org.beehyv.dhis2openimis.adapter.dhis.cache.program;

import org.beehyv.dhis2openimis.adapter.dhis.pojo.program.Program;
import org.beehyv.dhis2openimis.adapter.dhis.pojo.program.ProgramBundle;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Vishal
 */
@Service
public class ProgramCache {
    private Map<String, Program> cache;

    public ProgramCache() {
        cache = new HashMap<String, Program>();
    }


    public void save(ProgramBundle bundle) {
    	cache.clear();
    	
        List<Program> attributes = bundle.getPrograms();
        Map<String, Program> entries = attributes.stream().collect(Collectors.toMap(
        									Program::getDisplayName, program -> program));

        cache.putAll(entries);
    }

    public Program getByDisplayName(String displayName) {
        return cache.get(displayName);
    }
    
    public String getIdByDisplayName(String displayName) {
    	return cache.get(displayName).getId();
    }
}
