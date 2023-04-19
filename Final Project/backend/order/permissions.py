from rest_framework.permissions import BasePermission

class IsOrderUser(BasePermission):
    """
    Allows access only to admin users.
    """

    def has_object_permission(self, request, view, obj):
        return bool(request.user == obj.user)