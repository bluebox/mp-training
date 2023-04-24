from rest_framework.decorators import api_view, permission_classes
from rest_framework.permissions import IsAuthenticated, IsAdminUser
from rest_framework.response import Response
from rest_framework_simplejwt.serializers import TokenObtainPairSerializer
from rest_framework_simplejwt.views import TokenObtainPairView
from base.serializers import (UserSerializer, 
                              UserSerializerWithToken, 
                              AddressesSerializer, 
                              ExperienceSerializer, 
                              EducationSerializer, 
                              JobApplicationSerializer, 
                              JobApplicationDetailedSerializer, 
                              SkillDetailsSerializer, 
                              SkillSerializer)
from django.contrib.auth.models import User
from django.contrib.auth.hashers import make_password
from rest_framework import status
from base.models import Address, Experience, Education, JobApplications, JobPost, SkillDetails, Skill, Company
    
    
@api_view((['POST']))
def register_user(request):
    data = request.data
    try:
        user = User.objects.create(
            first_name = data['name'],
            username = data['email'],
            email = data['email'],
            password = make_password(data['password'])
            
        )
        
        serializer = UserSerializerWithToken(user, many=False)
        return Response(serializer.data)
    except:
        message = {'detail': 'user with this email already exists'}
        return Response(message, status=status.HTTP_400_BAD_REQUEST)


@api_view(['GET'])
@permission_classes([IsAuthenticated])
def getUserProfile(request):
    try:
        user = request.user
        serializer = UserSerializer(user, many=False)
        return Response(serializer.data) 
    except:
        message = {'detail': 'user Profile not Found'}
        return Response(message, status=status.HTTP_400_BAD_REQUEST)


@api_view(['PUT'])
@permission_classes([IsAuthenticated])
def updateUserProfile(request):
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
        message = {'detail': 'user Profile Updation Failed'}
        return Response(message, status=status.HTTP_400_BAD_REQUEST)
    
    
@api_view(['GET'])
@permission_classes([IsAdminUser])
def getUsers(request):
    try:
        users = User.objects.all()
        serializer = UserSerializer(users, many=True)
        return Response(serializer.data) 
    except:
        message = {'detail': 'users not found'}
        return Response(message, status=status.HTTP_400_BAD_REQUEST)


@api_view(['POST'])
@permission_classes([IsAuthenticated])
def addNewAddress(request):
    try:
        user = request.user
        data =  request.data
        houseNo = data['houseNo']
        streetNo = data['streetNo']
        city = data['city']
        state = data['state']
        country = data['country']
        created_address = Address.objects.create(user=user, house_no=houseNo, street=streetNo,city=city, state=state, country=country)
        created_address.save()
        return Response(AddressesSerializer(created_address, many=False).data)
    except:
        message = {'detail': 'Failed to add address'}
        return Response(message, status=status.HTTP_400_BAD_REQUEST)


@api_view(['GET'])
@permission_classes([IsAuthenticated])
def getAddress(request):
    try:
        user = request.user
        addresses = Address.objects.filter(user=user)
        serializer = AddressesSerializer(addresses, many=True)
        return Response(serializer.data)
    except:
        message = {'detail': 'Failed to get address'}
        return Response(message, status=status.HTTP_400_BAD_REQUEST)


@api_view(['PUT'])
@permission_classes([IsAuthenticated])
def editAddress(request, pk):
    try:
        user = request.user
        data =  request.data
        address = Address.objects.get(_id=pk)
        address.house_no = data['houseNo']
        address.street = data['streetNo']
        address.city = data['city']
        address.state = data['state']
        address.country = data['country']
        address.save()
        return Response(AddressesSerializer(address, many=False).data)
    except:
        message = {'detail': 'Failed to edit address'}
        return Response(message, status=status.HTTP_400_BAD_REQUEST)


@api_view(['DELETE'])
@permission_classes([IsAuthenticated])
def deleteAddress(request, pk):
    try:
        user = request.user
        address = Address.objects.get(_id=pk, user=user)
        address.delete()
        return Response({'Message': 'Address deleted successfully'}, status=status.HTTP_200_OK)
    except:
        message = {'detail': 'Failed to delete address'}
        return Response(message, status=status.HTTP_400_BAD_REQUEST)
    

