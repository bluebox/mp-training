import json
from django.shortcuts import render
from django.views.decorators.csrf import csrf_exempt


# Create your views here.

@csrf_exempt
def index(request):
    if request.method == "GET":
        return render(request, "index.html")
    else:
        data = json.loads(request.body)

