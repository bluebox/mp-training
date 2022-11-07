from .jwtauthetication import *
from django.db.models import Q
from rest_framework.exceptions import AuthenticationFailed
from rest_framework.views import APIView
from django.http import Http404
from rest_framework import status
from rest_framework.response import Response

from .serializers import *
from .paginator import *

from .manager import *



class FreelancerRegister(APIView):
    def get(self,request):
        freelancer = freelancer_details.objects.all()
        serializer = FreelancerDetailsSerializers(freelancer, many=True)
        return Response(serializer.data)

    def post(self, request):
        serializer = FreelancerDetailsSerializers(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)


class FreelanceUpdate(APIView):
    @staticmethod
    def get_object(email_id):
        try:
            return freelancer_details.objects.get(email_id=email_id)
        except client_details.DoesNotExist:
            raise Http404

    def put(self, request, email_id):
        freelance = self.get_object(email_id=email_id)
        serializer = FreelancerDetailsSerializers(freelance, data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

    def post(self, request):
        response = freelancer_authentication(request)
        return response


class FreelancerLogin(APIView):
    authentication_classes = [JWTAuthentication]

    def get_object(self, email_id):
        try:
            return freelancer_details.objects.get(email_id=email_id)
        except client_details.DoesNotExist:
            raise Http404

    def get(self, request):
        serializer = FreelancerDetailsSerializers(request.user)
        return Response(serializer.data)

    def put(self, request, email_id):
        freelance = self.get_object(email_id=email_id)
        serializer = FreelancerDetailsSerializers(freelance, data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

    def delete(self, request, email_id):
        freelance = self.get_object(email_id=email_id)
        freelance.delete()
        return Response(status=status.HTTP_204_NO_CONTENT)


class ClientRegister(APIView):
    def get(self, request):
        client = client_details.objects.all()
        serializer = ClientDetailsSerializers(client, many=True)
        return Response(serializer.data)

    def post(self, request):
        serializer = ClientDetailsSerializers(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)


class ClientUpdate(APIView):
    @staticmethod
    def get_object(email_id):
        try:
            return client_details.objects.get(email_id=email_id)
        except client_details.DoesNotExist:
            raise Http404

    def get(self, request, email_id):
        client = self.get_object(email_id=email_id)
        serializer = ClientDetailsSerializers(client)
        return Response(serializer.data)

    def put(self, request, email_id):
        client = self.get_object(email_id=email_id)
        serializer = ClientDetailsSerializers(client, data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

    def delete(self, request, email_id):
        client = self.get_object(email_id=email_id)
        client.delete()
        return Response(status=status.HTTP_204_NO_CONTENT)


class ClientJobsRegister(APIView):

    def get(self, request):
        client = client_jobs.objects.filter(project_title__icontains=request.GET.get('project_title'), is_deleted=False)
        users, total_pages, page = listing(request, client)
        serializer = ClientJobsSerializers(users, many=True)
        return Response({
            'pageItems': serializer.data,
            'totalPages': total_pages,
            'page': page
        })

    def post(self, request):
        print(request.data)
        serializer = ClientJobsSerializers(data=request.data)

        if serializer.is_valid():
            print(serializer)
            serializer.save()
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)


class ClientJobsSearch(APIView):
    def get(self, request):
        client = client_jobs.objects.filter(project_title__icontains=request.GET.get('project_title'))
        serializer = ClientJobsSerializers(client, many=True)
        return Response(serializer.data)


class ClientJobsUpdate(APIView):
    def get_object(self, job_id):
        try:
            return client_jobs.objects.get(job_id=job_id)
        except client_jobs.DoesNotExist:
            raise Http404

    def get(self, request, job_id):
        client = self.get_object(job_id=job_id)
        serializer = ClientJobsSerializers(client)
        return Response(serializer.data)

    def put(self, request, job_id):
        client = self.get_object(job_id=job_id)
        serializer = ClientJobsSerializers(client, data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

    def delete(self, request, job_id):
        client = self.get_object(job_id=job_id)
        client.delete()
        return Response(status=status.HTTP_204_NO_CONTENT)


class FreelancerProposals(APIView):
    # def get(self, request):
    #     client = freelancer_proposals.objects.all()
    #     serializer = client_jobs_serializers(client, many=True)
    #     return Response(serializer.data)

    def post(self, request):
        serializer = FreelancerProposalsSerializers(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

    def put(self, request):
        client = freelancer_proposals.objects.get(proprosal_id=int(request.GET.get('proprosal_id')))
        serializer = FreelancerProposalsSerializers(client, data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

    def delete(self, request):
        client = freelancer_proposals.objects.get(proprosal_id=int(request.GET.get('proprosal_id')))
        client.delete()
        return Response(status=status.HTTP_204_NO_CONTENT)


class FreelancerProposalsUpdate(APIView):
    @staticmethod
    def get_object(job_id):
        try:
            return freelancer_proposals.objects.get(job_id=job_id)
        except freelancer_proposals.DoesNotExist:
            raise Http404

    def get(self, request, job_id):
        client = self.get_object(job_id=job_id)
        serializer = FreelancerProposalsSerializers(client)
        return Response(serializer.data)

    def put(self, request, job_id):
        client = self.get_object(job_id=job_id)
        serializer = FreelancerProposalsSerializers(client, data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

    def delete(self, request, job_id):
        client = self.get_object(job_id=job_id)
        client.delete()
        return Response(status=status.HTTP_204_NO_CONTENT)


class Proposals(APIView):
    @staticmethod
    def get_object(freelancer_id):
        try:
            return freelancer_proposals.objects.filter(freelancer_id=freelancer_id)
        except freelancer_proposals.DoesNotExist:
            raise Http404

    def get(self, request):
        client = self.get_object(freelancer_id=request.GET.get('id'))
        serializer = FreelancerProposalsSerializers(client, many=True)
        return Response(serializer.data)

    def put(self, request):
        client = self.get_object(freelancer_id=request.GET.get('id'))
        serializer = FreelancerProposalsSerializers(client, data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

    def delete(self, request, freelancer_id):
        client = self.get_object(freelancer_id=freelancer_id)
        client.delete()
        return Response(status=status.HTTP_204_NO_CONTENT)


class GetFreelancerProposals(APIView):
    def get(self, request):
        client = freelancer_proposals.objects.filter(Q(freelancer_id=int(request.GET.get('id'))),
                                                     Q(proprosal_status__icontains=request.GET.get('data')))
        users, total_pages, page = listing(request, client)
        serializer = FreelancerProposalsSerializers(users, many=True)
        return Response({
            'pageItems': serializer.data,
            'totalPages': total_pages,
            'page': page
        })

        # return Response(serializer.data)


class ProposalExists(APIView):
    def get(self, request):
        data = freelancer_proposals.objects.filter(job_id=int(request.GET.get('job_id')),
                                                   freelancer_id=request.GET.get('freelancer_id')).first()
        serializer = FreelancerProposalsSerializers(data)
        return Response(serializer.data)


class GetProposalDetails(APIView):
    def get(self, request):
        client = freelancer_proposals.objects.filter(job_id=int(request.GET.get('job_id')))
        serializer = FreelancerProposalsSerializers(client, many=True)
        return Response(serializer.data)


class GetProposal(APIView):
    def get(self, request):
        client = freelancer_proposals.objects.get(proprosal_id=int(request.GET.get('proprosal_id')))
        serializer = FreelancerProposalsSerializers(client)
        return Response(serializer.data)


class GetClientJobs(APIView):
    def get(self, request):
        if request.GET.get('filter') == 'true':
            filter1 = True
        elif request.GET.get('filter') == 'all':
            get_client_jobs = client_jobs.objects.filter(Q(client_id=int(request.GET.get('client_id'))))
            users, total_pages, page = listing(request, get_client_jobs)
            serializer = ClientJobsSerializers(users, many=True)
            return Response({
                'pageItems': serializer.data,
                'totalPages': total_pages,
                'page': page
            })
        else:
            filter1 = False
        get_client_jobs = client_jobs.objects.filter(Q(client_id=int(request.GET.get('client_id'))),
                                                     Q(is_deleted=filter1))
        users, total_pages, page = listing(request, get_client_jobs)
        serializer = ClientJobsSerializers(get_client_jobs, many=True)

        return Response({
            'pageItems': serializer.data,
            'totalPages': total_pages,
            'page': page
        })


class CreateContract(APIView):
    def post(self, request):
        serializer = ClientContractDetailsSerializers(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        return Response(serializer.errors)


class GetContractOfClient(APIView):
    def get(self, request):
        get_client_jobs = client_contract_details.objects.filter(client_id=request.GET.get('client_id'))
        serializer = ClientContractDetailsSerializers(get_client_jobs, many=True)
        return Response(serializer.data)


class ClientPayment(APIView):
    def post(self, request):
        serializer = ClientFeeRecordSerializers(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        return Response(serializer.errors)


class ClientFeePaymentDetailsView(APIView):
    def get(self, request):
        get_client_jobs = client_fee_record.objects.get(contract_id=request.GET.get('contract_id'))
        serializer = ClientFeeRecordSerializers(get_client_jobs)
        return Response(serializer.data)


class GetFreelancerPaymentDetails(APIView):
    def get(self, request):
        object1 = freelancer_payment_details.objects.filter(freelancer_id=request.GET.get('freelancer_id'))
        users, total_pages, page = listing(request, object1)
        serializer = FreelancerPaymentDetailsSerializers(users, many=True)
        return Response({
            'pageItems': serializer.data,
            'totalPages': total_pages,
            'page': page
        })


class UpdateFreelanceProposal(APIView):
    def put(self, request, proprosal_id):
        client = freelancer_proposals.objects.get(proprosal_id=proprosal_id)
        serializer = FreelancerProposalsSerializers(client, data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

    def get(self, request):
        client = freelancer_proposals.objects.get(proprosal_id=request.GET.get('proprosal_id'))
        serializer = FreelancerProposalsSerializers(client)
        return Response(serializer.data)


class UpdateContractDetails(APIView):
    def put(self, request, contract_id):
        client = client_contract_details.objects.get(contract_id=int(contract_id))
        serializer = ClientContractDetailsSerializers(client, data=request.data)
        print(serializer)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)


class GetContractOfFreelancer(APIView):
    def get(self, request):
        get_client_jobs = client_contract_details.objects.get(emp_proposal_id=request.GET.get('emp_proposal_id'))
        serializer = ClientContractDetailsSerializers(get_client_jobs)
        return Response(serializer.data)


class NewFeedback(APIView):
    def post(self, request):
        serializer = FreelancerFeedbackFormSerializers(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        return Response(serializer.errors)

    def get(self, request):
        try:
            feedback = freelancer_feedback_form.objects.get(contract_id=request.GET.get('contract_id'))
            serializer = FreelancerFeedbackFormSerializers(feedback)
            return Response(serializer.data)
        except freelancer_feedback_form.DoesNotExist:
            raise Http404


class NewFreelancerPayment(APIView):
    def post(self, request):
        serializer = FreelancerPaymentDetailsSerializers(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        return Response(serializer.errors)


class NewClientFeedback(APIView):
    def post(self, request):
        serializer = ClientFeedbackFormSerializers(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        return Response(serializer.errors)

    def get(self, request):
        try:
            feedback = client_feedback_form.objects.get(contract_id=request.GET.get('contract_id'))
            serializer = ClientFeedbackFormSerializers(feedback)
            return Response(serializer.data)
        except client_feedback_form.DoesNotExist:
            raise Http404