@api_view(['POST'])
@permission_classes([IsAuthenticated])
def addNewExperience(request):
    try:
        user = request.user
        print(user)
        data =  request.data
        company_name = data['companyName']
        job_location = data['jobLocation']
        description = data['jobDescription']
        experience = data['experience']
        created_experience = Experience.objects.create(user=user, company_name=company_name, job_location=job_location,description=description, experience=experience)
        created_experience.save()
        return Response(ExperienceSerializer(created_experience, many=False).data)
    except:
        message = {'detail': 'Failed to add Experience'}
        return Response(message, status=status.HTTP_400_BAD_REQUEST)


@api_view(['GET'])
@permission_classes([IsAuthenticated])
def getExperience(request):
    try:
        user = request.user
        experiences = Experience.objects.filter(user=user)
        serializer = ExperienceSerializer(experiences, many=True)
        return Response(serializer.data)
    except:
        message = {'detail': 'Failed to Get Experience'}
        return Response(message, status=status.HTTP_400_BAD_REQUEST)


@api_view(['PUT'])
@permission_classes([IsAuthenticated])
def editExperience(request, pk):
    try:
        user = request.user
        data =  request.data
        experience = Experience.objects.get(_id=pk)
        experience.company_name = data['companyName']
        experience.job_location = data['jobLocation']
        experience.description = data['jobDescription']
        experience.experience = data['experience']
        experience.save()
        return Response(ExperienceSerializer(experience, many=False).data)
    except:
        message = {'detail': 'Failed to edit Experience'}
        return Response(message, status=status.HTTP_400_BAD_REQUEST)


@api_view(['DELETE'])
@permission_classes([IsAuthenticated])
def deleteExperience(request, pk):
    try:
        user = request.user
        userExperience = Experience.objects.get(_id=pk, user=user)
        userExperience.delete()
        return Response({'Message': 'Experience deleted Successfully'}, status=status.HTTP_200_OK)
    except:
        message = {'detail': 'Failed to delete Experience'}
        return Response(message, status=status.HTTP_400_BAD_REQUEST)
    
    
@api_view(['POST'])
@permission_classes([IsAuthenticated])
def addNewEducation(request):
    try:
        user = request.user
        data =  request.data
        major = data['major']
        degree = data['degree']
        university = data['university']
        percentage = data['percentage']
        created_education = Education.objects.create(user=user, major=major, degree=degree,university=university, percentage=percentage)
        created_education.save()
        return Response(EducationSerializer(created_education, many=False).data)
    except:
        message = {'detail': 'Failed to add Education'}
        return Response(message, status=status.HTTP_400_BAD_REQUEST)


@api_view(['GET'])
@permission_classes([IsAuthenticated])
def getEducation(request):
    try:
        user = request.user
        educations = Education.objects.filter(user=user)
        serializer = EducationSerializer(educations, many=True)
        return Response(serializer.data)
    except:
        message = {'detail': 'Failed to get Education'}
        return Response(message, status=status.HTTP_400_BAD_REQUEST)


@api_view(['PUT'])
@permission_classes([IsAuthenticated])
def editEducation(request, pk):
    try:
        user = request.user
        data =  request.data
        education = Education.objects.get(_id=pk)
        education.major = data['major']
        education.degree = data['degree']
        education.university = data['university']
        education.percentage = data['percentage']
        education.save()
        return Response(EducationSerializer(education, many=False).data)
    except:
        message = {'detail': 'Failed to edit Education'}
        return Response(message, status=status.HTTP_400_BAD_REQUEST)


@api_view(['DELETE'])
@permission_classes([IsAuthenticated])
def deleteEducation(request, pk):
    try:
        user = request.user
        userEducation = Education.objects.get(_id=pk, user=user)
        userEducation.delete()
        return Response({'Messaage': 'Education deleted successfully'}, status=status.HTTP_200_OK)    
    except:
        message = {'detail': 'Failed to delete Education'}
        return Response(message, status=status.HTTP_400_BAD_REQUEST)


