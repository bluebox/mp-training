from dataclasses import fields
from operator import mod
from unittest.util import _MAX_LENGTH
from rest_framework.serializers import *

from .utilsemail import Util
from .models import * 
from .models import User
from rest_framework import serializers
from django.utils.encoding import force_bytes, DjangoUnicodeDecodeError, smart_str
from django.utils.http import urlsafe_base64_decode, urlsafe_base64_encode
from django.contrib.auth.tokens import PasswordResetTokenGenerator
class Myserializer(ModelSerializer):
    class Meta:
        model=employee
        fields='__all__'
    def create(self, validated_data):
        return employee.objects.create(**validated_data)    


    def update(self, instance, validated_data):
        """
        Update and return an existing `Snippet` instance, given the validated data.
        """
        instance.emp_name = validated_data.get('title', instance.emp_name)
        instance.emp_age = validated_data.get('emp_age', instance.emp_age)
        instance.emp_address = validated_data.get('emp_address', instance.emp_address)
       
        instance.save()
        return instance  




class Myserializer1(HyperlinkedModelSerializer):
    class Meta:
        model=employee
        fields='__all__'




class Myserializer2(ModelSerializer):
    class Meta:
        model=User
        fields=['id','password','username','first_name','last_name' ,'email' ]

class ExampleSerializer(Serializer):
    emp_name= models.CharField(max_length=20)
    emp_age = models.IntegerField(default=0)
    emp_number= models.IntegerField(default=000000)
    emp_address = models.CharField(max_length=200)

class UserRegisterationsSerializer(ModelSerializer):
    password2=serializers.CharField(style={'input_type': 'password'},write_only=True)
    class Meta:
        model=User
        fields='__all__'
        extra_kwargs={
            'password':{'write_only':True,}
        }

    def validate(self, attrs):
        password= attrs.get('password')
        password2= attrs.get('password2')
        if password != password2:
            raise serializers.ValidationError("password and confirmed Password not matched")
        return attrs  


    def create(self, validate_date):
        return User.objects.create_user(**validate_date)    


class UserLoginSerializer(ModelSerializer):
    email=serializers.EmailField(max_length=255)
    class Meta:
        model=User
        fields=['email', 'password']



class UserProfileSerilaizer(ModelSerializer):
    class Meta:
        model=User
        fields=['id', 'email', 'name']


class UserChangePasswoordSerializer(ModelSerializer):
    password=serializers.CharField(max_length=225,style={'input_type': 'password'},write_only=True)
    password2=serializers.CharField(max_length=225,style={'input_type': 'password'},write_only=True)
    class Meta:
        model=User
        fields=['password', 'password2']
    def validate(self, attrs):
        password= attrs.get('password')
        password2= attrs.get('password2')
        user = self.context.get('user')
        if password != password2:
            raise serializers.ValidationError("password and confirmed Password not matched")
        user.set_password(password)
        user.save()
        return attrs  

class SendPasswordEmailResetSerializer(ModelSerializer):
    email=serializers.EmailField(max_length=255)
    class Meta:
        model=User
        fields=['email']
    def validate(self, attrs):
        email= attrs.get('email')
       
        user = self.context.get('user')
        if User.objects.filter(email=email).exists():
            user = User.objects.get(email=email)
            uid=urlsafe_base64_encode(force_bytes(user.id))
            print("encoded UID",uid)
            token = PasswordResetTokenGenerator().make_token(user)
            print("password Reset Token", token)
            link='http://localhost:3000/emailresetPassword/'+uid+'/'+token
            print('password Reset Link', link)
            #send email
            body='Click Following Link to Reset Yur Password'+link
            data={
                'subject':'Reset Your password',
                'body':body,
                'to_email':user.email,
            }
            Util.send_email(data)
            return attrs

        else:
            raise ValidationError("Your are a not Registered User")
             

 
class UserPasswordResetSerializer(ModelSerializer):
    password=serializers.CharField(max_length=225,style={'input_type': 'password'},write_only=True)
    password2=serializers.CharField(max_length=225,style={'input_type': 'password'},write_only=True)
    class Meta:
        model=User
        fields=['password', 'password2']
    def validate(self, attrs):
        try:
            password= attrs.get('password')
            password2= attrs.get('password2')
            uid = self.context.get('uid')
            token = self.context.get('token')
            if password != password2:
                raise serializers.ValidationError("password and confirmed Password not matched")
            id = smart_str(urlsafe_base64_decode(uid))
            user = User.objects.get(id=id)
            if not  PasswordResetTokenGenerator().check_token(user,token):
                raise ValidationError('Token is Not Valid or Expired')
        
            user.set_password(password)
            user.save()
            return attrs
        except DjangoUnicodeDecodeError as identifier:
            PasswordResetTokenGenerator().check_token(user, token)
            raise ValidationError('Token is Not Valid or Expired')