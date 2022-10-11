import imp
from rest_framework.serializers import ModelSerializer
from rest_framework import serializers
from .models import Coupon, Employee, Enquiry, Package, Place, Tour, Vehicle

class VehicleSerializer(ModelSerializer):

        class Meta:
                model = Vehicle
                fields='__all__'



class PlaceSerializer(ModelSerializer):

    class Meta:
        model = Place
        fields = '__all__'


class CouponSerializer(ModelSerializer):

    class Meta:
        model = Coupon
        fields = '__all__'

class EmployeeSerializer(ModelSerializer):

    class Meta:
        model = Employee
        fields = '__all__'

class TourDetailSerializer(ModelSerializer):

    places = PlaceSerializer(many=True)
    coupons = CouponSerializer(many=True)
    guides = EmployeeSerializer(many=True)

    class Meta:
        model = Tour
        fields = '__all__'

class TourSerializer(ModelSerializer):

    class Meta:
        model = Tour
        fields = '__all__'

class EnquirySerializer(ModelSerializer):

    class Meta:
        model = Enquiry
        fields = '__all__'

class PackageSerializer(ModelSerializer):

    class Meta:
        model = Package
        fields = '__all__'

class PackageDetailSerializer(ModelSerializer):
    tours = TourDetailSerializer(many=True)

    class Meta:
        model = Package
        fields = '__all__'



# class PlaceSerializer(ModelSerializer):

#     class Meta:
#         model = Place
#         fields = ['id', 'place_name']


