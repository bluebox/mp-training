from rest_framework import serializers

from Admin.models import TestModel


class TestSerializer(serializers.ModelSerializer):
    class Meta:
        model = TestModel
        fields = (
            'username',
            'password'
        )