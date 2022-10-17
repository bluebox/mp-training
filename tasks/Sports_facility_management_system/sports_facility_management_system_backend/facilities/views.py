import datetime

from django.http import HttpResponse
from rest_framework import exceptions
from rest_framework.renderers import JSONRenderer
from rest_framework.response import Response
from rest_framework.views import APIView

from .jwtauthentication import create_access_token, create_refresh_token
from .managers import booking_form, slots_booked, get_slots_booked, get_equipments, get_facility_sport_id, \
    post_equipments_booked, post_invoices, search_facilities, get_sports, facilities_containing_sport, \
    check_access_token, get_user_booking, cancel_booking, give_feedback, get_user_details, edit_user_details, \
    create_user, add_sports_facility, get_all_slots, get_particular_booking_details
from .models import FacilityDetail, Sport, SportsInFacility, SlotsInSportFacility, SlotsBookedForBookingId, \
    EquipmentsRentedForBookingId, User, UserToken
from .serilizers import FacilityDetailSerializer, SportsSerializer, SlotsSerializer, CreateFacilitySerializer, \
    CreateSportsInFacilitySerializer


class FacilitiesDetailsView(APIView):

    def get(self, request):
        facilities = FacilityDetail.objects.all()
        serializer = FacilityDetailSerializer(facilities, many=True)
        json_data = JSONRenderer().render(data=serializer.data)
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

    def get(self, request):
        fid = request.GET.get('fid')
        facility = FacilityDetail.objects.get(facility_id=fid)
        serializer = FacilityDetailSerializer(facility)
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

    def delete(self, request):
        try:
            fid = request.GET.get('fid')
            facility = FacilityDetail.objects.get(facility_id=fid)
            facility.delete()
            msg = "successfully deleted"
            json_data = JSONRenderer().render(msg)
            return HttpResponse(json_data, content_type='application/json')
        except Exception as e:
            return Response(str(e), status=500)


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

    def get(self, request):
        bid = request.GET.get('bid')
        try:
            booking_details = get_particular_booking_details(bid)
            return booking_details
        except Exception as e:
            return Response(str(e), status=500)


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

class UserLogin(APIView):
    def post(self, request):
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
        response = Response()
        response.data = {
            "access_token": access_token,
            "refresh_token": refresh_token,
        }
        return response


class SearchFacilities(APIView):

    def get(self, request):

        try:
            result = search_facilities(request)
            return result
        except:
            msg = "not found"
            json_data = JSONRenderer().render(msg)
            return HttpResponse(json_data, content_type='application/json')


class GetSports(APIView):

    def get(self, request):

        try:
            sports = get_sports()
            return sports
        except:
            msg = "not found"
            json_data = JSONRenderer().render(msg)
            return HttpResponse(json_data, content_type='application/json')


class GetFacilitiesInSports(APIView):
    def get(self, request, sid):

        try:
            facilities = facilities_containing_sport(sid)
            return facilities
        except:
            msg = "not found"
            json_data = JSONRenderer().render(msg)
            return HttpResponse(json_data, content_type='application/json')


class CheckAccessToken(APIView):

    def get(self, request):
        refresh_token = request.GET.get('refresh_token')
        try:
            user_id = check_access_token(refresh_token)
            msg = user_id
        except:
            msg = "access token does not exist"
        json_data = JSONRenderer().render(msg)
        return HttpResponse(json_data, content_type='application/json')


class GetUserBookings(APIView):

    def get(self, request, uid):
        return get_user_booking(uid)


class CancelBooking(APIView):

    def post(self, request, bid):
        try:
            msg = cancel_booking(bid)

        except:
            msg = "error occurred try again"

        json_data = JSONRenderer().render(msg)
        return HttpResponse(json_data, content_type='application/json')


class UpdateFeedback(APIView):

    def post(self, request, bid):
        try:
            msg = give_feedback(request, bid)

        except:
            msg = "error occurred try again"

        json_data = JSONRenderer().render(msg)
        return HttpResponse(json_data, content_type='application/json')


class GetUserDetails(APIView):

    def get(self, request):
        try:
            uid = request.GET.get('uid')
            user = get_user_details(uid)
            return user
        except:
            msg = "error occurred try again"

            json_data = JSONRenderer().render(msg)
            return HttpResponse(json_data, content_type='application/json')

    def post(self, request):
        json_data = create_user(request)
        return json_data

    def put(self, request):
        try:
            uid = request.GET.get('uid')
            edit_user_details(uid, request)
            msg = "details updated successfully"
            json_data = JSONRenderer().render(msg)
            return HttpResponse(json_data, content_type='application/json')
        except Exception as e:
            return Response(str(e), 500)


class AddSportsToFacility(APIView):

    def post(self, request):

        try:
            add_sports_facility(request)
            msg = "details updated successfully"
            json_data = JSONRenderer().render(msg)
            return HttpResponse(json_data, content_type='application/json')
        except Exception as e:
            return Response(str(e), status=500)


class GetAllSlots(APIView):

    def get(self, request):
        try:
            return get_all_slots()
        except Exception as e:
            return Response(str(e), status=500)
