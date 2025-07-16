from django.http import HttpResponse
from django.shortcuts import render
from django.template.response import TemplateResponse


def home(request):
    if request.method=='GET':
        print('in the view')
        return HttpResponse('this is the home page')
    return HttpResponse('request not allowed')

def CustomClassMiddleware(request):
    if request.method=='GET':
        # a=1/0 this is for the process_exception example
        print('in the view of CustomClassMiddleware')
        return HttpResponse('CustomClassMiddleware')
    return HttpResponse('request not allowed')

def template_example(request):
    if request.method=='GET':
        return TemplateResponse(request,'home.html',{'user':'anand'})
    return None

def cookies_example(request):
    if request.method=='GET':
        response=HttpResponse('example of cookie')
        response.set_cookie('name','anand')
        response.set_cookie('theme','dark',max_age=10)
        return response
    return None

def get_cookie_example(request):
    name=request.COOKIES.get('name')
    return HttpResponse(f'{name}')

def delete_cookie_example(request):
    response=HttpResponse('successfully deleted')
    response.delete_cookie('name',path='/')
    return response

def update_cookie_example(request):
    response=HttpResponse('successfully Updated')
    response.set_cookie('name','anand')
    response.set_cookie('theme','light')
    return response
