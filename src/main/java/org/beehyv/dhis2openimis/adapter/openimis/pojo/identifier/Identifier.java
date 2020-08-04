package org.beehyv.dhis2openimis.adapter.openimis.pojo.identifier;

public class Identifier {
	private IdentifierType type;
	private String use;
	private String value;
	
	public Identifier() {
		
	}
	
	public IdentifierType getType() {
		return type;
	}
	public void setType(IdentifierType type) {
		this.type = type;
	}
	public String getUse() {
		return use;
	}
	public void setUse(String use) {
		this.use = use;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Identifier [type=" + type + ", use=" + use + ", value=" + value + "]";
	}
	
}
