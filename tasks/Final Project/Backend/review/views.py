from django.shortcuts import render
from base.product import movies  
from rest_framework.decorators import api_view, permission_classes
from rest_framework.permissions import IsAuthenticated, IsAdminUser
from rest_framework.response import Response
from .models import Review
from django.contrib.auth.models import User
# from .serializers import *
from rest_framework_simplejwt.serializers import TokenObtainPairSerializer
from rest_framework_simplejwt.views import TokenObtainPairView
from rest_framework import status
from user.views import *

from django.contrib.auth.hashers import make_password

# Create your views here.

@api_view(['POST'])
@permission_classes([IsAuthenticated])
def createMovieReview(request, pk):
    user = request.user
    movie = Movie.objects.get(_id=pk)
    data = request.data

    #1 - Review already exists
    alreadyExists = movie.review_set.filter(user=user).exists()

    if alreadyExists:
        content = {'details': 'Movie already reviewed'}
        return Response(content, status=status.HTTP_400_BAD_REQUEST)

    #2 - No Rating or 0
    elif data['rating'] == 0:
        content = {'details' : 'Please select a rating'}
        return Response(content, status=status.HTTP_400_BAD_REQUEST)

    # 3 - Create review

    else:
        review = Review.objects.create(
            user = user,
            movie = movie,
            name = user.first_name,
            rating = data['rating'],
            comment = data['comment'],
        )


        reviews = movie.review_set.all()
        movie.numReviews = len(reviews)

        total = 0
        for i in reviews:
            total += i.rating
        
        movie.rating = total / len(reviews)
        movie.save()

        return Response('Review Added')
