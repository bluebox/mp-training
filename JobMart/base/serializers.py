from rest_framework import serializers
from django.contrib.auth.models import User
from rest_framework_simplejwt.tokens import RefreshToken
from . models import JobPost, Address, Experience, Education, JobApplications, SkillDetails, Skill,Company


class UserSerializer(serializers.ModelSerializer):
    name = serializers.SerializerMethodField(read_only=True)
    _id = serializers.SerializerMethodField(read_only=True)
    isAdmin = serializers.SerializerMethodField(read_only=True)
    class Meta:
        model = User
        fields = ['id','_id', 'username', 'email', 'name', 'isAdmin', ]
        
        
    def get_name(self, obj):
        name = obj.first_name
        if name == '':
            name = obj.email
            
        return name
    
    def get__id(self, obj):
        return obj.id
    
    def get_isAdmin(self, obj):
        return obj.is_staff
    
    
class UserSerializerWithToken(UserSerializer):
    token = serializers.SerializerMethodField(read_only=True)
    
    class Meta:
        model = User
        fields = ['id','_id', 'username', 'email', 'name', 'token', 'isAdmin']
        
    def get_token(self, obj):
        token = RefreshToken.for_user(obj)
        return str(token.access_token)
        

class JobPostSerializer(serializers.ModelSerializer):
    class Meta:
        model = JobPost
        fields = '__all__'
        depth = 1
        
class AddressesSerializer(serializers.ModelSerializer):
    class Meta:
        model = Address
        fields = '__all__'
        
        
class ExperienceSerializer(serializers.ModelSerializer):
    class Meta:
        model = Experience
        fields = '__all__'
        
        
class EducationSerializer(serializers.ModelSerializer):
    class Meta:
        model = Education
        fields = '__all__'


class JobApplicationSerializer(serializers.ModelSerializer):
    class Meta:
        model = JobApplications
        fields = '__all__'
        
        
class JobApplicationDetailedSerializer(serializers.ModelSerializer):
    class Meta:
        model = JobApplications
        fields = ['job_post', 'id',]
        depth = 2
        
        
class JobApplicationUserDetailsSerializer(serializers.ModelSerializer):
    class Meta:
        model = JobApplications
        fields = ['applicant', 'resume']
        depth = 1
        

class SkillDetailsSerializer(serializers.ModelSerializer):    
    class Meta:
        model = SkillDetails
        fields = '__all__'
        depth = 1
        
        
class SkillSerializer(serializers.ModelSerializer):
    class Meta:
        model = Skill
        fields = '__all__'
        
class CandidateDetailsSerializer(serializers.ModelSerializer):
    address = AddressesSerializer(many=True)
    experience = ExperienceSerializer(many=True)
    education = EducationSerializer(many=True )
    skills = SkillDetailsSerializer(many=True)
    class Meta:
        model = User
        fields = ['id', 'address', 'experience', 'education', 'skills', ]
        
class CompanySerializer(serializers.ModelSerializer):
    class Meta:
        model = Company
        fields = '__all__'
        

class CompanyListSerializer(serializers.ModelSerializer):
    class Meta:
        model = Company
        fields = ['company_name', '_id']