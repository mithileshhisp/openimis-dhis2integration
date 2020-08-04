package org.beehyv.dhis2openimis.adapter.dhis.fetch;

import org.beehyv.dhis2openimis.adapter.dhis.cache.TrackedEntityTypeCache;
import org.beehyv.dhis2openimis.adapter.dhis.pojo.type.TrackedEntityTypeBundle;
import org.beehyv.dhis2openimis.adapter.util.ParamsUtil;
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
public class TrackedEntityTypeFetcher {
	private RestTemplate restTemplate;
	private HttpEntity<Void> request;
	private TrackedEntityTypeCache cache;
	
	@Value("${app.dhis2.api.TrackedEntityTypes}")
	private String trackedEntityTypesUrl;
	
	@Autowired
	public TrackedEntityTypeFetcher(@Qualifier("Dhis2") HttpHeaders authHeaders, TrackedEntityTypeCache cache) {
		request = new HttpEntity<Void>(authHeaders);
		this.cache = cache;
		restTemplate = new RestTemplate();
	}
	
	public void fetchAndCache() {
		String url = getUrl();
		ResponseEntity<TrackedEntityTypeBundle> response = 
				restTemplate.exchange(url, HttpMethod.GET, request, TrackedEntityTypeBundle.class);
		
		TrackedEntityTypeBundle bundle = response.getBody();
		
		cache.save(bundle);
	}

	private String getUrl() {
		return trackedEntityTypesUrl + "?" + ParamsUtil.PAGER_PARAM;
	}
	
	
}
