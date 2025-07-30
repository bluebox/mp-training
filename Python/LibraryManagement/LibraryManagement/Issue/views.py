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
@permission_classes([IsAuthenticated])
def IssueBook(request):
    serializer = IssueSerializer(data=request.data)
    if serializer.is_valid():
        book_id = serializer.validated_data['book'].id
        book = Book.objects.get(id=book_id)
        if book.availablity:
            serializer.save()
            book.availablity = False
            book.save()
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        else:
            return Response({'error': 'Book is not available'}, status=status.HTTP_400_BAD_REQUEST)
    return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

@api_view(['PATCH'])
@permission_classes([IsAuthenticated])
def ReturnBook(request, issue_id):
    try:
        issue = Issue.objects.select_related('book').get(id=issue_id)
    except Issue.DoesNotExist:
        return Response({'error': 'Issue record not found'}, status=status.HTTP_404_NOT_FOUND)

    if issue.status == 'R':
        return Response({'error': 'Book already returned'}, status=status.HTTP_400_BAD_REQUEST)

    issue.status = 'R'
    issue.returnDate = timezone.now().date()
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


@api_view(['GET'])
@permission_classes([IsAuthenticated])
def allBooksWithMemberId(request,id):
    issues=Issue.objects.select_related('book','member').filter(member__id=id)
    books = []
    for issue in issues:
        book = issue.book
        books.append({
            'issue_id': issue.id,
            'book_id': book.id,
            'title': book.title,
            'author': book.author,
            'category': book.category,
            'issue_status': issue.status
        })
    return Response(books,status=HTTP_200_OK)