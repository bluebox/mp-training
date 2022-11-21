from vehicle.models import Vehicle
from vehicle.serializers import vehicleSerializer


def getVehicle(vehicle_no):
    if vehicle_no is None:
        vehicle = Vehicle.objects.all()
        serialize = vehicleSerializer(vehicle, many=True)
        return serialize.data
    else:
        vehicle = Vehicle.objects.get(vehicle_no=vehicle_no)
        serialize = vehicleSerializer(vehicle)
        return serialize.data

def addVehicle(request):
    serializer = vehicleSerializer(data=request.data)
    if serializer.is_valid():
        vehicle_no = request.data['vehicle_no']
        model = request.data['model']
        owner_name = request.data['owner_name']
        permit_range = request.data['permit_range']
        fuel_efficiency = request.data['fuel_efficiency']
        load_capacity = request.data['load_capacity']
        vehicle = Vehicle(vehicle_no=vehicle_no, model=model, owner_name=owner_name, permit_range=permit_range,
                          fuel_efficiency=fuel_efficiency, load_capacity=load_capacity)
        vehicle.save()
        return vehicleSerializer(vehicle).data
    return 'failed'

def updateVehicle(request, vehicle_no):
    try:
        model = request.data['model']
        owner_name = request.data['owner_name']
        permit_range = request.data['permit_range']
        fuel_efficiency = request.data['fuel_efficiency']
        load_capacity = request.data['load_capacity']
        print(vehicle_no, model, owner_name, permit_range, fuel_efficiency, load_capacity)
        print(Vehicle.objects.get(vehicle_no=vehicle_no))

        vehicle = Vehicle(id=Vehicle.objects.get(vehicle_no=vehicle_no), model=model,
                          owner_name=owner_name, permit_range=permit_range, fuel_efficiency=fuel_efficiency,
                          load_capacity=load_capacity)
        print(vehicle)
        vehicle.save()
        print(vehicle)
        return vehicleSerializer(vehicle).data
    except:
        return 'failed'
