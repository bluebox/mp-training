from django.shortcuts import render, redirect
from django.http import HttpResponse
from django.contrib.auth.forms import UserCreationForm
from .models import Product, Contact
from math import ceil
from .forms import CreateUserForm
from django.contrib.auth.models import User
from django.contrib import messages
from django.contrib.auth import authenticate, login, logout
from django.contrib.auth.decorators import login_required
# Create your views here.
@login_required(login_url='/shop/login/')
def index(request):
    # products = Product.objects.all()
    # print(products)
    # n = len(products)
    # nSlides = n//4 + ceil((n/4)-(n//4))
    allProds = []
    catprods = Product.objects.values('category', 'id')

    cats = {item['category'] for item in catprods}

    for cat in cats:
        prod = Product.objects.filter(category=cat)

        n = len(prod)
        nSlides = n // 4 + ceil((n / 4) - (n // 4))
        allProds.append([prod, range(1, nSlides), nSlides])
    # allProds = [[products, range(1, len(products)), nSlides], [products, range(1, len(products)), nSlides], [products, range(1, len(products)), nSlides]]
    params = {'allProds': allProds}
    # params = {'nSlides': nslides, 'range': range(1,nslides), 'product': products}
    return render(request, "shop/index.html", params)


def about(request):
    return render(request, "shop/about.html")


def contact(request):
    if request.method == "POST":
        name = request.POST.get('name', '')
        phone = request.POST.get('phone', '')
        email = request.POST.get('email', '')
        message = request.POST.get('message', '')
        print(name, phone, message, email)
        contact = Contact(name=name, email=email, phone=phone, message=message)
        contact.save()
    return render(request, "shop/contact.html")
    # return render(request,'blog/index.html')


def tacker(request):
    return render(request, "shop/tracker.html")
    # return render(request,'blog/index.html')


def search(request):
    return HttpResponse("we are at search")
    # return render(request,'blog/index.html')


def prodView(request, myid):
    # Fetch the product using id
    product = Product.objects.filter(id=myid)
    print(product)
    return render(request, 'shop/prodview.html', {'product': product[0]})


def checkout(request):
    return HttpResponse("we are at checkout")
    # return render(request,'blog/index.html')

def loginPage(request):
    # return HttpResponse("we are at checkout")
    if request.user.is_authenticated:
        return redirect('ShopHome')
    else:

        if request.method == "POST":
            username = request.POST.get('username')
            password = request.POST.get('password')
            user = authenticate(request, username=username, password=password)
            if user is not None:
                login(request, user)
                return redirect('ShopHome')
            else:
                messages.info(request, 'username or password is incorrect')
            # print(username, password, user.email, user.username)

        return render(request, 'shop/login.html')

def logoutUser(request):
    logout(request)
    return redirect('login')
def signup(request):
    if request.user.is_authenticated:
        return redirect('ShopHome')
    else:
        form = CreateUserForm()
        if request.method == "POST":
            form = CreateUserForm(request.POST)
            if form.is_valid():
                form.save()
                user = form.cleaned_data.get('username')
                messages.success(request, 'Account was created for ' + user)
                return redirect('login')
        context = {'form': form}
        return render(request, 'shop/signup.html', context)
