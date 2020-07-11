package org.hispindia.dhis2openimis.adapter.openimis;

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

import org.hispindia.dhis2openimis.adapter.dhis.pojo.organisation.OrganisationUnitAttribute;
import org.hispindia.dhis2openimis.adapter.dhis.pojo.organisation.OrganisationUnitAttributeRequest;
import org.hispindia.dhis2openimis.adapter.dhis.pojo.organisation.OrganisationUnitAttributeValue;
import org.hispindia.dhis2openimis.adapter.dhis.pojo.organisation.OrganisationUnitAttributeValueRequest;
import org.hispindia.dhis2openimis.adapter.dhis.pojo.organisation.OrganisationUnitDhis2;
import org.hispindia.dhis2openimis.adapter.dhis.pojo.organisation.OrganisationUnitDhis2Request;
import org.hispindia.dhis2openimis.adapter.dhis.pojo.organisation.OrganisationUnitParentRequest;
import org.hispindia.dhis2openimis.adapter.dhis.pojo.organisation.OrganisationUnitPostResponse;
import org.hispindia.dhis2openimis.adapter.dhis.pojo.organisation.OrganisationUnitsDhis2Bundle;
import org.hispindia.dhis2openimis.adapter.openimis.pojo.location.LegacyOpenIMISHealthFacility;
import org.hispindia.dhis2openimis.adapter.openimis.pojo.location.LegacyOpenIMISLocation;
import org.hispindia.dhis2openimis.adapter.util.ParamsUtil;
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


@Component
public class APICaller {
        private static final Logger logger = LoggerFactory.getLogger(APICaller.class);
        
        @Value("${app.dhis2.api.SyncOrganisationUnits}")
        String dhis2GetURL;
        
        private HttpEntity<Void> request;
	private RestTemplate restTemplate;  
        private HttpHeaders authHeaders;
	
        
        @Autowired
	public APICaller(RestTemplate restTemplate, @Qualifier("LocalDhis2")HttpHeaders authHeaders) {
		request = new HttpEntity<Void>(authHeaders);
		//restTemplate = new RestTemplate();
                this.restTemplate = restTemplate;
                this.authHeaders = authHeaders;
	}


