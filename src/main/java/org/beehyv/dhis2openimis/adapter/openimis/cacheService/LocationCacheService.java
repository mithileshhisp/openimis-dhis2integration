package org.beehyv.dhis2openimis.adapter.openimis.cacheService;

import org.beehyv.dhis2openimis.adapter.openimis.pojo.identifier.Identifier;
import org.beehyv.dhis2openimis.adapter.openimis.pojo.identifier.IdentifierTypeCodename;
import org.beehyv.dhis2openimis.adapter.openimis.pojo.location.Location;
import org.beehyv.dhis2openimis.adapter.util.exception.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class LocationCacheService {

    private Map<String, Location> map;

    public LocationCacheService() {
        map = new HashMap<String, Location>();
    }

    public void save(List<Location> locations) {
    	map.clear();
    	
        Map<String, Location> newLocations = locations.stream()
                .collect(Collectors.toMap(Location::getId, location->location));

        map.putAll(newLocations);
    }

    public Location getLocationById(String id) {
        return map.get(id);
    }
    
    public String getCodeById(String id) throws ObjectNotFoundException {
    	Location location = map.get(id);
    	if(location != null) {
    		List<Identifier> identifiers = location.getIdentifier();
    		for(Identifier identifier : identifiers) {
    			if(identifier.getType().getCoding().get(0).getCode() == IdentifierTypeCodename.FI) {
    				return identifier.getValue();
    			}
    		}
    		throw new ObjectNotFoundException("No identifier in Location with IdentifierTypeCodename.FI");
    	} else {
    		throw new ObjectNotFoundException("No location with given id: "+ id);
    	}
    	
    }
}
