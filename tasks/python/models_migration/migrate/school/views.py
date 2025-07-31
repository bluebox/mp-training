import json
from collections import defaultdict

import rest_framework
from django.shortcuts import render
from django.db.models import *
from rest_framework import status
from rest_framework.pagination import PageNumberPagination
from rest_framework.permissions import IsAuthenticated
from rest_framework.viewsets import ModelViewSet
from rest_framework.response import Response
from rest_framework.views import APIView
from rest_framework_simplejwt.authentication import JWTAuthentication

from .model_serializers import StudentSerializer, TeacherSerializer, SubjectSerializer, ClassesSerializer, \
    ResultsSerializer, StudentProfileSerializer, TeacherSubjectSerializer, StudentDetailsSerializer
from .models import *
from .templates.permissions.AdminPermissions import CustomStudentTablePermissions, IsAdmin, IsAdminOrTeacher
from .templates.permissions.StudentPermissions import IsStudent
from .templates.permissions.TeacherPermissions import IsTeacher


class TeacherViewSet(ModelViewSet):
    permission_classes = [CustomStudentTablePermissions]
    authentication_classes = [JWTAuthentication]
    model = Teacher
    queryset = Teacher.objects.all()
    serializer_class = TeacherSerializer
    pagination_class = rest_framework.pagination.PageNumberPagination


class AllTeachersWp(APIView):
    permission_classes = [IsAdmin]

    def get(self, request):
        class_teachers = Classes.objects.select_related('teacher').values('teacher__user_id')
        teachers = list(Teacher.objects.values())
        class_teachers_set = set()
        for val in class_teachers:
            class_teachers_set.add(val['teacher__user_id'])
        for i in teachers:
            if i["user_id"] in class_teachers_set:
                i["Class_teacher"] = True
            else:
                i["Class_teacher"] = False
        print(teachers)
        # serializer = StudentSerializer(result_page, many=True)
        return Response(list(teachers),status=200)

class AllTeachers(APIView):
    permission_classes = [IsAdmin]
    def get(self,request):
        class_teachers = Classes.objects.select_related('teacher').values('teacher__user_id')
        teachers = list(Teacher.objects.values())
        class_teachers_set = set()
        for val in class_teachers:
            class_teachers_set.add(val['teacher__user_id'])
        for i in teachers:
            if i["user_id"] in class_teachers_set:
                i["Class_teacher"] =True
            else:
                i["Class_teacher"] = False
        print(teachers)
        paginator = PageNumberPagination()
        result_page = paginator.paginate_queryset(teachers, request)
        # serializer = StudentSerializer(result_page, many=True)
        return paginator.get_paginated_response(result_page)
        # return Response(list(teachers),status=200)

class SubjectTeacher(APIView):
    permission_classes = [IsAdmin]
    def get(self,request):
        subject_teachers = Teacher.objects.prefetch_related('subject_teacher').values('user_id',"Name","subject_teacher__subject__id","subject_teacher__subject__Name")
        print(subject_teachers)
        teachers_dict = dict()
        for teacher in subject_teachers:
            print(teacher)
            if teacher['user_id'] in teachers_dict:
                teachers_dict[teacher['user_id']]['subjects'].append({teacher[ 'subject_teacher__subject__id']:teacher['subject_teacher__subject__Name']})
            else:
                teachers_dict[teacher['user_id']] = {}
                print(teachers_dict)
                teachers_dict[teacher['user_id']]['Name'] = teacher['Name']
                teachers_dict[teacher['user_id']]['subjects'] = [{teacher[ 'subject_teacher__subject__id']:teacher['subject_teacher__subject__Name']}]
        print(teachers_dict)
        return Response(teachers_dict,status=200)




