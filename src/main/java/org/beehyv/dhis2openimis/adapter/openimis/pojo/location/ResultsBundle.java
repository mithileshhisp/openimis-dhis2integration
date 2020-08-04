/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.beehyv.dhis2openimis.adapter.openimis.pojo.location;

import com.fasterxml.jackson.annotation.JsonRootName;
import java.util.List;


/**
 *
 * @author Mithilesh Thakur
 */

public class ResultsBundle {
    
    
    private List<Result> result;
	
    public ResultsBundle() {

    }


    /**
     * @return the result
     */
    public List<Result> getResult() {
        return result;
    }

    /**
     * @param result the result to set
     */
    public void setResult(List<Result> result) {
        this.result = result;
    }
    
    /*
    @Override
    public String toString() {
            return "ResultsBundle [result=" + result + "]";
    }
    */
    
}
