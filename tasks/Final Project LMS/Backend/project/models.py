"""Create Models for application"""
from django.db import models
from django.core import serializers
from django.db.models import Aggregate


def upload_to(instance, filename):
    return 'course_images/{filename}'.format(filename=filename)


class Instructor(models.Model):
    """ Instructor Models"""
    id = models.AutoField(primary_key=True)
    first_name = models.CharField(max_length=25)
    last_name = models.CharField(max_length=25)
    email = models.CharField(max_length=40, unique=True)
    password = models.CharField(max_length=25, unique=True)
    qualification = models.CharField(max_length=25)
    designation = models.CharField(max_length=50)
    mobile_no = models.CharField(max_length=25, unique=True)
    skills = models.TextField()

    class Meta:
        """Data about Teacher class"""
        verbose_name_plural = "1. Instructor"

    def __str__(self):
        return f'{self.id}-{self.first_name}'


class Student(models.Model):
    """ Student Models"""
    id = models.AutoField(primary_key=True)
    first_name = models.CharField(max_length=25)
    last_name = models.CharField(max_length=25)
    email = models.CharField(max_length=40, unique=True)
    password = models.CharField(max_length=25, unique=True)
    qualification = models.CharField(max_length=25)
    interested_category = models.TextField(max_length=25)
    mobile_no = models.CharField(max_length=25, unique=True)
    address = models.TextField()

    class Meta:
        """Data about Student class"""
        verbose_name_plural = '2. Student'

    def __str__(self):
        return f'{self.id}-{self.first_name}'



class Category(models.Model):
    """ Category Models"""
    id = models.AutoField(primary_key=True)
    name = models.CharField(max_length=40, unique=True)

    class Meta:
        """Data about Category class"""
        verbose_name_plural = '3. Category'

    def __str__(self):
        return f'{self.name}'


class SubCategory(models.Model):
    """"Sub-Category Model"""
    id = models.AutoField(primary_key=True)
    name = models.CharField(max_length=100, unique=True)
    category = models.ForeignKey(Category, on_delete=models.CASCADE)

    class Meta:
        """Data about Sub_Category class"""
        verbose_name_plural = '4. SubCategory'

    def __str__(self):
        return f'{self.name} - {self.category.name}'


class Course(models.Model):
    """Courses Model"""
    id = models.AutoField(primary_key=True)
    title = models.CharField(max_length=120)
    description = models.TextField()
    teacher = models.ForeignKey(Instructor, on_delete=models.CASCADE, related_name='teacher_courses')
    subcategory = models.ForeignKey(SubCategory, on_delete=models.CASCADE)
    language = models.CharField(max_length=20)
    image = models.ImageField(upload_to=upload_to, null=True)

    class Meta:
        """Data about Course class"""
        verbose_name_plural = '5. Course'

    def __str__(self):
        return f'{self.id}-{self.title}'



class Topic(models.Model):
    """Topic Model"""
    id = models.AutoField(primary_key=True)
    title = models.CharField(max_length=120)
    course = models.ForeignKey(Course, on_delete=models.CASCADE, related_name='topic_videos')
    url = models.TextField()

    class Meta:
        """Data about Topics class"""
        verbose_name_plural = '6. Topic'

    def __str__(self):
        return f'{self.title}-{self.course}'


# Student course enrollment
class StudentCourseEnrollment(models.Model):
    id = models.AutoField(primary_key=True)
    course = models.ForeignKey(Course, on_delete=models.CASCADE, related_name='enrolled_courses')
    student = models.ForeignKey(Student, on_delete=models.CASCADE, related_name='enrolled_students')
    enrolled_time = models.DateTimeField(auto_now_add=True)

    class Meta:
        """Data about Student Course Enrollment model"""
        verbose_name_plural = '7.Enrolled Course'

    def __str__(self):
        return f'{self.student} enrolled in {self.course}'


class CourseRating(models.Model):
    id = models.AutoField(primary_key=True)
    course = models.ForeignKey(Course, on_delete=models.CASCADE)
    student = models.ForeignKey(Student, on_delete=models.CASCADE)
    rating = models.PositiveBigIntegerField(default=0)
    review = models.TextField(null=True)

    class Meta:
        """Data about Student Course Enrollment model"""
        verbose_name_plural = '9.Course Rating'

    def __str__(self):
        return f'{self.student} reviewed {self.course}'


class StudentFavoriteCourse(models.Model):
    id = models.AutoField(primary_key=True)
    course = models.ForeignKey(Course, on_delete=models.CASCADE)
    student=models.ForeignKey(Student, on_delete=models.CASCADE)
    status=models.BooleanField(default=False)

    class Meta:
        """Data about student favorite course"""
        verbose_name_plural = '8. Favourite Course'

    def __str__(self):
        return f'{self.student} added {self.course} as favourite'


class Avg(Aggregate):
    function = 'AVG'
    name = 'Avg'

