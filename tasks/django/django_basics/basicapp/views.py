from django.shortcuts import render

# Create your views here.

from .models import Order

def index(request):
    orders = Order.objects.all()
    # orders = Order.objects.prefetch_related('products').all()

    for order in orders:
       
            temp = order.customer
    return render(request, 'index.html')