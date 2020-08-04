package org.beehyv.dhis2openimis.adapter.dhis.fetch;

import java.util.List;

import org.beehyv.dhis2openimis.adapter.dhis.cache.org_unit.OrganisationUnitCacheService;
import org.beehyv.dhis2openimis.adapter.dhis.pojo.organisation.OrganisationUnit;
import org.beehyv.dhis2openimis.adapter.dhis.pojo.organisation.OrganisationUnitsBundle;
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
public class OrganisationUnitFetcher {
	private static final Logger logger = LoggerFactory.getLogger(OrganisationUnitFetcher.class);
	
	private HttpEntity<Void> request;
	private RestTemplate restTemplate;
	private OrganisationUnitCacheService orgCache;
	
	@Value("${app.dhis2.api.OrganisationUnits}")
	private String orgUnitsUrl;
	
	@Autowired
	public OrganisationUnitFetcher(@Qualifier("Dhis2") HttpHeaders authHeaders, OrganisationUnitCacheService orgCache) {
		request = new HttpEntity<Void>(authHeaders);
		this.orgCache = orgCache;
		restTemplate = new RestTemplate();
	}
	
	
	public void fetchAndCache() {
		String url = orgUnitsUrl + "?" + ParamsUtil.ORG_UNITS_PARAM;
		OrganisationUnitsBundle bundle = getFromApi(url);
		cache(bundle);
		logger.info("Organisation unit data extraction complete");
	}
	
	
	private OrganisationUnitsBundle getFromApi(String url) {
		ResponseEntity<OrganisationUnitsBundle> response = restTemplate.exchange(url, HttpMethod.GET, request, OrganisationUnitsBundle.class);
		return response.getBody();
	}
	
	
	private void cache(OrganisationUnitsBundle bundle) {
		List<OrganisationUnit> organisations = bundle.getOrganisationUnits();
		orgCache.save(organisations);
	}
}
