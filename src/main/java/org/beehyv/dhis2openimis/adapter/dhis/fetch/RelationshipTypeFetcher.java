package org.beehyv.dhis2openimis.adapter.dhis.fetch;

import org.beehyv.dhis2openimis.adapter.dhis.cache.RelationshipTypeCache;
import org.beehyv.dhis2openimis.adapter.dhis.pojo.relationship_type.RelationshipTypeBundle;
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

/**
 * @author Vishal
 */
@Component
public class RelationshipTypeFetcher {
    private RestTemplate restTemplate;
    private HttpEntity<Void> request;
    private RelationshipTypeCache cache;
    
    @Value("${app.dhis2.api.RelationshipTypes}")
    private String relationshipTypesUrl;
    
    @Autowired
    public RelationshipTypeFetcher(@Qualifier("Dhis2") HttpHeaders authHeaders, RelationshipTypeCache cache) {
        request = new HttpEntity<Void>(authHeaders);
        this.cache = cache;
        restTemplate = new RestTemplate();
    }

    public void fetchAndCache() {
    	String url = getUrl();
        ResponseEntity<RelationshipTypeBundle> response = 
        		restTemplate.exchange(url,HttpMethod.GET, request, RelationshipTypeBundle.class);

        RelationshipTypeBundle bundle = response.getBody();

        cache.save(bundle);
    }

	private String getUrl() {
		return relationshipTypesUrl + "?" + ParamsUtil.PAGER_PARAM;
	}
}
