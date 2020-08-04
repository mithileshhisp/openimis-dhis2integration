package org.beehyv.dhis2openimis.adapter.dhis.pojo.data_element;

/**
 * @author Vishal
 */
public class DataElement {
    private String id;
    private String code;
    private String displayName;

    public DataElement() {
        super();
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getDisplayName() {
        return displayName;
    }
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
