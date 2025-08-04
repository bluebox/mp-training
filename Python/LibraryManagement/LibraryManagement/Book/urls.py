from django.urls import path,include
from rest_framework.routers import DefaultRouter

from .views import BookViewSet, getAlluserOfBookWithId, viewBooksAvailable, viewBookSearchField

router=DefaultRouter()
router.register(r'crud',BookViewSet,basename='books')
urlpatterns=[
    path('',include(router.urls),name='books'),
    path('alluserborrowers/<int:id>/',getAlluserOfBookWithId,name='getAlluserOfBookWithId'),
    path('viewbooksavailable/',viewBooksAvailable,name='viewBooksAvailable'),
    path('<str:title>/',viewBookSearchField,name='viewBookSearchField')
]