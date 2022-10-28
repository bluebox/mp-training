from datetime import datetime
import json
from email.mime import image
from datetime import date
from django.db.models import Subquery
from django.shortcuts import render, redirect, get_object_or_404
from django.http import HttpResponse
from rest_framework.views import View
from django.contrib import messages
from rest_framework import status, exceptions
from rest_framework.response import Response
from django.http.response import JsonResponse, Http404
from django.core.files.storage import default_storage
from rest_framework.views import APIView
from rest_framework.decorators import api_view
from .models import Customer, Owner, Vehicle, OwnerToken, CustomerToken, Bill, Rent_Trip
from cloudinary import uploader
from django.views.decorators.csrf import csrf_exempt
from django.db.models import Q
from rest_framework.exceptions import AuthenticationFailed
from . import serializer
import jwt, datetime
from .serializer import OwnerSerializer, CustomerSerializer, VehicleSerializer, BillSerializer, \
    Rent_TripSerializer
from .jwt_authentication import JwtAuthentication_owner, JwtAuthentication_customer, create_access_token, \
    create_refresh_token, decode_refresh_token



# # Create your views here.

# # API
class OwnerList(APIView):
    def get(self, request, format=None):
        owner = Owner.objects.all()
        serializer = OwnerSerializer(owner, many=True)
        return Response(serializer.data)

    def post(self, request, format=None):
        serializer = OwnerSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)


class OwnerDetail(APIView):
    authentication_classes = [JwtAuthentication_owner]

    # def get_object(self, pk):
    #     try:
    #         return Owner.objects.get(pk=pk)
    #     except Owner.DoesNotExist:
    #         raise Http404

    def get(self, request, format=None):
        owner = Owner.objects.get(email=request.user)
        serializer = OwnerSerializer(owner)
        return Response(serializer.data)

    def put(self, request, format=None):
        owner = Owner.objects.get(email=request.user)
        serializer = OwnerSerializer(owner, data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

    def delete(self, request,format=None):
        owner = Owner.objects.get(email=request.user)
        owner.delete()
        return Response(status=status.HTTP_204_NO_CONTENT)


class CustomerList(APIView):
    def get(self, request, format=None):
        customer = Customer.objects.all()
        serializer = CustomerSerializer(customer, many=True)
        return Response(serializer.data)

    def post(selfself, request, format=None):
        serializer = CustomerSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)


