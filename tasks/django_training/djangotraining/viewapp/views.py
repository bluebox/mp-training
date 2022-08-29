from django.shortcuts import render
from django.http import HttpResponse

from django.views.decorators.http import require_http_methods,require_POST
# Create your views here.


@require_http_methods(['GET', 'POST'])
def function_based(request):

    return HttpResponse("Function based request")