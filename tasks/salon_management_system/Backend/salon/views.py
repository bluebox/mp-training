"""views"""
# pylint:disable=E1101
# pylint:disable=C0103
# pylint:disable=W0703
from rest_framework.views import APIView
from rest_framework.response import Response
from rest_framework import generics, filters
from .models import Branch, User, ServicesProvided, Client
from .serializers import BranchSerializer, Userserializer, ClientSerializer, ServicesSerializer
from .managers import Branches, Services, Appointments, Employees, Clients, Review, Transactions


class BranchList(APIView):
    """View to get the details from Database"""
    @staticmethod
    def get(request):
        """this function is to display all the details of the branch table"""
        try:
            branch = Branches.get_branch(request)
            return Response(branch, status=200)
        except Exception as e:
            return Response(str(e), status=500)


class SearchBranches(generics.ListAPIView):
    """this view is for searching """
    queryset = Branch.objects.all()
    serializer_class = BranchSerializer
    filter_backends = [filters.SearchFilter]
    search_fields = ['branch_id', 'branch_name', 'location']


class OneBranch(APIView):
    """this view returns only one branch"""
    @staticmethod
    def get(request, branch_id):
        """this function returns only one record from the branch model"""
        try:
            branch = Branches.get_one_branch(request, branch_id)
            return Response(branch, status=200)
        except Exception as e:
            return Response(str(e), status=500)


class NewBranch(APIView):
    """View to post the data into the database"""
    @staticmethod
    def post(request):
        """this function is for posting branch details in to the database"""
        try:
            branch = Branches.create_branch(request)
            return Response(branch, status=200)
        except Exception as e:
            return Response(str(e), status=500)


class UpdateBranch(APIView):
    """this view for updating branch details"""

    @staticmethod
    def post(request, branch_id):
        """this function is for posting the update details to the database"""
        try:
            branch = Branches.update_branch(request, branch_id)
            return Response(branch, status=200)
        except Exception as e:
            return Response(str(e), status=500)


class DeleteBranch(APIView):
    """this view is for deleting a record"""
    @staticmethod
    def post(request):
        """this function is to delete a record"""
        try:
            branch = Branches.delete_branch(request)
            return Response(branch, status=200)
        except Exception as e:
            return Response(str(e), status=500)


# services_provided
class ServicesList(APIView):
    """This view is used to get and post the ServicesProvided details"""
    @staticmethod
    def get(request):
        """this function is to display the services list of the services provided table"""
        try:
            service = Services.get_services(request)
            return Response(service)
        except Exception as e:
            return Response(str(e), status=500)

    @staticmethod
    def post(request):
        """this function is for posting new services data in to the services provided table"""
        try:
            service = Services.create_service(request)
            return Response(service, status=200)
        except Exception as e:
            return Response(str(e), status=500)


class SearchService(generics.ListAPIView):
    """this view is for searching services"""
    queryset = ServicesProvided.objects.all()
    serializer_class = ServicesSerializer
    filter_backends = [filters.SearchFilter]
    search_fields = ['service_id', 'service_name', 'Amount_to_be_paid']


class OneService(APIView):
    """this is to get one service record"""

    @staticmethod
    def get(request, service_id):
        """this function return details related to one service"""
        try:
            service = Services.get_one_service(request, service_id)
            return Response(service)
        except Exception as e:
            return Response(str(e), status=500)


class UpdateService(APIView):
    """this view for updating branch details"""

    @staticmethod
    def post(request, service_id):
        """this function is for posting the update details to the database"""
        try:
            service = Services.update_service(request, service_id)
            return Response(service)
        except Exception as e:
            return Response(str(e), status=500)


class DeleteService(APIView):
    """this view is for deleting a record"""

    @staticmethod
    def post(request):
        """this function is to delete a record"""
        try:
            service = Services.delete_service(request)
            return Response(service)
        except Exception as e:
            return Response(str(e), status=500)


class NewAppointment(APIView):
    """this view to post the data"""

    @staticmethod
    def post(request):
        """this function adds a new appointment record in the appointment table"""
        try:
            appointment = Appointments.create_appointment(request)
            return Response(appointment, status=200)
        except Exception as e:
            return Response(str(e), status=500)


class AppointmentList(APIView):
    """This view is used to get the Appointment details"""

    @staticmethod
    def get(request):
        """this function is for displaying data of the Appointment table"""
        try:
            appointment = Appointments.get_booked_appointments(request)
            return Response(appointment, status=200)
        except Exception as e:
            return Response(str(e), status=500)


class ConfirmAppointment(APIView):
    """This view is used to get the Appointment details"""

    @staticmethod
    def get(request):
        """this function is for displaying data of the Appointment table"""
        try:
            appointment = Appointments.get_confirmed_appointments(request)
            return Response(appointment, status=200)
        except Exception as e:
            return Response(str(e), status=500)


