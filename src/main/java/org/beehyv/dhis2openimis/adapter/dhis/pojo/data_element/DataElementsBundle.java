package org.beehyv.dhis2openimis.adapter.dhis.pojo.data_element;

import java.util.List;

/**
 * @author Vishal
 */
public class DataElementsBundle {

    private List<DataElement> options;

    public DataElementsBundle() {
        
    }

    public List<DataElement> getOptions() {
        return options;
    }
    public void setOptions(List<DataElement> options) {
        this.options = options;
    }
}
