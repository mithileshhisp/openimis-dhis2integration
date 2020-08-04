package org.beehyv.dhis2openimis.adapter.openimis.pojo.claim;

import org.beehyv.dhis2openimis.adapter.openimis.pojo.ExtensionItem;
import org.beehyv.dhis2openimis.adapter.openimis.pojo.Reference;
import org.beehyv.dhis2openimis.adapter.openimis.pojo.claim.claimItem.ClaimItem;
import org.beehyv.dhis2openimis.adapter.openimis.pojo.claim.diagnosis.Diagnosis;
import org.beehyv.dhis2openimis.adapter.openimis.pojo.identifier.Identifier;

import java.time.LocalDate;
import java.util.List;

public class Claim {

    private String resourceType;
    private BillablePeriod billablePeriod;
    private LocalDate created;
    private List<Diagnosis> diagnosis;
    private Reference enterer;
    private List<ExtensionItem> extension;
    private Reference facility;
    private String id;
    private List<Identifier> identifier;
    private List<ClaimItem> item;
    private Reference patient;
    private ClaimsTotal total;
    private ClaimsType type;

    public Claim() {
    	
    }

    public String getResourceType() {
        return resourceType;
    }
    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }
    public BillablePeriod getBillablePeriod() {
        return billablePeriod;
    }
    public void setBillablePeriod(BillablePeriod billablePeriod) {
        this.billablePeriod = billablePeriod;
    }
    public LocalDate getCreated() {
        return created;
    }
    public void setCreated(LocalDate created) {
        this.created = created;
    }
    public List<Diagnosis> getDiagnosis() {
        return diagnosis;
    }
    public void setDiagnosis(List<Diagnosis> diagnosis) {
        this.diagnosis = diagnosis;
    }
    public Reference getEnterer() {
		return enterer;
	}
	public void setEnterer(Reference enterer) {
		this.enterer = enterer;
	}
	public List<ExtensionItem> getExtension() {
		return extension;
	}
	public void setExtension(List<ExtensionItem> extension) {
		this.extension = extension;
	}
	public Reference getFacility() {
		return facility;
	}
	public void setFacility(Reference facility) {
		this.facility = facility;
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
    public Reference getPatient() {
		return patient;
	}
	public void setPatient(Reference patient) {
		this.patient = patient;
	}
	public ClaimsTotal getTotal() {
        return total;
    }
    public void setTotal(ClaimsTotal total) {
        this.total = total;
    }
    public ClaimsType getType() {
        return type;
    }
    public void setType(ClaimsType type) {
        this.type = type;
    }
	public List<ClaimItem> getItem() {
		return item;
	}
	public void setItem(List<ClaimItem> item) {
		this.item = item;
	}

	@Override
	public String toString() {
		return "Claim [resourceType=" + resourceType + ", billablePeriod=" + billablePeriod + ", created=" + created
				+ ", diagnosis=" + diagnosis + ", enterer=" + enterer + ", facility=" + facility + ", id=" + id
				+ ", identifier=" + identifier + ", item=" + item + ", patient=" + patient + ", total=" + total
				+ ", type=" + type + "]";
	}

	

    
}