class CustomerDetail(APIView):
    authentication_classes = [JwtAuthentication_customer]

    # def get_object(self, pk):
    #     try:
    #        return Customer.objects.get(pk=pk)
    #     except Customer.DoesNotExist :
    #         raise Http404

    def get(self, request, format=None):
        customer = Customer.objects.get(email=request.user)
        serializer = CustomerSerializer(customer)
        return Response(serializer.data)

    def put(self, request, format=None):
        customer = Customer.objects.get(email=request.user)
        serializer = CustomerSerializer(customer, data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

    def delete(self, request, pk, format=None):
        customer = Customer.objects.get(pk=pk)
        customer.delete()
        return Response(status=status.HTTP_204_NO_CONTENT)


class LoginCustomerJwt(APIView):
    def post(self, request):
        email = request.data['email']
        password = request.data['password']
        if Customer.objects.filter(email=email).exists():
            customer = Customer.objects.get(email=email)
            if customer.password != password:
                raise exceptions.APIException('Wrong Password!')

        else:
            raise exceptions.APIException("email doesn't exist, Try again")

        customer = Customer.objects.get(email=email, password=password)

        access_token = create_access_token(customer.customer_id)
        refresh_token = create_refresh_token(customer.customer_id)

        CustomerToken.objects.create(
            customer_token_id=customer.customer_id,
            token=refresh_token,
            expired_at=datetime.datetime.utcnow() + datetime.timedelta(days=5)
        )
        response = Response()
        response.set_cookie(key='refresh_token', value=refresh_token, httponly=False)
        response.data = {
            'access_token': access_token,
            # 'customer_id' : customer.customer_id

        }
        return response


class LoginOwnerJwt(APIView):
    def post(self, request):
        email = request.data['email']
        password = request.data['password']
        if Owner.objects.filter(email=email).exists():
            owner = Owner.objects.get(email=email)
            if owner.password != password:
                raise exceptions.APIException('Wrong Password!')

        else:
            raise exceptions.APIException("email doesn't exist, Try again")

        owner = Owner.objects.get(email=email, password=password)

        access_token = create_access_token(owner.owner_id)
        refresh_token = create_refresh_token(owner.owner_id)

        OwnerToken.objects.create(
            owner_token_id=owner.owner_id,
            token=refresh_token,
            expired_at=datetime.datetime.utcnow() + datetime.timedelta(days=5)
        )
        response = Response()
        response.set_cookie(key='refresh_token', value=refresh_token, httponly=False)
        response.data = {
            'access_token': access_token,
            # 'owner' : owner.owner_id
        }
        return response


class RefreshJwtTokenOwner(APIView):

    def post(self, request):
        refresh_token = request.COOKIES.get('refresh_token')
        token_id = decode_refresh_token(refresh_token)
        if not OwnerToken.objects.filter(
                owner_token_id=token_id,
                token=refresh_token,
                expired_at__gt=datetime.datetime.now(tz=datetime.timezone.utc)
        ).exists():
            raise exceptions.AuthenticationFailed('Unauthenticated')

        access_token = create_access_token(token_id)

        return JsonResponse({
            'token': access_token
        })


class RefreshJwtTokenCustomer(APIView):

    def post(self, request):
        refresh_token = request.COOKIES.get('refresh_token')
        token_id = decode_refresh_token(refresh_token)
        if not CustomerToken.objects.filter(
                customer_token_id=token_id,
                token=refresh_token,
                expired_at__gt=datetime.datetime.now(tz=datetime.timezone.utc)
        ).exists():
            raise exceptions.AuthenticationFailed('Unauthenticated')

        access_token = create_access_token(token_id)

        return JsonResponse({
            'token': access_token
        })


class LogoutOwnerJwt(APIView):

    def post(self, request):

        refresh_token = request.COOKIES.get('refresh_token')
        CustomerToken.objects.filter(
            token=refresh_token
        ).delete()

        response = Response()
        response.delete_cookie(key='refresh_token')

        response.data = {
            'message': 'Logged out Successfully',
        }
        return response


class LogoutCustomerJwt(APIView):

    def post(self, request):
        refresh_token = request.COOKIES.get('refresh_token')
        CustomerToken.objects.filter(
            token=refresh_token
        ).delete()

        response = Response()
        response.delete_cookie(key='refresh_token')

        response.data = {
            'message': 'Logged out Successfully',
        }
        return response


class VehicleList(APIView):

    def get(self, request, format=None):
        vehicle = Vehicle.objects.filter(is_available=True)
        serializer = VehicleSerializer(vehicle, many=True)
        return Response(serializer.data)


class UpdateVehiclePrice(APIView):
    def put(self, request, pk ):
        vehicle = Vehicle.objects.get(vehicle_no=pk)
        vehicle.price_day = request.data['price_day']
        vehicle.save()
        # serializer = VehicleSerializer([vehicle], mant=True)
        # if serializer.is_valid():
        #     serializer.save()
        return Response({"msg" : "updated successfully"})
        # return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

class AddVehicle (APIView) :

    authentication_classes = [JwtAuthentication_owner]

    def post(self, request, format=None):

        owner = Owner.objects.get(email=request.user)

        print(owner.owner_id)
        owner_id = owner.owner_id
        vehicle_no = request.data.get('vehicle_no')
        type = request.data.get('type')
        brand = request.data.get('brand')
        model = request.data.get('model')
        image = request.data.get('image')
        price_day = request.data.get('price_day')


        data = {
            "owner_id" : owner_id,
            "vehicle_no" : vehicle_no,
            "type" :  type,
            "brand" : brand,
            "model" : model ,
            "image" : image,
            "price_day" :price_day
        }

        serializer = VehicleSerializer(data={
            "owner_id": owner_id,
            "vehicle_no": vehicle_no,
            "type": type,
            "brand": brand,
            "model": model,
            "image": image,
            "price_day": price_day
        })

        if serializer.is_valid():
            serializer.save()
            return Response({'message': 'Your Vehicle has beemn Added'})
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

class VehicleDetail(APIView):
    authentication_classes = [JwtAuthentication_owner]
    # def get_object(self, pk):
    #     try:
    #         return Vehicle.objects.get(pk=pk)
    #     except Vehicle.DoesNotExist:
    #         raise Http404

    def get(self, request, format=None):
        owner = Owner.objects.get(email=request.user)
        vehicles = Vehicle.objects.filter(owner_id=owner.owner_id)
        serializer = VehicleSerializer(vehicles, many=True)
        return Response(serializer.data)

    def put(self, request,format=None):
        vehicle = Vehicle.objects.filter(owner_id=request.user)
        serializer = VehicleSerializer(vehicle, data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

    def delete(self, request, format=None):
        vehicle = Vehicle.object.get(request)
        vehicle.delete()
        return Response({"message": "success"}, safe=False, status=status.HTTP_204_NO_CONTENT)




class Rent_TripList(APIView):
    authentication_classes = [JwtAuthentication_customer]

    def get(self, request):
        customer = Customer.objects.get(email=request.user)
        customer_id = customer.customer_id

        rent_trip = Rent_Trip.objects.filter(customer_id=customer_id).order_by('-rent_id')

        users, totalPages, page = listing(request, rent_trip)

        serializer = Rent_TripSerializer(users, many=True)
        return Response({
            'pageItems': serializer.data,
            'totalPages': totalPages,
            'page': page
        })

class Book(APIView):
    authentication_classes = [JwtAuthentication_customer]

    def post(self, request):
        customer = Customer.objects.get(email=request.user)
        customer_id = customer.customer_id
        vehicle_no = request.data.get('vehicle_no')

        # pick = request.data['pickup_date'
        # ret = request.data.get['return_date']

        # pick = datetime.date(request.data.get('pickup_date'))
        # pick = datetime.datetime.strptime(request.data['pickup_date'], '%Y-%d-%m')
        # # try:
        # pick2 = datetime.datetime.strptime(request.data['return_date'], '%Y-%d-%m')
        # except :
        #     print('err')

        #
        # date = Rent_Trip.objects.filter( pickup_date__lte= request.data.get('pickup_date'),
        #                                  pickup_date__gte= request.data.get('return_date'),
        #                                  vehicle_no=vehicle_no)

        # date = Rent_Trip.objects.filter(vehicle_no=vehicle_no)

        # vehicle = Rent_Trip.objects.filter(vehicle_no=vehicle_obj)
        rent = Rent_Trip.objects.filter(pickup_date__range=[request.data.get('pickup_date'),
                                                            request.data.get('return_date')],
                                        vehicle_no=vehicle_no).exists()
        if (rent):
            return Response({"message": 'Not available on this date please Choose different date'})
        else:
            owner_id = request.data.get('owner_id')
            pickup_date = request.data.get('pickup_date')
            return_date = request.data.get('return_date')
            request.data['customer_id'] = customer.customer_id
            data = {
                'customer_id': customer_id,
                'vehicle_no': vehicle_no,
                'owner_id': owner_id,
                'pickup_date': pickup_date,
                'return_date': return_date
            }

            serializer = Rent_TripSerializer(data={
                'customer_id': customer_id,
                'vehicle_no': vehicle_no,
                'owner_id': owner_id,
                'pickup_date': pickup_date,
                'return_date': return_date
            })
            if serializer.is_valid():
                serializer.save()
                days =  no_of_days(pickup_date, return_date)
                vehicle = Vehicle.objects.get(vehicle_no=vehicle_no)
                amount = days * vehicle.price_day
                return Response({'message':'Booked Successfully', 'amount': amount, 'days':days, 'id' : serializer.data['rent_id'] })
            return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST )


class Rent_TripDetail(APIView):
    def get_object(self, pk):
        try:
            return Rent_Trip.objects.get(pk=pk)
        except Rent_Trip.DoesNotExist:
            raise Http404

    def get(self, request, pk, format=None):
        trip = self.get_object(pk=pk)
        serializer = Rent_TripSerializer(trip)
        return Response(serializer.data)

    def put(self, request, pk, format=None):
        rent_trip = self.get_object(pk=pk)
        rent_trip.customer_review = request.data['customer_review']
        rent_trip.save()
        serializer = Rent_TripSerializer([rent_trip], many=True)
        return Response(serializer.data)

    def delete(self, request, pk):
        rent_trip = self.get_object(pk=pk)
        rent_trip.delete()
        return Response({"message" : "Order Cancelled"})


class BillList(APIView):
    def get(self, request, format=None):
        bill = Bill.objects.all()
        print(bill)
        serializer = BillSerializer(bill, many=True)
        return Response(serializer.data)

    def post(self,request, id):

        trip = Rent_Trip.objects.get(rent_id=id)
        bill = Bill.objects.filter(rental_id=id).exists()

        if(bill):
            return Response({"message": "Bill already exist"})
        else:
            rent_id = id
            pickup_date = trip.pickup_date
            return_date = trip.return_date

            rental_days = no_of_days(pickup_date, return_date)
            vehicle = Vehicle.objects.get(vehicle_no=trip.vehicle_no)
            amount = rental_days * vehicle.price_day

            serializer = BillSerializer(data={
                "rental_id": rent_id,
                "rental_days": rental_days,
                "amount": amount,

                })
            if serializer.is_valid():
                serializer.save()

                trip.bill_generated = True
                trip.save()
                return Response({'message':'Bill generated successfully'})


        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)


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
        owner = Owner.objects.get(email=request.user)
        owner_id = owner.owner_id

        rent_trip = Rent_Trip.objects.filter(owner_id=owner_id).order_by('-rent_id')

        orders, totalPages, page = listing(request, rent_trip)

        serializer = Rent_TripSerializer(orders, many=True)

        return Response({
            'pageItems': serializer.data,
            'totalPages': totalPages,
            'page': page
        })



