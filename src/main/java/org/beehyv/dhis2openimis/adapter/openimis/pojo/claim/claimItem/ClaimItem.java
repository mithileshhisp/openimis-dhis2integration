package org.beehyv.dhis2openimis.adapter.openimis.pojo.claim.claimItem;

public class ClaimItem {
    private Category category;
    private Quantity quantity;
    private Integer sequence;
    private Service service;
    private UnitPrice unitPrice;
    
    public ClaimItem() {
		
	}
    
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public Quantity getQuantity() {
		return quantity;
	}
	public void setQuantity(Quantity quantity) {
		this.quantity = quantity;
	}
	public Integer getSequence() {
		return sequence;
	}
	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}
	public Service getService() {
		return service;
	}
	public void setService(Service service) {
		this.service = service;
	}
	public UnitPrice getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(UnitPrice unitPrice) {
		this.unitPrice = unitPrice;
	}

	@Override
	public String toString() {
		return "ClaimItem [category=" + category + ", quantity=" + quantity + ", sequence=" + sequence + ", service="
				+ service + ", unitPrice=" + unitPrice + "]";
	}
    
    
}