# class StudentDetails(APIView):
#     permission_classes = [IsStudent]
#     def get(self,request):
#         params = request.query_params
#         if len(params) == 0:
#             return Response(status=status.HTTP_400_BAD_REQUEST)
#         else:
#             student = Student.objects.select_related('studentprofile').get(pk=params['id'])
#             details = student.studentprofile
#             data = {
#                 "Name": student.Name,
#                 "Class": student.Class.Class_id,
#                 "Section": student.Class.Section,
#                 "attendance": student.attendance,
#                 "status": student.status,
#                 "FatherName": details.FatherName,
#                 "MotherName": details.MotherName,
#                 "FatherAge": details.FatherAge,
#                 "MotherAge": details.MotherAge,
#                 "Age": details.Age,
#                 "address": details.address,
#                 "phoneNo": details.phoneNo
#             }
#             return Response(data)
#     def post(self,request):
#         params = request.query_params
#         if len(params) == 0:
#             return Response(status=status.HTTP_400_BAD_REQUEST)
#         else:
#             # print(request.data)
#             # return Response(status=status.HTTP_400_BAD_REQUEST)
class StudentDetails(APIView):
    permission_classes = [IsStudent]
    authentication_classes = [JWTAuthentication]

    def get(self, request):
        params = request.query_params
        if "id" not in params:
            return Response({"error": "Missing id param"}, status=400)
        try:
            student = Student.objects.select_related("studentprofile", "Class").get(pk=params["id"])
            # serealizer = StudentDetailsSerializer(student)
            # return Response(serealizer.data,status=200)
        except Student.DoesNotExist:
            return Response({"error": "Student not found"}, status=404)

        details = student.studentprofile
        data = {
            "Name": student.Name,
            "Class": student.Class.Class_id,
            "Section": student.Class.Section,
            "attendance": student.attendance,
            "status": student.status,
            "FatherName": details.FatherName,
            "MotherName": details.MotherName,
            "FatherAge": details.FatherAge,
            "MotherAge": details.MotherAge,
            "Age": details.Age,
            "address": details.address,
            "phoneNo": details.phoneNo
        }
        return Response(data)

    def put(self, request):
        params = request.query_params
        if "id" not in params:
            return Response({"error": "Missing id param"}, status=400)
        try:
            student = Student.objects.select_related("studentprofile").get(pk=params["id"])
        except Student.DoesNotExist:
            return Response({"error": "Student not found"}, status=404)

        serializer = StudentProfileSerializer(student.studentprofile, data=request.data, partial=False)
        if serializer.is_valid():
            serializer.save()
            return Response({"message": "Profile updated successfully", "data": serializer.data})
        else:
            return Response(serializer.errors, status=400)
class ALLStudentsView(APIView):
    permission_classes = [CustomStudentTablePermissions]
    authentication_classes = [JWTAuthentication]
    def get(self, request):
        params = request.query_params
        qs = Student.objects.all()

        # Apply filtering if name or id is provided
        if 'name' in params:
            qs = qs.filter(name=params['name'])
        if 'id' in params:
            qs = qs.filter(user_id=params['id'])
        serializer = StudentSerializer(qs,many=True)
        return Response(serializer.data)

class StudentsView(APIView):
    # permission_classes = [CustomStudentTablePermissions]
    # authentication_classes = [JWTAuthentication]
    # def get(self,request):
    #     params = request.query_params
    #     if len(params) == 0:
    #         qs = Student.objects.all()
    #         paginator = PageNumberPagination()
    #         result_page = paginator.paginate_queryset(qs, request)
    #         serializer = StudentSerializer(result_page, many=True)
    #         return paginator.get_paginated_response(serializer.data)
    #     else:
    #         if 'name' in params:
    #             qs = Student.objects.all().filter(name=params['name'])
    #             serializer = StudentSerializer(qs, many=True)
    #             serializer.data["result"] = serializer.data
    #             return Response(serializer.data)
    #         elif 'id' in params:
    #             qs = Student.objects.get(user_id=params['id'])
    #             serializer = StudentSerializer(qs)
    #             serializer.data["result"] = serializer.data
    #             return Response(serializer.data)
    #         else:
    #             return Response({'message':'Please provide a name or id'})
    #         # http://127.0.0.1:8000/Student/?id=2&name='john doe'
    permission_classes = [CustomStudentTablePermissions]
    authentication_classes = [JWTAuthentication]

    def get(self, request):
        params = request.query_params
        qs = Student.objects.all()

        # Apply filtering if name or id is provided
        if 'name' in params:
            qs = qs.filter(name=params['name'])
        if 'id' in params:
            qs = qs.filter(user_id=params['id'])

        paginator = PageNumberPagination()
        result_page = paginator.paginate_queryset(qs, request)
        serializer = StudentSerializer(result_page, many=True)
        return paginator.get_paginated_response(serializer.data)
    def post(self,request):
        print(request.data)
        serializer = StudentSerializer(data=request.data)
        print("in post ")
        if serializer.is_valid():
            print("yes")
            serializer.save()
            return Response(serializer.data)
        else:
            print("no")
            return Response(serializer.errors)

    def patch(self,request):
        params = request.query_params
        if 'id' in params:
            change_id = params['id']
            try:
                s = Student.objects.get(user_id=change_id)
            except Exception:
                return Response({'message':'Student does not exist'})
            serializer = StudentSerializer(s,data=request.data,partial=True)
            if serializer.is_valid():
                serializer.save()
                return Response(serializer.data)
            else:
                return Response(serializer.errors)
        else:
            return Response({'message':'Please provide and id'},status=400)

    def put(self,request):
        params = request.query_params
        if 'id' in params:
            change_id = params.get('id')
            try:
                s = Student.objects.get(user_id=change_id)
            except Exception:
                return Response({'message':'Student does not exist'})
            serializer = StudentSerializer(s,data=request.data)
            if serializer.is_valid():
                serializer.save()
                serializer.data["result"] = serializer.data
                return Response(serializer.data,status=200)
            else:
                return Response(serializer.errors)
        else:
            return Response({'message':'Please provide an id'})

    def delete(self,request):
        params = request.query_params
        print(params)
        if len(params)==0:
            return Response(status=400)
        else:
            if 'id' in params:
                Student.objects.filter(id=params['id']).delete()
                return Response(status=204)
            else:
                return Response(status=403)


