from http.client import responses

import requests
class TestApi:
    def setup_method(self):
        self.url = "http://127.0.0.1:8000/test/hello/"
        self.response = requests.get(self.url)
    def test_api(self):
        print(self.response.json())
        assert self.response.status_code == 200
    def test_msg(self):
        assert "message" in self.response.json()
    def test_params(self):
        params = {
            'Name':'Madhav',
            'Age':'21',
            'City':'Hyderabad'
        }
        self.response = requests.get(self.url,params=params)
        print(self.response.json())
        json_response = self.response.json()
        dict_response = json_response.get('message')
        for i in dict_response:
            dict_response[i] = dict_response[i][0]
        assert dict_response == params