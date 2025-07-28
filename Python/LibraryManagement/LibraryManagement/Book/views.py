from rest_framework import viewsets, status
from rest_framework.decorators import api_view, permission_classes
from rest_framework.permissions import IsAuthenticated
from rest_framework.response import Response

from .CustomPermission import CustomAdminPermission
from .models import Book
from .serializers import BookSerializer
from Issue.models import Issue

from Issue.serializers import IssueSerializer

from Member.serializers import MemberSerializer


class BookViewSet(viewsets.ModelViewSet):
    queryset = Book.objects.all()
    serializer_class = BookSerializer
    permission_classes = [CustomAdminPermission,IsAuthenticated]
    def update(self, request, *args, **kwargs):
        book = self.get_object()
        is_issued = Issue.objects.filter(book=book, status='I').exists()
        if is_issued:
            new_status = request.data.get('status', None)
            new_availablity = request.data.get('availablity', None)
            if book.status!=new_status or new_availablity!=book.availablity:
                return Response({'error':"this book is already issued can't update"},status=status.HTTP_400_BAD_REQUEST)

        return super().update(request, *args, **kwargs)

@api_view(['GET'])
@permission_classes([IsAuthenticated,CustomAdminPermission])
def getAlluser(request, id):
    issues = Issue.objects.select_related('book').filter(book__id=id)
    members = list({issue.member for issue in issues})
    serializer = MemberSerializer(members, many=True)
    return Response(serializer.data)
