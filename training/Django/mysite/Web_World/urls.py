from django.urls import path
from . import views
from rest_framework.views import APIView

urlpatterns = [
    path('', views.index, name='index'),
    # path("form/",views.get_form,name='form'),
    # path("ModelForm/",views.get_example,name='ModelForm'),
    # path("rest/",views.getData),
    # path("customers/",views.customers.as_view()),
    path("customers/<str:id>",views.customers.as_view()),
    path("orders/",views.orders.as_view()),
    path("orders/<int:id>", views.orders.as_view()),
    path("books/",views.books.as_view()),
    path("books/<int:id>",views.books.as_view()),


    path("authors/",views.authors),
    path("authors/<int:id>",views.authors),
    path("book_authors/",views.book_authors.as_view()),
]# Home page at root of this app
