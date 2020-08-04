package org.beehyv.dhis2openimis.adapter.openimis.pojo.claim.diagnosis;

import java.util.List;

public class DiagnosisCodeableConcept {

    private List<DiagnosisCoding> coding;


    public DiagnosisCodeableConcept() {

    }

    public List<DiagnosisCoding> getCoding() {
        return coding;
    }

    public void setCoding(List<DiagnosisCoding> coding) {
        this.coding = coding;
    }

    @Override
    public String toString() {
        return "DiagnosisCodeableConcept [coding=" + coding + "]";
    }
}
