#!/bin/bash
echo "=== GeoCat DCAT Harvester Demo ==="
echo ""
echo "1. Health Check:"
curl -s http://localhost:8080/api/dcat/health | python3 -m json.tool
echo ""
echo "2. Harvest from GeoNetwork:"
curl -s -X POST http://localhost:8080/api/dcat/harvest \
  -H "Content-Type: application/json" \
  -d '{"cswUrl":"https://demo.geonetwork.org/srv/eng/csw"}' | python3 -m json.tool
echo ""
echo "✅ Demo complete!"
