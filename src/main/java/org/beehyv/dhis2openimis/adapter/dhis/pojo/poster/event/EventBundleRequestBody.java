package org.beehyv.dhis2openimis.adapter.dhis.pojo.poster.event;

import java.util.List;

public class EventBundleRequestBody {
	private List<Event> events;

	public EventBundleRequestBody() {
		
	}
	
	public List<Event> getEvents() {
		return events;
	}
	public void setEvents(List<Event> events) {
		this.events = events;
	}

	@Override
	public String toString() {
		return "{\"events\": " + events + "}";
	}
	
	
}
