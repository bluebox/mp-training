import datetime
import json

from django.contrib.auth.hashers import make_password
from django.db.models import Avg
from django.http import Http404, JsonResponse

from rest_framework import status, exceptions
from rest_framework.exceptions import APIException
from rest_framework.response import Response
from django.core import serializers
from TourismServer.managers.adminManager import listing

from TourismServer.permissions import AllowAny
from bookingsapp.models import BookingDetails, CancellationDetails, Feedback, PaymentDetails, User, UserToken
from bookingsapp.serializers import BookingDetailSerializer, BookingSerializer, CancellationDetailSerializer, CancellationSerializer, FeedbackDetailSerializer, FeedbackSerializer, PaymentSerializer, UserSerializer
from rest_framework.views import APIView

import uuid

from toursapp.models import Tour
from .JwtAuthentication import JWTAuthentication, create_access_token, create_refresh_token, decode_refresh_token

import cloudinary

def getAverageRatingAndTotalRatings(request):
    if request.method == 'GET':
        try:
            total_ratings = Feedback.objects.all().count()
            average_rating = Feedback.objects.all().aggregate(Avg('rating'))
            return JsonResponse({
                'average_rating': round(average_rating['rating__avg'], 1),
                'total_ratings': total_ratings
            })
        except Exception as e:
            raise APIException(e)

class uploadImage(APIView):
    
    def post(self, request, format=None):
        try:
            imageUrl = cloudinary.uploader.upload(request.data['file'])

            # Build the URL for the image and save it in the variable 'srcURL'
            # srcURL = cloudinary.CloudinaryImage("quickstart_butterfly").build_url()
            return Response(imageUrl['secure_url'])
        except Exception as e:
            raise APIException(e)

class uploadVideo(APIView):
    
    def post(self, request, format=None):
        try:
            videoUrl = cloudinary.uploader.upload_large(request.data['file'])

            # Build the URL for the image and save it in the variable 'srcURL'
            # srcURL = cloudinary.CloudinaryImage("quickstart_butterfly").build_url()
            return Response(videoUrl['secure_url'])
        except Exception as e:
            raise APIException(e)


class AllUserList(APIView):
    """
    List all snippets, or create a new snippet.
    """
    def get(self, request, format=None):
        try:
            user_list = User.objects.all()
            serializer = UserSerializer(user_list, many=True)
            return Response(serializer.data)
        except Exception as e:
            raise APIException(e)


class UserList(APIView):
    """
    List all snippets, or create a new snippet.
    """
    def get(self, request, format=None):
        try:
            text=request.GET.get('text')
            if text != '':
                user_list = (User.objects.filter(name__icontains=text) | 
                                User.objects.filter(email__icontains=text))
            else:
                user_list = User.objects.all()
            users, totalPages, page = listing(request, user_list)
            serializer = UserSerializer(users, many=True)
            return Response({
                'pageItems': serializer.data,
                'totalPages': totalPages,
                'page': page
            })
        except Exception as e:
            raise APIException(e)

    def post(self, request, format=None):
        try:
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
        except Exception as e:
            raise APIException(e)


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
        try:
            user = User.objects.get(email=request.user)
            serializer = UserSerializer(user)
            return Response(serializer.data)
        except Exception as e:
            raise APIException(e)

    def put(self, request, format=None):
        try:
            user = User.objects.get(email=request.user)
            serializer = UserSerializer(user, data=request.data)
            if serializer.is_valid():
                serializer.save()
                return Response(serializer.data)
            raise APIException(serializer.errors)
        except Exception as e:
            raise APIException(e)

    def delete(self, request, format=None):
        try:
            user = User.objects.get(email=request.user)
            user.delete()
            return Response(status=status.HTTP_204_NO_CONTENT)
        except Exception as e:
            raise APIException(e)


class UpdateUserByAdmin(APIView):
    """
    Retrieve, update or delete a snippet instance.
    """

    authentication_classes = [JWTAuthentication]
    # permission_classes = (IsAuthenticated,)


    def get_object(self, pk):
        try:
            return User.objects.get(id=pk)
        except User.DoesNotExist:
            raise Http404


    def get(self, request, pk=None, format=None):
        try:
            user = self.get_object(pk)
            serializer = UserSerializer(user)
            return Response(serializer.data)
        except Exception as e:
            raise APIException(e)

    def put(self, request,pk=None, format=None):
        try:
            user = self.get_object(pk)
            serializer = UserSerializer(user, data=request.data)
            if serializer.is_valid():
                serializer.save()
                return Response(serializer.data)
            raise APIException(serializer.errors)
        except Exception as e:
            raise APIException(e)

    def delete(self, request, pk=None, format=None):
        try:
            user = self.get_object(pk)
            user.delete()
            return Response(status=status.HTTP_204_NO_CONTENT)
        except Exception as e:
            raise APIException(e)



class Login(APIView):

    # permission_classes = (AllowAny,)
    def post(self, request):
        try:
            email = request.data['email']
            password = request.data['password']
            encryptedPassword = make_password(password)
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
        except Exception as e:
            raise APIException(e)


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
        try:
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
        except Exception as e:
            raise APIException(e)


def getFeedbacks(request):
    try:
        feedbacks = Feedback.objects.all()
        serializer = FeedbackDetailSerializer(feedbacks, many=True)
        return JsonResponse(serializer.data, safe=False)
    except Exception as e:
            raise APIException(e)


class FeedbackViewSet(APIView):

    authentication_classes = [JWTAuthentication]

    def post(self, request, format=None):
        try:
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
        except Exception as e:
            raise APIException(e)



