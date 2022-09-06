from django.shortcuts import render
from django.http import HttpResponse
from django.views import View
from django.views.decorators.http import require_http_methods
from .models import Employee


# Create your views here.
# def demo(request):
#     return HttpResponse("<h1>cricket</h1>")



def function_based_view(request):
    if request.method == "GET":
        return HttpResponse("get response")

    elif request.method == "POST":
        return HttpResponse("post response")

    else:
        return HttpResponse("No response")


class Classview(View):
    def get(self,request):
        return HttpResponse("I am get response")

    def post(self,request):
        return HttpResponse("I am post response")

def show(request):
    context = {
        "Total_Employees":Employee.objects.all()
    }
    return render(request, 'app3/views.html',context)
