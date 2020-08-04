package org.beehyv.dhis2openimis.adapter.openimis.pojo.claimResponse.claimResponseItem;

import java.util.List;

public class ClaimResponseItem {
    private List<ClaimResponseAdjudication> adjudication;
    private Integer sequenceLinkId;

    
    public ClaimResponseItem() {
		
	}


	public List<ClaimResponseAdjudication> getAdjudication() {
		return adjudication;
	}
	public void setAdjudication(List<ClaimResponseAdjudication> adjudication) {
		this.adjudication = adjudication;
	}
	public Integer getSequenceLinkId() {
		return sequenceLinkId;
	}
	public void setSequenceLinkId(Integer sequenceLinkId) {
		this.sequenceLinkId = sequenceLinkId;
	}


	@Override
	public String toString() {
		return "ClaimResponseItem [adjudication=" + adjudication + ", sequenceLinkId=" + sequenceLinkId + "]";
	}
    
    
}
