package org.beehyv.dhis2openimis.adapter.openimis.pojo.claim;

public class ClaimsTotal {
    private Double value;
    
    
    public ClaimsTotal() {
    	
    }
    
    public ClaimsTotal(Double value) {
        this.value = value;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Total [value=" + value + "]";
    }
}
