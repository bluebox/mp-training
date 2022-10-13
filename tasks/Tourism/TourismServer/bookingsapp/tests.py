from django.test import TestCase

from bookingsapp.models import User

# Create your tests here.

class UserTestCase(TestCase):
    def setUp(self):
        return User.objects.create(name = 'test',
                        email = 'test@gmail.com',
                        password = 'test',
                        mobile = 1202938389)
