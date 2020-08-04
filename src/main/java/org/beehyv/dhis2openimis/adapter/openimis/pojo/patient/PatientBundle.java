package org.beehyv.dhis2openimis.adapter.openimis.pojo.patient;

import org.beehyv.dhis2openimis.adapter.openimis.pojo.Bundle;
import org.beehyv.dhis2openimis.adapter.openimis.pojo.LinkDetail;

import java.util.List;

public class PatientBundle extends Bundle {
	private List<PatientResource> entry;

	public PatientBundle() {
		
	}

	public PatientBundle(String resourceType, List<LinkDetail> link, Integer total, String type, List<PatientResource> entry) {
		super(resourceType, link, total, type);
		this.entry = entry;
	}

	public List<PatientResource> getEntry() {
		return entry;
	}

	public void setEntry(List<PatientResource> entry) {
		this.entry = entry;
	}

	@Override
	public String toString() {
		return "PatientBundle [resourceType=" + resourceType + ", entry=" + entry + ", link=" + link + ", total="
				+ total + ", type=" + type + "]";
	}
	
	
}
