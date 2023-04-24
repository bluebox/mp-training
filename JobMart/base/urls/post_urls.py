from django.urls import path
from base.views import posts_views as views

urlpatterns = [
    
    path('', views.getPosts, name='posts' ),
    path('<int:pk>/', views.getPost, name='post' ),
    path('addJobPost/', views.addPost, name='added-post' ),
    path('editJobPost/<int:pk>/', views.editPost, name='edit-post' ),
]

