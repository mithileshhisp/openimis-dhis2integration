package org.beehyv.dhis2openimis.adapter.openimis.pojo.claim;

import org.beehyv.dhis2openimis.adapter.openimis.pojo.claim.Claim;

public class ClaimResource {

    private Claim resource;
    private String fullUrl;
    
    public ClaimResource() {
    	
    }
    
    public ClaimResource(Claim resource, String fullUrl) {
        this.resource = resource;
        this.fullUrl = fullUrl;
    }


    public String getFullUrl() {
        return fullUrl;
    }

    public void setFullUrl(String fullUrl) {
        this.fullUrl = fullUrl;
    }

    public Claim getResource() {
        return resource;
    }

    public void setResource(Claim resource) {
        this.resource = resource;
    }

    @Override
    public String toString() {
        return "ClaimResource [fullUrl=" + fullUrl + ", resource=" + resource + "]";
    }

}
