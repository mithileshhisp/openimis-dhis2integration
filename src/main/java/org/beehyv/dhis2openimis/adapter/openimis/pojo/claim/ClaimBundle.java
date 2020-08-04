package org.beehyv.dhis2openimis.adapter.openimis.pojo.claim;

import org.beehyv.dhis2openimis.adapter.openimis.pojo.Bundle;
import org.beehyv.dhis2openimis.adapter.openimis.pojo.LinkDetail;

import java.util.List;

public class ClaimBundle extends Bundle {

    private List<ClaimResource> entry;
    
    public ClaimBundle() {

    }
    
    public ClaimBundle(String resourceType, List<LinkDetail> link, Integer total, String type, List<ClaimResource> entry) {
        super(resourceType, link, total, type);
        this.entry = entry;
    }

    

    public List<ClaimResource> getEntry() {
        return entry;
    }

    public void setEntry(List<ClaimResource> entry) {
        this.entry = entry;
    }

    @Override
    public String toString() {
        return "ClaimBundle [resourceType=" + resourceType + ", entry=" + entry + ", link=" + link + ", total="
                + total + ", type=" + type + "]";
    }
}
