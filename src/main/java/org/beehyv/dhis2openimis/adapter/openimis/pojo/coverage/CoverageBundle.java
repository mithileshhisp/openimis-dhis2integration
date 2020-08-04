package org.beehyv.dhis2openimis.adapter.openimis.pojo.coverage;

import org.beehyv.dhis2openimis.adapter.openimis.pojo.Bundle;
import org.beehyv.dhis2openimis.adapter.openimis.pojo.LinkDetail;

import java.util.List;

/**
 * @author Vishal
 */
public class CoverageBundle extends Bundle {
    List<CoverageResource> entry;

    public CoverageBundle() {

    }

    public CoverageBundle(String resourceType, List<LinkDetail> link, Integer total, String type, List<CoverageResource> entry) {
        super(resourceType, link, total, type);
        this.entry = entry;
    }

    public List<CoverageResource> getEntry() {
        return entry;
    }

    public void setEntry(List<CoverageResource> entry) {
        this.entry = entry;
    }


    @Override
    public String toString() {
        return "CoverageBundle [resourceType=" + resourceType + ", entry=" + entry + ", link=" + link + ", total="
                + total + ", type=" + type + "]";
    }
}
