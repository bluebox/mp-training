import React, { useEffect, useState } from "react";
import { listEmployees } from "./scripts/AdminService";
import { useNavigate } from "react-router-dom";
import '../styles/Employees.css';

export default function Employees() {
  const [employees, setEmployees] = useState([]);
  const navigate = useNavigate();
  const [searchTerm, setSearchTerm] = useState("");

  useEffect(() => {
    listEmployees()
      .then((response) => {
        setEmployees(response.data);
      })
      .catch((error) => {
        console.error(error);
      });
  }, []);

  const filteredRequests = employees.filter((req) => {
    const matchesSearch = searchTerm
      ? Object.values(req)
        .join(" ")
        .toLowerCase()
        .includes(searchTerm.toLowerCase())
      : true;
    return matchesSearch;
  });



  const handleAction = async (employeeid) => {
    navigate(`/admindashboard/employee-attendence/${employeeid}`);
  };

  return (
    <div className="employees-container">
      <h2 className="employees-heading">LIST OF EMPLOYEES</h2>

      <div style={{ marginBottom: "10px", display: "flex", gap: "10px", flexWrap: "wrap" }}>
        <input
          type="text"
          placeholder="Search Employee..."
          value={searchTerm}
          onChange={(e) => setSearchTerm(e.target.value)}
          style={{
            padding: "6px 10px",
            borderRadius: "5px",
            border: "1px solid #d1d5db",
            flex: "1",
          }}
        /></div>

      <div className="table-responsive">
        <table className="employees-table">
          <thead>
            <tr>
              <th>Employee Id</th>
              <th>Employee Name</th>
              <th>Email</th>
              <th>Role</th>
              <th>Mobile No</th>
              <th>Date Of Birth</th>
              <th>Date Of Join</th>
              <th>Gender</th>
              <th>Address</th>
              <th>Reporting To Employee</th>
              <th>View Attendence</th>
            </tr>
          </thead>
          <tbody>
            {employees.length === 0 ? (
              <tr>
                <td colSpan={11} style={{ textAlign: 'center' }}>No employees available</td>
              </tr>
            ) : (
              filteredRequests.map((employee) => (
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
                  <td><button style={{ backgroundColor: "#088220ea", color: "white", border: "none", fontWeight: "bold" }} onClick={() => handleAction(employee.employeeId)} >View Attendence</button></td>
                </tr>
              )))}
          </tbody>
        </table>
      </div>
    </div>
  );
}