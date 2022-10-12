"""views"""
# from django.contrib.auth import authenticate, login, logout

from rest_framework.views import APIView

# from rest_framework.decorators import api_view

from rest_framework.response import Response

from rest_framework import status

from .models import Appointment, Branch, User, ServicesProvided, Employee, Client

from .serializers import AppointmentSerializer, BranchSerializer, EmployeeSerializer

from .serializers import Userserializer, ClientSerializer, ServicesSerializer

class UserList(APIView):
    """This view is used to get and post the user details"""
    def get(self, request):
        """this functon is for displaying data from User model"""
        users = User.objects.all()
        serializer = Userserializer(users, many=True)
        return Response(serializer.data)

    def post(self, request):
        """this function is for posting the data into the User Table"""
        serializer = Userserializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        else:
            print('invalid')
            return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)


class BranchList(APIView):
    """View to get the details from Database"""
    def get(self, request):
        """this function is to display all the details of the branch table"""
        branches = Branch.objects.all()
        serializer = BranchSerializer(branches, many=True)
        return Response(serializer.data)


class NewBranch(APIView):
    """View to post the data into the database"""
    def post(self, request):
        """this function is for posting branch details in to the database"""
        serializer = BranchSerializer(data=request.data)
        print(serializer)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        else:
            print('invalid')
            return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

class NewAppointment(APIView):
    def post(self,request):
        serializer = AppointmentSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        else:
            print('invalid')
            return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

class ServicesList(APIView):
    """This view is used to get and post the ServicesProvided details"""
    def get(self, request):
        """this function is to display the services list of the servicesprovided table"""
        services = ServicesProvided.objects.all()
        serializer = ServicesSerializer(services, many=True)
        return Response(serializer.data)

    def post(self, request):
        """this function is for posting new services data in to the services provided table"""
        serializer = ServicesSerializer(data=request.data)
        print(serializer)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        else:
            print('invalid')
            return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)


class AppointmentList(APIView):
    """This view is used to get and post the Appointment details"""
    def get(self, request):
        """this function is for displaying data of the Appointment table"""
        appointments = Appointment.objects.all()
        serializer = AppointmentSerializer(appointments, many=True)
        return Response(serializer.data)

    def post(self, request):
        """this function is for posting data into the appointment table"""
        serializer = AppointmentSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        else:
            return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)


class EmployeeList(APIView):
    """view to get the data from the database"""
    def get(self, request):
        """this function for displaying the employeelist"""
        employees = User.objects.filter(is_staff = "True", is_superuser="False").select_related('employee')
        serializer = Userserializer(employees, many=True)
        return Response(serializer.data)


class ListOfEmployees(APIView):
    """view to get the data from the database"""
    def get(self, request):
        """this function for displaying the employeelist"""
        employees = User.objects.filter(is_staff='True', is_superuser="False").values('id',
        'username', "first_name", "last_name","email","employee__emp_id", "employee__role",
        "employee__emp_contact_number", "employee__branch_id")
        return Response(employees)


class ClientList(APIView):
    """view to get the data from the database"""
    def get(self, request):
        """this function for displaying the ClientList"""
        clients = Client.objects.all()
        serializer = ClientSerializer(clients, many=True)

        return Response(serializer.data)


class ListOfClients(APIView):
    """view to get the data from the database"""
    def get(self, request):
        """this function for displaying the ClientList"""
        clients = User.objects.filter(is_staff='True').values('id',
        'username', "first_name", "last_name", "email", "client__Client_contact_number")
        return Response(clients)


class EmpBranch(APIView):
    """view to get the data from the database"""
    def get(self, request):
        """this function for displaying the employeelist and branch"""
        employees = Employee.objects.select_related('branch_id').only('emp_id', 'branch_id')
        serilaizer = EmployeeSerializer(employees, many=True)
        return Response(serilaizer.data)


class ClientRegistration(APIView):
    """view for posting data into the database"""
    def post(self, request):
        """this function is for registration of a new client"""
        serializer = Userserializer(data={'username': request.data["username"],
        "first_name": request.data["first_name"], 'last_name': request.data["last_name"],
        'email': request.data['email'], "password": request.data['password']})
        if serializer.is_valid():
            user = serializer. save()
            client_obj = ClientSerializer(data={'user_id': user.id,
            'Client_contact_number': request.data["Client_contact_number"]})
            if client_obj.is_valid():
                client_obj.save()
                return Response({'data': serializer.data,'message':'register success'},status=200)
            else:
                return Response({'message': 'invalid'})
        else:
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
            else:
                return Response({'message': 'invalid'})
        else:
            return Response({'message': 'invalid'})


# class LoginUser(APIView):
#     def post(self,request):
#         if request.method == 'POST':
#             username = request.data.get("username")
#             password = request.data.get('password')
#             try:
#                 user = User.objects.get(username = username)
#             except:
#                 return Response({'msg':"User Does not Exist"})

#             user = authenticate(request, username = username, password = password)
#             if user is not None:
#                 login(request, user)
#                 return Response({'msg':"logged in" , 'user': user.username} ,status=200)
#             else:
#                 return Response({'msg':"password incorrect" })

#         return Response({"msg":"not created"},status =200)
