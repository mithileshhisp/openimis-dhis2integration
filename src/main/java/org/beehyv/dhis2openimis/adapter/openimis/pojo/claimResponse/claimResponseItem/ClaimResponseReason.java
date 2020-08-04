package org.beehyv.dhis2openimis.adapter.openimis.pojo.claimResponse.claimResponseItem;

import java.util.List;

public class ClaimResponseReason {
    private List<ClaimResponseCoding> coding;
    private String text;

    
    public ClaimResponseReason() {
		
	}

    
    public List<ClaimResponseCoding> getCoding() {
        return coding;
    }
    public void setCoding(List<ClaimResponseCoding> coding) {
        this.coding = coding;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "ClaimResponseReason [coding=" + coding + ", text=" + text + "]";
    }
}
