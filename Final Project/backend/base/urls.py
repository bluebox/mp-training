from django.urls import path
from . import views
urlpatterns = [
    path('products/', views.getProducts, name="products"),
    path('product/<int:pk>/', views.getProduct, name="product-details"),
    path('users/', views.getUsers, name='user'),
    path('user/<int:pk>/', views.getUser, name='user_detail'),
    path('user/profile/', views.getUserProfile, name='user_profile'),
    path('user/profile/update/', views.updateUserProfile, name='update_user_profile'),
    path('token/', views.MyTokenObtainPairView.as_view(), name='token_obtain'),
    path('user/register/', views.registerUser, name='register_user'),
    path('users/delete/<int:pk>/', views.deleteUser, name='delete_user'),
    path('users/detail/<int:pk>/', views.getUserDetail, name='user_detail'),
    path('users/update/<int:pk>/', views.updateUserDetail, name='user_update'),
    path('product/create/', views.createProduct, name='create_product'),
    path('product/delete/<int:pk>/', views.deleteProduct, name='product_delete'),
    path('product/update/<int:pk>/', views.updateProduct, name='update_product'),
]