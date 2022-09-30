from rest_framework import serializers
from .models import FacilityDetail, SportsInFacility, Sport, SlotsInSportFacility, Slot,User


class FacilityDetailSerializer(serializers.ModelSerializer):

    class Meta:
        model = FacilityDetail
        # fields = ['facility_name', 'facility_location', 'facility_email']
        # fields = '__all__'
        exclude = ['facility_password', 'facility_phone']


class CreateFacilitySerializer(serializers.ModelSerializer):

    class Meta:
        model = FacilityDetail
        # fields = '__all__'
        exclude = ['facility_id']


class SportsInFacilitySerializer(serializers.ModelSerializer):

    class Meta:
        model = SportsInFacility
        fields = '__all__'


class CreateSportsInFacilitySerializer(serializers.ModelSerializer):

    class Meta:
        model = SportsInFacility
        exclude = ['facility_sport_id']


class SportsSerializer(serializers.ModelSerializer):

    class Meta:
        model = Sport
        fields = '__all__'


class SlotsDetailsSerializer(serializers.ModelSerializer):

    class Meta:
        model = SlotsInSportFacility
        fields = '__all__'


class SlotsSerializer(serializers.ModelSerializer):

    class Meta:
        model = Slot
        fields = '__all__'


class UserDetailsSerializer(serializers.ModelSerializer):

    class Meta:
        model = User
        fields = '__all__'

