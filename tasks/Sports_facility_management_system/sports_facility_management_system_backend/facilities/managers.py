import datetime
import random
import string

from django.db.models import Avg
from rest_framework import exceptions

from facilities.jwtauthentication import create_access_token, create_refresh_token
from facilities.models import BookingData, SlotsBookedForBookingId, Slot, SportsInFacility, Equipment, Sport, \
    FacilityDetail, EquipmentsRentedForBookingId, UserToken, User, Invoice, SlotsInSportFacility
from facilities.pagnation import listing
from facilities.serilizers import BookingFormSerializer, EquipmentSerializer, SportsInFacilitySerializer, \
    InvoiceSerializer, FacilityDetailSerializer, SportsSerializer, UserBookingsSerializer, UserSerializer, \
    CreateUserSerializer, SlotsSerializer, GetInvoiceSerializer, EquipmentBookedSerializer, CreateFacilitySerializer, \
    SlotsDetailSerializer, BookedSlotsSerializer


class Facilities:
    @staticmethod
    def get_facility(request):

        fid = request.GET.get('fid')

        facility = FacilityDetail.objects.get(facility_id=fid)
        serializer = FacilityDetailSerializer(facility)

        return serializer.data

    @staticmethod
    def post_facility(request):

        serializer = CreateFacilitySerializer(data=request.data)

        if serializer.is_valid():
            serializer.save()
            facility = FacilityDetail.objects.get(facility_phone=request.data['facility_phone'])
            serializer = FacilityDetailSerializer(facility)
            res = {'msg': 'successfully added facility', 'facility_details': serializer.data}

            return res

        raise exceptions.APIException(serializer.errors)

    @staticmethod
    def delete_facility(request):

        facility = FacilityDetail.objects.get(facility_id=request.GET.get('fid'))
        facility.delete()

        msg = "successfully deleted"
        return msg

    @staticmethod
    def view_sports_in_facility(request):

        sports = Sport.objects.filter(facility=request.GET.get('fid'))
        serializer = SportsSerializer(sports, many=True)

        return serializer.data

    @staticmethod
    def add_sports_facility(request):

        facility_id = request.data['facility_id']

        sport_array = request.data['sportObj']
        sport_obj_list = []
        facility_obj = FacilityDetail.objects.get(facility_id=facility_id)

        for sport in sport_array:
            sport_obj = Sport.objects.get(sport_id=sport['sport_id'])
            sports_in_facility_obj = SportsInFacility(
                facility=facility_obj,
                sport=sport_obj,
                cost_per_slot=sport['cost_per_slot']
            )
            sport_obj_list.append(sports_in_facility_obj)

        SportsInFacility.objects.bulk_create(sport_obj_list)

        msg: 'successfully added facility'

        return msg

    @staticmethod
    def delete_sport_in_facility(request):

        obj = SportsInFacility.objects.get(facility=request.query_params.get('fid'),
                                           sport=request.query_params.get('sid'))
        obj.delete()

        msg = 'sport successfully deleted'
        return msg

    @staticmethod
    def get_facility_sport_id(request):

        fs_id = SportsInFacility.objects.filter(facility=request.query_params.get('fid'),
                                                sport=request.query_params.get('sid'))
        serializer = SportsInFacilitySerializer(fs_id[0])

        return serializer.data

    @staticmethod
    def get_equipments(request):

        equipments = Equipment.objects.filter(sport=request.GET.get('sid'))
        serializer = EquipmentSerializer(equipments, many=True)

        return serializer.data

    @staticmethod
    def get_bookings_in_facility(request):

        booking_details = BookingData.objects.filter(facility_sport_id__facility__facility_id=request.GET.get('fid'))
        serializer = UserBookingsSerializer(booking_details, many=True)

        return serializer.data

    @staticmethod
    def get_all_sports():

        sports = Sport.objects.all()
        serializer = SportsSerializer(sports, many=True)

        return serializer.data

    @staticmethod
    def search_filter_pagination(request):

        sid = request.query_params.get('sid')
        q = request.GET.get('q')
        if len(sid) == 0:
            sid = [1, 2, 3, 4, 5]
        else:
            sid = sid.split(',')

        facilities_obj = FacilityDetail.objects.prefetch_related().filter(sport__in=sid,
                                                                          facility_name__icontains=q).distinct()
        facilities_list, total_pages, current_page = listing(request, facilities_obj)
        serializer = FacilityDetailSerializer(facilities_list, many=True)

        return serializer.data, total_pages, current_page

    @staticmethod
    def search_filter_load_more(request):

        sid = request.query_params.get('sid')
        x = request.query_params.get('x')  # no of results to fetch
        q = request.GET.get('q')
        if len(sid) == 0:
            sid = [1, 2, 3, 4, 5]
        else:
            sid = sid.split(',')

        facilities_obj = FacilityDetail.objects.prefetch_related().filter(sport__in=sid,
                                                                          facility_name__icontains=q).distinct()
        total_results = len(facilities_obj)
        facilities_obj = facilities_obj[:int(x)]
        serializer = FacilityDetailSerializer(facilities_obj, many=True)

        return serializer.data, total_results


