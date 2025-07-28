from django.db.models import Prefetch, Q, F
from .models import Employees, EmployeeJobDetails, EmployeeOfficeAddressDetails, PayScale, Designations
from django.core.exceptions import ObjectDoesNotExist

class DatabaseOperationManager:

    @staticmethod
    def get_employee_full_data(user):
        try:
            emp = Employees.objects.select_related('dept')\
                .prefetch_related(
                    Prefetch('job_details', queryset=EmployeeJobDetails.objects.filter(is_active=True)),
                    Prefetch('address_details'),
                    Prefetch('pay_scales')
                ).get(user=user)

            return emp
        except ObjectDoesNotExist:
            raise ValueError("Employee not found")

    @staticmethod
    def update_employee_info(emp, data):
        for attr, value in data.items():
            if hasattr(emp, attr):
                setattr(emp, attr, value)
        emp.save()
        return emp

    @staticmethod
    def update_office_address(emp, data):
        address, _ = EmployeeOfficeAddressDetails.objects.get_or_create(emp=emp)
        valid_fields = [f.name for f in EmployeeOfficeAddressDetails._meta.get_fields()]
        for attr, value in data.items():
            if attr in valid_fields:
                setattr(address, attr, value)
        address.save()
        return address

    @staticmethod
    def change_team_id(emp_id, team_id):
        job = EmployeeJobDetails.objects.get(emp__emp_id=emp_id, is_active=True)
        job.team_id = team_id
        job.save()
        return job

    @staticmethod
    def update_payscale(emp_id, salary):
        emp = Employees.objects.get(emp_id=emp_id)
        PayScale.objects.update_or_create(emp=emp, salary=salary)
        return True

    @staticmethod
    def get_all_employees():
        return Employees.objects.select_related('dept').prefetch_related(
            Prefetch('job_details', queryset=EmployeeJobDetails.objects.filter(is_active=True)),
            'address_details',
            'pay_scales'
        )

    @staticmethod
    def assign_or_update_job(emp_id, data):
        emp = Employees.objects.get(emp_id=emp_id)

        EmployeeJobDetails.objects.filter(emp=emp, is_active=True).update(is_active=False)

        new_job = EmployeeJobDetails.objects.create(
            emp=emp,
            designation=data['designation'],
            effective_from=data['effective_from'],
            effective_till=data['effective_till'],
            team_id=data['team_id'],
            is_active=True
        )
        return new_job