from django.db.models.query_utils import Q
from django.http.response import JsonResponse
from django.views.decorators.csrf import csrf_exempt

from project.models import Instructor, Student, StudentCourseEnrollment, Course, CourseRating, StudentFavoriteCourse, \
    Topic


@csrf_exempt
def instructor_login(request):
    email = request.POST['email']
    password = request.POST['password']
    try:
        instructorData = Instructor.objects.get(email=email, password=password)
    except Instructor.DoesNotExist:
        instructorData = None
    if instructorData:
        return JsonResponse({'bool': True, 'teacher_id': instructorData.id})
    else:
        return JsonResponse({'bool': False})


@csrf_exempt
def student_login(request):
    email = request.POST['email']
    password = request.POST['password']
    try:
        studentData = Student.objects.get(email=email, password=password)
    except Student.DoesNotExist:
        studentData = None
    if studentData:
        return JsonResponse({'bool': True, 'student_id': studentData.id})
    else:
        return JsonResponse({'bool': False})


@csrf_exempt
def fetch_enroll_status(request, user_id, course_id):
    student = Student.objects.filter(id=user_id).first()
    course = Course.objects.filter(id=course_id).first()
    enrollStatus = StudentCourseEnrollment.objects.filter(course=course, student=student).count()
    if enrollStatus:
        return JsonResponse({'bool': True})
    else:
        return JsonResponse({'bool': False})


@csrf_exempt
def fetch_rating_status(request, user_id, course_id):
    student = Student.objects.filter(id=user_id).first()
    course = Course.objects.filter(id=course_id).first()
    ratingStatus = CourseRating.objects.filter(course=course, student=student).count()
    if ratingStatus:
        return JsonResponse({'bool': True})
    else:
        return JsonResponse({'bool': False})


@csrf_exempt
def fetch_favorite_status(request, student_id, course_id):
    student = Student.objects.filter(id=student_id).first()
    course = Course.objects.filter(id=course_id).first()
    favoriteStatus = StudentFavoriteCourse.objects.filter(course=course, student=student).first()
    if favoriteStatus:
        return JsonResponse({'bool': True})
    else:
        return JsonResponse({'bool': False})


@csrf_exempt
def remove_favorite_course(request, student_id, course_id):
    student = Student.objects.filter(id=student_id).first()
    course = Course.objects.filter(id=course_id).first()
    favoriteStatus = StudentFavoriteCourse.objects.filter(course=course, student=student).delete()
    if favoriteStatus:
        return JsonResponse({'bool': True})
    else:
        return JsonResponse({'bool': False})


class CourseRatingManager:
    @staticmethod
    def get_queryset():
        return CourseRating.objects.filter(course__isnull=False).order_by('-id')


class AllCourses:
    def get_queryset(self):
        queryset = super().get_queryset()
        if 'result' in self.request.GET:
            limit = int(self.request.GET['result'])
            queryset = queryset.order_by('-id')[:limit]
        return queryset


class TeacherCourses:
    def get_teacher_courses(self):
        teacher_id = self.kwargs['id']
        teacher = Instructor.objects.get(pk=teacher_id)
        course_teacher = Course.objects.filter(teacher=teacher)
        return course_teacher


class TopicVideoList:
    def get_topic_videos_list(self):
        course_id = self.kwargs['course_id']
        course = Course.objects.get(pk=course_id)
        teacher_course_topic = Topic.objects.filter(course=course)
        return teacher_course_topic


class EnrolledStudents:
    def get_course_enrolled_student(self):
        if 'course_id' in self.kwargs:
            course_id = self.kwargs['course_id']
            course = Course.objects.get(pk=course_id)
            enrolled_students = StudentCourseEnrollment.objects.filter(course=course)
            return enrolled_students
        else:
            enrolled_students = StudentCourseEnrollment.objects.distinct()
            return enrolled_students


class EnrolledCourseStudent:
    def student_enrolled_course(self):
        student_id = self.kwargs['student_id']
        student = Student.objects.get(pk=student_id)
        enrolled_courses = StudentCourseEnrollment.objects.filter(student=student)
        return enrolled_courses


class FavoriteCourse:
    def student_favorite_courses(self):
        if 'student_id' in self.kwargs:
            student_id = self.kwargs['student_id']
            student = Student.objects.get(pk=student_id)
            favourite_course = StudentFavoriteCourse.objects.filter(student=student).distinct()
            return favourite_course
        else:
            favourite_course = StudentFavoriteCourse.objects.distinct()
            return favourite_course


def filter_course_by_title(search):
    qs = Course.objects.filter(Q(title__icontains=search))
    return qs


