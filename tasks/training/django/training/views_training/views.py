from re import template
from urllib import request
from django.shortcuts import render
from django.http import HttpResponse
import json
from django.views.decorators.http import require_http_methods
from django.views import View
from django.views.generic import ListView
from .models import TeamMember
# Create your views here.

def fun_based_view(request):
    if request.method=='POST':
        values = {
            'firstName' : request.POST['firstName'],
            'lastName' : request.POST['lastName'],
        }
       
        return HttpResponse(json.dumps(values),content_type='application/json', status =200)
    if request.method == "PUT":
        return HttpResponse(status=405)
    return HttpResponse("hello Aishwarya")


# using decorators for method handling using function based viewa
@require_http_methods(['POST','GET'])
def fun_based_decorators(request):
    return HttpResponse(json.dumps(request.POST) , content_type = 'application/json')


# can use it for particular request
# @require_http_methods(['GET'])
# def fun_based_decorators(request):

#     return HttpResponse(json.dumps(request.GET) , content_type = 'application/json')



# custom class based view
class GreetingView(View):
    template_name = 'views_training/greet.html'
    def get(self,request):
        return HttpResponse(json.dumps(request.GET) , content_type = 'application/json')

    def post(self,request):
        return render(request , self.template_name , {"names" : request.POST} )  #rendering template in class based view
    

class ListTeam(ListView):
    model = TeamMember
    template_name: 'views_training/teammember_list.html'
    # object_list: "teammates"
    
    