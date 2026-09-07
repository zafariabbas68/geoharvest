#!/bin/bash
echo "╔══════════════════════════════════════════════════════════════╗"
echo "║     🌍 GeoCat DCAT Harvester - Production Ready Demo         ║"
echo "║     Real CSW XML Parser | DCAT Compatible | Spring Boot 3    ║"
echo "╚══════════════════════════════════════════════════════════════╝"
echo ""

echo "📡 Testing Real CSW Harvesting from GeoNetwork..."
echo ""

echo "1️⃣  Service Information:"
curl -s http://localhost:8080/api/dcat/info | python3 -m json.tool
echo ""

echo "2️⃣  Health Check:"
curl -s http://localhost:8080/api/dcat/health | python3 -m json.tool
echo ""

echo "3️⃣  Harvest Real CSW Metadata:"
curl -s -X POST http://localhost:8080/api/dcat/harvest \
  -H "Content-Type: application/json" \
  -d '{"cswUrl":"https://demo.geonetwork.org/srv/eng/csw"}' | python3 -m json.tool
echo ""

echo "4️⃣  Get DCAT-Compatible Records:"
curl -s -X POST http://localhost:8080/api/dcat/records \
  -H "Content-Type: application/json" \
  -d '{"cswUrl":"https://demo.geonetwork.org/srv/eng/csw", "maxRecords":3}' | python3 -m json.tool
echo ""

echo "✅ Demo Complete - Service Ready for Jeroen Ticheler (GeoCat)"
echo ""
echo "📦 Next Steps:"
echo "   • Package as Docker container"
echo "   • Deploy to cloud (AWS/GCP)"
echo "   • Integrate with GeoCat Bridge"
