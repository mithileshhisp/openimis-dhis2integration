package org.beehyv.dhis2openimis.adapter.openimis.pojo;

import java.util.List;

public class Bundle {

    protected String resourceType;
    protected List<LinkDetail> link;
    protected Integer total;
    protected String type;

    public Bundle() {
    }

    public Bundle(String resourceType, List<LinkDetail> link, Integer total, String type) {
        this.resourceType = resourceType;
        this.link = link;
        this.total = total;
        this.type = type;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public List<LinkDetail> getLink() {
        return link;
    }

    public void setLink(List<LinkDetail> link) {
        this.link = link;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Bundle [resourceType=" + resourceType + ", link=" + link + ", total=" + total + ", type=" + type + "]";
    }

}
