from rest_framework import serializers

from .models import Issue
from Book.serializers import BookSerializer
from Member.serializers import MemberSerializer


class IssueSerializer(serializers.ModelSerializer):
    # book=BookSerializer()
    # member=MemberSerializer()
    class Meta:
        model=Issue
        fields='__all__'
