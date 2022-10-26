import io
from django.core.mail import send_mail
from django.views.decorators.csrf import csrf_exempt

from django.http.response import JsonResponse
from .serializers import *
from login.serializers import *
from rest_framework.parsers import JSONParser
from rest_framework.views import APIView ,Response
from rest_framework import status
import random
from rest_framework_simplejwt.tokens import RefreshToken
from employeedetail.serializers import *
from login.serializers import *

def get_token(user):
    refresh=RefreshToken.for_user(user)
    return {
        'refresh':str(refresh),
        'access':str(refresh.access_token)
    }

@csrf_exempt
def vacancy(request):
    if request.method == 'GET':
        data=vaacancy.objects.all()
        data_s=vacancy_serializers(data,many=True)
        return JsonResponse(data_s.data,safe=False)


        return JsonResponse("Error" , safe=False)


@csrf_exempt

def decider(request):

    if request.method=='POST':
        jsondata=request.body
        stream=io.BytesIO(jsondata)
        userdata=JSONParser().parse(stream)

        mail=userdata['mail']
        print(mail[-10:])
        data=candidate_info.objects.filter(mail=mail).values()

        if mail[-10:]!="@gmail.com":
            return JsonResponse('invalid',safe=False)

        elif len(data)==0:
            data={'message':'signup',
                  'otp':str(random.randint(1111,9999))
            }
            send_mail('otp','OTP:-' +data['otp'] +'otp will expire in 2 min','hightechotp@gmail.com',[mail])
            return JsonResponse(data,safe=False)

        elif len(data)==1:

            return JsonResponse('login',safe=False)



class apply(APIView):
    def post(self,request,format=None):
        data=request.data
        print(data)

        cid=candidate_info.objects.filter(mail=data[1]).values()
        applied_vacancy = assesment.objects.filter(cid=cid[0]['cid'], vid=data[0]).values()
        if len(applied_vacancy)>0:
            return JsonResponse('already applied',safe=False)
        print(type(cid),cid)
        applydata = {
            "cid":cid[0]['cid'],
            "vid":data[0],
            "name":cid[0]['name']
        }
        print(applydata)
        serializer=assesment_serializers(data=applydata)


        if serializer.is_valid() :
            serializer.save()

            return JsonResponse('done', safe=False)
        return Response(serializer.errors,status=status.HTTP_400_BAD_REQUEST)


class signup(APIView):
    def post(self,request,format=None):
        data=request.data
        cid=candidate_info.objects.all()
        data['cid']=len(cid)
        logindata = {
            "cid":data['cid'],
            "loginid":data['mail'],
            "password":data['password']

        }

        serializer=candidate_info_serializers(data=data)
        lserializer=apply_candiate_login_serializers(data=logindata)
        print(data,logindata)

        if serializer.is_valid() :
            serializer.save()
            if lserializer.is_valid():
               lserializer.save()
               return JsonResponse('done', safe=False)
        return Response(serializer.errors,status=status.HTTP_400_BAD_REQUEST)


class create(APIView):
    def post(self,request,format=None):
        data=request.data

        data['vid']=vaacancy.objects.all().count()+1
        print(data)
        print(data['designeation'])
        print(data)

        data['designeation']=(designation.objects.filter(designation_name=data['designeation']).values())[0]['designation_id']

        print(data)
        serializer=vacancy_serializers(data=data)



        if serializer.is_valid() :
            serializer.save()

            return JsonResponse('done', safe=False)
        return Response(serializer.errors,status=status.HTTP_400_BAD_REQUEST)
class delete(APIView):
    def post(self,request):
        data=request.data
        print(data)
        record=vaacancy.objects.filter(vid=data)
        if record.count()==1:
            record.delete()
            return JsonResponse('done', safe=False)
        return JsonResponse('invalid request',safe=False)

