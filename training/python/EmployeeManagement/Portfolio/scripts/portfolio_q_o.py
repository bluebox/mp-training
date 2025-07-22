from Portfolio.models import *
def run():
    print(DepartmentModel.objects.all().values())
