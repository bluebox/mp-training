"""views"""
#pylint:disable=E1101
#pylint:disable=C0103
#pylint:disable=W0703
from rest_framework.views import APIView
from rest_framework.response import Response
from rest_framework import generics,status,filters
from django_filters.rest_framework import DjangoFilterBackend
from .models import Appointment, Branch, User, ServicesProvided, Employee, Client
from .serializers import AppointmentSerializer, BranchSerializer, EmployeeSerializer
from .serializers import Userserializer, ClientSerializer, ServicesSerializer
from .managers import Branches,Services,Appointments

class BranchList(APIView):
    """View to get the details from Database"""
    def get(self, request):
        """this function is to display all the details of the branch table"""
        try:
            branch = Branches.get_branch(request)
            return Response(branch,status=200)
        except Exception as e:
            return Response(str(e), status=500)

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
        try:
            branch = Branches.getonebranch(request, branch_id)
            return Response(branch,status=200)
        except Exception as e:
            return Response(str(e), status=500)

class NewBranch(APIView):
    """View to post the data into the database"""
    def post(self, request):
        """this function is for posting branch details in to the database"""
        try:
            branch = Branches.createbranch(request)
            return Response(branch,status=200)
        except Exception as e:
            return Response(str(e), status=500)

class UpdateBranch(APIView):
    """this view for updating branch details"""
    def post(self,request,branch_id):
        """this function is for posting the updtae details to the database"""
        try:
            branch=Branches.updatebranch(request,branch_id)
            return Response(branch,status=200)
        except Exception as e:
            return Response(str(e), status=500)

class DeleteBranch(APIView):
    """this view is for deleting a record"""
    def post(self,request):
        """this function is to delete a record"""
        try:
            branch = Branches.deletebranch(request)
            return Response(branch,status=200)
        except Exception as e:
            return Response(str(e), status=500)

#services_provided
class ServicesList(APIView):
    """This view is used to get and post the ServicesProvided details"""
    def get(self, request):
        """this function is to display the services list of the servicesprovided table"""
        try:
            service = Services.getservices(request)
            return Response(service)
        except Exception as e:
            return Response(str(e), status=500)

    def post(self, request):
        """this function is for posting new services data in to the services provided table"""
        try:
            service =Services.createservice(request)
            return Response(service,status=200)
        except Exception as e:
            return Response(str(e), status=500)

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
        try:
            service = Services.getoneservice(request,service_id)
            return Response(service)
        except Exception as e:
            return Response(str(e), status=500)

class UpdateService(APIView):
    """this view for updating branch details"""
    def post(self,request,service_id):
        """this function is for posting the updtae details to the database"""
        try:
            service=Services.updateservice(request,service_id)
            return Response(service)
        except Exception as e:
            return Response(str(e), status=500)

class DeleteService(APIView):
    """this view is for deleting a record"""
    def post(self,request):
        """this function is to delete a record"""
        try:
            service = Services.deleteservice(request)
            return Response(service)
        except Exception as e:
            return Response(str(e), status=500)

class NewAppointment(APIView):
    """this view to post the data"""
    def post(self,request):
        """this function adds a new appointment record in the appointment table"""
        try:
            appointment = Appointments.createappointment(request)
            return Response(appointment,status=200)
        except Exception as e:
            return Response(str(e), status=500)

class AppointmentList(APIView):
    """This view is used to get the Appointment details"""
    def get(self, request):
        """this function is for displaying data of the Appointment table"""
        try:
            appointment=Appointments.getbookedappointments(request)
            return Response(appointment,status=200)
        except Exception as e:
            return Response(str(e), status=500)

class ConfirmAppointment(APIView):
    """This view is used to get the Appointment details"""
    def get(self, request):
        """this function is for displaying data of the Appointment table"""
        try:
            appointment = Appointments.getconfirmedappointments(request)
            return Response(appointment,status=200)
        except Exception as e:
            return Response(str(e), status=500)

class CompleteAppointment(APIView):
    """This view is used to get Appointment details"""
    def get(self, request):
        """this function is for displaying data of the Appointment table"""
        try:
            appointment=Appointments.getcompleteappointments(request)
            return Response(appointment,status=200)
        except Exception as e:
            return Response(str(e), status=500)

class RejectAppointment(APIView):
    """This view is used to get the Appointment details"""
    def get(self, request):
        """this function is for displaying data of the Appointment table"""
        try:
            appointment = Appointments.getrejectappointments(request)
            return Response(appointment,status=200)
        except Exception as e:
            return Response(str(e), status=500)

class OneAppointment(APIView):
    """this view return only one record from the database"""
    def get(self,request,appointment_id):
        """this view return only one appointment"""
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
