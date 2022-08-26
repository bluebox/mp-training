from django.shortcuts import render

from django.http import HttpResponse

# test cmment commit message
def defaultview(request):
    return HttpResponse("Hello, world! you are at turls defaultview")

def one_parameter(request,parameter):
    html="Hello, world! you are at turls one_parameter" + parameter
    return HttpResponse(html)

def int_parameter(request,parameter):
    html="Hello, world! you are at turls int_parameter" + str(parameter)
    return HttpResponse(html)


def multi_paramter(request,a,b,c):
    html="Hello, world! you are at turls multiple parameters" + a + "," + b + "," + c
    return HttpResponse(html)

def user_converter(request,parameter):
    html="Hello, world! you are at turls converter parameters" + "   " + parameter
    return HttpResponse(html)

def regex_in_url(request,parameter):
    html="Hello, world! you are at turls converter parameters" + "   " + parameter
    return HttpResponse(html)


def articles(request,yyyy,mm,slug):
    html="Hello, world! you are at turls converter parameters" + "   " + yyyy +"   "+mm+"   "+slug
    return HttpResponse(html)

def year_archive(request,year,**kwargs):

    return HttpResponse(str(year),kwargs['foo'])
