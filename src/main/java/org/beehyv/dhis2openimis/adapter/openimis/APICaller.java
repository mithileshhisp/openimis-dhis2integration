package org.beehyv.dhis2openimis.adapter.openimis;

import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.beehyv.dhis2openimis.adapter.dhis.claim.ClaimFacade;
import org.beehyv.dhis2openimis.adapter.dhis.insuree.InsureeFacade;
import org.beehyv.dhis2openimis.adapter.dhis.insuree.policy_details.PolicyDetailsFacade;
import org.beehyv.dhis2openimis.adapter.openimis.cacheService.*;
import org.beehyv.dhis2openimis.adapter.openimis.pojo.Bundle;
import org.beehyv.dhis2openimis.adapter.openimis.pojo.LinkDetail;
import org.beehyv.dhis2openimis.adapter.openimis.pojo.claim.Claim;
import org.beehyv.dhis2openimis.adapter.openimis.pojo.claim.ClaimBundle;
import org.beehyv.dhis2openimis.adapter.openimis.pojo.claim.ClaimResource;
import org.beehyv.dhis2openimis.adapter.openimis.pojo.claimResponse.ClaimResponse;
import org.beehyv.dhis2openimis.adapter.openimis.pojo.claimResponse.ClaimResponseBundle;
import org.beehyv.dhis2openimis.adapter.openimis.pojo.claimResponse.ClaimResponseResource;
import org.beehyv.dhis2openimis.adapter.openimis.pojo.coverage.Coverage;
import org.beehyv.dhis2openimis.adapter.openimis.pojo.coverage.CoverageBundle;
import org.beehyv.dhis2openimis.adapter.openimis.pojo.coverage.CoverageResource;
import org.beehyv.dhis2openimis.adapter.openimis.pojo.location.Location;
import org.beehyv.dhis2openimis.adapter.openimis.pojo.location.LocationBundle;
import org.beehyv.dhis2openimis.adapter.openimis.pojo.location.LocationResource;
import org.beehyv.dhis2openimis.adapter.openimis.pojo.patient.Patient;
import org.beehyv.dhis2openimis.adapter.openimis.pojo.patient.PatientBundle;
import org.beehyv.dhis2openimis.adapter.openimis.pojo.patient.PatientResource;
import org.beehyv.dhis2openimis.adapter.openimis.pojo.practitioner.Practitioner;
import org.beehyv.dhis2openimis.adapter.openimis.pojo.practitioner.PractitionerBundle;
import org.beehyv.dhis2openimis.adapter.openimis.pojo.practitioner.PractitionerResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class APICaller {
	
	private static final Logger logger = LoggerFactory.getLogger(APICaller.class);
	
	private PatientCacheService patientCacheService;
	private ClaimCacheService claimCacheService;
	private ClaimResponseCacheService claimResponseCacheService;
	private LocationCacheService locationCacheService;
	private PractitionerCacheService practitionerCacheService;
	private CoverageCacheService coverageCacheService;
	private HttpEntity<Void> request;
	private InsureeFacade insureeFacade;
	private PolicyDetailsFacade policyDetailsFacade;
	private ClaimFacade claimFacade;
	
	@Autowired
	public APICaller(PatientCacheService patientCacheService, ClaimCacheService claimCacheService,
					 ClaimResponseCacheService claimResponseCacheService, LocationCacheService locationCacheService,
					 PractitionerCacheService practitionerCacheService, CoverageCacheService coverageCacheService,
					 @Qualifier("OpenImis") HttpEntity<Void> request, InsureeFacade insureeFacade, 
					 PolicyDetailsFacade policyDetailsFacade, ClaimFacade claimFacade) {
		this.patientCacheService = patientCacheService;
		this.claimCacheService = claimCacheService;
		this.claimResponseCacheService = claimResponseCacheService;
		this.locationCacheService = locationCacheService;
		this.practitionerCacheService = practitionerCacheService;
		this.coverageCacheService = coverageCacheService;
		this.request = request;
		this.insureeFacade = insureeFacade;
		this.policyDetailsFacade = policyDetailsFacade;
		this.claimFacade = claimFacade;
	}
	
	public void getCoverageBundle(String url) {
		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<CoverageBundle> response = restTemplate.exchange(url, HttpMethod.GET, request, CoverageBundle.class);
		CoverageBundle coverageBundle = response.getBody();

		coverageCacheService.clear();
		fillInCacheCoverage(coverageBundle);
		postCoverageToDhis();
		
		try {
			String nextPageUrl = getNextPageUrl(coverageBundle);
			logger.info("Fetching Coverage bundle from url :" + nextPageUrl);
			getCoverageBundle(nextPageUrl);
		} catch (NoSuchElementException e) {
			logger.info("---- Imis Coverage data export completed ----");
		}

	}

	public void getPatientBundleAndPostToDhis(String url) {
		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<PatientBundle> response = restTemplate.exchange(url, HttpMethod.GET, request, PatientBundle.class);
		PatientBundle patientBundle = response.getBody();
		
		patientCacheService.clear();
		fillInCachePatient(patientBundle);
		postPatientsToDhis();
		
		try {
			String nextPageUrl = getNextPageUrl(patientBundle);
			logger.info("Fetching Patient bundle from url :" + nextPageUrl);
			getPatientBundleAndPostToDhis(nextPageUrl);
		} catch (NoSuchElementException e) {
			logger.info("--- Imis Patients data export completed ---");
		}
		
	}

//	public void getClaimBundle(String url) {
//		RestTemplate restTemplate = new RestTemplate();
//
//		ResponseEntity<ClaimBundle> response = restTemplate.exchange(url, HttpMethod.GET, request, ClaimBundle.class);
//		ClaimBundle claimBundle = response.getBody();
//		fillInCacheClaim(claimBundle);
//		
//		try {
//			String nextPageUrl = getNextPageUrl(claimBundle);
//			getClaimBundle(nextPageUrl);
//		} catch (NoSuchElementException e) {
//			System.out.println("Claim data extraction finished!");
//		}
//
//	}
//
//	public void getClaimResponseBundle(String url) {
//		RestTemplate restTemplate = new RestTemplate();
//
//		ResponseEntity<ClaimResponseBundle> response = restTemplate.exchange(url, HttpMethod.GET, request, ClaimResponseBundle.class);
//		ClaimResponseBundle claimResponseBundle = response.getBody();
//		fillInCacheClaimResponse(claimResponseBundle);
//		
//		try {
//			String nextPageUrl = getNextPageUrl(claimResponseBundle);
//			getClaimResponseBundle(nextPageUrl);
//		} catch (NoSuchElementException e) {
//			System.out.println("Claim Response data extraction finished!");
//		}
//
//	}
	
	
	public void getClaimAndClaimResponseBundleAndPostToDhis(String claimUrl, String claimResponseUrl, Integer pageOffset) {
		RestTemplate restTemplate = new RestTemplate();
		for(int pageitr= pageOffset; pageitr<14000; pageitr++) {
			claimResponseCacheService.clear();
			claimCacheService.clear();
			try {
				String currentClaimUrl = claimUrl + "&page-offset=" + String.valueOf(pageitr);
				String currentClaimResponseUrl = claimResponseUrl + "&page-offset=" + String.valueOf(pageitr);
				
				logger.info("Fetching Claim and ClaimResponse bundle from url :" + currentClaimUrl);
				ResponseEntity<ClaimResponseBundle> crResponse = restTemplate.exchange(currentClaimResponseUrl, HttpMethod.GET, request, ClaimResponseBundle.class);
				ClaimResponseBundle claimResponseBundle = crResponse.getBody();
				fillInCacheClaimResponse(claimResponseBundle);
				
				ResponseEntity<ClaimBundle> response = restTemplate.exchange(currentClaimUrl, HttpMethod.GET, request, ClaimBundle.class);
				ClaimBundle claimBundle = response.getBody();
				fillInCacheClaim(claimBundle);
				
				postClaimsToDhis();
			} catch(Exception e) {
				logger.error(e.getMessage());
				logger.error("Claim/ClaimResponse fetch failed for page-offset=" + String.valueOf(pageitr));
			}
		}
	}
	
	public void getLocationBundle(String url) {
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<LocationBundle> response = restTemplate.exchange(url, HttpMethod.GET, request, LocationBundle.class);
		LocationBundle locationBundle = response.getBody();

		fillInCacheLocation(locationBundle);
		try {
			String nextPageUrl = getNextPageUrl(locationBundle);
			getLocationBundle(nextPageUrl);
		} catch (NoSuchElementException e) {
			logger.info("Location Response data extraction finished!");
		}

	}

	public void getPractitionerBundle(String url) {
		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<PractitionerBundle> response = restTemplate.exchange(url, HttpMethod.GET, request, PractitionerBundle.class);
		PractitionerBundle practitionerBundle = response.getBody();

		fillInCachePractitioner(practitionerBundle);

		try {
			String nextPageUrl = getNextPageUrl(practitionerBundle);
			getPractitionerBundle(nextPageUrl);
		} catch (NoSuchElementException e) {
			System.out.println("Practitioner Response data extraction finished!");
		}

	}


	
	private void fillInCachePatient(PatientBundle patientBundle) {
		List<PatientResource> patientResources = patientBundle.getEntry();
		
		List<Patient> patients = patientResources.stream().map(p -> p.getResource()).collect(Collectors.toList());
		
		patientCacheService.save(patients);
	}

	private void fillInCacheCoverage(CoverageBundle coverageBundle) {
		List<CoverageResource> coverageResources = coverageBundle.getEntry();

		List<Coverage> coverages = coverageResources.stream().map(p -> p.getResource()).collect(Collectors.toList());

		coverageCacheService.save(coverages);
	}

	private void fillInCacheClaim(ClaimBundle claimBundle) {
		List<ClaimResource> claimResources = claimBundle.getEntry();

		List<Claim> claims = claimResources.stream().map(p -> p.getResource()).collect(Collectors.toList());

		claimCacheService.save(claims);
	}

	private void fillInCacheClaimResponse(ClaimResponseBundle claimResponseBundle) {
		List<ClaimResponseResource> claimResponseResources = claimResponseBundle.getEntry();

		List<ClaimResponse> claimResponses = claimResponseResources.stream().map(p -> p.getResource()).collect(Collectors.toList());

		claimResponseCacheService.save(claimResponses);
	}

	private void fillInCacheLocation(LocationBundle locationBundle) {
		List<LocationResource> locationResources = locationBundle.getEntry();

		List<Location> locations = locationResources.stream().map(p -> p.getResource()).collect(Collectors.toList());

		locationCacheService.save(locations);
	}

	private void fillInCachePractitioner(PractitionerBundle practitionerBundle) {
		List<PractitionerResource> practitionerResources = practitionerBundle.getEntry();

		List<Practitioner> practitioners = practitionerResources.stream().map(p -> p.getResource()).collect(Collectors.toList());

		practitionerCacheService.save(practitioners);
	}
	
	
	private String getNextPageUrl(Bundle bundle) {
		List<LinkDetail> links = bundle.getLink();
		for(LinkDetail link : links) {
			if(link.getRelation().equals("next")) {
				return link.getUrl().replace("http:", "https:");
			}
		}
		throw new NoSuchElementException();
	}
	
	private void postPatientsToDhis() {
		Collection<Patient> patients = patientCacheService.getAllPatients();
		for(Patient patient : patients) {
			try {
				logger.info("InsureeFacade.adaptAndPost called for Patient: " + patient.getId());
				insureeFacade.adaptAndPost(patient);
				logger.info("InsureeFacade.adaptAndPost successfully finished Patient: " + patient.getId());
			} catch(Exception e) {
				logger.error("Error occured during adding data for Patient: '" + patient.getId() + "'. Reason: " + e.getMessage());
			}
		}
	}
	
	private void postCoverageToDhis() {
		Collection<Coverage> coverages = coverageCacheService.getAllCoverages();
		for(Coverage coverage : coverages) {
			try {
				logger.info("PolicyDetailsFacade.adaptAndPost called for Coverage: " + coverage.getIdentifier().get(0).getValue());
				policyDetailsFacade.adaptAndPost(coverage);
				logger.info("PolicyDetailsFacade.adaptAndPost successfully finished for Coverage: " + coverage.getIdentifier().get(0).getValue());
			} catch(Exception e) {
				logger.error("Error occured during adding data for Coverage: '" + coverage.getIdentifier().get(0).getValue() + "'. Reason: " + e.getMessage()); 
			}
		}
	}
	
	private void postClaimsToDhis() {
		Collection<ClaimResponse> crs = claimResponseCacheService.getAllClaimResponses();
		for(ClaimResponse cr: crs) {
			try {
				logger.info("ClaimFacade.adaptAndPost called for Claim: '" + cr.getId() + "'");
				claimFacade.adaptAndPost(cr);
				logger.info("ClaimFacade.adaptAndPost successfully finished for Claim: '" + cr.getId() + "'");
			} catch(Exception e) {
				logger.error("Error occured during adding data for Claim: '" + cr.getId() + "' . Reason: " + e.getMessage());
			}
		}
	}
}
