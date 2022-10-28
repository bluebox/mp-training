from django.shortcuts import render
from users.models import Appointment, Doctor, Patients, DatewiseSlot, Staff,AllUser
from rest_framework import viewsets
from users.serializers import AppointmentSerializer, DatewiseSlotSerializer, DoctorSerializer, PatientSerializer, StaffSerializer,AllUserSerializer
from rest_framework.views import APIView
from rest_framework.response import Response
from rest_framework.exceptions import AuthenticationFailed
from datetime import timedelta
import jwt,datetime
# Create your views here.
class PatientViewSet(viewsets.ModelViewSet):
    """
    A viewset for viewing and editing user instances.
    """
    queryset = Patients.objects.all()
    serializer_class = PatientSerializer
   
class DoctorViewSet(viewsets.ModelViewSet):
    """
    A viewset for viewing and editing user instances.
    """
    queryset = Doctor.objects.all()
    serializer_class = DoctorSerializer
    
class StaffViewSet(viewsets.ModelViewSet):
    """
    A viewset for viewing and editing user instances.
    """
    queryset = Staff.objects.all()
    serializer_class = StaffSerializer
class DatewiseSlotViewSet(viewsets.ModelViewSet):
    """
    A viewset for viewing and editing user instances.
    """
    queryset = DatewiseSlot.objects.all()
    serializer_class = DatewiseSlotSerializer
class AppointmentViewSet(viewsets.ModelViewSet):
    """
    A viewset for viewing and editing user instances.
    """
    queryset =Appointment.objects.all()
    serializer_class = AppointmentSerializer
class AllUsersViewSet(viewsets.ModelViewSet):
    """
    A viewset for viewing and editing user instances.
    """
    queryset = AllUser.objects.all()
    serializer_class =AllUserSerializer

class UserView(APIView):

    def get(self, request):
        user_obj =  AllUser.objects.all()
        serializer = AllUserSerializer(user_obj, many=True)
        return Response({
            'status': True,
            'message': 'user fetched',
            'data': serializer.data
        })

    def post(self,request):
        try:
            data = request.data
            serializer = AllUserSerializer(data=data)
            if serializer.is_valid():
                serializer.save()  # save data in database
                print(serializer.data)
                return Response({
                    'status': True,
                    'message': 'success data!!!',
                    'data': serializer.data
                })
            return Response({
                'status': False,
                'message': 'something went wrong!!',
                'data': serializer.errors
            })

        except Exception as e:
            print(e)
        return Response({
            'status': False,
            'message': 'something went wrong!!!'
        })

class Login(APIView):
    def post(self,request):
        email=request.data['email']
        password=request.data['password']
        print(email, password)
        print(AllUser.objects.all())
        user=AllUser.objects.filter(email=email).first()
        # print(user.password, password)
        if user is None:
            raise AuthenticationFailed("user not found")
        if user.password != password:
            raise AuthenticationFailed("password incorrect")
        payload={
            'email':user.email,
            "exp":datetime.datetime.utcnow()+datetime.timedelta(minutes=60),
            'iat':datetime.datetime.utcnow()
        }
        token=jwt.encode(payload,'secret',algorithm='HS256').decode('utf-8')
        response=Response()
        response.set_cookie(key="jwt",value=token,httponly=True,samesite="Lax")
        response.data={
            'message':'sucess',
            'jwt':token
        }
        return response
class GetUser(APIView):
    def get(self,request):
        token=request.COOKIES.get('jwt')
        print(token)
        if not token:
            raise AuthenticationFailed("unauthenticated")

        try:
            print('in')
            payload=jwt.decode(token,'secret',algorithm=['HS256']);
            print(payload)
        except:
            raise AuthenticationFailed("invalid")
        user = AllUser.objects.filter(email=payload['email']).first()
        print(user)
        serializer = AllUserSerializer(user)
        print(serializer.data)
        return Response(serializer.data)


class Logout(APIView):
    def post(self,request):
        response=Response()
        response.delete_cookie('jwt')
        response.data={
            'message':"sucess"
        }
        return response
class SlotFilter(APIView):
    def get(self,request,doctor,date):
        # doctor=request.data['doctor']
        # date=request.data['date']
        slot=DatewiseSlot.objects.filter(doctor=doctor , date=date).values()
        print(slot)
        return Response(slot)