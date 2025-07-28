# utils.py
import requests
from django.core.cache import cache

COUNTRY_API = "https://api.countrystatecity.in/v1/countries"
API_KEY = "settings.SECRET_KEY"  # Replace securely via environment or secrets manager
HEADERS = {"X-CSCAPI-KEY": API_KEY}

class ExternalGeoService:

    @staticmethod
    def fetch_countries():
        cached = cache.get("country_list")
        if cached:
            return cached

        response = requests.get(COUNTRY_API, headers=HEADERS)
        if response.status_code == 200:
            countries = response.json()
            cache.set("country_list", countries, 86400)  # cache for 1 day
            return countries
        raise ValueError("Unable to fetch countries")

    @staticmethod
    def fetch_states(country_code):
        key = f"states_{country_code}"
        cached = cache.get(key)
        if cached:
            return cached

        url = f"{COUNTRY_API}/{country_code}/states"
        response = requests.get(url, headers=HEADERS)
        if response.status_code == 200:
            states = response.json()
            cache.set(key, states, 86400)
            return states
        raise ValueError("Unable to fetch states")

    @staticmethod
    def fetch_cities(country_code, state_code):
        key = f"cities_{country_code}_{state_code}"
        cached = cache.get(key)
        if cached:
            return cached

        url = f"{COUNTRY_API}/{country_code}/states/{state_code}/cities"
        response = requests.get(url, headers=HEADERS)
        if response.status_code == 200:
            cities = response.json()
            cache.set(key, cities, 86400)
            return cities
        raise ValueError("Unable to fetch cities")
