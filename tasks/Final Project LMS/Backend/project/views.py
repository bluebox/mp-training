"""Creating views"""
from django.db.models.query_utils import Q
from django.http.response import JsonResponse
from rest_framework import generics, status
from django.views.decorators.csrf import csrf_exempt
from rest_framework.pagination import PageNumberPagination
from rest_framework.renderers import JSONRenderer

from .models import *
from .project_manager import CourseRatingManager, filter_course_by_title, AllCourses, \
    TeacherCourses, TopicVideoList, EnrolledStudents, EnrolledCourseStudent, FavoriteCourse
from .serializers import *
from rest_framework.views import APIView
from rest_framework.response import Response
from django.http.response import Http404


class StandardResultsSetPagination(PageNumberPagination):
    page_size = 8
    page_size_query_param = 'page_size'
    max_page_size = 8


class InstructorView(APIView):
    """Handle List type record (fetch and post data)"""

    def get_instructor(self, pk):
        try:
            teacher = Instructor.objects.get(id=pk)
            return teacher
        except Instructor.DoesNotExist:
            raise Http404

    def get(self, request, pk=None):
        if pk:
            instructor = self.get_instructor(pk)
            serializer = InstructorSerializer(instructor)
        else:
            instructor = Instructor.objects.all()
            serializer = InstructorSerializer(instructor, many=True)
        return Response(serializer.data)

    def post(self, request):
        serializer = InstructorSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return JsonResponse("Instructor Data Added Successfully", safe=False)
        return JsonResponse(serializer.errors)

    def put(self, request, pk=None):
        instructor = Instructor.objects.get(id=pk)
        serializer = InstructorSerializer(instance=instructor, data=request.data, partial=True)
        if serializer.is_valid():
            serializer.save()
            return JsonResponse("success", safe=False)
        return JsonResponse("Failed to update instructor")

    def delete(self, request, pk=None):
        instructor = Instructor.objects.get(id=pk)
        instructor.delete()
        return Response(status=status.HTTP_204_NO_CONTENT)


class SubCategoryList(APIView):
    def get_subcategory(self, pk):
        try:
            sub_category = SubCategory.objects.get(id=pk)
            return sub_category
        except Instructor.DoesNotExist:
            raise Http404

    def get(self, request, pk=0):
        if pk:
            sub_category = self.get_subcategory(pk)
            serializer = SubCategorySerializer(sub_category)
        else:
            sub_category = SubCategory.objects.all()
            serializer = SubCategorySerializer(sub_category, many=True)
        return Response(serializer.data)

    def post(self, request):
        serializer = SubCategorySerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return JsonResponse("Sub-Category Data Added Successfully", safe=False)
        return JsonResponse("Sub-Category Data failed to add", safe=False)

    def put(self, request, pk=None):
        subcategory = SubCategory.objects.get(id=pk)
        serializer = SubCategorySerializer(instance=subcategory, data=request.data, partial=True)
        if serializer.is_valid():
            serializer.save()
            return JsonResponse("Student Updated Successfully", safe=False)
        return JsonResponse("Failed to update student")

    def delete(self, request, pk=None):
        subcategory = SubCategory.objects.get(id=pk)
        subcategory.delete()
        return Response(status=status.HTTP_204_NO_CONTENT)


class CourseList(APIView):
    def get_course(self, pk):
        try:
            course = Course.objects.get(id=pk)
            return course
        except Course.DoesNotExist:
            raise Http404

    def get(self, request, pk=0):
        if pk:
            course = self.get_course(pk)
            serializer = CourseSerializer(course)
        else:
            course = Course.objects.all()
            serializer = CourseSerializer(course, many=True)
        return Response(serializer.data)

    def post(self, request):
        serializer = CourseSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return JsonResponse("Course Data Added Successfully", safe=False)
        return JsonResponse(serializer.errors)

    def put(self, request, pk=None):
        course = Course.objects.get(id=pk)
        serializer = CourseSerializer(instance=course, data=request.data, partial=True)
        if serializer.is_valid():
            serializer.save()
            return JsonResponse("Course Updated Successfully", safe=False)
        return JsonResponse("Failed to update Course")

    def delete(self, request, pk=None):
        course = Course.objects.get(id=pk)
        course.delete()
        return Response(status=status.HTTP_204_NO_CONTENT)


class SearchCourseList(generics.ListAPIView):
    queryset = models.Course.objects.all()
    serializer_class = CourseSerializer

    def get_queryset(self):
        try:
            if 'searchData' in self.kwargs:
                search = self.kwargs['searchData']
                qs = filter_course_by_title(search)
                return qs
        except Exception as e:
            return Course.objects.none()


