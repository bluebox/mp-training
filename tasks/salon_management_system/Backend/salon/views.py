"""views"""
# from django.contrib.auth import authenticate, login, logout
from rest_framework.views import APIView
# from rest_framework.decorators import api_view
from rest_framework.parsers import JSONParser
from rest_framework.response import Response
from rest_framework import generics,status,filters
# from rest_framework.generics import ListAPIView
from django_filters.rest_framework import DjangoFilterBackend
from django.http import JsonResponse
# from django.contrib import messages
from .models import Appointment, Branch, User, ServicesProvided, Employee, Client
from .serializers import AppointmentSerializer, BranchSerializer, EmployeeSerializer
from .serializers import Userserializer, ClientSerializer, ServicesSerializer
# from salon import serializers

class UserList(APIView):
    """This view is used to get and post the user details"""
    def get(self, request):
        """this functon is for displaying data from User model"""
        print(request.data)
        users = User.objects.all()
        serializer = Userserializer(users, many=True)
        return Response(serializer.data)

    def post(self, request):
        """this function is for posting the data into the User Table"""
        serializer = Userserializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        print('invalid')
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)


class BranchList(APIView):
    """View to get the details from Database"""
    def get(self, request):
        """this function is to display all the details of the branch table"""
        print(request.data)
        branches = Branch.objects.all()
        serializer = BranchSerializer(branches, many=True)
        return Response(serializer.data)

class SearchBranches(generics.ListAPIView):
    """this view is for searching """
    queryset = Branch.objects.all()
    serializer_class = BranchSerializer
    filter_backends = [filters.SearchFilter]
    search_fields = ['branch_id','branch_name','location']

class OneBranch(APIView):
    """this view returns only one branch"""
    def get(self,request,branch_id):
        """this function returns only one record from the branch model"""
        print(request.data)
        branch = Branch.objects.get(branch_id=branch_id)
        serializer = BranchSerializer(branch,many=False)
        return Response(serializer.data)

class NewBranch(APIView):
    """View to post the data into the database"""
    def post(self, request):
        """this function is for posting branch details in to the database"""
        serializer = BranchSerializer(data=request.data)
        print(serializer)
        print(serializer.is_valid)
        if serializer.is_valid():
            serializer.save()
            return Response({'data' :serializer.data, 'msg':'successful'},status=200)
        print('invalid')
        print(serializer.errors)
        error_list = [serializer.errors[error][0] for error in serializer.errors]
        print(error_list)
        return Response({'msg':error_list},status=200)

class UpdateBranch(APIView):
    """this view for updating branch details"""
    def post(self,request,branch_id):
        """this function is for posting the updtae details to the database"""
        branch = Branch.objects.get(branch_id=branch_id)
        serializer = BranchSerializer(instance=branch,data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)

        return Response(status=status.HTTP_404_NOT_FOUND)

class DeleteBranch(APIView):
    """this view is for deleting a record"""
    def post(self,request):
        """this function is to delete a record"""
        data = JSONParser().parse(request)
        branch = Branch.objects.get(branch_id=data['branch_id'])
        branch.delete()
        return JsonResponse("succesfully deleted",safe=False)

class ServicesList(APIView):
    """This view is used to get and post the ServicesProvided details"""
    def get(self, request):
        """this function is to display the services list of the servicesprovided table"""
        print(request.data)
        services = ServicesProvided.objects.all()
        serializer = ServicesSerializer(services, many=True)
        return Response(serializer.data)

    def post(self, request):
        """this function is for posting new services data in to the services provided table"""
        serializer = ServicesSerializer(data=request.data)
        print(serializer)
        if serializer.is_valid():
            serializer.save()
            return Response({'data' :serializer.data, 'msg':'successful'},status=200)
        print('invalid')
        error_list = [serializer.errors[error][0] for error in serializer.errors]
        return Response({'msg':error_list},status=200)
        
