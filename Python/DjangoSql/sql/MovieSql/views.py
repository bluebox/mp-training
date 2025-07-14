from django.contrib.sites import requests
from django.db import transaction
from django.views.decorators.csrf import csrf_exempt
from django.http import JsonResponse
import json
from .models import Directors, Watchhistory, Movies


@csrf_exempt
def bulk_insert(request):
    if request.method == 'POST':
        try:
            data = json.loads(request.body)
            directors = [Directors(name=entry['name'], email=entry['email']) for entry in data]
            Directors.objects.bulk_create(directors)
            response_data = [{'name': d.name, 'email': d.email} for d in directors]
            return JsonResponse({"inserted": response_data}, status=201)
        except Exception as e:
            return JsonResponse({"error": str(e)}, status=400)
    return JsonResponse({"error": "Only POST allowed"}, status=405)


@csrf_exempt
def transaction_example(request):
    if request.method == "POST":
        try:
            with transaction.atomic():
                data=json.loads(request.body)
                user_id =data.get('user_id')
                movie_id = data.get('movie_id')

                if not user_id or not movie_id:
                    return JsonResponse({'msg': 'user_id and movie_id are required'}, status=400)

                movie = Movies.objects.get(movie_id=movie_id)

                if movie.current_views >= 5:
                    raise Exception('Movie limit reached')

                movie.current_views += 1
                movie.save()

                watch = Watchhistory(user_id=user_id, movie=movie)
                watch.save()

                return JsonResponse({"success": "inserted"}, status=201)

        except Exception as e:
            return JsonResponse({'msg': str(e)}, status=400)

    return JsonResponse({'error': 'Only POST allowed'}, status=405)


@csrf_exempt
def aggregation_example(request):
    if request.method=="GET":
        pass