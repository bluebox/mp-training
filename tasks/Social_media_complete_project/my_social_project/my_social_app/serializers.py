import profile
from attr import fields
from rest_framework.serializers import *

from .models import Tag, Post, Comment, Notification,Chatmessage, ThreadChatMessage, Threads
from .models import User, FollowUsers
from rest_framework import serializers
from django.utils.encoding import force_bytes, DjangoUnicodeDecodeError, smart_str
from django.utils.http import urlsafe_base64_decode, urlsafe_base64_encode
from django.contrib.auth.tokens import PasswordResetTokenGenerator
from django.core.mail import EmailMessage, send_mail
from my_social_project.settings import EMAIL_HOST_USER


class UserRegisterationsSerializer(ModelSerializer):
    password2 = serializers.CharField(style={'input_type': 'password'}, write_only=True)
    print("hello")

    class Meta:
        model = User
        fields = ['email', 'password', 'password2', 'username', 'first_name', 'last_name', ]
        extra_kwargs = {
            'password': {'write_only': True, }
        }

    def validate(self, attrs):
        password = attrs.get('password')
        password2 = attrs.get('password2')
        if password != password2:
            raise serializers.ValidationError("password and confirmed Password not matched")
        return attrs
      
    def create(self, validate_date):
        print(validate_date, "validate data")
        return User.objects.create_user(**validate_date)

class sendEmailForRegistration(ModelSerializer):
     def validate(self, attrs):
        email = attrs.get('email')

        user = self.context.get('user')
        if User.objects.filter(email=email).exists():
            user = User.objects.get(email=email)
            uid = urlsafe_base64_encode(force_bytes(user.id))
            print("encoded UID", uid)
            token = PasswordResetTokenGenerator().make_token(user)
            print("password Reset Token", token)
            link = 'http://localhost:4200/reset-password/' + uid + '/' + token

            print('password Reset Link', link)
            # send email
            body = link

            data = {
                'subject': 'Reset Your password',
                'body': body,
                'to_email': user.email,
            }

            email = EmailMessage(
                subject=data['subject'],
                body=data['body'],
                from_email=EMAIL_HOST_USER,
                to=[data['to_email']]

            )

            email.send()
            return attrs

        else:
            raise ValidationError("Your are a not Registered User")
class UserLoginSerializer(ModelSerializer):
    email = serializers.EmailField(max_length=255)

    class Meta:
        model = User
        fields = ['email', 'password']
        # , 'password', 'first_name', 'last_name', 'username','id'


# class ProfileInfoSerializer(ModelSerializer):
#     class Meta:
#         model = User
#         fields = ['first_name', 'last_name']
#
#     def validate(self, attrs):
#         firstName = attrs.get('firstName')
#         lastName = attrs.get('lastName')
#         # gender = attrs.get('gender')
#         # intro = attrs.get('intro')
#         # hometown = attrs.get('hometown')
#         # currentCity = attrs.get('currentCity')
#         # workplace = attrs.get('workplace')
#         # eduInstitution = attrs.get('eduInstitution')
#         # countryName = attrs.get('countryName')
#         # birthDate = attrs.get('birthDate')
#         user = self.context.get('user')
#         # if gender == 'male':
#         #     user.gender = 1
#         # elif gender == 'female':
#         #     user.gender = 2
#         # elif gender == 'transgender':
#         #     user.gender == 3
#         user.first_name = firstName
#         user.last_name = lastName
#         # user.intro = intro
#         # user.hometown = hometown
#         # user.current_city = currentCity
#         # user.country = countryName
#         # user.workplace = workplace
#         # user.edu_institution = eduInstitution
#         # user.birth_date = birthDate
#         user.save()
#         return attrs


