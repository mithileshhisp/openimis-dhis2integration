package org.beehyv.dhis2openimis.adapter.openimis.pojo.claim.claimItem;

public class Service {

    private String text;
    
    public Service() {
    	
    }
    
    public Service(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Service [text=" + text + "]";
    }
}
