#pylint:disable=E1101 
#pylint:disable=C0412
#pylint:disable=R1705
"""views for handling appointment"""
import json
from django.db.models import Q
from rest_framework import status
from rest_framework.parsers import JSONParser
from django.http.response import JsonResponse
from rest_framework.response import Response
from rest_framework.views import APIView
from users.models import Customer, Staff
from .models import Branch, Appointment, Lab, Bill, Test, Review, Report
from .serializers import TestSerializer, BranchSerializer, AppointmentSerializer,GetAppointmentSerializer, LabSerializer, ReviewSerializer, BillSerializer, ReportSerializer



class DetailsForBooking(APIView):
    """details needed for booking appointment"""
    @staticmethod
    def get():
        """get method for all"""
        doctors = Staff.objects.filter(designation='Doctor')
        doctors = list(doctors.values("staff_id", "user_id__username"))
        nurses = Staff.objects.filter(designation='Nurse')
        nurses = list(nurses.values("staff_id", "user_id__username"))
        lab_technicians = Staff.objects.filter(designation='Lab Technician')
        lab_technicians = list(lab_technicians.values("staff_id",
                                                      "user_id__username"))
        sample_collectors = Staff.objects.filter(designation='Sample Collector')
        sample_collectors = list(sample_collectors.values("staff_id",
                                                          "user_id__username"))
        tests = Test.objects.all()
        tests = TestSerializer(tests, many=True)
        branches = Branch.objects.all()
        branches = BranchSerializer(branches, many=True)
        users = Customer.objects.all()
        users = list(users.values('customer_id', 'user_id__username'))

        return Response({'doctors': json.dumps(doctors), 'nurses': json.dumps(nurses),
                         'lab_technicians': json.dumps(lab_technicians),
                         'sample_collectors': json.dumps(sample_collectors),
                         'tests': tests.data, 'branches': branches.data,
                         'users': json.dumps(users)}, status=200)


# ----------------- views related appointments --------------------------

class DetailAppointment(APIView):
    """detail view of appointment"""
    @staticmethod
    def get(app_id):
        """get method for appointment"""
        appointment = Appointment.objects.get(appointment_id=app_id)
        appointments_tests = list(appointment.tests.all().values(
                        'test_id', 'test_name', 'test_description'))
        serializer = AppointmentSerializer(appointment, many=False)
        return Response({'appointment': serializer.data, 'related_tests': appointments_tests},
                        status=200)

    @staticmethod
    def delete(app_id):
        """delete method for appointment"""
        appointment = Appointment.objects.filter(appointment_id=app_id).first()
        if appointment:
            appointment.delete()
            return JsonResponse(data={'success': 'Appointment Data deleted successfully.'},
                                safe=False)
        else:
            return JsonResponse(data={'success': 'Appointment Data is not deleted successfully.'},
                                safe=False)

    @staticmethod
    def put(request, app_id):
        """update method for appointment"""
        appointment_data = JSONParser().parse(request)
        appointment = Appointment.objects.get(appointment_id=app_id)
        serializer = AppointmentSerializer(appointment, data=appointment_data)
        if serializer.is_valid():
            serializer.save()
            return JsonResponse({"message": "Appointment Created", "action_status": "success"},
                                status=status.HTTP_201_CREATED, safe=False)
        # return JsonResponse(serializer.errors, status=status.HTTP_400_BAD_REQUEST, safe=False)
        else:
            error_list = [serializer.errors[error][0] for error in serializer.errors]
            return Response({"message": error_list, "action_status": "failure"}, status=200)


class FilterAppointment(APIView):
    """filter appointments"""
    @staticmethod
    def get(request):
        """get for filter"""
        text = request.GET['text']
        appointments = Appointment.objects.filter(Q(user__user_id__username__icontains=text)
                                                  |Q(user__customer_id__icontains=text))
        appointments_tests = []
        for appointment in appointments:
            each_appointment_tests = list(appointment.tests.all().values(
                'test_id', 'test_name', 'test_description'
            ))
            serializer = TestSerializer(each_appointment_tests, many=True)
            appointments_tests.append(each_appointment_tests)
        appointments_tests_data = json.dumps(appointments_tests)
        serializer = GetAppointmentSerializer(appointments, many=True)
        return Response({'appointments': serializer.data,
                         'related_tests': appointments_tests_data}, status=200)


