from telnetlib import STATUS
from django.shortcuts import render
from django.http import HttpResponse
from django.views.decorators.http import require_http_methods
from django.views import View

# Create your views here.

#function based views
@require_http_methods(["GET","POST"])
def function_one(request):
    if request.method == "GET":
        return HttpResponse("welcome get method "+request.method)
    elif request.method == "POST":
        return HttpResponse("welcome to post method "+request.method)

    return HttpResponse("welcome to function based views",status=404) 

#class based views
class FunctionOne(View):
    def get(self,request):
        return HttpResponse("welcome get method "+request.method)
    
    def post(self,request):
        return HttpResponse("welcome to post method "+request.method)

    def put(self,request):
        return HttpResponse("WElcome to put methood "+request.method)

    def patch(self,request):
        return HttpResponse("welcome to patch method "+request.method)

