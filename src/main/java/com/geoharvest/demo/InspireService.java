package com.geoharvest.demo;

import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.*;

@Service
public class InspireService {
    
    public Map<String, Object> enrichWithInspire(Map<String, Object> record) {
        Map<String, Object> inspire = new HashMap<>();
        inspire.put("inspire_conformant", true);
        inspire.put("inspire_metadata_date", Instant.now().toString());
        inspire.put("inspire_spatial_data_service_type", "discovery");
        inspire.put("inspire_keywords", List.of("INSPIRE", "geospatial", "metadata"));
        inspire.put("inspire_theme", List.of("Land use", "Environmental monitoring", "Transport"));
        return inspire;
    }
    
    public Map<String, Object> getInspireMetadata() {
        Map<String, Object> metadata = new HashMap<>();
        metadata.put("conformant", true);
        metadata.put("directive", "2007/2/EC");
        metadata.put("languages", List.of("en", "fr", "de", "it"));
        metadata.put("metadata_standard", "ISO 19115");
        metadata.put("metadata_version", "1.2");
        return metadata;
    }
}
