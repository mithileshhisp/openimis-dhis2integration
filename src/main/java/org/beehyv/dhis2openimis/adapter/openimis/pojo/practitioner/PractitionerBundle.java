package org.beehyv.dhis2openimis.adapter.openimis.pojo.practitioner;

import org.beehyv.dhis2openimis.adapter.openimis.pojo.Bundle;
import org.beehyv.dhis2openimis.adapter.openimis.pojo.LinkDetail;

import java.util.List;

public class PractitionerBundle extends Bundle {

    private List<PractitionerResource> entry;

    public PractitionerBundle() {

    }

    public PractitionerBundle(String resourceType, List<LinkDetail> link, Integer total, String type, List<PractitionerResource> entry) {
        super(resourceType, link, total, type);
        this.entry = entry;
    }

    public List<PractitionerResource> getEntry() {
        return entry;
    }

    public void setEntry(List<PractitionerResource> entry) {
        this.entry = entry;
    }


    @Override
    public String toString() {
        return "Practitioner Bundle [resourceType=" + resourceType + ", entry=" + entry + ", link=" + link + ", total="
                + total + ", type=" + type + "]";
    }
}
