from django.urls import reverse
from rest_framework import status
from rest_framework.test import APITestCase
from django.contrib.auth.models import User
from .models import Company, JobPost
from .serializers import JobPostSerializer

class GetCompanyPostsTestCase(APITestCase):
    
    def setUp(self):
        self.firstname = 'admin'
        self.username = 'admin@example.com'
        self.email = 'admin@example.com'
        self.password = 'mphs@123.'
        self.user = User.objects.create_user(username=self.username, email=self.email, password=self.password, first_name=self.firstname, is_staff=True)
        self.company = Company.objects.create(user=self.user, company_name='ABC Company', description='A sample company', industry_type='software',)
        self.jobpost1 = JobPost.objects.create(company=self.company, role='Job role 1', location='location 1', job_description='Job Description 1', job_type='intern', skills_required='skill1,skill2', experience=1, department='department 1', role_category='role category 1', education='education 1')
        self.jobpost1 = JobPost.objects.create(company=self.company, role='Job role 2', location='location 2', job_description='Job Description 2', job_type='Regular', skills_required='skill3,skill4', experience=2, department='department 2', role_category='role category 2', education='education 2')
        self.url = reverse('company_posts')
        
    def test_get_company_posts_with_valid_user(self):
        """
        Test to verify if job posts of a company can be retrieved successfully by an authenticated admin user
        """
        self.client.force_authenticate(user=self.user)
        response = self.client.get(self.url)
        job_posts = JobPost.objects.filter(company=self.company)
        serializer = JobPostSerializer(job_posts, many=True)
        self.assertEqual(response.status_code, status.HTTP_200_OK)
        self.assertEqual(response.data, serializer.data)
        
    def test_get_company_posts_with_unauthenticated_user(self):
        """
        Test to verify if an unauthenticated user cannot retrieve job posts of a company
        """
        response = self.client.get(self.url)
        self.assertEqual(response.status_code, status.HTTP_401_UNAUTHORIZED)
        
    def test_get_company_posts_with_non_admin_user(self):
        """
        Test to verify if a non-admin user cannot retrieve job posts of a company
        """
        non_admin_user = User.objects.create_user('testuser', 'test@example.com', 'testpassword')
        self.client.force_authenticate(user=non_admin_user)
        response = self.client.get(self.url)
        self.assertEqual(response.status_code, status.HTTP_403_FORBIDDEN)
        