class AppointmentAPI(APIView):
    """ api view for appointment addding and get all  """
    @staticmethod
    def get(request):
        """get all appointment"""
        appointments = Appointment.objects.all()
        appointments_tests = []
        for appointment in appointments:
            each_appointment_tests = list(appointment.tests.all().values(
                'test_id', 'test_name', 'test_description'
            ))
            serializer = TestSerializer(each_appointment_tests, many=True)
            appointments_tests.append(each_appointment_tests)
        appointments_tests_data = json.dumps(appointments_tests)
        serializer = GetAppointmentSerializer(appointments, many=True)
        return Response({'appointments': serializer.data, 'related_tests':
                         appointments_tests_data}, status=200)

    @staticmethod
    def post(request):
        """post for appointment"""
        print(request.data)
        data = request.data.get('form')
        print(data)
        apmt = AppointmentSerializer(data=data)
        if apmt.is_valid():
            apmt.save()
            return JsonResponse({"message": "Branch Created", "action_status": "success"},
                                status=status.HTTP_201_CREATED, safe=False)
        # return JsonResponse(serializer.errors, status=status.HTTP_400_BAD_REQUEST, safe=False)
        else:
            error_list = [apmt.errors[error][0] for error in apmt.errors]
            return Response({"message": error_list, "action_status": "failure"}, status=200)


class CustomerAppointments(APIView):
    """customer booking appointment api"""
    @staticmethod
    def get(cust_id):
        """get customer apmnt"""
        appointments = Appointment.objects.filter(user__customer_id=cust_id)
        appointments_tests = []
        for appointment in appointments:
            each_appointment_tests = list(appointment.tests.all().values(
                'test_id', 'test_name', 'test_description'
            ))
            # serializer = TestSerializer(each_appointment_tests, many=True)
            appointments_tests.append(each_appointment_tests)
        appointments_tests_data = json.dumps(appointments_tests)
        serializer = GetAppointmentSerializer(appointments, many=True)
        return Response({'appointments': serializer.data, 'related_tests': appointments_tests_data},
                        status=200)


# ----------------- views related branches --------------------------


class FilterBranches(APIView):
    """filter branches"""
    @staticmethod
    def get(request):
        """filter branches"""
        text = request.GET['text']
        branches = Branch.objects.filter(Q(branch_id__icontains=text)
                                         |Q(branch_name__icontains=text)
                                         |Q(location__icontains=text))
        serializer = BranchSerializer(branches, many=True)
        return Response({"branches":serializer.data}, status=200)


class DetailBranch(APIView):
    """detail branch"""
    @staticmethod
    def get(_id):
        """get detail of branch"""
        branch = Branch.objects.get(branch_id=_id)
        serializer = BranchSerializer(branch, many=False)
        return Response({'branch': serializer.data}, status=200)

    @staticmethod
    def delete(_id):
        """delete branch"""
        branch = Branch.objects.get(branch_id=_id)
        if branch:
            branch.delete()
            return JsonResponse(data={'success': 'Branch deleted successfully.'}, safe=False)
        return JsonResponse(
            data={'Failure': 'Branch doesn\'t exists '}, safe=False)

    @staticmethod
    def put(request, _id):
        """update branch"""
        data = request.data.get('form')
        branch = Branch.objects.get(branch_id=_id)
        serializer = BranchSerializer(branch, data=data)
        if serializer.is_valid():
            serializer.save()
            return JsonResponse({"message": "Branch Created", "action_status": "success"},
                                 status=status.HTTP_201_CREATED, safe=False)
        else:
            error_list = [serializer.errors[error][0] for error in serializer.errors]
            return Response({"message":error_list, "action_status": "failure"}, status=200)


class   BranchAPI(APIView):
    """branch api"""
    @staticmethod
    def get(request):
        """get all branch"""
        branches = Branch.objects.all()
        serializer = BranchSerializer(branches, many=True)
        return Response(serializer.data, status=200)

    @staticmethod
    def post(request):
        """add branch"""
        print(request.data)
        data = request.data.get('form')
        serializer = BranchSerializer(data=data)
        if serializer.is_valid():
            serializer.save()
            return Response({"message": "New Branch Created", "action_status": "success"}, status=200)
        else:
            error_list = [serializer.errors[error][0] for error in serializer.errors]
            return Response({"message":error_list, "action_status": "failure"}, status=200)


# ----------------- views related lab --------------------------


