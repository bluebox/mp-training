from django.urls import path, include
from rest_framework.routers import DefaultRouter

from bookingsapp import views
from bookingsapp.views import BookingViewset, FeedbackViewSet, LogoutViewSet, PaymentViewset, UserList, UserDetail, Login, getFeedbacks, uploadImage, RefreshJwtTokenViewSet

# from rest_framework_simplejwt.views import TokenObtainPairView, TokenRefreshView

# from rest_framework_simplejwt.models import TokenUser



#
# router = DefaultRouter()
# router.register('users', UserViewSet.as_view(), basename='users')

urlpatterns = [
    # path('', include(router.urls)),
    path('login/', Login.as_view(), name='login'),
    path('login/refresh/', RefreshJwtTokenViewSet.as_view(), name='token_refresh'),
    path('logout/', LogoutViewSet.as_view(), name='logout'),
    path('users/', UserList.as_view(), name='users'),
    # path('books/', BookViewSet.as_view(), name='books'),
    path('getuser/', UserDetail.as_view(), name='userDetails'),
    path('uploadImage/', uploadImage.as_view(), name='upload'),
    path('feedbacks/', getFeedbacks, name='feedback_list'),
    path('postfeedback/', FeedbackViewSet.as_view(), name='feedback_post'),
    path('payment/', PaymentViewset.as_view(), name='payment'),
    path('booking/', BookingViewset.as_view(), name='booking'),
    path('booking/<int:id>', BookingViewset.as_view(), name='booking'),

]