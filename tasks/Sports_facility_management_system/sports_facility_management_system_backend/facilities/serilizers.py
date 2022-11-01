from rest_framework import serializers
from .models import FacilityDetail, SportsInFacility, Sport, SlotsInSportFacility, Slot, User, BookingData, \
    Equipment, Invoice, EquipmentsRentedForBookingId, SlotsBookedForBookingId


class FacilityDetailSerializer(serializers.ModelSerializer):
    class Meta:
        model = FacilityDetail
        exclude = ['facility_password', 'facility_phone']


class CreateFacilitySerializer(serializers.ModelSerializer):
    class Meta:
        model = FacilityDetail
        exclude = ['facility_id']


class SportsSerializer(serializers.ModelSerializer):
    class Meta:
        model = Sport
        fields = '__all__'


class SportsInFacilitySerializer(serializers.ModelSerializer):
    facility = FacilityDetailSerializer()
    sport = SportsSerializer()

    class Meta:
        model = SportsInFacility
        fields = '__all__'


class CreateSportsInFacilitySerializer(serializers.ModelSerializer):
    class Meta:
        model = SportsInFacility
        exclude = ['facility_sport_id']


class SlotsDetailsSerializer(serializers.ModelSerializer):
    class Meta:
        model = SlotsInSportFacility
        fields = '__all__'


class SlotsSerializer(serializers.ModelSerializer):
    class Meta:
        model = Slot
        fields = '__all__'


class BookedSlotsSerializer(serializers.ModelSerializer):

    slot_id = SlotsSerializer()

    class Meta:
        model = SlotsBookedForBookingId
        fields = ['slot_id']


class UserDetailsSerializer(serializers.ModelSerializer):
    class Meta:
        model = User
        fields = '__all__'


class SportsSerializer(serializers.ModelSerializer):
    facility = FacilityDetailSerializer(many=True)

    class Meta:
        model = Sport
        fields = '__all__'


class BookingFormSerializer(serializers.ModelSerializer):
    class Meta:
        model = BookingData
        fields = ['user_id', 'facility_sport_id', 'date']


class GetBookingIDSerializer(serializers.ModelSerializer):
    class Meta:
        model = BookingData
        fields = ['booking_id']


class EquipmentSerializer(serializers.ModelSerializer):
    class Meta:
        model = Equipment
        fields = '__all__'


class EquipmentBookedSerializer(serializers.ModelSerializer):
    equip_id = EquipmentSerializer()

    class Meta:
        model = EquipmentsRentedForBookingId
        fields = '__all__'


class InvoiceSerializer(serializers.ModelSerializer):
    class Meta:
        model = Invoice
        fields = ['total_cost', 'booking_id']


class GetInvoiceSerializer(serializers.ModelSerializer):
    class Meta:
        model = Invoice
        fields = '__all__'


class UserBookingsSerializer(serializers.ModelSerializer):
    slots = SlotsSerializer(many=True)
    equipments_booked = EquipmentSerializer(many=True)
    facility_sport_id = SportsInFacilitySerializer()

    class Meta:
        model = BookingData
        fields = '__all__'


class UserSerializer(serializers.ModelSerializer):
    class Meta:
        model = User
        exclude = ['user_id', 'user_password']


class CreateUserSerializer(serializers.ModelSerializer):
    class Meta:
        model = User
        exclude = ['user_id']


class AddSportsToFacility(serializers.ModelSerializer):
    class Meta:
        model = SportsInFacility
        exclude = ['facility_sport_id']


class SlotsDetailSerializer(serializers.ModelSerializer):
    slot_id = SlotsSerializer()

    class Meta:
        model = SlotsInSportFacility
        fields = ['slot_id']


class SearchSerializer(serializers.ModelSerializer):
    facility = FacilityDetailSerializer()

    class Meta:
        model = SportsInFacility
        fields = '__all__'
