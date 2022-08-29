from django.shortcuts import render
from django.http import HttpResponse
from django.views import View

# Create your views here.
# def demo(request):
#     return HttpResponse("<h1>cricket</h1>")

def fview(request):
    if request.method == "GET":
        return HttpResponse("get response")

    elif request.method == "POST":
        return HttpResponse("post response")

    else:
        return HttpResponse("No response")

class Cview(View):
    def get(self,request):
        return HttpResponse("I am get response")

    def post(self,request):
        return HttpResponse("I am post response")
