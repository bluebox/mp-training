from django.urls import path
from home import views
urlpatterns = [
    path('',views.index),
    path('login',views.loginUser),
    path('signup',views.signup),
    path('base',views.base),
    path('display',views.display),
    path('BorrowAndReturn',views.bnr),
    path('logout',views.logoutUser),
]