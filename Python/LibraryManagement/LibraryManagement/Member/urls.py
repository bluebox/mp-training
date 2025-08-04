from django.urls import path, include
from rest_framework.routers import DefaultRouter
from .views import MemberViewSet, fetchParticularUser, verify_access_token, FileUploadView
from rest_framework_simplejwt.views import (
    TokenObtainPairView,
    TokenRefreshView,
)

router=DefaultRouter()
router.register(r'crud',MemberViewSet)
urlpatterns=[
    path('',include(router.urls)),
    path('login/', TokenObtainPairView.as_view(), name='token_obtain_pair'),
    path('token/refresh/', TokenRefreshView.as_view(), name='token_refresh'),
    path('fetchParicularUser/',fetchParticularUser,name='fetchParticularUser'),
    path('verify_access_token/', verify_access_token, name='verify_access_token'),
    path('upload_file/',FileUploadView.as_view(),name='FileUploadView')
]

