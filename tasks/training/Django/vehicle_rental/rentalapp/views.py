from email.mime import image
from django.shortcuts import render, redirect
from django.http import HttpResponse
from django.contrib import messages
from django.http.response import JsonResponse
from django.core.files.storage import default_storage
from .models import Customer, Owner, Vehicle
from django.views.decorators.csrf import csrf_exempt

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