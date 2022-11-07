from .models import *
from rest_framework import serializers


class FreelancerDetailsSerializers(serializers.ModelSerializer):
    class Meta:
        model = freelancer_details
        fields = '__all__'


class ClientDetailsSerializers(serializers.ModelSerializer):
    class Meta:
        model = client_details
        fields = '__all__'


class ClientJobsSerializers(serializers.ModelSerializer):
    class Meta:
        model = client_jobs
        fields = '__all__'


class FreelancerProposalsSerializers(serializers.ModelSerializer):
    class Meta:
        model = freelancer_proposals
        fields = '__all__'


class ClientContractDetailsSerializers(serializers.ModelSerializer):
    class Meta:
        model = client_contract_details
        fields = '__all__'


class FreelancerPaymentDetailsSerializers(serializers.ModelSerializer):
    class Meta:
        model = freelancer_payment_details
        fields = '__all__'


class ClientFeeRecordSerializers(serializers.ModelSerializer):
    class Meta:
        model = client_fee_record
        fields = '__all__'


class ClientFeedbackFormSerializers(serializers.ModelSerializer):
    class Meta:
        model = client_feedback_form
        fields = '__all__'


class FreelancerFeedbackFormSerializers(serializers.ModelSerializer):
    class Meta:
        model = freelancer_feedback_form
        fields = '__all__'
