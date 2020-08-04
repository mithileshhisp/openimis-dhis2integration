package org.beehyv.dhis2openimis.adapter.dhis.fetch;

import java.util.List;
import java.util.stream.Collectors;

import org.beehyv.dhis2openimis.adapter.dhis.cache.attribute.TrackedEntityAttributeCache;
import org.beehyv.dhis2openimis.adapter.dhis.cache.attribute_options.CachingService;
import org.beehyv.dhis2openimis.adapter.dhis.pojo.tracked_entity.TrackedEntityAttribute;
import org.beehyv.dhis2openimis.adapter.dhis.pojo.tracked_entity.TrackedEntityAttributeBundle;
import org.beehyv.dhis2openimis.adapter.dhis.pojo.tracked_entity.TrackedEntityAttributeDetail;
import org.beehyv.dhis2openimis.adapter.dhis.pojo.tracked_entity.TrackedEntityAttributeDetailBundle;
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

@Component
public class TrackedEntityAttributeAndOptionsFetcher {
	
	private static final Logger logger = LoggerFactory.getLogger(TrackedEntityAttributeAndOptionsFetcher.class);
	
	private HttpEntity<Void> request;
	private RestTemplate restTemplate;
	
	@Autowired private CachingService optionsCache;
	@Autowired private TrackedEntityAttributeCache attributeCache;
	
	@Value("${app.dhis2.api.TrackedEntityAttributes}")
	private String teaUrl;
	
	@Value("${app.dhis2.api.Options}")
	private String optionsUrl;
	
	@Autowired
	public TrackedEntityAttributeAndOptionsFetcher(@Qualifier("Dhis2") HttpHeaders authHeaders, RestTemplate restTemplate) {
		this.request = new HttpEntity<Void>(authHeaders);
		this.restTemplate = restTemplate;
	}
	
	
	public void fetchAndCache() {
		TrackedEntityAttributeDetailBundle detailBundle = getAllAttributes();
		attributeCache.save(detailBundle);
		logger.info("Attribute details bundle fetched and cached");
		
		List<TrackedEntityAttributeDetail> attributesWithOptions = filterAttributesWithOptions(detailBundle);
		
		for(TrackedEntityAttributeDetail attribute : attributesWithOptions) {
			List<TrackedEntityAttribute> attributeOptions = fetchOptionValuesBundle(attribute);
			optionsCache.saveInCache(attribute.getShortName(), attributeOptions);
			logger.info("Options fetched and cached for " + attribute.getShortName());
		}
	}
	
	
	private TrackedEntityAttributeDetailBundle getAllAttributes() {
		String url = teaUrl + "?" + ParamsUtil.TRACKED_ENTITY_ATTRIBUTES_PARAM;
		ResponseEntity<TrackedEntityAttributeDetailBundle> response = restTemplate.exchange(url, HttpMethod.GET, request, TrackedEntityAttributeDetailBundle.class);
		
		TrackedEntityAttributeDetailBundle bundle = response.getBody();
		return bundle;
	}


	private List<TrackedEntityAttributeDetail> filterAttributesWithOptions(TrackedEntityAttributeDetailBundle bundle) {
		List<TrackedEntityAttributeDetail> attributes = bundle.getTrackedEntityAttributes();
		
		List<TrackedEntityAttributeDetail> output = attributes.stream()
													.filter(attribute -> attribute.getOptionSet() != null)
													.collect(Collectors.toList());
		
		return output;
	}
	
	
	private List<TrackedEntityAttribute> fetchOptionValuesBundle(TrackedEntityAttributeDetail attribute) {
		String optionSetId = attribute.getOptionSet().getId();
		String url = getOptionsUrl(optionSetId);
		
		ResponseEntity<TrackedEntityAttributeBundle> response = restTemplate.exchange(url, HttpMethod.GET, request, TrackedEntityAttributeBundle.class);
		
		TrackedEntityAttributeBundle bundle = response.getBody();
		return bundle.getOptions();
	}
	
	private String getOptionsUrl(String optionSetId) {
    	return optionsUrl + "?paging=false&fields=id,displayName,code&filter=optionSet.id:eq:" + optionSetId;
    }
	
}
