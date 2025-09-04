import React, { useEffect, useState } from "react";
import {
  fetchApprovedAmounts,
  fetchEmployeeList,
  fetchCategoryWiseApproved,
} from "../../service/managerdashboardService";

import { Doughnut } from "react-chartjs-2";
import { Chart as ChartJS, ArcElement, Tooltip, Legend } from "chart.js";

ChartJS.register(ArcElement, Tooltip, Legend);

const ManagerDashboard = () => {
  const [totalApprovedAmount, setTotalApprovedAmount] = useState(0);
  const [employeeData, setEmployeeData] = useState({
    employees: [],
    totalCount: 0,
  });
  const [categoryData, setCategoryData] = useState([]);
  const [loading, setLoading] = useState(true);

  const username = localStorage.getItem("username");
  const password = localStorage.getItem("password");
  const managerId = localStorage.getItem("userId");

  useEffect(() => {
    const loadDashboardData = async () => {
      setLoading(true);
      try {
        const [approvedAmounts, employeeList, categoryApproved] =
          await Promise.all([
            fetchApprovedAmounts(),
            fetchEmployeeList(),
            fetchCategoryWiseApproved(),
          ]);
        console.log(employeeList);

        const now = new Date();
        const currentMonth = now.getMonth();
        const currentYear = now.getFullYear();

        const totalAmount = approvedAmounts
          .filter((expense) => {
            const dateStr =
              expense?.approvedDate ||
              expense?.approvedOn ||
              expense?.date ||
              expense?.createdAt;
            const d = dateStr ? new Date(dateStr) : null;
            return d && !isNaN(d) && d.getMonth() === currentMonth && d.getFullYear() === currentYear;
          })
          .reduce((sum, expense) => sum + (Number(expense.amount) || 0), 0);

        setTotalApprovedAmount(totalAmount);

        setEmployeeData(employeeList);

        setCategoryData(categoryApproved);
      } catch (err) {
        alert(err.message);
      }
      setLoading(false);
    };

    loadDashboardData(); 
  }, [username, password, managerId]);

  if (loading) return <div className="text-center mt-4">Loading...</div>;

  const categoryLabels = categoryData.map((category) => category.categoryName);
  const approvedAmounts = categoryData.map(
    (category) => category.approvedAmount
  );

  const doughnutData = {
    labels: categoryLabels,
    datasets: [
      {
        label: "Approved Amount",
        data: approvedAmounts,
        backgroundColor: [
          "rgba(54, 162, 235, 0.6)",
          "rgba(255, 99, 132, 0.6)",
          "rgba(255, 206, 86, 0.6)",
          "rgba(75, 192, 192, 0.6)",
        ],
        borderColor: [
          "rgba(54, 162, 235, 1)",
          "rgba(255, 99, 132, 1)",
          "rgba(255, 206, 86, 1)",
          "rgba(75, 192, 192, 1)",
        ],
        borderWidth: 1,
      },
    ],
  };

  const doughnutOptions = {
    responsive: true,
    plugins: {
      legend: { position: "bottom" },
      tooltip: {
        callbacks: {
          label: (context) => {
            const label = context.label || "";
            const value = context.parsed || 0;
            return `${label}: ₹${value.toLocaleString()}`;
          },
        },
      },
    },
  };

  return (
    <div className="container mt-4">
      <div className="row mb-4">
        <div className="col-12">
          <h2>Manager Dashboard</h2>
        </div>
      </div>

      <div className="row mb-4">
        <div className="col-md-6 mb-3 mb-md-0">
          <div className="card h-100">
            <div className="card-body text-center d-flex flex-column justify-content-center">
              <h4>Total Amount Approved by You</h4>
              <h2>₹{totalApprovedAmount.toLocaleString()}</h2>
            </div>
          </div>
        </div>

        <div className="col-md-6 d-flex align-items-center justify-content-center">
          <div className="card w-100">
            <div className="card-header text-center">
              <h5>Amount Approved Per Category</h5>
            </div>
            <div className="card-body d-flex justify-content-center align-items-center">
              <div style={{ width: "100%", maxWidth: "350px" }}>
                <Doughnut data={doughnutData} options={doughnutOptions} />
              </div>
            </div>
          </div>
        </div>
      </div>

      <div className="row mb-4">
        <div className="col-12">
          <div className="card h-100">
            <div className="card-header">
              <h5>Total Employees Under Manager: {employeeData.totalCount}</h5>
            </div>
            <div className="card-body">
              <div className="table-responsive">
                <table className="table table-striped">
                  <thead className="table-dark">
                    <tr>
                      <th>Employee Name</th>
                    </tr>
                  </thead>
                  <tbody>
                    {employeeData?.employees?.map((username, index) => (
                      <tr key={index}>
                        <td>{username}</td>
                      </tr>
                    ))}
                  </tbody>
                </table>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default ManagerDashboard;
