from django.shortcuts import render,redirect
from django.contrib.auth.forms import UserCreationForm
from authentication.forms import CreateUserForm
from django.contrib import messages
from django.contrib.auth import authenticate,login,logout
from django.contrib.auth.decorators import login_required


# Create your views here.
@login_required(login_url='signin')
def home(request):
    return render(request,"authentication/index.html")

def register(request):
    form = CreateUserForm()

    if request.method == 'POST':
        form = CreateUserForm(request.POST)
        if form.is_valid():
            form.save()
            user = form.cleaned_data.get("username")
            messages.success(request,"account created for "+user)

            return redirect('signin')
    context = {'form' : form}
    return render(request,"authentication/signup.html",context)

def signin(request):

    if request.method == 'POST':
        username = request.POST.get('username')
        password = request.POST.get('password')

        user = authenticate(request,username=username,password=password)

        if user is not None:
            login(request,user)
            return redirect('home')
        else:
            messages.info(request,"username or password is incorrect")
            return render(request,"authentication/signin.html")
        
    return render(request,"authentication/signin.html")

@login_required(login_url='signin')
def signout(request):
    logout(request)
    return redirect('signin')