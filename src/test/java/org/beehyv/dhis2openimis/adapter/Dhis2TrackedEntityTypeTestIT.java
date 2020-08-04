package org.beehyv.dhis2openimis.adapter;

import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class Dhis2TrackedEntityTypeTestIT {

    TestRestTemplate restTemplate = new TestRestTemplate();
    HttpHeaders headers = new HttpHeaders();
    static String reference;

    @Test
    public void testDhisPostTrackedEntityType() {
        String json = "{\"trackedEntityType\":\"EoBGArVCQ69\",\"orgUnit\":\"icaoSRGCUjW\",\"attributes\":[{\"attribute\":\"AAjWdVvBwtE\",\"value\":\"50\"},{\"attribute\":\"Hxyr4f36WHF\",\"value\":\"Emergency\"},{\"attribute\":\"Z4yrjMuGkeY\",\"value\":\"7\"},{\"attribute\":\"aEWuz6qyTs6\",\"value\":\"cx\"},{\"attribute\":\"cPbpCJnkrci\",\"value\":\"ds\"},{\"attribute\":\"gRLd9ezU69M\",\"value\":\"fd\"},{\"attribute\":\"wDBF7RjuEyp\",\"value\":\"tte\"},{\"attribute\":\"yoULFOTtmoP\",\"value\":\"xd\"}]}";

        headers.add(HttpHeaders.AUTHORIZATION, "Basic YmVlaHl2OkRoaXNAMTIz");
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
        HttpEntity<String> entity = new HttpEntity<String>(json, headers);
        ResponseEntity<String> response = restTemplate.exchange("http://172.105.47.158/imis/api/trackedEntityInstances", HttpMethod.POST, entity, String.class);
        int responseStatusCodeValue = response.getStatusCodeValue();
        reference = response.getBody().split("reference\":\"")[1].substring(0,11);
        assertEquals(200, responseStatusCodeValue);
    }

    @Test
    public void testDhisGetTrackedEntityType() {
        headers.add(HttpHeaders.AUTHORIZATION, "Basic YmVlaHl2OkRoaXNAMTIz");
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange("http://172.105.47.158/imis/api/trackedEntityInstances/" + reference + "?query.json", HttpMethod.GET, entity, String.class);
        assertNotNull(response);
    }
}