class AllCourseList(AllCourses, generics.ListCreateAPIView):
    queryset = Course.objects.all()
    serializer_class = CourseSerializer
    pagination_class = StandardResultsSetPagination

    def get_queryset(self):
        try:
            return super().get_queryset()
        except Exception as e:
            return Course.objects.none()
        # qs = super().get_queryset()
        # if 'result' in self.request.GET:
        #     limit = int(self.request.GET['result'])
        #     qs = Course.objects.all().order_by('-id')[:limit]
        # return qs


class AllCourseList1(generics.RetrieveUpdateDestroyAPIView):
    queryset = Course.objects.all()
    serializer_class = CourseSerializer


class TeacherCourseList(TeacherCourses, generics.ListAPIView):
    serializer_class = CourseSerializer

    def get_queryset(self):
        try:
            return self.get_teacher_courses()
        except Exception as e:
            pass


class TopicList(APIView):
    def get_topic(self, pk):
        try:
            topic = Topic.objects.get(id=pk)
            return topic
        except Topic.DoesNotExist:
            raise Http404

    def get(self, request, pk=0):
        if pk:
            topic = self.get_topic(pk)
            serializer = TopicSerializer(topic)
        else:
            topic = Topic.objects.all()
            serializer = TopicSerializer(topic, many=True)
        return Response(serializer.data)

    def post(self, request):
        serializer = TopicSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return JsonResponse("Topic Data Added Successfully", safe=False)
        return JsonResponse(serializer.errors)

    def put(self, request, pk=None):
        topic = Topic.objects.get(id=pk)
        serializer = TopicSerializer(instance=topic, data=request.data, partial=True)
        if serializer.is_valid():
            serializer.save()
            return JsonResponse("topic Updated Successfully", safe=False)
        return JsonResponse("Failed to update topic")

    def delete(self, request, pk=None):
        topic = Topic.objects.get(id=pk)
        topic.delete()
        return Response(status=status.HTTP_204_NO_CONTENT)


class TeacherCourseChapterList(TopicVideoList, generics.ListAPIView):
    serializer_class = TopicSerializer

    def get_queryset(self):
        try:
            return self.get_topic_videos_list()
        except Exception as e:
            pass


class CourseRatingPost(APIView):
    def post(self, request):
        serializer = CourseRatingSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return JsonResponse("Rating Added Successfully", safe=False)
        return JsonResponse(serializer.errors)


class CourseRatingList(generics.ListCreateAPIView):
    queryset = CourseRating.objects.all()
    serializer_class = CourseRatingSerializer

    def get_queryset(self):
        try:
            return CourseRatingManager.get_queryset()
        except Exception as e:
            return e


class CourseRatingDetail(generics.RetrieveUpdateDestroyAPIView):
    queryset = CourseRating.objects.all()
    serializer_class = CourseRatingSerializer


# ------------------ Student/User side

class StudentViewPost(APIView):
    def post(self, request):
        serializer = StudentSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return JsonResponse("Student Data Added Successfully", safe=False)
        return JsonResponse("Student Data failed to add", safe=False)


class StudentList(generics.ListCreateAPIView):
    queryset = Student.objects.all()
    serializer_class = StudentSerializer


class StudentDetail(generics.RetrieveUpdateDestroyAPIView):
    queryset = Student.objects.all()
    serializer_class = StudentSerializer


class EnrollCoursePost(APIView):
    def post(self, request):
        serializer = EnrollCourseSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return JsonResponse("Student Enrolled Successfully", safe=False)
        return JsonResponse("Student Failed To Add", safe=False)


class EnrollCourseList(generics.ListCreateAPIView):
    queryset = StudentCourseEnrollment.objects.all()
    serializer_class = EnrollCourseSerializer


class EnrollCourseDetail(generics.RetrieveUpdateDestroyAPIView):
    queryset = StudentCourseEnrollment.objects.all()
    serializer_class = EnrollCourseSerializer


class EnrolledStudentList(EnrolledStudents, generics.ListAPIView):
    queryset = StudentCourseEnrollment.objects.all()
    serializer_class = EnrollCourseSerializer

    def get_queryset(self):
        try:
            return self.get_course_enrolled_student()
        except Exception as e:
            pass


class EnrolledCourseList(EnrolledCourseStudent, generics.ListAPIView):
    queryset = StudentCourseEnrollment.objects.all()
    serializer_class = EnrollCourseSerializer

    def get_queryset(self):
        try:
            return self.student_enrolled_course()
        except Exception as e:
            pass


class TeacherDashboard(generics.RetrieveAPIView):

    queryset = Instructor.objects.all()
    serializer_class = TeacherDashboardSerializer


class StudentDashboard(generics.RetrieveAPIView):
    queryset = Student.objects.all()
    serializer_class = StudentDashboardSerializer


class StudentFavoriteCourseList(FavoriteCourse, generics.ListCreateAPIView):
    queryset = StudentFavoriteCourse.objects.all()
    serializer_class = FavouriteCourseSerializer

    def get_queryset(self):
        try:
            return self.student_favorite_courses()
        except Exception as e:
            pass

