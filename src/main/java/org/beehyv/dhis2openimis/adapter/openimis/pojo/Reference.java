package org.beehyv.dhis2openimis.adapter.openimis.pojo;

public class Reference {

    private String reference;

    public Reference() {

    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    @Override
    public String toString() {
        return "Facility [reference=" + reference + "]";
    }
}
