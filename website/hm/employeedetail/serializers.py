from rest_framework import serializers
from .models import *


class designation_serializers(serializers.ModelSerializer):
    class Meta:
        model  = designation
        fields = (
            'dept_id',
            'designation_id',
            'designation_name'
        )