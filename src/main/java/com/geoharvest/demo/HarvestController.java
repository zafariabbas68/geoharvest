package com.geoharvest.demo;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import java.util.*;

@RestController
@RequestMapping("/api/harvest")
public class HarvestController {
    
    @Autowired
    private CswXmlParser cswParser;
    
    @Autowired
    private GeoServerService geoServerService;
    
    @Autowired
    private StacService stacService;
    
    @Autowired
    private InspireService inspireService;

    // ===== Core Endpoints =====
    
    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> health() {
        Map<String, String> status = new HashMap<>();
        status.put("status", "running");
        status.put("service", "GeoHarvest Platform");
        status.put("version", "2.0.0");
        status.put("timestamp", new Date().toString());
        return ResponseEntity.ok(status);
    }

    @GetMapping("/info")
    public ResponseEntity<Map<String, Object>> getInfo() {
        Map<String, Object> info = new HashMap<>();
        info.put("name", "GeoHarvest Platform");
        info.put("version", "2.0.0");
        info.put("description", "Universal Geospatial Metadata Harvester");
        info.put("standards", List.of("CSW 2.0.2", "DCAT", "STAC 1.0.0", "INSPIRE"));
        info.put("endpoints", List.of(
            "GET  /api/harvest/health",
            "GET  /api/harvest/info",
            "POST /api/harvest/harvest",
            "POST /api/harvest/records",
            "GET  /api/harvest/sources",
            "GET  /api/stac/catalog",
            "GET  /api/stac/items",
            "GET  /api/inspire/metadata"
        ));
        return ResponseEntity.ok(info);
    }

    @PostMapping("/harvest")
    public ResponseEntity<Map<String, Object>> harvest(@RequestBody Map<String, String> request) {
        String cswUrl = request.get("cswUrl");
        
        if (cswUrl == null || cswUrl.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "cswUrl is required"));
        }
        
        Map<String, Object> result = cswParser.fetchAndParseCsw(cswUrl, 10);
        result.put("status", result.containsKey("error") ? "error" : "success");
        result.put("cswUrl", cswUrl);
        result.put("harvester", "GeoHarvest v2.0");
        
        return ResponseEntity.ok(result);
    }
    
    @PostMapping("/records")
    public ResponseEntity<Map<String, Object>> getRecords(@RequestBody Map<String, Object> request) {
        String cswUrl = (String) request.get("cswUrl");
        int maxRecords = request.containsKey("maxRecords") ? (int) request.get("maxRecords") : 10;
        boolean includeStac = request.containsKey("includeStac") && (boolean) request.get("includeStac");
        
        Map<String, Object> result = cswParser.fetchAndParseCsw(cswUrl, maxRecords);
        
        if (includeStac && result.containsKey("records")) {
            @SuppressWarnings("unchecked")
            List<Map<String, String>> records = (List<Map<String, String>>) result.get("records");
            List<Map<String, Object>> stacItems = stacService.createStacItems(records);
            result.put("stac_items", stacItems);
        }
        
        result.put("status", result.containsKey("error") ? "error" : "success");
        result.put("cswUrl", cswUrl);
        
        return ResponseEntity.ok(result);
    }
    
    @GetMapping("/sources")
    public ResponseEntity<Map<String, Object>> getSources() {
        Map<String, Object> sources = new HashMap<>();
        sources.put("status", "success");
        
        List<Map<String, String>> sourceList = new ArrayList<>();
        
        Map<String, String> norway = new HashMap<>();
        norway.put("name", "Norway GeoNetwork");
        norway.put("url", "https://www.geonorge.no/geonetwork/srv/eng/csw");
        norway.put("type", "GeoNetwork CSW");
        norway.put("country", "Norway");
        norway.put("records", "9,269");
        sourceList.add(norway);
        
        Map<String, String> netherlands = new HashMap<>();
        netherlands.put("name", "Netherlands Nationaal GeoRegister");
        netherlands.put("url", "https://nationaalgeoregister.nl/geonetwork/srv/dut/csw");
        netherlands.put("type", "GeoNetwork CSW");
        netherlands.put("country", "Netherlands");
        netherlands.put("records", "Available");
        sourceList.add(netherlands);
        
        Map<String, String> france = new HashMap<>();
        france.put("name", "France Data.gouv");
        france.put("url", "https://csw.data.gouv.fr/geonetwork/srv/eng/csw");
        france.put("type", "GeoNetwork CSW");
        france.put("country", "France");
        france.put("records", "Available");
        sourceList.add(france);
        
        sources.put("sources", sourceList);
        return ResponseEntity.ok(sources);
    }
    
    // ===== STAC Endpoints =====
    
    @GetMapping("/stac/catalog")
    public ResponseEntity<Map<String, Object>> getStacCatalog() {
        return ResponseEntity.ok(stacService.generateCatalog());
    }
    
    @GetMapping("/stac/items")
    public ResponseEntity<List<Map<String, Object>>> getStacItems(
            @RequestParam(defaultValue = "10") int limit,
            @RequestParam(defaultValue = "https://www.geonorge.no/geonetwork/srv/eng/csw") String source) {
        
        Map<String, Object> result = cswParser.fetchAndParseCsw(source, limit);
        @SuppressWarnings("unchecked")
        List<Map<String, String>> records = (List<Map<String, String>>) result.getOrDefault("records", new ArrayList<>());
        
        return ResponseEntity.ok(stacService.createStacItems(records));
    }
    
    // ===== INSPIRE Endpoints =====
    
    @GetMapping("/inspire/metadata")
    public ResponseEntity<Map<String, Object>> getInspireMetadata() {
        return ResponseEntity.ok(inspireService.getInspireMetadata());
    }
}
