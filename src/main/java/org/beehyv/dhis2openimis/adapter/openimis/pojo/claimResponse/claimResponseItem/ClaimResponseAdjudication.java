package org.beehyv.dhis2openimis.adapter.openimis.pojo.claimResponse.claimResponseItem;

public class ClaimResponseAdjudication {
	private ClaimResponseAmount amount;
    private ClaimResponseCategory category;
    private ClaimResponseReason reason;
    
    
    public ClaimResponseAdjudication() {
		
	}
    
    
	public ClaimResponseAmount getAmount() {
		return amount;
	}
	public void setAmount(ClaimResponseAmount amount) {
		this.amount = amount;
	}
	public ClaimResponseCategory getCategory() {
		return category;
	}
	public void setCategory(ClaimResponseCategory category) {
		this.category = category;
	}
	public ClaimResponseReason getReason() {
		return reason;
	}
	public void setReason(ClaimResponseReason reason) {
		this.reason = reason;
	}


	@Override
	public String toString() {
		return "ClaimResponseAdjudication [amount=" + amount + ", category=" + category + ", reason=" + reason + "]";
	}

	

    
}
