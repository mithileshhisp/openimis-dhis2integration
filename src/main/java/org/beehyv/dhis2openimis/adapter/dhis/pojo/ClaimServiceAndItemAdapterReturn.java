package org.beehyv.dhis2openimis.adapter.dhis.pojo;

import java.util.List;

import org.beehyv.dhis2openimis.adapter.dhis.pojo.poster.DetailsJson;

/**
 * This class in an internal helper data structure. ClaimServiceAndItemAdapter class returns this class as output.
 * @author Shubham Jaiswal
 *
 */
public class ClaimServiceAndItemAdapterReturn {
	private DetailsJson detailsJson;
	private ClaimItemOrService eventType;
	
	public ClaimServiceAndItemAdapterReturn() {
		
	}
	
	public DetailsJson getDetailsJson() {
		return detailsJson;
	}
	public ClaimItemOrService getEventType() {
		return eventType;
	}
	public void setDetailsJson(DetailsJson detailsJson) {
		this.detailsJson = detailsJson;
	}
	public void setEventType(ClaimItemOrService eventType) {
		this.eventType = eventType;
	}
	
	
	
	
	
	
	
}