class SearchService(generics.ListAPIView):
    """this view is for searching services"""
    queryset = ServicesProvided.objects.all()
    serializer_class = ServicesSerializer
    filter_backends = [filters.SearchFilter]
    search_fields = ['service_id','service_name','Amount_to_be_paid']

class OneService(APIView):
    """this is to get one service record"""
    def get(self,request,service_id):
        """this function return deatils related to one service"""
        print(request.data)
        service = ServicesProvided.objects.get(service_id=service_id)
        serilaizer = ServicesSerializer(service,many=False)
        return Response(serilaizer.data)

class UpdateService(APIView):
    """this view for updating branch details"""
    def post(self,request,service_id):
        """this function is for posting the updtae details to the database"""
        service = ServicesProvided.objects.get(service_id=service_id)
        serializer = ServicesSerializer(instance=service,data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        return Response(status=status.HTTP_404_NOT_FOUND)

class DeleteService(APIView):
    """this view is for deleting a record"""
    def post(self,request):
        """this function is to delete a record"""
        data = JSONParser().parse(request)
        service = ServicesProvided.objects.get(service_id=data['service_id'])
        service.delete()
        return JsonResponse("succesfully deleted",safe=False)

class NewAppointment(APIView):
    """this view to post the data"""
    def post(self,request):
        """this function adds a new appointment record in the appointment table"""
        serializer = AppointmentSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        print('invalid')
        print(serializer.errors)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

class AppointmentList(APIView):
    """This view is used to get the Appointment details"""
    def get(self, request):
        """this function is for displaying data of the Appointment table"""
        print(request.data)
        appointments = Appointment.objects.filter(Appointment_Status = "booked")
        serializer = AppointmentSerializer(appointments, many=True)
        return Response(serializer.data)

class ConfirmAppointment(APIView):
    """This view is used to get the Appointment details"""
    def get(self, request):
        """this function is for displaying data of the Appointment table"""
        print(request.data)
        appointments = Appointment.objects.filter(Appointment_Status = 'confirm')
        serializer = AppointmentSerializer(appointments, many=True)
        return Response(serializer.data)

class CompleteAppointment(APIView):
    """This view is used to get Appointment details"""
    def get(self, request):
        """this function is for displaying data of the Appointment table"""
        print(request.data)
        appointments = Appointment.objects.filter(Appointment_Status = 'complete')
        serializer = AppointmentSerializer(appointments, many=True)
        return Response(serializer.data)

class RejectAppointment(APIView):
    """This view is used to get the Appointment details"""
    def get(self, request):
        """this function is for displaying data of the Appointment table"""
        print(request.data)
        appointments = Appointment.objects.filter(Appointment_Status__in = ['reject','cancel'])
        serializer = AppointmentSerializer(appointments, many=True)
        return Response(serializer.data)

class OneAppointment(APIView):
    """this view return only one record from the database"""
    def get(self,request,appointment_id):
        """this view return only one appointment"""
        print(request.data)
        appointmnet = Appointment.objects.get(Appointment_id=appointment_id)
        serilaizer = AppointmentSerializer(appointmnet,many=False)
        return Response(serilaizer.data)

class UpdateAppointment(APIView):
    """this view is for updating data"""
    def post(self,request,appointment_id):
        """this function is for posting the updated data"""
        appointment = Appointment.objects.get(Appointment_id=appointment_id)
        serializer = AppointmentSerializer(instance=appointment,data=request.data)
        if serializer.is_valid():
            print(serializer.errors)
            serializer.save(update_fields=["Appointment_Status"])
            return Response(serializer.data)
        print(serializer.errors)
        return Response(status=status.HTTP_404_NOT_FOUND)

class EmployeeList(APIView):
    """view to get the data from the database"""
    def get(self, request):
        """this function for displaying the employeelist"""
        print(request.data)
        employees = User.objects.filter(employee__role = "HairStylist").values('id',
        'username', "first_name", "last_name","email","employee__emp_id", "employee__role",
        "employee__emp_contact_number", "employee__branch_id")
        return Response(employees)

class ListOfEmployees(APIView):
    """view to get the data from the database"""
    def get(self, request):
        """this function for displaying the employeelist"""
        print(request.data)
        employees = User.objects.filter(is_staff='True', is_superuser="False").values('id',
        'username', "first_name", "last_name","email","employee__emp_id", "employee__role",
        "employee__emp_contact_number", "employee__branch_id")
        return Response(employees)

class SearchEmployee(generics.ListAPIView):
    """this view is for searching"""
    queryset = User.objects.filter(is_staff='True', is_superuser="False").values('id',
        'username', "first_name", "last_name","email","employee__emp_id", "employee__role",
        "employee__emp_contact_number", "employee__branch_id")
    serializer_class = Userserializer
    filter_backends = [filters.SearchFilter]
    search_fields = ['username']

class ClientList(APIView):
    """view to get the data from the database"""
    def get(self, request):
        """this function for displaying the ClientList"""
        print(request.data)
        clients = Client.objects.all()
        serializer = ClientSerializer(clients,many=True)
        # filter_backends = [filters.SearchFilter]
        # search_fields = ['Client_contact_number']

        return Response(serializer.data)

class SearchClients(generics.ListAPIView):
    """this view is for seraching clients"""
    queryset = Client.objects.all()
    serializer_class = ClientSerializer
    filter_backends = [DjangoFilterBackend]
    filterset_fields = ['user_id', 'id']
    filter_backends = [filters.SearchFilter]
    search_fields = ['Client_contact_number','id']

class ListOfClients(APIView):
    """view to get the data from the database"""
    def get(self, request):
        """this function for displaying the ClientList"""
        print(request.data)
        clients = User.objects.filter(is_staff='False').values('id',
        'username', "first_name", "last_name", "email","client__id",
        "client__Client_contact_number")
        return Response(clients)


class EmpBranch(APIView):
    """view to get the data from the database"""
    def get(self, request):
        """this function for displaying the employeelist and branch"""
        print(request.data)
        employees = Employee.objects.order_by('emp_id').last()
        serilaizer = EmployeeSerializer(employees, many=False)
        return Response(serilaizer.data)


class ClientRegistration(APIView):
    """view for posting data into the database"""
    def post(self, request):
        """this function is for registration of a new client"""
        username = request.data.get("username")
        user = User.objects.filter(username=username).first()
        if user:
            print(user)
            return Response({"message":"username already taken"})
        serializer = Userserializer(data={'username': request.data["username"],
        "first_name": request.data["first_name"], 'last_name': request.data["last_name"],
        'email': request.data['email'], "password": request.data['password']})
        if serializer.is_valid():
            user = serializer. save()
            client_obj = ClientSerializer(data={'user_id': user.id,
            'Client_contact_number': request.data["Client_contact_number"]})
            if client_obj.is_valid():
                client_obj.save()
                return Response({'data': serializer.data,'message':'success'},status=200)
            return Response({'message': 'invalid'})
        return Response({'message': 'invalid'})


class EmployeeRegistration(APIView):
    """view for posting data into the database"""
    def post(self, request):
        """this function is for registration of a new Employee"""
        print(request.data)
        serializer = Userserializer(data={'username': request.data["username"],
        "first_name": request.data["first_name"], 'last_name': request.data["last_name"],
        'email': request.data['email'], "password": request.data['password'],
        "is_staff": request.data["is_staff"]})
        print(serializer)
        if serializer.is_valid():
            user = serializer.save()
            print(user)
            employee_object = EmployeeSerializer(data={'user_id': user.id,
            'emp_id': request.data['emp_id'], 'branch_id': request.data['branch_id'],
            'role': request.data['role'], 'emp_contact_number': request.data['emp_contact_number']})
            if employee_object.is_valid():
                employee_object.save()
                return Response({'data': serializer.data}, status=200)
            return Response({'message': 'invalid'})
        return Response({'message': 'invalid'})
