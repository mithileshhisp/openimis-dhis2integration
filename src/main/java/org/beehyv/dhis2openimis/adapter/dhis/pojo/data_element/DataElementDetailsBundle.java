package org.beehyv.dhis2openimis.adapter.dhis.pojo.data_element;

import java.util.List;

/**
 * @author Vishal
 */
public class DataElementDetailsBundle {

    private List<DataElementDetail> dataElements;

    public DataElementDetailsBundle() {
        
    }

	public List<DataElementDetail> getDataElements() {
		return dataElements;
	}
	public void setDataElements(List<DataElementDetail> dataElements) {
		this.dataElements = dataElements;
	}

    
}
