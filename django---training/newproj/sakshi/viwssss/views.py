from django.shortcuts import render
from django.http import HttpResponse
from django.views.generic import ListView
#from books.models import Book

class BookListView(ListView):
    model = Book

    def books(self, *args, **kwargs):
        response = HttpResponse(
            # RFC 1123 date format.
            context={'name':harry potter and half blood prince ,
        )
        return response