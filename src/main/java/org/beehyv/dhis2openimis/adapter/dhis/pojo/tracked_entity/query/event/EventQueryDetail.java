package org.beehyv.dhis2openimis.adapter.dhis.pojo.tracked_entity.query.event;

/**
 * Not all fields in the actually query response are added here, as they weren't deemed essential.
 * @author Shubham Jaiswal
 */
public class EventQueryDetail {
	private String event;
	
	
	public EventQueryDetail() {

	}
	
	
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}
	
}
