from django.contrib.auth import authenticate
from django.db.models.query_utils import Q
from rest_framework.decorators import api_view, permission_classes
from rest_framework.permissions import IsAdminUser
from rest_framework.response import Response
from rest_framework.status import HTTP_400_BAD_REQUEST
from rest_framework_simplejwt.serializers import TokenObtainPairSerializer
from rest_framework_simplejwt.tokens import RefreshToken
from rest_framework_simplejwt.views import TokenObtainPairView

from project.models import Instructor, Student, StudentCourseEnrollment, Course, CourseRating, StudentFavoriteCourse, \
    Topic
from project.serializers import UserSerializer
from django.contrib.auth.models import User
from django.http import JsonResponse
from django.views.decorators.csrf import csrf_exempt



@api_view(['GET'])
@permission_classes([IsAdminUser])
def getUser(request):
    try:
        user = request.user
    except User.DOESNOTEXIST:
        return {'bool': False}
    serializer = UserSerializer(user, many=False)
    return Response(serializer.data)


class MyTokenObtainPairSerializer(TokenObtainPairSerializer):
    def validate(self, attrs):
        username = attrs.get('username')
        password = attrs.get('password')
        user = authenticate(username=username, password=password)
        if not user:
            return {'bool': False}
        data = super().validate(attrs)
        if self.user.is_staff:
            data['staff'] = True
            try:
                instructor = Instructor.objects.get(user=self.user)
            except:
                return {'bool': False}

            data['_id'] = instructor._id
            data['id'] = user.id
        else:
            data['staff'] = False
            try:
                student = Student.objects.get(user=self.user)
            except:
                return {'bool': False}
            data['_id'] = student._id
            data['id'] = user.id
        data['bool'] = True,
        return data


class MyTokenObtainPairView(TokenObtainPairView):
    serializer_class = MyTokenObtainPairSerializer

@csrf_exempt
def ins_register(request):
    try:
        if request.method == 'POST':
            data = request.POST
            username = data['username']
            email = data['email']
            password = data['password']
            first_name = data['first_name']
            last_name = data['last_name']
            is_staff = True
            user = User.objects.create_user(username=username, email=email, password=password,
                                            first_name=first_name, last_name=last_name, is_staff=is_staff)
            user.save()

            # create the instructor object
            instructor = Instructor(user=user, qualification=data['qualification'],
                                    designation=data['designation'], mobile_no=data['mobile_no'],
                                    skills=data['skills'])
            instructor.save()
            return JsonResponse("Instructor Data Added Successfully", safe=False)
        return JsonResponse({'success': False})
    except Exception as e:
        return JsonResponse({'bool', False})

@csrf_exempt
def stu_register(request):
    try:
        if request.method == 'POST':
            data = request.POST
            username = data['username']
            email = data['email']
            password = data['password']
            first_name = data['first_name']
            last_name = data['last_name']
            is_staff = False
            user = User.objects.create_user(username=username, email=email, password=password,
                                            first_name=first_name, last_name=last_name, is_staff=is_staff)
            user.save()

            student = Student(user=user, qualification=data['qualification'],
                              interested_category=data['interested_category'], mobile_no=data['mobile_no'],
                              address=data['address'])
            student.save()
            return JsonResponse("Student Data Added Successfully", safe=False)
        return JsonResponse("Student Data failed to add", safe=False)
    except Exception as e:
         return JsonResponse({'bool', False})

@csrf_exempt
def stu_register(request):
    try:
        if request.method == 'POST':
            data = request.POST
            username = data['username']
            email = data['email']
            password = data['password']
            first_name = data['first_name']
            last_name = data['last_name']
            is_staff = False
            user = User.objects.create_user(username=username, email=email, password=password,
                                            first_name=first_name, last_name=last_name, is_staff=is_staff)
            user.save()

            student = Student(user=user, qualification=data['qualification'],
                              interested_category=data['interested_category'], mobile_no=data['mobile_no'],
                              address=data['address'])
            student.save()
            return JsonResponse("Student Data Added Successfully", safe=False)
        return JsonResponse("Student Data failed to add", safe=False)

    except Exception as e:
        return JsonResponse({'bool', False})




