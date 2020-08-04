package org.beehyv.dhis2openimis.adapter.openimis.pojo.coverage;

/**
 * @author Vishal
 */
public class CoverageResource {
    private Coverage resource;
    private String fullUrl;

    public CoverageResource(Coverage resource, String fullUrl) {
        this.resource = resource;
        this.fullUrl = fullUrl;
    }

    public CoverageResource() {
    }

    public String getFullUrl() {
        return fullUrl;
    }

    public void setFullUrl(String fullUrl) {
        this.fullUrl = fullUrl;
    }

    public Coverage getResource() {
        return resource;
    }

    public void setResource(Coverage resource) {
        this.resource = resource;
    }

    @Override
    public String toString() {
        return "CoverageResource [fullUrl=" + fullUrl + ", resource=" + resource + "]";
    }
}
