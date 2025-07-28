import rest_framework.pagination
from django.shortcuts import render
from rest_framework.permissions import IsAuthenticated
from rest_framework import viewsets, status, generics
from rest_framework.views import APIView
from rest_framework.response import Response

from .models import (
    Student, StudentProfile, Department,
    Course, Teacher, Enrollment, TeacherCourse
)
from .serializers import (
    StudentSerializer, StudentProfileSerializer, DepartmentSerializer,
    CourseSerializer, TeacherSerializer, EnrollmentSerializer, TeacherCourseSerializer, StudentFullProfileSerializer
)



class StudentAPIView(APIView):
    def get(self, request, id=None):
        if id:
            try:
                student = Student.objects.get(student_id=id)
                serializer = StudentSerializer(student)
                return Response(serializer.data)
            except Student.DoesNotExist:
                return Response({'error': 'Student not found'}, status=status.HTTP_404_NOT_FOUND)
        students = Student.objects.all()
        serializer = StudentSerializer(students, many=True)
        return Response(serializer.data)

    def post(self, request):
        serializer = StudentSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

    def put(self, request, id):
        try:
            student = Student.objects.get(student_id=id)
        except Student.DoesNotExist:
            return Response({'error': 'Student not found'}, status=status.HTTP_404_NOT_FOUND)
        serializer = StudentSerializer(student, data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

    def patch(self, request, id):
        try:
            student = Student.objects.get(student_id=id)
        except Student.DoesNotExist:
            return Response({'error': 'Student not found'}, status=status.HTTP_404_NOT_FOUND)
        serializer = StudentSerializer(student, data=request.data, partial=True)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

    def delete(self, request, id):
        deleted, _ = Student.objects.filter(student_id=id).delete()
        if deleted:
            return Response(status=status.HTTP_204_NO_CONTENT)
        return Response({'error': 'Student not found'}, status=status.HTTP_404_NOT_FOUND)

class StudentProfileAPIView(APIView):
    def get(self, request, id=None):
        if id:
            try:
                profile = StudentProfile.objects.get(student_id=id)
                serializer = StudentProfileSerializer(profile)
                return Response(serializer.data)
            except StudentProfile.DoesNotExist:
                return Response({'error': 'Profile not found'}, status=status.HTTP_404_NOT_FOUND)
        profiles = StudentProfile.objects.all()
        serializer = StudentProfileSerializer(profiles, many=True)
        return Response(serializer.data)

    def post(self, request):
        serializer = StudentProfileSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

    def put(self, request, id):
        try:
            profile = StudentProfile.objects.get(student_id=id)
        except StudentProfile.DoesNotExist:
            return Response({'error': 'Profile not found'}, status=status.HTTP_404_NOT_FOUND)
        serializer = StudentProfileSerializer(profile, data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

    def patch(self, request, id):
        try:
            profile = StudentProfile.objects.get(student_id=id)
        except StudentProfile.DoesNotExist:
            return Response({'error': 'Profile not found'}, status=status.HTTP_404_NOT_FOUND)
        serializer = StudentProfileSerializer(profile, data=request.data, partial=True)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

    def delete(self, request, id):
        deleted, _ = StudentProfile.objects.filter(student_id=id).delete()
        if deleted:
            return Response(status=status.HTTP_204_NO_CONTENT)
        return Response({'error': 'Profile not found'}, status=status.HTTP_404_NOT_FOUND)

class DepartmentAPIView(APIView):
    def get(self, request, id=None):
        if id:
            try:
                dept = Department.objects.get(dept_id=id)
                serializer = DepartmentSerializer(dept)
                return Response(serializer.data)
            except Department.DoesNotExist:
                return Response({'error': 'Department not found'}, status=status.HTTP_404_NOT_FOUND)
        depts = Department.objects.all()
        serializer = DepartmentSerializer(depts, many=True)
        return Response(serializer.data)

    def post(self, request):
        serializer = DepartmentSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

    def put(self, request, id):
        try:
            dept = Department.objects.get(dept_id=id)
        except Department.DoesNotExist:
            return Response({'error': 'Department not found'}, status=status.HTTP_404_NOT_FOUND)
        serializer = DepartmentSerializer(dept, data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

    def patch(self, request, id):
        try:
            dept = Department.objects.get(dept_id=id)
        except Department.DoesNotExist:
            return Response({'error': 'Department not found'}, status=status.HTTP_404_NOT_FOUND)
        serializer = DepartmentSerializer(dept, data=request.data, partial=True)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

    def delete(self, request, id):
        deleted, _ = Department.objects.filter(dept_id=id).delete()
        if deleted:
            return Response(status=status.HTTP_204_NO_CONTENT)
        return Response({'error': 'Department not found'}, status=status.HTTP_404_NOT_FOUND)

class CourseAPIView(APIView):
    def get(self, request, id=None):
        if id:
            try:
                course = Course.objects.get(course_id=id)
                serializer = CourseSerializer(course)
                return Response(serializer.data)
            except Course.DoesNotExist:
                return Response({'error': 'Course not found'}, status=status.HTTP_404_NOT_FOUND)
        courses = Course.objects.all()
        serializer = CourseSerializer(courses, many=True)
        return Response(serializer.data)

    def post(self, request):
        serializer = CourseSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

    def put(self, request, id):
        try:
            course = Course.objects.get(course_id=id)
        except Course.DoesNotExist:
            return Response({'error': 'Course not found'}, status=status.HTTP_404_NOT_FOUND)
        serializer = CourseSerializer(course, data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

    def patch(self, request, id):
        try:
            course = Course.objects.get(course_id=id)
        except Course.DoesNotExist:
            return Response({'error': 'Course not found'}, status=status.HTTP_404_NOT_FOUND)
        serializer = CourseSerializer(course, data=request.data, partial=True)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

    def delete(self, request, id):
        deleted, _ = Course.objects.filter(course_id=id).delete()
        if deleted:
            return Response(status=status.HTTP_204_NO_CONTENT)
        return Response({'error': 'Course not found'}, status=status.HTTP_404_NOT_FOUND)

class TeacherAPIView(APIView):
    def get(self, request, id=None):
        if id:
            try:
                teacher = Teacher.objects.get(teacher_id=id)
                serializer = TeacherSerializer(teacher)
                return Response(serializer.data)
            except Teacher.DoesNotExist:
                return Response({'error': 'Teacher not found'}, status=status.HTTP_404_NOT_FOUND)
        teachers = Teacher.objects.all()
        serializer = TeacherSerializer(teachers, many=True)
        return Response(serializer.data)

    def post(self, request):
        serializer = TeacherSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

    def put(self, request, id):
        try:
            teacher = Teacher.objects.get(teacher_id=id)
        except Teacher.DoesNotExist:
            return Response({'error': 'Teacher not found'}, status=status.HTTP_404_NOT_FOUND)
        serializer = TeacherSerializer(teacher, data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

    def patch(self, request, id):
        try:
            teacher = Teacher.objects.get(teacher_id=id)
        except Teacher.DoesNotExist:
            return Response({'error': 'Teacher not found'}, status=status.HTTP_404_NOT_FOUND)
        serializer = TeacherSerializer(teacher, data=request.data, partial=True)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

    def delete(self, request, id):
        deleted, _ = Teacher.objects.filter(teacher_id=id).delete()
        if deleted:
            return Response(status=status.HTTP_204_NO_CONTENT)
        return Response({'error': 'Teacher not found'}, status=status.HTTP_404_NOT_FOUND)

class EnrollmentAPIView(APIView):
    def get(self, request, id=None):
        if id:
            try:
                enrollment = Enrollment.objects.get(enrollment_id=id)
                serializer = EnrollmentSerializer(enrollment)
                return Response(serializer.data)
            except Enrollment.DoesNotExist:
                return Response({'error': 'Enrollment not found'}, status=status.HTTP_404_NOT_FOUND)
        enrollments = Enrollment.objects.all()
        serializer = EnrollmentSerializer(enrollments, many=True)
        return Response(serializer.data)

    def post(self, request):
        serializer = EnrollmentSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

    def put(self, request, id):
        try:
            enrollment = Enrollment.objects.get(enrollment_id=id)
        except Enrollment.DoesNotExist:
            return Response({'error': 'Enrollment not found'}, status=status.HTTP_404_NOT_FOUND)
        serializer = EnrollmentSerializer(enrollment, data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

    def patch(self, request, id):
        try:
            enrollment = Enrollment.objects.get(enrollment_id=id)
        except Enrollment.DoesNotExist:
            return Response({'error': 'Enrollment not found'}, status=status.HTTP_404_NOT_FOUND)
        serializer = EnrollmentSerializer(enrollment, data=request.data, partial=True)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

    def delete(self, request, id):
        deleted, _ = Enrollment.objects.filter(enrollment_id=id).delete()
        if deleted:
            return Response(status=status.HTTP_204_NO_CONTENT)
        return Response({'error': 'Enrollment not found'}, status=status.HTTP_404_NOT_FOUND)

class TeacherCourseAPIView(APIView):
    def get(self, request, id=None):
        if id:
            try:
                record = TeacherCourse.objects.get(teacher_course_id=id)
                serializer = TeacherCourseSerializer(record)
                return Response(serializer.data)
            except TeacherCourse.DoesNotExist:
                return Response({'error': 'Record not found'}, status=status.HTTP_404_NOT_FOUND)
        records = TeacherCourse.objects.all()
        serializer = TeacherCourseSerializer(records, many=True)
        return Response(serializer.data)

    def post(self, request):
        serializer = TeacherCourseSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

    def put(self, request, id):
        try:
            record = TeacherCourse.objects.get(teacher_course_id=id)
        except TeacherCourse.DoesNotExist:
            return Response({'error': 'Record not found'}, status=status.HTTP_404_NOT_FOUND)
        serializer = TeacherCourseSerializer(record, data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

    def patch(self, request, id):
        try:
            record = TeacherCourse.objects.get(teacher_course_id=id)
        except TeacherCourse.DoesNotExist:
            return Response({'error': 'Record not found'}, status=status.HTTP_404_NOT_FOUND)
        serializer = TeacherCourseSerializer(record, data=request.data, partial=True)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

    def delete(self, request, id):
        deleted, _ = TeacherCourse.objects.filter(teacher_course_id=id).delete()
        if deleted:
            return Response(status=status.HTTP_204_NO_CONTENT)
        return Response({'error': 'Record not found'}, status=status.HTTP_404_NOT_FOUND)

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

    def post(self, request):
        serializer = StudentFullProfileSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)



# class StudentViewSet(viewsets.ModelViewSet):
#     queryset = Student.objects.all()
#     serializer_class = StudentSerializer
#
# class DepartmentViewSet(viewsets.ModelViewSet):
#     queryset = Department.objects.all()
#     serializer_class = DepartmentSerializer
#
# class DepartmentView(generics.ListAPIView):
#     queryset = Department.objects.all()
#     serializer_class = DepartmentSerializer
#     pagination_class = rest_framework.pagination.PageNumberPagination
#
# class StudentView(APIView):
#
#     # def get(self, request):
#     def get(self, request, id = None):
#         # id = request.query_params.get('id',None)
#         if id is not None:
#             queryset = Student.objects.get(student_id__exact = id)
#             serializer = StudentSerializer(queryset)
#             return Response(serializer.data)
#         else:
#             queryset = Student.objects.all()
#             serializer = StudentSerializer(queryset, many=True)
#             return Response(serializer.data)
#
#     def post(self, request):
#         serializer = StudentSerializer(data=request.data)
#         # print(request.data)
#         if serializer.is_valid():
#             # print(serializer.validated_data)
#             serializer.save()
#             # print(serializer.data)
#             return Response(serializer.data, status=status.HTTP_201_CREATED)
#         return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)
#
#
#     def delete(self, request, id):
#         queryset = Student.objects.filter(student_id__exact =id).delete()
#         if queryset[0]:
#             return Response("deleted",status=status.HTTP_204_NO_CONTENT)
#         return Response("no record matched",status = status.HTTP_400_BAD_REQUEST)
#
#
#     def put(self, request, id):
#         queryset = Student.objects.get(student_id__exact = id)
#         serializer = StudentSerializer(queryset, data = request.data)
#         if serializer.is_valid():
#             serializer.save()
#             return Response(serializer.data)
#         return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)
#
#
#     def patch(self, request, id):
#         queryset = Student.objects.get(student_id__exact=id)
#         serializer = StudentSerializer(queryset, data=request.data, partial=True)
#         if serializer.is_valid():
#             serializer.save()
#             return Response(serializer.data)
#         return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)
#
# class StudentProfileView(APIView):
#     permission_classes = [IsAuthenticated]
#
#     def get(self, request, id=None):
#
#         if id is not None:
#             queryset =  StudentProfile.objects.get(student__exact=id)
#             serializer = StudentProfileSerializer(queryset)
#             return Response(serializer.data)
#
#         # return Response("id not found", status=status.HTTP_400_BAD_REQUEST)
#
#         queryset = StudentProfile.objects.all()
#         serializer = StudentProfileSerializer(queryset, many=True)
#         return Response(serializer.data)
#
#     def post(self, request):
#
#         serializer = StudentProfileSerializer(data=request.data)
#         if serializer.is_valid():
#             serializer.save()
#             return Response(serializer.data, status=status.HTTP_201_CREATED)
#         return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)
#
#     def delete(self, request, id):
#         queryset = StudentProfile.objects.filter(student__exact=id).delete()
#         if queryset[0]:
#             return Response("deleted", status=status.HTTP_204_NO_CONTENT)
#         return Response("no record matched", status=status.HTTP_400_BAD_REQUEST)
#
#     def put(self, request, id):
#         queryset = StudentProfile.objects.get(student__exact=id)
#         serializer = StudentProfileSerializer(queryset, data=request.data)
#         if serializer.is_valid():
#             serializer.save()
#             return Response(serializer.data)
#         return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)
#
#     def patch(self, request, id):
#         queryset = StudentProfile.objects.get(student__exact=id)
#         serializer = StudentProfileSerializer(queryset, data=request.data, partial=True)
#         if serializer.is_valid():
#             serializer.save()
#             return Response(serializer.data)
#         return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)
#
# class StudentFullProfileView(APIView):
#     def get(self, request, id=None):
#     # def get(self, request):
#         if id is not None:
#             queryset = StudentProfile.objects.select_related('student').all().filter(student__student_id__exact = 1)
#             serializer = StudentFullProfileSerializer(queryset, many=True)
#             return Response(serializer.data)
#         queryset = StudentProfile.objects.select_related('student').all()
#         serializer = StudentFullProfileSerializer(queryset, many=True)
#         return Response(serializer.data)
#
# class CourseView(APIView):
#
#     def get(self, request):
#         queryset = Course.objects.all()
#         serializer = CourseSerializer(queryset, many=True)
#         return Response(serializer.data)
#
#     def post(self, request):
#         serializer = CourseSerializer(data=request.data)
#         if serializer.is_valid():
#             serializer.save()
#             return Response(serializer.data, status = status.HTTP_201_CREATED)
#         return Response(serializer.errors,status = status.HTTP_400_BAD_REQUEST)
#
#     def delete(self, request, id):
#         queryset = Course.objects.get(course_id__exact = id)
#         if queryset:
#             queryset.delete()
#             return Response("deleted",status = status.HTTP_204_NO_CONTENT)
#         return Response("record not found", status = status.HTTP_404_NOT_FOUND)
#
#     def put(self, request, id):
#
#         queryset = Course.objects.get(course_id__exact = id)
#         serializer = CourseSerializer(queryset, data=request.data)
#         if serializer.is_valid():
#             serializer.save()
#             return Response(serializer.data, status = status.HTTP_201_CREATED)
#         return Response(serializer.errors,status = status.HTTP_400_BAD_REQUEST)
#
#     def patch(self, request, id):
#
#         queryset = Course.objects.get(course_id__exact = id)
#         serializer = CourseSerializer(queryset, data=request.data, partial=True)
#         if serializer.is_valid():
#             serializer.save()
#             return Response(serializer.data, status = status.HTTP_201_CREATED)
#         return Response(serializer.errors,status = status.HTTP_400_BAD_REQUEST)