 	public void getLegacyDdemoOpenIMISOrgAndPostToDhis2(String url){
		
            //RestTemplate restTemplate = new RestTemplate();
            
            //RestTemplate  restTemplate = new RestTemplate();
            
            /*
            List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();        
            MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
            converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));         
            messageConverters.add(converter);  
            restTemplate.setMessageConverters(messageConverters);    
            */
            
            
            System.out.println( "imisClaimResponseUrl -- " + url );
            //ResponseEntity response = restTemplate.exchange(url, HttpMethod.GET, request, Object.class);
            
            //ResponseEntity response = restTemplate.execute(url, HttpMethod.GET, null, null);
            
            //String result = restTemplate.getForObject(url, String.class);
            
            //System.out.println(result);
            
            //System.out.println( "response result -- " + result );
            
           // System.out.println( "response Body -- " + result.getBody());

            //RestTemplate restTemplate = new RestTemplate();
            //String result = restTemplate.getForObject(uri, String.class);
     
            //ResponseEntity<LegacyOpenIMISLocation> response = restTemplate.exchange(url, HttpMethod.GET, request, LegacyOpenIMISLocation.class);
            //LegacyOpenIMISLocation legacyOpenIMISLocation = response.getBody();
                
            
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            //Charset utf8 = Charset.forName("UTF-8");
            //MediaType mediaType = new MediaType("text", "html", utf8);
            //headers.setContentType(mediaType);
            headers.setContentType(MediaType.APPLICATION_JSON);
            
            headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
            HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
            
            //request = new HttpEntity<Void>(headers);
            
            
             //headers.setAcceptCharset(Arrays.asList(Charset.forName("UTF-8")));
            //HttpHeaders headers = new HttpHeaders();
            //headers.set("User-Agent", "mozilla");
            //headers.set("Accept-Language", "ko"); 
            
            //headers.setAccept(Arrays.asList(MediaType.ALL));
            
            
           
            ResponseEntity response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
            Object responseBody = response.getBody();
            
            
            
            /*
            ResponseEntity<LegacyOpenIMISLocationBundle> response = restTemplate.exchange(url, HttpMethod.GET, entity, LegacyOpenIMISLocationBundle.class);
            LegacyOpenIMISLocationBundle bundle = response.getBody();
            
            List<LegacyOpenIMISLocation> results = bundle.getLegacyOpenIMISLocations();
            */
            
            /*
            ResponseEntity<ResultsBundle> response = restTemplate.exchange(url, HttpMethod.GET, request, ResultsBundle.class);
            
            ResultsBundle bundle = response.getBody();
            
            List<Result> results = bundle.getResult();
            */
            
            //System.out.println( "response -- " + response );
            //System.out.println( "response body -- " + bundle );
            //System.out.println( "respons eBody  -- " + responseBody );
            
            //System.out.println( "results  -- " + results.size() );
            
          
            //List<Result> results = bundle.getResult();
            /*
            for(Result re : results) {
                System.out.println( "CITYCODE  -- " + re.getTotalPatients() );
            }
            */
            
            JSONObject obj = new JSONObject(responseBody.toString());
            JSONArray locationArr = obj.getJSONArray("locations");
            //System.out.println("json jarray  locations :" + arr.toString() );
            //System.out.println( "locations length-- " + arr.length() );
            /*
            for (int i = 0; i < arr.length(); i++)
            {
                int locationId =  arr.getJSONObject(i).getInt("locationId");
                System.out.println( "locationId -- " + locationId );
                
            }
            */
            
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
            
            /*
            for(LegacyOpenIMISLocation leve21Org : level2OrgUnits ) {
                
                System.out.println( leve21Org.getLocationId() + " -- " + leve21Org.getLocationCode() + " -- " + leve21Org.getLocationName() + " -- " + leve21Org.getLocationType() );
            }
            */
            
            System.out.println( legacyOpenIMISLocationArray.length );
            
            System.out.println( level2OrgUnits.size() + " -- " + level3OrgUnits.size() + " -- " + level4OrgUnits.size() + " -- " + level5OrgUnits.size() );
            
            JSONArray healthFacilityArr = obj.getJSONArray("hf");
            LegacyOpenIMISHealthFacility[] legacyOpenIMISHealthFacilityArray = gson.fromJson(healthFacilityArr.toString(), LegacyOpenIMISHealthFacility[].class);  
          
            List<LegacyOpenIMISHealthFacility> openIMISHealthFacilityList = new ArrayList<>();
            for(LegacyOpenIMISHealthFacility healthFacility : legacyOpenIMISHealthFacilityArray ) {
                openIMISHealthFacilityList.add(healthFacility);
            }        
                   
            System.out.println(  " Health Facility List " + openIMISHealthFacilityList.size() );
            //String result = restTemplate.exchange(url, HttpMethod.GET, entity, String.class).getBody();
            //System.out.println( "response  -- " + response );
            //Object result = response.getBody();
            //System.out.println( "legacyOpenIMISLocation -- " + reference );
            
            
            //String dhis2URL = "http://127.0.0.1:8092/dhis234/api/organisationUnits.json?fields=id,displayName,shortName,code,level,attributeValues[attribute[id,displayName,code],value]&sortOrder=ASC&paging=false";
            //String dhis2LevelURL = "http://127.0.0.1:8092/dhis234/api/organisationUnits.json?fields=id,displayName,shortName,code,level,attributeValues[attribute[id,displayName,code],value]&filter=level:eq:4&sortOrder=ASC&paging=false";
            //String dhis2PostURL = "http://127.0.0.1:8092/dhis234/api/organisationUnits";
            
            String dhis2PostURL = dhis2GetURL;
            
            System.out.println( "dhis2URL -- " + dhis2PostURL );
            
            String dhis2URL = dhis2GetURL + "?" + ParamsUtil.ORG_UNITS_SYNC_PARAM;
            
            System.out.println( "dhis2GetURL -- " + dhis2URL );
            
            
            //logger.debug(" debug url  -- " + dhis2URL );
            //logger.debug(" Posting orgUnit : --------------------- " );
            
            //OrganisationUnitsDhis2Bundle dhis2OrgBundle = getDhis2OrgUnitFromApi(dhis2URL);
            
            //System.out.println( "dhis2OrgBundle -- " + dhis2OrgBundle );
            
            //System.out.println( "dhis2URL OrganisationUnits -- " + dhis2OrgBundle.getOrganisationUnits() );
            
            
            
            /*
            for(OrganisationUnitDhis2 orgUntDhis2 : dhis2OrgBundle.getOrganisationUnits() ) {
                
                if( orgUntDhis2.getLevel().equalsIgnoreCase("1")){
                    leval2ParentId = orgUntDhis2.getId();
                }
                    
                System.out.println( " dhis2 org--  " + orgUntDhis2.getDisplayName() + " -- " + orgUntDhis2.getId() + " -- " + orgUntDhis2.getLevel() );
                
                for(OrganisationUnitAttributeValue orgAttrValue : orgUntDhis2.getAttributeValues() ) {
                    OrganisationUnitAttribute orgUnitAttribute = orgAttrValue.getAttribute();
                    
                    if( orgUnitAttribute.getCode().equalsIgnoreCase("openIMISLocationId")){
                        locationIdCodeId = orgUnitAttribute.getId();
                    }
                    else if( orgUnitAttribute.getCode().equalsIgnoreCase("openIMISLocationType")){
                        locationTypeCodeId = orgUnitAttribute.getId();
                    }
                    
                    
                    System.out.println( " dhis2 orgAttrValue--  " + orgAttrValue.getValue() + " -- " + orgUnitAttribute.getId() + " -- " + orgUnitAttribute.getCode() );
                }
            }
      

            */
            
            
            String leval2ParentId = "";
            String locationIdCodeId = "";
            String locationTypeCodeId = "";
            List<String> dhis2OrgUnitsCodeList = new ArrayList<>();
            Map<String, String> orgUnitParentIdMap =  new HashMap<>();
                       
            //getDhis2OrgUnitDetails( dhis2URL, leval2ParentId, locationIdCodeId, locationTypeCodeId, dhis2OrgUnitsCodeList, orgUnitParentIdMap );
            
            /*
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
                    //System.out.println( " dhis2 orgAttrValue--  " + orgAttrValue.getValue() + " -- " + orgUnitAttribute.getId() + " -- " + orgUnitAttribute.getCode() );
                }
            }
            */
           
            // level - 2
            /*
            for(LegacyOpenIMISLocation leve2Org : level2OrgUnits ) {
                
                OrganisationUnitDhis2Request organisationUnitDhis2Request = new OrganisationUnitDhis2Request();
                
                organisationUnitDhis2Request.setName(leve2Org.getLocationName());
                organisationUnitDhis2Request.setCode(leve2Org.getLocationCode());
                organisationUnitDhis2Request.setShortName(leve2Org.getLocationName());
                organisationUnitDhis2Request.setOpeningDate(LocalDate.now().toString());
                
                OrganisationUnitParentRequest orgUnitParent = new OrganisationUnitParentRequest();
                orgUnitParent.setId(leval2ParentId);
                organisationUnitDhis2Request.setParent( orgUnitParent );
                
                List<OrganisationUnitAttributeValueRequest> attributeValues = new ArrayList<>();
		fillLocationOrgAttrValue(attributeValues, locationIdCodeId, leve2Org.getLocationId());
		fillLocationOrgAttrValue(attributeValues, locationTypeCodeId, leve2Org.getLocationType());
                
                organisationUnitDhis2Request.setAttributeValues(attributeValues);
                
                //List<OrganisationUnitAttributeValueRequest> orgAttrValue = new ArrayList<>();

                //OrganisationUnitAttributeRequest orgUnitAttr = new OrganisationUnitAttributeRequest();

                //organisationUnitDhis2RequestList.add(organisationUnitDhis2Request);
                
                OrganisationUnitPostResponse postOrgResponse = postToDhis2(dhis2PostURL, organisationUnitDhis2Request );
                System.out.println( "post response Level 2  -- " + postOrgResponse.getResponse() );
            }
            */
            
            // for level-3
            /*
            getDhis2OrgUnitDetails( dhis2URL, leval2ParentId, locationIdCodeId, locationTypeCodeId, dhis2OrgUnitsCodeList, orgUnitParentIdMap );
            postToDhis2( dhis2PostURL, level3OrgUnits, orgUnitParentIdMap, leval2ParentId, locationIdCodeId, locationTypeCodeId, dhis2OrgUnitsCodeList  );
            */
            
            /*
            for(LegacyOpenIMISLocation leve3Org : level3OrgUnits ) {
                
                OrganisationUnitDhis2Request organisationUnitDhis2Request = new OrganisationUnitDhis2Request();
                
                organisationUnitDhis2Request.setName(leve3Org.getLocationName());
                organisationUnitDhis2Request.setCode(leve3Org.getLocationCode());
                organisationUnitDhis2Request.setShortName(leve3Org.getLocationName());
                organisationUnitDhis2Request.setOpeningDate(LocalDate.now().toString());
                
                OrganisationUnitParentRequest orgUnitParent = new OrganisationUnitParentRequest();
                orgUnitParent.setId(orgUnitParentIdMap.get(leve3Org.getParentLocationId()));
                organisationUnitDhis2Request.setParent( orgUnitParent );
                
                List<OrganisationUnitAttributeValueRequest> attributeValues = new ArrayList<>();
		fillLocationOrgAttrValue(attributeValues, locationIdCodeId, leve3Org.getLocationId());
		fillLocationOrgAttrValue(attributeValues, locationTypeCodeId, leve3Org.getLocationType());
                
                organisationUnitDhis2Request.setAttributeValues(attributeValues);
                
                OrganisationUnitPostResponse postOrgResponse = postToDhis2(dhis2PostURL, organisationUnitDhis2Request );
                System.out.println( "post response -- " + postOrgResponse.getResponse() );
            }
            */
            
            // level -4
            /*
            getDhis2OrgUnitDetails( dhis2URL, leval2ParentId, locationIdCodeId, locationTypeCodeId, dhis2OrgUnitsCodeList, orgUnitParentIdMap );
            postToDhis2( dhis2PostURL, level4OrgUnits, orgUnitParentIdMap, leval2ParentId, locationIdCodeId, locationTypeCodeId, dhis2OrgUnitsCodeList  );
            */
            
            /*
            for(LegacyOpenIMISLocation leve4Org : level4OrgUnits ) {
                
                OrganisationUnitDhis2Request organisationUnitDhis2Request = new OrganisationUnitDhis2Request();
                
                organisationUnitDhis2Request.setName(leve4Org.getLocationName());
                organisationUnitDhis2Request.setCode(leve4Org.getLocationCode());
                organisationUnitDhis2Request.setShortName(leve4Org.getLocationName());
                organisationUnitDhis2Request.setOpeningDate(LocalDate.now().toString());
                
                OrganisationUnitParentRequest orgUnitParent = new OrganisationUnitParentRequest();
                orgUnitParent.setId(orgUnitParentIdMap.get(leve4Org.getParentLocationId()));
                organisationUnitDhis2Request.setParent( orgUnitParent );
                
                List<OrganisationUnitAttributeValueRequest> attributeValues = new ArrayList<>();
		fillLocationOrgAttrValue(attributeValues, locationIdCodeId, leve4Org.getLocationId());
		fillLocationOrgAttrValue(attributeValues, locationTypeCodeId, leve4Org.getLocationType());
                
                organisationUnitDhis2Request.setAttributeValues(attributeValues);
                
                OrganisationUnitPostResponse postOrgResponse = postToDhis2(dhis2PostURL, organisationUnitDhis2Request );
                System.out.println( "post response -- " + postOrgResponse.getResponse() );
            }
            */
            
            // level -5
            /*
            getDhis2OrgUnitDetails( dhis2URL, leval2ParentId, locationIdCodeId, locationTypeCodeId, dhis2OrgUnitsCodeList, orgUnitParentIdMap );
            postToDhis2( dhis2PostURL, level5OrgUnits, orgUnitParentIdMap, leval2ParentId, locationIdCodeId, locationTypeCodeId, dhis2OrgUnitsCodeList  );
            */
            
            /*
            for(LegacyOpenIMISLocation leve5Org : level5OrgUnits ) {
                
                OrganisationUnitDhis2Request organisationUnitDhis2Request = new OrganisationUnitDhis2Request();
                
                organisationUnitDhis2Request.setName(leve5Org.getLocationName());
                organisationUnitDhis2Request.setCode(leve5Org.getLocationCode());
                organisationUnitDhis2Request.setShortName(leve5Org.getLocationName());
                organisationUnitDhis2Request.setOpeningDate(LocalDate.now().toString());
                
                OrganisationUnitParentRequest orgUnitParent = new OrganisationUnitParentRequest();
                orgUnitParent.setId(orgUnitParentIdMap.get(leve5Org.getParentLocationId()));
                organisationUnitDhis2Request.setParent( orgUnitParent );
                
                List<OrganisationUnitAttributeValueRequest> attributeValues = new ArrayList<>();
		fillLocationOrgAttrValue(attributeValues, locationIdCodeId, leve5Org.getLocationId());
		fillLocationOrgAttrValue(attributeValues, locationTypeCodeId, leve5Org.getLocationType());
                
                organisationUnitDhis2Request.setAttributeValues(attributeValues);
                
                OrganisationUnitPostResponse postOrgResponse = postToDhis2(dhis2PostURL, organisationUnitDhis2Request );
                System.out.println( "post response -- " + postOrgResponse.getResponse() );
            }
            */
            
            // for health-facility
            /*
            getDhis2OrgUnitDetails( dhis2URL, leval2ParentId, locationIdCodeId, locationTypeCodeId, dhis2OrgUnitsCodeList, orgUnitParentIdMap );
            postHealthFacilityToDhis2( dhis2PostURL, openIMISHealthFacilityList, orgUnitParentIdMap, locationIdCodeId, locationTypeCodeId, dhis2OrgUnitsCodeList );
            */
            
            /*
            openIMISHealthFacilityList.stream().filter((healthFacility) -> ( !dhis2OrgUnitsCodeList.contains(healthFacility.getHfCode() ))).map((healthFacility) -> {
                OrganisationUnitDhis2Request organisationUnitDhis2Request = new OrganisationUnitDhis2Request();
                organisationUnitDhis2Request.setName(healthFacility.getHfName());
                organisationUnitDhis2Request.setCode(healthFacility.getHfCode());
                organisationUnitDhis2Request.setShortName(healthFacility.getHfName());
                organisationUnitDhis2Request.setOpeningDate(LocalDate.now().toString());
                OrganisationUnitParentRequest orgUnitParent = new OrganisationUnitParentRequest();
                orgUnitParent.setId(orgUnitParentIdMap.get(healthFacility.getLocationId()));
                organisationUnitDhis2Request.setParent( orgUnitParent );
                List<OrganisationUnitAttributeValueRequest> attributeValues = new ArrayList<>();
                fillLocationOrgAttrValue(attributeValues, locationIdCodeId, healthFacility.getHfid());
                fillLocationOrgAttrValue(attributeValues, locationTypeCodeId, healthFacility.getHfLevel() );
                organisationUnitDhis2Request.setAttributeValues(attributeValues);
                return organisationUnitDhis2Request;
            }).map((organisationUnitDhis2Request) -> postToDhis2(dhis2PostURL, organisationUnitDhis2Request )).forEach((postOrgResponse) -> {
                System.out.println( "post response -- " + postOrgResponse.getResponse() );
            }); 
            */
            
            
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

            /*
            try {
            } catch (Exception e) {
            logger.info("Legacy Ddemo OpenIMIS Org Exception");
            }
             */

	}

