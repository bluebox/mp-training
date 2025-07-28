# permissions.py
from rest_framework.permissions import BasePermission

class IsEmployee(BasePermission):
    def has_permission(self, request, view):
        return request.user.is_authenticated and request.user.is_employee()

class IsManager(BasePermission):
    def has_permission(self, request, view):
        return request.user.is_authenticated and request.user.is_manager()

class IsHR(BasePermission):
    def has_permission(self, request, view):
        return request.user.is_authenticated and request.user.is_hr()

class IsCEO(BasePermission):
    def has_permission(self, request, view):
        return request.user.is_authenticated and request.user.is_ceo()

class HasElevatedPermission(BasePermission):
    def has_permission(self, request, view):
        return request.user.is_authenticated and (request.user.is_manager() or request.user.is_hr() or request.user.is_ceo())
