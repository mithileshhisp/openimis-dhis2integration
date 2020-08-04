package org.beehyv.dhis2openimis.adapter.openimis.pojo;

/**
 * @author Vishal
 */
public class ExtensionItem {
    private String url;
    private String valueString;
    private String valueDate;
    private Boolean valueBoolean;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getValueString() {
        return valueString;
    }

    public void setValueString(String valueString) {
        this.valueString = valueString;
    }

    public String getValueDate() {
        return valueDate;
    }

    public void setValueDate(String valueDate) {
        this.valueDate = valueDate;
    }

    public Boolean getValueBoolean() {
        return valueBoolean;
    }

    public void setValueBoolean(Boolean valueBoolean) {
        this.valueBoolean = valueBoolean;
    }

	@Override
	public String toString() {
		return "ExtensionItem [url=" + url + ", valueString=" + valueString + ", valueDate=" + valueDate
				+ ", valueBoolean=" + valueBoolean + "]";
	}
    
    
}
