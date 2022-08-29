from multiprocessing import context
from django.shortcuts import render
from django.http import HttpResponse
def index(request):
    return HttpResponse("hello world!")

def temp1(request):
    return render(request,'jangoapp2/index.html')

def temp2(request):
    context = {
        'name' : 'manasa',
        'age':21,
        'dept' : ['ece','mpc','general'],
        'id' : {'btech':'18TR1A0489','inter': 'bme13','ssc':'5'},
        'series' : (2,4,6,8,10)
    }
    return render(request,'jangoapp2/temp2.html',context)

def temp3(request):
    context = {
        'data' : [1,2,3,4,5]
    }
    return render(request,'jangoapp2/temp3.html',context)

def temp4(request):
    context = {
        'name1' : 'manasa',
        'name2' : 'aishwarya',
        'name3' : 'rakesh',
        'name4' : 'venkatesh',
        'name5' : 'arun',
        'name6' : 'shazwan ali',
        'name7' : 'mydayisgoodday'
    }
    return render(request,'jangoapp2/temp4.html',context)

def temp5(request):
    return render(request,'jangoapp2/temp5.html')