class applied(APIView):
    def post(self,request):

        send_data=[]
        datas={
            "vid": '',
            "designeation": '',
            "salary": "''",
            "skill": "",
            "experience": "",
            "no_of_vacancy": "",
            "status":"False"
        }
        data=request.data
        vdata = vaacancy.objects.all().values()
        cid=apply_candiate_login.objects.filter(loginid=data['mail']).values()


        i=0

        while(i<len(vdata)):
            datas['status']='False'
            datas['vid']=vdata[i]['vid']
            datas['designeation']=vdata[i]['designeation_id']
            datas['salary']=vdata[i]['salary']
            datas['experience']=vdata[i]['experience']
            datas['vno_of_vacancyid']=vdata[i]['no_of_vacancy']
            applied_vacancy = assesment.objects.filter(cid=cid[0]['cid_id'], vid=vdata[i]['vid']).values()

            if len(applied_vacancy)>0:
                print(applied_vacancy, vdata[i]['vid'], len(applied_vacancy),datas['status'])
                datas['status']='True'
                print(applied_vacancy, vdata[i]['vid'], len(applied_vacancy), datas['status'])
          #  print(applied_vacancy, vdata[i]['vid'], len(applied_vacancy), datas['status'])

            send_data.append(datas)
         #   print(datas)
        #    print(vdata[i])
            print(send_data)
            i = i + 1
       # print(send_data)

        return JsonResponse(send_data, safe=False)

class application(APIView):
    def post(self,request):
        data = request.data
        print(data)

        cid = apply_candiate_login.objects.filter(loginid=data['mail']).values()
        # print(cid)
        applied_data = assesment.objects.filter(cid=cid[0]['cid_id']).values()
        # print(data)
        # print(vdata)
        # print(cid)
        # print(applied_data)
        v=[]
        a=[]

        for i in applied_data:
            vdata = vaacancy.objects.filter(vid=i['vid_id']).values()
            print(vdata[0]['designeation_id'])
            vdata =designation.objects.get(designation_id=vdata[0]['designeation_id'])
            v.append(vdata)
            adata = assesment.objects.get(vid=i['vid_id'])
            a.append(adata)

        print(v)


        data_s2 = assesment_serializers(a, many=True)
        data_s1 = designation_serializers(v, many=True)

        return JsonResponse(data_s2.data, safe=False)
class applicant(APIView):
    def get(self,request):
        data=request.GET.get('vid')
        print(data)
        assesment_data=assesment.objects.filter(vid=data)
        data_s=assesment_serializers(assesment_data,many=True)
        return JsonResponse(data_s.data,safe=False)

class action(APIView):
    def post(selfs,request):
        data=request.data
        userdata = assesment.objects.get(cid=data['cid'])
        if data['action']=='reject':
            userdata.update = 'rejected'
            userdata.result = 'rejected'
            userdata.save()
            return JsonResponse('done',safe=False)

        if(userdata.update=='APPLICATION UNDER REVIEW'):
           #userdata[0]['update']='Apptitute schedule on 20-10-2022'
           userdata.update='Apptitute schedule on 20-10-2022'
           userdata.result='apptitute'

           userdata.save()
           return JsonResponse('done', safe=False)
        if (userdata.update == 'Apptitute schedule on 20-10-2022'):
            # userdata[0]['update']='Apptitute schedule on 20-10-2022'
            userdata.update = 'Codeing schedule on 21-10-2022'
            userdata.result = 'codeing'

            userdata.save()
            return JsonResponse('done', safe=False)
        if (userdata.update == 'Codeing schedule on 21-10-2022'):
            # userdata[0]['update']='Apptitute schedule on 20-10-2022'
            userdata.update = 'Interview1 schedule on 22-10-2022'
            userdata.result = 'interview1'

            userdata.save()
            return JsonResponse('done', safe=False)
        if (userdata.update == 'Interview1 schedule on 22-10-2022'):
            # userdata[0]['update']='Apptitute schedule on 20-10-2022'
            userdata.update = 'Interview2 schedule on 23-10-2022'
            userdata.result = 'interview2'

            userdata.save()
        if userdata.interview2!='pending':
            userdata.update='selected'
            userdata.result='selected'
            userdata.save()
            return JsonResponse('done', safe=False)
class enter(APIView):
    def post(self,request):
        data=request.data
        print(data)
        userdata=assesment.objects.get(cid=data['cid'])
        if (userdata.result == 'apptitute'):
            userdata.apptitute=data['marks']
            userdata.save()
            return JsonResponse('done', safe=False)
        if (userdata.result == 'codeing'):
            userdata.codeing=data['marks']
            userdata.save()
            return JsonResponse('done', safe=False)
        if (userdata.result == 'interview1'):
            userdata.interview1=data['marks']
            userdata.save()
            return JsonResponse('done', safe=False)
        if (userdata.result == 'interview2'):
            userdata.interview2=data['marks']
            userdata.result='aaaa'
            userdata.save()
            return JsonResponse('done', safe=False)






#






