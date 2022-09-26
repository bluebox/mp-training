from email.mime import image
from django.shortcuts import render, redirect, get_object_or_404
from django.http import HttpResponse
from django.contrib import messages
from rest_framework import status, viewsets
from rest_framework.response import Response
from django.http.response import JsonResponse, Http404
from django.core.files.storage import default_storage
from rest_framework.views import APIView
from rest_framework.decorators import api_view
from .models import Customer, Owner, Vehicle, Vehicle_status, Bill, Rent_Trip
from django.views.decorators.csrf import csrf_exempt
from . import serializer
from .serializer import OwnerSerializer, CustomerSerializer, VehicleSerializer, VehicleStatusSerializer, BillSerializer, Rent_TripSerializer

#
# # Create your views here.
# def home(request):
#     return render(request, 'home.html' )
#
# def customer_login(request):
#     return render(request, 'customer_login.html')
#
# def owner_login(request):
#     return render(request, 'owner_login.html')
#
# def customer_register(request):
#     return render(request, 'customer_register.html')
#
# def owner_register(request):
#     return render(request, 'owner_register.html')
#
# def c_login(request):
#     if request.method == "POST" :
#         email = request.POST['email']
#         dl = request.POST['password']
#
#         user = Customer.objects.all().values()
#
#         for i in user:
#             if i['email']==email and i['dl_no']==dl :
#                 return HttpResponse("No vehicles yet")
#
#         else:
#             messages.error(request,"invalid mail or password")
#             return redirect('customer_login')
#
#
# owner_name =""
# def o_login(request):
#     if request.method == "POST":
#         email = request.POST['email']
#         contact = request.POST['password']
#
#         owner= Owner.objects.all().values()
#
#         for i in owner:
#
#             if i['email'] == email and i['contact_no'] == contact :
#
#                 return render(request, 'add_vehicle.html',{ 'name':i["name"], 'id':i['owner_id']})
#
#         else:
#             messages.error(request, "invalid email or password")
#             return redirect('owner_login')
#
# # API
class OwnerList(APIView):
    def get(self, request, format=None):
        owner = Owner.objects.all()
        serializer = OwnerSerializer(owner, many=True)
        return Response(serializer.data)

    def post(self, request, format=None):
        serializer = OwnerSerializer(data = request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

class OwnerDetail(APIView):
    def get_object(self, pk):
        try:
            return Owner.objects.get(pk=pk)
        except Owner.DoesNotExist:
            raise Http404

    def get(self, request, pk, format=None):
        owner = self.get_object(pk=pk)
        serializer = OwnerSerializer(owner)
        return Response(serializer.data)

    def put(self, request, pk, format=None):
        owner = self.get_object(pk=pk)
        serializer = OwnerSerializer(owner, data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

    def delete(self, request, pk, format=None):
        owner = self.get_object(pk=pk)
        owner.delete()
        return Response(status=status.HTTP_204_NO_CONTENT)


class CustomerList(APIView):
    def get(self, request,format=None):
        customer = Customer.objects.all()
        serializer = CustomerSerializer(customer, many=True)
        return Response(serializer.data)

    def post(selfself, request, format=None):
        serializer = CustomerSerializer(data = request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

class CustomerDetail(APIView):
    def get_object(self, pk):
        try:
           return Customer.objects.get(pk=pk)
        except Customer.DoesNotExist :
            raise Http404

    def get(self,request, pk, format=None):
        customer = self.get_object(pk=pk)
        serializer = CustomerSerializer(customer)
        return Response(serializer.data)

    def put(self,request, pk, format=None):
        customer = self.get_object(pk=pk)
        serializer = CustomerSerializer(customer, data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

    def delete(self, request, pk, format=None):
        customer = self.get_object(pk=pk)
        customer.delete()
        return Response(status=status.HTTP_204_NO_CONTENT)


# class VehicleViewSet(viewsets.ViewSet):
#     # queryset = Vehicle.objects.all()
#     # serializer_class = VehicleSerializer(queryset, many=True)
#
#     def list(self, request):
#         queryset = Vehicle.objects.all()
#         serializer = VehicleSerializer(queryset, many=True)
#         return  Response(serializer.data)
#
#     def retrieve(self, request, pk=None):
#         queryset = Vehicle.objects.all()
#         vehicle = get_object_or_404(queryset, pk=pk)
#         serializer = VehicleSerializer(vehicle)
#         return Response(serializer.data)
#
#     def create(self, request):
#         queryset = Vehicle.objects.all()
#         serializer = VehicleSerializer(data = request.data)
#         if serializer.is_valid():
#             serializer.save()
#             return Response(serializer.data, status=status.HTTP_201_CREATED)
#         return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)
#
#     def update(self, request, pk=None):
#         queryset = Vehicle.objects.all()
#
    # def partial_update(self, request, pk=None):
    #     pass
    #
    # def destroy(self, request, pk=None):
    #     pass
class VehicleList(APIView):
    def get(self, request,format=None):
        vehicle = Vehicle.objects.all()
        serializer = VehicleSerializer(vehicle, many=True)
        return Response(serializer.data)

    def post(self, request, format=None):
        serializer = VehicleSerializer(data = request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

class VehicleDetail(APIView):
    def get_object(self, pk):
        try:
           return Vehicle.objects.get(pk=pk)
        except Vehicle.DoesNotExist :
            raise Http404

    def get(self,request, pk, format=None):
        vehicle = self.get_object(pk=pk)
        serializer = VehicleSerializer(vehicle)
        return Response(serializer.data)

    def put(self,request, pk, format=None):
        vehicle = self.get_object(pk=pk)
        serializer = VehicleSerializer(vehicle, data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

    def delete(self, request, pk, format=None):
        vehicle = self.get_object(pk=pk)
        vehicle.delete()
        return Response(status=status.HTTP_204_NO_CONTENT)



class VehicleStatusList(APIView):
    def get(self, request, format=None):
        vehicle_status = Vehicle_status.objects.all()
        serializer = VehicleStatusSerializer(vehicle_status, many=True)
        return Response(serializer.data)

    def post(self, request, format=None):
        serializer = VehicleStatusSerializer(data = request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

class VehicleStatusDetail(APIView):
    def get_object(self, pk):
        try:
            return Vehicle_status.objects.get(pk=pk)
        except Vehicle.DoesNotExist:
            raise Http404

    def get(self, request, pk, format=None):
        vehicle_status = self.get_object(pk=pk)
        serializer = VehicleStatusSerializer(vehicle_status)
        return Response(serializer.data)

    def put(self, request, pk, format=None):
        vehicle_status = self.get_object(pk=pk)
        serializer = VehicleStatusSerializer(vehicle_status, data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

class Rent_TripList(APIView):
    def get(self, request, format=None):

        rent_trip = Rent_Trip.objects.all()
        serializer = Rent_TripSerializer(rent_trip, many=True)
        return Response(serializer.data)

    def post(self, request, format=None):

        serializer = Rent_TripSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

class Rent_TripDetail(APIView):
    def get_object(self, pk):
        try:
            return Rent_Trip.objects.get(pk=pk)
        except Rent_Trip.DoesNotExist:
            raise Http404

    def get(self, request, pk, format=None):
        vehicle_status = self.get_object(pk=pk)
        serializer = Rent_TripSerializer(vehicle_status)
        return Response(serializer.data)

    def put(self, request, pk, format=None):
        rent_trip = self.get_object(pk=pk)
        serializer = Rent_TripSerializer(rent_trip, data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)


class BillList(APIView):
    def get(self, request, format=None):
        bill = Bill.objects.all()
        serializer = BillSerializer(bill, many=True)
        return Response(serializer.data)

    def post(self, request, format=None):
        serializer = BillSerializer(data = request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

class BillDetail(APIView):
    def get_object(self, pk):
        try:
            return Bill.objects.get(pk=pk)
        except Bill.DoesNotExist:
            raise Http404

    def get(self, request, pk, format=None):
        bill = self.get_object(pk=pk)
        serializer = Rent_TripSerializer(bill)
        return Response(serializer.data)

@csrf_exempt
@api_view(['POST'])
def save_file(request):
    file = request.FILES["img"]
    file_name = default_storage.save(file.name,file)

    return JsonResponse(file_name,safe=False)