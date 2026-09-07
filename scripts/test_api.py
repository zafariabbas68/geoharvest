import requests
import json

# Your service URL
BASE_URL = "http://localhost:8080/api/dcat"

def test_health():
    response = requests.get(f"{BASE_URL}/health")
    print("Health Check:", response.json())

def test_harvest():
    payload = {
        "cswUrl": "https://demo.geonetwork.org/srv/eng/csw"
    }
    response = requests.post(f"{BASE_URL}/harvest", json=payload)
    print("Harvest Response:", json.dumps(response.json(), indent=2))

if __name__ == "__main__":
    print("🧪 Testing GeoCat DCAT Harvester\n")
    test_health()
    print("\n" + "="*50 + "\n")
    test_harvest()