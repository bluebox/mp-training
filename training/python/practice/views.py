from django.http import HttpResponse
from django.shortcuts import render
from django.template import loader
from django_filters.rest_framework import DjangoFilterBackend
from rest_framework import status
from rest_framework.response import Response
from rest_framework.views import APIView
from django.apps import apps
from django.db.models import Q, Sum, Avg, Count, Max, Min, F
from django.core.exceptions import FieldError
from .models import Student, Professors, Courses, Enrollments, Departments, Grades
from .serializers import StudentSerializer, CourseSerializer, ProfessorSerializer, DepartmentSerializer,GradeSerializer, EnrollmentSerializer

# def tables(request):
#     template = loader.get_template('data.html')
#     students=Student.objects.all().values
#     professors=Professors.objects.all().values()
#     courses=Courses.objects.all().values()
#     enrollments=Enrollments.objects.all().values()
#     departments=Departments.objects.all().values()
#     grades=Grades.objects.all().values()
#     context={
#         'students':students ,
#         'professors':professors,
#         'courses':courses,
#         'enrollments':enrollments,
#         'departments':departments,
#         'grades':grades
#     }
#     return HttpResponse(template.render(context,request))
class StudentTable(APIView):
    def get(self,request,id=None):
        if id==None:
            students=Student.objects.all()
            serializer=StudentSerializer(students,many=True)
            if serializer:
                return Response(serializer.data,status=status.HTTP_201_CREATED)
        else:
            obj = Student.objects.get(student_id=id)
            serializer=StudentSerializer(obj)
            return Response(serializer.data,status=status.HTTP_201_CREATED)

        return Response(status=status.HTTP_400_BAD_REQUEST)
    def post(self,request):
        serializer=StudentSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data,status=status.HTTP_201_CREATED)
        return Response(serializer.errors,status=status.HTTP_400_BAD_REQUEST)
    def delete(self,request,id):
        try:
            data=Student.objects.get(student_id=id)
        except:
            return Response(status=status.HTTP_400_BAD_REQUEST)
        data.delete()
        return Response(status=status.HTTP_204_NO_CONTENT)
    def put(self,request,id):
        try:
            obj=Student.objects.get(student_id=id)
        except:
            return Response({"error":"data not found"},status=status.HTTP_400_BAD_REQUEST)
        serializer=StudentSerializer(obj,data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data,status=status.HTTP_201_CREATED)
        return Response({"error":"serialization error"},status=status.HTTP_400_BAD_REQUEST)
    def patch(self,request,id):
        try:
            obj=Student.objects.get(student_id=id)
        except:
            return Response({"error":"data not found"},status=status.HTTP_400_BAD_REQUEST)
        serializer=StudentSerializer(obj,data=request.data,partial=True)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data,status=status.HTTP_206_PARTIAL_CONTENT)
class CourseTable(APIView):
    def get(self,request,id=None):
        if id==None:
            courses=Courses.objects.all()
            serializer=CourseSerializer(courses,many=True)
            if serializer:
                return Response(serializer.data,status=status.HTTP_201_CREATED)
        else:
            obj = Courses.objects.get(course_id=id)
            serializer=CourseSerializer(obj)
            return Response(serializer.data,status=status.HTTP_201_CREATED)
        return Response(serializer.errors,status=status.HTTP_400_BAD_REQUEST)
    def post(self,request):
        serializer=CourseSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data,status=status.HTTP_201_CREATED)
        return Response(serializer.errors,status=status.HTTP_400_BAD_REQUEST)
    def delete(self,request,id):
        try:
            data=Courses.objects.get(course_id=id)
        except:
            return Response(status=status.HTTP_400_BAD_REQUEST)
        data.delete()
        return Response(status=status.HTTP_204_NO_CONTENT)
    def put(self,request,id):
        try:
            obj=Courses.objects.get(student_id=id)
        except:
            return Response({"error":"data not found"},status=status.HTTP_400_BAD_REQUEST)
        serializer=CourseSerializer(obj,data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data,status=status.HTTP_201_CREATED)
        return Response({"error":"serialization error"},status=status.HTTP_400_BAD_REQUEST)
    def patch(self,request,id):
        try:
            obj=Courses.objects.get(student_id=id)
        except:
            return Response({"error":"data not found"},status=status.HTTP_400_BAD_REQUEST)
        serializer=CourseSerializer(obj,data=request.data,partial=True)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data,status=status.HTTP_206_PARTIAL_CONTENT)
