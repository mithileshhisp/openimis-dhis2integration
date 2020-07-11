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
public class OrganisationUnitAttributeValueRequest<T> {
    
    private T value;
    private OrganisationUnitAttributeRequest attribute;

    public OrganisationUnitAttributeValueRequest() {

    }
    public OrganisationUnitAttributeValueRequest(OrganisationUnitAttributeRequest attribute, T value) {
            this.attribute = attribute;
            this.value = value;
    }       

    /**
     * @return the value
     */
    public T getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(T value) {
        this.value = value;
    }

    /**
     * @return the attribute
     */
    public OrganisationUnitAttributeRequest getAttribute() {
        return attribute;
    }

    /**
     * @param attribute the attribute to set
     */
    public void setAttribute(OrganisationUnitAttributeRequest attribute) {
        this.attribute = attribute;
    }    

    @Override
    public String toString() {
        if (value instanceof Integer ) {
            return "{\"value\":" + value + "\", \"attribute\":\"" + attribute  + "\"}";
        }else{
            return "{\"value\":\"" + getValue() + "\", \"attribute\":\"" + attribute  + "\"}";
        } 
    }

}