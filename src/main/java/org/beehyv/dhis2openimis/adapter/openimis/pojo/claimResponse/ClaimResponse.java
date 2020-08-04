package org.beehyv.dhis2openimis.adapter.openimis.pojo.claimResponse;

import org.beehyv.dhis2openimis.adapter.openimis.pojo.Reference;
import org.beehyv.dhis2openimis.adapter.openimis.pojo.claimResponse.claimResponseItem.ClaimResponseAmount;
import org.beehyv.dhis2openimis.adapter.openimis.pojo.claimResponse.claimResponseItem.ClaimResponseItem;
import org.beehyv.dhis2openimis.adapter.openimis.pojo.claimResponse.claimResponseItem.ClaimResponseReason;
import org.beehyv.dhis2openimis.adapter.openimis.pojo.identifier.Identifier;

import java.time.LocalDate;
import java.util.List;

public class ClaimResponse {
    private String resourceType;
    private LocalDate created;
    private String id;
    private List<Identifier> identifier;
    private List<ClaimResponseItem> item;
    private ClaimResponseReason outcome;
    private Reference request;
    private ClaimResponseAmount totalBenefit;
    
    
    public ClaimResponse() {
		
	}

    
    public String getResourceType() {
        return resourceType;
    }
    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }
    public LocalDate getCreated() {
		return created;
	}
	public void setCreated(LocalDate created) {
		this.created = created;
	}
	public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public List<Identifier> getIdentifier() {
        return identifier;
    }
    public void setIdentifier(List<Identifier> identifier) {
        this.identifier = identifier;
    }
    public List<ClaimResponseItem> getItem() {
		return item;
	}
	public void setItem(List<ClaimResponseItem> item) {
		this.item = item;
	}
	public ClaimResponseReason getOutcome() {
        return outcome;
    }
    public void setOutcome(ClaimResponseReason outcome) {
        this.outcome = outcome;
    }
    public Reference getRequest() {
        return request;
    }
    public void setRequest(Reference request) {
        this.request = request;
    }
    public ClaimResponseAmount getTotalBenefit() {
		return totalBenefit;
	}
	public void setTotalBenefit(ClaimResponseAmount totalBenefit) {
		this.totalBenefit = totalBenefit;
	}


	@Override
	public String toString() {
		return "ClaimResponse [resourceType=" + resourceType + ", created=" + created + ", id=" + id + ", identifier="
				+ identifier + ", item=" + item + ", outcome=" + outcome + ", request=" + request + ", totalBenefit="
				+ totalBenefit + "]";
	}


	
}
