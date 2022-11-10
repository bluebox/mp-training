from datetime import datetime

from django.core.paginator import Paginator
from rest_framework.views import View
from rest_framework import status, exceptions
from rest_framework.response import Response
from django.http.response import JsonResponse, Http404
from rest_framework.views import APIView
from rest_framework.decorators import api_view
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

class Owners() :

    @staticmethod
    def owner_list():
        owner = Owner.objects.all()
        serializer = OwnerSerializer(owner, many=True)
        return serializer.data

    @staticmethod
    def owner_registration(request):
        serializer = OwnerSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return serializer.data

        else:
            raise exceptions.APIException(serializer.errors)

    @staticmethod
    def get_owner(request):
        owner = Owner.objects.get(email=request.user)
        serializer = OwnerSerializer(owner)
        return serializer.data

    @staticmethod
    def update_owner(request):
        owner = Owner.objects.get(email=request.user)
        serializer = OwnerSerializer(owner, data=request.data)
        if serializer.is_valid():
            serializer.save()
            return serializer.data

    @staticmethod
    def delete_owner(request):
        owner = Owner.objects.get(email=request.user)
        owner.delete()
        msg = "successfully deleted"
        return msg

    @staticmethod
    def get_owner_details(request, id):
        vehicle = Vehicle.objects.get(vehicle_no=id)
        owner = Owner.objects.get(owner_id=vehicle.owner_id_id)
        serializer = OwnerSerializer(owner)
        return serializer.data

    @staticmethod
    def owner_login(request):
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
        }
        return response

    @staticmethod
    def refresh_token(request):
        refresh_token = request.COOKIES.get('refresh_token')
        token_id = decode_refresh_token(refresh_token)
        if not OwnerToken.objects.filter(
                owner_token_id=token_id,
                token=refresh_token,
                expired_at__gt=datetime.datetime.now(tz=datetime.timezone.utc)
        ).exists():
            raise exceptions.AuthenticationFailed('Unauthenticated')

        access_token = create_access_token(token_id)

        return access_token

    @staticmethod
    def owner_logout(request):
        refresh_token = request.COOKIES.get('refresh_token')
        OwnerToken.objects.filter(
            token=refresh_token
        ).delete()

        response = Response()
        response.delete_cookie(key='refresh_token')

        response.data = {
            'message': 'Logged out Successfully',
        }
        return response

class Customers():
    @staticmethod
    def customer_list():
        customer = Customer.objects.all()
        serializer = CustomerSerializer(customer, many=True)
        return serializer.data

    @staticmethod
    def customer_registration(request):
        serializer = CustomerSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return serializer.data

        else:
            raise exceptions.APIException(serializer.errors)

    @staticmethod
    def get_customer(request):
        customer = Customer.objects.get(email=request.user)
        serializer = CustomerSerializer(customer)
        return serializer.data

    @staticmethod
    def update_customer(request):
        customer = Customer.objects.get(email=request.user)
        serializer = CustomerSerializer(customer, data=request.data)
        if serializer.is_valid():
            serializer.save()
            return serializer.data

    @staticmethod
    def customer_login(request):
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
        }
        return response

    @staticmethod
    def refresh_token(request):
        refresh_token = request.COOKIES.get('refresh_token')
        token_id = decode_refresh_token(refresh_token)
        if not CustomerToken.objects.filter(
                customer_token_id=token_id,
                token=refresh_token,
                expired_at__gt=datetime.datetime.now(tz=datetime.timezone.utc)
        ).exists():
            raise exceptions.AuthenticationFailed('Unauthenticated')

        access_token = create_access_token(token_id)

        return access_token

    @staticmethod
    def customer_logout(request):
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

class Vehicles():
    @staticmethod
    def get_vehicles(request):
        vehicle = Vehicle.objects.filter(is_available=True)
        serializer = VehicleSerializer(vehicle, many=True)
        return serializer.data

    @staticmethod
    def update_price(request,pk):
        vehicle = Vehicle.objects.get(vehicle_no=pk)
        vehicle.price_day = request.data['price_day']
        vehicle.save()
        msg = 'updated successfully'
        return msg

    @staticmethod
    def add_vehicle(request):
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
            "owner_id": owner_id,
            "vehicle_no": vehicle_no,
            "type": type,
            "brand": brand,
            "model": model,
            "image": image,
            "price_day": price_day
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
            msg = 'updated successfully'
            return msg

        else :
            raise exceptions.APIException(serializer.errors)

    @staticmethod
    def delete_vehicle(request, numid):
        vehicle = Vehicle.objects.get(vehicle_no=numid)
        vehicle.delete()
        msg = 'deleted successfully'
        return msg

    @staticmethod
    def get_owners_vehicle(request):
        owner = Owner.objects.get(email=request.user)
        vehicles = Vehicle.objects.filter(owner_id=owner.owner_id)
        serializer = VehicleSerializer(vehicles, many=True)
        return serializer.data

    @staticmethod
    def vehicle_status(request, pk):
        vehicle = Vehicle.objects.get(vehicle_no=pk)

        if vehicle.is_available == True:
            vehicle.is_available = False
            vehicle.save()
            msg= "Vehicle disabled"
            return msg

        else:
            vehicle.is_available = True
            vehicle.save()
            msg= "Vehicle enabled"
            return msg

    @staticmethod
    def get_vehicle(request, id):
        vehicle = Vehicle.objects.get(vehicle_no=id)
        serializer = VehicleSerializer(vehicle)
        return serializer.data

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

