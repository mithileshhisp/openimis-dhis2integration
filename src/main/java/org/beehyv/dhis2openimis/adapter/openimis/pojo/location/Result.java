/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.beehyv.dhis2openimis.adapter.openimis.pojo.location;

/**
 *
 * @author Mithilesh Thakur
 */
public class Result {
    
    private String DISTLGDCODE;
    private String DISTNAME;
    private String DISTRICTCODE;
    private String CITYCODE;
    private String CITYNAME;
    private String TotalPatients;
    private String TATMET;
    private String TATFAIL;
	
    public Result() {
		
	}

    /**
     * @return the DISTLGDCODE
     */
    public String getDISTLGDCODE() {
        return DISTLGDCODE;
    }

    /**
     * @param DISTLGDCODE the DISTLGDCODE to set
     */
    public void setDISTLGDCODE(String DISTLGDCODE) {
        this.DISTLGDCODE = DISTLGDCODE;
    }

    /**
     * @return the DISTNAME
     */
    public String getDISTNAME() {
        return DISTNAME;
    }

    /**
     * @param DISTNAME the DISTNAME to set
     */
    public void setDISTNAME(String DISTNAME) {
        this.DISTNAME = DISTNAME;
    }
    
    /**
     * @return the DISTRICTCODE
     */
    public String getDISTRICTCODE() {
        return DISTRICTCODE;
    }

    /**
     * @param DISTRICTCODE the DISTRICTCODE to set
     */
    public void setDISTRICTCODE(String DISTRICTCODE) {
        this.DISTRICTCODE = DISTRICTCODE;
    }
    
     /**
     * @return the CITYCODE
     */
    public String getCITYCODE() {
        return CITYCODE;
    }

    /**
     * @param CITYCODE the CITYCODE to set
     */
    public void setCITYCODE(String CITYCODE) {
        this.CITYCODE = CITYCODE;
    }

    /**
     * @return the CITYNAME
     */
    public String getCITYNAME() {
        return CITYNAME;
    }

    /**
     * @param CITYNAME the CITYNAME to set
     */
    public void setCITYNAME(String CITYNAME) {
        this.CITYNAME = CITYNAME;
    }

    /**
     * @return the TotalPatients
     */
    public String getTotalPatients() {
        return TotalPatients;
    }

    /**
     * @param TotalPatients the TotalPatients to set
     */
    public void setTotalPatients(String TotalPatients) {
        this.TotalPatients = TotalPatients;
    }

    /**
     * @return the TATMET
     */
    public String getTATMET() {
        return TATMET;
    }

    /**
     * @param TATMET the TATMET to set
     */
    public void setTATMET(String TATMET) {
        this.TATMET = TATMET;
    }

    /**
     * @return the TATFAIL
     */
    public String getTATFAIL() {
        return TATFAIL;
    }

    /**
     * @param TATFAIL the TATFAIL to set
     */
    public void setTATFAIL(String TATFAIL) {
        this.TATFAIL = TATFAIL;
    }
   
    /*
    @Override
	public String toString() {
		return "Result [DISTLGDCODE=" + DISTLGDCODE + ", DISTNAME=" + DISTNAME + ", DISTRICTCODE=" + DISTRICTCODE +  ", CITYCODE=" + CITYCODE + ", CITYNAME=" 
                        + CITYNAME + ", TotalPatients=" + TotalPatients +  ", TATMET=" + TATMET +  ", TATFAIL=" + TATFAIL + "]";
	}    
    */
}
