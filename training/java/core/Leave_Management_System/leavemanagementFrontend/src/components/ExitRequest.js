import React, { useState, useEffect, useCallback } from "react";
import { useAuth } from "../context/AuthContext";
import api from "../api/api";
import "./ExitRequest.css";

export default function ExitRequestTabs() {
  const { user } = useAuth();
  const [tab, setTab] = useState("create");
  const [exitRequests, setExitRequests] = useState([]);
  const [remarks, setRemarks] = useState("");
  const [exitDate, setExitDate] = useState("");
  const [status, setStatus] = useState("idle");
  const [message, setMessage] = useState("");

  const today = new Date().toISOString().split("T")[0];

  useEffect(() => {
    setMessage("");
    setStatus("idle");
  }, [tab]);

  const fetchMyRequests = useCallback(async () => {
    if (!user?.empId) return;

    setStatus("loading");
    setMessage("");

    try {
      const res = await api.get(`/exit-requests/get/${user.empId}`,{withCredentials: true});
      setExitRequests(res.data.exitRequests || []);
      setStatus("success");
    } catch (err) {
      console.error("Failed to fetch exit requests:", err);
      setMessage(
        err.response?.data?.message || "Failed to fetch exit requests."
      );
      setStatus("error");
    }
  }, [user]);

  useEffect(() => {
    if (tab === "view") fetchMyRequests();
  }, [tab, fetchMyRequests]);

  const handleSubmit = async (e) => {
    e.preventDefault();
    setStatus("loading");
    setMessage("");

    if (!exitDate || !remarks.trim()) {
      setMessage("Exit date and remarks are required.");
      setStatus("error");
      return;
    }

    const requestData = {
      employeeId: user.empId,
      exitDate,
      remarks,
      requestStatus: "PENDING",
      requestSubmittedAt: new Date().toISOString(),
      adminRemarks: ""
    };

    try {
      const res = await api.post(
        `/exit-requests/create?cancelFutureLeaves=false`,{withCredentials: true},
        requestData,{withCredentials: true}
      );

      setMessage(res.data.message || "Exit request submitted successfully.");
      setStatus("success");
      setExitDate("");
      setRemarks("");
      await fetchMyRequests();
    } catch (err) {
      const errorMessage =
        err.response?.data?.message || "Failed to submit exit request.";
      console.error("Error submitting exit request:", errorMessage);

      if (errorMessage.includes("approved future leaves")) {
        const confirmCancel = window.confirm(
          "You have approved future leaves. Submitting exit will cancel them. Do you want to proceed?"
        );

        if (confirmCancel) {
          try {
            const res = await api.post(
              `/exit-requests/create?cancelFutureLeaves=true`,{withCredentials: true},
              requestData
            );
            setMessage(
              res.data.message +
                (res.data.futureLeavesCancelled
                  ? " (Future approved leaves were cancelled and balance restored.)"
                  : "")
            );
            setStatus("success");
            setExitDate("");
            setRemarks("");
            await fetchMyRequests();
          } catch (finalErr) {
            setMessage(
              finalErr.response?.data?.message ||
                "Failed to submit exit request."
            );
            setStatus("error");
          }
        } else {

          setExitDate("");
          setRemarks("");
          setMessage(
            "Exit request not submitted because you chose not to cancel your future leaves."
          );
          setStatus("idle");
        }
      } else {
        setMessage(errorMessage);
        setStatus("error");
      }
    }
  };

  const handleCancel = async (requestId) => {
    setStatus("loading");
    setMessage("");
    try {
      await api.put(`/exit-requests/cancel/${requestId}`,{withCredentials: true});
      setMessage("Exit request canceled successfully.");
      setStatus("success");
      await fetchMyRequests();
    } catch (err) {
      console.error("Error canceling exit request:", err);
      setMessage(
        err.response?.data?.message || "Failed to cancel exit request."
      );
      setStatus("error");
    }
  };

  return (
    <div className="exit-request-tabs-container">
      <div className="tab-buttons">
        <button
          className={tab === "create" ? "active" : ""}
          onClick={() => setTab("create")}
        >
          Create Exit Request
        </button>
        <button
          className={tab === "view" ? "active" : ""}
          onClick={() => setTab("view")}
        >
          View Exit Requests
        </button>
      </div>

      {message && <div className={`status-message ${status}`}>{message}</div>}

      {tab === "create" && (
        <form onSubmit={handleSubmit} className="exit-form">
          <div className="form-group">
            <label htmlFor="exitDate">Intended Exit Date</label>
            <input
              type="date"
              id="exitDate"
              value={exitDate}
              onChange={(e) => setExitDate(e.target.value)}
              disabled={status === "loading"}
              min={today}
            />
          </div>
          <div className="form-group">
            <label htmlFor="remarks">Remarks</label>
            <textarea
              id="remarks"
              value={remarks}
              onChange={(e) => setRemarks(e.target.value)}
              disabled={status === "loading"}
              rows={3}
            />
          </div>
          <button
            type="submit"
            disabled={status === "loading"}
            className="submit-button"
          >
            {status === "loading" ? "Submitting..." : "Submit"}
          </button>
        </form>
      )}

      {tab === "view" && (
        <table className="exit-request-table">
          <thead>
            <tr>
              <th>Exit Date</th>
              <th>Status</th>
              <th>Action</th>
            </tr>
          </thead>
          <tbody>
            {exitRequests.length === 0 ? (
              <tr>
                <td colSpan="3">No exit requests found.</td>
              </tr>
            ) : (
              exitRequests.map((req) => (
                <tr key={req.requestId}>
                  <td>{new Date(req.exitDate).toLocaleDateString()}</td>
                  <td>{req.requestStatus}</td>
                  <td>
                    {req.requestStatus === "PENDING" ? (
                      <button
                        className="cancel-button"
                        onClick={() => handleCancel(req.requestId)}
                        disabled={status === "loading"}
                      >
                        Cancel
                      </button>
                    ) : (
                      "-"
                    )}
                  </td>
                </tr>
              ))
            )}
          </tbody>
        </table>
      )}
    </div>
  );
}
