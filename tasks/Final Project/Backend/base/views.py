from django.shortcuts import render
from .product import movies  
from rest_framework.decorators import api_view, permission_classes
from rest_framework.permissions import IsAuthenticated, IsAdminUser
from rest_framework.response import Response
from django.core.paginator import Paginator, EmptyPage, PageNotAnInteger

from .models import Movie
from django.contrib.auth.models import User
from .serializers import *
from rest_framework_simplejwt.serializers import TokenObtainPairSerializer
from rest_framework_simplejwt.views import TokenObtainPairView
from rest_framework import status
from user.views import *

from django.contrib.auth.hashers import make_password

# Create your views here.



@api_view(['GET'])
def getMovies(request):
    query = request.query_params.get('keyword')
    print('query:', query)
    if query == None:
        query = ''

    movies = Movie.objects.filter(name__icontains=query)

    page = request.query_params.get('page')
    paginator = Paginator(movies, 4)

    try:
        movies = paginator.page(page)
    except PageNotAnInteger:
        movies = paginator.page(1)
    except EmptyPage:
        movies = paginator.page(paginator.num_pages)

    if page == None:
        page = 1

    page = int(page)
    serializer = MovieSerializer(movies, many=True)
    # print("Serializer from base",serializer.data)
    return Response({'movies':serializer.data, 'page':page, 'pages':paginator.num_pages})



@api_view(['POST']) 
@permission_classes([IsAdminUser])
def createMovie(request):
    user = request.user
    movie = Movie.objects.create(
        user=user,
        name='Sample Name',
        price=0,
        productionHouse ='Sample Brand',
        number_of_screens=0,
        category='Sample Category',
        description=''
    )
    serializer = MovieSerializer(movie, many=False)
    return Response(serializer.data)

@api_view(['PUT'])
@permission_classes([IsAdminUser])
def updateMovie(request, pk):
    data = request.data
    movie = Movie.objects.get(_id=pk)

    movie.name = data['name']
    movie.price = data['price']
    movie.productionHouse = data['productionHouse']
    movie.number_of_screens = data['number_of_screens']
    movie.category = data['category']
    movie.description = data['description']

    movie.save()
    
    serializer = MovieSerializer(movie, many=False)
    return Response(serializer.data)

@api_view(['GET'])
def getMovie(request, pk):
    movie = Movie.objects.get(_id=pk)
    serializer = MovieSerializer(movie, many=False)
    return Response(serializer.data)


@api_view(['DELETE'])
@permission_classes([IsAdminUser])
def deleteMovie(request, pk):
    movie = Movie.objects.get(_id=pk)
    movie.delete()
    return Response('Product Deleted')


@api_view(['POST'])
def uploadImage(request):
    data= request.data 

    movie_id = data['movie_id']
    movie = Movie.objects.get(_id=movie_id)

    movie.image = request.FILES.get('image')
    movie.save()
    return Response('Image was uploaded')

