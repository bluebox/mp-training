from django.db.models import Avg
import datetime
from django.http import Http404, JsonResponse

from rest_framework import status, exceptions
from rest_framework.exceptions import APIException
from rest_framework.response import Response
from rest_framework.decorators import authentication_classes
from bookingsapp.Managers.BookingsManager import delete_booking_by_admin, edit_booking_by_admin, get_booking, get_bookings, get_bookings_by_pagination, get_cancellation_by_pagination, post_booking
from bookingsapp.Managers.PaymentManager import edit_payment_by_admin, get_payment_by_id_by_admin, get_payments_by_admin, post_feedback_by_admin
from bookingsapp.Managers.UserManager import delete_user, edit_user, get_all_users_by_admin, get_user, get_users_by_admin_using_pagination, login, logout, register_user_and_get_token
from bookingsapp.models import BookingDetails, CancellationDetails, Feedback, PaymentDetails, User
from bookingsapp.serializers import BookingDetailSerializer, CancellationSerializer, FeedbackDetailSerializer, FeedbackSerializer, UserSerializer
from rest_framework.views import APIView

import uuid
from .JwtAuthentication import JWTAuthentication

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

@authentication_classes([])
class uploadImage(APIView):
    
    def post(self, request):
        try:
            imageUrl = cloudinary.uploader.upload(request.data['file'])
            return Response(imageUrl['secure_url'])
        except Exception as e:
            raise APIException(e)

class uploadVideo(APIView):
    
    def post(self, request):
        try:
            videoUrl = cloudinary.uploader.upload_large(request.data['file'])
            return Response(videoUrl['secure_url'])
        except Exception as e:
            raise APIException(e)


class AllUserList(APIView):
    """
    List all snippets
    """
    def get(self, request):
        try:
            return get_all_users_by_admin(request)
        except Exception as e:
            raise APIException(e)

@authentication_classes([])
class UserList(APIView):
    """
    List all snippets, or create a new snippet.
    """
    def get(self, request):
        try:
            return get_users_by_admin_using_pagination(request)
        except Exception as e:
            raise APIException(e)


    def post(self, request):
        try:
            return register_user_and_get_token(request)
        except Exception as e:
            raise APIException(e)


class UserDetail(APIView):
    """
    Retrieve, update or delete a snippet instance.
    """

    authentication_classes = [JWTAuthentication]
    # permission_classes = (IsAuthenticated,)

    def get(self, request):
        try:
            user = User.objects.get(email=request.user)
            return get_user(request, user)
        except Exception as e:
            raise APIException(e)

    def put(self, request):
        try:
            user = User.objects.get(email=request.user)
            
        except Exception as e:
            raise APIException(e)

    def delete(self, request):
        try:
            user = User.objects.get(email=request.user)
            return delete_user(request, user)
        except Exception as e:
            raise APIException(e)


class addUserByAdmin(APIView):
    
    def post(self, request):
        serializer = UserSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        raise APIException(serializer.errors)
        

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


    def get(self, request, pk=None):
        try:
            user = self.get_object(pk)
            return get_user(request, user)
        except Exception as e:
            raise APIException(e)

    def put(self, request,pk=None):
        try:
            user = self.get_object(pk)
            return edit_user(request, user)
        except Exception as e:
            raise APIException(e)

    def delete(self, request, pk=None):
        try:
            user = self.get_object(pk)
            return delete_user(request, user)
        except Exception as e:
            raise APIException(e)


@authentication_classes([])
class Login(APIView):

    # permission_classes = (AllowAny,)
    def post(self, request):
        try:
            return login(request)
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
            return logout(request)
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

    def post(self, request):
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

    def get(self, request):
        try:
            return get_payments_by_admin(request)
        except Exception as e:
            raise APIException(e)

    def post(self, request):
        try:
            return post_feedback_by_admin(request)
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


    def get(self, request, pk):
        try:
            payment = self.get_object(pk)
            return get_payment_by_id_by_admin(request, payment)
        except Exception as e:
            raise APIException(e)

    def put(self, request, pk):
        try:
            payment = self.get_object(pk)
            return edit_payment_by_admin(request, payment)
        except Exception as e:
            raise APIException(e)


class BookingViewset(APIView):

    authentication_classes=[JWTAuthentication]

    def get(self, request):
        try:
            user = User.objects.get(email=request.user)
            if user.isAdmin:
                bookings = BookingDetails.objects.filter(tourid__start_date__gt=datetime.datetime.utcnow())
                return get_bookings(request, bookings)
            else:
                bookings = BookingDetails.objects.filter(userid=user.id)
                return get_bookings(request, bookings)
            
        except Exception as e:
            raise APIException(e)

    def post(self, request):
        try:
            user = User.objects.get(email=request.user)
            request.data['transaction_id'] = uuid.uuid1()
            request.data['userid'] = user.id
            return post_booking(request)
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


    def get(self, request, pk):
        try:
            booking = self.get_object(pk)
            return get_booking(request, booking)
        except Exception as e:
            raise APIException(e)

    def put(self, request, pk):
        try: 
            booking = self.get_object(pk)
            booking.isCancelled = True
            # cancellation_charges = request.data['cancellation_charges']
            if request.data['reason_for_cancellation']:
                reason_for_cancellation = request.data['reason_for_cancellation']
            cancellation_charges = float(booking.tourid.price) * 0.15
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

    def get(self, request):
        try:
            return get_bookings_by_pagination(request)
        except Exception as e:
            raise APIException(e)
    
class BookingAdminDetailViewset(APIView):

    def get_object(self, pk):
        try:
            return BookingDetails.objects.get(id=pk)
        except BookingDetails.DoesNotExist:
            raise Http404
    def get(self, request, pk):
        try:
            booking = self.get_object(pk)
            return get_booking(request, booking)
        except Exception as e:
            raise APIException(e)

    def put(self, request, pk):
        try:
            booking = self.get_object(pk)
            return edit_booking_by_admin(request, booking)
        except Exception as e:
            raise APIException(e)

    def delete(self, request, pk):
        try:
            booking = self.get_object(pk)
            return delete_booking_by_admin(request, booking)
        except Exception as e:
            raise APIException(e)



class CancellationList(APIView):
    """
    List all snippets, or create a new snippet.
    """
    def get(self, request):
        try:
            return get_cancellation_by_pagination(request)
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
    def get(self, request, pk):
        try:
            cancellation = self.get_object(pk)
            serializer = CancellationSerializer(cancellation)
            return Response(serializer.data)
        except Exception as e:
            raise APIException(e)

    def put(self, request, pk):
        try:
            cancellation = self.get_object(pk)
            serializer = CancellationSerializer(cancellation, data=request.data)
            if serializer.is_valid():
                serializer.save()
                return Response(serializer.data)
            raise APIException(serializer.errors)
        except Exception as e:
            raise APIException(e)

    def delete(self, request, pk):
        try:
            cancellation = self.get_object(pk)
            cancellation.delete()
            serializer = CancellationSerializer(cancellation)
            return Response(serializer.data)
        except Exception as e:
            raise APIException(e)
        