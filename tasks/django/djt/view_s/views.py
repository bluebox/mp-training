import json
from urllib import request
from django.views.decorators.http import require_http_methods
from django.http import HttpResponse
from django.views import View
from django.views.decorators.http import require_http_methods


# @require_http_methods(["GET", "POST"])
def function(request):
    value = ''
    if request.method == "POST":
        value = 'post'
    elif request.method == "GET":
        value = 'get'
    else:
        value = 'not post or get'
    return HttpResponse(value)

class ViewClass(View):
    def get(self, request):
        return HttpResponse("{% csrf_token %} <h1>get response</h1>")

    def post(self, request):
        return HttpResponse("{% csrf_token %} <h1>post response</h1>")
