from multiprocessing import context
from django.shortcuts import render
from .models import *
from django.contrib.auth.decorators import login_required
from Social_Media_App.models import *
# Create your views here.
@login_required

def home1(request):
    user_profile = Profile.objects.get(user=request.user)
    user_obect = User.objects.get(username = request.user.username)
    threads = ThreadingTable.objects.by_user(user=request.user).prefetch_related('message1').order_by('timestamp') 
    arr = []
    profile1=[]
    for i in range(len(threads)):
        arr.append(threads[i])
        y= threads[i].second_person.id
        d = Profile.objects.get(user=y)
        profile1.append(d.profile_img)
        print("hello dudep",profile1)

    context={
        'threads':threads,
        'user_profile':user_profile,
            
    }
    return render(request,"message.html", context)