import imp
from urllib import response
from django.shortcuts import render
from rest_framework.views import APIView
from rest_framework.response import Response
from .models import *
from .serializers import *
import geopy.distance
from django.utils import timezone
import datetime
from django.http import JsonResponse
from cloudinary.uploader import upload
from django.core.serializers.json import DjangoJSONEncoder
import json
from rest_framework.parsers import JSONParser


# Create your views here.

#initial home page

class Property(APIView):
    def get(self,request,id):
        properties=Properties.objects.filter(id=id)
        for i in properties:
            temp=i.type.all()
        t=list(temp.values())
        s=list(properties.values())
        return JsonResponse(s+t,safe=False)
   
class All_Properties(APIView):
    def get(self,request):
        properties=Properties.objects.all().values()
        s=list(properties)
        return JsonResponse(s,safe=False)
        # serializer = PropertySerializer(properties,many=True)
        # return Response(serializer.data)

class like_func(APIView):
    def get(self,request,p_id,c_id):
        if(Liked.objects.filter(property=p_id,customer=c_id)):
            pass
        else:
            item=Liked()
            item.property=Properties.objects.get(id=p_id)
            item.customer=User.objects.get(id=c_id)
            item.save()
        response = Response()
        response.data = {
            'message': 'success'
        }
        return response
       



#home page after applying filters
#pass filtering user coords in request
class Filtered_Properties(APIView):
    def post(self,request):
        properties=Properties.objects.all()

        if request.data['property_type'] != "":
            properties=properties.filter(type__property_type=request.data['property_type'])
        if request.data['bhk_type'] != "":
            properties=properties.filter(type__bhk_type=request.data['bhk_type'])
        if request.data['furnishing'] != "":
            properties=properties.filter(type__furnishing=request.data['furnishing'])
        if request.data['aminities'] != "":
            properties=properties.filter(type__aminities=request.data['aminities'])
        if request.data['residence_type'] != "":
            properties=properties.filter(type__residence_type=request.data['residence_type'])
        if request.data['gender'] != "":
            properties=properties.filter(type__gender=request.data['gender'])
        if request.data['distance']:
            coords_2=(request.data['lat'],request.data['long'])
            for item in properties:
                lat1=item.lat
                lon1=item.long
                coords_1=(lat1,lon1)
                if(geopy.distance.geodesic(coords_1, coords_2).km>request.data['distance']):
                    properties = properties.exclude(id=item.id)
        for item in properties:
            if(item.price<=request.data['min_rainge'] or item.price>=request.data['max_rainge']):
                    properties = properties.exclude(id=item.id)

        s=list(properties.values())
        return JsonResponse(s,safe=False)



class Register_Property(APIView):
    def post(self,request):
        item=TypeTable()
        item.property_type = request.data['property_type']
        item.bhk_type = request.data['bhk_type']
        item.gender = request.data['gender']
        item.room_type = request.data['room_type']
        item.furnishing = request.data['furnishing']
        item.aminities = request.data['aminities']
        item.residence_type = request.data['residence_type']
        item.save()

        ite=Properties()
        ite.name = request.data['name']
        ite.description =request.data['description']
        ite.price = request.data['price']
        ite.long = request.data['long']
        ite.lat = request.data['lat']
        ite.image=request.data.get('image')
        ite.malik=User.objects.filter(id=request.data['id']).first()
        ite.save()
        ite.type.add(item)
        ite.save()

        it=Owner()
        it.owner = User.objects.filter(id=request.data['id']).first()
        it.property = ite
        it.save()

        i=NewArrivals()
        i.datetime = datetime.datetime.now(tz=timezone.utc)
        i.property = ite
        i.save()

        response = Response()
        response.data = {
            'message': 'success'
        }
        return response

class upload_file(APIView):
    def post(self,request):
        temp=upload(request.data.get('image'))
        return Response(temp['secure_url'])

        
class wishlist(APIView):
    def get(self,request,id):
        use=User.objects.get(id=id)
        temp=Liked.objects.filter(customer=use).values()
        s=list(temp)
        kemp=list()
        for item in s:
            prop=Properties.objects.filter(id=item['property_id']).values()
            kemp.append(json.dumps(list(prop), cls=DjangoJSONEncoder))
        return JsonResponse(kemp,safe=False)
class delete(APIView):
    def delete(self,request,id,cid):
        temp=Liked.objects.filter(property=id,customer=cid)
        temp.delete()
        response = Response()
        response.data = {
            'message': 'success'
        }
        return response

class one_filter(APIView):
    def post(self,request):
        if request.data.get('str')=='buy':
            properties=Properties.objects.filter(type__residence_type = 'buy').values()
        if request.data.get('str')=='lease':
            properties=Properties.objects.filter(type__residence_type = 'lease').values()
        if request.data.get('str')=='rent':
            properties=Properties.objects.filter(type__residence_type = 'rent').values()
        if request.data.get('str')=='coliving':
            properties=Properties.objects.filter(type__room_type = 'coliving').values()
        if request.data.get('str')=='villa':
            properties=Properties.objects.filter(type__property_type = 'villa').values()

        s=list(properties)
        return JsonResponse(s,safe=False)
class add_Review(APIView):
    def post(self,request):
        tem=Review()
        if request.data.get('rating') != '':
            tem.rating=request.data.get('rating')
        if request.data.get('subject') != '':
            tem.subject=request.data.get('subject')
        if request.data.get('review') != '':
            tem.review=request.data.get('review')
        temp=Properties.objects.get(id=request.data.get('property_id'))
        tem.property=temp
        temp=User.objects.get(id=request.data.get('reviewer_id'))
        tem.customer=temp
        tem.date=datetime.datetime.utcnow()
        tem.save()

        return Response({'msg':'success'})

class GetReview(APIView):
    def get(self,request,id):
        reviews1=Review.objects.filter(property=id)
        lis=[]
        for x in reviews1:
            cust_id=x.customer
            obj=User.objects.get(email_id=cust_id)
            temp={
                "cust_name":obj.u_name,
                "rating":x.rating,
                "review":x.review,
                "subject":x.subject,
                "image":obj.profile,
                "date":x.date
            }
            lis.append(temp)
            
            
        
        return JsonResponse(lis,safe=False)

class User_properties(APIView):
    def get(self,request,id):
        myprop=Properties.objects.filter(malik_id=id)
        return JsonResponse(list(myprop.values()),safe=False)
class delete_users_product(APIView):
     def delete(self,request,id):
        pro=Properties.objects.filter(id=id)
        pro.delete()
        response = Response()
        response.data = {
            'message': 'success'
        }
        return response