
import datetime
from django.http import Http404, HttpResponse
from django.shortcuts import render
from rest_framework.views import APIView
from rest_framework.response import Response
from rest_framework import status
from rest_framework.exceptions import APIException
from TourismServer.managers.adminManager import listing
from TourismServer.permissions import IsAdminUser, IsAuthenticated
from bookingsapp.JwtAuthentication import JWTAuthentication
from toursapp import serializers
from toursapp.models import Coupon, Employee, Enquiry, Package, Place, Tour, Vehicle
from toursapp.serializers import CouponSerializer, EmployeeSerializer, EnquirySerializer, PackageDetailSerializer, PackageSerializer, PlaceSerializer, TourDetailSerializer, TourSerializer, \
    VehicleSerializer


def index(req):
    return HttpResponse("welcome")

class AllVehicleList(APIView):

    authentication_classes = [JWTAuthentication]
    permission_classes = (IsAdminUser,)

    def get(self, request, format=None):
        vehicles = Vehicle.objects.all()
        serializer = VehicleSerializer(vehicles, many=True)
        return Response(serializer.data)


class VehicleList(APIView):

    authentication_classes = [JWTAuthentication]
    permission_classes = (IsAdminUser,)

    def get(self, request, format=None):
        text = request.GET.get('text')
        vehicles_list = (Vehicle.objects.filter(vehicle_type__icontains=text) | 
                            Vehicle.objects.filter(model__icontains=text) |
                            Vehicle.objects.filter(vehicle_number__icontains=text))
        vehicles, totalPages, page = listing(request, vehicles_list)
        serializer = VehicleSerializer(vehicles, many=True)
        return Response({
            'pageItems': serializer.data,
            'totalPages': totalPages,
            'page': page
        })

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

    authentication_classes = [JWTAuthentication]

    # permission_classes = (IsAdminUser,)


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


class AllToursListViewSet(APIView):

    def get(self, request, format=None):
        tours = Tour.objects.all()
        serializer = TourDetailSerializer(tours, many=True)
        return Response(serializer.data)

class AllUserToursListViewSet(APIView):

    def get(self, request, format=None):
        tours = Tour.objects.filter(start_date__gt=datetime.datetime.utcnow())
        serializer = TourDetailSerializer(tours, many=True)
        return Response(serializer.data)


class ToursListViewSet(APIView):

    def get(self, request, format=None):
        text = request.GET.get('text')
        tours_list = (Tour.objects.filter(tour_name__icontains=text) | 
                            Tour.objects.filter(tour_to__icontains=text) |
                            Tour.objects.filter(description__icontains=text) |
                            Tour.objects.filter(tour_type__icontains=text))
        tours, totalPages, page = listing(request, tours_list)
        serializer = TourDetailSerializer(tours, many=True)
        return Response({
            'pageItems': serializer.data,
            'totalPages': totalPages,
            'page': page
        })

    def post(self, request, format=None):
        serializer = TourSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)


class ToursFilterByType(APIView):
    def get(self, request, tour_type=''):
        tours = Tour.objects.filter(tour_type__contains=tour_type, start_date__gt=datetime.datetime.utcnow())
        serializer = TourDetailSerializer(tours, many=True)
        return Response(serializer.data)


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

class AllPlacesListViewSet(APIView):

    def get(self, request, format=None):
        places = Place.objects.all()
        serializer = PlaceSerializer(places, many=True)
        return Response(serializer.data)


class PlacesListViewSet(APIView):

    def get(self, request, format=None):
        text = request.GET.get('text')
        places_list = (Place.objects.filter(place_name__icontains=text) | 
                            Place.objects.filter(description__icontains=text))
        places, totalPages, page = listing(request, places_list)
        serializer = PlaceSerializer(places, many=True)
        return Response({
            'pageItems': serializer.data,
            'totalPages': totalPages,
            'page': page
        })

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


class AllCouponsListViewSet(APIView):

    def get(self, request, format=None):
        # coupons = Coupon.objects.filter(valid_till__gt=datetime.datetime.utcnow())
        coupons = Coupon.objects.filter()
        serializer = CouponSerializer(coupons, many=True)
        return Response(serializer.data)



class CouponsListViewSet(APIView):

    def get(self, request, format=None):
        # coupons = Coupon.objects.filter(valid_till__gt=datetime.datetime.utcnow())
        text = request.GET.get('text')
        coupons_list = (Coupon.objects.filter(couponcode__icontains=text, valid_till__gt=datetime.datetime.utcnow()) | 
                            Coupon.objects.filter(description__icontains=text, valid_till__gt=datetime.datetime.utcnow()))
        coupons, totalPages, page = listing(request, coupons_list)
        serializer = CouponSerializer(coupons, many=True)
        return Response({
            'pageItems': serializer.data,
            'totalPages': totalPages,
            'page': page
        })

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



class AllPackagesList(APIView):

    def get(self, request, format=None):
        packages = Package.objects.all()
        serializer = PackageSerializer(packages, many=True)
        return Response(serializer.data)


class PackagesList(APIView):

    def get(self, request, format=None):
        text = request.GET.get('text')
        packages_list = (Package.objects.filter(package_name__icontains=text) | 
                            Package.objects.filter(package_type__icontains=text) |
                            Package.objects.filter(description__icontains=text))
        packages, totalPages, page = listing(request, packages_list)
        serializer = PackageSerializer(packages, many=True)
        return Response({
            'pageItems': serializer.data,
            'totalPages': totalPages,
            'page': page
        })

    def post(self, request, format=None):
        serializer = PackageSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        raise APIException(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

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
        # package = self.get_object(pk)
        # serializer = PackageDetailSerializer(package)
        # return Response(serializer.data)
        package = self.get_object(pk)
        serializer = PackageDetailSerializer(package)
        data = serializer.data
        tours = []
        for i in data['tours']:
            if datetime.datetime.strptime(i['start_date'].split('T')[0], "%Y-%m-%d") > datetime.datetime.utcnow():
                tours.append(i)
        data['tours'] = tours

        return Response(data)


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
        # data = serializer.data
        # tours = []
        # for i in data['tours']:
        #     if datetime.datetime.strptime(i['start_date'].split('T')[0], "%Y-%m-%d") > datetime.datetime.utcnow():
        #         tours.append(i)
        # data['tours'] = tours
        #
        # return Response(data)
        return Response(serializer.data)


    def put(self, request, pk, format=None):
        package = self.get_object(pk)
        serializer = PackageSerializer(package, data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        raise APIException(serializer.errors)

    def delete(self, request, pk, format=None):
        package = self.get_object(pk)
        package.delete()
        serializer = PackageSerializer(package)
        return Response(serializer.data)
        # return Response(status=status.HTTP_204_NO_CONTENT)



class AllEmployeeList(APIView):

    def get(self, request, format=None):
        packages = Employee.objects.all()
        serializer = EmployeeSerializer(packages, many=True)
        return Response(serializer.data)


class EmployeeList(APIView):

    def get(self, request, format=None):
        # employee_list = Employee.objects.all()
        # serializer = EmployeeSerializer(packages, many=True)
        # return Response(serializer.data)
        text = request.GET.get('text')
        employee_list = (Employee.objects.filter(name__icontains=text) | 
                            Employee.objects.filter(email__icontains=text))
        employees, totalPages, page = listing(request, employee_list)
        serializer = EmployeeSerializer(employees, many=True)
        return Response({
            'pageItems': serializer.data,
            'totalPages': totalPages,
            'page': page
        })

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


class TourPlaceDelete(APIView):
    def put(self, request, pk):
        tour = Tour.objects.get(id=1)

        return Response({'msg':'Success'})