from django.urls import reverse
from rest_framework import status
from rest_framework.test import APITestCase
from .models import Order, ShippingAddress
from django.contrib.auth.models import User
from base.models import Product

class ShippingAddressTestCase(APITestCase):

    def setUp(self):
        self.user = User.objects.create_user(username='username', password='pass@123')

    def test_save_shipping_address(self):
        url = reverse('saveShippingAddress')
        data = {
            'locality': 'example',
            'city': 'example',
            'pincode': '123123',
            'state': 'example'
        }
        response = self.client.post(url, data, format='json')
        self.assertEqual(response.status_code, status.HTTP_401_UNAUTHORIZED)

    def test_save_shipping_address_authenticated(self):
        self.client.force_authenticate(user=self.user)
        url = reverse('saveShippingAddress')
        data = {
            'locality': 'example',
            'city': 'example',
            'pincode': '123123',
            'state': 'example'
        }
        response = self.client.post(url, data, format='json')
        self.assertEqual(response.status_code, status.HTTP_200_OK)


class OrderTestCase(APITestCase):

    def setUp(self):
        self.user = User.objects.create_user(username='username', password='pass@123')
        self.product = Product.objects.create(name='product', price=100, countInStock=10)
        self.shippingAddress = ShippingAddress.objects.create(locality='example', city='example',
                                                              postalCode='123123', state='exaple', user=self.user)

    def test_add_order_items(self):
        url = reverse('add_order_items')
        data = {
            'shippingAddress': self.shippingAddress.id,
            'user': self.user.id,
            'paymentMethod': 'example_method',
            'taxPrice': 12.12,
            'shippingPrice': 12.12,
            'totalPrice': 24.24,
            'orderItems': {
                'product': {
                    'id': self.product.id
                },
                'qty': 1
            }
        }
        response = self.client.post(url, data, format='json')
        self.assertEqual(response.status_code, status.HTTP_401_UNAUTHORIZED)

    def test_add_order_items_authenticate(self):
        self.client.force_authenticate(user=self.user)
        data = {
            'shippingAddress': self.shippingAddress.id,
            'user': self.user.id,
            'paymentMethod': 'example_method',
            'taxPrice': 12.12,
            'shippingPrice': 12.12,
            'totalPrice': 24.24,
            'orderItems': [
                {
                    'product': {
                        'id': self.product.id
                    },
                    'qty': 1,
                }
            ]
        }
        url = reverse('add_order_items')
        response = self.client.post(url, data, format='json')
        self.assertEqual(response.status_code, status.HTTP_200_OK)

    def test_get_my_orders(self):
        url = reverse('my_orders')
        response = self.client.get(url, format='json')
        self.assertEqual(response.status_code, status.HTTP_401_UNAUTHORIZED)

    def test_get_my_orders_authenticated(self):
        self.client.force_authenticate(user=self.user)
        url = reverse('my_orders')
        response = self.client.get(url, format='json')
        self.assertEqual(response.status_code, status.HTTP_200_OK)


class OrderAdminTestCase(APITestCase):

    def setUp(self):
        self.admin = User.objects.create_superuser(username='admin_user', password='pass@123')

    def test_get_orders(self):
        url = reverse('orders')
        response = self.client.get(url, format='json')
        self.assertEqual(response.status_code, status.HTTP_401_UNAUTHORIZED)

    def test_get_orders_admin(self):
        self.client.force_authenticate(user=self.admin)
        url = reverse('orders')
        response = self.client.get(url, format='json')
        self.assertEqual(response.status_code, status.HTTP_200_OK)


class OrderDetailTestCase(APITestCase):

    def setUp(self):
        self.user = User.objects.create_user(username='user', password='pass123')
        self.shippingAddress = ShippingAddress.objects.create(user=self.user)
        self.order = Order.objects.create(user=self.user, shippingAddress=self.shippingAddress)
        self.admin = User.objects.create_superuser(username='admin_user', password='pass@123')

    def test_get_order_detail(self):
        url = reverse('order_by_id', args=[self.order.id])
        response = self.client.get(url, format='json')
        self.assertEqual(response.status_code, status.HTTP_401_UNAUTHORIZED)

    def test_get_order_detail_authenticated(self):
        self.client.force_authenticate(user=self.user)
        url = reverse('order_by_id', args=[self.order.id])
        response = self.client.get(url, format='json')
        self.assertEqual(response.status_code, status.HTTP_200_OK)

    def test_order_paid(self):
        url = reverse('order_paid', args=[self.order.id])
        response = self.client.put(url, format='json')
        self.assertEqual(response.status_code, status.HTTP_401_UNAUTHORIZED)

    def test_order_paid_authenticate(self):
        self.client.force_authenticate(user=self.user)
        url = reverse('order_paid', args=[self.order.id])
        response = self.client.put(url, format='json')
        self.assertEqual(response.status_code, status.HTTP_200_OK)

    def test_order_delivered(self):
        url = reverse('order_delivered', args=[self.order.id])
        response = self.client.put(url, format='json')
        self.assertEqual(response.status_code, status.HTTP_401_UNAUTHORIZED)

    def test_order_delivered_authenticated(self):
        self.client.force_authenticate(user=self.user)
        url = reverse('order_delivered', args=[self.order.id])
        response = self.client.put(url, format='json')
        self.assertEqual(response.status_code, status.HTTP_403_FORBIDDEN)

    def test_order_delivered_admin(self):
        self.client.force_authenticate(user=self.admin)
        url = reverse('order_delivered', args=[self.order.id])
        response = self.client.put(url, format='json')
        self.assertEqual(response.status_code, status.HTTP_200_OK)
