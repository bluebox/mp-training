import datetime
import json
from sqlite3 import apilevel

from django.db.models import Subquery, Avg
from django.http import HttpResponse, Http404, JsonResponse
from django.shortcuts import render
from django.template.defaulttags import csrf_token
from rest_framework.decorators import api_view
from pytz import utc

from rest_framework import status, exceptions
from rest_framework.response import Response
# from bookingsapp import serializers
from django.core import serializers

from bookingsapp.models import BookingDetails, CancellationDetails, Feedback, PaymentDetails, User, UserToken
from bookingsapp.serializers import BookingDetailSerializer, BookingSerializer, CancellationDetailSerializer, CancellationSerializer, FeedbackDetailSerializer, FeedbackSerializer, PaymentSerializer, UserSerializer
from rest_framework.views import APIView

import uuid

from toursapp.models import Tour
from .JwtAuthentication import JWTAuthentication, create_access_token, create_refresh_token, decode_refresh_token

# from .JwtAuthentication import call
# Create your views here.

import cloudinary
# from dotenv import load_dotenv
# env = load_dotenv()
# secret = env('access_secret')



# cloudinary.uploader.upload(open('/anjunabeach.jpeg', 'rb'))

def getAverageRatingAndTotalRatings(request):
    if request.method == 'GET':
        total_ratings = Feedback.objects.all().count()
        average_rating = Feedback.objects.all().aggregate(Avg('rating'))
        return JsonResponse({
            'average_rating': round(average_rating['rating__avg'], 1),
            'total_ratings': total_ratings
        })

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
        if not serializer.is_valid():
            raise exceptions.APIException(json.dumps(serializer.errors))
        # if serializer.is_valid():
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

        response.set_cookie(key='refresh_token', value=refresh_token, httponly=True)
        response.set_cookie(key='access_token', value=access_token, httponly=True)
        response.data = serializers.serialize('json', [user])
        return response
        # else:
        #     raise exceptions.APIException(serializer.errors)
        # return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)


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


class UpdateUserByAdmin(APIView):
    """
    Retrieve, update or delete a snippet instance.
    """

    # authentication_classes = [JWTAuthentication]
    # permission_classes = (IsAuthenticated,)


    def get_object(self, pk):
        try:
            return User.objects.get(id=pk)
        except User.DoesNotExist:
            raise Http404


    def get(self, request, pk=None, format=None):
        user = self.get_object(pk)
        serializer = UserSerializer(user)
        return Response(serializer.data)

    def put(self, request,pk=None, format=None):
        user = self.get_object(pk)
        serializer = UserSerializer(user, data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

    def delete(self, request, pk=None, format=None):
        user = self.get_object(pk)
        user.delete()
        return Response(status=status.HTTP_204_NO_CONTENT)


# @api_view(['PUT'])
# def UpdateUserByAdmin(request, pk=None):
#     if request.method == 'PUT':
#         user = User.objects.get(id=pk)
#         is_admin: bool = not user.isAdmin
#         user.isAdmin = is_admin
#         user.save()
#         serializer = UserSerializer([user], many=True)
#         return Response(serializer.data)
#     raise exceptions.APIException(request.method + " method doesn't work")


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
        response.set_cookie(key='refresh_token', value=refresh_token, httponly=True)
        response.set_cookie(key='access_token', value=access_token, httponly=True)
        # userObj = json.loads(user)
        
        response.data = serializers.serialize('json', [user])
        return response


# class RefreshJwtTokenViewSet(APIView):

#     def post(self, request):
#         refresh_token = request.COOKIES.get('refresh_token')
#         user_id = decode_refresh_token(refresh_token)
#         if not UserToken.objects.filter(
#             user_id=user_id,
#             token=refresh_token,
#             expired_at__gt=datetime.datetime.now(tz=datetime.timezone.utc)
#         ).exists():
#             raise exceptions.AuthenticationFailed('Unauthenticated')

#         access_token = create_access_token(user_id)

#         return JsonResponse({
#             'token': access_token
#             })


class LogoutViewSet(APIView):

    def post(self, request):
        refresh_token = request.COOKIES.get('refresh_token')
        UserToken.objects.filter(
            token=refresh_token
        ).delete()

        response = Response()
        response.delete_cookie(key='refresh_token')
        response.delete_cookie(key='access_token')

        response.data = {
            'message': 'successfully logged out',
        }
        return response


def getFeedbacks(request):
    feedbacks = Feedback.objects.all()
    serializer = FeedbackDetailSerializer(feedbacks, many=True)
    return JsonResponse(serializer.data, safe=False)


class FeedbackViewSet(APIView):

    authentication_classes = [JWTAuthentication]

    def post(self, request, format=None):
        email = request.user
        user = User.objects.get(email=email)
        comment = request.data['comment']
        rating = request.data['rating']
        user_id = user.id
        data = {
            'user_id': user_id,
            'comment': comment,
            'rating': rating
        }
        if Feedback.objects.filter(user_id=user.id).exists():
            feedback = Feedback.objects.get(user_id=user.id)
            serializer = FeedbackSerializer(feedback, data=data)
            if not serializer.is_valid():
                raise exceptions.APIException(serializer.errors)
                # return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)
            serializer.save()
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        else:
            
            serializer = FeedbackSerializer(data=data)
            if not serializer.is_valid():
                raise exceptions.APIException(serializer.errors)
            serializer.save()
            return Response(serializer.data, status=status.HTTP_201_CREATED)



class PaymentViewset(APIView):

    # authentication_classes=[JWTAuthentication]

    def get(self, request, format=None):
        payment = PaymentDetails.objects.all()
        serializer = PaymentSerializer(payment, many=True)
        return Response(serializer.data)

    def post(self, request, format=None):
        # user = User.objects.get(email=request.user)
        request.data['transaction_id'] = str(uuid.uuid1())
        serializer = PaymentSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)


