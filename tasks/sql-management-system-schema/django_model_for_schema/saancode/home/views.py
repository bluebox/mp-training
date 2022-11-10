from django.shortcuts import render

# Create your views here.

def homeView(request):
    return render(request, 'home/base.html')

def problems(request):
    return render(request, 'home/problems.html')

def discuss(request):
    return render(request, 'home/base.html')

def notifications(request):
    return render(request, 'home/base.html')

def streak(request):
    return render(request, 'home/base.html')

def account(request):
    return render(request, 'home/base.html')

def signin(request):
    return render(request, 'home/base.html')