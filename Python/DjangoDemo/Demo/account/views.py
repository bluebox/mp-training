from django.views.decorators.csrf import csrf_exempt
from django.http import JsonResponse
import json
from .models import Student
from django.core import serializers
@csrf_exempt
def add_student(request):
    if request.method == 'POST':
        try:
            data = json.loads(request.body)
            student = Student(
                name=data.get('name'),
                age=data.get('age')
            )
            student.save()
            return JsonResponse({'success': "data stored in db"},status=200)
        except Exception as e:
            return JsonResponse({'error': str(e)}, status=500)
    return JsonResponse({'failed': 'Invalid request'}, status=400)
@csrf_exempt
def get_data(request):
    if request.method == 'GET':
        try:
            students=Student.objects.all()
            data = serializers.serialize("json", students)
            return JsonResponse({'data': data}, status=200)
        except Exception as e:
            return JsonResponse({'err': str(e)}, status=500)
    return JsonResponse({"msg": "This is not a valid request"}, status=400)