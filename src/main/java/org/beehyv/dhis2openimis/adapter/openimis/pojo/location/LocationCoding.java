package org.beehyv.dhis2openimis.adapter.openimis.pojo.location;

public class LocationCoding {
    private String code;
    private String system;

    public LocationCoding() {
    	
    }

    
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getSystem() {
        return system;
    }
    public void setSystem(String system) {
        this.system = system;
    }


	@Override
	public String toString() {
		return "LocationCoding [code=" + code + ", system=" + system + "]";
	}

    
}
