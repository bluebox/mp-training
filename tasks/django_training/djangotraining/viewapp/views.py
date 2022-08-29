from django.shortcuts import render
from django.http import HttpResponse
from django.views.decorators.http import require_http_methods,require_POST



@require_http_methods(['GET'])
def one_method(request):
    return HttpResponse("Function based get request")


@require_http_methods(['GET', 'POST'])
def two_method(request):
    if request.method=="GET":
        return HttpResponse("returning from get method")

    elif request.method=="POST":
        return HttpResponse("Returning from post method")
