import io
import json

from django.contrib.sites import requests
from django.shortcuts import render, redirect
from django.views import View
from django.contrib.auth.models import User
from django.http import HttpResponse
from django.contrib.auth.hashers import make_password
from django.contrib.auth import authenticate,login,logout
from rest_framework.decorators import api_view
from rest_framework.permissions import IsAuthenticated
from rest_framework.response import Response

from apps.models import *

from django.views.decorators.csrf import csrf_exempt
from rest_framework.parsers import JSONParser
from django.http.response import JsonResponse

from apps.models import *
from apps.serializers import logSerializer,UserSerializer,LeaveSerializer,AttendenceSerializer,InsuranceSerializer,PersonaldetailsSerializer
from rest_framework.views import APIView
from rest_framework_simplejwt.authentication import JWTAuthentication
from rest_framework_simplejwt.views import TokenObtainPairView
import requests
x = 5
data = [0]
# Create your views here.
# def setcookie(request,a):
#     global x
#     x = 6
#     b = personal_details.objects.get(username = a);
#     print("cookie")
#     serd = PersonaldetailsSerializer(b)
#     print(serd)
#     print(serd.data["id"])
#     # print(b["id"])
#     request.session['id_no']=serd.data["id"]
#     print(x)
#     return x


def logApi(request,id=0):

    if request.method=='GET':
        loggers = log.objects.all()
        log_ser = logSerializer(loggers,many = True)
        return JsonResponse(log_ser.data,safe = False)

class index(View):
    def get(self,request):
        return render(request,"apps/index.html")

class signin(View):
    def post(self,request):
        userdata = JSONParser().parse(request)
        print(userdata)
        # a = dict(userdata)
        # print(a)
        # print(type(a))
        # print(userdata);
        # print(type(userdata));
        # para= io.IOBase(userdata)
        # print(para)
        user_ser = UserSerializer(data=userdata)
        print(user_ser)
        # print ("serializeru:",user_ser)


        if user_ser.is_valid():
            # passw = user_ser.data["password"];
            print(5)
            a = log(user_name=user_ser.data["username"], password= user_ser.data["password"], email="shashi@gmail.com")
            a.save()
            pas = make_password(user_ser.data["password"])
            # print("password to Hash: ", pas)
            # user_ser.data["password"] = pas
            b = User(username= user_ser.data["username"], password= pas)
            b.save()

            # user_ser.save()
            b = "successfully posted"
            return JsonResponse(b, safe=False)
        a = "failed to add"
        return JsonResponse(a,safe=False)
       # username = request.POST["username"]
       # password = request.POST["password"]

       # b = User(username = username,password = pas)
       # b.save()
       # return HttpResponse("registration success!!!!")

class inside(View):
    def post(self,request):
        jsondata = JSONParser().parse(request)
        print(jsondata)
        serdata = UserSerializer(jsondata)
        print(serdata.data["username"])
        user = serdata.data["username"]
        pa = serdata.data["password"]

        user_name = user
        password = pa
        obj = authenticate(username=user_name, password=password)
        print(type(obj))
        print(obj.username)
        if obj is None:
            lf = "login failed";
            return JsonResponse(lf)
        elif obj.username == "hari":
            print("medplusindia")
            # a = leave.objects.all()
            # b = leave.objects.select_related('profile','teamlead').all()
            emp = personal_details.objects.filter(lead_id = Team_lead.objects.get(id = 1))
            print(emp)
            c = []
            d = []
            e =  []
            for i in emp:
                c.append(i.id);
            print(c)
            for i in c :
                d.append(leave.objects.filter(profil_id = i));
            print(d)
            for i in d:
                for j in i:
                   e.append(j)
            print("this")
            print(e)
            global data
            data = e
            print("what?",data[0].id)
            arr = []
            serdata = LeaveSerializer(data,many=True)
            for i in data:
               print( str(i.id) +  "--->" + i.leave_type + "- -->" + i.profil_id.username);
               arr.append(str(i.id) + "--->" +i.leave_type + "- -->" + i.profil_id.username);


            print(arr);
            # print(a)
            # print(b)
            print(data)

            return JsonResponse(arr,safe=False)
        else:
            # url = 'https://127.0.0.1:8000/gettoken'
            # myobj = {'username': 'rishikesh', 'password': 'Medplus1'}
            #
            # x = requests.post(url, json=myobj)
            # print(x.text)
            # redirect(')
            # r = requests.('https://127.0.0.1:8000/gettoken/',
            #                   data={'username': 'rishikesh', 'password': 'Medplus1'})
            # print(r)
            #
            # print()
            # setcookie(request,user)
            # print("habibi")
            # print(request.session['id_no'])
            # print("habibi")
            login(request,obj)
            print(obj.last_login)
            if obj.last_login == None:
               # return render(request,'apps/
               ENQ = 'FIRST TIME'
               return JsonResponse(ENQ,safe=False)
            else:
                ENQ = 'DASHBOARD'
                print("kachoko")
                return JsonResponse(ENQ,safe=False)

