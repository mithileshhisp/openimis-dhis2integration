/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.beehyv.dhis2openimis.adapter.dhis.pojo.organisation;

import java.util.List;
import org.beehyv.dhis2openimis.adapter.dhis.pojo.poster.event.Event;

/**
 *
 * @author Mithilesh Thakur
 */
public class OrganisationUnitsDhis2BundleRequest {
    
    private List<OrganisationUnitDhis2Request> organisationUnits;

    public OrganisationUnitsDhis2BundleRequest() {

    }

    /**
     * @return the organisationUnits
     */
    public List<OrganisationUnitDhis2Request> getOrganisationUnits() {
        return organisationUnits;
    }

    /**
     * @param organisationUnits the organisationUnits to set
     */
    public void setOrganisationUnits(List<OrganisationUnitDhis2Request> organisationUnits) {
        this.organisationUnits = organisationUnits;
    }

    
    @Override
    public String toString() {
            return "{\"organisationUnits\": " + organisationUnits + "}";
    }
}
