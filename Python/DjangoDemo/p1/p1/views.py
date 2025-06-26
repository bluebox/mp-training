from django.db import connection
from django.http import HttpResponse
from . import settings
def check_db(request):
    db_name = settings.DATABASES['default']['NAME']
    return HttpResponse(f"Database Name: {db_name}")
