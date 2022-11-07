
from django.urls import path
from . import views


urlpatterns = [
    path("freelancer",views.FreelancerRegister.as_view(),name="freelancer_register"),
    path("freelancer/login/",views.FreelanceUpdate.as_view(),name="freelance_update"),
    path("freelancer/login/<str:email_id>/",views.FreelanceUpdate.as_view(),name="freelance_update"),
    path("freelancer/details/",views.FreelancerLogin.as_view(),name="freelance_update"),
    path("client", views.ClientRegister.as_view(), name="client_register"),
    path("client/<str:email_id>/", views.ClientUpdate.as_view(), name="client_update"),
    path("client_jobs", views.ClientJobsRegister.as_view(), name="client_jobs_register"),
    path("client_jobs_search", views.ClientJobsSearch.as_view(), name="client_jobs_register"),
    path("get_client_jobs", views.GetClientJobs.as_view(), name="get_client_jobs"),
    path("client_jobs/<int:job_id>/", views.ClientJobsUpdate.as_view(), name="client_jobs_update"),
    path("freelancer_proposals", views.FreelancerProposals.as_view(), name="freelancer_proposals"),
    path("freelancer_proposals/<int:job_id>/", views.FreelancerProposalsUpdate.as_view(), name="client_view_proposals"),
    path("proposals/<int:freelancer_id>/", views.Proposals.as_view(), name="freelancer_view_proposals"),
    path("get_freelancer_proposals", views.GetFreelancerProposals.as_view(), name="freelancer_view_proposals"),
    path("get_all_freelancer_proposals", views.Proposals.as_view(), name="freelancer_view_proposals"),
    path("proposal_exists", views.ProposalExists.as_view(), name="freelancer_view_proposals"),
    path("get_proposal_details", views.GetProposalDetails.as_view(), name="get_proposal_details"),
    path("get_proposal", views.GetProposal.as_view(), name="get_proposal"),
    path("create_contract", views.CreateContract.as_view(), name="get_proposal_details"),
    path("get_contract_of_client", views.GetContractOfClient.as_view(), name="get_contract_of_client"),
    path('client_payment',views.ClientPayment.as_view(),name='client_payment'),
    path('get_fee_payment_details',views.ClientFeePaymentDetailsView.as_view(),name='client_payment'),
    path('get_freelancer_payment_details',views.GetFreelancerPaymentDetails.as_view(),name='client_payment'),
    path('update_freelance_proposal/<int:proprosal_id>',views.UpdateFreelanceProposal.as_view(),name='freelance'),
    path('get_contract_of_freelancer',views.GetContractOfFreelancer.as_view(),name='client_payment'),
    path('update_contract_details/<str:contract_id>',views.UpdateContractDetails.as_view(),name='update_contract'),
    path('new_feedback',views.NewFeedback.as_view(),name='new_feedback'),
    path('freelancer_feedback',views.NewFeedback.as_view(),name='new_feedback'),
    path('freelancer_payment',views.UpdateFreelanceProposal.as_view(),name='new_feedback'),
    path('new_freelancer_payment',views.NewFreelancerPayment.as_view(),name='new_freelancer_feedback'),
    path('new_client_feedback',views.NewClientFeedback.as_view(),name='new_client_feedback'),
    path('delete_client_job/<int:job_id>',views.ClientJobsUpdate.as_view(),name='delete_client_job'),
    path('edit_client_job',views.ClientJobsUpdate.as_view(),name='delete_client_job'),


]
