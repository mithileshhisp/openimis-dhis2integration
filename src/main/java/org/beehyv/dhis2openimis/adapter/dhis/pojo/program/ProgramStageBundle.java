package org.beehyv.dhis2openimis.adapter.dhis.pojo.program;

import org.beehyv.dhis2openimis.adapter.dhis.pojo.Pager;

import java.util.List;

/**
 * @author Vishal
 */
public class ProgramStageBundle {
    Pager pager;
    List<ProgramStage> programStages;

    public Pager getPager() {
        return pager;
    }

    public void setPager(Pager pager) {
        this.pager = pager;
    }

    public List<ProgramStage> getProgramStages() {
        return programStages;
    }

    public void setProgramStages(List<ProgramStage> programStages) {
        this.programStages = programStages;
    }
}
