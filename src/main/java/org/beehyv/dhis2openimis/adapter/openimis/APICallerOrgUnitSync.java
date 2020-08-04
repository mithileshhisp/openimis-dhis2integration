/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.beehyv.dhis2openimis.adapter.openimis;

import com.google.gson.Gson;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.stream.Collectors;
import org.beehyv.dhis2openimis.adapter.dhis.pojo.organisation.OrganisationUnitAttribute;
import org.beehyv.dhis2openimis.adapter.dhis.pojo.organisation.OrganisationUnitAttributeRequest;
import org.beehyv.dhis2openimis.adapter.dhis.pojo.organisation.OrganisationUnitAttributeValue;
import org.beehyv.dhis2openimis.adapter.dhis.pojo.organisation.OrganisationUnitAttributeValueRequest;
import org.beehyv.dhis2openimis.adapter.dhis.pojo.organisation.OrganisationUnitDhis2;
import org.beehyv.dhis2openimis.adapter.dhis.pojo.organisation.OrganisationUnitDhis2Request;
import org.beehyv.dhis2openimis.adapter.dhis.pojo.organisation.OrganisationUnitParentRequest;
import org.beehyv.dhis2openimis.adapter.dhis.pojo.organisation.OrganisationUnitPostResponse;
import org.beehyv.dhis2openimis.adapter.dhis.pojo.organisation.OrganisationUnitsDhis2Bundle;
import org.beehyv.dhis2openimis.adapter.openimis.pojo.location.LegacyOpenIMISHealthFacility;
import org.beehyv.dhis2openimis.adapter.openimis.pojo.location.LegacyOpenIMISLocation;
import org.beehyv.dhis2openimis.adapter.util.ParamsUtil;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Mithilesh Thakur
 */
@Component
public class APICallerOrgUnitSync {
private static final Logger logger = LoggerFactory.getLogger(APICallerOrgUnitSync.class);
        
        @Value("${app.dhis2.api.SyncOrganisationUnits}")
        String dhis2GetURL;
        
        private HttpEntity<Void> request;
	private RestTemplate restTemplate;  
        private HttpHeaders authHeaders;
	
        
        @Autowired
	public APICallerOrgUnitSync(RestTemplate restTemplate, @Qualifier("LocalDhis2")HttpHeaders authHeaders) {
		request = new HttpEntity<Void>(authHeaders);
		//restTemplate = new RestTemplate();
                this.restTemplate = restTemplate;
                this.authHeaders = authHeaders;
	}
        
