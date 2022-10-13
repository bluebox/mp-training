from rest_framework.permissions import BasePermission

from bookingsapp.models import User


class AllowAny(BasePermission):
    """
    Allow any access.
    This isn't strictly required, since you could use an empty
    permission_classes list, but it's useful because it makes the intention
    more explicit.
    """

    def has_permission(self, request, view):
        return True


class IsAuthenticated(BasePermission):
    """
    Allows access only to authenticated users.
    """

    def has_permission(self, request, view):
        return bool(request.user)


class IsAdminUser(BasePermission):
    """
    Allows access only to admin users.
    """

    def has_permission(self, request, view):
        if request.user:
                user = User.objects.get(email=request.user)
                return bool(request.user and user.isAdmin)
        return False
