package com.geoharvest.demo;

import org.springframework.stereotype.Service;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class StacService {
    
    private final List<Map<String, Object>> stacItems = new ArrayList<>();
    
    public Map<String, Object> generateCatalog() {
        Map<String, Object> catalog = new HashMap<>();
        catalog.put("stac_version", "1.0.0");
        catalog.put("id", "geoharvest-catalog");
        catalog.put("type", "Catalog");
        catalog.put("title", "🌍 GeoHarvest STAC Catalog");
        catalog.put("description", "Auto-generated STAC catalog from harvested CSW metadata");
        
        Map<String, Object> links = new HashMap<>();
        links.put("self", "/api/stac/catalog");
        links.put("root", "/api/stac/catalog");
        catalog.put("links", links);
        
        return catalog;
    }
    
    public Map<String, Object> createStacItem(Map<String, String> record) {
        Map<String, Object> item = new HashMap<>();
        item.put("stac_version", "1.0.0");
        item.put("type", "Feature");
        item.put("id", record.get("id"));
        
        // Properties
        Map<String, Object> properties = new HashMap<>();
        properties.put("title", record.get("title"));
        properties.put("description", record.get("abstract"));
        properties.put("datetime", record.getOrDefault("date", Instant.now().toString()));
        properties.put("type", record.get("type"));
        properties.put("created", Instant.now().toString());
        item.put("properties", properties);
        
        // Geometry (placeholder - would need bounding box from metadata)
        Map<String, Object> geometry = new HashMap<>();
        geometry.put("type", "Point");
        geometry.put("coordinates", new double[]{0.0, 0.0});
        item.put("geometry", geometry);
        item.put("bbox", new double[]{-180.0, -90.0, 180.0, 90.0});
        
        // Links
        Map<String, Object> links = new HashMap<>();
        links.put("self", "/api/stac/item/" + record.get("id"));
        links.put("parent", "/api/stac/catalog");
        links.put("collection", "/api/stac/collection");
        item.put("links", links);
        
        return item;
    }
    
    public List<Map<String, Object>> createStacItems(List<Map<String, String>> records) {
        List<Map<String, Object>> items = new ArrayList<>();
        for (Map<String, String> record : records) {
            items.add(createStacItem(record));
        }
        return items;
    }
}
