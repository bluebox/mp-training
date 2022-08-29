from django.shortcuts import render
from django.shortcuts import HttpResponse
from django.views.decorators.http import require_http_methods
from django.views import View
# Create your views here.

def index(request):
    return render(request,'home_views/index.html')




class ViewEx(View):
    def get(self,request):
        return HttpResponse("Heyyyyyyyy get")

    def post(self,request):
        return HttpResponse("Heyyyyyyyy post")




