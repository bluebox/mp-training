from django.urls import path
from . import views
from rest_framework_simplejwt.views import TokenObtainPairView,TokenRefreshView


urlpatterns = [
    path('', views.index, name='index'),
    path('contact/', views.contact_view, name='contact'),
    path('post/<int:id>/', views.post_detail, name='post_detail'),
    path("register/",views.register,name="register"),
    # path("login/",views.auth_login,name="register"),
    path("login/", TokenObtainPairView.as_view(), name='token_obtain_pair'),
    path('api/token/', TokenObtainPairView.as_view(), name='token_obtain_pair'),
    path('api/token/refresh/', TokenRefreshView.as_view(), name='token_refresh')
]
# in urls.py (project-level)
# handler404 = 'blog.views.custom_404'
# handler500 = 'blog.views.custom_500'
