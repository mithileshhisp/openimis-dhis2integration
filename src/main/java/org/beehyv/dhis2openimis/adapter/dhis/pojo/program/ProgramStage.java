package org.beehyv.dhis2openimis.adapter.dhis.pojo.program;

/**
 * @author Vishal
 */
public class ProgramStage {
    private String id;
    private String displayName;

    public ProgramStage() {
		
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