class ProfileInfoSerializer(ModelSerializer):
    username = serializers.CharField(max_length=225, style={'input_type': 'username'}, write_only=True)
    # id = serializers.CharField(max_length=225, style={'input_type': 'id'}, write_only=True)
    first_name = serializers.CharField(max_length=225, style={'input_type': 'firstName'}, write_only=True)
    last_name = serializers.CharField(max_length=225, style={'input_type': 'lastName'}, write_only=True)
    intro = serializers.CharField(max_length=225, style={'input_type': 'intro'}, write_only=True)
    hometown = serializers.CharField(max_length=225, style={'input_type': 'hometown'}, write_only=True)
    current_city = serializers.CharField(max_length=225, style={'input_type': 'currentCity'}, write_only=True)
    workplace = serializers.CharField(max_length=225, style={'input_type': 'workplace'}, write_only=True)
    edu_institution = serializers.CharField(max_length=225, style={'input_type': 'eduInstitution'}, write_only=True)
    country = serializers.CharField(max_length=225, style={'input_type': 'countryName'}, write_only=True)
    birth_date = serializers.CharField(max_length=225, style={'input_type': 'birthDate'}, write_only=True)

    gender = serializers.CharField(max_length=225, style={'input_type': 'gender'}, write_only=True)

    class Meta:
        model = User
        fields = ['username', 'first_name', 'last_name', 'intro', 'hometown', 'current_city', 'workplace',
                  'edu_institution',
                  'birth_date', 'gender', "country"]

    def validate(self, attrs):
        username = attrs.get('username')
        first_name = attrs.get('first_name')
        intro = attrs.get('intro')
        last_name = attrs.get('last_name')
        hometown = attrs.get('hometown')
        currentCity = attrs.get('current_city')
        workplace = attrs.get('workplace')
        eduInstitution = attrs.get('edu_institution')
        country = attrs.get('country')
        birth_date = attrs.get('birth_date')
        gender = attrs.get('gender')

        user = self.context.get('user')

        user.username = username
        user.first_name = first_name
        user.last_name = last_name
        user.hometown = hometown
        user.intro = intro
        user.current_city = currentCity
        user.workplace = workplace
        user.edu_institution = eduInstitution
        user.country = country
        user.birth_date = birth_date
        user.gender = gender
        user.save()
        return attrs


class ProfileSerializer(ModelSerializer):
    class Meta:
        model = User
        fields = ["id", "email", 'username', 'first_name', 'last_name', 'intro', 'hometown', 'current_city',
                  'workplace',
                  'edu_institution', 'profile_photo', 'cover_photo', 'role', 'follower_count', 'following_count',
                  'birth_date', 'gender', "country", 'enabled', 'account_verified', 'email_verified', 'join_date']


class UserChangePasswordSerializer(ModelSerializer):
    password = serializers.CharField(max_length=225, style={'input_type': 'password'}, write_only=True)
    password2 = serializers.CharField(max_length=225, style={'input_type': 'password2'}, write_only=True)
    oldpassword = serializers.CharField(max_length=225, style={'input_type': 'oldpassword'}, write_only=True)

    class Meta:
        model = User
        fields = ['password', 'password2', 'oldpassword']

    def validate(self, attrs):
        password = attrs.get('password')
        password2 = attrs.get('password2')
        oldpassword = attrs.get('oldpassword')

        user = self.context.get('user')

        print(user.check_password(oldpassword))

        if user.check_password(oldpassword) == False:
            raise serializers.ValidationError(
                "old password is not matched with existing password, enter correct existing password")

        if password != password2:
            raise serializers.ValidationError("password and confirmed Password not matched")
        user.set_password(password)
        user.save()
        return attrs


class UserChangeEmailSerializer(ModelSerializer):
    password = serializers.CharField(max_length=225, style={'input_type': 'password'}, write_only=True)
    newEmail = serializers.CharField(max_length=225, style={'input_type': 'newEmail'}, write_only=True)

    class Meta:
        model = User
        fields = ['newEmail', 'password']

    def validate(self, attrs):
        password = attrs.get('password')
        newEmail = attrs.get('newEmail')

        user = self.context.get('user')

        oldEmail = user.email
        print(oldEmail)
        if oldEmail is not None:
            if User.objects.filter(email=newEmail).exists() == False:
                if user.check_password(password) == True:
                    user.email = newEmail
                    user.save()


            else:
                raise serializers.ValidationError('Entered email is already Exists, please enter new one')
        else:
            raise serializers.ValidationError('Your Old Email is not valid')
        return attrs


class SendPasswordEmailResetSerializer(ModelSerializer):
    email = serializers.EmailField(max_length=255)

    class Meta:
        model = User
        fields = ['email']

    def validate(self, attrs):
        email = attrs.get('email')

        user = self.context.get('user')
        if User.objects.filter(email=email).exists():
            user = User.objects.get(email=email)
            uid = urlsafe_base64_encode(force_bytes(user.id))
            print("encoded UID", uid)
            token = PasswordResetTokenGenerator().make_token(user)
            print("password Reset Token", token)
            link = 'http://localhost:4200/reset-password/' + uid + '/' + token

            print('password Reset Link', link)
            # send email
            body = link

            data = {
                'subject': 'Reset Your password',
                'body': body,
                'to_email': user.email,
            }

            email = EmailMessage(
                subject=data['subject'],
                body=data['body'],
                from_email=EMAIL_HOST_USER,
                to=[data['to_email']]

            )

            email.send()
            return attrs

        else:
            raise ValidationError("Your are a not Registered User")


