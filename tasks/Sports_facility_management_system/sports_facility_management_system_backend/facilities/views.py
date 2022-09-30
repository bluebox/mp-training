from django.http import HttpResponse
from rest_framework.renderers import JSONRenderer
from rest_framework.response import Response
from rest_framework.views import APIView

from .managers import booking_form
from .models import FacilityDetail, Sport, SportsInFacility, Slot, SlotsInSportFacility
from .serilizers import FacilityDetailSerializer, SportsInFacilitySerializer, SportsSerializer, SlotsDetailsSerializer, \
    SlotsSerializer, CreateFacilitySerializer, CreateSportsInFacilitySerializer
import json
import io


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
        slots_sport_facility = SlotsInSportFacility.objects.filter(facility_sport=fs_object.facility_sport_id)
        for slot in slots_sport_facility:
            slot_time.append(slot.slot)
        serializer = SlotsSerializer(slot_time, many=True)
        json_data = JSONRenderer().render(data=serializer.data)
        return HttpResponse(json_data, content_type='application/json')


class BookingFormView(APIView):

    def get(self, request):
        json_data = request.data
        try:
            booking_form(json_data, request)
        except:
            return Response({'msg': 'booking not done'})
