from django.urls import path
from base.views import user_views as views

urlpatterns = [
    path('login/', views.MyTokenObtainPairView.as_view(), name='token_obtain_pair'),
    path('register/', views.register_user, name='register_user'),
    path('profile/', views.getUserProfile, name='users_profile'),
    path('profile/update/', views.updateUserProfile, name='users_profile-update'),
    path('addAddress/', views.addNewAddress, name='add-address'),
    path('getAddress/', views.getAddress, name='get-address'),
    path('deleteAddress/<int:pk>', views.deleteAddress, name='delete-address'),
    path('addExperience/', views.addNewExperience, name='add-experience'),
    path('getExperience/', views.getExperience, name='get-experience'),
    path('deleteExperience/<int:pk>', views.deleteExperience, name='delete-experience'),
    path('addEducation/', views.addNewEducation, name='add-education'),
    path('getEducation/', views.getEducation, name='get-address'),
    path('deleteEducation/<int:pk>', views.deleteEducation, name='delete-education'),
    path('getSkills/', views.getSkills, name='get-skills'),
    path('getAllSkills/', views.getAllSkills, name='get-all-skills'),
    path('addToCart/', views.addNewToCart, name='add-to-cart'),
    path('getForCart/', views.getFromCart, name='add-to-cart'),
    path('deleteFromCart/<int:pk>', views.deleteFromCart, name='delete-from-cart'),
    path('editAddress/<int:pk>', views.editAddress, name='edit-address'),
    path('editExperience/<int:pk>', views.editExperience, name='edit-experience'),
    path('editEducation/<int:pk>', views.editEducation, name='edit-education'),
    path('addSkill/', views.addSkill, name='add-skill'),
    path('deleteSkill/<int:pk>', views.deleteSkill, name='delete-skill'),
    path('', views.getUsers, name='users'),
    
]