class CustomerReview(APIView):
    def put(self, request, pk, format=None):
        rent_trip = Rent_Trip.objects.get(pk=pk)
        rent_trip.customer_review = request.data['customer_review']
        rent_trip.save()
        serializer = Rent_TripSerializer([rent_trip], many=True)


        return Response(serializer.data)

class OwnerReview(APIView):
    def put(self, request, pk, format=None):
        rent_trip = Rent_Trip.objects.get(pk=pk)
        rent_trip.owner_review= request.data['owner_review']
        rent_trip.save()
        serializer = Rent_TripSerializer([rent_trip], many=True)

        return Response(serializer.data)


class DeleteVehicle(View):
    def delete(self, request, numid):
        vehicle = Vehicle.objects.get(vehicle_no=numid)
        vehicle.delete()
        return JsonResponse({"message": "success"}, safe=False)
        # serializer = VehicleSerializer(vehicle)

class GetBookingId(APIView):
    def get(self, request, id):
        booking_id = Rent_Trip.objects.filter(customer_id=id).order_by('-check_in').first
        serializer = Rent_TripSerializer(booking_id)
        print(serializer)
        return JsonResponse(serializer.data)

def no_of_days(pickup_date, return_date):
    date_format = "%Y-%m-%d"
    a = datetime.datetime.strptime(str(pickup_date), date_format)
    b = datetime.datetime.strptime(str(return_date), date_format)
    delta = b - a
    return delta.days+1

