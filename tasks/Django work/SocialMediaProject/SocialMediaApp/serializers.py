from dataclasses import field
from rest_framework.serializers import *
from .models import User
from rest_framework import serializers

from django.utils.encoding import force_bytes, DjangoUnicodeDecodeError, smart_str
from django.utils.http import urlsafe_base64_decode, urlsafe_base64_encode
from django.contrib.auth.tokens import PasswordResetTokenGenerator
from .utilsemail import Util


class serializer1(ModelSerializer):
   class Meta:
        model=User
        field=['id','email', 'password','username']    


class UserRegisterationsSerializer(ModelSerializer):
    password2=serializers.CharField(style={'input_type': 'password'},write_only=True)
    print("hello")
    class Meta:
        model=User
        fields=['email', 'password', 'password2','username','first_name','last_name',]   
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
        print(validate_date,"validate data")
        return User.objects.create_user(**validate_date)    



class UserLoginSerializer(ModelSerializer):
    email=serializers.EmailField(max_length=255)
    class Meta:
        model=User
        fields=['email', 'password']





class UserProfileSerilaizer(ModelSerializer):
    class Meta:
        model=User
        fields=['id', 'email', 'username']


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