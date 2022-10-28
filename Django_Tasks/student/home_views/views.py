from django.shortcuts import render
from django.shortcuts import HttpResponse
from django.views.decorators.http import require_http_methods
from django.views import View
import json
from django.core.exceptions import PermissionDenied

# Create your views here.

def index(request):
    return render(request,'home_views/index.html')




class ViewEx(View):

    def get(self,request):
        return HttpResponse("Heyyyyyyyy get",status=405)

    # def post(self,request):
    #     return HttpResponse("Heyyyyyyyy post")
    #


    def post(self,request):
        raise PermissionDenied


    def delete(self, request):
        context = {
            'name': 'Purnima'
        }
        return HttpResponse(json.dumps(context),content_type="application/json")





