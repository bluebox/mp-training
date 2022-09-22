from telnetlib import STATUS
from django.shortcuts import render,HttpResponse
from django.views.generic import View
from django.views.decorators.http import require_http_methods
import json

# Create your views here.
@require_http_methods(["GET"])
def funcomp(request):
    return HttpResponse("hello i am GET component")

@require_http_methods(["POST"])
def funcomp(request):
    return HttpResponse("hello i am POST component")

def funcompifcondition(request):
    if request.method=="POST":
        return HttpResponse("hello i am POST component")
    elif request.method=="GET":
        return HttpResponse("hello i am GET component")


class Classcomp(View):
    def get(self,request):
        get_data=request.GET
        home_get=get_data.get("home")
        return HttpResponse("hello i am get request"+" "+str(home_get))
    def post(self,request):
        get_data=request.POST
        user_name=get_data.get("username")
        pass_word=get_data.get("password")
        print(pass_word)

        return HttpResponse("hello i am post request")        

def statuscode(request):
    if request.method=="POST":
        return HttpResponse("hello i am post",status=201)
    elif request.method=="GET":
        return HttpResponse("hello i am GET component")

def jsonsend(request):
    dict_a={
        "user":"yatin"
    }
    return HttpResponse(json.dumps(dict_a),content_type="application/json")
