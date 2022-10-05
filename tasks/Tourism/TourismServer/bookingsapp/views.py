import datetime
import json
from sqlite3 import apilevel
from django.http import HttpResponse, Http404, JsonResponse
from django.shortcuts import render
from django.template.defaulttags import csrf_token
from django.views.decorators.csrf import csrf_exempt

from rest_framework import status, exceptions
from rest_framework.response import Response
# from bookingsapp import serializers
from django.core import serializers

from bookingsapp.models import BookingDetails, Feedback, User, UserToken
from bookingsapp.serializers import BookingDetailSerializer, BookingSerializer, FeedbackSerializer, PaymentSerializer, UserSerializer
from rest_framework.views import APIView

import uuid


from .JwtAuthentication import JWTAuthentication, create_access_token, create_refresh_token, decode_refresh_token

# from .JwtAuthentication import call
# Create your views here.

import cloudinary
# from dotenv import load_dotenv
# env = load_dotenv()
# secret = env('access_secret')



# cloudinary.uploader.upload(open('/anjunabeach.jpeg', 'rb'))

class uploadImage(APIView):
    
    def post(self, request, format=None):
        print(request)
        imageUrl = cloudinary.uploader.upload(request.data['file'])

        # Build the URL for the image and save it in the variable 'srcURL'
        # srcURL = cloudinary.CloudinaryImage("quickstart_butterfly").build_url()
        return Response(imageUrl['secure_url'])


class UserList(APIView):
    """
    List all snippets, or create a new snippet.
    """
    def get(self, request, format=None):
        users = User.objects.all()
        serializer = UserSerializer(users, many=True)
        return Response(serializer.data)

    def post(self, request, format=None):
        print(request.data)

        serializer = UserSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            user = User.objects.get(email=request.data['email'])
            access_token = create_access_token(user.id)
            refresh_token = create_refresh_token(user.id)
            UserToken.objects.create(
                user_id = user.id, 
                token = refresh_token,
                expired_at = datetime.datetime.utcnow() + datetime.timedelta(days = 5)
            )
            response = Response()

            response.set_cookie(key='refresh_token', value=refresh_token, httponly=False)
            response.data = {
                'token': access_token,
            }
            return response
            # return Response(serializer.data, status=status.HTTP_201_CREATED)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)


