"""Serializer class"""
from django.contrib.auth.models import User
from rest_framework import serializers
from . import models
from django.db.models import Avg


class UserSerializer(serializers.ModelSerializer):
    """Serializer for User model"""

    class Meta:
        model = User
        fields = ('username', 'first_name', 'last_name', 'email', 'password', 'is_staff')


class InstructorSerializer(serializers.ModelSerializer):
    """"Serializer"""
    user = UserSerializer(read_only=True)

    class Meta:
        """Meta Class"""
        model = models.Instructor
        fields = ('_id', 'user', 'qualification', 'designation', 'mobile_no', 'skills', 'teacher_courses')
        depth = 0

    def to_representation(self, instance):
        self.Meta.depth = 1
        return super(InstructorSerializer, self).to_representation(instance)


class SubCategorySerializer(serializers.ModelSerializer):
    """Serializer"""

    class Meta:
        """Meta Class"""
        model = models.SubCategory
        fields = ('id', 'name', 'category')


class TopicSerializer(serializers.ModelSerializer):
    """Serializer for Topic"""

    class Meta:
        """Meta Class"""
        model = models.Topic
        fields = '__all__'


class CoursePostSerializer(serializers.ModelSerializer):
    class Meta:
        model = models.Course
        fields = ('title', 'description', 'teacher', 'subcategory', 'language', 'image')


class CourseSerializer(serializers.ModelSerializer):
    """Serializer for Course"""
    topic_videos = TopicSerializer(read_only=True, many=True)
    total_enrolled_students = serializers.SerializerMethodField(read_only=True)
    course_rating = serializers.SerializerMethodField(read_only=True)
    teacher = InstructorSerializer(read_only=True)

    class Meta:
        """Meta Class"""
        model = models.Course
        fields = ('id', 'title', 'description', 'teacher', 'subcategory', 'language', 'image', 'topic_videos',
                  'total_enrolled_students', 'course_rating')

    def __init__(self, *args, **kwargs):
        super(CourseSerializer, self).__init__(*args, **kwargs)
        request = self.context.get('request')
        self.Meta.depth = 0

    def to_representation(self, instance):
        self.Meta.depth = 2
        return super(CourseSerializer, self).to_representation(instance)

    def get_total_enrolled_students(self, obj):
        total_enrolled_students = models.StudentCourseEnrollment.objects.filter(course=obj).count()
        return total_enrolled_students

    def get_course_rating(self, obj):
        course_rating = models.CourseRating.objects.filter(course=obj).aggregate(models.Avg('rating'))['rating__avg']
        return course_rating


class StudentSerializer(serializers.ModelSerializer):
    """Serializer for Student"""

    class Meta:
        """Meta Class"""
        model = models.Student
        fields = '__all__'
        depth = 0

    def to_representation(self, instance):
        self.Meta.depth = 1
        return super(StudentSerializer, self).to_representation(instance)


class CourseRatingSerializer(serializers.ModelSerializer):
    """Serializer for Course Rating"""

    class Meta:
        """Meta Class"""
        model = models.CourseRating
        fields = '__all__'

    def __init__(self, *args, **kwargs):
        super(CourseRatingSerializer, self).__init__(*args, **kwargs)
        request = self.context.get('request')
        self.Meta.depth = 0

    def to_representation(self, instance):
        self.Meta.depth = 2
        return super(CourseRatingSerializer, self).to_representation(instance)


class EnrollCourseSerializer(serializers.ModelSerializer):
    """Serializer for Enroll Course"""

    class Meta:
        """Meta Class"""
        model = models.StudentCourseEnrollment
        fields = ('course', 'student', 'enrolled_time')
        depth = 3


class EnrollCourseSerializerPost(serializers.ModelSerializer):
    """Serializer for Enroll Course"""

    class Meta:
        """Meta Class"""
        model = models.StudentCourseEnrollment
        fields = ('course', 'student', 'enrolled_time')
        depth = 0


class TeacherDashboardSerializer(serializers.ModelSerializer):
    """Serializer for Teacher Dashboard """
    total_teacher_courses = serializers.SerializerMethodField(read_only=True)
    total_teacher_chapters = serializers.SerializerMethodField(read_only=True)
    total_teacher_students = serializers.SerializerMethodField(read_only=True)

    class Meta:
        """Meta Class"""
        model = models.Instructor
        fields = ['total_teacher_courses', 'total_teacher_chapters', 'total_teacher_students']

    def get_total_teacher_courses(self, obj):
        total_course = models.Course.objects.filter(teacher=obj).count()
        return total_course

    def get_total_teacher_chapters(self, obj):
        total_chapters = models.Topic.objects.filter(course__teacher=obj).count()
        return total_chapters

    def get_total_teacher_students(self, obj):
        total_students = models.StudentCourseEnrollment.objects.filter(course__teacher=obj).count()
        return total_students


class StudentDashboardSerializer(serializers.ModelSerializer):
    """serializer for student dashboard"""
    total_enrolled_courses = serializers.SerializerMethodField(read_only=True)
    total_favorite_courses = serializers.SerializerMethodField(read_only=True)

    class Meta:
        """Meta Class"""
        model = models.Student
        fields = ['total_enrolled_courses', 'total_favorite_courses']

    def get_total_enrolled_courses(self, obj):
        total_enrolled_course = models.StudentCourseEnrollment.objects.filter(student=obj).count()
        return total_enrolled_course

    def get_total_favorite_courses(self, obj):
        total_favourite_courses = models.StudentFavoriteCourse.objects.filter(student=obj).count()
        return total_favourite_courses


class FavouriteCourseSerializer(serializers.ModelSerializer):
    """Serializer for Favourite Course model"""

    class Meta:
        """Meta Class"""
        model = models.StudentFavoriteCourse
        fields = '__all__'

    #     depth = 0
    #
    # def to_representation(self, instance):
    #     self.Meta.depth = 2
    #     return super(FavouriteCourseSerializer, self).to_representation(instance)

    def __init__(self, *args, **kwargs):
        super(FavouriteCourseSerializer, self).__init__(*args, **kwargs)
        request = self.context.get('request')
        self.Meta.depth = 0
        if request and request.method == 'GET':
            self.Meta.depth = 3
