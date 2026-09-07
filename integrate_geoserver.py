import requests
import json

# Your DCAT Harvester
DCAT_URL = "http://localhost:8080/api/dcat"

# Your GeoServer (adjust port if different)
GEOSERVER_URL = "http://localhost:8081/geoserver/rest"
AUTH = ("admin", "geoserver")  # default credentials

def test_dcat_harvester():
    """Test DCAT Harvester endpoints"""
    print("Testing DCAT Harvester...")
    
    # Health check
    health = requests.get(f"{DCAT_URL}/health")
    print(f"Harvester Status: {health.json()}")
    
    # Harvest metadata
    harvest = requests.post(f"{DCAT_URL}/harvest", 
                           json={"cswUrl": "https://demo.geonetwork.org/srv/eng/csw"})
    print(f"Harvest Result: {harvest.json()}")
    
    return harvest.json()

def check_geoserver():
    """Check if GeoServer is running"""
    try:
        resp = requests.get(f"{GEOSERVER_URL}/about/version", auth=AUTH)
        print(f"GeoServer Version: {resp.status_code}")
        return True
    except:
        print("GeoServer not reachable on port 8081")
        return False

if __name__ == "__main__":
    print("="*50)
    print("GeoCat Integration Demo")
    print("="*50)
    
    test_dcat_harvester()
    print("\n" + "="*50)
    check_geoserver()