class Slots:

    @staticmethod
    def get_all_slots_available(fid, sid):
        slots_sport_facility = Slot.objects.prefetch_related().filter(
                                            slotsinsportfacility__facility_sport_id__facility=fid,
                                            slotsinsportfacility__facility_sport_id__sport=sid)
        serializer = SlotsSerializer(slots_sport_facility, many=True)

        return serializer.data

    @staticmethod
    def get_booked_slots(request):
        fsid = request.GET.get('fsid')
        date = request.GET.get('date')

        slots = Slot.objects.prefetch_related().filter(bookingdata__facility_sport_id=fsid,
                                                       bookingdata__date=date,
                                                       bookingdata__isCancelled=False)
        serializer = SlotsSerializer(slots, many=True)
        return serializer.data


class Bookings:

    @staticmethod
    def get_particular_booking_details(request):
        bid = request.GET.get('bid')
        booking_obj = BookingData.objects.get(booking_id=bid)
        serializer = UserBookingsSerializer(booking_obj)
        invoice_obj = Invoice.objects.filter(booking_id=bid)
        serializer2 = GetInvoiceSerializer(invoice_obj[0])

        obj = {
            "booking": serializer.data,
            "invoice": serializer2.data
        }
        return obj

    @staticmethod
    def get_booked_equipment(request):

        equip_obj = EquipmentsRentedForBookingId.objects.filter(booking_id=request.GET.get('bid'))
        serializer = EquipmentBookedSerializer(equip_obj, many=True)

        return serializer.data

    @staticmethod
    def booking_form(request):

        serializer = BookingFormSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            booking_id_obj = BookingData.objects.filter(user_id=request.data['user_id']).order_by('-booking_id')[0:1]
            booking_id = booking_id_obj[0].booking_id
            cost_for_slots = Bookings.book_slots(request, booking_id)
            cost_for_equip = Bookings.book_equipments(request, booking_id)
            total_cost = cost_for_slots + cost_for_equip
            Bookings.post_invoices(total_cost, booking_id)

            return booking_id
        raise exceptions.APIException(serializer.errors)

    @staticmethod
    def book_slots(request, booking_id):

        list_slot_objects = []
        slots = request.data['slots_id']
        booking_id_obj = BookingData.objects.get(booking_id=booking_id)

        for slot in slots:
            slot_obj = Slot.objects.get(slot_id=slot)
            slots_booked_object = SlotsBookedForBookingId(
                booking_id=booking_id_obj,
                slot_id=slot_obj
            )
            list_slot_objects.append(slots_booked_object)

        cost_per_slot = SportsInFacility.objects.get(facility_sport_id=request.data['facility_sport_id']).cost_per_slot
        cost_for_slots = len(list_slot_objects) * cost_per_slot
        SlotsBookedForBookingId.objects.bulk_create(list_slot_objects)

        return cost_for_slots

    @staticmethod
    def book_equipments(request, booking_id):

        list_equip_objects = []
        equipments_booked = request.data['equipments_booked']
        equipments_quantity = request.data['equipments_quantity']

        booking_id_obj = BookingData.objects.get(booking_id=booking_id)

        total_cost_per_slot = 0
        for i in range(len(equipments_booked)):
            equipments_obj = Equipment.objects.get(equip_name=equipments_booked[i])
            equip_booked_object = EquipmentsRentedForBookingId(
                booking_id=booking_id_obj,
                equip_id=equipments_obj,
                quantity=equipments_quantity[i]

            )
            total_cost_per_slot += equipments_obj.rent_per_slot * equipments_quantity[i]
            list_equip_objects.append(equip_booked_object)
        total_cost_equip = total_cost_per_slot * len(request.data['slots_id'])
        EquipmentsRentedForBookingId.objects.bulk_create(list_equip_objects)
        return total_cost_equip

    @staticmethod
    def post_invoices(total_cost, booking_id):

        obj = {
            'booking_id': booking_id,
            'total_cost': total_cost
        }
        serializer = InvoiceSerializer(data=obj)
        if serializer.is_valid():
            serializer.save()

    @staticmethod
    def cancel_booking(bid):

        booking_details = BookingData.objects.get(booking_id=bid)
        booking_details.isCancelled = True
        booking_details.save()
        msg = "cancelled booking successfully"

        return msg

    @staticmethod
    def give_feedback(request, bid):

        booking_details = BookingData.objects.get(booking_id=bid)
        booking_details.reviews = request.data['review']
        booking_details.ratings = request.data['rating']
        booking_details.save()
        msg = "booking updated successfully"

        return msg