@csrf_exempt
def fetch_enroll_status(request, user_id, course_id):
    try:
        student = Student.objects.filter(_id=user_id).first()
        course = Course.objects.filter(id=course_id).first()
        enrollStatus = StudentCourseEnrollment.objects.filter(course=course, student=student).count()
        if enrollStatus:
            return JsonResponse({'bool': True})
        else:
            return JsonResponse({'bool': False})
    except Exception as e:
        return JsonResponse({'bool': False})



@csrf_exempt
def fetch_rating_status(request, user_id, course_id):
    try:
        student = Student.objects.filter(_id=user_id).first()
        course = Course.objects.filter(id=course_id).first()
        ratingStatus = CourseRating.objects.filter(course=course, student=student).count()
        if ratingStatus:
            return JsonResponse({'bool': True})
        else:
            return JsonResponse({'bool': False})
    except Exception as e:
        return JsonResponse({'bool': False})


@csrf_exempt
def fetch_favorite_status(request, student_id, course_id):
    try:
        student = Student.objects.filter(_id=student_id).first()
        course = Course.objects.filter(id=course_id).first()
        favoriteStatus = StudentFavoriteCourse.objects.filter(course=course, student=student).first()
        if favoriteStatus:
            return JsonResponse({'bool': True})
        else:
            return JsonResponse({'bool': False})
    except Exception as e:
        return JsonResponse({'bool', False})


@csrf_exempt
def remove_favorite_course(request, student_id, course_id):
    try:
        student = Student.objects.filter(id=student_id).first()
        course = Course.objects.filter(id=course_id).first()
        favoriteStatus = StudentFavoriteCourse.objects.filter(course=course, student=student).delete()
        if favoriteStatus:
            return JsonResponse({'bool': True})
        else:
            return JsonResponse({'bool': False})
    except Exception as e:
        return JsonResponse({'bool', False})


class CourseRatingManager:
    @staticmethod
    def get_queryset():
        try:
            return CourseRating.objects.filter(course__isnull=False).order_by('-id')
        except Exception as e:
            return JsonResponse({'bool', False})


class AllCourses:
    def get_queryset(self):
        queryset = super().get_queryset()
        try:
            if 'result' in self.request.GET:
                limit = int(self.request.GET['result'])
                queryset = queryset.order_by('-id')[:limit]
            return queryset
        except Exception as e:
            return JsonResponse({'bool', False})


class TeacherCourses:
    def get_teacher_courses(self):
        try:
            teacher_id = self.kwargs['id']
            course_teacher = Course.objects.filter(teacher=teacher_id)
            return course_teacher
        except Exception as e:
            return JsonResponse({'bool', False})


class TopicVideoList:
    def get_topic_videos_list(self):
        try:
            course_id = self.kwargs['course_id']
            course = Course.objects.get(pk=course_id)
            teacher_course_topic = Topic.objects.filter(course=course)
            return teacher_course_topic
        except Exception as e:
            return JsonResponse({'bool', False})


class EnrolledStudents:
    def get_course_enrolled_student(self):
        try:
            if 'course_id' in self.kwargs:
                course_id = self.kwargs['course_id']
                course = Course.objects.get(pk=course_id)
                enrolled_students = StudentCourseEnrollment.objects.filter(course=course)
                return enrolled_students
            else:
                enrolled_students = StudentCourseEnrollment.objects.distinct()
                return enrolled_students
        except Exception as e:
            return JsonResponse({'bool', False})


class EnrolledCourseStudent:
    def student_enrolled_course(self):
        try:
            student_id = self.kwargs['student_id']
            student = Student.objects.get(pk=student_id)
            enrolled_courses = StudentCourseEnrollment.objects.filter(student=student)
            return enrolled_courses
        except Exception as e:
            return JsonResponse({'bool', False})


class FavoriteCourse:
    def student_favorite_courses(self):
        try:
            if 'student_id' in self.kwargs:
                student_id = self.kwargs['student_id']
                student = Student.objects.get(pk=student_id)
                favourite_course = StudentFavoriteCourse.objects.filter(student=student).distinct()
                return favourite_course
            else:
                favourite_course = StudentFavoriteCourse.objects.distinct()
                return favourite_course
        except Exception as e:
            return JsonResponse({'bool', False})


def filter_course_by_title(search):
    try:
        qs = Course.objects.filter(Q(title__icontains=search))
        return qs
    except Exception as e:
        return JsonResponse({'bool', False})
