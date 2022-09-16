from django.http import HttpResponse
from django.shortcuts import render,redirect
from .models import ValidatorsTask


# Create your views here.


def login(request):
    context = {}
    return render(request, 'registerUser.html', context)


def validate(request):
    if request.method == 'POST':
        uid = request.POST['uid']
        uname = request.POST['username']
        upass = request.POST['upass']
        v = ValidatorsTask(uid=uid, uname=uname, upass=upass)
        v.save()
        return redirect('vallog')


    return HttpResponse('not successful')
