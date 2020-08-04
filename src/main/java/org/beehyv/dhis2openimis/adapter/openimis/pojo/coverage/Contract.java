package org.beehyv.dhis2openimis.adapter.openimis.pojo.coverage;

import java.util.List;

/**
 * @author Vishal
 */
public class Contract {
    private String resourceType;
    private List<Agent> agent;
    private List<CoverageItem> valuedItem;

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public List<Agent> getAgent() {
        return agent;
    }

    public void setAgent(List<Agent> agent) {
        this.agent = agent;
    }

    public List<CoverageItem> getValuedItem() {
        return valuedItem;
    }

    public void setValuedItem(List<CoverageItem> valuedItem) {
        this.valuedItem = valuedItem;
    }
}
