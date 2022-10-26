from django.test import TestCase
from rest_framework.test import APITestCase
from django.urls import reverse
from bookingsapp.models import User

# Create your tests here.

class TestSetUP(APITestCase):

    def setUp(self):
        self.register_url = reverse('userDetails')
        self.login_url = reverse('login')
        self.user_data = {
            'name' : 'test',
            'email' : 'test@gmail.com',
            'password' : 'test',
            'mobile' : 1202938389
        }
        return super().setUp()

    def tearDown(self):
        return super().tearDown()
