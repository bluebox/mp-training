import React, { useEffect } from "react";
import { getMappingRequests } from "../services/CategoryMappingService";

const ViewMappingRequests = () => {
  const [mappingRequestData, setMappingRequestData] = React.useState([]);
  const [loading, setLoading] = React.useState(false);

  const fetchMappingRequests = async () => {
    try {
      setLoading(true);
      const response = await getMappingRequests();
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
            </tr>
          ))}
          {mappingRequestData.length === 0 && (
            <tr>
              <td colSpan="8" style={{ textAlign: "center" }}>
                No mapping requests found
              </td>
            </tr>
          )}
        </tbody>
      </table>
    </div>
  );
};

export default ViewMappingRequests;
