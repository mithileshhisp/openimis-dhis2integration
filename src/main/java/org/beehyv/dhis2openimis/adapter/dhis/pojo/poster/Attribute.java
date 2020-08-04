package org.beehyv.dhis2openimis.adapter.dhis.pojo.poster;

public class Attribute<T> {
	private String attribute;
	private T value;

	public Attribute() {
		
	}
	
	public Attribute(String attribute, T value) {
		this.attribute = attribute;
		this.value = value;
	}

	public String getAttribute() {
		return attribute;
	}
	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}
	public T getValue() {
		return value;
	}
	public void setValue(T value) {
		this.value = value;
	}


	@Override
	public String toString() {
		if (value instanceof Integer) {
			return "{\"attribute\":\"" + attribute + "\", \"value\":" + value + "}";
		} else {
			return "{\"attribute\":\"" + attribute + "\", \"value\":\"" + value + "\"}";
		}
	}
	
	
}