class profileupdation(View):
    def post(self,request):
        userdata = JSONParser().parse(request)
        print(userdata)
        logd = log.objects.get(id = userdata["login_id"])
        dep = department.objects.get(id = userdata["dep_id"])
        led = Team_lead.objects.get(id = userdata["lead_id"])
        print(led)
        a = personal_details(username=userdata["username"],full_name=userdata["full_name"],dob=userdata["dob"],login_id = logd,contact_no=userdata["contact_no"],dep_id = dep,lead_id=led)
        a.save()
        k = "succesfullyadded"
        return JsonResponse(k,safe=False)
        # username = request.POST["username"]
        # fullmname = request.POST["fullname"]
        # dob = request.POST["dob"]
        # contact = request.POST["contact"]
        # b = personal_details(full_name=fullmname,dob = dob,contact_no = contact)
        # b.login_id = log.objects.get(user_name = username)
        # b.dep_id = department.objects.get(id = 1)
        # b.lead_id = Team_lead.objects.get(id = 1)
        # b.save()
        #
        # return render(request,'apps/success.html')

class task(View):
    def get(self,request):
        return render(request,"apps/tasks.html")

class taskassign(View):
    def get(self,request):
        return HttpResponse("will review it and share response")
class approveleave(View):
    def get(self,request):
        global data
        print(data)
        # a = leave.objects.get(id = data[0].id)
        # a.delete()
        j = "deleted"
        return JsonResponse(j,safe=False)
class applyleave(View):
    def post(self,request):
        userdata = JSONParser().parse(request)
        print(userdata)
        print(userdata["profil_id"])
        a = personal_details.objects.get(id = userdata["profil_id"])
        print(a)
        userdata["profil_id"] = a;
        print(userdata)

        leave_ser = LeaveSerializer(data=userdata)
        print(leave_ser)
        # LeaveSerializer.data["profile_id"] = a;
        if True:
            print(5)
            k = leave(leave_type = leave_ser.initial_data["leave_type"],profil_id = userdata["profil_id"] )
            k.save()
            l = "leaveapplied"
            return JsonResponse(l,safe=False)
        L = 'FAILED'
        return JsonResponse(L,safe=False)
          # profile_id = request.POST["attid"]
          # leavetype = request.POST["leavetype"]
          # a = leave(leave_type = leavetype,profile_id = personal_details.objects.get(id = profile_id ))
          # a.save()
          # return HttpResponse("SUCCESSFULLY APPILIED")

class att(APIView):
    permission_classes = [IsAuthenticated]
    authentication_classes = [JWTAuthentication]
    def get(self,request):
        # global x
        # jsondata = JSONParser().parse(request)

        # k = json.loads(jsondata)
        # id = jsondata["id"]
        # print(id)
        # ser_data = AttendenceSerializer(data = jsondata)
        # id = ser_data.data["id"]

        # a = Attendence.objects.filter(profil_id = 1)
        # ser_data = AttendenceSerializer(data=a,many=True)
        # arr = []
        # for i in ser_data.initial_data:
        #     arr.append(i)
        # print(arr)
        #
        # print(ser_data.initial_data)
        # # print(arr)
        # abb = []
        # for i in arr:
        #   s_d = AttendenceSerializer(data=i)
        #   abb.append(s_d)
        # print(s_d)
        #
        # return  JsonResponse(arr,safe=False)

        # if request.method == 'GET':
        print("attendence start")
        # print(x)
        loggers = Attendence.objects.filter(profil_id = 5)
        log_ser = AttendenceSerializer(loggers, many=True)
        print(json.dumps(log_ser.data[0]))
        # print(json.dumps(log_ser))
        # print(JsonResponse(log_ser.data, safe=False))
        return JsonResponse(json.dumps(log_ser.data), safe=False)

class ins(APIView):
    permission_classes = [IsAuthenticated]
    authentication_classes = [JWTAuthentication]
    def get(self,request):
        if request.method == 'GET':
          a = insurance.objects.filter(persona_id = 5)
          ser_data = InsuranceSerializer(a,many=True)
          jsondata = json.dumps(ser_data.data)
          print(jsondata)
          return JsonResponse(jsondata,safe=False)

class signout(View):
    def get(self,request):
        # if 'id' in request.session:
        #   del request.session['id']
        if True:
            request.session.flush()
            request.session.modified = True
            # del request.session["id_no"]

            logout(request)
            K = "SESSIONDELETED"
            return JsonResponse(K, safe=False)

        K = "SESSIONNOTDELETED"
        return  JsonResponse(K,safe=False)

