from django.db.models import Prefetch

from employee_management.constants import ExitClearanceConstantKey
from employee_management.models import EmployeeJobDetails
from facility_management.models import FacilityDetails
from module_access_management.permissions import EmployeeManagementPermission, ProfilePermission
from user_management.constants import AccessLevelKey
from utils.custom_decorators import cache_permission


class EmployeeAccessPermissions:

    @cache_permission
    def has_approve_transfer_request_permission(self, request):
        return EmployeeManagementPermission.APPROVE_TRANSFER_REQUEST.value in request.user.permissions

    @cache_permission
    def has_employee_add_permissions(self, request):
        return EmployeeManagementPermission.ADD_EMPLOYEE.value in request.user.permissions_with_territories or EmployeeManagementPermission.BULK_ONBOARD_REQUEST.value in request.user.permissions_with_territories


    @cache_permission
    def has_employee_edit_permissions(self, request):
        return EmployeeManagementPermission.EDIT_EMPLOYEE.value in request.user.permissions_with_territories

    @cache_permission
    def has_employee_approve_edit_request_permission(self, request):
        return EmployeeManagementPermission.EMPLOYEE_EDIT_REQUEST_APPROVAL.value in request.user.permissions_with_territories

    @cache_permission
    def has_employee_onboard_view_permission(self, request):
        return EmployeeManagementPermission.ONBOARD_VIEW.value in request.user.permissions_with_territories

    @cache_permission
    def has_all_employee_view_permission(self, request):
        return EmployeeManagementPermission.ALL_EMPLOYEES_VIEW.value in request.user.permissions_with_territories

    @cache_permission
    def has_regional_employee_view_permission(self, request):
        return EmployeeManagementPermission.REGIONAL_EMPLOYEES_VIEW.value in request.user.permissions_with_territories

    @cache_permission
    def has_onboard_request_approve(self, request):
        return EmployeeManagementPermission.ONBOARD_REQUEST_APPROVE.value in request.user.permissions_with_territories

    @cache_permission
    def has_exit_request_permission(self, request):
        return EmployeeManagementPermission.APPLY_EXIT_REQ.value in request.user.permissions

    @cache_permission
    def has_error_logs_exception_view_permission(self, request):
        return ProfilePermission.PRODUCTION_ERROR_LOGS.value in request.user.permissions

    @cache_permission
    def has_biometric_login_permission(self, request):
        return ProfilePermission.BIOMETRIC_LOGIN.value in request.user.permissions

    @cache_permission
    def has_my_exit_view_permission(self, request):
        return EmployeeManagementPermission.MY_EXIT_REQ.value in request.user.permissions

    @cache_permission
    def has_all_exit_view_permission(self, request):
        return EmployeeManagementPermission.SUBORDINATE_EXIT_REQ_VIEW.value in request.user.permissions or \
            EmployeeManagementPermission.MY_EXIT_REQ.value in request.user.permissions or \
            EmployeeManagementPermission.REGIONAL_EXIT_REQ_VIEW.value in request.user.permissions

    @cache_permission
    def has_subordinates_exit_view_permission(self, request):
        return EmployeeManagementPermission.SUBORDINATE_EXIT_REQ_VIEW.value in request.user.permissions

    @cache_permission
    def has_regional_exit_view_permission(self, request):
        return EmployeeManagementPermission.REGIONAL_EXIT_REQ_VIEW.value in request.user.permissions

    @cache_permission
    def has_extended_exit_view_permission(self, request):
        return EmployeeManagementPermission.EXTENDED_EXIT_REQ_VIEW.value in request.user.permissions

    @cache_permission
    def has_employees_under_exit_view_permission(self, request):
        return EmployeeManagementPermission.EXTENDED_EXIT_REQ_VIEW.value in request.user.permissions or \
            EmployeeManagementPermission.DEPARTMENT_EXIT_REQ_VIEW.value in request.user.permissions

    @cache_permission
    def has_approval_exit_permission(self, request):
        return EmployeeManagementPermission.APPROVE_L1_EXIT_REQ.value in request.user.permissions or \
            EmployeeManagementPermission.APPROVE_L2_EXIT_REQ.value in request.user.permissions

    @cache_permission
    def has_l1approval_exit_permission(self, request):
        return EmployeeManagementPermission.APPROVE_L1_EXIT_REQ.value in request.user.permissions

    @cache_permission
    def has_l2approval_exit_permission(self, request):
        return EmployeeManagementPermission.APPROVE_L2_EXIT_REQ.value in request.user.permissions

    @cache_permission
    def has_edit_requests_view_permission(self, request):
        return EmployeeManagementPermission.EDIT_REQUEST_VIEW.value in request.user.permissions_with_territories

    @cache_permission
    def has_employee_reports_view_permission(self, request):
        return EmployeeManagementPermission.EMPLOYEE_REPORTS_VIEW.value in request.user.permissions

    @cache_permission
    def has_department_employee_view_permission(self, request):
        return EmployeeManagementPermission.VIEW_DEPARTMENT_EMPLOYEES.value in request.user.permissions

    @cache_permission
    def has_extended_team_employee_view_permission(self, request):
        return EmployeeManagementPermission.VIEW_TEAM.value in request.user.permissions

    @cache_permission
    def has_department_employee_reports_view_permission(self, request):
        return EmployeeManagementPermission.DEPARTMENT_EMPLOYEE_REPORTS_VIEW.value in request.user.permissions
    @cache_permission
    def has_employee_transfer_request_approval_permission(self, request):
        return EmployeeManagementPermission.APPROVE_TRANSFER_REQUEST.value in request.user.permissions

    @cache_permission
    def has_employee_transfer_request_view_permission(self, request):
        return EmployeeManagementPermission.EMPLOYEE_TRANSFER_REQUEST_VIEW.value in request.user.permissions

    @cache_permission
    def has_regional_transfer_request_view_permission(self, request):
        return EmployeeManagementPermission.REGIONAL_TRANSFER_REQUEST_VIEW.value in request.user.permissions

    @cache_permission
    def has_transfer_request_view_permission(self, request):
        return EmployeeManagementPermission.TRANSFER_REQUEST_VIEW.value in request.user.permissions

    @cache_permission
    def has_employee_onboard_lock_view_permission(self, request):
        return EmployeeManagementPermission.EMPLOYEE_ON_BOARD_LOCK_VIEW.value in request.user.permissions

    @cache_permission
    def has_employee_onboard_lock_approve_or_reject_permission(self, request):
        return EmployeeManagementPermission.EMPLOYEE_ON_BOARD_LOCK_APPROVE_OR_REJECT.value in request.user.permissions

    @cache_permission
    def has_employee_onboard_lock_creation_permission(self, request):
        return EmployeeManagementPermission.EMPLOYEE_ON_BOARD_LOCK_CREATION.value in request.user.permissions

    @staticmethod
    def is_employee_and_requester_territory_same(facility_obj, territories, access_level):
        if access_level:
            access_level_key = AccessLevelKey.ACCESS_LEVEL_MAPPING.get(access_level)
            if access_level_key == 'country':
                return True
            if facility_obj:
                return facility_obj.__dict__.get(access_level_key) in territories
        return False

    @staticmethod
    def check_user_has_territory_permission(request, permission, employee_id):
        prefetch_related = Prefetch('location', queryset=FacilityDetails.objects.select_related('address_details'))
        location_obj = EmployeeJobDetails.objects.prefetch_related(prefetch_related).get(
            employee_id=employee_id, is_active=True).location
        return EmployeeAccessPermissions().is_employee_and_requester_territory_same(location_obj,
                                                                                    request.user.permissions_with_territories.get(
                                                                                        permission, {}).get(
                                                                                        'territories'),
                                                                                    request.user.permissions_with_territories.get(
                                                                                        permission, {}).get(
                                                                                        'access_level')
                                                                                    )

    @staticmethod
    def is_self_user(request, employee_id):
        return int(employee_id) == int(request.user.employee_id)

    @cache_permission
    def has_employee_deserter_exemption_permission(self, request):
        return EmployeeManagementPermission.DESERTER_EXEMPTION.value in request.user.permissions

    @cache_permission
    def has_employee_apprentice_confirm_permission(self,request):
        return EmployeeManagementPermission.HR_APPRENTICE_CONFIRM.value in request.user.permissions_with_territories

    @cache_permission
    def has_apprentice_to_regular_permission(self,request):
        return EmployeeManagementPermission.TEAM_APPRENTICE_CONFIRM.value in request.user.permissions_with_territories

    @cache_permission
    def has_view_reportees_permission(self, request):
        return EmployeeManagementPermission.VIEW_REPORTEES.value in request.user.permissions

    @cache_permission
    def has_reset_password_permission(self, request):
        return EmployeeManagementPermission.RESET_PASSWORD.value in request.user.permissions

    @cache_permission
    def has_probation_extend_view_permission(self, request):
        return (EmployeeManagementPermission.PROBATION_EXTEND_HR_VIEW.value in request.user.permissions or
                EmployeeManagementPermission.PROBATION_EXTEND_SUBORDINATE_VIEW.value in request.user.permissions or
                EmployeeManagementPermission.EXTENDED_PROBATION_EXTEND_SUBORDINATE_VIEW.value in request.user.permissions)

    @cache_permission
    def has_aadhar_black_list(self, request):
        return EmployeeManagementPermission.AADHAR_BLACK_LIST.value in request.user.permissions

    @cache_permission
    def has_document_scanning_view_permission(self, request):
        return EmployeeManagementPermission.DOCUMENTS_SCANNING.value in request.user.permissions

    @cache_permission
    def has_authorized_signatory(self, request):
        return EmployeeManagementPermission.AUTHORIZED_SIGNATORY.value in request.user.permissions

    @cache_permission
    def has_approve_authorized_signature_permission(self, request):
        return EmployeeManagementPermission.APPROVE_AUTHORIZED_SIGNATORY.value in request.user.permissions

    @cache_permission
    def has_deserter_intimaion_doc_view_permission(self, request):
        return EmployeeManagementPermission.DESERTER_INTIMATION_DOCUMENT.value in request.user.permissions

    @cache_permission
    def has_exit_feedback_permission(self, request):
        return EmployeeManagementPermission.FEEDBACK_FORM_PERMISSION.value in request.user.permissions

    @cache_permission
    def has_employee_self_onboard_view_permission(self, request):
        return EmployeeManagementPermission.SELF_ONBOARD_VIEW.value in request.user.permissions_with_territories

    @staticmethod
    def check_user_has_any_permission(request, permissions, employee_id, **kwargs):
        from employee_management.employee_orm_manager import EmployeeORMManager
        prefetch_related = Prefetch('location', queryset=FacilityDetails.objects.select_related('address_details'))
        location_obj = EmployeeJobDetails.objects.prefetch_related(prefetch_related).get(
            employee_id=employee_id, is_active=True).location
        for permission in permissions:
            permission_dict = request.user.permissions_with_territories.get(permission, {})
            access_level = permission_dict.get('access_level')
            territories = permission_dict.get('territories')
            if access_level:
                if EmployeeAccessPermissions().is_employee_and_requester_territory_same(location_obj,
                                                                                        territories,
                                                                                        access_level):
                    return True
            elif permission_dict:
                if permission in kwargs.get('team_permission', []):
                    employee_ids = EmployeeORMManager.get_team_employee_ids_under_manager(request.user.employee_id)
                    if employee_id in employee_ids:
                        return True
                elif permission in kwargs.get('subordinate_permission', []):
                    employee_ids = list(EmployeeORMManager.get_employee_sub_ordinates(request.user.employee_id)
                                        .values_list('employee_id', flat=True))
                    if employee_id in employee_ids:
                        return True
                elif permission in kwargs.get('department_permission', []):
                    employee_ids = list(EmployeeORMManager.get_employee_ids_under_department_head(
                                        request.user.employee_id))
                    if employee_id in employee_ids:
                        return True
                else:
                    return True
        return False

    @staticmethod
    def check_all_employees_and_requester_territory_same(access_level, territories, location_objs):
        if access_level:
            access_level_key = AccessLevelKey.ACCESS_LEVEL_MAPPING.get(access_level)
            if access_level_key == 'country':
                return True
            if location_objs:
                return all(facility_obj.__dict__.get(access_level_key) in territories for facility_obj in location_objs)
        return False

    @cache_permission
    def has_bulk_action_has_permission(self, request):
        return EmployeeManagementPermission.EMPLOYEE_BULK_ACTION.value in request.user.permissions_with_territories

    @cache_permission
    def has_approve_bulk_action_has_permission(self, request):
        return EmployeeManagementPermission.APPROVE_BULK_ACTION.value in request.user.permissions_with_territories

    @cache_permission
    def has_add_manual_exit_clearance_permission(self, request):
        return EmployeeManagementPermission.ADD_MANUAL_EXIT_CLEARANCE.value in request.user.permissions_with_territories

    @cache_permission
    def has_hr_exit_clearance_view_permission(self, request):
        return EmployeeManagementPermission.HR_CLEARANCE.value in request.user.permissions

    @cache_permission
    def has_any_exit_clearance_view_permission(self, request):
        exit_clearance_permissions = [EmployeeManagementPermission.HR_CLEARANCE.value,
                                      EmployeeManagementPermission.ACCOUNTS_CLEARANCE.value,
                                      EmployeeManagementPermission.EDP_CLEARANCE.value,
                                      EmployeeManagementPermission.EXIT_CLEARANCE_VIEW.value,
                                      ]
        user_permissions = set(request.user.permissions)
        return any(elem in user_permissions for elem in exit_clearance_permissions)

    @cache_permission
    def has_exit_clearance_permission_matching_approver_type(self, request):
        approver_type = request.data.get("activeKey")
        user_permissions = set(request.user.permissions)
        if ExitClearanceConstantKey.HR == approver_type:
            return EmployeeManagementPermission.HR_CLEARANCE.value in user_permissions
        elif ExitClearanceConstantKey.ACCOUNTS == approver_type:
            return EmployeeManagementPermission.ACCOUNTS_CLEARANCE.value in user_permissions
        elif ExitClearanceConstantKey.EDP == approver_type:
            return EmployeeManagementPermission.EDP_CLEARANCE.value in user_permissions
        elif approver_type in [ExitClearanceConstantKey.REPORTING_HEAD, ExitClearanceConstantKey.STORE_SUPERVISOR_OR_MANAGER, ExitClearanceConstantKey.HOD]:
            return EmployeeManagementPermission.EXIT_CLEARANCE_VIEW.value in user_permissions
        elif ExitClearanceConstantKey.HR == approver_type:
            return EmployeeManagementPermission.HR_CLEARANCE.value in user_permissions
        return False



    @cache_permission
    def has_ffs_bulk_upload_permission(self, request):
        return EmployeeManagementPermission.FFS_BULK_UPLOAD_PERMISSION.value in request.user.permissions_with_territories

    @cache_permission
    def has_ffs_bulk_upload_approval_permission(self, request):
        return (EmployeeManagementPermission.FFS_BULK_UPLOAD_APPROVAL_PERMISSION.value in
                request.user.permissions_with_territories)

    @cache_permission
    def has_salary_change_request_permission(self, request):
        return EmployeeManagementPermission.SALARY_EDIT_REQUEST_VIEW.value in request.user.permissions_with_territories

    @cache_permission
    def has_failed_celery_tasks_view_permission(self, request):
        return ProfilePermission.FAILED_CELERY_TASKS_VIEW.value in request.user.permissions_with_territories

    @cache_permission
    def has_upcoming_superannuation_employees_view_permission(self, request):
        return EmployeeManagementPermission.VIEW_UPCOMING_SUPERANNUATION_EMPLOYEES.value in request.user.permissions_with_territories