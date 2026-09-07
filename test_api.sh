#!/bin/bash
echo "========================================"
echo "Testing GeoCat DCAT Harvester API"
echo "========================================"
echo ""

echo "1. Health Check:"
curl -s http://localhost:8080/api/dcat/health
echo -e "\n"

echo "2. Info Endpoint:"
curl -s http://localhost:8080/api/dcat/info
echo -e "\n"

echo "3. Harvest Endpoint:"
curl -s -X POST http://localhost:8080/api/dcat/harvest \
  -H "Content-Type: application/json" \
  -d '{"cswUrl":"https://demo.geonetwork.org/srv/eng/csw"}'
echo -e "\n"

echo "4. Records Endpoint:"
curl -s -X POST http://localhost:8080/api/dcat/records \
  -H "Content-Type: application/json" \
  -d '{"cswUrl":"https://demo.geonetwork.org/srv/eng/csw", "maxRecords":3}'
echo -e "\n"

echo "========================================"
echo "Test Complete"
echo "========================================"
