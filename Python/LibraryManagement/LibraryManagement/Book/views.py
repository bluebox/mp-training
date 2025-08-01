from django.core.serializers import serialize
from django.db.models import Q
from rest_framework import viewsets, status
from rest_framework.decorators import api_view, permission_classes
from rest_framework.permissions import IsAuthenticated
from rest_framework.response import Response
from rest_framework.status import HTTP_200_OK

from .CustomPermission import CustomAdminPermission
from .models import Book
from .serializers import BookSerializer
from Issue.models import Issue

from Issue.serializers import IssueSerializer

from Member.serializers import MemberSerializer

from Member.models import Member


class BookViewSet(viewsets.ModelViewSet):
    queryset = Book.objects.all()
    serializer_class = BookSerializer
    permission_classes = [CustomAdminPermission,IsAuthenticated]
    def update(self, request, *args, **kwargs):
        book=Book.objects.get(id=kwargs['pk'])
        is_issued = Issue.objects.filter(book=book, status='I').exists()
        if is_issued:
            return Response({'error':"this book is already issued can't update"},status=status.HTTP_400_BAD_REQUEST)
        return super().update(request, *args, **kwargs)

@api_view(['GET'])
@permission_classes([IsAuthenticated,CustomAdminPermission])
def getAlluserOfBookWithId(request, id):
    """issues = Issue.objects.select_related('book').filter(book__id=id)
    members = list({issue.member for issue in issues})
    serializer = MemberSerializer(members, many=True)
    return Response(serializer.data)"""
    issues=Issue.objects.prefetch_related('member').all()
    member=set(issue.member for issue in issues)
    serializer=MemberSerializer(member,many=True)
    return Response(serializer.data,status=HTTP_200_OK)

@api_view(['GET'])
@permission_classes([IsAuthenticated])
def viewBooksAvailable(request):
    query=Q()
    query &=Q(status=True)
    query &=Q(availablity=True)
    book=Book.objects.filter(query)
    serializer=BookSerializer(book,many=True)
    print(book)
    return Response(serializer.data,status=HTTP_200_OK)