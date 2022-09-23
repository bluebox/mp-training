from .models import  *
from rest_framework import serializers
class freelancer_detailsSerializers(serializers.ModelSerializer):
    class Meta:
        model = freelancer_details
        fields = '__all__'

class client_detailsSerializers(serializers.ModelSerializer):
    class Meta:
        model = client_details
        fields = '__all__'


class client_jobs_serializers(serializers.ModelSerializer):
    class Meta:
        model = client_jobs
        fields = '__all__'


class freelancer_proposals(serializers.ModelSerializer):
    class Meta:
        model = freelancer_proposals
        fields = '__all__'


class client_contract_details(serializers.ModelSerializer):
    class Meta:
        model = client_contract_details
        fields = '__all__'


class freelancer_payment_details(serializers.ModelSerializer):
    class Meta:
        model = freelancer_payment_details
        fields = '__all__'
class client_fee_record(serializers.ModelSerializer):
    class Meta:
        model = client_fee_record
        fields = '__all__'
class client_feedback_form(serializers.ModelSerializer):
    class Meta:
        model = client_feedback_form
        fields = '__all__'

class freelancer_feedback_form(serializers.ModelSerializer):
    class Meta:
        model = freelancer_feedback_form
        fields = '__all__'