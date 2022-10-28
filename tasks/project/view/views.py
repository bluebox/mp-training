from django.shortcuts import render
from django.views import View

from django.http import HttpResponse
import json
# Create your views here.

def firstview(request):

    return render(request, "index.html")


class secview(View):
    def get(self, request):
        return render(request, 'index.html')


class json9(View):
    def get(self, request):
        return render(request, 'index.html')

    def post(self, request):
        return render(request, 'index2.html')

class Json1(View):
    def get(self,request):
        parameters=request.GET
        response_object={
            'name': 'srikar'
        }
        return HttpResponse(json.dumps(response_object) ,content_type="application/json")


class geet(View):
    def get(self,request):
        params=request.GET
        poost=params.get('size')
        return HttpResponse(" GET , parameter size : " + poost)

    def post(self,request):
        params=request.GET
        formdata=request.POST
        value=params.get('size')
        username=formdata.get('username')
        password=formdata.get('password')
        return HttpResponse("GET,parameter  size: " + value + "  and formdata username : "+username
                            + " and password : "+password)

class poostt(View):
    def get(self, request):
        params = request.GET
        poost = params.get('size')
        return HttpResponse("GET, parameter size : " + poost)

    def post(self,request):
        params=request.GET
        formdata=request.POST
        value=params.get('size')
        username=formdata.get('username')
        password=formdata.get('password')
        return HttpResponse("POST,parameter size : " + value + "  and formdata  username: "+username
                            + " and password : "+password)


