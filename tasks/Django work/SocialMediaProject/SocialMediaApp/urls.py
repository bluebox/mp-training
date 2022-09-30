from django.urls import path
from .views import SendPasswordResetEmailView, UserChangePasswordView, UserProfileView, UserRegistration, UserLoginView
from rest_framework_simplejwt.views import TokenObtainPairView, TokenRefreshView, TokenVerifyView
urlpatterns = [
    path('register/', UserRegistration.as_view(), name='token_obtain_pair'),
    path('login/', UserLoginView.as_view(), name='token_obtain_pair'),
    path('profile/', UserProfileView.as_view(), name='token_obtain_pair'),
    path('changepassword/', UserChangePasswordView.as_view(), name='token_obtain_pair'),
    path('sendemail/', SendPasswordResetEmailView.as_view(), name='SendPasswordResetEmailView'),
    path('gettoken/', TokenObtainPairView.as_view(), name='token_obtain_pair'),
    path('refreshtoken/', TokenRefreshView.as_view(), name='token_refresh'),
    path('verifytoken/', TokenVerifyView.as_view(), name='token_verify'),
   
]