@api_view(['POST'])
@permission_classes([IsAuthenticated])
def addNewToCart(request):
    try:
        user = request.user
        data = request.data
        post_id = int(data['postId'])
        post_id_int = int(post_id)
        post = JobPost.objects.get(id=post_id_int)
        job_post_applied = JobApplications.objects.get(job_post=post, applicant=user)
        if(job_post_applied):
            message = {'detail': 'You have Already applied this job.'}
            return Response(message, status=status.HTTP_400_BAD_REQUEST)
    except JobApplications.DoesNotExist:
        applicant_resume = request.FILES.get('resume')
        job_applied_create = JobApplications.objects.create(job_post=post, applicant=user, resume=applicant_resume)
        serializer = JobApplicationSerializer(job_applied_create, many=False)
        return Response(serializer.data, status=status.HTTP_200_OK)
        

@api_view(['GET'])
@permission_classes([IsAuthenticated])
def getFromCart(request):
    try:
        user = request.user
        job_applications = JobApplications.objects.filter(applicant=user)
        serializer = JobApplicationDetailedSerializer(job_applications, many=True)
        return Response(serializer.data)
    except:
        message = {'detail': 'Failed to get Job Applications'}
        return Response(message, status=status.HTTP_400_BAD_REQUEST)


@api_view(['DELETE'])
@permission_classes([IsAuthenticated])
def deleteFromCart(request, pk):
    try:
        user = request.user
        job_application_to_delete = JobApplications.objects.get(job_post=pk, applicant=user)
        job_application_to_delete.delete()
        return Response({'Message': 'Job application has been deleted'}, status=status.HTTP_200_OK)
    except:
        message = {'detail': 'Failed to Delete Job Application'}
        return Response(message, status=status.HTTP_400_BAD_REQUEST)
    


@api_view(['GET'])
@permission_classes([IsAuthenticated])
def getSkills(request):
    try:
        user = request.user
        skill_set = SkillDetails.objects.get(user=user)
        serializer = SkillDetailsSerializer(skill_set, many=False)
        return Response(serializer.data)
    except SkillDetails.DoesNotExist:
        message = {'detail': 'No Skills Present Click add Skills to add new Skills'}
        return Response(message, status=status.HTTP_400_BAD_REQUEST)


@api_view(['GET'])
@permission_classes([IsAuthenticated])
def getAllSkills(request):
    try:
        skills = Skill.objects.all()
        serializer = SkillSerializer(skills, many=True)
        return Response({'skillsList':serializer.data})
    except:
        message = {'detail': 'Failed to Get Skills'}
        return Response(message, status=status.HTTP_400_BAD_REQUEST)


@api_view(['POST'])
@permission_classes([IsAuthenticated])
def addSkill(request):
    try:
        user = request.user
        data = request.data
        skill_id = int(data['skill_id'])
        skill_set = SkillDetails.objects.get(user=user)
        new_skill = Skill.objects.get(_id=skill_id)
        skill_set.skills.add(new_skill)
        skill_set.save()
        return Response({'message': 'Skill added successfully'})
    except SkillDetails.DoesNotExist:
        new_skill = Skill.objects.get(_id=skill_id)
        new_skill_set = SkillDetails.objects.create(user=user)
        new_skill_set.add(new_skill)
        skill_set.save()
        return Response({'message': 'new Sill set has been added with new Skill'})
    except:
        return Response({'message': 'skill is already present'})
    
    
@api_view(['DELETE'])
@permission_classes([IsAuthenticated])
def deleteSkill(request, pk):
    try:
        user = request.user
        skill_to_delete = Skill.objects.get(_id=pk)
        user_skill_set = SkillDetails.objects.get(user=user)
        user_skill_set.skills.remove(skill_to_delete)
        message = {'detail': 'Skill has been Removed'}
        return Response(message, status=status.HTTP_200_OK)
    except:
        message = {'detail': 'Failed to Delete Skill'}
        return Response(message, status=status.HTTP_400_BAD_REQUEST)


class MyTokenObtainPairSerializer(TokenObtainPairSerializer):
    def validate(self, attrs):
        data = super().validate(attrs)
        
        serializer = UserSerializerWithToken(self.user).data
        for k, v in serializer.items():
            data[k] = v

        return data


class MyTokenObtainPairView(TokenObtainPairView):
    serializer_class = MyTokenObtainPairSerializer