import requests
import json
url = 'http://127.0.0.1:8081/example/5'
x= requests.get(url=url)
data = x.json()
print(data) 
print(type(data))     

x = json.dumps(data)
print(type(x))