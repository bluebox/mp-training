from django.urls import path
from base.views import company_views as views

urlpatterns = [
    path('register/', views.register_company, name='register_user'),
    path('profile/', views.getCompanyProfile, name='get-company-details'),
    path('profile/update/', views.updateProfile, name='update-profile'),
    path('company-profile/', views.getCompanyDetailProfile, name='company-profile'),
    path('company-profile/update', views.updateCompanyDetailProfile, name='company-profile'),
    path('posts/', views.get_company_posts, name='company_posts'),
    path('delete-post/<int:pk>', views.deletePost, name='delete-post'),
    path('posts/<int:pk>', views.get_company_post, name='company_detail_post'),
    path('candidate-details/<int:pk>', views.get_candidate_details, name='company_posts'),
    path('companies-list/', views.getCompaniesList, name='get-company-list'),
    path('companies-list-filter/<str:pk>', views.getCompaniesListByFiltering, name='get-company-list-by-filter'),
    path('companies-location-filter/<str:pk>', views.getCompaniesListByLocation, name='get-company-list-by-location-filter'),
    path('posts/<int:pk>/getAppliedCandidates/', views.get_applied_candidates, name='applied_candidates'),
]

