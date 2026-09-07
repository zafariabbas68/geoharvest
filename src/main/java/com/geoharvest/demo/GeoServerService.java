package com.geoharvest.demo;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;
import java.util.*;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Service
public class GeoServerService {
    
    private final RestTemplate restTemplate = new RestTemplate();
    private String geoserverUrl = "http://localhost:8082/geoserver";
    private String username = "admin";
    private String password = "admin123";
    
    private HttpHeaders createAuthHeaders() {
        HttpHeaders headers = new HttpHeaders();
        String auth = username + ":" + password;
        byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes(StandardCharsets.US_ASCII));
        String authHeader = "Basic " + new String(encodedAuth);
        headers.set("Authorization", authHeader);
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
    
    public Map<String, Object> healthCheck() {
        Map<String, Object> result = new HashMap<>();
        try {
            String url = geoserverUrl + "/rest/about/version.json";
            HttpHeaders headers = createAuthHeaders();
            HttpEntity<String> entity = new HttpEntity<>(headers);
            ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.GET, entity, Map.class);
            result.put("status", "connected");
            result.put("version", response.getBody());
        } catch (Exception e) {
            result.put("status", "disconnected");
            result.put("error", e.getMessage());
        }
        return result;
    }
    
    public Map<String, Object> createWorkspace(String workspaceName) {
        Map<String, Object> result = new HashMap<>();
        try {
            String url = geoserverUrl + "/rest/workspaces";
            HttpHeaders headers = createAuthHeaders();
            
            Map<String, Object> body = new HashMap<>();
            Map<String, String> workspace = new HashMap<>();
            workspace.put("name", workspaceName);
            body.put("workspace", workspace);
            
            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);
            restTemplate.postForEntity(url, entity, String.class);
            result.put("status", "created");
            result.put("workspace", workspaceName);
        } catch (Exception e) {
            result.put("error", e.getMessage());
        }
        return result;
    }
}
