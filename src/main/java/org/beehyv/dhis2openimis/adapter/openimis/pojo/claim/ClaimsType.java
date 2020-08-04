package org.beehyv.dhis2openimis.adapter.openimis.pojo.claim;

public class ClaimsType {

    private String text;
    
    public ClaimsType() {
    	
    }
    
    public ClaimsType(String text) {
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
        return "Type [text=" + text + "]";
    }
}
