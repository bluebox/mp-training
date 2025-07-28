from django.db import models

from Book.models import Book

from Member.models import Member


class Issue(models.Model):
    book=models.ForeignKey(Book,on_delete=models.CASCADE,related_name='book')
    member=models.ForeignKey(Member,on_delete=models.CASCADE,related_name='member')
    status=models.CharField(choices=[('I','ISSUE'),('R','RETURNED')] ,max_length=1,default='I')
    issueDate=models.DateField(auto_now_add=True)
    returnDate = models.DateField(null=True, blank=True)

    class Meta:
        db_table="Issue"

    def __str__(self):
        return f"book id is {self.book}"
