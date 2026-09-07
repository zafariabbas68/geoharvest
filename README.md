

## 📝 **Professional README.md for Your Repository**


# 🌍 GeoHarvest Platform

[![Java](https://img.shields.io/badge/Java-17-blue.svg)](https://adoptium.net/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.1.5-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![STAC](https://img.shields.io/badge/STAC-1.0.0-purple.svg)](https://stacspec.org/)
[![INSPIRE](https://img.shields.io/badge/INSPIRE-Compliant-orange.svg)](https://inspire.ec.europa.eu/)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](LICENSE)
[![GitHub stars](https://img.shields.io/github/stars/zafariabbas68/geoharvest.svg)](https://github.com/zafariabbas68/geoharvest/stargazers)

## Universal Geospatial Metadata Harvester

**GeoHarvest** is a production-ready platform that harvests geospatial metadata from CSW (Catalogue Service for the Web) endpoints, converts them to DCAT format, and provides STAC (SpatioTemporal Asset Catalog) and INSPIRE-compliant outputs. Built with Spring Boot 3.1.5 and Java 17, it serves as a modern bridge between legacy geospatial catalogs and next-generation data discovery systems.

---

## ✨ Features

| Feature | Description |
|---------|-------------|
| **CSW to DCAT** | Harvest metadata from any CSW 2.0.2 endpoint and convert to DCAT |
| **STAC Support** | Generate STAC 1.0.0 catalogs and items from harvested metadata |
| **INSPIRE Compliance** | EU spatial data infrastructure standard ready |
| **REST API** | 9 production endpoints for programmatic access |
| **Professional Web UI** | Clean, bright, accessible dashboard with live stats |
| **Multi-Source** | Norway, Netherlands, France, and custom CSW endpoints |
| **Docker Ready** | Containerized for easy deployment |
| **GeoServer Integration** | Optional publish to GeoServer workspaces |
| **Swagger/OpenAPI** | Interactive API documentation |

---

## 📡 API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/api/harvest/health` | Service health check |
| `GET` | `/api/harvest/info` | Service information |
| `POST` | `/api/harvest/harvest` | Harvest metadata from CSW |
| `POST` | `/api/harvest/records` | Get DCAT records (with STAC toggle) |
| `GET` | `/api/harvest/sources` | List available data sources |
| `GET` | `/api/harvest/stac/catalog` | Get STAC catalog |
| `GET` | `/api/harvest/stac/items` | Get STAC items |
| `GET` | `/api/harvest/inspire/metadata` | Get INSPIRE metadata |
| `POST` | `/api/harvest/publish` | Publish to GeoServer |

---

## 🚀 Quick Start

### Prerequisites
- Java 17
- Maven 3.8+
- Docker (optional)

### Run Locally

```bash
# Clone the repository
git clone https://github.com/zafariabbas68/geoharvest.git
cd geoharvest

# Build and run
mvn clean compile
mvn spring-boot:run

# Access the application
open http://localhost:8080/index.html
```

### Run with Docker

```bash
# Build the image
docker build -t geoharvest .

# Run the container
docker run -d -p 8080:8080 --name geoharvest geoharvest
```

---

## 🖥️ Web UI

The platform includes a professional web dashboard:

![Web UI](https://via.placeholder.com/800x400?text=GeoHarvest+Platform+UI)

**Features:**
- Live service status
- Record count and response time
- Quick presets (Norway, Netherlands, France)
- Harvest and record fetching
- STAC and INSPIRE integration
- Toast notifications and loading states

---

## 📊 Tested Data Sources

| Country | Endpoint | Status | Records |
|---------|----------|--------|---------|
| 🇳🇴 Norway | `https://www.geonorge.no/geonetwork/srv/eng/csw` | ✅ Working | 9,269 |
| 🇳🇱 Netherlands | `https://nationaalgeoregister.nl/geonetwork/srv/dut/csw` | ✅ Working | Available |
| 🇫🇷 France | `https://csw.data.gouv.fr/geonetwork/srv/eng/csw` | ⚠️ DNS Issue | N/A |

---

## 🛠️ Technology Stack

| Technology | Purpose |
|------------|---------|
| **Java 17** | Core programming language |
| **Spring Boot 3.1.5** | Application framework |
| **Maven** | Build automation |
| **Docker** | Containerization |
| **Thymeleaf** | Server-side rendering (optional) |
| **Swagger/OpenAPI** | API documentation |
| **HTML/CSS/JS** | Professional web UI |
| **JAXP** | XML parsing for CSW responses |

---

## 📁 Project Structure

```
geoharvest/
├── src/main/java/com/geoharvest/demo/
│   ├── GeoHarvestApplication.java   # Main entry point
│   ├── HarvestController.java       # REST API endpoints
│   ├── CswXmlParser.java           # CSW XML parser
│   ├── GeoServerService.java       # GeoServer integration
│   ├── StacService.java            # STAC catalog support
│   └── InspireService.java         # INSPIRE compliance
├── src/main/resources/
│   ├── application.properties      # Configuration
│   └── static/
│       └── index.html              # Web dashboard
├── Dockerfile                      # Container definition
├── pom.xml                         # Maven dependencies
└── README.md                       # This file
```

---

## 🧪 Testing

### Health Check
```bash
curl http://localhost:8080/api/harvest/health
```

### Harvest Records
```bash
curl -X POST http://localhost:8080/api/harvest/records \
  -H "Content-Type: application/json" \
  -d '{"cswUrl":"https://www.geonorge.no/geonetwork/srv/eng/csw?service=CSW&version=2.0.2&request=GetRecords&typeNames=gmd:MD_Metadata&maxRecords=5"}'
```

### Get STAC Catalog
```bash
curl http://localhost:8080/api/harvest/stac/catalog
```

### Get INSPIRE Metadata
```bash
curl http://localhost:8080/api/harvest/inspire/metadata
```

### Get Available Sources
```bash
curl http://localhost:8080/api/harvest/sources
```

---

## 📸 Sample Response

### Harvest Response
```json
{
  "status": "success",
  "recordsFound": 10,
  "cswUrl": "https://www.geonorge.no/geonetwork/srv/eng/csw",
  "records": [
    {
      "title": "Historiske kart for Bergen Kommune",
      "abstract": "No abstract available",
      "type": "dataset",
      "date": "2025-05-05",
      "id": "c681649b-d61e-4aee-82f1-e7f0c50eaf79"
    }
  ]
}
```

---

## 🎯 Future Enhancements

- [ ] Real-time IoT data streaming
- [ ] AI-powered metadata enrichment
- [ ] OAuth2 security
- [ ] Kubernetes deployment
- [ ] Digital twin integration
- [ ] Full GeoServer automation

---

## 🤝 Contributing

Contributions are welcome! Please:
1. Fork the repository
2. Create a feature branch
3. Submit a pull request

---

## 📄 License

This project is licensed under the Apache License 2.0 - see the [LICENSE](LICENSE) file for details.

---

## 📧 Contact

**Author:** Ghulam Abbas Zafari  
**Role:** Geoinformatics Engineer, Politecnico di Milano  
**Email:** ghalumabbas.zafari@gmail.com  
**LinkedIn:** [linkedin.com/in/ghalum-abbass-zafari](https://linkedin.com/in/ghalum-abbass-zafari)  

---

## 🌟 Acknowledgments

- GeoCat BV for inspiration and guidance
- Spring Boot community for excellent framework
- OSGeo for open geospatial standards

---

**Built with ❤️ for the geospatial community. Designed for interoperability.**
EOF
```

---

## 🚀 **Push the Updated README**

```bash
git add README.md
git commit -m "Add comprehensive README with badges, features, API docs, and usage examples"
git push
```

---

## ✅ **Your Repository is Now Ready!**

Visit: https://github.com/zafariabbas68/geoharvest

**You can now share this link with:**  
- 🌍 **GeoCat BV** (Jeroen Ticheler)  
- 💼 **Potential employers**  
- 👥 **The geospatial community**  

