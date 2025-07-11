import json

from django.contrib.auth.decorators import login_required
from django.contrib.auth.hashers import make_password
from django.shortcuts import render
from django.http import HttpResponse, HttpResponseNotFound, Http404, HttpResponseRedirect
from django.contrib.auth import authenticate, login
from django.urls import reverse
from django.views.decorators.csrf import csrf_exempt

#function based views
blog_posts = [
    {
        'blog_id': 1,
        'blog_title': 'Getting Started with Django',
        'blog_data': 'Django is a high-level Python web framework that encourages rapid development and clean design.'
    },
    {
        'blog_id': 2,
        'blog_title': 'Understanding Django Templates',
        'blog_data': 'Templates allow you to generate dynamic HTML using Django’s templating language.'
    },
    {
        'blog_id': 3,
        'blog_title': 'Working with Django Models',
        'blog_data': 'Models define the structure of your database tables and are the backbone of any Django app.'
    },
    {
        'blog_id': 4,
        'blog_title': 'Django Class Based Views Explained',
        'blog_data': 'Class Based Views (CBVs) help you reuse code and organize your logic in object-oriented ways.'
    },
    {
        'blog_id': 5,
        'blog_title': 'Creating REST APIs with Django REST Framework',
        'blog_data': 'DRF makes it easy to build web APIs with features like authentication, serialization, and viewsets.'
    },
    {
        'blog_id': 6,
        'blog_title': 'How to Use Static and Media Files in Django',
        'blog_data': 'Learn how to manage CSS, JavaScript, and user-uploaded files in your Django project.'
    },
]

def home(request):
    return HttpResponse("<h1>this is the home page</h1>")

def home_template(request):
    return render(request, 'blog/home.html', {'blog_posts': blog_posts})


def blog_detail(request,id):
   temp=False
   for blog in blog_posts:
       if blog['blog_id']==id:
           temp=True
           fact=blog
           break
   if not temp:
       raise Http404()
   return render(request,'blog/single.html',{'fact':fact})

@csrf_exempt
def login_view(request):
    if request.method == 'POST':
        username = request.POST.get('username')
        password = request.POST.get('password')
        val=make_password(password)
        print(val)
        # data=json.loads(request.body)
        # username=data.get('username')
        # password=data.get('password')
        user = authenticate(request, username=username, password=password)
        if user is not None:
            login(request, user)
            return HttpResponseRedirect(reverse('dashboard'))
        else:
            return HttpResponse("Invalid credentials")

    return render(request, 'blog/login.html')

@login_required
def dashboard(request):
    return HttpResponse('welcome to dashboard')