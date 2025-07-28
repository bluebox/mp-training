
const BASE_URL = "http://localhost:8000";

const API = {
  AUTH: {
    LOGIN: `${BASE_URL}/api/token/`,
    REFRESH: `${BASE_URL}/api/token/refresh/`,
  },

  EMPLOYEE: {
    PROFILE: `${BASE_URL}/employee/profile/`,
    UPDATE_JOB: `${BASE_URL}/employee/update-job/`,
  },

  MANAGER: {
    UPDATE_TEAM: `${BASE_URL}/manager/update-team/`,
  },

  HR: {
    CREATE_EMPLOYEE: `${BASE_URL}/hr/create-employee/`,
    DELETE_EMPLOYEE: `${BASE_URL}/hr/delete-employee/`,
    UPDATE_SALARY: `${BASE_URL}/hr/update-salary/`,
  },

  CEO: {
    ALL_EMPLOYEES: `${BASE_URL}/ceo/employees/`,
  },

  SHARED: {
    DEPARTMENTS: `${BASE_URL}/departments/add/`,
    DESIGNATIONS: `${BASE_URL}/designation/`,
  },
};

export default API;

