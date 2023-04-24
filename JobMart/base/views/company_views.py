from rest_framework.decorators import api_view, permission_classes
from rest_framework.permissions import IsAuthenticated, IsAdminUser
from rest_framework.response import Response
from base.serializers import (UserSerializer, 
                              UserSerializerWithToken, 
                              JobPostSerializer, 
                              JobApplicationUserDetailsSerializer, 
                              CandidateDetailsSerializer, 
                              CompanySerializer, 
                              CompanyListSerializer)
from django.contrib.auth.models import User
from django.contrib.auth.hashers import make_password
from rest_framework import status
from base.models import JobPost, Company, JobApplications


@api_view((['POST']))
def register_company(request):
    data = request.data
    try:
        user = User.objects.create(
            first_name = data['companyName'],
            username = data['email'], 
            email = data['email'],
            password = make_password(data['password']),
            is_staff = True
            
        )    
        company = Company.objects.create(
            user=user,
            company_name = data['companyName'],
            industry_type = data['industryType'],
            description = data['description'],
            Established = data['established']
        )
        serializer = UserSerializerWithToken(user, many=False)
        return Response(serializer.data)
    except:
        message = {'detail': 'user with this email already exists'}
        return Response(message, status=status.HTTP_400_BAD_REQUEST)
    

@api_view(['GET'])
@permission_classes([IsAuthenticated, IsAdminUser])
def get_company_posts(request):
    try:
        user = request.user
        company = Company.objects.get(user=user)
        jobPosts = JobPost.objects.filter(company=company)
        serializer = JobPostSerializer(jobPosts, many=True)
        return Response(serializer.data)
    except:
        message = {'detail': 'Company Does not Exist'}
        return Response(message, status=status.HTTP_400_BAD_REQUEST)  


@api_view(['GET'])
@permission_classes([IsAuthenticated, IsAdminUser])
def get_company_post(request, pk):
    try:
        jobPost = JobPost.objects.get(id=pk)
        serializer = JobPostSerializer(jobPost, many=False)
        return Response(serializer.data)
    except:
        message = {'detail': 'Company Post doesnot Exist'}
        return Response(message, status=status.HTTP_400_BAD_REQUEST)


@api_view(['DELETE'])
@permission_classes([IsAuthenticated, IsAdminUser])
def deletePost(request, pk):
    try:
        jobPost_to_delete = JobPost.objects.get(id=pk)
        jobPost_to_delete.delete()
        return Response({'Message': 'Job post deleted Successfully'}, status=status.HTTP_200_OK)
    except:
        message = {'detail': 'Job Post Doesnot exists'}
        return Response(message, status=status.HTTP_400_BAD_REQUEST)


@api_view(['GET'])
@permission_classes([IsAuthenticated, IsAdminUser])
def get_applied_candidates(request, pk):
    try:
        jobPost = JobPost.objects.get(id=pk)
        candidates_applied = JobApplications.objects.filter(job_post=jobPost)
        serializer = JobApplicationUserDetailsSerializer(candidates_applied, many=True)
        return Response(serializer.data)
    except:
        message = {'detail': 'Applied Candidates doesnot exist'}
        return Response(message, status=status.HTTP_400_BAD_REQUEST)


@api_view(['GET'])
@permission_classes([IsAuthenticated, IsAdminUser])
def get_candidate_details(request, pk):
    try:
        candidate = User.objects.get(id=pk)
        serializer = CandidateDetailsSerializer(candidate, many=False)
        return Response(serializer.data)
    except:
        message = {'detail': 'Candidate details doesnot exists'}
        return Response(message, status=status.HTTP_400_BAD_REQUEST)
    

@api_view(['GET'])
@permission_classes([IsAuthenticated, IsAdminUser])
def getCompanyProfile(request):
    try:
        user = request.user
        serializer = UserSerializer(user, many=False)
        return Response(serializer.data)
    except:
        message = {'detail': 'Unable to fetch Company Profile'}
        return Response(message, status=status.HTTP_400_BAD_REQUEST)


@api_view(['PUT'])
@permission_classes([IsAuthenticated, IsAdminUser])
def updateProfile(request):
    try:
        user = request.user
        serializer = UserSerializerWithToken(user, many=False)
        data = request.data
        user.first_name = data['name']
        user.username = data['email']
        user.email = data['email']
        
        if data['password'] != '':
            user.password = make_password(data['password'])
            
        user.save()
        return Response(serializer.data) 
    except:
        message = {'detail': 'Profile updation not completed'}
        return Response(message, status=status.HTTP_400_BAD_REQUEST)


@api_view(['GET'])
@permission_classes([IsAuthenticated, IsAdminUser])
def getCompanyDetailProfile(request):
    try:
        user = request.user
        company = Company.objects.get(user=user)
        serializer = CompanySerializer(company, many=False)
        return Response(serializer.data)
    except:
        message = {'detail': 'Company Profile Fetching not completed'}
        return Response(message, status=status.HTTP_400_BAD_REQUEST)


@api_view(['PATCH'])
@permission_classes([IsAuthenticated, IsAdminUser])
def updateCompanyDetailProfile(request):
    try:
        user = request.user
        company = Company.objects.get(user=user)
        data = request.data
        company.company_name = data['companyName']
        company.industry_type = data['industryType']
        company.description = data['description']
        company.Established = data['established']
        company.image = request.FILES.get('image')
        company.save()
        serializer = CompanySerializer(company, many=False)
        return Response(serializer.data)
    except:
        message = {'detail': 'Company Profile updation Failed'}
        return Response(message, status=status.HTTP_400_BAD_REQUEST)


@api_view(['GET'])
def getCompaniesList(request):
    try:
        companies = Company.objects.all()
        serializer = CompanyListSerializer(companies, many=True)
        return Response(serializer.data)
    except:
        message = {'detail': 'Error in loading Companies'}
        return Response(message, status=status.HTTP_400_BAD_REQUEST)


@api_view(['GET'])
def getCompaniesListByFiltering(request, pk):
    try: 
        company = Company.objects.get(company_name=pk)
        company_related_job_posts = JobPost.objects.filter(company=company)
        serializer = JobPostSerializer(company_related_job_posts, many=True)
        return Response(serializer.data)
    except:
        job_posts = JobPost.objects.all()
        serializer = JobPostSerializer(job_posts, many=True)
        return Response(serializer.data)



@api_view(['GET'])
def getCompaniesListByLocation(request, pk):
    try: 
        location_related_job_posts = JobPost.objects.filter(location__icontains=pk)
        serializer = JobPostSerializer(location_related_job_posts, many=True)
        return Response(serializer.data)
    except:
        job_posts = JobPost.objects.all()
        serializer = JobPostSerializer(job_posts, many=True)
        return Response(serializer.data)
    
         
    