// src/utils/role.js

export const ROLES = {
  EMPLOYEE: "employee",
  MANAGER: "manager",
  HR: "hr",
  CEO: "ceo",
};

// Return true if role matches any allowed role
export const hasAccess = (userRole, allowedRoles = []) => {
  return allowedRoles.includes(userRole);
};
