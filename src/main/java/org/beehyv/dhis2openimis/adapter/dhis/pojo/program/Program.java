package org.beehyv.dhis2openimis.adapter.dhis.pojo.program;

import java.util.List;

/**
 * @author Vishal
 */
public class Program {
    private String id;
    private String displayName;
    private List<ProgramStage> programStages;

    public Program() {
		
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
	public List<ProgramStage> getProgramStages() {
		return programStages;
	}
	public void setProgramStages(List<ProgramStage> programStages) {
		this.programStages = programStages;
	}
    
}