 	public void getLegacyDdemoOpenIMISOrgAndPostToDhis2(String url){

            //System.out.println( "imisClaimResponseUrl -- " + url );

            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
 
            headers.setContentType(MediaType.APPLICATION_JSON);
            
            headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
            HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
 
            ResponseEntity response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
            Object responseBody = response.getBody();
 
            JSONObject obj = new JSONObject(responseBody.toString());
            JSONArray locationArr = obj.getJSONArray("locations");

            Gson gson = new Gson(); 
 
            LegacyOpenIMISLocation[] legacyOpenIMISLocationArray = gson.fromJson(locationArr.toString(), LegacyOpenIMISLocation[].class);  
 
            List<LegacyOpenIMISLocation> level2OrgUnits = new ArrayList<>();
            List<LegacyOpenIMISLocation> level3OrgUnits = new ArrayList<>();
            List<LegacyOpenIMISLocation> level4OrgUnits = new ArrayList<>();
            List<LegacyOpenIMISLocation> level5OrgUnits = new ArrayList<>();
            
            for(LegacyOpenIMISLocation location : legacyOpenIMISLocationArray ) {
//                
//                if( location.getParentLocationId() == null && location.getLocationType().equalsIgnoreCase("R")){
//                    level2OrgUnits.add(location);
//                }
                
                if( location.getParentLocationId() == null ){
                    level2OrgUnits.add(location);
                }
                else if( location.getLocationType().equalsIgnoreCase("D") && location.getParentLocationId() != null){
                    level3OrgUnits.add(location);
                }
                else if(  location.getLocationType().equalsIgnoreCase("W") && location.getParentLocationId() != null){
                    level4OrgUnits.add(location);
                }
                else if( location.getLocationType().equalsIgnoreCase("V") && location.getParentLocationId() != null){
                    level5OrgUnits.add(location);
                }
                //System.out.println(location);
            }

            //System.out.println( legacyOpenIMISLocationArray.length );
            
            //System.out.println( level2OrgUnits.size() + " -- " + level3OrgUnits.size() + " -- " + level4OrgUnits.size() + " -- " + level5OrgUnits.size() );
            
            JSONArray healthFacilityArr = obj.getJSONArray("hf");
            LegacyOpenIMISHealthFacility[] legacyOpenIMISHealthFacilityArray = gson.fromJson(healthFacilityArr.toString(), LegacyOpenIMISHealthFacility[].class);  
          
            List<LegacyOpenIMISHealthFacility> openIMISHealthFacilityList = new ArrayList<>();
            for(LegacyOpenIMISHealthFacility healthFacility : legacyOpenIMISHealthFacilityArray ) {
                openIMISHealthFacilityList.add(healthFacility);
            }        
                   
            //System.out.println(  " Health Facility List " + openIMISHealthFacilityList.size() );

            String dhis2PostURL = dhis2GetURL;
            
            //System.out.println( "dhis2URL -- " + dhis2PostURL );
            
            String dhis2URL = dhis2GetURL + "?" + ParamsUtil.ORG_UNITS_SYNC_PARAM;
            
            //System.out.println( "dhis2GetURL -- " + dhis2URL );
 
            String leval2ParentId = "";
            String locationIdCodeId = "";
            String locationTypeCodeId = "";
            List<String> dhis2OrgUnitsCodeList = new ArrayList<>();
            Map<String, String> orgUnitParentIdMap =  new HashMap<>();

            // for level-2
            //getDhis2OrgUnitDetails( dhis2URL, leval2ParentId, locationIdCodeId, locationTypeCodeId, dhis2OrgUnitsCodeList, orgUnitParentIdMap );
            logger.info("Posting Level - 2 -- " + + level2OrgUnits.size() );
            //level2OrgUnits.stream().distinct().collect(Collectors.toList());
            level2OrgUnits = level2OrgUnits.stream().collect(Collectors.toCollection(()->new TreeSet<>(Comparator.comparing(LegacyOpenIMISLocation::getLocationCode)))).stream().collect(Collectors.toList());
            logger.info("Posting Level - 2 -- " + + level2OrgUnits.size() );
            createOrgObjectAndPostToDhis2( 2, dhis2URL, dhis2PostURL, level2OrgUnits, orgUnitParentIdMap, leval2ParentId, locationIdCodeId, locationTypeCodeId, dhis2OrgUnitsCodeList  );
                         
            //// for level-3
            //getDhis2OrgUnitDetails( dhis2URL, leval2ParentId, locationIdCodeId, locationTypeCodeId, dhis2OrgUnitsCodeList, orgUnitParentIdMap );
            level3OrgUnits = level3OrgUnits.stream().collect(Collectors.toCollection(()->new TreeSet<>(Comparator.comparing(LegacyOpenIMISLocation::getLocationCode)))).stream().collect(Collectors.toList());
            logger.info("Posting Level - 3 -- " + + level3OrgUnits.size() );
            
            createOrgObjectAndPostToDhis2( 3, dhis2URL, dhis2PostURL, level3OrgUnits, orgUnitParentIdMap, leval2ParentId, locationIdCodeId, locationTypeCodeId, dhis2OrgUnitsCodeList  );
            
            // level -4
            //getDhis2OrgUnitDetails( dhis2URL, leval2ParentId, locationIdCodeId, locationTypeCodeId, dhis2OrgUnitsCodeList, orgUnitParentIdMap );
            level4OrgUnits = level4OrgUnits.stream().collect(Collectors.toCollection(()->new TreeSet<>(Comparator.comparing(LegacyOpenIMISLocation::getLocationCode)))).stream().collect(Collectors.toList());
            logger.info("Posting Level - 4 -- " + level4OrgUnits.size() );
            createOrgObjectAndPostToDhis2( 4, dhis2URL, dhis2PostURL, level4OrgUnits, orgUnitParentIdMap, leval2ParentId, locationIdCodeId, locationTypeCodeId, dhis2OrgUnitsCodeList  );
            
            // level -5
            //getDhis2OrgUnitDetails( dhis2URL, leval2ParentId, locationIdCodeId, locationTypeCodeId, dhis2OrgUnitsCodeList, orgUnitParentIdMap );
            
            level5OrgUnits = level5OrgUnits.stream().collect(Collectors.toCollection(()->new TreeSet<>(Comparator.comparing(LegacyOpenIMISLocation::getLocationCode)))).stream().collect(Collectors.toList());
            logger.info("Posting Level - 5 -- " + level5OrgUnits.size());
            createOrgObjectAndPostToDhis2( 5, dhis2URL, dhis2PostURL, level5OrgUnits, orgUnitParentIdMap, leval2ParentId, locationIdCodeId, locationTypeCodeId, dhis2OrgUnitsCodeList  );
           
            // for health-facility
            //getDhis2OrgUnitDetails( dhis2URL, leval2ParentId, locationIdCodeId, locationTypeCodeId, dhis2OrgUnitsCodeList, orgUnitParentIdMap );
            
            openIMISHealthFacilityList = openIMISHealthFacilityList.stream().collect(Collectors.toCollection(()->new TreeSet<>(Comparator.comparing(LegacyOpenIMISHealthFacility::getHfCode)))).stream().collect(Collectors.toList());
            logger.info("Posting Health Facility -- " + openIMISHealthFacilityList.size());
            postHealthFacilityToDhis2( dhis2URL, dhis2PostURL, openIMISHealthFacilityList, orgUnitParentIdMap, leval2ParentId, locationIdCodeId, locationTypeCodeId, dhis2OrgUnitsCodeList );

	}

