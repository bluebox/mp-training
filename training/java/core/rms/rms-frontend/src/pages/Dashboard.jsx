import React, { useEffect, useState } from "react";
import api from "../config/axiosConfig";
import { useSelector } from "react-redux";

function AdminDashboardMain() {

  const employees = useSelector((store) => store.employees.employees);
  const activeEmployeesLength=useSelector((store)=>store.employees.activeEmployeeCount);
  const items = useSelector((store) => store.items);
  const orders=useSelector((store) => store.orders);

  const [summary, setSummary] = useState({
    employees: 0,
    items: 0,
    orders: 0,
    activeEmployees: 0,
    availableWaiters: 0,
  });

  useEffect(() => {
    async function fetchData() {
      try {

        const waitersRes = await api.get("/api/waiters/available");

        setSummary((prev) => ({
          ...prev,
          employees: employees.length,
          items: items.length,
          orders: orders.length,
          activeEmployees: activeEmployeesLength,
          availableWaiters: waitersRes?.data?.data?.length || 0,
        }));
      } catch (error) {
        console.error("Error fetching dashboard data", error);
      }
    }

    fetchData();
  }, [employees]);

  return (
    <div className="p-4">
      <h2 className="mb-4">Summary Reports</h2>
      <div className="row g-4">
        <div className="col-md-4">
          <div className="card text-center shadow-sm p-3">
            <h6>Total Employees</h6>
            <h2 className="text-primary">{summary.employees}</h2>
          </div>
        </div>

        <div className="col-md-4">
          <div className="card text-center shadow-sm p-3">
            <h6>Active Employees</h6>
            <h2 className="text-success">{summary.activeEmployees}</h2>
          </div>
        </div>

        <div className="col-md-4">
          <div className="card text-center shadow-sm p-3">
            <h6>Available Waiters</h6>
            <h2 className="text-warning">{summary.availableWaiters}</h2>
          </div>
        </div>

        <div className="col-md-6">
          <div className="card text-center shadow-sm p-3">
            <h6>Total Items</h6>
            <h2 className="text-info">{summary.items}</h2>
          </div>
        </div>

        <div className="col-md-6">
          <div className="card text-center shadow-sm p-3">
            <h6>Successful Orders</h6>
            <h2 className="text-danger">{summary.orders}</h2>
          </div>
        </div>
      </div>
    </div>
  );
}

export default AdminDashboardMain;