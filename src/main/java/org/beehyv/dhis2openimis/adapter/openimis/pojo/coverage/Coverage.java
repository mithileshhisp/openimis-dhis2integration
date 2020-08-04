package org.beehyv.dhis2openimis.adapter.openimis.pojo.coverage;

import org.beehyv.dhis2openimis.adapter.openimis.pojo.ExtensionItem;
import org.beehyv.dhis2openimis.adapter.openimis.pojo.Reference;
import org.beehyv.dhis2openimis.adapter.openimis.pojo.claim.BillablePeriod;
import org.beehyv.dhis2openimis.adapter.openimis.pojo.identifier.Identifier;

import java.util.List;

/**
 * @author Vishal
 */
public class Coverage {

    private String resourceType;
    private List<Contract> contract;
    private List<Identifier> identifier;
    private Reference policyHolder;
    private BillablePeriod period;
    private String status;
    private Grouping grouping;
    private List<ExtensionItem> extension;

    public Coverage() {
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public List<Contract> getContract() {
        return contract;
    }

    public void setContract(List<Contract> contract) {
        this.contract = contract;
    }

    public List<Identifier> getIdentifier() {
        return identifier;
    }

    public void setIdentifier(List<Identifier> identifier) {
        this.identifier = identifier;
    }

    public Reference getPolicyHolder() {
        return policyHolder;
    }

    public void setPolicyHolder(Reference policyHolder) {
        this.policyHolder = policyHolder;
    }

    public BillablePeriod getPeriod() {
        return period;
    }

    public void setPeriod(BillablePeriod period) {
        this.period = period;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Grouping getGrouping() {
        return grouping;
    }

    public void setGrouping(Grouping grouping) {
        this.grouping = grouping;
    }

    public List<ExtensionItem> getExtension() {
        return extension;
    }

    public void setExtension(List<ExtensionItem> extension) {
        this.extension = extension;
    }

    @Override
    public String toString() {
        return "Coverage{" +
                "resourceType='" + resourceType + '\'' +
                ", contract=" + contract +
                ", identifier=" + identifier +
                ", policyHolder=" + policyHolder +
                ", period=" + period +
                ", status='" + status + '\'' +
                ", grouping=" + grouping +
                ", extension=" + extension +
                '}';
    }
}

