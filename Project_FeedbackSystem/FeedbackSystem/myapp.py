import requests
import json

URL = "http://127.0.0.1:8000/Faculty/faculty-serializer/"

data = {
    "first_name":"venky",
    "last_name":"bbb",
    "facul_username":"hsh",
    "password":"shjuhsku",
    "gender" : "hsh",
    "date_of_birth" : "2022-06-06",
    "main_subject" : "hsh",
    "department" : "hsh",
    "joining_date" : "2022-06-06",
    "qualification" : "hsh",
    "experience" : 2,
    "user_type" : "hsh",
}

json_data = json.dumps(data)
req = requests.post(url = URL, data = json_data)
r = req.json()
print(data)
