from django.urls import path
from rest_framework_simplejwt.views import TokenObtainPairView, TokenRefreshView

from . import views
from rest_framework.views import APIView

urlpatterns = [
    path('', views.index, name='index'),
    # path("form/",views.get_form,name='form'),
    # path("ModelForm/",views.get_example,name='ModelForm'),
    # path("rest/",views.getData),

    path("customers/", views.customers.as_view()),  # For query params like ?username=
    path("customers/<str:id>", views.customers.as_view()),  # For path-based retrieval
    path("orders/",views.orders.as_view()),
    path("orders/<int:id>", views.orders.as_view()),
    path("books/",views.books.as_view()),
    path("books/<int:id>",views.books.as_view()),

    path('getUserRole/<str:user_name>', views.getUserRole.as_view()),
    path("authors/",views.authors),
    path("authors/<int:id>",views.authors),
    path("book_authors/",views.book_authors.as_view()),

    path("login/", TokenObtainPairView.as_view(), name='token_obtain_pair'),
    path('api/token/', TokenObtainPairView.as_view(), name='token_obtain_pair'),
    path('api/token/refresh/', TokenRefreshView.as_view(), name='token_refresh')
]# Home page at root of this app
