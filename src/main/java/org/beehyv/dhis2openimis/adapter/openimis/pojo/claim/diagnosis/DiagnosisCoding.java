package org.beehyv.dhis2openimis.adapter.openimis.pojo.claim.diagnosis;

public class DiagnosisCoding {

    private String code;

    private DiagnosisCoding() {

    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "DiagnosisCoding [code=" + code + "]";
    }
}