class TeacherResultsView(APIView):
    permission_classes = [IsTeacher]
    def get(self,request):
        params = request.query_params
        print("params",params)
        if 'id' not in params or 'sub_id' not in params:
            return Response({'message':'Provide both id and sub_id'},status=400)
        else:
            qs = list(Results.objects.select_related('student','subject').prefetch_related('subject__subject_teacher_set').filter(Q(subject=params['sub_id'])&Q(subject__subject_teacher__teacher = params['id'])).values('id','Class','student__user_id','student__Name','subject__Name','grade','percentage').distinct())
            print(qs)
            paginator = PageNumberPagination()
            result_page = paginator.paginate_queryset(qs, request)
            # serializer = StudentSerializer(result_page, many=True)
            return paginator.get_paginated_response(result_page)

class AllSubjectsView(APIView):
    permission_classes = [IsAdmin]
    authentication_classes = [JWTAuthentication]
    def get(self,request):
        qs = Subject.objects.all()

        serializer = SubjectSerializer(qs,many=True)
        return Response(serializer.data,status=200)

class SubjectViewSet(ModelViewSet):
    permission_classes = [IsAdminOrTeacher]
    authentication_classes = [JWTAuthentication]
    model = Subject
    serializer_class = SubjectSerializer
    queryset = Subject.objects.all()
    pagination_class = rest_framework.pagination.PageNumberPagination

class AllClassesView(APIView):
    permission_classes = [CustomStudentTablePermissions]
    authentication_classes = [JWTAuthentication]
    def get(self,request):
        qs = Classes.objects.all()
        print(qs)
        serializer = ClassesSerializer(qs,many=True)
        return Response(serializer.data,status=200)

class ClassesViewSet(ModelViewSet):
    permission_classes = [CustomStudentTablePermissions]
    authentication_classes = [JWTAuthentication]
    model = Classes
    serializer_class = ClassesSerializer
    queryset = Classes.objects.all()
    pagination_class = rest_framework.pagination.PageNumberPagination

class ResultsViewSet(ModelViewSet):
    permission_classes = [IsAuthenticated]
    authentication_classes = [JWTAuthentication]
    model = Results
    serializer_class = ResultsSerializer
    queryset = Results.objects.all()
    pagination_class = rest_framework.pagination.PageNumberPagination
#
# class TeacherStudentResults(APIView):
#     permission_classes = [IsTeacher]
#     def get(self,request):



class TeacherSubjectsStudents(APIView):
    permission_classes = [IsTeacher]
    def get(self,request):
        params = request.query_params
        if 'id' in params:
            try:
                subject_ids = list(subject_teacher.objects.select_related('teacher','subject').filter(teacher__user_id = params['id']).values_list('subject_id',flat=True))
                if not subject_ids:
                    return Response({},status=200)
                students = Student.objects.filter(
                    Class__subject_teacher_set__subject_id__in=subject_ids,
                    Class__subject_teacher_set__teacher__user_id=params['id']
                ).values(
                    "user_id",
                    "Class_id",
                    "Class__subject_teacher_set__subject_id"
                )
                print(students)
                response = []
                class_id = set()
                subject_id = defaultdict(set)
                user_id = defaultdict(set)
                for student in students:
                    class_id.add(student['Class_id'])
                    subject_id[student['Class_id']].add(student['Class__subject_teacher_set__subject_id'])
                    user_id[student['Class_id']].add(student['user_id'])
                for cid in class_id:
                    response_item = {'class_id':cid,'subject_id':list(subject_id[cid]),'students':list(user_id[cid])}
                    response.append(response_item)
                print("response",response)
                return Response(response, status=200)
            except Expression as ex:
                print("Exception",ex)
                return Response(status=400)
        else:
            return Response(status=400)


