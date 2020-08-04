package org.beehyv.dhis2openimis.adapter.openimis.pojo.location;

import org.beehyv.dhis2openimis.adapter.openimis.pojo.Bundle;
import org.beehyv.dhis2openimis.adapter.openimis.pojo.LinkDetail;

import java.util.List;

public class LocationBundle extends Bundle {

    private List<LocationResource> entry;

    public LocationBundle() {

    }

    public LocationBundle(String resourceType, List<LinkDetail> link, Integer total, String type, List<LocationResource> entry) {
        super(resourceType, link, total, type);
        this.entry = entry;
    }

    public List<LocationResource> getEntry() {
        return entry;
    }

    public void setEntry(List<LocationResource> entry) {
        this.entry = entry;
    }


    @Override
    public String toString() {
        return "Location Bundle [resourceType=" + resourceType + ", entry=" + entry + ", link=" + link + ", total="
                + total + ", type=" + type + "]";
    }
}
