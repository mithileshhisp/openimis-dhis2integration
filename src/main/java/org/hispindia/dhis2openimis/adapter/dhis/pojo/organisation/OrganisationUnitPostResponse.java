/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hispindia.dhis2openimis.adapter.dhis.pojo.organisation;

import org.hispindia.dhis2openimis.adapter.dhis.pojo.post_response.Response;

/**
 *
 * @author Mithilesh Thakur
 */
public class OrganisationUnitPostResponse {

    private String httpStatus;
    private Integer httpStatusCode;
    private String status;
    private Response response;

    public OrganisationUnitPostResponse() {

    }

    /**
     * @return the httpStatus
     */
    public String getHttpStatus() {
        return httpStatus;
    }

    /**
     * @param httpStatus the httpStatus to set
     */
    public void setHttpStatus(String httpStatus) {
        this.httpStatus = httpStatus;
    }

    /**
     * @return the httpStatusCode
     */
    public Integer getHttpStatusCode() {
        return httpStatusCode;
    }

    /**
     * @param httpStatusCode the httpStatusCode to set
     */
    public void setHttpStatusCode(Integer httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the response
     */
    public Response getResponse() {
        return response;
    }

    /**
     * @param response the response to set
     */
    public void setResponse(Response response) {
        this.response = response;
    }

}
