package com.geoharvest.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GeoHarvestApplication {
    public static void main(String[] args) {
        SpringApplication.run(GeoHarvestApplication.class, args);
        System.out.println("🌍 GeoHarvest Platform is running on http://localhost:8080");
        System.out.println("📊 Universal Geospatial Metadata Harvester");
    }
}
