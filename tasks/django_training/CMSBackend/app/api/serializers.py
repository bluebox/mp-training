from rest_framework import serializers
from app.models import FlatOwner

# class FlatOwnerSerializer(serializers.Serializer):
#     id=serializers.IntegerField(read_only=True)
#     name=serializers.CharField(max_length=50,allow_blank=False,required=True)
#     mobileNo=serializers.CharField(max_length=10)
#     email=serializers.EmailField()
#     username=serializers.CharField(max_length=30,required=True,allow_blank=False)
#     password=serializers.CharField(max_length=15,required=True,allow_blank=False)
#     address=serializers.CharField(max_length=100)
#
#     def create(self, validated_data):
#         return FlatOwner.objects.create(**validated_data)
#
#     def update(self, instance, validated_data):
#         instance.title = validated_data.get('title', instance.title)
#         instance.code = validated_data.get('code', instance.code)
#         instance.linenos = validated_data.get('linenos', instance.linenos)
#         instance.language = validated_data.get('language', instance.language)
#         instance.style = validated_data.get('style', instance.style)
#         instance.save()
#         return instance


class FlatOwnerSerializer(serializers.ModelSerializer):
    class Meta:
        model = FlatOwner
        fields = ['id', 'name', 'mobileNo', 'email', 'address', 'username']


