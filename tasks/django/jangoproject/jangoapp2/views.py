from django.shortcuts import render
from django.http import HttpResponse
def index(request):
    return HttpResponse("hello world!")

def temp1(request):
    return render(request,'jangoapp2/index.html')