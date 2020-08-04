package org.beehyv.dhis2openimis.adapter.dhis.pojo.program;

import java.util.List;

/**
 * @author Vishal
 */
public class ProgramBundle {
    List<Program> programs;
    
    public ProgramBundle() {
		
	}
    
    public List<Program> getPrograms() {
        return programs;
    }

    public void setPrograms(List<Program> programs) {
        this.programs = programs;
    }

}
