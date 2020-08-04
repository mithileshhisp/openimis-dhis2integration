package org.beehyv.dhis2openimis.adapter.openimis.pojo.patient;

import java.util.List;

import org.beehyv.dhis2openimis.adapter.openimis.pojo.Address;
import org.beehyv.dhis2openimis.adapter.openimis.pojo.ExtensionItem;
import org.beehyv.dhis2openimis.adapter.openimis.pojo.NameDetail;
import org.beehyv.dhis2openimis.adapter.openimis.pojo.TelecomDetail;
import org.beehyv.dhis2openimis.adapter.openimis.pojo.identifier.Identifier;
import org.beehyv.dhis2openimis.adapter.openimis.pojo.patient.marital.MaritalStatusType;


public class Patient {
	private String resourceType;
	private List<Address> address;
	private String birthDate;
	private List<ExtensionItem> extension;
	private String gender;
	private String id;
	private List<Identifier> identifier;
	private MaritalStatusType maritalStatus;
	private List<NameDetail> name;
	private List<TelecomDetail> telecom;
	
	public Patient() {
		
	}

	public String getResourceType() {
		return resourceType;
	}
	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}
	public List<Address> getAddress() {
		return address;
	}
	public void setAddress(List<Address> address) {
		this.address = address;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
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
	public MaritalStatusType getMaritalStatus() {
		return maritalStatus;
	}
	public void setMaritalStatus(MaritalStatusType maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	public List<NameDetail> getName() {
		return name;
	}
	public void setName(List<NameDetail> name) {
		this.name = name;
	}
	public List<TelecomDetail> getTelecom() {
		return telecom;
	}
	public void setTelecom(List<TelecomDetail> telecom) {
		this.telecom = telecom;
	}

	public List<ExtensionItem> getExtension() {
		return extension;
	}

	public void setExtension(List<ExtensionItem> extension) {
		this.extension = extension;
	}

	@Override
	public String toString() {
		return "Patient [resourceType=" + resourceType + ", address=" + address + ", birthDate=" + birthDate +", extensions=" + extension
				+ ", gender=" + gender + ", id=" + id + ", identifier=" + identifier + ", maritalStatus="
				+ maritalStatus + ", name=" + name + ", telecom=" + telecom + "]\n";
	}
	
	
}
