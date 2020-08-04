package org.beehyv.dhis2openimis.adapter.openimis.pojo.claim.diagnosis;

import java.util.List;

public class Diagnosis {
    private DiagnosisCodeableConcept diagnosisCodeableConcept;
    private Integer sequence;
    private List<DiagnosisType> type;

    public Diagnosis() {

    }

    public DiagnosisCodeableConcept getDiagnosisCodeableConcept() {
        return diagnosisCodeableConcept;
    }

    public void setDiagnosisCodeableConcept(DiagnosisCodeableConcept diagnosisCodeableConcept) {
        this.diagnosisCodeableConcept = diagnosisCodeableConcept;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public List<DiagnosisType> getType() {
        return type;
    }

    public void setType(List<DiagnosisType> type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Diagnosis [diagnosisCodeableConcept=" + diagnosisCodeableConcept + ", sequence=" + sequence + ", type=" + type + "]";
    }
}
