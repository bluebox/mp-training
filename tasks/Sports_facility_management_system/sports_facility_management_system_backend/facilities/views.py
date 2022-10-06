from django.http import HttpResponse
from django.views.generic import ListView
from rest_framework import generics
from rest_framework.renderers import JSONRenderer
from rest_framework.response import Response
from rest_framework.views import APIView
from .managers import booking_form, slots_booked, get_slots_booked, get_equipments, get_facility_sport_id, \
    post_equipments_booked, post_invoices
from .models import FacilityDetail, Sport, SportsInFacility, Slot, SlotsInSportFacility, SlotsBookedForBookingId, \
    BookingData, EquipmentsRentedForBookingId
from .serilizers import FacilityDetailSerializer, SportsInFacilitySerializer, SportsSerializer, SlotsDetailsSerializer, \
    SlotsSerializer, CreateFacilitySerializer, CreateSportsInFacilitySerializer, BookingFormSerializer
import json
import io
from itertools import islice


class FacilitiesDetailsView(APIView):

    def get(self, request):
        facilities = FacilityDetail.objects.all()
        serializer = FacilityDetailSerializer(facilities, many=True)
        json_data = JSONRenderer().render(data=serializer.data)
        return HttpResponse(json_data, content_type='application/json')

    @staticmethod
    def post(request):
        json_data = request.data
        serializer = CreateFacilitySerializer(data=json_data)

        if serializer.is_valid():
            serializer.save()
            facility = FacilityDetail.objects.get(facility_phone=request.data['facility_phone'])
            serializer = FacilityDetailSerializer(facility)
            msg = {'msg': 'successfully added a Sports to facility', 'facility_details': serializer.data}
            json_data = JSONRenderer().render(msg)
            return HttpResponse(json_data, content_type='application/json')
        else:
            json_data = JSONRenderer().render(serializer.errors)
            return HttpResponse(json_data, content_type='application/json')


class AddSportsToFacilityView(APIView):

    def post(self, request):
        json_data = request.data
        serializer = CreateSportsInFacilitySerializer(data=json_data)
        if serializer.is_valid():
            serializer.save()
            msg = {'msg': 'successfully added a Sports to facility'}
            json_data = JSONRenderer().render(msg)
            return HttpResponse(json_data, content_type='application/json')
        else:
            json_data = JSONRenderer().render(serializer.errors)
            return HttpResponse(json_data, content_type='application/json')


class SportsInFacilityView(APIView):

    def get(self, request, fid):
        sports = Sport.objects.filter(facility=fid)

        serializer = SportsSerializer(sports, many=True)
        json_data = JSONRenderer().render(data=serializer.data)
        return HttpResponse(json_data, content_type='application/json')


class FacilityView(APIView):

    def get(self, request, fid):
        facility = FacilityDetail.objects.get(facility_id=fid)
        serializer = FacilityDetailSerializer(facility)
        json_data = JSONRenderer().render(data=serializer.data)
        return HttpResponse(json_data, content_type='application/json')


class SlotView(APIView):

    def get(self, request, fid, sid):
        slot_time = []
        fs_object = SportsInFacility.objects.get(facility=fid, sport=sid)
        slots_sport_facility = SlotsInSportFacility.objects.filter(facility_sport_id=fs_object.facility_sport_id)
        for slot in slots_sport_facility:
            slot_time.append(slot.slot_id)
        serializer = SlotsSerializer(slot_time, many=True)
        json_data = JSONRenderer().render(data=serializer.data)
        return HttpResponse(json_data, content_type='application/json')


class BookingFormView(APIView):
    # will save the booking details and sends back generated booking id

    def post(self, request):

        try:
            booking_form(request)
            slots_booked_list, cost_for_slots = slots_booked(request)
            equipments_booked, cost_for_equipments = post_equipments_booked(request)
            # total_billings = cost_for_slots + cost_for_equipments
            try:
                total_billings = cost_for_slots + cost_for_equipments
                SlotsBookedForBookingId.objects.bulk_create(slots_booked_list)
                EquipmentsRentedForBookingId.objects.bulk_create(equipments_booked)
                post_invoices(request, total_billings)
                msg = "hello"
            except:
                msg = "bye"

            json_data = JSONRenderer().render(msg)
            return HttpResponse(json_data, content_type='application/json')
        except:
            msg = {'msg': 'exception occurred while saving try to book again'}
            exception_data = JSONRenderer().render(msg)
            return HttpResponse(exception_data, content_type='application/json')


class GetBookedSlots(APIView):

    def get(self, request):
        fsid = request.GET.get('fsid')
        date = request.GET.get('date')
        try:
            slot_time = get_slots_booked(fsid, date)
            serializer = SlotsSerializer(slot_time, many=True)
            json_data = JSONRenderer().render(data=serializer.data)
            return HttpResponse(json_data, content_type='application/json')

        except:
            msg = "error occurred"
            json_data = JSONRenderer().render(msg)
            return HttpResponse(json_data, content_type='application/json')


class GetEquipments(APIView):

    def get(self, request):
        sid = request.GET.get('sid')
        try:
            equipment_data = get_equipments(sid)
            return equipment_data
        except:
            msg = "error occurred"
            json_data = JSONRenderer().render(msg)
            return HttpResponse(json_data, content_type='application/json', status=400)


class GEtFacilitySportId(APIView):

    def get(self, request):
        fid = request.GET.get('fid')
        sid = request.GET.get('sid')

        try:
            facility_sport_id = get_facility_sport_id(fid, sid)

            return facility_sport_id
        except:
            msg = "error occurred"
            json_data = JSONRenderer().render(msg)
            return HttpResponse(json_data, content_type='application/json', status=400)


# class Try(APIView):
#
#     def post(self, request):
#
#         try:
#             total_billings = 1200
#             post_invoices(request, total_billings)
#             msg = "success"
#         except:
#             msg = "error occurred"
#         json_data = JSONRenderer().render(msg)
#         return HttpResponse(json_data, content_type='application/json', status=400)
