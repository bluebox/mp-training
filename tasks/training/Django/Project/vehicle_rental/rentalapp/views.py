from datetime import datetime
from rest_framework.views import View
from rest_framework import status, exceptions
from rest_framework.response import Response
from django.http.response import JsonResponse, Http404
from rest_framework.views import APIView
from rest_framework.decorators import api_view

from .managers import Owners, Customers, Vehicles, Rents, Booking, Bills, SearchVehicle, AvailableOnDate
from .models import Customer, Owner, Vehicle, OwnerToken, CustomerToken, Bill, RentTrip
from cloudinary import uploader
from django.views.decorators.csrf import csrf_exempt
from django.db.models import Q
import jwt, datetime
from .serializer import OwnerSerializer, CustomerSerializer, VehicleSerializer, BillSerializer, \
    Rent_TripSerializer, Rent_TripDetailSerializer
from .jwt_authentication import JwtAuthentication_owner, JwtAuthentication_customer, \
    create_access_token, \
    create_refresh_token, decode_refresh_token

# # Create your views here.
# # API
class OwnerList(APIView):
    """

    """
    def get(self, request):
        try:
            res= Owners.owner_list()
            return Response(res,status=200)

        except Exception as e:
            return Response(str(e), status=500)

    def post(self, request):
        try:
            response = Owners.owner_registration(request)
            return Response(response, status=200)

        except Exception as e:
            return Response(str(e), status=500)

class OwnerDetail(APIView):
    authentication_classes = [JwtAuthentication_owner]

    def get(self, request):
        try:
            response = Owners.get_owner(request)
            return Response(response, status=200)


        except Exception as e:
            return Response(str(e), status=500)

    def put(self, request):
        try:
            response = Owners.update_owner(request)
            return Response(response, status=200)

        except Exception as e:
            return Response(str(e), status=500)

    def delete(self, request):
        try:
            response = Owners.delete_owner(request)
            return Response(response, status=200)

        except Exception as e:
            return Response(str(e), status=500)


class CustomerList(APIView):
    def get(self, request):
        try:
            response = Customers.customer_list()
            return Response(response, status=200)

        except Exception as e:
            return Response(str(e), status=500)

    def post(selfself, request):
        try:
            response = Customers.customer_registration(request)
            return Response(response, status=200)

        except Exception as e:
            return Response(str(e), status=500)


class CustomerDetail(APIView):
    authentication_classes = [JwtAuthentication_customer]


    def get(self, request):
        try:
            response = Customers.get_customer(request)
            return Response(response, status=200)

        except Exception as e:
            return Response(str(e), status=500)

    def put(self, request):
        try:
            response = Customers.get_customer((request))
            return Response(response, status=200)

        except Exception as e :
            return Response(str(e), status=500)

    def delete(self, request, pk, format=None):
        customer = Customer.objects.get(pk=pk)
        customer.delete()
        return Response(status=status.HTTP_204_NO_CONTENT)


class LoginCustomerJwt(APIView):
    def post(self, request):
        try:
            response = Customers.customer_login(request)
            return response

        except Exception as e:
            return Response(str(e), status=500)


class LoginOwnerJwt(APIView):
    def post(self, request):
        try:
            response = Owners.owner_login(request)
            return response

        except Exception as e:
            return Response(str(e), status=500)



class RefreshJwtTokenOwner(APIView):
    def post(self, request):
        try:
            response = Owners.refresh_token(request)
            return Response(response, status=200)

        except Exception as e:
            return Response(str(e), status=500)



class RefreshJwtTokenCustomer(APIView):
    def post(self, request):
        try:
            response = Customers.refresh_token(request)
            return Response(response, status=200)

        except Exception as e:
            return Response(str(e), status=500)

class LogoutOwnerJwt(APIView):

    def post(self, request):
        try:
            response = Owners.owner_logout(request)
            return response

        except Exception as e:
            return Response(str(e), status=500)


class LogoutCustomerJwt(APIView):
    def post(self, request):
        try:
            response = Customers.customer_logout(request)
            return response

        except Exception as e:
            return Response(str(e), status=500)


class VehicleList(APIView):

    def get(self, request, format=None):
       try :
           response = Vehicles.get_vehicles(request)
           return Response(response, status=200)

       except Exception as e:
           return Response(str(e), status=500)



class UpdateVehiclePrice(APIView):
    def put(self, request, pk ):
        try :
            response = Vehicles.update_price(request, pk)
            return Response({"msg" : response }, status=200)

        except Exception as e:
            return Response(str(e), status=500)



