import requests
import json
url = 'http://127.0.0.1:8000/example2/'



def get_data(id = None):
    data={}
    if id is not None:
        data={'id':id}
    json_data = json.dumps(data)
    print(type(json_data))  # str
    print(type(data))  #dict
    r = requests.get(url=url, data=json_data)
    print(type(r))
    data=  r.json()
    print(data)
    print(type(data))  # dict


# get_data(5)


def post_data():
    data = {
    'emp_name':'mahesh',
    'emp_age':27,
    'emp_number':42354,
    'emp_address':'vizg'
    
     }


    json_data = json.dumps(data)
    r = requests.post(url=url, data=json_data)
    data=  r.json()
    print(data)


# post_data()




def update_data():
    data = {
        'id':'5',
        "emp_name": "sai kumar",
        "emp_age": 12,
        "emp_number": 7878,
        "emp_address": "wwfdw"
     }


    json_data = json.dumps(data)
    r = requests.put(url=url, data=json_data)
    data=  r.json()
    print(data)


update_data()