/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hispindia.dhis2openimis.adapter.dhis.pojo.organisation;

/**
 *
 * @author Mithilesh Thakur
 */
public class OrganisationUnitParentRequest {
    
    private String id;
   
    public OrganisationUnitParentRequest() {

    }   

   /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }
    
    @Override
    public String toString() {
            return "{\"id\":\"" + id + "\"}";
    }
}
