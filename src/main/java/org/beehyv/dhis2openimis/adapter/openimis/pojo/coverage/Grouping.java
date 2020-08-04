package org.beehyv.dhis2openimis.adapter.openimis.pojo.coverage;

/**
 * @author Vishal
 */
public class Grouping {
    private String group;
    private String groupDisplay;
    private String plan;
    private String planDisplay;

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getGroupDisplay() {
        return groupDisplay;
    }

    public void setGroupDisplay(String groupDisplay) {
        this.groupDisplay = groupDisplay;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public String getPlanDisplay() {
        return planDisplay;
    }

    public void setPlanDisplay(String planDisplay) {
        this.planDisplay = planDisplay;
    }
}