class Rents():
    @staticmethod
    def get_customer_trips(request):
        customer = Customer.objects.get(email=request.user)
        customer_id = customer.customer_id

        rent_trip = RentTrip.objects.filter(customer_id=customer_id).order_by('-rent_id')

        users, totalPages, page = listing(request, rent_trip)

        serializer = Rent_TripSerializer(users, many=True)
        response = ({
            'pageItems': serializer.data,
            'totalPages': totalPages,
            'page': page
        })
        return response

    @staticmethod
    def get_owner_orders(request):
        owner = Owner.objects.get(email=request.user)
        owner_id = owner.owner_id

        rent_trip = RentTrip.objects.filter(owner_id=owner_id).order_by('-rent_id')

        users, totalPages, page = listing(request, rent_trip)

        serializer = Rent_TripSerializer(users, many=True)
        response = ({
            'pageItems': serializer.data,
            'totalPages': totalPages,
            'page': page
        })
        return response

    @staticmethod
    def customer_review(request, pk):
        rent_trip = RentTrip.objects.get(pk=pk)
        rent_trip.customer_review = request.data['customer_review']
        rent_trip.save()
        serializer = Rent_TripSerializer([rent_trip], many=True)
        return serializer.data

    @staticmethod
    def owner_review(request, pk):
        rent_trip = RentTrip.objects.get(pk=pk)
        rent_trip.owner_review = request.data['owner_review']
        rent_trip.save()
        serializer = Rent_TripSerializer([rent_trip], many=True)
        return serializer.data

    @staticmethod
    def order_status(request, pk):
        trip = RentTrip.objects.get(rent_id = pk)
        trip.order_status = False

        trip.save()
        return  Response({"msg" : "Order cancelled"})

    @staticmethod
    def odometer_reading(request, pk):
        trip = RentTrip.objects.get(rent_id=pk)
        trip.odo_start_reading = request.data['odo_start_reading']
        trip.odo_end_reading = request.data['odo_end_reading']
        trip.save()

        bill = Bill.objects.get(rental_id_id=pk)
        bill.km_ran = int(trip.odo_end_reading) - int(trip.odo_start_reading)
        bill.save()
        msg: "data saved successfully"
        return msg

class Booking():

    @staticmethod
    def book(request):
        customer = Customer.objects.get(email=request.user)
        customer_id = customer.customer_id
        vehicle_no = request.data.get('vehicle_no')

        rent = RentTrip.objects.filter(Q(pickup_date__lte=request.data.get('pickup_date')) |
                                       Q(pickup_date__lte=request.data.get('return_date')),
                                       Q(return_date__gte=request.data.get('pickup_date')) |
                                       Q(return_date__gte=request.data.get('return_date')),
                                       vehicle_no=vehicle_no).exists()

        if (rent):
            details = {'message': 'Not available on this date please Choose different date'}
            return  details
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

            serializer = Rent_TripDetailSerializer(data={
                'customer_id': customer_id,
                'vehicle_no': vehicle_no,
                'owner_id': owner_id,
                'pickup_date': pickup_date,
                'return_date': return_date
            })
            if serializer.is_valid():
                serializer.save()
                days = no_of_days(pickup_date, return_date)
                vehicle = Vehicle.objects.get(vehicle_no=vehicle_no)
                amount = days * vehicle.price_day
                details = {'message': 'Booked Successfully',
                                 'amount': amount,
                                 'days': days,
                                 'id': serializer.data['rent_id']}
                return details
            return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

def no_of_days(pickup_date, return_date):
    date_format = "%Y-%m-%d"
    a = datetime.datetime.strptime(str(pickup_date), date_format)
    b = datetime.datetime.strptime(str(return_date), date_format)
    delta = b - a
    return delta.days+1

class Bills():
    @staticmethod
    def get_bill(request):
        bill = Bill.objects.all()
        print(bill)
        serializer = BillSerializer(bill, many=True)
        return serializer.data

    @staticmethod
    def gerate_bill(request, id):
        trip = RentTrip.objects.get(rent_id=id)
        bill = Bill.objects.filter(rental_id=id).exists()

        if (bill):
            return {"message": "Bill already exist"}
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
                return {"message": 'Bill generated successfully'}

        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

class SearchVehicle():
    @staticmethod
    def search(request):
        vehicicles = Vehicle.objects.filter(Q(model__icontains=request.GET.get('model')) |
                                            Q(brand__icontains=request.GET.get('model')))
        serializer = VehicleSerializer(vehicicles, many=True)
        if serializer:
            return serializer.data

class AvailableOnDate():
    @staticmethod
    def get_vehicles(request,  fromDate, toDate):
        fromDate = fromDate
        toDate = toDate

        rent = RentTrip.objects.filter(Q(pickup_date__lte=fromDate) |
                                       Q(pickup_date__lte=toDate),
                                       Q(return_date__gte=fromDate) |
                                       Q(return_date__gte=toDate))
        vehicle = set()
        for veh in rent:
            vehicle.add(veh.vehicle_no)

        print(vehicle)
        filtered_vehicles = Vehicle.objects.exclude(vehicle_no__in=vehicle, is_available=True )
        serializer = VehicleSerializer(filtered_vehicles, many=True)
        return serializer.data
