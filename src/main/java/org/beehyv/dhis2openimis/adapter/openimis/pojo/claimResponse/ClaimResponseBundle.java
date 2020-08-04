package org.beehyv.dhis2openimis.adapter.openimis.pojo.claimResponse;

import org.beehyv.dhis2openimis.adapter.openimis.pojo.Bundle;
import org.beehyv.dhis2openimis.adapter.openimis.pojo.LinkDetail;

import java.util.List;

public class ClaimResponseBundle extends Bundle {

    private List<ClaimResponseResource> entry;

    public ClaimResponseBundle() {

    }

    public ClaimResponseBundle(String resourceType, List<LinkDetail> link, Integer total, String type, List<ClaimResponseResource> entry) {
        super(resourceType, link, total, type);
        this.entry = entry;
    }

    public List<ClaimResponseResource> getEntry() {
        return entry;
    }

    public void setEntry(List<ClaimResponseResource> entry) {
        this.entry = entry;
    }


    @Override
    public String toString() {
        return "ClaimResponseBundle [resourceType=" + resourceType + ", entry=" + entry + ", link=" + link + ", total="
                + total + ", type=" + type + "]";
    }
}
