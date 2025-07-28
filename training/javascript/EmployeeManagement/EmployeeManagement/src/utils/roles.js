
export const ROLES = {
  EMPLOYEE: "employee",
  MANAGER: "manager",
  HR: "hr",
  CEO: "ceo",
};

export const hasAccess = (userRole, allowedRoles = []) => {
  return allowedRoles.includes(userRole);
};
