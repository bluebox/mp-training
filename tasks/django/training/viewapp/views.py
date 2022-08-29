import json
from urllib import request
from django.views.decorators.http import require_http_methods
from django.shortcuts import render
from django.http import HttpResponse
from django.template import loader
from django.views import View
import json


# Create your views here.

def index(request):
    return HttpResponse(request.method)

def MethodShow(request):
    display=None
    if request.method == "POST":
        display="This is a POST Method"
    elif request.method == "GET":
        display="This is a GET Method"
    else:
        display="This is neither a POST nor a GET method"
    return HttpResponse(display)


class BuiltInView(View):
    def get(self, request):
        parameters = request.GET
        res_obj = {
            "msg": "This is a GET request"
        }
        return HttpResponse(json.dumps(res_obj), content_type="application/json")
    def post(self, request):
        parameters = request.POST
        res_obj = {
            "msg": "This is a POST request"
        }
        return HttpResponse(json.dumps(res_obj), content_type="application/json")