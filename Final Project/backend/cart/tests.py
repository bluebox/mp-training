from django.urls import reverse
from rest_framework import status
from rest_framework.test import APITestCase
from base.models import Product
from cart.models import Cart, CartItem
from django.contrib.auth.models import User

class CartTestCase(APITestCase):

    def test_get_cart_items(self):
        url = reverse('cart')
        response = self.client.get(url, format='json')
        self.assertEqual(response.status_code, status.HTTP_401_UNAUTHORIZED)

    def test_get_cart_items_authenticate(self):
        self.user = User.objects.create_user(username='user', password='password')
        self.client.force_authenticate(user=self.user)
        url = reverse('cart')
        response = self.client.get(url, format='json')
        self.assertEqual(response.status_code, status.HTTP_200_OK)


class CartItemTestCase(APITestCase):

    def setUp(self):
        self.user = User.objects.create_user(username='user', password='Pass@123')
        self.product = Product.objects.create(name='example_product', price=35, user=self.user)

    def test_add_to_cart(self):
        url = reverse('addToCart')
        data = {
            'product': self.product.id,
            'qty': 1
        }
        response = self.client.post(url, data, format='json')
        self.assertEqual(response.status_code, status.HTTP_401_UNAUTHORIZED)

    def test_add_to_cart_authenticated(self):
        self.client.force_authenticate(user=self.user)
        url = reverse('addToCart')
        data = {
            'product': self.product.id,
            'qty': 1
        }
        response = self.client.post(url, data, format='json')
        self.assertEqual(response.status_code, status.HTTP_200_OK)


class AlterCartItemTestCase(APITestCase):

    def setUp(self):
        self.product = Product.objects.create(name='example', price=10)
        self.user = User.objects.create_user(username='user', password='Pass@123')
        self.cart = Cart.objects.create(user=self.user)
        self.cartItem = CartItem.objects.create(cart=self.cart, product=self.product)

    def test_update_cart(self):
        url = reverse('updateCart', args=[self.product.id])
        response = self.client.put(url, format='json')
        self.assertEqual(response.status_code, status.HTTP_401_UNAUTHORIZED)

    def test_remove_from_cart(self):
        url = reverse('removeItem', args=[self.product.id])
        response = self.client.delete(url, format='json')
        self.assertEqual(response.status_code, status.HTTP_401_UNAUTHORIZED)

    def test_remove_from_cart_authenticate(self):
        self.client.force_authenticate(user=self.user)
        url = reverse('removeItem', args=[self.product.id])
        response = self.client.delete(url, format='json')
        self.assertEqual(response.status_code, status.HTTP_204_NO_CONTENT)