package org.beehyv.dhis2openimis.adapter.dhis.pojo.organisation;

import java.util.List;

public class OrganisationUnitsBundle {
	private List<OrganisationUnit> organisationUnits;
	
	public OrganisationUnitsBundle() {
		
	}
	
	public List<OrganisationUnit> getOrganisationUnits() {
		return organisationUnits;
	}
	public void setOrganisationUnits(List<OrganisationUnit> organisationUnits) {
		this.organisationUnits = organisationUnits;
	}
	
	@Override
	public String toString() {
		return "OrganisationUnitsBundle [organisationUnits=" + organisationUnits + "]";
	}
	
	
}
