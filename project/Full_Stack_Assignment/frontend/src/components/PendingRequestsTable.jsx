import React, { useEffect, useState, useRef, useCallback } from "react";
import { useAuth } from "../context/AuthContext";
import {
  approveRequests,
  getPendingRequests,
  rejectRequests,
} from "../services/productApi";

const PendingRequestsTable = () => {
  const [allRequests, setAllRequests] = useState([]);
  const [visibleRequests, setVisibleRequests] = useState([]);
  const [selectedIds, setSelectedIds] = useState([]);
  const [message, setMessage] = useState("");
  const [currentPage, setCurrentPage] = useState(1);
  const { currentUser } = useAuth();
  const managerId = currentUser.userId;

  const pageSize = 5;
  const observerRef = useRef();

  useEffect(() => {
    fetchPending();
  }, []);

  const fetchPending = async () => {
    try {
      const response = await getPendingRequests();
      if (Array.isArray(response)) {
        setAllRequests(response);
        setCurrentPage(1);
        setVisibleRequests(response.slice(0, pageSize));
      } else {
        console.error("Expected array, got:", response);
      }
    } catch (err) {
      console.error("Error fetching pending requests:", err);
    }
  };

  const loadMore = () => {
    const nextPage = currentPage + 1;
    const nextData = allRequests.slice(0, nextPage * pageSize);
    if (nextData.length > visibleRequests.length) {
      setVisibleRequests(nextData);
      setCurrentPage(nextPage);
    }
  };

  const lastRowRef = useCallback(
    (node) => {
      if (observerRef.current) observerRef.current.disconnect();
      observerRef.current = new IntersectionObserver((entries) => {
        if (entries[0].isIntersecting) {
          loadMore();
        }
      });
      if (node) observerRef.current.observe(node);
    },
    [visibleRequests]
  );

  const toggleSelection = (id) => {
    setSelectedIds((prev) =>
      prev.includes(id) ? prev.filter((i) => i !== id) : [...prev, id]
    );
  };

  const handleAction = async (type) => {
    if (selectedIds.length === 0) return;

    try {
      if (type === "approve") {
        await approveRequests(selectedIds, managerId);
      } else {
        await rejectRequests(selectedIds, managerId);
      }

      setMessage(`Successfully ${type}d selected requests.`);
      setSelectedIds([]);
      fetchPending();
    } catch (err) {
      console.error(`${type} failed:`, err);
      setMessage(`${type} failed`);
    }
  };

  return (
    <div
      className="card bg-dark text-white p-3 mb-4 shadow"
      style={{ maxHeight: "500px", overflowY: "auto" }}
    >
      <h4>Pending Product Requests</h4>
      {message && <div className="alert alert-info mt-2">{message}</div>}
      <table className="table table-dark table-striped table-bordered mt-3">
        <thead>
          <tr>
            <th></th>
            <th>Product Name</th>
            <th>Description</th>
            <th>Price</th>
            <th>Quantity</th>
            <th>Requested By</th>
            <th>Requested At</th>
          </tr>
        </thead>
        <tbody>
          {visibleRequests.map((req, idx) => {
            const isLast = idx === visibleRequests.length - 1;
            return (
              <tr key={req.productRequestId} ref={isLast ? lastRowRef : null}>
                <td>
                  <input
                    type="checkbox"
                    checked={selectedIds.includes(req.productRequestId)}
                    onChange={() => toggleSelection(req.productRequestId)}
                  />
                </td>
                <td>{req.productName}</td>
                <td>{req.description}</td>
                <td>{req.price}</td>
                <td>{req.quantity}</td>
                <td>{req.requestedBy}</td>
                <td>{new Date(req.createdAtDate).toLocaleString()}</td>
              </tr>
            );
          })}
        </tbody>
      </table>
      <div className="d-flex gap-2">
        <button
          className="btn btn-success"
          onClick={() => handleAction("approve")}
        >
          Approve Selected
        </button>
        <button
          className="btn btn-danger"
          onClick={() => handleAction("reject")}
        >
          Reject Selected
        </button>
      </div>
    </div>
  );
};

export default PendingRequestsTable;
