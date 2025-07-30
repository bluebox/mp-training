from django.urls import path

from .views import IssueBook, ReturnBook, AllIsssue, allBooksWithMemberId

urlpatterns=[
    path('issuebook/',IssueBook,name='IssueBook'),
    path('returnbook/<int:issue_id>/',ReturnBook,name='ReturnBook'),
    path('allrecords/',AllIsssue,name='AllIsssue'),
    path('allBooksWithMemberId/<int:id>/',allBooksWithMemberId,name='allBooksWithMemberId'),
]