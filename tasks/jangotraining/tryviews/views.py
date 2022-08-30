from django.shortcuts import render
from django.http import HttpResponse
# Create your views here.
from django.views import View
from django.views.decorators.http import require_http_methods#function based views
def fun(request):
    return HttpResponse("hello this is a function bassed view")

#function based views with Decorators
@require_http_methods(['GET','POST'])
def post1(request):
    # if request.method=='GET':
    #     return HttpResponse("this is a get method"+'the name you have passed is'+request.GET['name']+'the age that you have passed is '+request.GET['age'])
    return HttpResponse("this is a get method"+'the name you have passed is'+request.GET['name']+'the age that you have passed is '+request.GET['age'])

#class based views
class ClassView(View):
    def get(self,request):
        return HttpResponse('the name you have passed is'+request.GET['name']+'the age that you have passed is '+request.GET['age'])

    def post(self, request):
        return HttpResponse('the name you have passed is' + request.GET['name'] + 'the age that you have passed is ' + request.GET[ 'age'])