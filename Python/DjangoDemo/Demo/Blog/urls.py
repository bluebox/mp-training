from django.urls import path
from .views import Blog_api, Comments_api

urlpatterns = [
    path('', Blog_api.as_view(), name='blog'),
    path('comment/',Comments_api.as_view(),name='comments')
]
