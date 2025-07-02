from django.shortcuts import render
from django.http import JsonResponse

def hello_api(request):
    params = request.GET
    for key,val in params.items():
        print(key,":",val)
    return JsonResponse({'message':dict(params)})

