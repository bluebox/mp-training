from rest_framework import serializers
from .models import Theatre,Movie,Users,Hall,Seating,Booking,Cart,PROMOCODE,Payment,Bill,DatewiseHall





class UserSerializer(serializers.ModelSerializer):
    class Meta:
        model = Users
        fields = ['User_id', 'User_name','User_email','User_phone','password','Role']
        # def create(self,validated_data):
        #     password=validated_data.pop('password',None)
        #     instance=self.Meta.model(**validated_data)
        #     if password is not None:
        #         instance.set_password(password)
        #     instance.save()
        #     return instance


class MovieSerializer(serializers.ModelSerializer):
    class Meta:
        model = Movie
        fields = '__all__'


class TheatreSerializer(serializers.ModelSerializer):
    class Meta:
        model = Theatre
        fields = '__all__'

class HallSerializer(serializers.ModelSerializer):
    class Meta:
        model = Hall
        fields = '__all__'


class SeatingSerializer(serializers.ModelSerializer):
    class Meta:
        model = Seating
        fields = '__all__'


# class Selected_seatsSerializer(serializers.ModelSerializer):
#     class Meta:
#         model = Selected_seats
#         fields = '__all__'


class PromocodeSerializer(serializers.ModelSerializer):
    class Meta:
        model = PROMOCODE
        fields = '__all__'


class BookingSerializer(serializers.ModelSerializer):
    class Meta:
        model = Booking
        fields = '__all__'

# class SnacksSerializer(serializers.ModelSerializer):
#     class Meta:
#         model = Snacks
#         fields = '__all__'


class CartSerializer(serializers.ModelSerializer):
    class Meta:
        model = Cart
        fields = '__all__'


class BillSerializer(serializers.ModelSerializer):
    class Meta:
        model = Bill
        fields = '__all__'

class PaymentSerializer(serializers.ModelSerializer):
    class Meta:
        model = Payment
        fields = '__all__'

class DatewiseHallSerializer(serializers.ModelSerializer):
    class Meta:
        model = DatewiseHall
        fields = '__all__'

