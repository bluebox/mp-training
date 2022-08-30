from django.shortcuts import render
from django.http import HttpResponse
from django.views.decorators.http import require_http_methods
from django.views.generic import TemplateView
from django.views import View
import json

# Create your views here.


def function_view(request):
    method = request.method
    # csrf token authorization was muted in setting.py to get other request methods accessible
    return HttpResponse('function based  view and the request method is :  '+method)


@require_http_methods(["GET", "POST"])
def function_view_with_decorators(request):
    if request.method == 'GET':
        return HttpResponse('function based view with decorator and get method is used')

    else:
        return HttpResponse('function based view with decorator and post method is used')


class AboutView (TemplateView):
    template_name = "viewstraining/index.html"
# this is class based generic view


class CViewWithParams(View):

    def get(self, request):
        value = request.GET.get('name')
        return HttpResponse("THIS IS THE GET METHOD and with parameter name : " + value)


class CViewWithFormData(View):

    def post(self, request):
        first_name = request.POST.get('first_name')
        last_name = request.POST.get('last_name')
        return HttpResponse("THIS IS THE POST METHOD and with form data   first_name : " + first_name + " last_name : " + last_name)


class CViewWithJsonResponse(View):
    def get(self,request):
        response_object={
            'name': 'harsha',
            'age': '23',
            'place': 'hyderabad'

        }
        return HttpResponse(json.dumps(response_object), content_type='application/json')
