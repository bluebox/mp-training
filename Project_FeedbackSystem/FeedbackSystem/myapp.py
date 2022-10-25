import requests
import json

URL = "http://127.0.0.1:8000/Student/create-student"

data = {
    "first_name": "John",
    "last_name": "Andreson",
    "stud_username": "john@gmail.com",
    "password": "azsxdcfv",
    "gender": "Male",
    "date_of_birth": "2022-06-06",
    "father_name": "Big Venky",
    "roll_no": 102,
    "class_code": "MM1004",
    "user_type": "student"
}

json_data = json.dumps(data)

req = requests.post(url = URL, data = json_data)
r = str(req.json())
# print(req)
