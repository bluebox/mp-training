from django.shortcuts import render
from django.http import HttpResponse
from django.views.decorators.http import require_http_methods
from django.views import View
import json

# Create your views here.
def default(request):
    return HttpResponse("Day 3: Default page")

# function based views
def function_based(request):
    if request.method == 'GET':
        return HttpResponse("GET METHOD")
    elif request.method == 'POST':
        return HttpResponse("POST METHOD")
    elif request.method == 'PATCH':
        return HttpResponse("PATCH METHOD")
    return HttpResponse("function based views")

# function based views using decorators
@require_http_methods(["GET",'POST'])
def fbv_using_decorators(request):
    return HttpResponse("you will only see this message if the request.method is get or post")

# class based views
class Class_based(View):
    def get(self,request):
        return HttpResponse("GET METHOD")
    def post(self,request):
        return HttpResponse("POST METHOD")

# class based using json and query params
class Cbv_json(View):
    def get(self,request):
        params=request.GET
        food={"fav":"chicken65"}
        return HttpResponse(json.dumps(food),content_type="application/json")
    def post(self,request):
        data=request.POST
        return HttpResponse("POST METHOD")