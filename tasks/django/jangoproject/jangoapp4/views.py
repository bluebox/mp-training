from django.shortcuts import render,redirect
from django.contrib.auth.forms import UserCreationForm
from django.contrib.auth.models import User
from django.contrib.auth import login, authenticate  # add to imports
from django.contrib import messages
# Create your views here
def register(request):
    if request.method == 'POST':
        form = UserCreationForm(request.POST)
        if form.is_valid():
            form.save()

        return redirect('login')
    else :
        form = UserCreationForm()

    context = {
        'form' : form
    }
    return render(request,'jangoapp4/register.html',context)



# def loginPage(request):
#     page = 'login'
#     context = {
#         'page': page
#     }
#     if request.user.is_authenticated:
#         return redirect('profiles')

#     if request.method == 'POST':
#         print(request.POST)
#         username = request.POST['username']
#         password = request.POST['password']
#         try:
#             user = User.objects.get(username = username)
#         except:
#             messages.error(request, 'username does not exist')
#         user = authenticate(request, username=username, password=password)
#         if user is not None:
#             login(request, user)
#             return redirect('profiles')
#         else:
#             messages.error(request, 'username or password is incorrect')

#     return render(request, 'users/login_register.html', context)
