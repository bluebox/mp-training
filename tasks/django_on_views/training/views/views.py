import json

from django.shortcuts import render
from django.http import HttpResponse
from django.views.decorators.http import require_http_methods
from views import my_custom_class
from django.views import View


# Create your views here.
#views based on functions
@require_http_methods(["POST","GET"])
def func_based(request):
    return render(request,'views/index.html')


#views based on classes
class MyView(View):
    def get(self,request):
      return HttpResponse("I AM THEIR SON")
    def post(self,request):
        return post(self,request)


class conver(View):
    def get(self,request):
        # para=request.GET
        ro = {"mymsg" : "this msg"}
        return HttpResponse(json.dumps(ro["mymsg"]),content_type='application/json')