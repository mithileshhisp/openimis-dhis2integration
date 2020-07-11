/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hispindia.dhis2openimis.adapter.openimis.pojo.location;

import java.util.List;


/**
 *
 * @author Mithilesh Thakur
 */
public class LegacyOpenIMISLocationBundle {
    
    private List<LegacyOpenIMISLocation> locations;
    
    public LegacyOpenIMISLocationBundle() {
		
	}
    /**
     * @return the locations
     */
    public List<LegacyOpenIMISLocation> getLegacyOpenIMISLocations() {
        return locations;
    }

    /**
     * @param locations the locations to set
     */
    public void setLegacyOpenIMISLocations(List<LegacyOpenIMISLocation> locations) {
        this.locations = locations;
    }
    
    /*
    @Override
    public String toString() {
            return "LegacyOpenIMISLocationBundle [locations=" + locations + "]";
    }
    */
    
    
}
