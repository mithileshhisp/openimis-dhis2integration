package org.beehyv.dhis2openimis.adapter.openimis.pojo.claimResponse;

public class ClaimResponseResource {

    private ClaimResponse resource;
    private String fullUrl;
    
    public ClaimResponseResource() {
    	
    }
    
    public ClaimResponseResource(ClaimResponse resource, String fullUrl) {
        this.resource = resource;
        this.fullUrl = fullUrl;
    }

    public String getFullUrl() {
        return fullUrl;
    }

    public void setFullUrl(String fullUrl) {
        this.fullUrl = fullUrl;
    }

    public ClaimResponse getResource() {
        return resource;
    }

    public void setResource(ClaimResponse resource) {
        this.resource = resource;
    }

    @Override
    public String toString() {
        return "ClaimResource [fullUrl=" + fullUrl + ", resource=" + resource + "]\n";
    }
}
