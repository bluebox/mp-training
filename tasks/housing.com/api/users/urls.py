from django.urls import path
from .views import *

urlpatterns = [
    path('signup', Signup.as_view()),
    path('login', Login.as_view()),
    path('user', User_view.as_view()),
    path('user/<int:id>', malik.as_view()),
    path('logout', Logout.as_view()),
    path('upload/',upload_file.as_view()),
    path('Edit_Profile',Edit_Profile.as_view())

]
