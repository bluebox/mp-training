from django.http import JsonResponse
from rest_framework import status
from rest_framework.response import Response
from rest_framework.views import APIView
from .employeeview import *
from .models import *

global i
i = 0



class DeviceView(APIView):
    def get(self, request):
        try:
            data, total_pages, current_page = get_devices(request)
            return Response({
                'pageItems': data,
                'totalPages': total_pages,
                'page': current_page
            })
        except Exception as e:
            return JsonResponse(str(e),safe=False)


    def post(self, request):
        try:
            return JsonResponse(add_device(request),safe=False)
        except Exception as e:
            return JsonResponse(str(e),safe=False)

    def delete(self, request):
      try:
          response = {
              'msg': 'Delete device'
          }
          delete_device(request)
          return JsonResponse(response)
      except Exception as e:
          return JsonResponse(str(e),safe=False)



class ComplaintView(APIView):
    def get(self, request):
        try:
            data, total_pages,current_page=complaint_search(request)

            return Response({
                'pageItems': data,
                'totalPages': total_pages,
                'page': current_page
            })
        except Exception as e:
            return JsonResponse(str(e),safe=False)


    def post(self, request):
        try:
          return JsonResponse(post_complaint(request),safe=False)
        except Exception as e:
            return JsonResponse(str(e),safe=False)


    def delete(self, request):
        try:
            comp_id = request.GET.get('comp_id')
            complaint = Complaint.objects.get(comp_id=comp_id)
            complaint.delete()
            return Response("deleted successfully")
        except Exception as e:
            return JsonResponse(str(e),safe=False)


class LoginView(APIView):
    def get(self, request):
        try:
            token, data = login(request)
            response = {
            "userdetails": data,
            'token': token,
            }
            return JsonResponse(response)

        except Exception as e:
            return JsonResponse(str(e),safe=False)


class EmployeeView(APIView):
    def get(self, request):
        try:
            data = get_employee_byname(request)
            return JsonResponse(data, safe=False)

        except Exception as e:
            return JsonResponse(str(e))

    def post(self, request):
        try:
           return JsonResponse(post_employee(request))
        except Exception as e:
            return JsonResponse(str(e),safe=False)


class FacilityView(APIView):

    def get(self, request):
       try:
           data = get_employee_list(request)
           return JsonResponse(data, safe=False)

       except Exception as e:
           return JsonResponse(str(e),safe=False)

    def post(self, request):
       try :
           data = post_facility(request)
           return JsonResponse(data, safe=False)

       except Exception as e:
            return JsonResponse(str(e),safe=False)



class TasksView(APIView):
    def get(self, request):
        try :
            data = get_assigned_tasks(request)
            return JsonResponse(data, safe=False)
        except Exception as e:
            return JsonResponse(str(e),safe=True)

class ManagerView(APIView):
    def get(self, request):
        try:
            data,total_pages,current_page= get_tasks(request)
            return Response(data={
                'pageItems': data,
                'totalPages': total_pages,
                'page': current_page,
            }, status=status.HTTP_200_OK)

        except Exception as e:
            return Response(str(e),safe=False)



class AssignView(APIView):
    def get(self, request):
        try:
            data = assign_task(request)
            return JsonResponse(data, safe=False)

        except Exception as e:
            return JsonResponse(str(e),safe=False)


class EditView(APIView):
    def post(self, request):
      try:
          data = edit_profile(request)
          return JsonResponse(data)
      except Exception as e:
        return JsonResponse(str(e),safe=False)


class CreateEmployee(APIView):
    def post(self, request):
        try:
            data = create_employee(request)
            return JsonResponse(data)

        except Exception as e:
            return JsonResponse(str(e),safe=False)

    def delete(self, request):
        try:
            delete_employee(request)
            response = {
                "msg": "complaint is deleted successfully"
            }
            return JsonResponse(response)
        except Exception as e:
            return JsonResponse(str(e),safe=False)




def search_employee(request):
    try:
       data = search_employees(request)
       return JsonResponse(data, safe=False)

    except Exception:
        return JsonResponse("employee does not exists", safe=False)


def facilities(request):
    try:
       data = get_facilities(request)
       return JsonResponse(data, safe=False)

    except Exception as e:
        return JsonResponse(str(e))



def update_status(request):
    try:
        data = update_status_fun(request)
        return JsonResponse(data)
    except Exception as e:
        return JsonResponse(str(e))


class ComplaintSearch(APIView):
    def get(self, request):
        data=get_complaint_search(request)
        return JsonResponse(data, safe=False)


class ProfileView(APIView):
    def get(self, request):
        total_complaints,problems_solved=get_profile(request)
        response = {
            'total_complaints':total_complaints,
            "problems_solved":problems_solved
        }
        return JsonResponse(response,safe=False)



# def devices_of_employee(request):
#     try:
#         emp_id = request.GET.get('emp_id')
#         devices = Employee_Devices.objects.filter(emp_id=emp_id)
#         serialized_data = EmployeeDeviceSerializer(devices, many=True)
#         return JsonResponse(serialized_data.data, safe=False)
#     except Exception as e:
#         return JsonResponse(str(e))

# def getassignedto(request):
#     try:
#         assignedto = Issued_to.objects.get(comp_id=request.GET.get('comp_id'))
#         complaint = Complaint.objects.get(comp_id=request.GET.get('comp_id'))
#         emp_id = assignedto.emp_id
#         response = {'emp_name': emp_id.emp_name,
#                     'status': complaint.is_assigned}
#         return JsonResponse(response)
#     except:
#         complaint = Complaint.objects.get(comp_id=request.GET.get('comp_id'))
#         response = {'comp_status': complaint.is_assigned}
#         return JsonResponse(response)
