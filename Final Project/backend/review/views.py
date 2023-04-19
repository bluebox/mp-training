from django.shortcuts import render
from rest_framework.decorators import api_view, permission_classes
from .permissions import IsBuyer
from rest_framework import status
from rest_framework.response import Response
from .serializers import ReviewSerializer
# Create your views here.


@api_view(['POST'])
@permission_classes([IsBuyer])
def createReview(request, pk):
    request.data['product'] = pk
    request.data['user'] = request.user.id
    serializer = ReviewSerializer(data=request.data)
    if serializer.is_valid():
        serializer.save()
        return Response('Review added')
    return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)
