import React from 'react';
import ManagerDashboard from './Manager/ManagerDashboard';
import AdminDashboard from './Admin/AdminDashboard';
import EmployeeDashboard from './Employee/EmployeeDashboard';

export default function Dashboard({currRole}) {
  
  return (
    <div>
        {currRole === "ROLE_EMPLOYEE" && <EmployeeDashboard/>}
        {currRole === "ROLE_ADMIN" && <AdminDashboard/>}
        {currRole === "ROLE_MANAGER" && <ManagerDashboard/>}
    </div>
  );
}
