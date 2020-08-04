package org.beehyv.dhis2openimis.adapter.openimis.pojo.location;

import org.beehyv.dhis2openimis.adapter.openimis.pojo.Address;
import org.beehyv.dhis2openimis.adapter.openimis.pojo.TelecomDetail;
import org.beehyv.dhis2openimis.adapter.openimis.pojo.identifier.Identifier;

import java.util.List;

public class Location {
    private String resourceType;
    private Address address;
    private String id;
    private List<Identifier> identifier;
    private String name;
    private List<TelecomDetail> telecom;
    private LocationType type;
    
    
    public Location() {
		
	}
    
    
    public String getResourceType() {
        return resourceType;
    }
    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }
    public Address getAddress() {
        return address;
    }
    public void setAddress(Address address) {
        this.address = address;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public List<Identifier> getIdentifier() {
        return identifier;
    }
    public void setIdentifier(List<Identifier> identifier) {
        this.identifier = identifier;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<TelecomDetail> getTelecom() {
        return telecom;
    }
    public void setTelecom(List<TelecomDetail> telecom) {
        this.telecom = telecom;
    }
    public LocationType getType() {
        return type;
    }
    public void setType(LocationType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Claim [resourceType=" + resourceType + ", address=" + address + ", id=" + id
                + ", identifiers=" + identifier + ", name=" + name + ", telecom=" + telecom +", type=" + type+ "]\n";
    }
}
