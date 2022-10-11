
import datetime
from django.http import Http404, HttpResponse
from django.shortcuts import render
from rest_framework.views import APIView
from rest_framework.response import Response
from rest_framework import status
from rest_framework.exceptions import APIException
from toursapp.models import Coupon, Employee, Enquiry, Package, Place, Tour, Vehicle
from toursapp.serializers import CouponSerializer, EmployeeSerializer, EnquirySerializer, PackageDetailSerializer, PackageSerializer, PlaceSerializer, TourDetailSerializer, TourSerializer, \
    VehicleSerializer


def index(req):
    return HttpResponse("welcome")

class VehicleList(APIView):

    def get(self, request, format=None):
        vehicles = Vehicle.objects.all()
        serializer = VehicleSerializer(vehicles, many=True)
        return Response(serializer.data)

    def post(self, request, format=None):
        serializer = VehicleSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

class VehicleDetails(APIView):
    """
    Retrieve, update or delete a snippet instance.
    """

    # authentication_classes = [JWTAuthentication]
    # permission_classes = (IsAuthenticated,)


    def get_object(self, pk):
        try:
            return Vehicle.objects.get(id=pk)
        except Vehicle.DoesNotExist:
            raise Http404


    def get(self, request, pk, format=None):
        vehcile = self.get_object(pk)
        serializer = VehicleSerializer(vehcile)
        return Response(serializer.data)

    def put(self, request, pk, format=None):
        vehcile = self.get_object(pk)
        serializer = VehicleSerializer(vehcile, data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)
    
    def delete(self, request, pk, format=None):
        vehicle = self.get_object(pk)
        vehicle.delete()
        serializer = VehicleSerializer(vehicle)
        return Response(serializer.data)



class ToursListViewSet(APIView):

    def get(self, request, format=None):
        tours = Tour.objects.all()
        serializer = TourDetailSerializer(tours, many=True)
        return Response(serializer.data)

    def post(self, request, format=None):
        serializer = TourSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)


class TourDetails(APIView):
    """
    Retrieve, update or delete a snippet instance.
    """

    # authentication_classes = [JWTAuthentication]
    # permission_classes = (IsAuthenticated,)


    def get_object(self, pk):
        try:
            return Tour.objects.get(id=pk)
        except Tour.DoesNotExist:
            raise Http404


    def get(self, request, pk, format=None):
        tour = self.get_object(pk)
        serializer = TourDetailSerializer(tour)
        return Response(serializer.data)

    def put(self, request, pk, format=None):
        tour = self.get_object(pk)
        # coupons = Coupon.objects.filter(id__in=request.data['coupons'])
        
        # places = Place.objects.filter(id__in=request.data['coupons'])

        serializer = TourSerializer(tour, data=request.data)
        if serializer.is_valid():
            serializer.save()
            # tour = Tour.objects.get(id=pk)
            # for coupon in coupons:
            #     tour.coupons.add(coupon)
            # for place in places:
            #     tour.places.add(place)
            return Response(serializer.data)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

    def delete(self, request, pk, format=None):
        tour = self.get_object(pk)
        tour.delete()
        return Response(status=status.HTTP_204_NO_CONTENT)


class EnquiryListViewSet(APIView):

    def get(self, request, format=None):
        enquiry = Enquiry.objects.filter(status=True)
        serializer = EnquirySerializer(enquiry, many=True)
        return Response(serializer.data)

    def post(self, request, format=None):
        serializer = EnquirySerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)



