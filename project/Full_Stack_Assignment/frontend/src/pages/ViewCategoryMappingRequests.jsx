import React, { useEffect, useState } from "react";
import {
  getMappingRequests,
  updateMappingRequestStatus,
} from "../services/CategoryMappingService";
import { useAuth } from "../context/AuthContext";

const ViewCategoryMappingRequests = () => {

  const { currentUser } = useAuth();

  const [mappingRequestData, setMappingRequestData] = React.useState([]);
  const [loading, setLoading] = React.useState(false);
  const [requestIds, setRequestIds] = useState("");
  const [status, setStatus] = useState("");
  const [loggedInUser, setLoggedInUser] = useState("");
  const [selectedRequests, setSelectedRequests] = useState([]);

  const handleCheckboxChange = (requestId) => {
    
    setSelectedRequests((prevSelectedRequests) =>
      prevSelectedRequests.includes(requestId)
        ? prevSelectedRequests.filter((id) => id !== requestId)
        : [...prevSelectedRequests, requestId]
    );
  };

  const handleStatusUpdate = async (newStatus) => {
    try {
      setLoading(true);
      await updateMappingRequestStatus(selectedRequests, currentUser.userId , newStatus);
      setSelectedRequests([]);

      setRequestIds("");
      setStatus("");
      setLoggedInUser("");
      fetchMappingRequests();
    } catch (error) {
      console.error(`Error updating status to ${newStatus}:`, error);
    } finally {
      setLoading(false);
    }
  };

  const fetchMappingRequests = async () => {
    try {
      setLoading(true);
      const parsedRequestIds = requestIds
        .split(",")
        .map((id) => parseInt(id.trim()))
        .filter((id) => !isNaN(id));
      const response = await getMappingRequests(
        parsedRequestIds,
        status,
        loggedInUser === "" ? null : parseInt(loggedInUser)
      );
      setMappingRequestData(response.data);
    } catch (error) {
      console.error("Error fetching mapping requests:", error);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchMappingRequests();
  }, []);

  if (loading) {
    return (
      <div className="container mt-4">
        <h2>Category-Product Mapping Requests</h2>
        <p>Loading...</p>
      </div>
    );
  }

  return (
    <div className="container mt-4">
      <h2>Category-Product Mapping Requests</h2>
      <div className="row mb-3">
        <div className="col-md-4">
          <label htmlFor="status" className="form-label">
            Status:
          </label>
          <select
            className="form-select"
            id="status"
            value={status}
            onChange={(e) => setStatus(e.target.value)}
          >
            <option value="">All</option>
            <option value="PENDING">PENDING</option>
            <option value="APPROVED">APPROVED</option>
            <option value="DECLINED">DECLINED</option>
          </select>
        </div>
        <div className="col-md-4">
          <label htmlFor="loggedInUser" className="form-label">
            Requested By User ID:
          </label>
          <input
            type="number"
            className="form-control"
            id="loggedInUser"
            value={loggedInUser}
            onChange={(e) => setLoggedInUser(e.target.value)}
            placeholder="Enter User ID:"
          />
        </div>
      </div>
      <div className="row mb-3">
        <div className="col">
          <button className="btn btn-primary" onClick={fetchMappingRequests}>
            Search
          </button>
        </div>
      </div>
      <table border="1" cellPadding="10" className="table table-striped">
        <thead>
          <tr>
            <th>ID</th>
            <th>Product ID</th>
            <th>Category ID</th>
            <th>Requested By</th>
            <th>Status</th>
            <th>Approved By</th>
            <th>Created At</th>
            <th>Updated At</th>
            {status === "PENDING" && <th>Select</th>}
          </tr>
        </thead>
        <tbody>
          {mappingRequestData.map((request) => (
            <tr key={request.mappingRequestId}>
              <td>{request.mappingRequestId}</td>
              <td>{request.productId}</td>
              <td>{request.categoryId}</td>
              <td>{request.requestedBy}</td>
              <td>{request.status}</td>
              <td>{request.approvedBy ?? "—"}</td>
              <td>{new Date(request.createdAtDate).toLocaleString()}</td>
              <td>{new Date(request.updatedAtDate).toLocaleString()}</td>
              {(request.status === "PENDING") && (
                <td>
                  <input
                    type="checkbox"
                    checked={selectedRequests.includes(
                      request.mappingRequestId
                    )}
                    onChange={() =>
                      handleCheckboxChange(request.mappingRequestId)
                    }
                  />
                </td>
              )}
            </tr>
          ))}
          {mappingRequestData.length === 0 && (
            <tr>
              <td colSpan="9" style={{ textAlign: "center" }}>
                No mapping requests found
              </td>
            </tr>
          )}
        </tbody>
      </table>
      {selectedRequests.length > 0 && (
        <div className="mt-3">
          <button
            className="btn btn-success me-2"
            onClick={() => handleStatusUpdate("APPROVED")}
          >
            Accept Selected
          </button>
          <button
            className="btn btn-danger"
            onClick={() => handleStatusUpdate("DECLINED")}
          >
            Reject Selected
          </button>
        </div>
      )}
    </div>
  );
};

export default ViewCategoryMappingRequests;
