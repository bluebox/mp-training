from django.http import HttpResponse
from rest_framework.renderers import JSONRenderer

from facilities.models import BookingData, SlotsBookedForBookingId, Slot, SportsInFacility, Equipment, Sport, \
    FacilityDetail, EquipmentsRentedForBookingId, UserToken, User
from facilities.serilizers import BookingFormSerializer, EquipmentSerializer, SportsInFacilitySerializer, \
    InvoiceSerializer, FacilityDetailSerializer, SportsSerializer, UserBookingsSerializer, UserSerializer, \
    CreateUserSerializer, SlotsSerializer


def booking_form(request):
    serializer = BookingFormSerializer(data=request.data)
    if serializer.is_valid():
        serializer.save()

    else:
        error_data = JSONRenderer().render(serializer.errors)
        return HttpResponse(error_data, content_type='application/json')


def slots_booked(request):
    list_slot_objects = []
    slots = request.data['slots_id']
    booking_id_obj = BookingData.objects.filter(user_id=request.data['user_id']).order_by('-booking_id')[0:1]
    for slot in slots:
        slot_obj = Slot.objects.get(slot_id=slot)
        slots_booked_object = SlotsBookedForBookingId(
            booking_id=booking_id_obj[0],
            slot_id=slot_obj

        )
        list_slot_objects.append(slots_booked_object)
    cost_per_slot = SportsInFacility.objects.get(facility_sport_id=request.data['facility_sport_id']).cost_per_slot
    cost_for_slots = len(list_slot_objects) * cost_per_slot
    return list_slot_objects, cost_for_slots


def get_slots_booked(fs_id, date):
    fs_obj = SportsInFacility.objects.get(facility_sport_id=fs_id)
    booking_ids = BookingData.objects.filter(facility_sport_id=fs_obj, date=date)
    slots_list = []

    for booking_obj in booking_ids:
        slots_query = SlotsBookedForBookingId.objects.filter(booking_id=booking_obj)

        for slot_obj in slots_query:
            slot_id = slot_obj.slot_id
            slots_list.append(slot_id)
    return slots_list


def get_equipments(sid):
    equipments = Equipment.objects.filter(sport=sid)
    serializer = EquipmentSerializer(equipments, many=True)
    json_data = JSONRenderer().render(data=serializer.data)
    return HttpResponse(json_data, content_type='application/json')


def get_facility_sport_id(fid, sid):
    fs_id = SportsInFacility.objects.filter(facility=fid, sport=sid)
    serializer = SportsInFacilitySerializer(fs_id[0])
    json_data = JSONRenderer().render(data=serializer.data)
    return HttpResponse(json_data, content_type='application/json')


def post_equipments_booked(request):
    list_equip_objects = []
    equipments_booked = request.data['equipments_booked']
    equipments_quantity = request.data['equipments_quantity']
    booking_id_obj = BookingData.objects.filter(user_id=request.data['user_id']).order_by('-booking_id')[0:1]
    total_cost_per_slot = 0
    for i in range(len(equipments_booked)):
        equipments_obj = Equipment.objects.get(equip_name=equipments_booked[i])
        equip_booked_object = EquipmentsRentedForBookingId(
            booking_id=booking_id_obj[0],
            equip_id=equipments_obj,
            quantity=equipments_quantity[i]

        )
        total_cost_per_slot += equipments_obj.rent_per_slot * equipments_quantity[i]
        list_equip_objects.append(equip_booked_object)
    total_cost = total_cost_per_slot * len(request.data['slots_id'])
    return list_equip_objects, total_cost


def post_invoices(request, total_cost):
    booking_id_obj = BookingData.objects.filter(user_id=request.data['user_id']).order_by('-booking_id')[0:1]
    obj = {
        'booking_id': booking_id_obj[0].booking_id,
        'total_cost': total_cost
    }
    serializer = InvoiceSerializer(data=obj)
    if serializer.is_valid():
        serializer.save()


def search_facilities(request):
    facilities = FacilityDetail.objects.filter(facility_name__icontains=request.GET.get('q'))[0:8]
    serializer = FacilityDetailSerializer(facilities, many=True)
    json_data = JSONRenderer().render(data=serializer.data)
    return HttpResponse(json_data, content_type='application/json')


def get_sports():
    sports = Sport.objects.all()
    serializer = SportsSerializer(sports, many=True)
    json_data = JSONRenderer().render(data=serializer.data)
    return HttpResponse(json_data, content_type='application/json')


def facilities_containing_sport(sid):
    sports = Sport.objects.filter(sport_id=sid)
    serializer = SportsSerializer(sports, many=True)
    json_data = JSONRenderer().render(data=serializer.data)
    return HttpResponse(json_data, content_type='application/json')


def check_access_token(token):
    refresh_token = UserToken.objects.get(token=token)
    user_id = refresh_token.user_id
    print(user_id)
    return user_id


def get_user_booking(user_id):
    booking_details = BookingData.objects.filter(user_id=user_id)
    serializer = UserBookingsSerializer(booking_details, many=True)
    json_data = JSONRenderer().render(data=serializer.data)
    return HttpResponse(json_data, content_type='application/json')


def cancel_booking(bid):
    try:
        booking_details = BookingData.objects.get(booking_id=bid)
        booking_details.delete()
        msg = "cancelled booking successfully"
    except:
        msg = "no booking found"
    return msg


def give_feedback(request, bid):
    try:
        booking_details = BookingData.objects.get(booking_id=bid)
        booking_details.reviews = request.data['review']
        booking_details.ratings = request.data['rating']
        booking_details.save()
        msg = "booking updated successfully"
    except:
        msg = "no booking found"
    return msg


def get_user_details(uid):
    user_obj = User.objects.get(user_id=uid)
    serializer = UserSerializer(user_obj)
    json_data = JSONRenderer().render(data=serializer.data)
    return HttpResponse(json_data, content_type='application/json')


def edit_user_details(uid, request):
    user_obj = User.objects.get(user_id=uid)
    user_obj.user_name = request.data['user_name']
    user_obj.user_email = request.data['user_email']
    user_obj.save()



def create_user(request):
    json_data = request.data
    serializer = CreateUserSerializer(data=json_data)

    if serializer.is_valid():
        serializer.save()
        msg = 'user account created successfully'
        json_data = JSONRenderer().render(msg)
        return HttpResponse(json_data, content_type='application/json')
    else:
        json_data = JSONRenderer().render(serializer.errors)
        return HttpResponse(json_data, content_type='application/json', status=404)


def add_sports_facility(request):
    facility_id = request.data['facility_id']
    sportArray = request.data['sportObj']
    # cost_for_slot_list = request.data['cost_for_slot_list']
    sport_obj_list = []
    facility_obj = FacilityDetail.objects.get(facility_id=facility_id)

    for sport in sportArray:
        sport_obj = Sport.objects.get(sport_id=sport['sport_id'])
        sports_in_facility_obj = SportsInFacility(
            facility=facility_obj,
            sport=sport_obj,
            cost_per_slot=sport['cost_per_slot']
        )
        sport_obj_list.append(sports_in_facility_obj)

    SportsInFacility.objects.bulk_create(sport_obj_list)


def get_all_slots():
    slots = Slot.objects.all()
    serializer = SlotsSerializer(slots, many=True)
    json_data = JSONRenderer().render(data=serializer.data)
    return HttpResponse(json_data, content_type='application/json')
