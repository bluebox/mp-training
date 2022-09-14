from django.urls import path
from jangoapp4 import views

urlpatterns = [
   path('register/',views.register,name='register')
]
