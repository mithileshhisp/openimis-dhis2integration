package org.beehyv.dhis2openimis.adapter.dhis.fetch;

import org.beehyv.dhis2openimis.adapter.dhis.cache.program.ProgramCache;
import org.beehyv.dhis2openimis.adapter.dhis.pojo.program.ProgramBundle;
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
public class ProgramFetcher {
    private RestTemplate restTemplate;
    private HttpEntity<Void> request;
    private ProgramCache cache;
    
    @Value("${app.dhis2.api.Programs}")
    private String programsUrl;
    
    @Autowired
    public ProgramFetcher(@Qualifier("Dhis2") HttpHeaders authHeaders, ProgramCache cache) {
        request = new HttpEntity<Void>(authHeaders);
        this.cache = cache;
        restTemplate = new RestTemplate();
    }

    public void fetchAndCache() {
    	String url = getUrl();
        ResponseEntity<ProgramBundle> response = restTemplate.exchange(url, HttpMethod.GET, request, ProgramBundle.class);

        ProgramBundle bundle = response.getBody();

        cache.save(bundle);
    }

	private String getUrl() {
		return programsUrl + "?" + ParamsUtil.PAGER_PARAM + "&" + ParamsUtil.PROGRAM_FIELDS_PARAM;
	}
}
