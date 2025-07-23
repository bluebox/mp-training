import rest_framework.pagination
from django.shortcuts import render

# Create your views here.
from rest_framework import viewsets, status, generics
from rest_framework.response import Response
from rest_framework.views import APIView

from .serializers import StudentSerializer, DepartmentSerializer, StudentProfileSerializer, \
    StudentFullProfileSerializer, CourseSerializer
from .models import Student, Department, StudentProfile, Course


class StudentViewSet(viewsets.ModelViewSet):
    queryset = Student.objects.all()
    serializer_class = StudentSerializer

class DepartmentViewSet(viewsets.ModelViewSet):
    queryset = Department.objects.all()
    serializer_class = DepartmentSerializer

class DepartmentView(generics.ListAPIView):
    queryset = Department.objects.all()
    serializer_class = DepartmentSerializer
    pagination_class = rest_framework.pagination.PageNumberPagination

class StudentView(APIView):

    # def get(self, request):
    def get(self, request, id = None):
        # id = request.query_params.get('id',None)
        if id is not None:
            queryset = Student.objects.get(student_id__exact = id)
            serializer = StudentSerializer(queryset)
            return Response(serializer.data)
        else:
            queryset = Student.objects.all()
            serializer = StudentSerializer(queryset, many=True)
            return Response(serializer.data)

    def post(self, request):
        serializer = StudentSerializer(data=request.data)
        # print(request.data)
        if serializer.is_valid():
            # print(serializer.validated_data)
            serializer.save()
            # print(serializer.data)
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)


    def delete(self, request, id):
        queryset = Student.objects.filter(student_id__exact =id).delete()
        if queryset[0]:
            return Response("deleted",status=status.HTTP_204_NO_CONTENT)
        return Response("no record matched",status = status.HTTP_400_BAD_REQUEST)


    def put(self, request, id):
        queryset = Student.objects.get(student_id__exact = id)
        serializer = StudentSerializer(queryset, data = request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)


    def patch(self, request, id):
        queryset = Student.objects.get(student_id__exact=id)
        serializer = StudentSerializer(queryset, data=request.data, partial=True)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

class StudentProfileView(APIView):

    def get(self, request, id=None):

        if id is not None:
            queryset =  StudentProfile.objects.get(student__exact=id)
            serializer = StudentProfileSerializer(queryset)
            return Response(serializer.data)

        # return Response("id not found", status=status.HTTP_400_BAD_REQUEST)

        queryset = StudentProfile.objects.all()
        serializer = StudentProfileSerializer(queryset, many=True)
        return Response(serializer.data)

    def post(self, request):

        serializer = StudentProfileSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

    def delete(self, request, id):
        queryset = StudentProfile.objects.filter(student__exact=id).delete()
        if queryset[0]:
            return Response("deleted", status=status.HTTP_204_NO_CONTENT)
        return Response("no record matched", status=status.HTTP_400_BAD_REQUEST)

    def put(self, request, id):
        queryset = StudentProfile.objects.get(student__exact=id)
        serializer = StudentProfileSerializer(queryset, data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

    def patch(self, request, id):
        queryset = StudentProfile.objects.get(student__exact=id)
        serializer = StudentProfileSerializer(queryset, data=request.data, partial=True)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

class StudentFullProfileView(APIView):
    def get(self, request, id=None):
    # def get(self, request):
        if id is not None:
            queryset = StudentProfile.objects.select_related('student').all().filter(student__student_id__exact = 1)
            serializer = StudentFullProfileSerializer(queryset, many=True)
            return Response(serializer.data)
        queryset = StudentProfile.objects.select_related('student').all()
        serializer = StudentFullProfileSerializer(queryset, many=True)
        return Response(serializer.data)

class CourseView(APIView):

    def get(self, request):
        queryset = Course.objects.all()
        serializer = CourseSerializer(queryset, many=True)
        return Response(serializer.data)

    def post(self, request):
        serializer = CourseSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status = status.HTTP_201_CREATED)
        return Response(serializer.errors,status = status.HTTP_400_BAD_REQUEST)

    def delete(self, request, id):
        queryset = Course.objects.get(course_id__exact = id)
        if queryset:
            queryset.delete()
            return Response("deleted",status = status.HTTP_204_NO_CONTENT)
        return Response("record not found", status = status.HTTP_404_NOT_FOUND)

    def put(self, request, id):

        queryset = Course.objects.get(course_id__exact = id)
        serializer = CourseSerializer(queryset, data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status = status.HTTP_201_CREATED)
        return Response(serializer.errors,status = status.HTTP_400_BAD_REQUEST)

    def patch(self, request, id):

        queryset = Course.objects.get(course_id__exact = id)
        serializer = CourseSerializer(queryset, data=request.data, partial=True)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status = status.HTTP_201_CREATED)
        return Response(serializer.errors,status = status.HTTP_400_BAD_REQUEST)