class EnquiryDetails(APIView):
    """
    Retrieve, update or delete a snippet instance.
    """

    # authentication_classes = [JWTAuthentication]
    # permission_classes = (IsAuthenticated,)


    def get_object(self, pk):
        try:
            return Enquiry.objects.get(id=pk)
        except Enquiry.DoesNotExist:
            raise Http404


    def get(self, request, pk, format=None):
        enquiry = self.get_object(pk)
        serializer = EnquirySerializer(enquiry)
        return Response(serializer.data)

    def put(self, request, pk, format=None):
        enquiry = self.get_object(pk)
        serializer = EnquirySerializer(enquiry, data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

class PlacesListViewSet(APIView):

    def get(self, request, format=None):
        places = Place.objects.all()
        serializer = PlaceSerializer(places, many=True)
        return Response(serializer.data)

    def post(self, request, format=None):
        serializer = PlaceSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        raise APIException(serializer.errors)
    
class PlaceDetails(APIView):
    """
    Retrieve, update or delete a snippet instance.
    """

    # authentication_classes = [JWTAuthentication]
    # permission_classes = (IsAuthenticated,)


    def get_object(self, pk):
        try:
            return Place.objects.get(id=pk)
        except Place.DoesNotExist:
            raise Http404


    def get(self, request, pk, format=None):
        place = self.get_object(pk)
        serializer = PlaceSerializer(place)
        return Response(serializer.data)

    def put(self, request, pk, format=None):
        place = self.get_object(pk)
        serializer = PlaceSerializer(place, data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)



class CouponsListViewSet(APIView):

    def get(self, request, format=None):
        # coupons = Coupon.objects.filter(valid_till__gt=datetime.datetime.utcnow())
        coupons = Coupon.objects.filter()
        serializer = CouponSerializer(coupons, many=True)
        return Response(serializer.data)

    def post(self, request, format=None):
        serializer = CouponSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)



class CouponDetails(APIView):
    """
    Retrieve, update or delete a snippet instance.
    """

    # authentication_classes = [JWTAuthentication]
    # permission_classes = (IsAuthenticated,)


    def get_object(self, pk):
        try:
            return Coupon.objects.get(id=pk)
        except Coupon.DoesNotExist:
            raise Http404


    def get(self, request, pk, format=None):
        coupon = self.get_object(pk)
        serializer = CouponSerializer(coupon)
        return Response(serializer.data)

    def put(self, request, pk, format=None):
        coupon = self.get_object(pk)
        serializer = CouponSerializer(coupon, data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)



class PackagesList(APIView):

    def get(self, request, format=None):
        packages = Package.objects.all()
        serializer = PackageSerializer(packages, many=True)
        return Response(serializer.data)

    def post(self, request, format=None):
        serializer = PackageSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

class PackageDetailed(APIView):
    """
    Retrieve, update or delete a snippet instance.
    """

    # authentication_classes = [JWTAuthentication]
    # permission_classes = (IsAuthenticated,)


    def get_object(self, pk):
        try:
            return Package.objects.get(id=pk)
        except Package.DoesNotExist:
            raise Http404


    def get(self, request, pk, format=None):
        package = self.get_object(pk)
        serializer = PackageDetailSerializer(package)
        return Response(serializer.data)


class PackageDetails(APIView):
    """
    Retrieve, update or delete a snippet instance.
    """

    # authentication_classes = [JWTAuthentication]
    # permission_classes = (IsAuthenticated,)


    def get_object(self, pk):
        try:
            return Package.objects.get(id=pk)
        except Package.DoesNotExist:
            raise Http404


    def get(self, request, pk, format=None):
        package = self.get_object(pk)
        serializer = PackageSerializer(package)
        return Response(serializer.data)

    def put(self, request, pk, format=None):
        package = self.get_object(pk)
        serializer = PackageSerializer(package, data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

    def delete(self, request, pk, format=None):
        package = self.get_object(pk)
        package.delete()
        serializer = PackageSerializer(package)
        return Response(serializer.data)
        # return Response(status=status.HTTP_204_NO_CONTENT)



class EmployeeList(APIView):

    def get(self, request, format=None):
        packages = Employee.objects.all()
        serializer = EmployeeSerializer(packages, many=True)
        return Response(serializer.data)

    def post(self, request, format=None):
        serializer = EmployeeSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)


class EmployeeDetails(APIView):
    """
    Retrieve, update or delete a snippet instance.
    """

    # authentication_classes = [JWTAuthentication]
    # permission_classes = (IsAuthenticated,)


    def get_object(self, pk):
        try:
            return Employee.objects.get(id=pk)
        except Employee.DoesNotExist:
            raise Http404


    def get(self, request, pk, format=None):
        employee = self.get_object(pk)
        serializer = EmployeeSerializer(employee)
        return Response(serializer.data)

    def put(self, request, pk, format=None):
        employee = self.get_object(pk)
        serializer = EmployeeSerializer(employee, data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

    def delete(self, request, pk, format=None):
        employee = self.get_object(pk)
        employee.delete()
        serializer = EmployeeSerializer(employee)
        return Response(serializer.data)
        # return Response(status=status.HTTP_204_NO_CONTENT)
