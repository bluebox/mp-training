from .models import movies
from django.shortcuts import HttpResponse
import json

def movies_data(request):
    if request.method=="GET":
        dict_a=[]
        movie=movies.objects.all()
        for i in movie:
            dict_a.append({"title":i.movie_name,"description":i.movie_description})

        return HttpResponse(json.dumps(dict_a),content_type="application/json")