class UserDetail(APIView):
    """
    Retrieve, update or delete a snippet instance.
    """

    authentication_classes = [JWTAuthentication]
    # permission_classes = (IsAuthenticated,)


    # def get_object(self, pk):
    #     try:
    #         return User.objects.get(id=pk)
    #     except User.DoesNotExist:
    #         raise Http404


    def get(self, request, format=None):
        user = User.objects.get(email=request.user)
        serializer = UserSerializer(user)
        return Response(serializer.data)

    def put(self, request, format=None):
        user = User.objects.get(email=request.user)
        serializer = UserSerializer(user, data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

    def delete(self, request, format=None):
        user = User.objects.get(email=request.user)
        user.delete()
        return Response(status=status.HTTP_204_NO_CONTENT)


class Login(APIView):
    def post(self, request):
        email = request.data['email']
        password = request.data['password']
        if User.objects.filter(email=email).exists():
            user = User.objects.get(email=email)
            if user.password != password:
                raise exceptions.APIException('Wrong Password!')

        else:
            raise exceptions.APIException("email doesn't exist, Try again")

        user = User.objects.get(email=email, password=password)

        access_token = create_access_token(user.id)
        refresh_token = create_refresh_token(user.id)

        UserToken.objects.create(
            user_id=user.id,
            token=refresh_token,
            expired_at=datetime.datetime.utcnow() + datetime.timedelta(days=5)
        )
        response = Response()
        response.set_cookie(key='refresh_token', value=refresh_token, httponly=False)
        response.data = {
            'access_token': access_token
        }
        return response


class RefreshJwtTokenViewSet(APIView):

    def post(self, request):
        refresh_token = request.COOKIES.get('refresh_token')
        user_id = decode_refresh_token(refresh_token)
        if not UserToken.objects.filter(
            user_id=user_id,
            token=refresh_token,
            expired_at__gt=datetime.datetime.now(tz=datetime.timezone.utc)
        ).exists():
            raise exceptions.AuthenticationFailed('Unauthenticated')

        access_token = create_access_token(user_id)

        return JsonResponse({
            'token': access_token
            })


class LogoutViewSet(APIView):

    def post(self, request):
        refresh_token = request.COOKIES.get('refresh_token')
        UserToken.objects.filter(
            token=refresh_token
        ).delete()

        response = Response()
        response.delete_cookie(key='refresh_token')

        response.data = {
            'message': 'successfully logged out',
        }

        return response


def getFeedbacks(request):
    feedbacks = Feedback.objects.all()
    serializer = FeedbackSerializer(feedbacks, many=True)
    return JsonResponse(serializer.data, safe=False)


class FeedbackViewSet(APIView):

    authentication_classes = [JWTAuthentication]

    def post(self, request, format=None):
        email = request.user
        user = User.objects.get(email=email)
        if Feedback.objects.filter(user_id=user.id).exists():
            feedback = Feedback.objects.get(user_id=user.id)
            serializer = FeedbackSerializer(data=feedback)
            if serializer.is_valid():
                serializer.save()
                return Response(serializer.data, status=status.HTTP_201_CREATED)
            return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)
        else:
            comment = request.data['comment']
            rating = request.data['rating']
            user_id = user.id
            data = {
                'user_id': user_id,
                'comment': comment,
                'rating': rating
            }
            serializer = FeedbackSerializer(data=data)
            if serializer.is_valid():
                serializer.save()
                return Response(serializer.data, status=status.HTTP_201_CREATED)
            return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)



class PaymentViewset(APIView):

    # authentication_classes=[JWTAuthentication]

    # def get(self, request, format=None):
    #     tours = Tour.objects.all()
    #     serializer = TourDetailSerializer(tours, many=True)
    #     return Response(serializer.data)

    def post(self, request, format=None):
        # user = User.objects.get(email=request.user)
        request.data['transaction_id'] = str(uuid.uuid1())
        serializer = PaymentSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)


class PaymentDetails(APIView):
    """
    Retrieve, update or delete a snippet instance.
    """

    # authentication_classes = [JWTAuthentication]
    # permission_classes = (IsAuthenticated,)


    def get_object(self, pk):
        try:
            return PaymentDetails.objects.get(id=pk)
        except PaymentDetails.DoesNotExist:
            raise Http404


    def get(self, request, pk, format=None):
        payment = self.get_object(pk)
        serializer = PaymentSerializer(payment)
        return Response(serializer.data)

    def put(self, request, pk, format=None):
        payment = self.get_object(pk)
        serializer = PaymentSerializer(payment, data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)


class BookingViewset(APIView):

    # authentication_classes=[JWTAuthentication]

    def get(self, request, format=None):
        tours = BookingDetails.objects.all()
        serializer = BookingDetailSerializer(tours, many=True)
        return Response(serializer.data)

    def post(self, request, format=None):
        # user = User.objects.get(email=request.user)
        # request.data['transaction_id'] = uuid.uuid1()
        serializer = BookingSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)


class FeedbackDetails(APIView):
    """
    Retrieve, update or delete a snippet instance.
    """

    # authentication_classes = [JWTAuthentication]
    # permission_classes = (IsAuthenticated,)


    def get_object(self, pk):
        try:
            return BookingDetails.objects.get(id=pk)
        except BookingDetails.DoesNotExist:
            raise Http404


    def get(self, request, pk, format=None):
        booking = self.get_object(pk)
        serializer = BookingSerializer(booking)
        return Response(serializer.data)

    def put(self, request, pk, format=None):
        booking = self.get_object(pk)
        serializer = BookingSerializer(booking, data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

