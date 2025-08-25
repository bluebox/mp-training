import React, { useEffect, useState } from 'react';
import { useCookies } from 'react-cookie';
import axios from 'axios';
import './IssueInsurance.css'; 

function IssueInsurance() {
  const [cookies] = useCookies(['userData']);
  const [issuedInsurances, setIssuedInsurances] = useState([]);
  const [statusFilter, setStatusFilter] = useState('ALL');
  const token = cookies.userData?.token;
  const adminId = cookies.userData?.username?.userId;

  useEffect(() => {
    fetchIssuedInsurances();
  }, [statusFilter]);

  const fetchIssuedInsurances = async () => {
    try {
      const url =
        statusFilter === 'ALL'
          ? `http://localhost:8080/api/issueInsurance/all`
          : `http://localhost:8080/api/issueInsurance/status/${statusFilter}`;

      const res = await axios.get(url, {
        headers: { Authorization: `Bearer ${token}` },
      });

      const adminInsurances = res.data.filter((i) => i.adminId === adminId);

      const mappedInsurances = adminInsurances.map((i) => ({
        ...i,
        insuranceType: i.insuranceType || i.type || `Type ${i.insuranceId}`,
        insuranceAmount: i.insuranceAmount || i.amountPaid || 0,
        validPeriod: i.remainingperiod || i.validPeriod || 0,
      }));

      setIssuedInsurances(mappedInsurances);
    } catch (err) {
      console.error('Error fetching issued insurances:', err);
      setIssuedInsurances([]);
    }
  };

  const handleStatusChange = (e) => {
    setStatusFilter(e.target.value);
  };

  return (
    <div className="container">
      <h1 className="title">Issue Insurance Operations</h1>

      <div className="filter">
        <label htmlFor="statusFilter">Filter by status: </label>
        <select
          id="statusFilter"
          className="select"
          value={statusFilter}
          onChange={handleStatusChange}
        >
          <option value="ALL">All</option>
          <option value="ACTIVE">Active</option>
          <option value="NOTACCEPTED">NotAccepted</option>
          <option value="PENDING">Pending</option>
        </select>
      </div>

      {issuedInsurances.length > 0 ? (
        <table className="table">
          <thead>
            <tr>
              <th>InsuranceId</th>
              <th>Insurance Type</th>
              <th>Issued To (Userid)</th>
              <th>Valid Period</th>
              <th>Amount</th>
            </tr>
          </thead>
          <tbody>
            {issuedInsurances.map((i) => (
              <tr key={i.issueInsuranceId}>
                <td>{i.insuranceId}</td>
                <td>{i.insuranceType}</td>
                <td>{i.userId}</td>
                <td>{i.validPeriod} years</td>
                <td>₹{i.insuranceAmount}</td>
              </tr>
            ))}
          </tbody>
        </table>
      ) : (
        <p>No issued insurances </p>
      )}
    </div>
  );
}

export default IssueInsurance;
