from django.shortcuts import render
from django.http import HttpResponse

# Create your views here.
def index(req):
    print("welcome Hari")
    return HttpResponse("welcome")

def no_parameter(req):
    return HttpResponse("welcome to no parameters page")

def one_parameter(req, params):
    return HttpResponse(f"welcome to parameter {params} page")

def multiple_parameter(req, params, param1):
    return HttpResponse(f"welcome to parameter {params} and {param1} page")

def int_parameter(req, params):
    return HttpResponse(f"welcome to int parameter {params} page")
def custom_parameter(req, params):
    return HttpResponse(f"welcome to custom converter parameter {params} page")

def re_path_parameter(req, params):
    return HttpResponse(f"welcome to re_path parameter {params} page")

def uuid_parameter(req, params):
    return HttpResponse(f"welcome to uuid parameter {params} page")


def slug_parameter(req, params):
    return HttpResponse(f"welcome to slug parameter {params} page")