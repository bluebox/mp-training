from django.db import transaction
from django.db.models import Sum, Avg, Max, Min, Q
from django.middleware.csrf import get_token
from django.views.decorators.csrf import csrf_exempt
from django.http import JsonResponse
import json
from .models import Directors, Watchhistory, Movies, Users


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
                data = json.loads(request.body)
                user_id = data.get('user_id')
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
    if request.method == "GET":
        sumval = Movies.objects.all().aggregate(sum=Sum("duration_minutes"))
        avgval = Movies.objects.all().aggregate(avg=Avg('duration_minutes'))
        maxval = Movies.objects.all().aggregate(max=Max('duration_minutes'))
        minval = Movies.objects.all().aggregate(min=Min('duration_minutes'))
        count = Users.objects.all().count()
        return JsonResponse({"sum": sumval, "avg": avgval, "max": maxval, 'min': minval, "count": count})
    return None


def get_csrf_token(request):
    # just to know how does the interactivity of React and django works using the csrf_token
    if request.method == "GET":
        print("jai shree ram")
        token = get_token(request)
        return JsonResponse({'csrfToken': token})
    return None


@csrf_exempt
def update_example(request):
    if request.method == 'PUT':
        try:
            data = json.loads(request.body)
            user_id = data.get('user_id')
            name = data.get('name')
            user = Users.objects.get(user_id=user_id)
            user.name = name
            user.save()
            return JsonResponse({'msg': "updated"}, status=200)
        except Exception as e:
            return JsonResponse({'err': str(e)}, status=400)
    return JsonResponse({"err": "PUT is only allowed"}, status=400)


@csrf_exempt
def delete_example(request, pk):
    if request.method == 'DELETE':
        try:
            user = Users.objects.get(user_id=pk)
            user.delete()
            return JsonResponse({"msg": "deleted successfully"})
        except Exception as e:
            return JsonResponse({'err': str(e)})
    return JsonResponse({"err": "request not allowed"})


@csrf_exempt
def Q_example(request):
    if request.method == "GET":
        data = json.loads(request.body)
        user_id = data.get('user_id')
        movie_id = data.get('movie_id')
        exist = Watchhistory.objects.filter(Q(user_id=user_id) & Q(movie_id=movie_id))
        if exist:
            return JsonResponse({'msg': f'found data with {user_id} and {movie_id}'})
        return JsonResponse({'msg': 'not found'})
    return JsonResponse({'err': 'request not allowed'})


@csrf_exempt
def Delete_from_Director(request):
    if request.method == "DELETE":
        try:
            director = Directors.objects.get(name='Christopher Nolan')
            director.delete()
            return JsonResponse({"msg": "Director Deleted Successfully"}, status=200)
        except Exception as e:
            return JsonResponse({"err": str(e)}, status=400)
    return JsonResponse({'msg': "Delete is the only allowed method"})


@csrf_exempt
def Delete_from_User(request):
    if request.method == "DELETE":
        try:
            user = Users.objects.get(name="John Doe")
            user.delete()
            return JsonResponse({'msg': "user is deleted successfully"}, status=200)
        except Exception as e:
            return JsonResponse({'err': str(e)})
    return JsonResponse({'err': "Request not allowed"})


#
# select * from Users where date_of_birth>'1990-01-01'
#
# select title from Movies where genre='Sci-Fi'
#
# select name,email,date_of_birth from Users where name like "J%"
#
# select name,date_of_birth from Users order by date_of_birth asc limit 2
#
# select title,duration_minutes as duration from Movies where duration_minutes<160 order by duration desc
#
# select name,date_of_birth from Users order by date_of_birth desc limit 2
#
# select name,date_of_birth from Users where date_of_birth>'1990-01-01' order by date_of_birth desc limit 3

def select_where_order_by_limit(request):
    if request.method == 'GET':
        user = list(Users.objects.filter(date_of_birth__gt='1990-01-01').values())
        movies = list(Movies.objects.filter(genre='Sci-Fi').values())
        users = list(Users.objects.filter(name__startswith='J').values('name', 'email', 'date_of_birth'))
        users_limit = list(Users.objects.all().order_by("date_of_birth")[:2].values('name', 'email', 'date_of_birth'))
        duration = list(Movies.objects.filter(duration_minutes__lt=160).order_by('duration_minutes')[:2].values('title',
                                                                                                                'duration_minutes'))
        date_order = list(Users.objects.all().order_by('-date_of_birth')[:2].values('name', 'date_of_birth'))
        date_where = list(
            Users.objects.filter(date_of_birth__gt='1990-01-01').order_by('-date_of_birth')[:3].values('name',
                                                                                                       'date_of_birth'))
        return JsonResponse({'user': user, "movies": movies, 'Users starts with J': users, "users_limit": users_limit,
                             "duration": duration, 'date_order': date_order, 'where_date': date_where}, status=200)
    return JsonResponse({'err': 'Method is not allowed'}, status=400)

def joins_example(request):
    if request.method=='GET':
        watch = list(Watchhistory.objects.select_related('user').all().values())
        temp=list(Watchhistory.objects.filter(user__name__istartswith='T').values())
        return JsonResponse({'msg':watch,'temp':temp})
    return None