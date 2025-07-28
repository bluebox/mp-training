import React from "react";
import { useSelector } from "react-redux";
import { ROLES } from "../utils/roles";
import ProfileView from "../features/feature/profile/ProfileView";
import ManageEmployees from "./ManageEmployees";
import ManageSalary from "./ManageSalary";
import ManageTeams from "./ManageTeams";
import Designations from "./Designations";
import Departments from "./Departments";

const Dashboard = () => {
  const role = useSelector((state) => state.auth.role);

  return (
    <div className="dashboard">
      <h1 className="text-2xl font-semibold mb-4">Dashboard</h1>

      <ProfileView />

      {(role === ROLES.MANAGER || role === ROLES.HR || role === ROLES.CEO) && (
        <div className="my-4">
          <h2 className="text-xl font-medium">Manage Teams</h2>
          <ManageTeams />
        </div>
      )}

      {(role === ROLES.HR || role === ROLES.CEO) && (
        <>
          <div className="my-4">
            <h2 className="text-xl font-medium">Manage Employees</h2>
            <ManageEmployees />
          </div>

          <div className="my-4">
            <h2 className="text-xl font-medium">Manage Salary</h2>
            <ManageSalary />
          </div>
        </>
      )}

      {role === ROLES.CEO && (
        <>
          <div className="my-4">
            <h2 className="text-xl font-medium">Departments</h2>
            <Departments />
          </div>

          <div className="my-4">
            <h2 className="text-xl font-medium">Designations</h2>
            <Designations />
          </div>
        </>
      )}
    </div>
  );
};

export default Dashboard;