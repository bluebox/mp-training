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
    ResultsSerializer, StudentResultsDashboard, StudentDetailsSerializer
from .models import *
from .templates.permissions.StudentPermissions import IsStudent
from .templates.permissions.TeacherPermissions import IsTeacher


class TeacherViewSet(ModelViewSet):
    permission_classes = [IsAuthenticated]
    authentication_classes = [JWTAuthentication]
    model = Teacher
    queryset = Teacher.objects.all()
    serializer_class = TeacherSerializer
    pagination_class = rest_framework.pagination.PageNumberPagination

class StudentDetails(APIView):
    permission_classes = [IsTeacher]
    def get(self,request):
        params = request.query_params
        if len(params) == 0:
            return Response(status=status.HTTP_400_BAD_REQUEST)
        else:
            qs = Student.objects.filter(id=params['id'])
            serializer = StudentDetailsSerializer(qs, many=True)
            return Response(serializer.data)


class StudentsView(APIView):
    permission_classes = [IsTeacher]
    authentication_classes = [JWTAuthentication]
    def get(self,request):
        params = request.query_params
        if len(params) == 0:
            qs = Student.objects.all()
            paginator = PageNumberPagination()
            result_page = paginator.paginate_queryset(qs, request)
            serializer = StudentSerializer(result_page, many=True)
            return paginator.get_paginated_response(serializer.data)
        else:
            if 'name' in params:
                qs = Student.objects.all().filter(name=params['name'])
                serializer = StudentSerializer(qs, many=True)
                serializer.data["result"] = serializer.data
                return Response(serializer.data)
            elif 'id' in params:
                qs = Student.objects.get(user_id=params['id'])
                serializer = StudentSerializer(qs)
                serializer.data["result"] = serializer.data
                return Response(serializer.data)
            else:
                return Response({'message':'Please provide a name or id'})
            # http://127.0.0.1:8000/Student/?id=2&name='john doe'
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
        if(len(params)==0):
            return Response(status=400)
        else:
            if 'id' in params:
                Student.objects.filter(id=params['id']).delete()
                return Response(status=204)
            else:
                return Response(status=400)


class SubjectViewSet(ModelViewSet):
    Permission_classes = [IsAuthenticated]
    authentication_classes = [JWTAuthentication]
    model = Subject
    serializer_class = SubjectSerializer
    queryset = Subject.objects.all()
    pagination_class = rest_framework.pagination.PageNumberPagination

class ClassesViewSet(ModelViewSet):
    Permission_classes = [IsAuthenticated]
    authentication_classes = [JWTAuthentication]
    model = Classes
    serializer_class = ClassesSerializer
    queryset = Classes.objects.all()
    pagination_class = rest_framework.pagination.PageNumberPagination

class ResultsViewSet(ModelViewSet):
    Permission_classes = [IsAuthenticated]
    authentication_classes = [JWTAuthentication]
    model = Results
    serializer_class = ResultsSerializer
    queryset = Results.objects.all()
    pagination_class = rest_framework.pagination.PageNumberPagination




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
        params = request.query_params
        if "id" in params:
            try:
                student = Student.objects.select_related('Class').prefetch_related(Prefetch('Class__subject_teacher_set',
                                                                                              queryset=subject_teacher.objects.select_related('subject','teacher'),to_attr="subject_teachers" )).get(user_id=request.user.id)
                subjects = []
                for i in student.Class.subject_teachers:
                    subjects.append({
                        "subject":i.subject.Name,
                        "teacher":i.teacher.Name,
                    })
                return Response({subjects})
            except Student.DoesNotExist:
                return Response({"error:Student Not found"},status=404)