from django.contrib.auth.hashers import make_password
from django.contrib.sites import requests
from django.utils import timezone
from random import randint, choice, uniform

from rest_framework.views import APIView

from ..models import *
from django.db.models import *
import random

# from ...migrate import settings


def func():
    # qs = Student.objects.select_related('Class','Class__teacher').all()
    # Results.objects.values('Class').annotate(avg_percentage=Avg('percentage'))
    # Results.objects.values('Class').annotate(avg_percentage = Avg('percentage')).filter(avg_percentage__gt=80)
    # Student.objects.all().order_by('attendance')

    # # student above class average percentage
    # sq = Results.objects.filter(Class_id = OuterRef('Class')).values('Class').annotate(avg=Avg('percentage')).values('avg')[:1]
    # oq = Results.objects.filter(percentage__gt=Subquery(sq)).values('student__Name','percentage','Class__Class_id')
    # print(len(oq))
    #
    # # Subject count per class
    # sq = Subject.objects.filter(Class_id = OuterRef('Class_id')).values('Class_id').annotate(cnt = Count('id')).values('cnt')[:1]
    # oq = Classes.objects.annotate(subject_count = Subquery(sq)).values('Class_id','subject_count')
    # print(oq)

    # avg score by subject
    sq = Results.objects.filter(subject=OuterRef('pk')).values('subject').annotate(avg=Avg('percentage')).values('avg')[
         :1]
    oq = Subject.objects.annotate(avg=Subquery(sq)).values('Name', 'Class', 'avg')
    print(oq)

    # #one to many
    # for i in Classes.objects.all():
    #     student = i.student_set.all()
    #     print(student.values())
    #
    # #many to many
    # for i in Teacher.objects.all():
    #     subject = i.subject_set.all()
    #     print(subject.values())

def populate():
    #==== 1. Create 10 Teachers (multi‑table → save individually) ====
    teacher_objs = []
    for i in range(1, 11):
        user = BaseUser.objects.create_user(
            username=f"teacher{i}",
            role=BaseUser.Role.TEACHER,
            password = "password123",
        )
        t = Teacher(
            level=choice(['p', 'h', 'l']),
            Name = f"Teacher{i}",
            experience=randint(1, 20),
            user = user
        )
        user.save()
        t.save()                 # one at a time
        teacher_objs.append(t)

    # ==== 2. Create 10 Subjects (single table → bulk) ====
    subjects = [Subject(Name=f"Subject {i}") for i in range(1, 11)]
    Subject.objects.bulk_create(subjects)
    subjects = list(Subject.objects.all())

    # ==== 3. Create 10 Classes (single table → bulk) ====
    classes = [
        Classes(Class_id=100 + i, Section=chr(65 + i), teacher=choice(teacher_objs))
        for i in range(10)
    ]
    Classes.objects.bulk_create(classes)
    classes = list(Classes.objects.all())

    # ==== 4. Map Teachers to Subjects via subject_teacher (bulk) ====
    mappings = []
    for _ in range(20):
        mappings.append(
            subject_teacher(
                subject=choice(subjects),
                teacher=choice(teacher_objs),
                rel_class=choice(classes)
            )
        )
    subject_teacher.objects.bulk_create(mappings)

    # ==== 5. Create 10 Students (multi‑table → save individually) ====
    student_objs = []
    for i in range(1, 11):
        user = BaseUser(
            username=f"student{i}",
            password="password123",
            role = BaseUser.Role.STUDENT
        )
        s = Student(
            Name=f"Student {i}",
            Class=choice(classes),
            attendance=randint(70, 100),
            status=choice(['S', 'D']),
            user = user,
            is_class_representative = random.choice([True,False]),
        )
        user.save()
        s.save()                # one at a time
        student_objs.append(s)

    # ==== 6. Create 10 Student Profiles (single table → bulk) ====
    profiles = [
        StudentProfile(
            Student=s,
            FatherName=f"Father {i}",
            MotherName=f"Mother {i}",
            FatherAge=randint(40, 55),
            MotherAge=randint(35, 50),
            Age=randint(13, 18),
            address=f"Address {i}",
            phoneNo=f"98765432{i:02}"
        )
        for i, s in enumerate(student_objs, start=1)
    ]
    StudentProfile.objects.bulk_create(profiles)

    # ==== 7. Create 20+ Results (single table → bulk) ====

    student_objs = list(Student.objects.all())
    subjects = list(Subject.objects.all())
    results = []
    seen_pairs = set()
    for student in student_objs:
        # pick 2 distinct subjects for this student
        chosen_subjects = random.sample(subjects, 2)
        for subject in chosen_subjects:
            pair = (student.user.id, subject.id)
            if pair in seen_pairs:
                continue
            seen_pairs.add(pair)
            results.append(
                Results(
                    Class=student.Class,
                    student=student,
                    subject=subject,
                    grade=randint(0, 10),
                    percentage=round(uniform(60, 100), 2)
                )
            )
    Results.objects.bulk_create(results)
    print("Data population complete.")

def correct_results():
    qs = Results.objects.all()
    for i in qs:
        if i.percentage > 90:
            i.grade = 10
        elif i.percentage >80:
            i.grade = 9
        elif i.percentage >70:
            i.grade = 8
        elif i.percentage >60:
            i.grade = 7
        elif i.percentage >50:
            i.grade = 6
        elif i.percentage>45:
            i.grade = 6
        else:
            i.grade = 0
        i.save()

def correct_passwords():
    students = Student.objects.dropped()
    for student in students:
        print(student.user.set_password("password123"))
        print(student.user.password)
        student.user.save()

def createAdmin():
    user = BaseUser(
        username="Admin",
        role=BaseUser.Role.ADMIN,
        # password="password123",
    )
    user.set_password("password123")
    user.save()


def run():
#     try:
#         JWT_URL = "127.0.0.1:8000/api/token"
#         data = {
#             "username": "teacher1",
#             "password": "password123"
#         }
#         response = requests.post(JWT_URL, json=data)
#     except Expression:
#     populate()
#     correct_passwords()
    # createAdmin()
    correct_results()


