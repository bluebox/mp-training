from django.urls import path
from . import views
from rest_framework_simplejwt.views import (
    TokenObtainPairView, 
)

urlpatterns = [
    path('login/', views.MyTokenObtainPairView.as_view(), name='token_obtain_pair'),
    path('register/', views.registerUser, name='register'),
    path('profile/update/', views.updateUserProfile, name='users=profile-update'),
    path('profile/', views.getUserProfile, name="users-profile"),
    path('', views.getUsers, name="users"),

    path('<int:pk>/', views.getUserById, name='user'),

    path('update/<int:pk>/', views.updateUser, name='user-update'),

    path('delete/<int:pk>/', views.deleteUser, name='user-delete'),

    path('towner/register/', views.registerTheatreUser, name='towner-register'),
]