class PaymentDetailsViewset(APIView):
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

    authentication_classes=[JWTAuthentication]

    def get(self, request, format=None):
        user = User.objects.get(email=request.user)
        if user.isAdmin:
            activeBookings = BookingDetails.objects.filter(tourid__start_date__gt=datetime.datetime.utcnow())
            serializer = BookingDetailSerializer(activeBookings, many=True)
            return Response(serializer.data)
        else:
            tours = BookingDetails.objects.filter(userid=user.id)
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


class BookingDetailsViewset(APIView):
    """
    Retrieve, update or delete a snippet instance.
    """

    authentication_classes = [JWTAuthentication]
    # permission_classes = (IsAuthenticated,)


    def get_object(self, pk):
        try:
            return BookingDetails.objects.get(id=pk)
        except BookingDetails.DoesNotExist:
            raise Http404


    def get(self, request, pk, format=None):
        booking = self.get_object(pk)
        serializer = BookingDetailSerializer(booking)
        return Response(serializer.data)

    def put(self, request, pk, format=None):
        # try: 
        booking = self.get_object(pk)
        booking.isCancelled = True
        cancellation_charges = request.data['cancellation_charges']
        reason_for_cancellation = request.data['reason_for_cancellation']
        # cancellation_charges = float(booking.tourid.price) * 0.15
        cancelled = CancellationDetails(bookingid=booking,
                                        refund_status='processing',
                                        cancellation_charges=cancellation_charges,
                                        reason_for_cancellation=reason_for_cancellation
                                        )
        cancelled.save()
        booking.save()
        serializer = BookingDetailSerializer([booking], many=True)
        return Response(serializer.data)


class BookingAdminViewset(APIView):

    authentication_classes=[JWTAuthentication]

    def get(self, request, format=None):
        activeBookings = BookingDetails.objects.filter(tourid__start_date__gt=datetime.datetime.utcnow())
        serializer = BookingDetailSerializer(activeBookings, many=True)
        return Response(serializer.data)
    
class BookingAdminDetailViewset(APIView):

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

    def delete(self, request, pk, format=None):
        booking = self.get_object(pk)
        booking.delete()
        serializer = BookingSerializer(booking)
        return Response(serializer.data)


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



class CancellationList(APIView):
    """
    List all snippets, or create a new snippet.
    """
    def get(self, request):
        cancellations = CancellationDetails.objects.all()
        serializer = CancellationSerializer(cancellations, many=True)
        return Response(serializer.data)

class CancellationDetail(APIView):
    """
    List all snippets, or create a new snippet.
    """

    def get_object(self, pk):
        try:
            return CancellationDetails.objects.get(id=pk)
        except CancellationDetails.DoesNotExist:
            raise Http404
    def get(self, request, pk, format=None):
        cancellation = self.get_object(pk)
        serializer = CancellationSerializer(cancellation)
        return Response(serializer.data)

    def put(self, request, pk, format=None):
        cancellation = self.get_object(pk)
        serializer = CancellationSerializer(cancellation, data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

    def delete(self, request, pk, format=None):
        cancellation = self.get_object(pk)
        cancellation.delete()
        serializer = CancellationSerializer(cancellation)
        return Response(serializer.data)
        