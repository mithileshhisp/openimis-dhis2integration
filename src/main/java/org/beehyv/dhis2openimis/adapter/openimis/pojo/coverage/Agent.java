package org.beehyv.dhis2openimis.adapter.openimis.pojo.coverage;

import org.beehyv.dhis2openimis.adapter.openimis.pojo.Reference;

import java.util.List;

/**
 * @author Vishal
 */
public class Agent {

    private List<Role> role;
    private Reference actor;

    public List<Role> getRole() {
        return role;
    }

    public void setRole(List<Role> role) {
        this.role = role;
    }

    public Reference getActor() {
        return actor;
    }

    public void setActor(Reference actor) {
        this.actor = actor;
    }
}
