from django.shortcuts import render

# Create your views here.


from django.shortcuts import HttpResponse
from django.views import View

class Index(View):
    def get(self, request):
        return HttpResponse("Heyyyyyyyy get", status=405)

    def post(self,request):
        return HttpResponse("Heyyyyyyyy post", status=405)
