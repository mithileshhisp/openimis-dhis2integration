package org.beehyv.dhis2openimis.adapter.openimis.pojo.location;

public class LocationResource {
    private Location resource;
    private String fullUrl;
    
    
    public LocationResource() {
		
	}

    
    public String getFullUrl() {
        return fullUrl;
    }
    public void setFullUrl(String fullUrl) {
        this.fullUrl = fullUrl;
    }
    public Location getResource() {
        return resource;
    }
    public void setResource(Location resource) {
        this.resource = resource;
    }

    @Override
    public String toString() {
        return "Location resource [fullUrl=" + fullUrl + ", resource=" + resource + "]";
    }
}
