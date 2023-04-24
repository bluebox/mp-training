from rest_framework.decorators import api_view, permission_classes
from rest_framework.permissions import IsAuthenticated, IsAdminUser
from rest_framework.response import Response
from base.models import JobPost, Company
from base.serializers import JobPostSerializer
from django.core.paginator import Paginator, EmptyPage, PageNotAnInteger
from rest_framework import status


@api_view(['GET'])
def getPosts(request):
    query = request.query_params.get('keyword')
    if query == None:
        query = ''
        
    jobPosts = JobPost.objects.filter(role__icontains=query) | JobPost.objects.filter(company__company_name__icontains=query)
    
    page = request.query_params.get('page')
    paginator = Paginator(jobPosts, 8)
    
    try:
        jobPosts = paginator.page(page)
    except PageNotAnInteger:
        jobPosts = paginator.page(1)
    except EmptyPage:
        jobPosts = paginator.page(paginator.num_pages)
        
    if page == None:
        page = 1
    page = int(page)
    serializer = JobPostSerializer(jobPosts, many=True)
    return Response({'jobPosts': serializer.data, 'page': page, 'pages': paginator.num_pages})


@api_view(['GET'])
def getPost(request, pk):
    try:
        jobPost = JobPost.objects.get(id=pk)
        serializer = JobPostSerializer(jobPost, many=False)
        return Response(serializer.data)
    except:
        message = {'detail': 'Job Post doesnot exists'}
        return Response(message, status=status.HTTP_400_BAD_REQUEST)


@api_view(['POST'])
@permission_classes([IsAdminUser, IsAuthenticated])
def addPost(request):
    try:
        user = request.user
        data = request.data
        company = Company.objects.get(user=user)
        new_job_post = JobPost.objects.create(
                company=company,
                location = data['location'],
                job_type = data['jobType'],
                skills_required = data['skillsRequired'],
                role = data['role'],
                job_description = data['jobDescription'],
                experience = data['experience'],
                department = data['department'],
                role_category = data['roleCategory'],
                education = data['education'],
                
            )
        new_job_post.save()
        serializer = JobPostSerializer(new_job_post, many=False)
        return Response(serializer.data)
    except:
        message = {'detail': 'Error in adding job Post'}
        return Response(message, status=status.HTTP_400_BAD_REQUEST)


@api_view(['PUT'])
@permission_classes([IsAdminUser, IsAuthenticated])
def editPost(request, pk):
    try:
        user = request.user
        data = request.data
        company = Company.objects.get(user=user)
        updated_job_post = JobPost.objects.get(id=pk)
        updated_job_post.company = company
        updated_job_post.location = data['location']
        updated_job_post.job_type = data['jobType']
        updated_job_post.skills_required = data['skillsRequired']
        updated_job_post.role = data['role']
        updated_job_post.job_description = data['jobDescription']
        updated_job_post.experience = data['experience']
        updated_job_post.department = data['department']
        updated_job_post.role_category = data['roleCategory']
        updated_job_post.education = data['education']
        
        updated_job_post.save()
        serializer = JobPostSerializer(updated_job_post, many=False)
        return Response(serializer.data)
    except:
        message = {'detail': 'Error in Editing JobPost'}
        return Response(message, status=status.HTTP_400_BAD_REQUEST)
    