from rest_framework.permissions import BasePermission, SAFE_METHODS

class IsAdmin(BasePermission):
    def has_permission(self, request, view):
        if request.method in SAFE_METHODS:
            return True
        return request.user.is_authenticated and request.user.role.lower() =='admin'
class CustomStudentTablePermissions(BasePermission):
    def has_permission(self, request, view):
        if request.method in SAFE_METHODS:
            return True
        elif (request.method.lower() == 'put' or request.method.lower() == 'get') and request.user.is_authenticated and request.user.role.lower() == 'teacher':
            return True
        return request.user.is_authenticated and request.user.role.lower() == 'admin'
class IsAdminOrTeacher(BasePermission):
    def has_permission(self, request, view):
        return request.user.is_authenticated and (request.user.role.lower() =='admin' or request.user.role.lower() == 'teacher')

