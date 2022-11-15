from rest_framework import serializers

from .models import Vehicle


class vehicleSerializer(serializers.ModelSerializer):

    class Meta:
        model = Vehicle
        fields = '__all__'