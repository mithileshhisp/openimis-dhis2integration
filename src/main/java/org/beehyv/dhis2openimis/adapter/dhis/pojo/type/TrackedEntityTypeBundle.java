package org.beehyv.dhis2openimis.adapter.dhis.pojo.type;

import java.util.List;

public class TrackedEntityTypeBundle {
	private List<TrackedEntityType> trackedEntityTypes;
	
	public TrackedEntityTypeBundle() {
		
	}
	
	public List<TrackedEntityType> getTrackedEntityTypes() {
		return trackedEntityTypes;
	}
	public void setTrackedEntityTypes(List<TrackedEntityType> trackedEntityTypes) {
		this.trackedEntityTypes = trackedEntityTypes;
	}
	
	
}
