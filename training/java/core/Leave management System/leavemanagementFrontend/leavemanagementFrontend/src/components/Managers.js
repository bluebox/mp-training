import React, { useState, useEffect, use } from "react";
import { listManagers } from "./scripts/AdminService";
import "../styles/Managers.css"
import { useNavigate } from "react-router-dom";

export default function Managers() {
  const [managers, setManagers] = useState([]);
  const navigate = useNavigate();
  useEffect(() => {
    listManagers().then((response) => {
      setManagers(response.data);
    }).catch(error => {
      console.error(error);
    })
  }, [])

  const handleAction = async (managerid) => {
   navigate(`/admindashboard/assigned-employees/${managerid}`);
  };

  return (
    <div className="managers-container">
      <h2 className="managers-heading">LIST OF MANAGERS</h2>
      {managers.length === 0 ? (
        <p>No managers available</p>
      ) : (
        <div className="table-responsive">
          <table className='managers-table'>
            <thead>
              <tr>
                <th>Employee ID</th>
                <th>Employee Name</th>
                <th>Email</th>
                <th>Role</th>
                <th>Mobile No</th>
                <th>Date Of Birth</th>
                <th>Date Of Join</th>
                <th>Gender</th>
                <th>Address</th>
                <th>Reporting To</th>
                <th>Employees</th>
              </tr>
            </thead>
            <tbody>
              {managers.map((employee) => (
                <tr key={employee.employeeId}>
                  <td>{employee.employeeId}</td>
                  <td>{employee.employeeName}</td>
                  <td>{employee.employeeEmail}</td>
                  <td>{employee.employeeRole}</td>
                  <td>{employee.employeeMobileNo}</td>
                  <td>{employee.employeeDOB}</td>
                  <td>{employee.employeeDOJ}</td>
                  <td>{employee.employeeGender}</td>
                  <td>{employee.employeeAddress}</td>
                  <td>{employee.reporterId}</td>
                  <td><button style={{ backgroundColor: "#088220ea", color: "white", border: "none", fontWeight: "bold" }} onClick={() => handleAction(employee.employeeId)} >View Employees</button></td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      )}
    </div>
  );
}