	private OrganisationUnitsDhis2Bundle getDhis2OrgUnitFromApi(String url) {
            
            ResponseEntity<OrganisationUnitsDhis2Bundle> responseOrgUnitDhis2 = restTemplate.exchange(url, HttpMethod.GET, request, OrganisationUnitsDhis2Bundle.class);
            return responseOrgUnitDhis2.getBody();
	}
        
        private OrganisationUnitPostResponse postToDhis2(String dhis2PostURL, OrganisationUnitDhis2Request organisationUnitDhis2RequestList) {

            restTemplate = new RestTemplate();
            HttpEntity<OrganisationUnitDhis2Request> postRrequest = new HttpEntity<>(organisationUnitDhis2RequestList, authHeaders);
            
            logger.debug("Posting orgUnit: " + organisationUnitDhis2RequestList.toString() + " to url: " + dhis2PostURL );
            ResponseEntity<OrganisationUnitPostResponse> response = restTemplate.exchange(dhis2PostURL, HttpMethod.POST, postRrequest, OrganisationUnitPostResponse.class);

            return response.getBody();
        }
        
	private void fillLocationOrgAttrValue(List<OrganisationUnitAttributeValueRequest> attributeValues, String locationIdCodeId, String attributeValue) {
            try {
                    OrganisationUnitAttributeRequest attribute = new OrganisationUnitAttributeRequest();
                    attribute.setId(locationIdCodeId);
                    
                    OrganisationUnitAttributeValueRequest orgAttributeValue = new OrganisationUnitAttributeValueRequest(attribute, attributeValue);

                    attributeValues.add(orgAttributeValue);
            } catch(NullPointerException e) {
                    //Do nothing as no proper mapping was found.
            }
	}
        
