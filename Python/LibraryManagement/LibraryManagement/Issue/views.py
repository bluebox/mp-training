from datetime import date

from django.core.serializers import serialize
from rest_framework.decorators import api_view, permission_classes
from rest_framework.permissions import IsAuthenticated
from rest_framework.response import Response
from rest_framework import status
from rest_framework.status import HTTP_200_OK

from .models import Issue
from Book.models import Book
from .serializers import IssueSerializer
from django.utils import timezone

from Book.CustomPermission import CustomAdminPermission


@api_view(['POST'])
@permission_classes([CustomAdminPermission,IsAuthenticated])
def IssueBook(request):
    serializer = IssueSerializer(data=request.data)
    if serializer.is_valid():
        book_id = serializer.validated_data['book'].id
        try:
            book = Book.objects.get(id=book_id)
        except Book.DoesNotExist:
            return Response({'error': 'Book not found'}, status=status.HTTP_404_NOT_FOUND)

        if book.availablity:
            serializer.save()
            book.availablity = False
            book.save()
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        else:
            return Response({'error': 'Book is not available'}, status=status.HTTP_400_BAD_REQUEST)
    return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

@api_view(['PATCH'])
@permission_classes([CustomAdminPermission,IsAuthenticated])
def ReturnBook(request, issue_id):
    try:
        issue = Issue.objects.get(id=issue_id)
    except Issue.DoesNotExist:
        return Response({'error': 'Issue record not found'}, status=status.HTTP_404_NOT_FOUND)

    if issue.status == 'R':
        return Response({'error': 'Book already returned'}, status=status.HTTP_400_BAD_REQUEST)



    issue.status = 'R'
    issue.returnDate = timezone.now().date()
    print(timezone.now().date())
    issue.save()
    issue.book.availablity = True
    issue.book.save()

    serializer = IssueSerializer(issue)
    return Response(serializer.data, status=status.HTTP_200_OK)

@api_view(['GET'])
@permission_classes([CustomAdminPermission,IsAuthenticated])
def AllIsssue(request):
    issue=Issue.objects.all()
    serializer=IssueSerializer(issue,many=True)
    return Response(serializer.data,status=HTTP_200_OK)