class ProfessorTable(APIView):
    def get(self,request,id=None):
        if id==None:
            professors=Professors.objects.all()
            serializer=ProfessorSerializer(professors,many=True)
            if serializer:
                return Response(serializer.data,status=status.HTTP_201_CREATED)
        else:
            obj = Professors.objects.get(professor_id=id)
            serializer=ProfessorSerializer(obj)
            return Response(serializer.data,status=status.HTTP_201_CREATED)
        return Response(serializer.errors,status=status.HTTP_400_BAD_REQUEST)
    def post(self,request):
        serializer=ProfessorSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data,status=status.HTTP_201_CREATED)
        return Response(serializer.errors,status=status.HTTP_400_BAD_REQUEST)
    def delete(self,request,id):
        try:
            data=Professors.objects.get(professor_id=id)
        except:
            return Response(status=status.HTTP_400_BAD_REQUEST)
        data.delete()
        return Response(status=status.HTTP_204_NO_CONTENT)
    def put(self,request,id):
        try:
            obj=Professors.objects.get(student_id=id)
        except:
            return Response({"error":"data not found"},status=status.HTTP_400_BAD_REQUEST)
        serializer=ProfessorSerializer(obj,data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data,status=status.HTTP_201_CREATED)
        return Response({"error":"serialization error"},status=status.HTTP_400_BAD_REQUEST)
    def patch(self,request,id):
        try:
            obj=Professors.objects.get(student_id=id)
        except:
            return Response({"error":"data not found"},status=status.HTTP_400_BAD_REQUEST)
        serializer=ProfessorSerializer(obj,data=request.data,partial=True)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data,status=status.HTTP_206_PARTIAL_CONTENT)
class DepartmentTable(APIView):
    def get(self,request,id=None):
        if id==None:
            departments=Departments.objects.all()
            serializer=DepartmentSerializer(departments,many=True)
            if serializer:
                return Response(serializer.data,status=status.HTTP_201_CREATED)
        else:
            obj = Departments.objects.get(department_id=id)
            serializer=DepartmentSerializer(obj)
            return Response(serializer.data,status=status.HTTP_201_CREATED)
        return Response(serializer.errors,status=status.HTTP_400_BAD_REQUEST)
    def post(self,request):
        serializer=DepartmentSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data,status=status.HTTP_201_CREATED)
        return Response(serializer.errors,status=status.HTTP_400_BAD_REQUEST)
    def delete(self,request,id):
        try:
            data=Departments.objects.get(department_id=id)
        except:
            return Response(status=status.HTTP_400_BAD_REQUEST)
        data.delete()
        return Response(status=status.HTTP_204_NO_CONTENT)
    def put(self,request,id):
        try:
            obj=Departments.objects.get(department_id=id)
        except:
            return Response({"error":"data not found"},status=status.HTTP_400_BAD_REQUEST)
        serializer=DepartmentSerializer(obj,data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data,status=status.HTTP_201_CREATED)
        return Response({"error":"serialization error"},status=status.HTTP_400_BAD_REQUEST)
    def patch(self,request,id):
        try:
            obj=Departments.objects.get(student_id=id)
        except:
            return Response({"error":"data not found"},status=status.HTTP_400_BAD_REQUEST)
        serializer=DepartmentSerializer(obj,data=request.data,partial=True)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data,status=status.HTTP_206_PARTIAL_CONTENT)