        /*
	private void getDhis2OrgUnitDetails( String dhis2URL, String leval2ParentId, String locationIdCodeId, String locationTypeCodeId,
                                            List<String> dhis2OrgUnitsCodeList, Map<String, String> orgUnitParentIdMap) {
            try {

                OrganisationUnitsDhis2Bundle dhis2OrgBundle = getDhis2OrgUnitFromApi(dhis2URL);
                for(OrganisationUnitDhis2 orgUntDhis2 : dhis2OrgBundle.getOrganisationUnits() ) {

                    System.out.println( " dhis2 org--  " + orgUntDhis2.getDisplayName() + " -- " + orgUntDhis2.getId() + " -- " + orgUntDhis2.getLevel() );
                    dhis2OrgUnitsCodeList.add(orgUntDhis2.getCode());
                    if( orgUntDhis2.getLevel().equalsIgnoreCase("1")){
                        leval2ParentId = orgUntDhis2.getId();
                    }

                    for(OrganisationUnitAttributeValue orgAttrValue : orgUntDhis2.getAttributeValues() ) {
                        OrganisationUnitAttribute orgUnitAttribute = orgAttrValue.getAttribute();

                        if( orgUnitAttribute.getCode().equalsIgnoreCase("openIMISLocationId")){
                            locationIdCodeId = orgUnitAttribute.getId();
                            orgUnitParentIdMap.put(orgAttrValue.getValue(), orgUntDhis2.getId());

                        }
                        else if( orgUnitAttribute.getCode().equalsIgnoreCase("openIMISLocationType")){
                            locationTypeCodeId = orgUnitAttribute.getId();
                        }
                    }
                }
            
            } catch(NullPointerException e) {
                    //Do nothing as no proper mapping was found.
            }
	}        
        */
        
