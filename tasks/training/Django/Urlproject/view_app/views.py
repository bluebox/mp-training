from django.shortcuts import render
from django.http import  HttpResponse
from django.views.decorators.http import require_http_methods
# Create your views here.

#function based view
# @require_http_methods(["GET", "POST"])
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