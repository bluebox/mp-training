from django.shortcuts import render

from usersadmin.models import students

# Create your views here.

def homepage(request):
    return render(request,"teachers/login.html")
def login_auth(request):
    if request.method=="POST":
        user_name=request.POST['username']
        user_pass=request.POST['password']
        try:
            user_obj=students.objects.get(username=user_name,password=user_pass)
        except:
            user_obj=False
    return 
            
        