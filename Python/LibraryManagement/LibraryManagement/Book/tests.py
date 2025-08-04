from django.test import TestCase
from django.urls import reverse
from rest_framework.test import APIClient
from rest_framework.authtoken.models import Token
from django.contrib.auth.models import User

from Member.models import Member


class TestBookWithAuth(TestCase):
    def setUp(self):
        self.user = Member.objects.create_user(email='test@gmail.com', password='123123')
        self.token = Token.objects.create(user=self.user)
        self.client = APIClient()
        self.client.credentials(HTTP_AUTHORIZATION='Bearer ' + self.token.key)
        self.url = reverse('books-list')
    def test_crud(self):
        data={
            "title":"bgmii",
            "author": "pubg",
            "category":"Guns"
        }
        resposne=self.client.post(self.url,data)
        self.assertEqual(resposne.status_code,201)