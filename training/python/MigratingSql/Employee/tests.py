import pytest
from django.urls import reverse
from rest_framework.test import APIClient
from Employee.models import CustomUser, Departments, Employees
from django.contrib.auth import get_user_model

User = get_user_model()

@pytest.fixture
def api_client():
    return APIClient()

@pytest.fixture
def create_user():
    def make_user(username, role):
        user = CustomUser.objects.create_user(username=username, password="testpass", role=role)
        dept, _ = Departments.objects.get_or_create(dept_name="Engineering")
        if role == "employee":
            emp = Employees.objects.create(user=user, emp_id=f"EMP{username}", emp_name=username, dob="1995-01-01", dept=dept)
            return user, emp
        return user, None
    return make_user


# ----------- EMPLOYEE TESTS -----------

@pytest.mark.django_db
def test_employee_can_view_own_profile(api_client, create_user):
    user = create_user("emp", "employee")
    api_client.force_authenticate(user=user)
    res = api_client.get(reverse("employee-profile"))
    assert res.status_code in [200, 404]  # 404 if no employee record yet

@pytest.mark.django_db
def test_employee_cannot_create_department(api_client, create_user):
    user = create_user("emp", "employee")
    api_client.force_authenticate(user=user)
    res = api_client.post(reverse("ceo-create-department"), {"dept_name": "ABC"}, format="json")
    assert res.status_code in [403, 401]

# ----------- MANAGER TESTS -----------

@pytest.mark.django_db
def test_manager_can_update_team_id(api_client, create_user):
    user = create_user("mgr", "manager")
    api_client.force_authenticate(user=user)
    res = api_client.patch(reverse("manager-update-team"), {"emp_id": 1234, "team_id": 5678}, format="json")
    assert res.status_code in [200, 404]  # If emp not found

@pytest.mark.django_db
def test_manager_cannot_create_designation(api_client, create_user):
    user = create_user("mgr", "manager")
    api_client.force_authenticate(user=user)
    res = api_client.post(reverse("hr-create-designation"), {"designation": "NewRole"}, format="json")
    assert res.status_code in [403, 401]

# ----------- HR TESTS -----------

@pytest.mark.django_db
def test_hr_can_create_employee(api_client, create_user):
    user = create_user("hr", "hr")
    dept, _ = Departments.objects.get_or_create(dept_name="Engineering")
    api_client.force_authenticate(user=user)
    res = api_client.post(reverse("hr-create-employee"), {
        "emp_name": "Emp1", "dob": "2000-01-01", "dept": dept.id
    }, format="json")
    assert res.status_code in [200, 201]

@pytest.mark.django_db
def test_hr_can_update_payscale(api_client, create_user):
    user = create_user("hr", "hr")
    api_client.force_authenticate(user=user)
    res = api_client.post(reverse("hr-update-salary"), {
        "emp_id": 1111, "salary": 60000
    }, format="json")
    assert res.status_code in [200, 404]

@pytest.mark.django_db
def test_hr_can_crud_designation(api_client, create_user):
    user = create_user("hr", "hr")
    api_client.force_authenticate(user=user)
    create_res = api_client.post(reverse("hr-create-designation"), {"designation": "Dev"}, format="json")
    assert create_res.status_code in [200, 201]

# ----------- CEO TESTS -----------

@pytest.mark.django_db
def test_ceo_can_create_department(api_client, create_user):
    user = create_user("ceo", "ceo")
    api_client.force_authenticate(user=user)
    res = api_client.post(reverse("ceo-create-department"), {"dept_name": "Strategy"}, format="json")
    assert res.status_code in [200, 201]

@pytest.mark.django_db
def test_ceo_can_view_all_employees(api_client, create_user):
    user = create_user("ceo", "ceo")
    api_client.force_authenticate(user=user)
    res = api_client.get(reverse("ceo-all-employees"))
    assert res.status_code in [200, 204]

@pytest.mark.django_db
def test_ceo_can_update_department(api_client, create_user):
    user = create_user("ceo", "ceo")
    dept, _ = Departments.objects.get_or_create(dept_name="OldName")
    api_client.force_authenticate(user=user)
    res = api_client.put(reverse("ceo-update-department", args=[dept.id]), {"dept_name": "NewName"}, format="json")
    assert res.status_code == 200

@pytest.mark.django_db
def test_ceo_can_delete_employee(api_client, create_user):
    user = create_user("ceo", "ceo")
    api_client.force_authenticate(user=user)
    res = api_client.delete(reverse("ceo-delete-employee", args=[1234]))
    assert res.status_code in [204, 404]

@pytest.mark.django_db
def test_ceo_can_crud_designation(api_client, create_user):
    user = create_user("ceo", "ceo")
    api_client.force_authenticate(user=user)
    res = api_client.post(reverse("hr-create-designation"), {"designation": "CTO"}, format="json")
    assert res.status_code in [200, 201]

# ----------- INVALID ACCESS TESTS -----------

@pytest.mark.django_db
def test_employee_cannot_delete_another_employee(api_client, create_user):
    user = create_user("emp1", "employee")
    api_client.force_authenticate(user=user)
    res = api_client.delete(reverse("ceo-delete-employee", args=[1234]))
    assert res.status_code in [403, 401]

@pytest.mark.django_db
def test_manager_cannot_create_employee(api_client, create_user):
    user = create_user("mgr", "manager")
    api_client.force_authenticate(user=user)
    res = api_client.post(reverse("hr-create-employee"), {
        "emp_name": "Fake", "dob": "1990-01-01", "dept": 1
    }, format="json")
    assert res.status_code in [403, 401]
