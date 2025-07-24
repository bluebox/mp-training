from django.contrib.sitemaps.views import index
from django.urls import path,include
from rest_framework.routers import DefaultRouter
from .views import Users

router = DefaultRouter()
router.register('users', Users)

urlpatterns = [
    path('', include(router.urls)),
]