class CompleteAppointment(APIView):
    """This view is used to get Appointment details"""

    @staticmethod
    def get(request):
        """this function is for displaying data of the Appointment table"""
        try:
            appointment = Appointments.get_complete_appointments(request)
            return Response(appointment, status=200)
        except Exception as e:
            return Response(str(e), status=500)


class RejectAppointment(APIView):
    """This view is used to get the Appointment details"""

    @staticmethod
    def get(request):
        """this function is for displaying data of the Appointment table"""
        try:
            appointment = Appointments.get_reject_appointments(request)
            return Response(appointment, status=200)
        except Exception as e:
            return Response(str(e), status=500)


class OneAppointment(APIView):
    """this view return only one record from the database"""

    @staticmethod
    def get(request, appointment_id):
        """this view return only one appointment"""
        try:
            appointment = Appointments.get_one_appointment(request, appointment_id)
            return Response(appointment, status=200)
        except Exception as e:
            return Response(str(e), status=500)


class UpdateAppointment(APIView):
    """this view is for updating data"""

    @staticmethod
    def post(request, appointment_id):
        """this function is for posting the updated data"""
        try:
            appointment = Appointments.update_appointment(request, appointment_id)
            return Response(appointment)
        except Exception as e:
            return Response(str(e), status=500)


class EmployeeList(APIView):
    """view to get the data from the database"""

    @staticmethod
    def get(request):
        """this function for displaying the employee list"""
        try:
            employees = Employees.get_hairstylist(request)
            return Response(employees)
        except Exception as e:
            return Response(str(e), status=500)


class ListOfEmployees(APIView):
    """view to get the data from the database"""

    @staticmethod
    def get(request):
        """this function for displaying the employee list"""
        try:
            employees = Employees.get_all_employees(request)
            return Response(employees)
        except Exception as e:
            return Response(str(e), status=500)


class SearchEmployee(generics.ListAPIView):
    """this view is for searching"""
    queryset = User.objects.filter(is_staff='True', is_superuser="False").values('id',
        'username', "first_name", "last_name",
        "email", "employee__emp_id",
        "employee__role", "employee__emp_contact_number", "employee__branch_id")
    serializer_class = Userserializer
    filter_backends = [filters.SearchFilter]
    search_fields = ['username']


class EmpBranch(APIView):
    """view to get the data from the database"""

    @staticmethod
    def get(request):
        """this function for displaying the employee list and branch"""
        try:
            employees = Employees.get_last_employee(request)
            return Response(employees)
        except Exception as e:
            return Response(str(e), status=500)


class EmployeeRegistration(APIView):
    """view for posting data into the database"""

    @staticmethod
    def post(request):
        """this function is for registration of a new Employee"""
        try:
            employee = Employees.create_employee(request)
            return Response(employee, status=200)
        except Exception as e:
            return Response(str(e), status=500)


class ClientList(APIView):
    """view to get the data from the database"""

    @staticmethod
    def get(request):
        """this function for displaying the ClientList"""
        try:
            clients = Clients.clients_list(request)
            return Response(clients)
        except Exception as e:
            return Response(str(e), status=500)


class SearchClients(generics.ListAPIView):
    """this view is for searching clients"""
    queryset = Client.objects.all()
    serializer_class = ClientSerializer
    filter_backends = [filters.SearchFilter]
    search_fields = ['Client_contact_number', 'id']


class ListOfClients(APIView):
    """view to get the data from the database"""

    @staticmethod
    def get(request):
        """this function for displaying the ClientList"""
        try:
            clients = Clients.list_of_clients(request)
            return Response(clients)
        except Exception as e:
            return Response(str(e), status=500)


class ClientRegistration(APIView):
    """view for posting data into the database"""

    @staticmethod
    def post(request):
        """this function is for registration of a new client"""
        try:
            client = Clients.client_register(request)
            return Response(client)
        except Exception as e:
            return Response(str(e), status=500)


class NewReview(APIView):
    """view for creating and reading all reviews"""

    @staticmethod
    def get(request):
        """to get all the reviews"""
        try:
            review = Review.get_all_reviews(request)
            return Response(review)
        except Exception as e:
            return Response(str(e), status=500)

    @staticmethod
    def post(request):
        """to add a new review"""
        try:
            review = Review.create_review(request)
            return Response(review)
        except Exception as e:
            return Response(str(e), status=500)


class AllTransactions(APIView):
    """view for creating , reading ,  updating transactions made"""

    @staticmethod
    def get(request):
        """to get all the transactions"""
        try:
            transaction = Transactions.get_all_transactions(request)
            return Response(transaction, status=200)
        except Exception as e:
            return Response(str(e), status=500)

    @staticmethod
    def post(request):
        """to create a new transaction"""
        try:
            transaction = Transactions.create_transaction(request)
            return  Response(transaction, many=True)
        except Exception as e:
            return Response(str(e), status=500)
