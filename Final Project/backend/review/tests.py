from django.urls import reverse
from rest_framework import status
from rest_framework.test import APITestCase
from base.models import Product
from django.contrib.auth.models import User

class ReviewTest(APITestCase):

    def setUp(self):
        self.user = User.objects.create_user(username='username', password='Pass@123')
        self.product = Product.objects.create(name='product', price=10)
        self.url = reverse('create-review', args=[self.product.id])

    def test_review(self):
        response = self.client.post(self.url, format='json')
        self.assertEqual(response.status_code, status.HTTP_401_UNAUTHORIZED)

    def test_review_authenticated(self):
        self.client.force_authenticate(user=self.user)
        data = {
            'title': 'Great product',
            'body': 'I love this product, it works really well',
            'rating': 5
        }
        response = self.client.post(self.url, data, format='json')
        self.assertEqual(response.status_code, status.HTTP_200_OK)

