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

