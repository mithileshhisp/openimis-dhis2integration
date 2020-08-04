package org.beehyv.dhis2openimis.adapter.dhis.pojo.tracked_entity;

import java.util.List;

public class TrackedEntityAttributeBundle {
	private List<TrackedEntityAttribute> options;

	public TrackedEntityAttributeBundle() {
		
	}
	
	public List<TrackedEntityAttribute> getOptions() {
		return options;
	}
	public void setOptions(List<TrackedEntityAttribute> options) {
		this.options = options;
	}
	
}
