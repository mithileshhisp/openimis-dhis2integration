package org.beehyv.dhis2openimis.adapter.dhis.fetch;

import org.beehyv.dhis2openimis.adapter.dhis.cache.attribute_options.CachingService;
import org.beehyv.dhis2openimis.adapter.dhis.cache.data_element.DataElementCache;
import org.beehyv.dhis2openimis.adapter.dhis.pojo.data_element.DataElement;
import org.beehyv.dhis2openimis.adapter.dhis.pojo.data_element.DataElementDetail;
import org.beehyv.dhis2openimis.adapter.dhis.pojo.data_element.DataElementDetailsBundle;
import org.beehyv.dhis2openimis.adapter.dhis.pojo.data_element.DataElementsBundle;
import org.beehyv.dhis2openimis.adapter.util.ParamsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Vishal
 */
@Component
public class DataElementFetcher {
    private static final Logger logger = LoggerFactory.getLogger(DataElementFetcher.class);
    private HttpEntity<Void> request;
    private RestTemplate restTemplate;

    @Autowired private CachingService optionsCache;
    @Autowired private DataElementCache dataElementCache;
    
    @Value("${app.dhis2.api.DataElements}")
    private String dataElementsUrl;
    
    @Value("${app.dhis2.api.Options}")
    private String optionsUrl;
    
    @Autowired
    public DataElementFetcher(@Qualifier("Dhis2") HttpHeaders authHeaders) {
        request = new HttpEntity<Void>(authHeaders);
        restTemplate = new RestTemplate();
    }


    public void fetchAndCache() {
        DataElementDetailsBundle detailBundle = fetchAllDataElements();
        dataElementCache.save(detailBundle);
        logger.info("Data Element Bundle fetched and cached");

        List<DataElementDetail> attributesWithOptions = filterAttributesWithOptions(detailBundle);

        for(DataElementDetail attribute : attributesWithOptions) {
            List<DataElement> attributeOptions = fetchOptionValuesBundle(attribute);
            logger.info("Data Element Options fetched for " + attribute.getShortName());
            optionsCache.saveDataElementsInCache(attribute.getShortName(), attributeOptions);
            
        }
        logger.info("Data Element Options fetched and cached");
    }


    private DataElementDetailsBundle fetchAllDataElements() {
        String url = dataElementsUrl + "?" + ParamsUtil.DATA_ELEMENTS_PARAM;
        ResponseEntity<DataElementDetailsBundle> response = restTemplate.exchange(url, HttpMethod.GET, request, DataElementDetailsBundle.class);

        DataElementDetailsBundle bundle = response.getBody();
        return bundle;
    }


    private List<DataElementDetail> filterAttributesWithOptions(DataElementDetailsBundle bundle) {
        List<DataElementDetail> attributes = bundle.getDataElements();

        List<DataElementDetail> output = attributes.stream()
                .filter(attribute -> attribute.getOptionSet() != null)
                .collect(Collectors.toList());

        return output;
    }


    private List<DataElement> fetchOptionValuesBundle(DataElementDetail attribute) {
        String optionSetId = attribute.getOptionSet().getId();
        String url = getOptionsUrl(optionSetId);

        ResponseEntity<DataElementsBundle> response = restTemplate.exchange(url, HttpMethod.GET, request, DataElementsBundle.class);

        DataElementsBundle bundle = response.getBody();
        return bundle.getOptions();
    }
    
    private String getOptionsUrl(String optionSetId) {
    	return optionsUrl + "?paging=false&fields=id,displayName,code&filter=optionSet.id:eq:" + optionSetId;
    }
}
