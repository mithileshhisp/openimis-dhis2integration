package org.beehyv.dhis2openimis.adapter.openimis.pojo.practitioner;


public class PractitionerResource {

    private Practitioner resource;
    private String fullUrl;

    public PractitionerResource(Practitioner resource, String fullUrl) {
        this.resource = resource;
        this.fullUrl = fullUrl;
    }

    public String getFullUrl() {
        return fullUrl;
    }

    public void setFullUrl(String fullUrl) {
        this.fullUrl = fullUrl;
    }

    public Practitioner getResource() {
        return resource;
    }

    public void setResource(Practitioner resource) {
        this.resource = resource;
    }

    @Override
    public String toString() {
        return "Practitioner resource [fullUrl=" + fullUrl + ", resource=" + resource + "]";
    }
}
