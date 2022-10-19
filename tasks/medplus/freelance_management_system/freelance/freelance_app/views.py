import json
from .jwtauthetication import *
import jwt
from rest_framework.exceptions import AuthenticationFailed
from rest_framework.views import APIView
from django.http import HttpResponse, JsonResponse, Http404
from django.contrib import messages
from django.shortcuts import render,redirect
from .models import *
from rest_framework import serializers, status
from rest_framework.decorators import api_view
from rest_framework.response import Response
from .forms import  FreelanceForm
from rest_framework.viewsets import ViewSet
from rest_framework import viewsets
from rest_framework import  generics
from .serializers import  *
from rest_framework.generics import ListAPIView,GenericAPIView
# Create your views here.



class freelancerRegister(APIView):
    def get(self, request,format=None):
        freelancer = freelancer_details.objects.all()
        serializer = freelancer_details_serializers(freelancer, many=True)
        return Response(serializer.data)

    def post(self, request, format=None):
        serializer = freelancer_details_serializers(data = request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

class freelanceUpdate(APIView):
    def get_object(self, email_id):
        try:
           return freelancer_details.objects.get(email_id=email_id)
        except client_details.DoesNotExist :
            raise Http404
    def put(self,request, email_id, format=None):
        freelance = self.get_object(email_id=email_id)
        serializer = freelancer_details_serializers(freelance, data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

    def post(self, request):
        email = request.data['email_id']
        password = request.data['password']
        user = freelancer_details.objects.filter(email_id=email).first()

        if user is None:
            raise AuthenticationFailed('User not found')

        if user.password!=password:
            raise AuthenticationFailed('Incorrect password!')

        # payload ={
        #     'passenger_id': user.passenger_id,
        #     'exp': datetime.datetime.utcnow()+datetime.timedelta(minutes=120),
        #     'iat': datetime.datetime.utcnow()
        # }

        access_token = create_access_token(user.email_id)
        refresh_token = create_refresh_token(user.email_id)

        UserToken.objects.create(
            email_id = user.email_id,
            token = refresh_token,
            expired_at = datetime.datetime.utcnow() + datetime.timedelta(days = 7)
        )

        response = Response()
        response.set_cookie(key='refresh_token', value=refresh_token, httponly=True)
        response.data ={
             'access_token': access_token,
             'msg': "successfully logged in"
        }

        return response


class freelancer_login(APIView):
    authentication_classes = [JWTAuthentication]
    def get_object(self, email_id):
        try:
           return freelancer_details.objects.get(email_id=email_id)
        except client_details.DoesNotExist :
            raise Http404
    def get(self,request, format=None):
        serializer = freelancer_details_serializers(request.user)
        return Response(serializer.data)
    def put(self,request, email_id, format=None):
        freelance = self.get_object(email_id=email_id)
        serializer = freelancer_details_serializers(freelance, data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

    def delete(self, request, email_id, format=None):
        freelance = self.get_object(email_id=email_id)
        freelance.delete()
        return Response(status=status.HTTP_204_NO_CONTENT)
class clientRegister(APIView):
    def get(self, request,format=None):
        client = client_details.objects.all()
        serializer = client_details_serializers(client, many=True)
        return Response(serializer.data)
    def  post(self, request, format=None):
        serializer = client_details_serializers(data = request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

class clientUpdate(APIView):
    def get_object(self, email_id):
        try:
           return client_details.objects.get(email_id=email_id)
        except client_details.DoesNotExist :
            raise Http404

    def get(self,request, email_id, format=None):
        client = self.get_object(email_id=email_id)
        serializer = client_details_serializers(client)
        return Response(serializer.data)

    def put(self,request, email_id, format=None):
        client = self.get_object(email_id=email_id)
        serializer = client_details_serializers(client, data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

    def delete(self, request, email_id, format=None):
        client = self.get_object(email_id=email_id)
        client.delete()
        return Response(status=status.HTTP_204_NO_CONTENT)

class clientJobsRegister(APIView):
    def get(self, request, format=None):
        client = client_jobs.objects.all()
        serializer = client_jobs_serializers(client, many=True)
        return Response(serializer.data)

    def post(self, request, format=None):
        print(request.data)
        serializer = client_jobs_serializers(data=request.data)

        if serializer.is_valid():
            print(serializer)
            serializer.save()
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)


class clientJobsSearch(APIView):
    def get(self, request):
        client = client_jobs.objects.filter(project_title__icontains=request.GET.get('project_title'))
        serializer = client_jobs_serializers(client, many=True)
        return Response(serializer.data)
class clientJobsUpdate(APIView):
    def get_object(self, job_id):
        try:
            return client_jobs.objects.get(job_id=job_id)
        except client_jobs.DoesNotExist:
            raise Http404

    def get(self, request, job_id, format=None):
        client = self.get_object(job_id=job_id)
        serializer = client_jobs_serializers(client)
        return Response(serializer.data)

    def put(self, request,job_id, format=None):
        client = self.get_object(job_id=job_id)
        serializer = client_jobs_serializers(client, data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

    def delete(self, request, job_id, format=None):
        client = self.get_object(job_id=job_id)
        client.delete()
        return Response(status=status.HTTP_204_NO_CONTENT)



class freelancerProposals(APIView):
    def get(self, request, format=None):
        client = freelancer_proposals.objects.all()
        serializer = client_jobs_serializers(client, many=True)
        return Response(serializer.data)

    def post(self, request, format=None):
        serializer = freelancer_proposals_serializers(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)
    def put(self, request):
        client = freelancer_proposals.objects.get(proprosal_id = int(request.GET.get('proprosal_id')))
        serializer = freelancer_proposals_serializers(client, data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)
    def delete(self, request):
        client = freelancer_proposals.objects.get(proprosal_id=int(request.GET.get('proprosal_id')))
        client.delete()
        return Response(status=status.HTTP_204_NO_CONTENT)
class freelancerProposalsUpdate(APIView):
    def get_object(self, job_id):
        try:
            return freelancer_proposals.objects.get(job_id=job_id)
        except freelancer_proposals.DoesNotExist:
            raise Http404

    def get(self, request, job_id, format=None):
        client = self.get_object(job_id=job_id)
        serializer = freelancer_proposals_serializers(client)
        return Response(serializer.data)

    def put(self, request, job_id, format=None):
        client = self.get_object(job_id=job_id)
        serializer = freelancer_proposals_serializers(client, data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

    def delete(self, request, job_id, format=None):
        client = self.get_object(job_id=job_id)
        client.delete()
        return Response(status=status.HTTP_204_NO_CONTENT)



class proposals(APIView):
    def get_object(self, freelancer_id):
        try:
            return freelancer_proposals.objects.get(freelancer_id=freelancer_id)
        except freelancer_proposals.DoesNotExist:
            raise Http404

    def get(self, request, freelancer_id, format=None):
        client = self.get_object(freelancer_id=freelancer_id)
        serializer = freelancer_proposals_serializers(client)
        return Response(serializer.data)

    def put(self, request, freelancer_id, format=None):
        client = self.get_object(freelancer_id=freelancer_id)
        serializer = freelancer_proposals_serializers(client, data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

    def delete(self, request, freelancer_id, format=None):
        client = self.get_object(freelancer_id=freelancer_id)
        client.delete()
        return Response(status=status.HTTP_204_NO_CONTENT)

class get_freelancer_proposals(APIView):
    def get(self,request):

        client = freelancer_proposals.objects.filter(freelancer_id=int(request.GET.get('id')))
        serializer = freelancer_proposals_serializers(client,many=True)
        return Response(serializer.data)

class Proposal_exists(APIView):
    def get(self,request):
        data = freelancer_proposals.objects.filter(job_id=int(request.GET.get('job_id')),freelancer_id=1).first()
        serializer = freelancer_proposals_serializers(data)
        return Response(serializer.data)
class get_proposal_details(APIView):
    def get(self,request):

        client = freelancer_proposals.objects.filter(job_id=int(request.GET.get('job_id')))
        serializer = freelancer_proposals_serializers(client,many=True)
        return Response(serializer.data)

class get_proposal(APIView):
    def get(self,request):

        client = freelancer_proposals.objects.get(proprosal_id=int(request.GET.get('proprosal_id')))
        serializer = freelancer_proposals_serializers(client)
        return Response(serializer.data)
class getClientJobs(APIView):
    def get(self,request):
        get_client_jobs = client_jobs.objects.filter(client_id = int(request.GET.get('client_id')))
        serializers = client_jobs_serializers(get_client_jobs,many=True)
        return Response(serializers.data)


class create_contract(APIView):
    def post(self,request):
        serializer = client_contract_details_serializers(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        return Response(serializer.errors)

class get_contract_of_client(APIView):
    def get(self,request):
        get_client_jobs = client_contract_details.objects.filter(client_id=request.GET.get('client_id'))
        serializers = client_contract_details_serializers(get_client_jobs,many=True)
        return Response(serializers.data)


class client_payment(APIView):
    def post(self,request):
        serializer = client_fee_record_serializers(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        return Response(serializer.errors)

class client_fee_payment_details_view(APIView):
    def get(self,request):
        get_client_jobs = client_fee_record.objects.get(contract_id=request.GET.get('contract_id'))
        serializers = client_fee_record_serializers(get_client_jobs)
        return Response(serializers.data)
class get_freelancer_payment_details(APIView):
    def get(self,request):
        object = freelancer_payment_details.objects.filter(freelancer_id = request.GET.get('freelancer_id'))
        print(object)
        serializers = freelancer_payment_details_serializers(object,many=True)
        return Response(serializers.data)


class update_freelance_proposal(APIView):
    def put(self, request, proprosal_id, format=None):
        client =freelancer_proposals.objects.get(proprosal_id=proprosal_id)
        serializer = freelancer_proposals_serializers(client, data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

    def get(self,request):
        client =freelancer_proposals.objects.get(proprosal_id=request.GET.get('proprosal_id'))
        serializers = freelancer_proposals_serializers(client)
        return Response(serializers.data)



class update_contract_details(APIView):
    def put(self, request, contract_id, format=None):
        client =client_contract_details.objects.get(contract_id=int(contract_id))
        serializer = client_contract_details_serializers(client, data=request.data)
        print(serializer)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

class get_contract_of_freelancer(APIView):
    def get(self,request):
        get_client_jobs = client_contract_details.objects.get(emp_proposal_id=request.GET.get('emp_proposal_id'))
        serializers = client_contract_details_serializers(get_client_jobs)
        return Response(serializers.data)



class new_feedback(APIView):
    def post(self,request):
        serializer = freelancer_feedback_form_serializers(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        return Response(serializer.errors)


    def get(self,request):
        try:
            feedback = freelancer_feedback_form.objects.get(contract_id=request.GET.get('contract_id'))
            serializers = freelancer_feedback_form_serializers(feedback)
            return Response(serializers.data)
        except freelancer_feedback_form.DoesNotExist:
            raise Http404

class new_freelancer_payment(APIView):
    def post(self,request):
        serializer = freelancer_payment_details_serializers(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        return Response(serializer.errors)


class new_client_feedback(APIView):
    def post(self,request):
        serializer = client_feedback_form_serializers(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        return Response(serializer.errors)


    def get(self,request):
        try:
            feedback = client_feedback_form.objects.get(contract_id=request.GET.get('contract_id'))
            serializers = client_feedback_form_serializers(feedback)
            return Response(serializers.data)
        except client_feedback_form.DoesNotExist:
            raise Http404
