/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.beehyv.dhis2openimis.adapter.dhis.pojo.organisation;

import java.util.List;

/**
 *
 * @author Mithilesh Thakur
 */
public class OrganisationUnitsDhis2Bundle {
    

    private List<OrganisationUnitDhis2> organisationUnits;

    public OrganisationUnitsDhis2Bundle() {

    }
    /**
     * @return the organisationUnits
     */
    public List<OrganisationUnitDhis2> getOrganisationUnits() {
        return organisationUnits;
    }

    /**
     * @param organisationUnits the organisationUnits to set
     */
    public void setOrganisationUnits(List<OrganisationUnitDhis2> organisationUnits) {
        this.organisationUnits = organisationUnits;
    }
 
    @Override
    public String toString() {
            return "OrganisationUnitsDhis2Bundle [organisationUnits=" + organisationUnits + "]";
    }



	    
}