class GetVehicle(APIView):
    def get(self,request, id):
        vehicle = Vehicle.objects.get(vehicle_no=id)
        serializer = VehicleSerializer(vehicle)
        return Response(serializer.data)

class GetVehiclesOwner(APIView):
    def get(self,request, id):
        vehicle = Vehicle.objects.get(vehicle_no=id)
        owner = Owner.objects.get(owner_id = vehicle.owner_id_id)
        serializer = OwnerSerializer(owner)
        return Response(serializer.data)

class Search(APIView):
    def get(self, request):
        vehicicles = Vehicle.objects.filter(Q(model__icontains = request.GET.get('model')) |
                                            Q(brand__icontains = request.GET.get('model')) )
        serializer =  VehicleSerializer(vehicicles, many=True)
        if serializer:
            return  Response(serializer.data)
        # else :
        #      return Response({"message" : "No results found"})

class UpdateOrderStatus(APIView):
    def put(self, request, pk):
        trip = Rent_Trip.objects.get(rent_id = pk)
        trip.order_status = False

        trip.save()
        return  Response({"msg" : "Order cancelled"})

class ChangeVehicleStatus(APIView):
    def put(self, request, pk):
        vehicle = Vehicle.objects.get(vehicle_no = pk)

        if vehicle.is_available == True:
            vehicle.is_available = False
            vehicle.save()
            return Response({"msg": "Vehicle dissabled"})

        else:
            vehicle.is_available = True
            vehicle.save()
            return Response({"msg": "Vehicle enabled"})