	private void createOrgObjectAndPostToDhis2( int level, String dhis2URL, String dhis2PostURL, List<LegacyOpenIMISLocation> openIMISLocationList, Map<String, String> orgUnitParentIdMap, String leval2ParentId, String locationIdCodeId, String locationTypeCodeId, List<String> dhis2OrgUnitsCodeList ) {
            try {
                //getDhis2OrgUnitDetails( dhis2URL, leval2ParentId, locationIdCodeId, locationTypeCodeId, dhis2OrgUnitsCodeList, orgUnitParentIdMap );
                
                leval2ParentId = "";
                locationIdCodeId = "";
                locationTypeCodeId = "";
                dhis2OrgUnitsCodeList = new ArrayList<>();
                orgUnitParentIdMap =  new HashMap<>();
                
                OrganisationUnitsDhis2Bundle dhis2OrgBundle = getDhis2OrgUnitFromApi(dhis2URL);
                for(OrganisationUnitDhis2 orgUntDhis2 : dhis2OrgBundle.getOrganisationUnits() ) {

                    //System.out.println( " dhis2 org--  " + orgUntDhis2.getDisplayName() + " -- " + orgUntDhis2.getId() + " -- " + orgUntDhis2.getLevel() );
                    dhis2OrgUnitsCodeList.add(orgUntDhis2.getCode());
                    if( orgUntDhis2.getLevel().equalsIgnoreCase("1")){
                        leval2ParentId = orgUntDhis2.getId();
                    }

                    for(OrganisationUnitAttributeValue orgAttrValue : orgUntDhis2.getAttributeValues() ) {
                        OrganisationUnitAttribute orgUnitAttribute = orgAttrValue.getAttribute();

                        if( orgUnitAttribute.getCode().equalsIgnoreCase("openIMISLocationId")){
                            locationIdCodeId = orgUnitAttribute.getId();
                            orgUnitParentIdMap.put(orgAttrValue.getValue(), orgUntDhis2.getId());

                        }
                        else if( orgUnitAttribute.getCode().equalsIgnoreCase("openIMISLocationType")){
                            locationTypeCodeId = orgUnitAttribute.getId();
                        }
                    }
                }
                
                if( openIMISLocationList !=null && openIMISLocationList.size() > 0 ){
                    //Set<LegacyOpenIMISLocation> openIMISLocationSet = openIMISLocationList.stream().collect(Collectors.toSet());
                
                    //System.out.println( leval2ParentId + " --  " + locationIdCodeId + " -- " + locationTypeCodeId + " -- " + dhis2OrgUnitsCodeList.size() + " -- " + orgUnitParentIdMap.size() );
                    for(LegacyOpenIMISLocation openIMISLocation : openIMISLocationList ) {
                        if( !dhis2OrgUnitsCodeList.contains( openIMISLocation.getLocationCode() )){
                            OrganisationUnitDhis2Request organisationUnitDhis2Request = new OrganisationUnitDhis2Request();

                            organisationUnitDhis2Request.setName(openIMISLocation.getLocationName());
                            organisationUnitDhis2Request.setCode(openIMISLocation.getLocationCode());
                            organisationUnitDhis2Request.setShortName(openIMISLocation.getLocationName());
                            //organisationUnitDhis2Request.setOpeningDate(LocalDate.now().toString());
                            //LocalDate date = LocalDate.parse("1990-01-01");
                            organisationUnitDhis2Request.setOpeningDate(LocalDate.of(1990, 1, 1).toString());
                            OrganisationUnitParentRequest orgUnitParent = new OrganisationUnitParentRequest();
        //                    if( openIMISLocation.getParentLocationId() == null && openIMISLocation.getLocationType().equalsIgnoreCase("R")){
        //                        orgUnitParent.setId(leval2ParentId);
        //                    }
                            if( openIMISLocation.getParentLocationId() == null ){
                                orgUnitParent.setId(leval2ParentId);
                            }

                            else{
                                orgUnitParent.setId(orgUnitParentIdMap.get(openIMISLocation.getParentLocationId()));
                            }

                            organisationUnitDhis2Request.setParent( orgUnitParent );

                            List<OrganisationUnitAttributeValueRequest> attributeValues = new ArrayList<>();
                            fillLocationOrgAttrValue(attributeValues, locationIdCodeId, openIMISLocation.getLocationId());
                            fillLocationOrgAttrValue(attributeValues, locationTypeCodeId, openIMISLocation.getLocationType());

                            organisationUnitDhis2Request.setAttributeValues(attributeValues);
    
                            if( organisationUnitDhis2Request.getParent().getId() != null ){
                                logger.info(" Pushing at Level " + level + " to dhis2 with code - " + openIMISLocation.getLocationCode() );
                                OrganisationUnitPostResponse postOrgResponse = postToDhis2(dhis2PostURL, organisationUnitDhis2Request );
                                logger.info( "post response -- " + postOrgResponse.getResponse() );
                                //System.out.println( "post response -- " + postOrgResponse.getResponse() );
                            }
                            else{
                                 logger.info(" Parent not present in openIMIS  at Level " + level + " with code - " + openIMISLocation.getLocationCode() );
                            }
                        }
                        else{
                            logger.info("Location at Level " + level + " present in dhis2 with code - " + openIMISLocation.getLocationCode() );
                        }
                      }
                }
                else{
                     logger.info("Location at Level " + level + " not present " );
                }
            } catch(NullPointerException e) {
                    //Do nothing as no proper mapping was found.
            }
	}       
        