class PaymentViewset(APIView):

    # authentication_classes=[JWTAuthentication]

    def get(self, request, format=None):
        try:
            payment = PaymentDetails.objects.all()
            serializer = PaymentSerializer(payment, many=True)
            return Response(serializer.data)
        except Exception as e:
            raise APIException(e)

    def post(self, request, format=None):
        try:
            # user = User.objects.get(email=request.user)
            request.data['transaction_id'] = str(uuid.uuid1())
            serializer = PaymentSerializer(data=request.data)
            if serializer.is_valid():
                serializer.save()
                return Response(serializer.data, status=status.HTTP_201_CREATED)
            raise APIException(serializer.errors)
        except Exception as e:
            raise APIException(e)


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
        try:
            payment = self.get_object(pk)
            serializer = PaymentSerializer(payment)
            return Response(serializer.data)
        except Exception as e:
            raise APIException(e)

    def put(self, request, pk, format=None):
        try:
            payment = self.get_object(pk)
            serializer = PaymentSerializer(payment, data=request.data)
            if serializer.is_valid():
                serializer.save()
                return Response(serializer.data)
            raise APIException(serializer.errors)
        except Exception as e:
            raise APIException(e)


class BookingViewset(APIView):

    authentication_classes=[JWTAuthentication]

    def get(self, request, format=None):
        try:
            user = User.objects.get(email=request.user)
            if user.isAdmin:
                activeBookings = BookingDetails.objects.filter(tourid__start_date__gt=datetime.datetime.utcnow())
                serializer = BookingDetailSerializer(activeBookings, many=True)
                return Response(serializer.data)
            else:
                tours = BookingDetails.objects.filter(userid=user.id)
                serializer = BookingDetailSerializer(tours, many=True)
                return Response(serializer.data)
        except Exception as e:
            raise APIException(e)

    def post(self, request, format=None):
        try:
            user = User.objects.get(email=request.user)
            request.data['transaction_id'] = uuid.uuid1()
            request.data['userid'] = user.id
            serializer = BookingSerializer(data=request.data)
            if serializer.is_valid():
                serializer.save()
                return Response(serializer.data, status=status.HTTP_201_CREATED)
            raise APIException(serializer.errors)
        except Exception as e:
            raise APIException(e)


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
        try:
            booking = self.get_object(pk)
            serializer = BookingDetailSerializer(booking)
            return Response(serializer.data)
        except Exception as e:
            raise APIException(e)

    def put(self, request, pk, format=None):
        try: 
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
        except Exception as e:
            raise APIException(e)


class BookingAdminViewset(APIView):

    authentication_classes=[JWTAuthentication]

    def get(self, request, format=None):
        try:
            text=request.GET.get('text')
            booking_list = (BookingDetails.objects.filter(passenger_details__icontains=text))
            bookings, totalPages, page = listing(request, booking_list)
            serializer = BookingDetailSerializer(bookings, many=True)
            return Response({
                'pageItems': serializer.data,
                'totalPages': totalPages,
                'page': page
            })
        except Exception as e:
            raise APIException(e)
    
class BookingAdminDetailViewset(APIView):

    def get_object(self, pk):
        try:
            return BookingDetails.objects.get(id=pk)
        except BookingDetails.DoesNotExist:
            raise Http404
    def get(self, request, pk, format=None):
        try:
            booking = self.get_object(pk)
            serializer = BookingSerializer(booking)
            return Response(serializer.data)
        except Exception as e:
            raise APIException(e)

    def put(self, request, pk, format=None):
        try:
            booking = self.get_object(pk)
            serializer = BookingSerializer(booking, data=request.data)
            if serializer.is_valid():
                serializer.save()
                return Response(serializer.data)
            raise APIException(serializer.errors)
        except Exception as e:
            raise APIException(e)

    def delete(self, request, pk, format=None):
        try:
            booking = self.get_object(pk)
            booking.delete()
            serializer = BookingSerializer(booking)
            return Response(serializer.data)
        except Exception as e:
            raise APIException(e)


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
        try:
            booking = self.get_object(pk)
            serializer = BookingSerializer(booking)
            return Response(serializer.data)
        except Exception as e:
            raise APIException(e)

    def put(self, request, pk, format=None):
        try:
            booking = self.get_object(pk)
            serializer = BookingSerializer(booking, data=request.data)
            if serializer.is_valid():
                serializer.save()
                return Response(serializer.data)
            raise APIException(serializer.errors)
        except Exception as e:
            raise APIException(e)



class CancellationList(APIView):
    """
    List all snippets, or create a new snippet.
    """
    def get(self, request):
        try:
            text = request.GET.get('text')
            cancellation_list = CancellationDetails.objects.filter(refund_status__contains=text)
            items, totalPages, page = listing(request, cancellation_list)
            serializer = CancellationSerializer(items, many=True)
            return Response({
                'pageItems': serializer.data,
                'totalPages': totalPages,
                'page': page
            })
        except Exception as e:
            raise APIException(e)


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
        try:
            cancellation = self.get_object(pk)
            serializer = CancellationSerializer(cancellation)
            return Response(serializer.data)
        except Exception as e:
            raise APIException(e)

    def put(self, request, pk, format=None):
        try:
            cancellation = self.get_object(pk)
            serializer = CancellationSerializer(cancellation, data=request.data)
            if serializer.is_valid():
                serializer.save()
                return Response(serializer.data)
            raise APIException(serializer.errors)
        except Exception as e:
            raise APIException(e)

    def delete(self, request, pk, format=None):
        try:
            cancellation = self.get_object(pk)
            cancellation.delete()
            serializer = CancellationSerializer(cancellation)
            return Response(serializer.data)
        except Exception as e:
            raise APIException(e)
        