class AddOdoReading(APIView):
    def put(self, request, pk):
        trip = Rent_Trip.objects.get(rent_id=pk)
        trip.odo_start_reading = request.data['odo_start_reading']
        trip.odo_end_reading = request.data['odo_end_reading']
        trip.save()

        bill = Bill.objects.get(rental_id_id = pk)
        bill.km_ran = int(trip.odo_end_reading) - int(trip.odo_start_reading)
        bill.save()

        return Response({"msg" : "data saved successfully"})

from django.core.paginator import Paginator

def listing(request, object_list):
    paginator = Paginator(object_list, 3)
    page_number = int(request.GET.get('page'))
    if not page_number:
        page_number = 1
    if page_number > paginator.num_pages:
        page_number = paginator.num_pages
    elif page_number < 1:
        page_number = 1
    page_obj = paginator.get_page(page_number)
    return page_obj, paginator.num_pages, page_number


@csrf_exempt
@api_view(['POST'])
def save_file(request):
    # file = request.FILES["img"]
    url = uploader.upload(request.FILES['img'])
    # file_name = default_storage.save(file.name,file)
    return JsonResponse({'url': url['secure_url']}, safe=False)

#
# def c_login(request):
#         data = json.loads(request.body.decode('utf-8'))
#         email = data['email']
#         password = data['password']
#         print(password)
#         customer = Customer.objects.get(email=email)
#
#         serializer= CustomerSerializer(customer)
#         if customer is None:
#             return JsonResponse({'mes': 'enter valid email'})
#
#         elif customer.password == password :
#             return JsonResponse(serializer.data)
#
#         else:
#             return JsonResponse({'mes': 'invalid password'})
#
#
#
# def o_login(request):
#     data = json.loads(request.body.decode('utf-8'))
#     email = data['email']
#     password = data['password']
#     print(password)
#
#     owner = Owner.objects.get(email=email)
#
#     serializer = OwnerSerializer(owner)
#     if owner is None:
#         return JsonResponse({'mes': 'enter valid email'})
#
#     elif owner.password == password:
#         return JsonResponse(serializer.data)
#
#     else:
#         return JsonResponse({'mes': 'invalid password'})



