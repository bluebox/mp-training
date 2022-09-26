from django.contrib import admin
from django.urls import path
from . import views

urlpatterns = [
    # path('',IndexSerializer,name="index"),
    # path("post",IndexPostSerializer,name="indexpost"),
    # path("update/<int:id>/",updateSerializer,name="update"),
    # path("delete/<int:id>/",deleteSerializer,name="delete")
    # path("freelance_details",views.freelance_details_page,name="freelance_details"),
    # path("",views.index.as_view(),name="freelance_details"),
    path("freelancer",views.freelancerRegister.as_view(),name="freelancer_register"),
    path("freelancer/<str:email_id>/",views.freelanceUpdate.as_view(),name="freelance_update"),
    path("client", views.clientRegister.as_view(), name="client_register"),
    path("client/<str:email_id>/", views.clientUpdate.as_view(), name="client_update"),
    path("client_jobs", views.clientJobsRegister.as_view(), name="client_jobs_register"),
    path("client_jobs/<int:job_id>/", views.clientJobsUpdate.as_view(), name="client_jobs_update"),
    path("freelancer_proposals", views.freelancerProposals.as_view(), name="freelancer_proposals"),
    path("freelancer_proposals/<int:job_id>/", views.freelancerProposalsUpdate.as_view(), name="client_view_proposals"),
    path("proposals/<int:freelancer_id>/", views.proposals.as_view(), name="freelancer_view_proposals"),


]