class AddVehicle (APIView) :

    authentication_classes = [JwtAuthentication_owner]

    def post(self, request, format=None):
        try:
            response = Vehicles.add_vehicle(request)
            return Response(response, status=200)

        except Exception as e:
            return Response(str(e), status=500)



class VehicleDetail(APIView):
    authentication_classes = [JwtAuthentication_owner]

    def get(self, request):
        try:
            response = Vehicles.get_owners_vehicle(request)
            return Response(response, status=200)

        except Exception as e:
            return Response(str(e), status=500)


class RentTripList(APIView):
    authentication_classes = [JwtAuthentication_customer]

    def get(self, request):
        try:
            response = Rents.get_customer_trips(request)
            return Response(response, status=200)

        except Exception as e:
            return Response(str(e), status=500)

class Book(APIView):
    authentication_classes = [JwtAuthentication_customer]

    def post(self, request):
        try:
            response = Booking.book(request)
            return Response(response, status=200)

        except Exception as e:
            return Response(str(e), status=500)

class RentTripDetail(APIView):
    def get_object(self, pk):
        try:
            return RentTrip.objects.get(pk=pk)
        except RentTrip.DoesNotExist:
            raise Http404

    def get(self, request, pk):
        trip = self.get_object(pk=pk)
        serializer = Rent_TripSerializer(trip)
        return Response(serializer.data)


class BillList(APIView):
    def get(self, request):
        try:
            response = Bills.get_bill(request)
            return Response(response, status=200)

        except Exception as e:
            return Response(str(e), status=500)

    def post(self, request, id):
        try:
            response = Bills.gerate_bill(request, id)
            return Response(response, status=200)

        except Exception as e:
            return Response(str(e), status=500)


class BillDetail(APIView):
    def get(self, request, id):
        try :
            bill = Bill.objects.get(rental_id_id=id)
            if bill:
                serializer = BillSerializer(bill)
                return Response(serializer.data)
        except :
            return Response({"msg":"No data found"})


class getOrders(APIView):
    authentication_classes = [JwtAuthentication_owner]

    def get(self, request):

        try:
            response = Rents.get_owner_orders(request)
            return Response(response, status=200)

        except Exception as e:
            return Response(str(e), status=500)

class CustomerReview(APIView):
    def put(self, request, pk):
       try:
           response = Rents.customer_review(request, pk)
           return Response(response, status=200)
       except Exception as e:
           return Response(str(e), status=500)

class OwnerReview(APIView):
    def put(self, request, pk):
        try:
            response = Rents.owner_review(request, pk)
            return Response(response, status=200)

        except Exception as e:
            return Response(str(e), status=500)

class DeleteVehicle(View):
    def delete(self, request, numid):
        try:
            response = Vehicles.delete_vehicle(request, numid)
            return Response({"msg": response})

        except :
            return Response({"msg":"No data found"})


class GetVehicle(APIView):
    def get(self,request, id):
        try:
            response = Vehicles.get_vehicle(request, id)
            return Response(response, status=200)

        except Exception as e:
            return Response(str(e), status=500)


class GetVehiclesOwner(APIView):
    def get(self, request, id):
        try:
            response = Owners.get_owner_details(request, id)
            return Response(response, status=200)

        except Exception as e:
            return Response(str(e), status=500)


class Search(APIView):
    def get(self, request):
        try:
            response = SearchVehicle.search(request)
            return Response(response, status=200)
        except Exception as e:
            return Response(str(e), status=500)


class UpdateOrderStatus(APIView):
    def put(self, request, pk):
        try:
            response = Rents.order_status(request, pk)
            return Response(response, status=200)

        except Exception as e:
            return Response(str(e), status=500)


class ChangeVehicleStatus(APIView):
    def put(self, request, pk):
        try:
            response = Vehicles.vehicle_status(request, pk)
            return Response(response, status=200)
        except Exception as e:
            return Response(str(e), status=500)

class AddOdoReading(APIView):
    def put(self, request, pk):
        try :
            response = Rents.odometer_reading(request, pk)
            return Response(response, status=200)
        except Exception as e:
            return Response(str(e), status=500)

class AvailableVehicles(APIView):
    def get(self, request, fromDate, toDate):
        try:
            response = AvailableOnDate.get_vehicles(request, fromDate, toDate)
            return Response(response, status=200)
        except Exception as e:
            return Response(str(e), status=500)


@csrf_exempt
@api_view(['POST'])
def save_file(request):
    url = uploader.upload(request.FILES['img'])
    return JsonResponse({'url': url['secure_url']}, safe=False)