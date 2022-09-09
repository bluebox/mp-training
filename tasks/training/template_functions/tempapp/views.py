from django.shortcuts import render

# Create your views here.


def home(request):
    data={'name':' medplus india'}
    return render(request,'index.html',data)  #using variable in template


def fortemp(request):
    data={'values':[1,2,3,4,5]}
    return render(request,'forloop.html',data)


def iftemp(request):
    data={'condition':True,'value':'successful'}
    return render(request,'if.html',data)