
from django.shortcuts import render
from django.http import HttpResponse
from django.views.decorators.http import require_http_methods
from django.views.generic import View
def function(request):
    if request.method =='GET':
        return HttpResponse(request.method)
    elif request.method =="POST":
        return HttpResponse(request.method)
    else:
        return HttpResponse("method not found")

@require_http_methods('GET')
def function1(request):
    return HttpResponse('post request using decorators')

class SecFun(View):
    def get(self,request):
        parameters = request.GET
        return HttpResponse(request.method+' request')
    def post(self,request):
        form_data = request.POST
        return HttpResponse(request.method+' request')