class EnrollmentTable(APIView):
    def get(self,request,id=None):
        if id==None:
            enrollments=Enrollments.objects.all()
            serializer=EnrollmentSerializer(enrollments,many=True)
            if serializer:
                return Response(serializer.data,status=status.HTTP_201_CREATED)
        else:
            obj = Enrollments.objects.get(enrollment_id=id)
            serializer=EnrollmentSerializer(obj)
            return Response(serializer.data,status=status.HTTP_201_CREATED)
        return Response(serializer.errors,status=status.HTTP_400_BAD_REQUEST)
    def post(self, request):
        serializer = EnrollmentSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)
    def delete(self,request,id):
        try:
            data=Enrollments.objects.get(enrolment_id=id)
        except:
            return Response(status=status.HTTP_400_BAD_REQUEST)
        data.delete()
        return Response(status=status.HTTP_204_NO_CONTENT)
    def put(self,request,id):
        try:
            obj=Enrollments.objects.get(student_id=id)
        except:
            return Response({"error":"data not found"},status=status.HTTP_400_BAD_REQUEST)
        serializer=EnrollmentSerializer(obj,data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data,status=status.HTTP_201_CREATED)
        return Response({"error":"serialization error"},status=status.HTTP_400_BAD_REQUEST)
    def patch(self,request,id):
        try:
            obj=Enrollments.objects.get(student_id=id)
        except:
            return Response({"error":"data not found"},status=status.HTTP_400_BAD_REQUEST)
        serializer=EnrollmentSerializer(obj,data=request.data,partial=True)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data,status=status.HTTP_206_PARTIAL_CONTENT)
class GradeTable(APIView):
    def get(self,request,id=None):
        if id==None:
            grades=Grades.objects.all()
            serializer=GradeSerializer(grades,many=True)
            if serializer:
                return Response(serializer.data,status=status.HTTP_201_CREATED)
        else:
            obj = Grades.objects.get(grade_id=id)
            serializer=GradeSerializer(obj)
            return Response(serializer.data,status=status.HTTP_201_CREATED)
        return Response(serializer.errors,status=status.HTTP_400_BAD_REQUEST)
    def post(self, request):
        serializer = GradeSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)
    def delete(self,request,id):
        try:
            data=Grades.objects.get(grades_id=id)
        except:
            return Response(status=status.HTTP_400_BAD_REQUEST)
        data.delete()
        return Response(status=status.HTTP_204_NO_CONTENT)
    def put(self,request,id):
        try:
            obj=Grades.objects.get(student_id=id)
        except:
            return Response({"error":"data not found"},status=status.HTTP_400_BAD_REQUEST)
        serializer=GradeSerializer(obj,data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data,status=status.HTTP_201_CREATED)
        return Response({"error":"serialization error"},status=status.HTTP_400_BAD_REQUEST)
    def patch(self,request,id):
        try:
            obj=Grades.objects.get(student_id=id)
        except:
            return Response({"error":"data not found"},status=status.HTTP_400_BAD_REQUEST)
        serializer=GradeSerializer(obj,data=request.data,partial=True)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data,status=status.HTTP_206_PARTIAL_CONTENT)


AGGREGATE_FUNCS = {
    "Sum": Sum,
    "Avg": Avg,
    "Count": Count,
    "Max": Max,
    "Min": Min,
    "F": F,
}
class DynamicQueryExecutor(APIView):
    def post(self, request):
        data = request.data
        try:
            model_name = data["model"]
            app_label = "tables"
            Model = apps.get_model(app_label, model_name)

            queryset = Model.objects.all()

            # Filters
            filters = data.get("filters", {})
            if filters:
                queryset = queryset.filter(**filters)

            # Aggregations
            annotate = data.get("annotate", {})
            if annotate:
                annotations = {}
                for alias, expression in annotate.items():
                    try:
                        func_name, field = expression.split("(")
                        func_name = func_name.strip()
                        field = field.strip(" )")
                        func = AGGREGATE_FUNCS.get(func_name)
                        if func is None:
                            return Response({"error": f"Invalid aggregate function: {func_name}"}, status=400)
                        annotations[alias] = func(field)
                    except Exception as e:
                        return Response({"error": f"Invalid annotation: {str(e)}"}, status=400)
                queryset = queryset.annotate(**annotations)

            # Values (flattened fields)
            values = data.get("values", [])
            if values:
                queryset = queryset.values(*values)

            # Ordering
            order_by = data.get("order_by", [])
            if order_by:
                queryset = queryset.order_by(*order_by)

            return Response(list(queryset), status=200)

        except LookupError:
            return Response({"error": f"Model '{model_name}' not found."}, status=400)
        except FieldError as e:
            return Response({"error": f"Invalid field: {str(e)}"}, status=400)
        except Exception as e:
            return Response({"error": f"Unexpected error: {str(e)}"}, status=500)
