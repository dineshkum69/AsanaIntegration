package com.java.integration.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;


@Service
public class WebhookService {

    private static final Logger logger = LoggerFactory.getLogger(WebhookService.class);

    @Value("asana.access.token")
    private String asanaAccessToken;

    public void handleNewAsanaTask(String taskDataJson) {
        try {
        	long taskId= 1235;
        	String taskName = "Sample Task";  
            String assignee = "John Doe";     
            String dueDate = "2023-08-31";
            String description = "Sample description"; 
            createRecordInAirtable(taskId,taskName, assignee, dueDate, description);
        } catch (Exception e) {
            logger.error("Error handling new Asana task: {}", e.getMessage(), e);
        }
    }

    private void createRecordInAirtable(long taskId,String taskName, String assignee, String dueDate, String description) {
    	
		String airtableApiKey = "patu3WG9VJnBqKROz.82e2482ea1e3e1df687e70dd3da8580563c010cc9b75b3cbb12248b93e82ce7b";
        try {
            String airtableBaseUrl = "https://api.airtable.com/v0/";
            String baseId = "appuxsZTpOdMihWSY";
            String tableIdOrName = "Asana%20Task"; 

            String airtableUrl = airtableBaseUrl + baseId + "/" + tableIdOrName;

            String jsonBody = "{\"fields\": {\"Task ID\": \""+taskId+"\",\"Name\": \"" + taskName + "\", \"Assignee\": \"" + assignee + "\", \"Due Date\": \"" + dueDate + "\", \"Description\": \"" + description + "\"}}";

            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + airtableApiKey);

            RequestEntity<String> requestEntity = new RequestEntity<>(jsonBody, headers, org.springframework.http.HttpMethod.POST, URI.create(airtableUrl));

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity, String.class);

            if (responseEntity.getStatusCode().is2xxSuccessful()) {

            	logger.info("Record created in Airtable successfully.");
            } else {
                logger.error("Failed to create record in Airtable. Response: {}", responseEntity.getBody());
            }
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode().value() == 429) {
                logger.error("Rate limit exceeded: {}", e.getMessage(), e);
            } else {
                logger.error("HTTP error while creating record in Airtable: {}", e.getMessage(), e);
            }
        } catch (Exception e) {
            logger.error("Error creating record in Airtable: {}", e.getMessage(), e);
    }
}
}