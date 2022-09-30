from django.http import JsonResponse

from .models import Branch, User, services_provided,Employee

from .serializers import BranchSerializer, EmployeeSerializer, ServicesSerializer, Userserializer,ClientSerializer

from rest_framework.views import APIView

from rest_framework.decorators import api_view

from rest_framework.response import Response

from rest_framework import status

from rest_framework.permissions import AllowAny

from django.contrib.auth import authenticate,login,logout

# @api_view(['GET','POST'])
# def user_list(request):

#     if request.method  == 'GET':
#         users = User.objects.all()
#         serializer = Userserializer(users,many=True)
#         return JsonResponse(serializer.data,safe = False)

#     if request.method == 'POST':
#         serializer = Userserializer(data=request.data)
#         if serializer.is_valid():
#             serializer.save()
#             return Response(serializer.data,status = status.HTTP_201_CREATED)

class UserList(APIView):
    def get(self, request):
        users = User.objects.all()
        serializer = Userserializer(users, many=True)
        return Response(serializer.data)

    def post(self,request):
        serializer = Userserializer(data=request.data)
        print(serializer)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        else:
            print('invalid')
            return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

class BranchList(APIView):
    def get(self,request):
        branches = Branch.objects.all()
        serializer = BranchSerializer(branches, many=True)
        return Response(serializer.data)

    def post(self,request):
        serializer = BranchSerializer(data=request.data)
        print(serializer)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        else:
            print('invalid')
            return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

class ServicesList(APIView):
    def get(self,request):
        services = services_provided.objects.all()
        serializer = ServicesSerializer(services, many=True)
        return Response(serializer.data)

    def post(self,request):
        serializer = ServicesSerializer(data=request.data)
        print(serializer)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        else:
            print('invalid')
            return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)


class EmployeeList(APIView):
    def get(self,request):
        employees = Employee.objects.filter(User__is_staff = 'True')
        serializer = EmployeeSerializer(employees,many=True)
        return Response(serializer.data)

class ClientRegistration(APIView):
    def post(self,request):
        serializer = Userserializer(data={'username': request.data["username"],"first_name":request.data["first_name"],'last_name':request.data["last_name"],'email':request.data['email'],"password":request.data['password']})
        if serializer.is_valid():
           Client =  serializer.save()
           client_obj = ClientSerializer(data={'Client':Client.id,'Client_contact_number':request.data["Client_contact_number"]})
           if client_obj.is_valid():
                client_obj.save()
                return Response({'data':serializer.data,'message':'successfully registered'},status=200)
        else:
            return Response({'message':'invalid'})

class LoginUser(APIView):
    def post(self,request):
        if request.method == 'POST':
            username = request.data.get("username")
            password = request.data.get('password')
            try:
                user = User.objects.get(username = username)
            except:
                return Response({'msg':"User Does not Exist"})

            user = authenticate(request, username = username, password = password)
            if user is not None:
                login(request, user)
                return Response({'msg':"logged in" , 'user': user.username} ,status=200)
            else:
                return Response({'msg':"password incorrect" })

        return Response({"msg":"not created"},status =200)