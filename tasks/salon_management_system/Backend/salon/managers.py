"""managers"""
# pylint:disable=W0613
# pylint:disable=W0107
# pylint:disable=E1101
# pylint:disable=C0301
from rest_framework.parsers import JSONParser
from .models import Appointment, Branch, User, ServicesProvided, Employee, Client, Reviews,\
    Transaction
from .serializers import AppointmentSerializer, BranchSerializer, EmployeeSerializer, \
    Userserializer, ClientSerializer, ServicesSerializer, ReviewSerializer,\
    TransactionSerializer


class Branches:
    """creating updating reading deleting branches"""

    def __init__(self):
        """init function"""
        pass

    @staticmethod
    def get_branch(request):
        """to get all branches"""
        branches = Branch.objects.all()
        serializer = BranchSerializer(branches, many=True)
        return serializer.data

    @staticmethod
    def get_one_branch(request, branch_id):
        """to one branch from all the branches"""
        branch = Branch.objects.get(branch_id=branch_id)
        serializer = BranchSerializer(branch, many=False)
        return serializer.data

    @staticmethod
    def create_branch(request):
        """to create a new branch"""
        serializer = BranchSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            branch = {'data': serializer.data, 'msg': 'successful'}
            return branch
        error_list = [serializer.errors[error][0] for error in serializer.errors]
        message = {'msg': error_list}
        return message

    @staticmethod
    def update_branch(request, branch_id):
        """to update existing branch"""
        branch = Branch.objects.get(branch_id=branch_id)
        serializer = BranchSerializer(instance=branch, data=request.data)
        if serializer.is_valid():
            serializer.save()
        return serializer.data

    @staticmethod
    def delete_branch(request):
        """to delete a branch permanently"""
        data = JSONParser().parse(request)
        branch = Branch.objects.get(branch_id=data['branch_id'])
        branch.delete()
        message = "successfully deleted"
        return message


class Services:
    """creating reading updating deleting services provided"""

    def __init__(self):
        pass

    @staticmethod
    def get_services(request):
        """to get all the services provided details"""
        services = ServicesProvided.objects.all()
        serializer = ServicesSerializer(services, many=True)
        return serializer.data

    @staticmethod
    def create_service(request):
        """to create a new service"""
        serializer = ServicesSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            service = {'data': serializer.data, 'msg': 'successful'}
            return service
        error_list = [serializer.errors[error][0] for error in serializer.errors]
        message = {'msg': error_list}
        return message

    @staticmethod
    def get_one_service(request, service_id):
        """to get one service"""
        service = ServicesProvided.objects.get(service_id=service_id)
        serializer = ServicesSerializer(service, many=False)
        return serializer.data

    @staticmethod
    def update_service(request, service_id):
        """to update an existing service"""
        service = ServicesProvided.objects.get(service_id=service_id)
        serializer = ServicesSerializer(instance=service, data=request.data)
        if serializer.is_valid():
            serializer.save()
        return serializer.data

    @staticmethod
    def delete_service(request):
        """to delete a service permanently"""
        data = JSONParser().parse(request)
        service = ServicesProvided.objects.get(service_id=data['service_id'])
        service.delete()
        message = "successfully deleted"
        return message


class Appointments:
    """creating reading updating deleting appointments"""

    def __init__(self):
        pass

    @staticmethod
    def create_appointment(request):
        """to create a new appointment"""
        serializer = AppointmentSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return serializer.data
        error_list = [serializer.errors[error][0] for error in serializer.errors]
        message = {'msg': error_list}
        return message

    @staticmethod
    def get_booked_appointments(request):
        """to get the list of all booked appointments"""
        appointments = Appointment.objects.filter(Appointment_Status="booked")
        serializer = AppointmentSerializer(appointments, many=True)
        return serializer.data

    @staticmethod
    def get_confirmed_appointments(request):
        """to get the list of all confirmed appointments"""
        appointments = Appointment.objects.filter(Appointment_Status='confirm')
        serializer = AppointmentSerializer(appointments, many=True)
        return serializer.data

    @staticmethod
    def get_complete_appointments(request):
        """to get the list of all completed appointments"""
        appointments = Appointment.objects.filter(Appointment_Status='complete')
        serializer = AppointmentSerializer(appointments, many=True)
        return serializer.data

    @staticmethod
    def get_reject_appointments(request):
        """to get the list of all rejected appointments"""
        appointments = Appointment.objects.filter(Appointment_Status__in=['reject', 'cancel'])
        serializer = AppointmentSerializer(appointments, many=True)
        return serializer.data

    @staticmethod
    def get_one_appointment(request, appointment_id):
        """to get one appointment"""
        appointment = Appointment.objects.get(Appointment_id=appointment_id)
        serializer = AppointmentSerializer(appointment, many=False)
        return serializer.data

    @staticmethod
    def update_appointment(request, appointment_id):
        """to update a appointment"""
        appointment = Appointment.objects.get(Appointment_id=appointment_id)
        serializer = AppointmentSerializer(instance=appointment, data=request.data)
        if serializer.is_valid():
            serializer.save()
        return serializer.data


