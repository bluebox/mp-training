
import axios from "axios";
import api from "../../api/api";


export const listManagers = () => {
    return api.get(`admin/getAllManagers`,{ withCredentials: true } );
}

export const listEmployees = () => {
    return api.get(`admin/getEmployees`,{ withCredentials: true } );
}

export const listEmployeesLeaveRequests = () => {
    return api.get(`admin/getEmployeeLeaveRequestsReport`,{ withCredentials: true } );
}

export const listEmployeesExitRequests = () => {
    return api.get(`admin/getEmployeeExitRequestsReport`,{ withCredentials: true } );
}

export const getuserDetails = (email) => {
    return api.get(`admin/getuserDetails?email=${email}`,{ withCredentials: true } );
}