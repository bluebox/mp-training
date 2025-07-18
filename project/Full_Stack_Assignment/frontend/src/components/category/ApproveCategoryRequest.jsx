import React, { useEffect, useState } from "react";
import { Button, Container, Table, Alert, Form } from "react-bootstrap";
import {
  getCategoryRequests,
  processCategoryRequest,
} from "../../services/CategoryRequestService";
import { useAuth } from "../../context/AuthContext";

const ApproveCategoryRequests = () => {
  const { currentUser } = useAuth();

  const [requests, setRequests] = useState([]);
  const [selectedIds, setSelectedIds] = useState([]);
  const [responseMsg, setResponseMsg] = useState(null);
  const [errorMsg, setErrorMsg] = useState(null);
  const [loading, setLoading] = useState(false);

  const fetchPendingRequests = async () => {
    try {
      setLoading(true);
      const result = await getCategoryRequests(); // Assumes all requests are fetched
      const pendingOnly = result?.filter((r) => r.status === "PENDING") || [];
      setRequests(pendingOnly);
    } catch (error) {
      console.error("Error fetching requests:", error);
      setErrorMsg("Failed to load requests.");
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchPendingRequests();
  }, []);

  const handleSelect = (id) => {
    setSelectedIds((prev) =>
      prev.includes(id) ? prev.filter((i) => i !== id) : [...prev, id]
    );
  };

  const handleSubmit = async (status) => {
    setErrorMsg(null);
    setResponseMsg(null);

    try {
      await processCategoryRequest({
        requestIds: selectedIds,
        approvedBy: currentUser.userId,
        status,
      });

      setResponseMsg(`Category requests ${status.toLowerCase()} successfully.`);
      setSelectedIds([]);
      fetchPendingRequests(); // Refresh table
    } catch (error) {
      console.error("Process Error:", error);
      setErrorMsg(
        error.response?.data?.message || "Failed to process category requests."
      );
    }
  };

  if (loading) {
    return <div className="text-center mt-5">Loading...</div>;
  }
  return (
    <Container className="mt-4">
      <h2>Approve Category Requests</h2>

      {responseMsg && <Alert variant="success">{responseMsg}</Alert>}
      {errorMsg && <Alert variant="danger">{errorMsg}</Alert>}

      <Table striped bordered hover className="mt-3">
        <thead>
          <tr>
            <th>Select</th>
            <th>Request ID</th>
            <th>Category Name</th>
            <th>Requested By</th>
            <th>Created At</th>
          </tr>
        </thead>
        <tbody>
          {requests.length > 0 ? (
            requests.map((r) => (
              <tr key={r.categoryRequestId}>
                <td>
                  <Form.Check
                    type="checkbox"
                    checked={selectedIds.includes(r.categoryRequestId)}
                    onChange={() => handleSelect(r.categoryRequestId)}
                  />
                </td>
                <td>{r.categoryRequestId}</td>
                <td>{r.categoryName}</td>
                <td>{r.requestedBy}</td>
                <td>{new Date(r.createdAtDate).toLocaleString()}</td>
              </tr>
            ))
          ) : (
            <tr>
              <td colSpan="5" className="text-center">
                No pending category requests.
              </td>
            </tr>
          )}
        </tbody>
      </Table>

      <div className="d-flex gap-3 mt-2">
        <Button variant="success" onClick={() => handleSubmit("APPROVED")}>
          Approve Selected
        </Button>
        <Button variant="danger" onClick={() => handleSubmit("DECLINED")}>
          Reject Selected
        </Button>
      </div>
    </Container>
  );
};

export default ApproveCategoryRequests;
