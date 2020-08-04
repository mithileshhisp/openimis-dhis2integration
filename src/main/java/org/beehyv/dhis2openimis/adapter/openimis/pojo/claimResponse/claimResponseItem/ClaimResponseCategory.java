package org.beehyv.dhis2openimis.adapter.openimis.pojo.claimResponse.claimResponseItem;

public class ClaimResponseCategory {
    private String text;

    
    public ClaimResponseCategory() {
    	
    }

    
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Category [text=" + text + "]";
    }
}
