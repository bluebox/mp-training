from .models import Appointment, Branch, User, ServicesProvided, Employee, Client
from .serializers import AppointmentSerializer, BranchSerializer, EmployeeSerializer
from .serializers import Userserializer, ClientSerializer, ServicesSerializer
from rest_framework.parsers import JSONParser

class Branches:
    def get_branch(request):
        branches = Branch.objects.all()
        serializer = BranchSerializer(branches, many=True)
        return serializer.data
    def getonebranch(request,branch_id):
        branch = Branch.objects.get(branch_id=branch_id)
        serializer = BranchSerializer(branch,many=False)
        return serializer.data
    def createbranch(request):
        serializer = BranchSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            branch = {'data' :serializer.data, 'msg':'successful'}
            return branch
        error_list = [serializer.errors[error][0] for error in serializer.errors]
        message = {'msg':error_list}
        return message
    def updatebranch(request,branch_id):
        branch = Branch.objects.get(branch_id=branch_id)
        serializer = BranchSerializer(instance=branch,data=request.data)
        if serializer.is_valid():
            serializer.save()
        return serializer.data
    def deletebranch(request):
        data = JSONParser().parse(request)
        branch = Branch.objects.get(branch_id=data['branch_id'])
        branch.delete()
        message="succesfully deleted"
        return message

class Services:
    def getservices(request):
        services = ServicesProvided.objects.all()
        serializer = ServicesSerializer(services, many=True)
        return serializer.data
    def createservice(request):
        serializer = ServicesSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            service = {'data' :serializer.data, 'msg':'successful'}
            return service
        error_list = [serializer.errors[error][0] for error in serializer.errors]
        message = {'msg':error_list}
        return message
    def getoneservice(request,service_id):
        service = ServicesProvided.objects.get(service_id=service_id)
        serilaizer = ServicesSerializer(service,many=False)
        return serilaizer.data
    def updateservice(request,service_id):
        service = ServicesProvided.objects.get(service_id=service_id)
        serializer = ServicesSerializer(instance=service,data=request.data)
        if serializer.is_valid():
            serializer.save()
        return serializer.data
    def deleteservice(request):
        data = JSONParser().parse(request)
        service = ServicesProvided.objects.get(service_id=data['service_id'])
        service.delete()
        message = "succesfully deleted"
        return message

class Appointments:
    def createappointment(request):
        serializer = AppointmentSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return serializer.data
        error_list = [serializer.errors[error][0] for error in serializer.errors]
        message = {'msg':error_list}
        return message
    def getbookedappointments(request):
        appointments = Appointment.objects.filter(Appointment_Status = "booked")
        serializer = AppointmentSerializer(appointments, many=True)
        return serializer.data
    def getconfirmedappointments(request):
        appointments = Appointment.objects.filter(Appointment_Status = 'confirm')
        serializer = AppointmentSerializer(appointments, many=True)
        return serializer.data
    def getcompleteappointments(request):
        appointments = Appointment.objects.filter(Appointment_Status = 'complete')
        serializer = AppointmentSerializer(appointments, many=True)
        return serializer.data
    def getrejectappointments(request):
        appointments = Appointment.objects.filter(Appointment_Status__in = ['reject','cancel'])
        serializer = AppointmentSerializer(appointments, many=True)
        return serializer.data