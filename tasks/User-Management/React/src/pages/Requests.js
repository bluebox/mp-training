import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import DashboardLayout from '../layouts/DashboardLayout';

function Requests() {
  const [pendingRequests, setPendingRequests] = useState([]);
  const [rejectedRequests, setRejectedRequests] = useState([]);
  const [filter, setFilter] = useState('pending');
  const [loading, setLoading] = useState(false);
  const navigate = useNavigate();

  const fetchRequests = async () => {
    setLoading(true);
    try {
      const response = await fetch('http://localhost:8080/api/users/getUserRequests');
      const data = response.ok ? await response.json() : [];
      setPendingRequests(data.filter(req => req.approval === 'PENDING'));
      setRejectedRequests(data.filter(req => req.approval === 'REJECTED'));
    } catch (error) {
      console.error('Error fetching user requests:', error);
      setPendingRequests([]);
      setRejectedRequests([]);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchRequests();
  }, []);

  const handleReject = async (requestId) => {
    try {
      const response = await fetch(
        `http://localhost:8080/api/users/rejectUser?requestId=${requestId}`,
        { method: 'PUT' }
      );
      if (response.ok) {
        alert("User request rejected");
        fetchRequests();
      } else {
        const errorText = await response.text();
        alert(`Failed to reject user request: ${errorText}`);
      }
    } catch (error) {
      alert("Error occurred: " + error.message);
    }
  };

  const usersToDisplay = filter === 'pending' ? pendingRequests : rejectedRequests;

  const capitalizeFirst = (str) => {
    if (!str) return "";
    return str.charAt(0).toUpperCase() + str.slice(1).toLowerCase();
  };


  if (loading) {
    return (
      <DashboardLayout>
        <p>Loading...</p>
      </DashboardLayout>
    );
  }

  return (
    <DashboardLayout>
      <h2>View User Requests</h2>
      <div className="filter-container">
        <button
          onClick={() => setFilter('pending')}
          className={`filter-button ${filter === 'pending' ? 'pending active-filter' : ''}`}
        >
          Pending
        </button>
        <button
          onClick={() => setFilter('rejected')}
          className={`filter-button ${filter === 'rejected' ? 'rejected active-filter' : ''}`}
        >
          Rejected
        </button>
      </div>

      <table className="data-table">
        <thead>
          <tr>
            <th>Request Id</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Email</th>
            <th>Phone Number</th>
            <th>Gender</th>
            <th>Country</th>
            <th>State</th>
            <th>City</th>
            {filter === 'pending' && <th>Action</th>}
          </tr>
        </thead>
        <tbody>
          {usersToDisplay.length === 0 ? (
            <tr>
              <td colSpan={filter === 'pending' ? 10 : 9} style={{ textAlign: 'center' }}>No {filter} requests found.</td>
            </tr>
          ) : (
            usersToDisplay.map(user => (
              <tr key={user.requestId}>
                <td>{user.requestId}</td>
                <td>{user.firstName}</td>
                <td>{user.lastName}</td>
                <td>{user.email}</td>
                <td>{user.phoneNumber}</td>
                <td>{capitalizeFirst(user.gender)}</td>
                <td>{user.country}</td>
                <td>{user.state}</td>
                <td>{user.city}</td>
                {filter === 'pending' && (
                  <td>
                    <div className="action-column">
                      <button
                        onClick={() => navigate(`/approve/${user.requestId}`)}
                      >Approve & Assign Roles
                      </button>
                      <button
                        className="reject-button"
                        onClick={() => handleReject(user.requestId)}
                      >Reject
                      </button>
                    </div>
                  </td>
                )}
              </tr>
            ))
          )}
        </tbody>
      </table>
    </DashboardLayout>
  );
}

export default Requests;