class Employees:
    """creating updating reading deleting employees"""

    def __init__(self):
        pass

    @staticmethod
    def get_hairstylist(request):
        """to get the employees who work as hairstylist"""
        employees = User.objects.filter(employee__role="HairStylist").values('id',
                                                                             'username', "first_name", "last_name",
                                                                             "email", "employee__emp_id",
                                                                             "employee__role",
                                                                             "employee__emp_contact_number",
                                                                             "employee__branch_id")
        return employees

    @staticmethod
    def get_all_employees(request):
        """to get all the employees"""
        employees = User.objects.filter(is_staff='True', is_superuser="False").values('id',
                                                                                      'username', "first_name",
                                                                                      "last_name", "email",
                                                                                      "employee__emp_id",
                                                                                      "employee__role",
                                                                                      "employee__emp_contact_number",
                                                                                      "employee__branch_id")
        return employees

    @staticmethod
    def get_last_employee(request):
        """to get the last employee joined"""
        employees = Employee.objects.order_by('emp_id').last()
        serializer = EmployeeSerializer(employees, many=False)
        return serializer.data

    @staticmethod
    def create_employee(request):
        """to create a new employee"""
        serializer = Userserializer(data={'username': request.data["username"],
                                          "first_name": request.data["first_name"],
                                          'last_name': request.data["last_name"],
                                          'email': request.data['email'],
                                          "password": request.data['password'], "is_staff": request.data["is_staff"]})
        if serializer.is_valid():
            user = serializer.save()
            employee_object = EmployeeSerializer(data={'user_id': user.id,
                                                       'emp_id': request.data['emp_id'],
                                                       'branch_id': request.data['branch_id'],
                                                       'role': request.data['role'],
                                                       'emp_contact_number': request.data['emp_contact_number']})
            if employee_object.is_valid():
                employee_object.save()
                return serializer.data
            message = "invalid"
            return message
        message = "invalid"
        return message


class Clients:
    """creating reading clients"""

    def __init__(self):
        pass

    @staticmethod
    def clients_list(request):
        """client list"""
        clients = Client.objects.all()
        serializer = ClientSerializer(clients, many=True)
        return serializer.data

    @staticmethod
    def client_register(request):
        """to create a new client"""
        username = request.data.get("username")
        user = User.objects.filter(username=username).first()
        if user:
            message = "username already taken"
            return message
        serializer = Userserializer(data={'username': request.data["username"],
                                          "first_name": request.data["first_name"],
                                          'last_name': request.data["last_name"],
                                          'email': request.data['email'], "password": request.data['password']})
        if serializer.is_valid():
            user = serializer.save()
            client_obj = ClientSerializer(data={'user_id': user.id,
                                                'Client_contact_number': request.data["Client_contact_number"]})
            if client_obj.is_valid():
                client_obj.save()
                client = {'data': serializer.data, 'message': 'success'}
                return client
            message = "invalid"
            return message
        message = "invalid"
        return message

    @staticmethod
    def list_of_clients(request):
        """ to get the list of all clients"""
        clients = User.objects.filter(is_staff='False').values('id',
                                                               'username', "first_name", "last_name", "email",
                                                               "client__id",
                                                               "client__Client_contact_number")
        return clients


class Review:
    """creating reading client reviews"""

    def __init__(self):
        pass

    @staticmethod
    def create_review(request):
        """to add a new review"""
        serializer = ReviewSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            review = {'data': serializer.data, 'msg': 'successful'}
            return review
        error_list = [serializer.errors[error][0] for error in serializer.errors]
        message = {'msg': error_list}
        return message

    @staticmethod
    def get_all_reviews(request):
        """to get all the reviews"""
        reviews = Reviews.objects.all()
        serializer = ReviewSerializer(reviews, many=True)
        return serializer.data


class Transactions:
    """creating reading editing transactions"""

    @staticmethod
    def create_transaction(request):
        """to create a new transaction"""
        serializer = TransactionSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            transaction = {'data': serializer.data, 'msg': 'successful'}
            return transaction
        error_list = [serializer.errors[error][0] for error in serializer.errors]
        message = {'msg': error_list}
        return message

    @staticmethod
    def get_all_transactions(request):
        """to get all the transactions made"""
        transactions = Transaction.objects.all()
        serializer = TransactionSerializer(transactions, many=True)
        return serializer.data
