from django.urls import path,include
from rest_framework.routers import DefaultRouter

from .views import BookViewSet, getAlluserOfBookWithId, viewBooksAvailable

router=DefaultRouter()
router.register(r'crud',BookViewSet,basename='books')
urlpatterns=[
    path('',include(router.urls)),
    path('alluserborrowers/<int:id>/',getAlluserOfBookWithId,name='getAlluserOfBookWithId'),
    path('viewbooksavailable/',viewBooksAvailable,name='viewBooksAvailable'),
]