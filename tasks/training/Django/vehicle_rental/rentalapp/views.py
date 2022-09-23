from email.mime import image
from django.shortcuts import render, redirect, get_object_or_404
from django.http import HttpResponse
from django.contrib import messages
from rest_framework import status, viewsets
from rest_framework.response import Response
from django.http.response import JsonResponse, Http404
from django.core.files.storage import default_storage
from rest_framework.views import APIView

from .models import Customer, Owner, Vehicle
from django.views.decorators.csrf import csrf_exempt

from .serializer import OwnerSerializer, CustomerSerializer, VehicleSerializer


# Create your views here.
def home(request):
    return render(request, 'home.html' )

def customer_login(request):
    return render(request, 'customer_login.html')

def owner_login(request):
    return render(request, 'owner_login.html')

def customer_register(request):
    return render(request, 'customer_register.html')

def owner_register(request):
    return render(request, 'owner_register.html')

def c_login(request):
    if request.method == "POST" :
        email = request.POST['email']
        dl = request.POST['password']

        user = Customer.objects.all().values()

        for i in user:
            if i['email']==email and i['dl_no']==dl :
                return HttpResponse("No vehicles yet")

        else:
            messages.error(request,"invalid mail or password")
            return redirect('customer_login')


owner_name =""
def o_login(request):
    if request.method == "POST":
        email = request.POST['email']
        contact = request.POST['password']

        owner= Owner.objects.all().values()

        for i in owner:

            if i['email'] == email and i['contact_no'] == contact :

                return render(request, 'add_vehicle.html',{ 'name':i["name"], 'id':i['owner_id']})

        else:
            messages.error(request, "invalid email or password")
            return redirect('owner_login')



def add_vehicle(request,para):
    if request.method == "POST":
        vehicle_no = request.POST['vehicle_no']
        type = request.POST['vehicle_type']
        brand = request.POST['vehicle_brand']
        model = request.POST['vehicle_model']
        # Savefile(request)
        image = request.FILES["img"]
        # file_name = default_storage.save(file.name,file)
        # image=file_name

        owner_id = para
        price_km = request.POST['price_per_km']
        price_hour = request.POST['price_per_hour']
        price_day = request.POST['price_per_day']

        veh = Vehicle(vehicle_no, type, brand, model, image, owner_id, price_km, price_hour, price_day).save()
        return HttpResponse("Added successfully")

    else:
        return HttpResponse("KHB")

def show(request):
    vehicle = Vehicle.objects.all()
    return render(request, "show_vehicle.html", {"vehicle" : vehicle})

# @csrf_exempt
# def Savefile(request):
#     file = request.FILES["img"]
#     file_name = default_storage.save(file.name,file)

#     return JsonResponse(file_name,safe=False)


# API
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
        serializer = OwnerSerializer(data = request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

class CustomerList(APIView):
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


class VehicleViewSet(viewsets.ModelViewSet):
    # queryset = Vehicle.objects.all()
    # serializer_class = VehicleSerializer(queryset, many=True)

    def list(self, request):
        queryset = Vehicle.objects.all()
        serializer = VehicleSerializer(queryset, many=True)
        return  Response(serializer.data)

    def retrieve(self, request, pk=None):
        queryset = Vehicle.objects.all()
        vehicle = get_object_or_404(queryset, pk=pk)
        serializer = VehicleSerializer(vehicle)
        return Response(serializer.data)

    # def create(self, request):
    #     pass
    #
    # def update(self, request, pk=None):
    #     pass
    #
    # def partial_update(self, request, pk=None):
    #     pass
    #
    # def destroy(self, request, pk=None):
    #     pass


