package org.beehyv.dhis2openimis.adapter.dhis.pojo.tracked_entity;

import java.util.List;

public class TrackedEntityAttributeDetailBundle {
	private List<TrackedEntityAttributeDetail> trackedEntityAttributes;
	
	public TrackedEntityAttributeDetailBundle() {
		
	}

	public List<TrackedEntityAttributeDetail> getTrackedEntityAttributes() {
		return trackedEntityAttributes;
	}
	public void setTrackedEntityAttributes(List<TrackedEntityAttributeDetail> trackedEntityAttributes) {
		this.trackedEntityAttributes = trackedEntityAttributes;
	}
	
	
}
