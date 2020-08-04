package org.beehyv.dhis2openimis.adapter.openimis.cacheService;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.beehyv.dhis2openimis.adapter.openimis.pojo.patient.Patient;
import org.springframework.stereotype.Service;

@Service
public class PatientCacheService {
	private Map<String, Patient> map;
	
	public PatientCacheService() {
		map = new HashMap<String, Patient>();
	}
	
	public void save(List<Patient> patients) {
		Map<String, Patient> newPatients = patients.stream()
											.collect(Collectors.toMap(Patient::getId, patient->patient));
		
		map.putAll(newPatients);
	}
	
	public Patient getById(String id) {
		return map.get(id);
	}
	
	public Collection<Patient> getAllPatients() {
		return map.values();
	}
	
	public void clear() {
		map.clear();
	}
}
