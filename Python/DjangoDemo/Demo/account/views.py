from django.views.decorators.csrf import csrf_exempt
from django.http import JsonResponse
import json
from .models import Student
from .serializers import StudentSerializer
from rest_framework.response import Response
from rest_framework import status
from rest_framework.decorators import api_view


@csrf_exempt
def add_student(request):
    if request.method == 'POST':
        try:
            data = json.loads(request.body)
            student = Student(name=data.get('name'), age=data.get('age'))
            student.save()
            return JsonResponse({'success': "data stored in db"}, status=200)
        except Exception as e:
            return JsonResponse({'error': str(e)}, status=500)
    return JsonResponse({'failed': 'Invalid request'}, status=400)


@api_view(['GET', 'POST'])
def get_data(request):
    if request.method == 'GET':
        try:
            students = Student.objects.all()
            serializer = StudentSerializer(students, many=True).data
            return Response({'data': serializer}, status=status.HTTP_200_OK)
        except Exception as e:
            return Response({'err': str(e)}, status=status.HTTP_500_INTERNAL_SERVER_ERROR)

    elif request.method == 'POST':
        serializer = StudentSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)


@api_view(['GET'])
def get_student_data(request, pk):
    # if request.method=='GET':
    #     student=Student.objects.filter(id=pk).first()
    #     serializer=StudentSerializer(student).data
    #     return Response({'data':serializer},status=status.HTTP_200_OK)
    # return Response({'err':" make a valid request"}) this using filter it will return [] if not found so we use get
    if request.method == 'GET':
        try:
            student = Student.objects.get(id=pk)
            serializer = StudentSerializer(student).data
            return Response({'data': serializer}, status=status.HTTP_200_OK)
        except Exception as e:
            return Response({'err': 'student not found'}, status=status.HTTP_404_NOT_FOUND)
    return Response({'err': " make a valid request"})