	private OrganisationUnitsDhis2Bundle getDhis2OrgUnitFromApi(String url) {
            
            /*
            RestTemplate restTemplate = new RestTemplate();
            String plainCreds = "admin:district";
            byte[] plainCredsBytes = plainCreds.getBytes();
            byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
            String base64Creds = new String(base64CredsBytes);

            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", "Basic " + base64Creds);
            HttpEntity<String> newRequest = new HttpEntity<String>(headers);
            */
            //request = new HttpEntity<Void>(authHeaders);
            ResponseEntity<OrganisationUnitsDhis2Bundle> responseOrgUnitDhis2 = restTemplate.exchange(url, HttpMethod.GET, request, OrganisationUnitsDhis2Bundle.class);
            return responseOrgUnitDhis2.getBody();
	}
        
        private OrganisationUnitPostResponse postToDhis2(String dhis2PostURL, OrganisationUnitDhis2Request organisationUnitDhis2RequestList) {
            
            //OrganisationUnitsDhis2BundleRequest organisationUnitsDhis2BundleRequest = new OrganisationUnitsDhis2BundleRequest();
            
            //organisationUnitsDhis2BundleRequest.setOrganisationUnits(organisationUnitDhis2RequestList);
            
            /*
            List<OrganisationUnitDhis2Request> organisationUnitDhis2Requests = Collections.singletonList(organisationUnitDhis2RequestList);
	    OrganisationUnitsDhis2BundleRequest organisationUnitsDhis2BundleRequest = new OrganisationUnitsDhis2BundleRequest();
            organisationUnitsDhis2BundleRequest.setOrganisationUnits(organisationUnitDhis2Requests);
            */
            
            /*
            RestTemplate restTemplate = new RestTemplate();
            String plainCreds = "admin:district";
            byte[] plainCredsBytes = plainCreds.getBytes();
            byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
            String base64Creds = new String(base64CredsBytes);
                    
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", "Basic " + base64Creds);
            */
            
            //HttpEntity<String> newRequest = new HttpEntity<String>(headers);
            
            //HttpEntity<TrackedEntityRequest> postRrequest = new HttpEntity<TrackedEntityRequest>(claim, headers);
            //HttpEntity<EnrollmentRequestBody> request = new HttpEntity<>(enrollment, authHeaders);
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
