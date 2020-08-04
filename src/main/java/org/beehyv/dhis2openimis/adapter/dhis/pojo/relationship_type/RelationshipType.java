package org.beehyv.dhis2openimis.adapter.dhis.pojo.relationship_type;

/**
 * @author Vishal
 */
public class RelationshipType {
    private String id;
    private String displayName;

    public RelationshipType() {

    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getDisplayName() {
        return displayName;
    }
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
