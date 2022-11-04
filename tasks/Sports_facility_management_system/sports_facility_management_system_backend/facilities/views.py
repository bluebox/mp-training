from rest_framework.response import Response
from rest_framework.views import APIView

from .managers import Facilities, Slots, Bookings, UserManager, get_ratings


class FacilityView(APIView):

    def get(self, request):
        try:
            res = Facilities.get_facility(request)
            return Response(res, status=200)
        except Exception as e:

            return Response(str(e), status=500)

    def post(self, request):
        try:
            res = Facilities.post_facility(request)
            return Response(res, status=200)

        except Exception as e:
            return Response(str(e), status=500)

    def delete(self, request):
        try:
            res = Facilities.delete_facility(request)
            return Response(res, status=200)
        except Exception as e:
            return Response(str(e), status=500)


class SportsInFacilityView(APIView):

    def get(self, request):
        try:
            res = Facilities.view_sports_in_facility(request)
            return Response(res, status=200)
        except Exception as e:
            return Response(str(e), status=500)

    def post(self, request):
        try:
            res = Facilities.add_sports_facility(request)
            return Response(res, status=200)
        except Exception as e:
            return Response(str(e), status=500)

    def delete(self, request):
        try:
            res = Facilities.delete_sport_in_facility(request)
            return Response(res, status=200)
        except Exception as e:
            return Response(str(e), status=500)


class GEtFacilitySportId(APIView):

    def get(self, request):
        try:
            res = Facilities.get_facility_sport_id(request)
            return Response(res, status=200)
        except Exception as e:
            return Response(str(e), status=500)


class GetSlotsInSportFacility(APIView):

    def get(self, request, fid, sid):
        try:
            res = Slots.get_all_slots_available(fid, sid)
            return Response(res, status=200)
        except Exception as e:
            return Response(str(e), status=500)


class GetBookingsInFacility(APIView):

    def get(self, request):

        try:
            res = Facilities.get_bookings_in_facility(request)
            return Response(res, status=200)
        except Exception as e:
            return Response(str(e), status=500)


class GetBookedSlots(APIView):

    def get(self, request):
        try:
            res = Slots.get_booked_slots(request)
            return Response(res, status=200)
        except Exception as e:
            return Response(str(e), status=500)


class GetEquipmentsRelatedToSports(APIView):

    def get(self, request):

        try:
            res = Facilities.get_equipments(request)
            return Response(res, status=200)
        except Exception as e:
            return Response(str(e), status=500)


class BookingFormView(APIView):

    def post(self, request):
        try:
            res = Bookings.booking_form(request)
            return Response(res, status=200)
        except Exception as e:
            return Response(str(e), status=500)

    def get(self, request):

        try:
            res = Bookings.get_particular_booking_details(request)
            return Response(res, status=200)
        except Exception as e:
            return Response(str(e), status=500)


class CancelBooking(APIView):

    def post(self, request, bid):
        try:
            msg = Bookings.cancel_booking(bid)
            return Response(msg, status=200)

        except Exception as e:
            return Response(str(e), status=500)


class UpdateFeedback(APIView):

    def post(self, request, bid):
        try:
            msg = Bookings.give_feedback(request, bid)
            return Response(msg, status=200)
        except Exception as e:
            return Response(str(e), status=500)


class GetRentedEquip(APIView):

    def get(self, request):
        res = Bookings.get_booked_equipment(request)
        return Response(res, status=200)


class UserLogin(APIView):

    def post(self, request):
        try:
            access_token, refresh_token, is_admin = UserManager.user_login(request)
            response = Response()
            response.data = {
                "access_token": access_token,
                "refresh_token": refresh_token,
                "is_admin": is_admin
            }
            return response

        except Exception as e:
            return Response(str(e), status=500)


class GetUserDetails(APIView):

    def get(self, request):
        try:
            user = UserManager.get_user_details(request)
            return Response(user, status=200)
        except Exception as e:
            return Response(str(e), status=500)

    def post(self, request):
        res = UserManager.create_user(request)
        return Response(res, status=200)

    def put(self, request):
        try:
            msg = UserManager.edit_user_details(request)
            return Response(msg, status=200)
        except Exception as e:
            return Response(str(e), 500)


class CheckRefreshToken(APIView):

    def get(self, request):
        refresh_token = request.GET.get('refresh_token')
        try:

            user_id, is_admin = UserManager.check_refresh_token(refresh_token)

            response = Response()
            response.data = {
                "user_id": user_id,
                "is_admin": is_admin
            }
            return response

        except Exception as e:
            return Response(str(e), status=500)


class GetUserBookings(APIView):

    def get(self, request, uid):
        try:
            res = UserManager.get_user_booking(uid)
            return Response(res, status=200)
        except Exception as e:
            return Response(str(e), status=500)


class GetSports(APIView):

    def get(self, request):
        try:
            res = Facilities.get_all_sports()
            return Response(res, status=200)
        except Exception as e:
            return Response(str(e), status=500)


class SearchFilterWithPagination(APIView):

    def get(self, request):

        try:

            data, total_pages, current_page = Facilities.search_filter_pagination(request)

            return Response({
                'pageItems': data,
                'totalPages': total_pages,
                'page': current_page
            })
        except Exception as e:

            return Response(str(e), status=500)


class SearchFilterWithLoadMore(APIView):

    def get(self, request):
        try:
            data, total_results = Facilities.search_filter_load_more(request)
            return Response({
                'pageItems': data,
                'total_results': total_results
            })
        except Exception as e:
            return Response(str(e), status=500)