class StudentResultsDashBoard(APIView):
    permission_classes = [IsStudent]
    def get(self,request):
        params = request.query_params
        if 'id' in params:
            try:
                student = Student.objects.prefetch_related("results_set").filter(user_id=params['id'])
                # serializer = CustomSerializer(student)
            except Student.DoesNotExist:
                return Response({'message': 'Student does not have any result'}, status=404)

            if not student:
                return Response({'message': 'Student does not have any result'}, status=404)
            else:
                # serializer = StudentResultsDashboard(student)
                return Response(student.values("user_id","Name","results__subject_id","results__grade","results__subject_id__Name","results__percentage"))

        else:
            return Response({'message':'Please provide an id'},status=400)

class StudentSubjectsDashboard(APIView):
    permission_classes = [IsStudent]

    def get(self, request):
        try:
            student = Student.objects.select_related('Class').prefetch_related(
                Prefetch(
                    'Class__subject_teacher_set',
                    queryset=subject_teacher.objects.select_related('subject', 'teacher'),
                    to_attr="subject_teachers"
                )
            ).get(user_id=request.user.id)
            subjects = [
                {
                    "subject": st.subject.Name,
                    "teacher": st.teacher.Name,
                }
                for st in student.Class.subject_teachers
            ]
            return Response({"subjects": subjects}, status=status.HTTP_200_OK)

        except Student.DoesNotExist:
            return Response({"error": "Student not found"}, status=status.HTTP_404_NOT_FOUND)

class TeacherClass(APIView):
    permission_classes = [IsAdminOrTeacher]
    def get(self,request):
        try:
            params = request.query_params
            if 'id' in params:
                teachers = Classes.objects.select_related('teacher').filter(id=params['id']).values('teacher__user_id', 'teacher__Name',
                                                                            'Class_id', 'Section', 'id')
            else:
                teachers = Classes.objects.select_related('teacher').values('teacher__user_id','teacher__Name','Class_id','Section','id')
            print(teachers)
            # teachers = list(teachers)
            data =[]
            for teacher in teachers:
                data.append(
                    {
                        'id':teacher['id'],
                        'teacher_id':teacher['teacher__user_id'],
                        'name':teacher['teacher__Name'],
                        'class':teacher['Class_id'],
                        'section':teacher['Section']
                    }
                )
            return Response(data,status=200)
        except Exception as e:
            print("Error",e)

# class SubjectTeacherRelationView(ModelViewSet):
#     permission_classes = [CustomStudentTablePermissions]
#     authentication_classes = [JWTAuthentication]
#     model = Subject
#     serializer_class = TeacherSubjectSerializer
#     queryset = subject_teacher.objects.all()



class SubjectTeacherRelationView(APIView):
    permission_classes = [CustomStudentTablePermissions]

    def get(self, request):
        params = request.query_params
        if 'id' in params:
            qs = subject_teacher.objects.select_related('subject', 'teacher', 'rel_class') \
                .filter(teacher__user_id=params['id']) \
                .values('id','subject__id', 'subject__Name', 'rel_class__Class_id', 'rel_class__Section',
                        'teacher__user_id', 'teacher__Name')
        else:
            qs = subject_teacher.objects.select_related('subject', 'teacher', 'rel_class') \
                .values('id','subject__id', 'subject__Name', 'rel_class__Class_id', 'rel_class__Section',
                        'teacher__user_id', 'teacher__Name')


        data = [{
            'id': sub['id'],
            'subject_id': sub['subject__id'],
            'subject_name': sub['subject__Name'],
            'class_id': sub['rel_class__Class_id'],
            'section': sub['rel_class__Section'],
            'teacher_id': sub['teacher__user_id'],
            'teacher_name': sub['teacher__Name']
        } for sub in qs]
        paginator = PageNumberPagination()
        result_page = paginator.paginate_queryset(data, request)
        # serializer = StudentSerializer(result_page, many=True)
        return paginator.get_paginated_response(result_page)

        # return Response(data, status=200)

    def post(self, request):
        serializer = TeacherSubjectSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=201)
        else:
            return Response(serializer.errors, status=400)
    def delete(self, request):
        params = request.query_params
        if 'id' in params:
            try:
                subject_teacher.objects.get(id=params['id']).delete()
                return Response(data={"message":"Record deleted successfully"},status=200)
            except Expression as e:
                return Response(data={"message":"Error in deletion"}, status=500)
        else:
            return Response(data={"message":"Did not receive id in params"}, status=400)






