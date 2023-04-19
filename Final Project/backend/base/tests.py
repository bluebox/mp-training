from django.urls import reverse
from rest_framework import status
from rest_framework.test import APITestCase
from django.contrib.auth.models import User
from .models import Product

class AccountTestCase(APITestCase):
    def test_register_user(self):
        """
        Ensure we can create a new account object.
        """
        url = reverse('register_user')
        data = {
            'username': 'user',
            'email': 'dev@gmail.com',
            'first_name': 'Alex,',
            'last_name': 'daderio',
            'password': 'Alex123'
        }
        response = self.client.post(url, data, format='json')
        self.assertEqual(response.status_code, status.HTTP_201_CREATED)

class LoginTestCase(APITestCase):
    def setUp(self):
        self.user = User.objects.create_user(username="example", password="pass123")

    def test_login(self):
        url = reverse('token_obtain')
        data = {
            "username": "example",
            "password": "pass123"
        }
        response = self.client.post(url, data, format='json')
        self.assertEqual(response.status_code, status.HTTP_200_OK)

class ProductTestCase(APITestCase):
    def setUp(self):
        self.user = User.objects.create_user(username="example", password="pass123")
        self.product = Product.objects.create(name='example_product', price=35, user=self.user)
        self.admin = User.objects.create_superuser(username='admin_user', password='Admin@123')


    def test_delete_product(self):
        url = reverse('product_delete', args=[self.product.id])
        response = self.client.delete(url, {}, format='json')
        self.assertEqual(response.status_code, status.HTTP_401_UNAUTHORIZED)

    def test_delete_product_authenticaated(self):
        self.client.force_authenticate(user=self.user)
        url = reverse('product_delete', args=[self.product.id])
        response = self.client.delete(url, {}, format='json')
        self.assertEqual(response.status_code, status.HTTP_403_FORBIDDEN)

    def test_delete_product_admin(self):
        self.client.force_authenticate(user=self.admin)
        url = reverse('product_delete', args=[self.product.id])
        response = self.client.delete(url, {}, format='json')
        self.assertEqual(response.status_code, status.HTTP_204_NO_CONTENT)

    def test_get_products(self):
        url = reverse('products')
        response = self.client.get(url, format='json')
        self.assertEqual(response.status_code, status.HTTP_200_OK)

    def test_get_product_detail(self):
        url = reverse('product-details', args=[self.product.id])
        response = self.client.get(url, format='json')
        self.assertEqual(response.status_code, status.HTTP_200_OK)

    def test_update_product(self):
        url = reverse('update_product', args=[self.product.id])
        data = {
            'name': 'example',
            'brand': 'example',
            'price': 1,
            'countInStock': 1,
            'description': 'example',
            'category': 'example'
        }
        response = self.client.put(url, data, format='json')
        self.assertEqual(response.status_code, status.HTTP_401_UNAUTHORIZED)

    def test_update_product_authenticate(self):
        self.client.force_authenticate(user=self.user)
        url = reverse('update_product', args=[self.product.id])
        data = {
            'name': 'example',
            'brand': 'example',
            'price': 1,
            'countInStock': 1,
            'description': 'example',
            'category': 'example'
        }
        response = self.client.put(url, data, format='json')
        self.assertEqual(response.status_code, status.HTTP_403_FORBIDDEN)

    def test_update_product_admin(self):
        self.admin = User.objects.create_superuser(username='admin', password='Pass@123')
        self.client.force_authenticate(user=self.admin)
        url = reverse('update_product', args=[self.product.id])
        data = {
            'name': 'example',
            'brand': 'example',
            'price': 1,
            'countInStock': 1,
            'description': 'example',
            'category': 'example'
        }
        response = self.client.put(url, data, format='json')
        self.assertEqual(response.status_code, status.HTTP_200_OK)

    def test_create_product(self):
        url = reverse('create_product')
        data = {
            'user': self.user.id,
            'name': 'example',
            'brand': 'example',
            'price': 1,
            'countInStock': 1,
            'description': 'example',
            'category': 'example'
        }
        response = self.client.post(url, data, format='json')
        self.assertEqual(response.status_code, status.HTTP_401_UNAUTHORIZED)

    def test_create_product_authenticate(self):
        user = User.objects.create_user(username='user', password='pass@123')
        self.client.force_authenticate(user=user)
        url = reverse('create_product')
        data = {
            'user': self.user.id,
            'name': 'example',
            'brand': 'example',
            'price': 1,
            'countInStock': 1,
            'description': 'example',
            'category': 'example'
        }
        response = self.client.post(url, data, format='json')
        self.assertEqual(response.status_code, status.HTTP_403_FORBIDDEN)

    def test_create_product_admin(self):
        self.client.force_authenticate(user=self.admin)
        url = reverse('create_product')
        data = {
            'user': self.user.id,
            'name': 'example',
            'brand': 'example',
            'price': 1,
            'countInStock': 1,
            'description': 'example',
            'category': 'example'
        }
        response = self.client.post(url, data, format='json')
        self.assertEqual(response.status_code, status.HTTP_200_OK)





