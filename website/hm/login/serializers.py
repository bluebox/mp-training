from rest_framework import serializers
from login.models import emp_login ,apply_candiate_login


class emp_loin_serializers(serializers.ModelSerializer):
    class Meta:
        model  = emp_login
        fields = ('eid',
                  'loginid',
                  'password')


class apply_candiate_login_serializers(serializers.ModelSerializer):
    class Meta:
        model  = apply_candiate_login
        fields = ('cid',
                  'loginid',
                  'password')