from django.shortcuts import render
from django.http import HttpResponse
from django.views.decorators.http import require_http_methods, require_POST
from django.views import View
# from django.views.generic import view
# Create your views here.

def viewFunction(request):
    if request.method == "GET":
        return HttpResponse("This is a function based " + request.method +" method")
    elif request.method == "POST":
        return HttpResponse("This is a function based " + request.method +" method")
    elif request.method == "DELETE":
        return HttpResponse("This is a function based " + request.method +" method")

    return HttpResponse(f"{request.method} Method not allowed", status=405)


@require_http_methods(["GET", "POST"])
def decorator_func(request):
    return HttpResponse("This is decorator function based " + request.method + " method")

class ClassBasedView(View):
    def get(self, request):
        return HttpResponse("This is the class based view with " + request.method + " method")
    def post(self, request):
        return HttpResponse("This is the class based view with " + request.method + " method")
    def delete(self, request):
        return HttpResponse("This is the class based view with " + request.method + " method")
