import React, { useEffect, useState } from "react";
import { getCategoryRequests } from "../../services/CategoryRequestService";

const ViewCategoryRequests = () => {
  const [requestData, setRequestData] = useState([]);
  const [loading, setLoading] = useState(false);
  const [errorMsg, setErrorMsg] = useState(null);

  // Search states
  const [searchId, setSearchId] = useState("");
  const [searchName, setSearchName] = useState("");
  const [searchStatus, setSearchStatus] = useState("");

  const fetchRequests = async () => {
    try {
      setLoading(true);
      setErrorMsg(null);
      const response = await getCategoryRequests();
      setRequestData(response || []);
    } catch (error) {
      console.error("Error fetching category requests:", error);
      setErrorMsg("Failed to load category requests.");
      setRequestData([]);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchRequests();
  }, []);

  // Filtered data based on search
  const filteredData = requestData.filter((request) => {
    const matchesId =
      searchId === "" ||
      String(request.categoryRequestId)
        .toLowerCase()
        .includes(searchId.toLowerCase());
    const matchesName =
      searchName === "" ||
      (request.categoryName &&
        request.categoryName.toLowerCase().includes(searchName.toLowerCase()));
    const matchesStatus =
      searchStatus === "" ||
      (request.status &&
        request.status.toLowerCase().includes(searchStatus.toLowerCase()));
    return matchesId && matchesName && matchesStatus;
  });

  return (
    <div className="container mt-4">
      <h2>Category Requests</h2>

      {/* Search Fields */}
      <div className="row mb-3">
        <div className="col">
          <input
            type="text"
            className="form-control"
            placeholder="Search by ID"
            value={searchId}
            onChange={(e) => setSearchId(e.target.value)}
          />
        </div>
        <div className="col">
          <input
            type="text"
            className="form-control"
            placeholder="Search by Category Name"
            value={searchName}
            onChange={(e) => setSearchName(e.target.value)}
          />
        </div>
        <div className="col">
          <input
            type="text"
            className="form-control"
            placeholder="Search by Status"
            value={searchStatus}
            onChange={(e) => setSearchStatus(e.target.value)}
          />
        </div>
      </div>

      {loading && <p>Loading...</p>}

      {errorMsg && (
        <div className="alert alert-danger" role="alert">
          {errorMsg}
        </div>
      )}

      {!loading && !errorMsg && (
        <table border="1" cellPadding="10" className="table table-striped">
          <thead>
            <tr>
              <th>ID</th>
              <th>Category Name</th>
              <th>Requested By</th>
              <th>Status</th>
              <th>Approved By</th>
              <th>Created At</th>
              <th>Updated At</th>
            </tr>
          </thead>
          <tbody>
            {filteredData && filteredData.length > 0 ? (
              filteredData.map((request) => (
                <tr key={request.categoryRequestId}>
                  <td>{request.categoryRequestId}</td>
                  <td>{request.categoryName}</td>
                  <td>{request.requestedBy}</td>
                  <td>{request.status}</td>
                  <td>{request.approvedBy ?? "—"}</td>
                  <td>
                    {request.createdAtDate
                      ? new Date(request.createdAtDate).toLocaleString()
                      : "—"}
                  </td>
                  <td>
                    {request.updatedAtDate
                      ? new Date(request.updatedAtDate).toLocaleString()
                      : "—"}
                  </td>
                </tr>
              ))
            ) : (
              <tr>
                <td colSpan="7" style={{ textAlign: "center" }}>
                  No category requests found.
                </td>
              </tr>
            )}
          </tbody>
        </table>
      )}
    </div>
  );
};

export default ViewCategoryRequests;

