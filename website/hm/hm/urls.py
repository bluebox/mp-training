from django.contrib import admin
from django.urls import path,include
from rest_framework_simplejwt.views import TokenObtainPairView,TokenRefreshView,TokenVerifyView

urlpatterns = [
     path('r/', include('recruitment.urls')),
     path('l/', include('login.urls')),
     path('o/', include('onboarding.urls')),
     path('admin/', admin.site.urls),
     path('gettoken/',TokenObtainPairView.as_view(),name='gettoken'),
     path('refreshtoken/',TokenRefreshView.as_view(),name='refreshtoken'),
     path('verifytoken/',TokenVerifyView.as_view(),name='verifytoken'),
]
