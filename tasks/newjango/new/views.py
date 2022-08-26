from django.shortcuts import render
from django.http import HttpResponse
# Create your views here.
def calling(request):
    return HttpResponse("HI SHYAM")
def one_para(request, para):
    result = " regex string  " + str(para)
    return HttpResponse(result)
def two_para(request, para,para1):
    result = str(para) + " is the " + str(para1)
    return HttpResponse(result)
def int_para(request, para):
    result = str(para) + " is the number"
    return HttpResponse(result)
def slug_para(request, para):
    result = "akjhsdkj " + str(para)
    return HttpResponse(result)
def uuid_para(request, para):
    result = para
    return HttpResponse(result)

def id_validation(request, para):
    result = str(para)
    return HttpResponse(result)
def nested(request, para2):
    result = "nested" + str(para2)
    return HttpResponse(result)

def passing_objects(request, **dict_a):
    result = "My name is " + dict_a["Name"] + " and age is " + dict_a["age"]
    return HttpResponse(result)