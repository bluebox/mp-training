from django.urls import path
from rest_framework_simplejwt.views import TokenObtainPairView, TokenRefreshView

from . import views
from rest_framework.views import APIView

urlpatterns = [
    # path('', views.index, name='index'),
    path("CustomUserView/", views.CustomUserView.as_view()),  # For query params like ?username=
    path("CustomUserView/<str:name>", views.CustomUserView.as_view()),
    path("orders/",views.orders.as_view()),
    path("orders/<int:id>", views.orders.as_view()),
    path("books/",views.books.as_view()),
    path("books/<str:id>",views.books.as_view()),
    path("getCustomers/",views.getCustomers,name="getCustomers"),
    path("getAuthors/",views.getAuthors,name="getAuthors"),
    path("login/",views.LogInView.as_view()),
path("logout/<int:id>",views.LogOut,name='LogOut'),
    path("GetAccessToken/<int:id>",views.GetAccessToken.as_view()),
path("getUsername/<int:id>",views.getUsername,name="getUsername"),
]
