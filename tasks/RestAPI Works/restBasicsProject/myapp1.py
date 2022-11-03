import requests
import json
url = 'http://127.0.0.1:8000/example1/'


data = {
    'emp_name':'sai',
    'emp_age':25,
    'emp_number':12354,
    'emp_address':'masid'
    
}


v = json.dumps(data)
d = requests.post(url=url, data=v)
xsx= d.json()
print(xsx)      