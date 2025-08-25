import React, { useEffect, useState } from "react";
import axios from "axios";
import { useCookies } from "react-cookie";

function Requests() {
  const [cookies] = useCookies(["userData"]);
  const [requests, setRequests] = useState([]);
  const token = cookies.userData?.token;
  const adminId = cookies.userData?.username?.userId;

  useEffect(() => {
    fetchRequests();
  }, []);

  const fetchRequests = async () => {
    try {
      const res = await axios.get(
        `http://localhost:8080/api/issueInsurance/requests/${adminId}`,
        { headers: { Authorization: `Bearer ${token}` } }
      );
      setRequests(res.data);
    } catch (err) {
      console.error(err);
    }
  };

  const acceptRequest = async (issueInsuranceId) => {
    try {
      await axios.post(
        `http://localhost:8080/api/issueInsurance/accept/${issueInsuranceId}`,
        {},
        { headers: { Authorization: `Bearer ${token}` } }
      );
      alert("Request accepted successfully");
      fetchRequests(); 
    } catch (err) {
      console.error(err);
      alert("Failed to accept request");
    }
  };

  return (
    <div className="requests-page">
      <h1>Pending Insurance Requests</h1>
      {requests.length > 0 ? (
        <table className="requests-table">
          <thead>
            <tr>
              <th>IssueInsuranceId</th>
              <th>InsuranceId</th>
              <th>UserId</th>
              <th>Valid Period</th>
              <th>Amount</th>
              <th>Status</th>
              <th>Actions</th>
            </tr>
          </thead>
          <tbody>
            {requests.map((i) => (
              <tr key={i.issueInsuranceId}>
                <td>{i.issueInsuranceId}</td>
                <td>{i.insuranceId}</td>
                <td>{i.userId}</td>
                <td>{i.remainingperiod || i.validPeriod} years</td>
                <td>₹{i.insuranceAmount || i.amountPaid}</td>
                <td>{i.issueInsuranceStatus || i.issueStatus}</td>
                <td>
                  <button onClick={() => acceptRequest(i.issueInsuranceId)}>
                    Accept
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      ) : (
        <p>Norequests.</p>
      )}
    </div>
  );
}

export default Requests;
