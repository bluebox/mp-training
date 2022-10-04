from django.shortcuts import render
# Create your views here.
from django.http import HttpResponse
# Create your views here.


def index(request):
    cont = {'uname': "Anand", 'id': "562731", 'age': 21, 'ph_no': [7,3,4,6,8,3,1,2,3,8], 'cnt': "Bhargavi Lenka is a good girl"}

    return render(request, 'index.html', cont)
