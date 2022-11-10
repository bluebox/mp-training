from django.shortcuts import render
from django.http import  HttpResponse
from django.views.decorators.http import require_http_methods
from django.views.generic import View
# Create your views here.

#function based view

def func_view(request):
    if request.method == "GET":
        return HttpResponse("Function based view "+ request.method)
    else:
        return HttpResponse ("Function based view "+ request.method)


def func_view1(request):
    if request.method == "GET":
        return HttpResponse("function based view "+ request.method)
    elif request.method == "POST" :
        return HttpResponse("function based view "+ request.method)
    elif request.method == "DELETE" :
        return HttpResponse("function based view "+ request.method)
    elif request.method == "PUT" :
        return HttpResponse("function based view "+ request.method)
    return HttpResponse("function based view ", status=405)


@require_http_methods(["GET", "POST"])
def func_view_deco(request):
    if request.method == "GET":
        return HttpResponse("Function based view "+ request.method)
    else:
        return HttpResponse ("Function based view "+ request.method)

class ClassBasedView(View):
    def get(self, request):
        parameters=request.GET
        return HttpResponse("Class based view " +request.method)
    def post(self, request):
        form_para=request.POST
        return HttpResponse("Class based view " +request.method)
    def put(self, request):
        return HttpResponse("Class based view " +request.method)
