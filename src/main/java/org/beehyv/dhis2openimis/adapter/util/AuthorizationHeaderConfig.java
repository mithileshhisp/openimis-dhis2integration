package org.beehyv.dhis2openimis.adapter.util;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

@Configuration
public class AuthorizationHeaderConfig {
	@Value("${app.openimis.username}")
	private String imisUsername;
	
	@Value("${app.openimis.password}")
	private String imisPassword;
	
	@Value("${app.dhis2.username}")
	private String dhis2Username;
	
	@Value("${app.dhis2.password}")
	private String dhis2Password;
	
	
	@Bean("OpenImis")
	public HttpEntity<Void> getOpenImisConfiguredRequest() {
		HttpHeaders headers = new HttpHeaders();
	    headers.add("Authorization", getOpenIMISAuthorizationHeader());

	    HttpEntity<Void> request = new HttpEntity<>(headers);
	    return request;
	}
	
	
	@Bean("Dhis2")
	public HttpHeaders getDhis2ConfiguredRequest(){
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", getDhis2AuthorizationHeader());
		return headers;
	}
	
	private String getDhis2AuthorizationHeader() {
        return "Basic " + Base64.getEncoder().encodeToString((dhis2Username + ":" + dhis2Password).getBytes());
    }
	
	private String getOpenIMISAuthorizationHeader() {
        return "Basic " + Base64.getEncoder().encodeToString((imisUsername + ":" + imisPassword).getBytes());
    }
}
