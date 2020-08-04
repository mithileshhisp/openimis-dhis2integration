package org.beehyv.dhis2openimis.adapter.dhis.pojo.poster;

public class DataValue<T> {
	private T value;
	private String dataElement;
	
	public DataValue() {
		
	}
	
	public DataValue(String dataElement, T value) {
		this.dataElement = dataElement;
		this.value = value;
	}
	
	public T getValue() {
		return value;
	}
	public void setValue(T value) {
		this.value = value;
	}
	public String getDataElement() {
		return dataElement;
	}
	public void setDataElement(String dataElement) {
		this.dataElement = dataElement;
	}

	@Override
	public String toString() {
		if (value instanceof Integer || value instanceof Double) {
			return "{\"value\":" + value + ", \"dataElement\":\"" + dataElement + "\"}";
		} else {
			return "{\"value\":\"" + value + "\", \"dataElement\":\"" + dataElement + "\"}";
		}
	}
	
	
	
}
