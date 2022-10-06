import io

from django.http import HttpResponse
from rest_framework.parsers import JSONParser
from rest_framework.renderers import JSONRenderer
from rest_framework.response import Response

from facilities.models import BookingData, SlotsBookedForBookingId, Slot, SportsInFacility, Equipment, Sport, \
    FacilityDetail, EquipmentsRentedForBookingId
from facilities.serilizers import BookingFormSerializer, EquipmentSerializer, SportsInFacilitySerializer, \
    InvoiceSerializer


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


def get_booking_details(request):
    booking_id = BookingData.objects.get(booking_id=request.data['booking_id'])

    no_slots_booked = SlotsBookedForBookingId.filter(booking_id=booking_id)
    equipments_rented = EquipmentsRentedForBookingId.filter(booking_id=booking_id)
    cost_per_slot = SportsInFacility.booking_id.cost_per_slot * len(no_slots_booked)