        // post healthFacility
	private void postHealthFacilityToDhis2( String dhis2URL, String dhis2PostURL, List<LegacyOpenIMISHealthFacility> openIMISHealthFacilityList, Map<String, String> orgUnitParentIdMap, String leval2ParentId, String locationIdCodeId, String locationTypeCodeId, List<String> dhis2OrgUnitsCodeList  ) {
            
            try {
                //getDhis2OrgUnitDetails( dhis2URL, leval2ParentId, locationIdCodeId, locationTypeCodeId, dhis2OrgUnitsCodeList, orgUnitParentIdMap );
                leval2ParentId = "";
                locationIdCodeId = "";
                locationTypeCodeId = "";
                dhis2OrgUnitsCodeList = new ArrayList<>();
                orgUnitParentIdMap =  new HashMap<>();
                OrganisationUnitsDhis2Bundle dhis2OrgBundle = getDhis2OrgUnitFromApi(dhis2URL);
                for(OrganisationUnitDhis2 orgUntDhis2 : dhis2OrgBundle.getOrganisationUnits() ) {

                    //System.out.println( " dhis2 org--  " + orgUntDhis2.getDisplayName() + " -- " + orgUntDhis2.getId() + " -- " + orgUntDhis2.getLevel() );
                    dhis2OrgUnitsCodeList.add(orgUntDhis2.getCode());
                    if( orgUntDhis2.getLevel().equalsIgnoreCase("1")){
                        leval2ParentId = orgUntDhis2.getId();
                    }

                    for(OrganisationUnitAttributeValue orgAttrValue : orgUntDhis2.getAttributeValues() ) {
                        OrganisationUnitAttribute orgUnitAttribute = orgAttrValue.getAttribute();

                        if( orgUnitAttribute.getCode().equalsIgnoreCase("openIMISLocationId")){
                            locationIdCodeId = orgUnitAttribute.getId();
                            orgUnitParentIdMap.put(orgAttrValue.getValue(), orgUntDhis2.getId());

                        }
                        else if( orgUnitAttribute.getCode().equalsIgnoreCase("openIMISLocationType")){
                            locationTypeCodeId = orgUnitAttribute.getId();
                        }
                    }
                }
                if( openIMISHealthFacilityList !=null && openIMISHealthFacilityList.size() > 0 ){
                        //Set<LegacyOpenIMISHealthFacility> openIMISHealthFacilitySet = openIMISHealthFacilityList.stream().collect(Collectors.toSet());
                        for(LegacyOpenIMISHealthFacility healthFacility : openIMISHealthFacilityList ) {

                            if( !dhis2OrgUnitsCodeList.contains( healthFacility.getHfCode() )){

                                OrganisationUnitDhis2Request organisationUnitDhis2Request = new OrganisationUnitDhis2Request();

                                organisationUnitDhis2Request.setName(healthFacility.getHfName());
                                organisationUnitDhis2Request.setCode(healthFacility.getHfCode());
                                organisationUnitDhis2Request.setShortName(healthFacility.getHfName());
                                //organisationUnitDhis2Request.setOpeningDate(LocalDate.now().toString());
                                //LocalDate date = LocalDate.parse("1900-01-01");
                                organisationUnitDhis2Request.setOpeningDate(LocalDate.of(1990, 1, 1).toString());
                                OrganisationUnitParentRequest orgUnitParent = new OrganisationUnitParentRequest();
                                orgUnitParent.setId(orgUnitParentIdMap.get(healthFacility.getLocationId()));
                                organisationUnitDhis2Request.setParent( orgUnitParent );

                                List<OrganisationUnitAttributeValueRequest> attributeValues = new ArrayList<>();
                                fillLocationOrgAttrValue(attributeValues, locationIdCodeId, healthFacility.getHfid());
                                fillLocationOrgAttrValue(attributeValues, locationTypeCodeId, healthFacility.getHfLevel() );

                                organisationUnitDhis2Request.setAttributeValues(attributeValues);
                                
                                if( organisationUnitDhis2Request.getParent().getId() != null ){
                                    logger.info(" Pushing Health Facilityt to dhis2 with code - " + healthFacility.getHfCode() );
                                    OrganisationUnitPostResponse postOrgResponse = postToDhis2(dhis2PostURL, organisationUnitDhis2Request );
                                    System.out.println( "post response -- " + postOrgResponse.getResponse() );
                                }
                                else{
                                    logger.info(" Parent not present in openIMIS Health Facility with code - " + healthFacility.getHfCode() );
                                }
                                
                        }
                        else{
                            logger.info(" Health Facility present in dhis2 with code - " + healthFacility.getHfCode() );
                        }

                    }                   
                }
                else{
                    logger.info(" no Health Facility present "  );
                }
            } catch(NullPointerException e) {
                    //Do nothing as no proper mapping was found.
            }
	}        
}