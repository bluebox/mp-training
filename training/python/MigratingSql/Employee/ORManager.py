from django.db.models import Prefetch, Q, F
from .models import Employees, EmployeeJobDetails, EmployeeOfficeAddressDetails, PayScale, Designations
from django.core.exceptions import ObjectDoesNotExist
from django.db import transaction
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

#The below one is done for safety
    @staticmethod
    @transaction.atomic
    def update_employee_info(emp, data):
        try:
            for attr, value in data.items():
                if hasattr(emp, attr):
                    setattr(emp, attr, value)
                else:
                    raise ValueError(f"Attrubute {attr} is not associated with employee")
            emp.full_clean()
            emp.save()
            return emp
        except Exception as e:
            raise RuntimeError(f"Updating employee info failed: {e}")


    @staticmethod
    @transaction.atomic
    def update_office_address(emp, data):
        try:
            address, _ = EmployeeOfficeAddressDetails.objects.get_or_create(emp=emp)
            valid_fields = [f.name for f in EmployeeOfficeAddressDetails._meta.get_fields()]
            for attr, value in data.items():
                if attr in valid_fields:
                    setattr(address, attr, value)
                else:
                    raise ValueError(f"Attribute {attr} is not associated with employee address")
            address.full_clean()
            address.save()
        except Exception as e:
            raise RuntimeError(f"Couldn't write the changes to database: {e}")
        return address


    @staticmethod
    def change_team_id(emp_id, team_id):
        try:
            job = EmployeeJobDetails.objects.get(emp__emp_id=emp_id, is_active=True)
            job.team_id = team_id
            job.full_clean()
            job.save()
            return job
        except Employees.DoesNotExist:
            raise ValueError(f"Sorry employee job details are unavailable for {emp_id}")
        except Exception as e:
            raise RuntimeError(f"Couldn't write the changes to database: {e}")

    @staticmethod
    def update_payscale(emp_id, salary):
        try:
            if not isinstance(salary, (int, float)) or salary < 0:
                raise ValueError("Salary must be a non-negative number.")
            emp = Employees.objects.get(emp_id=emp_id)
            PayScale.objects.update_or_create(emp=emp, salary=salary)
            return True
        except Employees.DoesNotExist:
            raise Employees.DoesNotExist(f"The employee with id {emp_id} does not exist")
        except Exception as e:
            raise RuntimeError(f"Couldn't write the changes to database: {e}")

    @staticmethod
    def get_all_employees():
        return Employees.objects.select_related('dept').prefetch_related(
            Prefetch('job_details', queryset=EmployeeJobDetails.objects.filter(is_active=True)),
            'address_details',
            'pay_scales'
        )

    @staticmethod
    @transaction.atomic
    def assign_or_update_job(emp_id, data):
        try:
            emp = Employees.objects.get(emp_id=emp_id)
            empjob=EmployeeJobDetails.objects.filter(emp=emp, is_active=True)
            if empjob:
                empjob.update(is_active=False)

            new_job = EmployeeJobDetails.objects.create(
                emp=emp,
                designation=data['designation'],
                effective_from=data['effective_from'],
                effective_till=data['effective_till'],
                team_id=data['team_id'],
                is_active=True
            )
            return new_job
        except Employees.DoesNotExist:
            raise Employees.DoesNotExist(f"The employee with id {emp_id} does not exist")
        except Exception as e:
            raise RuntimeError(f"Couldn't write the changes to database: {e}")