class UserPasswordResetSerializer(ModelSerializer):
    password = serializers.CharField(max_length=225, style={'input_type': 'password'}, write_only=True)
    password2 = serializers.CharField(max_length=225, style={'input_type': 'password'}, write_only=True)

    class Meta:
        model = User
        fields = ['password', 'password2']

    def validate(self, attrs):
        try:
            password = attrs.get('password')
            password2 = attrs.get('password2')
            uid = self.context.get('uid')
            token = self.context.get('token')
            if password != password2:
                raise serializers.ValidationError("password and confirmed Password not matched")
            id = smart_str(urlsafe_base64_decode(uid))
            user = User.objects.get(id=id)
            if not PasswordResetTokenGenerator().check_token(user, token):
                raise ValidationError('Token is Not Valid or Expired')

            user.set_password(password)
            user.save()
            return attrs
        except DjangoUnicodeDecodeError as identifier:
            PasswordResetTokenGenerator().check_token(user, token)
            raise ValidationError('Token is Not Valid or Expired')


class LoginDataSerializer(ModelSerializer):
    class Meta:
        model = User
        # fields = "__all__"
        exclude = ('password', 'last_login', 'date_joined', 'date_last_modified', 'join_date', 'is_admin', 'is_staff',
                   'is_superuser', 'is_active', 'groups', 'user_permissions', 'created_at', 'updated_at')


class ProfilePhotoSerializer(ModelSerializer):
    # profile_photo = serializers.ImageField(max_length=225, style={'input_type': 'profilePhoto'}, write_only=True)
    class Meta:
        model = User
        # fields = "__all__"
        exclude = ('password', 'last_login', 'date_joined', 'date_last_modified', 'join_date', 'is_admin', 'is_staff',
                   'is_superuser', 'is_active', 'groups', 'user_permissions', 'created_at', 'updated_at')


class FollowUsersSerializer(ModelSerializer):
    class Meta:
        model = FollowUsers
        fields = ['follower', 'followed', ]


class FollowerDataSerializer(ModelSerializer):
    class Meta:
        model = User
        # fields = "__all__"
        exclude = ('password', 'last_login', 'date_joined', 'date_last_modified', 'join_date', 'is_admin', 'is_staff',
                   'is_superuser', 'is_active', 'groups', 'user_permissions', 'created_at', 'updated_at')


class PostSerializer(ModelSerializer):
    class Meta:
        model = Post
        fields = '__all__'


class TagSerializer(ModelSerializer):
    class Meta:
        model = Tag
        fields = ['name', 'dateCreated', 'dateLastModified', 'tagUseCounter', ]


class GetPostDataByIdSerializer(ModelSerializer):
    class Meta:
        model = Post
        fields = "__all__"


class getuserPostSerializer(ModelSerializer):
    # author= LoginDataSerializer(allow_null=True)
    class Meta:
        model = Post
        fields = "__all__"


class   UserSerializer(ModelSerializer):
    class Meta:
        model = User
        # fields = "__all__"
        exclude = ('password', 'last_login', 'date_joined', 'date_last_modified', 'join_date', 'is_admin', 'is_staff',
                   'is_superuser', 'is_active', 'groups', 'user_permissions', 'created_at', 'updated_at')


class GetTimelinePostDataSerializer(ModelSerializer):
    author = UserSerializer()
    postTags = TagSerializer(many=True)

    class Meta:
        model = Post
        fields = "__all__"


class GetPostDataByIdSerializer(ModelSerializer):
    author = UserSerializer()
    postTags = TagSerializer(many=True)

    class Meta:
        model = Post
        fields = "__all__"


class commentserializer(ModelSerializer):
    author = UserSerializer()

    class Meta:
        model = Comment
        exclude = ('post',)


class NotificationSerializer(ModelSerializer):
    sender = UserSerializer()
    receiver = UserSerializer()
    owningComment = commentserializer()
    owningPost = GetPostDataByIdSerializer()

    class Meta:
        model = Notification
        fields = '__all__'



class ChatMessageSerializer(ModelSerializer):
    class Meta:
        model =Chatmessage
        fields='__all__'
class ThreadSerializer(ModelSerializer):
    first_person=UserSerializer()
    second_person=UserSerializer()
    class Meta:
        model =Threads
        fields = '__all__'



class ChatThreadSerializer(ModelSerializer):
    thread=ThreadSerializer()
    user=UserSerializer()
    class Meta:
        model =ThreadChatMessage
        fields='__all__'





class VerifyEmailSerializer(ModelSerializer):
   

    class Meta:
        model = User
        fields ='__all__'

    def validate(self, attrs):
        try:
           
            uid = self.context.get('uid')
            token = self.context.get('token')
            id = smart_str(urlsafe_base64_decode(uid))
            user = User.objects.get(id=id)
            if not PasswordResetTokenGenerator().check_token(user, token):
                raise ValidationError('Token is Not Valid or Expired')

            user.email_verified=True
            user.save()
            return attrs
        except DjangoUnicodeDecodeError as identifier:
            PasswordResetTokenGenerator().check_token(user, token)
            raise ValidationError('Token is Not Valid or Expired')
