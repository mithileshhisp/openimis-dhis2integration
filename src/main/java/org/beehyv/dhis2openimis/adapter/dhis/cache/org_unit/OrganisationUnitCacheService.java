package org.beehyv.dhis2openimis.adapter.dhis.cache.org_unit;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.beehyv.dhis2openimis.adapter.dhis.pojo.organisation.OrganisationUnit;
import org.beehyv.dhis2openimis.adapter.util.exception.ObjectNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class OrganisationUnitCacheService {
	
	/**
	 * code as key
	 * id as value
	 */
	private Map<String, String> cache;
	
	public OrganisationUnitCacheService() {
		cache = new HashMap<String, String>();
	}
	
	public void save(List<OrganisationUnit> organisations) {
		cache.clear();
		
		Map<String, String> entries = organisations.stream().filter(org -> (org.getCode()!=null)).collect(Collectors.toMap(
										OrganisationUnit::getCode, OrganisationUnit::getId));
		
		cache.putAll(entries);
	}
	
	public String getByCode(String code) throws ObjectNotFoundException {
		String output = cache.get(code);
		if(output != null) {
			return output;
		} else {
			throw new ObjectNotFoundException("No orgUnit found with code :" + code);
		}
	}
}