class DetailLab(APIView):
    """detail lab api view"""

    @staticmethod
    def get(_id):
        """get detail of branch"""
        lab = Lab.objects.get(lab_id=_id)
        serializer = LabSerializer(lab, many=False)
        return Response({'lab': serializer.data}, status=200)

    @staticmethod
    def put(request,_id):
        """update lab"""
        data = request.data.get('form')
        lab = Lab.objects.get(lab_id=_id)
        serializer = LabSerializer(lab, data=data)
        if serializer.is_valid():
            serializer.save()
            return Response({"message": "Lab updated", "action_status": "success"},
                            status=200)
        else:
            error_list = [serializer.errors[error][0] for error in serializer.errors]
            return Response({"message":error_list, "action_status": "failure"},
                            status=200)

    @staticmethod
    def delete(_id):
        """delete lab"""
        lab = Lab.objects.get(lab_id=_id)
        if lab:
            lab.delete()
            return JsonResponse(data={'success': 'lab deleted successfully.'}, safe=False)
        return JsonResponse(
            data={'Failure': 'lab doesn\'t exists . So, lab could not be deleted successfully.'},
            safe=False)


class LabAPI(APIView):
    """lab api"""
    @staticmethod
    def get():
        """get all labs"""
        labs = list(Lab.objects.all().values(
            'lab_id', 'lab_name', 'branch__branch_id',
            'branch__branch_name'
        ))
        return Response({'labs':json.dumps(labs)}, status=200)

    @staticmethod
    def post(request):
        """add lab"""
        print(request.data)
        data = request.data.get('form')
        serializer = LabSerializer(data=data)
        if serializer.is_valid():
            serializer.save()
            return Response({"message": "New Lab Created", "action_status": "success"},
                            status=200)
        else:
            error_list = [serializer.errors[error][0] for error in serializer.errors]
            return Response({"message": error_list, "action_status": "failure"},
                            status=200)

    
# ------------------- test views ----------------------------
class DetailTest(APIView):
    @staticmethod
    def put(request,_id):
        """update test"""
        data = request.data.get('form')
        test = Test.objects.get(test_id=_id)
        serializer = TestSerializer(test, data=data)
        if serializer.is_valid():
            serializer.save()
            return Response({"message": "Test Updated", "action_status": "success"})
        else:
            error_list = [serializer.errors[error][0] for error in serializer.errors]
            return Response({"message": error_list, "action_status": "failure"}, status=200)
        # return JsonResponse(serializer.errors, status=status.HTTP_400_BAD_REQUEST, safe=False)

    @staticmethod
    def delete(_id):
        """delete post"""
        test = Test.objects.get(lab_id=_id)
        if test:
            test.delete()
            return JsonResponse(data={'success': 'Test deleted successfully.'},
                                safe=False)
        return JsonResponse(
            data={'Failure': 'Test doesn\'t exists'}, safe=False)


class TestAPI(APIView):
    """api for tests in lab"""
    @staticmethod
    def get():
        """get all tests"""
        tests = Test.objects.all()
        serializer = TestSerializer(tests, many=True)
        return Response({"tests": serializer.data, "action_status": "success"}, status=200)

    @staticmethod
    def post(request):
        """add test"""
        print(request.data)
        data = request.data.get('form')
        serializer = TestSerializer(data=data)
        if serializer.is_valid():
            serializer.save()
            return Response({"message": "New Test Created", "action_status": "success"})
        else:
            error_list = [serializer.errors[error][0] for error in serializer.errors]
            return Response({"message": error_list, "action_status": "failure"}, status=200)

    

# -------------------------review ------------------------

class DetailReview(APIView):
    @staticmethod
    def put(request,_id):
        """update review"""
        data = request.data.get('form')
        review = Review.objects.get(id=_id)
        serializer = ReviewSerializer(review, data=data)
        if serializer.is_valid():
            serializer.save()
            return Response({"message": "Review Updated", "action_status": "success"})
        else:
            error_list = [serializer.errors[error][0] for error in serializer.errors]
            return Response({"message": error_list, "action_status": "failure"}, status=200)

    @staticmethod
    def delete(_id=""):
        """delete review"""
        review = Review.objects.get(lab_id=_id)
        if review:
            review.delete()
            return JsonResponse(data={'success': 'Test deleted successfully.'}, safe=False)
        return JsonResponse(
            data={'Failure': 'Test doesn\'t exists '},
            safe=False)


