package org.beehyv.dhis2openimis.adapter.dhis.pojo.tracked_entity.query.event;

import java.util.List;

/**
 * Response to {@code APIConfiguration getEventsForTrackedEntityInstanceQueryUrl(String tei)}
 * @author Shubham Jaiswal
 *
 */
public class EventQueryResponse {
	private List<EventQueryDetail> events;

	
	public EventQueryResponse() {
		
	}
	
	
	public List<EventQueryDetail> getEvents() {
		return events;
	}
	public void setEvents(List<EventQueryDetail> events) {
		this.events = events;
	}
	
}
