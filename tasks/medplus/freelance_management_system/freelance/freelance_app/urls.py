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
    path("freelancer/login/",views.freelanceUpdate.as_view(),name="freelance_update"),
    path("freelancer/login/<str:email_id>/",views.freelanceUpdate.as_view(),name="freelance_update"),
    path("freelancer/details/",views.freelancer_login.as_view(),name="freelance_update"),
    path("client", views.clientRegister.as_view(), name="client_register"),
    path("client/<str:email_id>/", views.clientUpdate.as_view(), name="client_update"),
    path("client_jobs", views.clientJobsRegister.as_view(), name="client_jobs_register"),
    path("client_jobs_search", views.clientJobsSearch.as_view(), name="client_jobs_register"),
    path("get_client_jobs", views.getClientJobs.as_view(), name="get_client_jobs"),
    path("client_jobs/<int:job_id>/", views.clientJobsUpdate.as_view(), name="client_jobs_update"),
    path("freelancer_proposals", views.freelancerProposals.as_view(), name="freelancer_proposals"),
    path("freelancer_proposals/<int:job_id>/", views.freelancerProposalsUpdate.as_view(), name="client_view_proposals"),
    path("proposals/<int:freelancer_id>/", views.proposals.as_view(), name="freelancer_view_proposals"),
    path("get_freelancer_proposals", views.get_freelancer_proposals.as_view(), name="freelancer_view_proposals"),
    path("proposal_exists", views.Proposal_exists.as_view(), name="freelancer_view_proposals"),
    path("get_proposal_details", views.get_proposal_details.as_view(), name="get_proposal_details"),
    path("get_proposal", views.get_proposal.as_view(), name="get_proposal"),
    path("create_contract", views.create_contract.as_view(), name="get_proposal_details"),
    path("get_contract_of_client", views.get_contract_of_client.as_view(), name="get_contract_of_client"),
    path('client_payment',views.client_payment.as_view(),name='client_payment'),
    path('get_fee_payment_details',views.client_fee_payment_details_view.as_view(),name='client_payment'),
    path('get_freelancer_payment_details',views.get_freelancer_payment_details.as_view(),name='client_payment'),
    path('update_freelance_proposal/<int:proprosal_id>',views.update_freelance_proposal.as_view(),name='freelance'),
    path('get_contract_of_freelancer',views.get_contract_of_freelancer.as_view(),name='client_payment'),
    path('update_contract_details/<str:contract_id>',views.update_contract_details.as_view(),name='update_contract'),
    path('new_feedback',views.new_feedback.as_view(),name='new_feedback'),
    path('freelancer_feedback',views.new_feedback.as_view(),name='new_feedback'),
    path('freelancer_payment',views.update_freelance_proposal.as_view(),name='new_feedback'),
    path('new_freelancer_payment',views.new_freelancer_payment.as_view(),name='new_freelancer_feedback'),
    path('new_client_feedback',views.new_client_feedback.as_view(),name='new_client_feedback'),
    path('delete_client_job/<int:job_id>',views.clientJobsUpdate.as_view(),name='delete_client_job'),
    path('edit_client_job',views.clientJobsUpdate.as_view(),name='delete_client_job'),


]