class ReviewAPI(APIView):
    """view for reviews """
    @staticmethod
    def get():
        """get all reviews """
        reviews = list(Review.objects.all().values(
            'id', 'user_id__username', 'rating', 'comment'
        ))
        # serializer = ReviewSerializer(reviews, many=True)
        return Response(json.dumps(reviews), status=200)

    @staticmethod
    def post(request):
        """add review"""
        print(request.data)
        data = request.data.get('form')
        serializer = ReviewSerializer(data=data)
        if serializer.is_valid():
            serializer.save()
            return Response({"message": "Review Created", "action_status": "success"})
        else:
            error_list = [serializer.errors[error][0] for error in serializer.errors]
            return Response({"message": error_list, "action_status": "failure"}, status=200)

    

# ------------------------------ bill ----------------------


class DetailBill(APIView):
    """detail bill api"""
    @staticmethod
    def delete(_id):
        """delete bill"""
        bill = Bill.objects.get(id=_id)
        if bill:
            bill.delete()
            return JsonResponse(data={'success': 'Bill Details deleted successfully.'}, safe=False)
        return JsonResponse(
            data={'Failure': 'Bill Doesn\'t exists.'}, safe=False)

    @staticmethod
    def put(request):
        """update bill"""
        data = request.data.get('form')
        bill = Bill.objects.get(id=data['id'])
        serializer = BillSerializer(bill, data=data)
        if serializer.is_valid():
            serializer.save()
            return Response({"message": "Bill Updated", "action_status": "success"})
        else:
            error_list = [serializer.errors[error][0] for error in serializer.errors]
            return Response({"message": error_list, "action_status": "failure"}, status=200)

    @staticmethod
    def get(_id):
        """get bill"""
        try:
            bill = list(Bill.objects.get(id=_id).values(
                'id', 'appointment__appointment_id', 'appointment__user__user_id__username',
                'consultation_fee', 'test_fee', 'tax', 'total'
            ))
            # serializer = BillSerializer(bills, many=True)
        except Exception as error:
            return Response(str(error), status=500)
        return Response(json.dumps(bill), status=200)


class BillAPI(APIView):
    """bill view"""
    @staticmethod
    def get():
        """get all bills"""
        try:
            bills = list(Bill.objects.all().values(
                'id', 'appointment__appointment_id', 'appointment__user__user_id__username',
                'consultation_fee', 'test_fee', 'tax', 'total'
            ))
            # serializer = BillSerializer(bills, many=True)
        except Exception as error:
            return Response(str(error), status=500)
        return Response(json.dumps(bills), status=200)

    @staticmethod
    def post(request):
        """add bill"""
        print(request.data)
        data = request.data.get('form')
        serializer = BillSerializer(data=data)
        if serializer.is_valid():
            serializer.save()
            return Response({"message": "Bill Created", "action_status": "success"})
        else:
            error_list = [serializer.errors[error][0] for error in serializer.errors]
            return Response({"message": error_list, "action_status": "failure"}, status=200)


# ------------------report -----------------

class ReportAPI(APIView):
    """report for appointment"""
    @staticmethod
    def get():
        """get all reports"""
        try:
            reports = list(Report.objects.all().values(
                'id', 'appointment__appointment_id', 'appointment__user__username',
                'description', 'report_type'
            ))
            # serializer = ReportSerializer(reports, many=True)
        except Exception as error:
            return Response(str(error), status=500)
        return Response(json.dumps(reports), status=200)
        # return Response(json.dumps(serializer.data), status=200)

    @staticmethod
    def post(request):
        """Add report"""
        print(request.data)
        data = request.data.get('form')
        serializer = ReportSerializer(data=data)
        if serializer.is_valid():
            serializer.save()
            return Response({"message": "Review Added", "action_status": "success"})
        else:
            error_list = [serializer.errors[error][0] for error in serializer.errors]
            return Response({"message": error_list, "action_status": "failure"}, status=200)

    
class DetailReport(APIView):
    @staticmethod
    def put(request,_id):
        """update review"""
        data = request.data.get('form')
        report = Report.objects.get(id=_id)
        serializer = ReportSerializer(report, data=data)
        if serializer.is_valid():
            serializer.save()
            return Response({"message": "review Updated", "action_status": "success"})
        else:
            error_list = [serializer.errors[error][0] for error in serializer.errors]
            return Response({"message": error_list, "action_status": "failure"}, status=200)

    @staticmethod
    def delete(_id=""):
        """delete report"""
        report = Report.objects.get(lab_id=_id)
        if report:
            report.delete()
            return JsonResponse(data={'success': 'Test deleted successfully.'}, safe=False)
        return JsonResponse(
            data={'Failure': 'Test doesn\'t exists . So, Branch could not be deleted successfully.'},
            safe=False)