class UserManager:

    @staticmethod
    def create_user(request):

        serializer = CreateUserSerializer(data=request.data)

        if serializer.is_valid():
            serializer.save()
            msg = 'user account created successfully'

            return msg

        raise exceptions.APIException(serializer.errors)

    @staticmethod
    def get_user_details(request):

        user_obj = User.objects.get(user_id=request.GET.get('uid'))
        serializer = UserSerializer(user_obj)

        return serializer.data

    @staticmethod
    def user_login(request):

        user_phone = request.data['user_phone']
        user_password = request.data['user_password']

        if User.objects.filter(user_phone=user_phone).exists():
            user = User.objects.get(user_phone=user_phone)
            if user.user_password != user_password:
                raise exceptions.APIException("incorrect user_password ")
        else:
            raise exceptions.APIException("phone number not registered,check ")

        access_token = create_access_token(user.user_id)
        refresh_token = create_refresh_token(user.user_id)

        UserToken.objects.create(
            user_id=user.user_id,
            token=refresh_token,
            expired_at=datetime.datetime.utcnow() + datetime.timedelta(days=5)
        )

        return access_token, refresh_token, user.is_admin

    @staticmethod
    def check_refresh_token(token):

        refresh_token = UserToken.objects.get(token=token)
        user_id = refresh_token.user_id
        user_obj = User.objects.get(user_id=user_id)

        return user_id, user_obj.is_admin

    @staticmethod
    def edit_user_details(request):

        user_obj = User.objects.get(user_id=request.GET.get('uid'))

        user_obj.user_name = request.data['user_name']
        user_obj.user_email = request.data['user_email']
        user_obj.save()

        msg = "details updated successfully"

        return msg

    @staticmethod
    def get_user_booking(user_id):

        booking_details = BookingData.objects.filter(user_id=user_id)
        serializer = UserBookingsSerializer(booking_details, many=True)

        return serializer.data


def get_ratings(fid):

    ratings = BookingData.objects.filter(ratings__gt=0, facility_sport_id__facility__facility_id=fid).\
                                                                            aggregate(Avg('ratings'))

    return ratings


def add_data():
    # to add random data
    for i in range(100):
        letters = string.ascii_lowercase
        numbers = string.digits
        result_str = ''.join(random.choice(letters) for i in range(4))
        result_number = ''.join(random.choice(numbers) for i in range(10))
        FacilityDetail.objects.create(facility_name=result_str + "sports", facility_location='madhapur',
                                      facility_phone=result_number, facility_email=result_str + '@gmail.com',
                                      facility_password='123456')
        facility = FacilityDetail.objects.get(facility_phone=result_number)
        sport = Sport.objects.get(sport_id=1)

        SportsInFacility.objects.create(facility=facility, sport=sport, cost_per_slot=1100)
