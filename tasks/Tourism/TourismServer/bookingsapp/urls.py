from django.urls import path
from bookingsapp.views import AllUserList, BookingAdminDetailViewset, BookingAdminViewset, BookingDetailsViewset, BookingViewset, CancellationDetail, CancellationList, FeedbackViewSet, LogoutViewSet, PaymentDetails, PaymentDetailsViewset, PaymentViewset, UpdateUserByAdmin, UserList, UserDetail, Login, addUserByAdmin, getAverageRatingAndTotalRatings, getFeedbacks, uploadImage, uploadVideo

urlpatterns = [
    # path('', include(router.urls)),
    path('login/', Login.as_view(), name='login'),
    # path('login/refresh/', RefreshJwtTokenViewSet.as_view(), name='token_refresh'),
    path('logout/', LogoutViewSet.as_view(), name='logout'),
    path('users/', UserList.as_view(), name='users'),
    path('allusers/', AllUserList.as_view(), name='users'),
    # path('users/<int:pk>', UserDetail.as_view(), name='users'),
    path('users/<int:pk>', UpdateUserByAdmin.as_view(), name='userAdminUpdate'),
    # path('books/', BookViewSet.as_view(), name='books'),
    path('user/', UserDetail.as_view(), name='userDetails'),
    path('add_user_by_admin/', addUserByAdmin.as_view(), name='add_user_by_admin'),
    path('uploadImage/', uploadImage.as_view(), name='upload_image'),
    path('uploadVideo/', uploadVideo.as_view(), name='upload_video'),
    path('feedbacks/', getFeedbacks, name='feedback_list'),
    path('postfeedback/', FeedbackViewSet.as_view(), name='feedback_post'),
    path('payment/', PaymentViewset.as_view(), name='payment'),
    path('payment/<int:pk>', PaymentDetailsViewset.as_view(), name='payment'),
    path('booking/', BookingViewset.as_view(), name='booking'),
    path('booking/<int:pk>', BookingDetailsViewset.as_view(), name='booking'),
    path('admin_booking_list/', BookingAdminViewset.as_view()),
    path('admin_booking_list/<int:pk>', BookingAdminDetailViewset.as_view()),
    path('cancellation/', CancellationList.as_view()),
    path('cancellation/<int:pk>', CancellationDetail.as_view()),
    path('getAverageRating/', getAverageRatingAndTotalRatings),

]