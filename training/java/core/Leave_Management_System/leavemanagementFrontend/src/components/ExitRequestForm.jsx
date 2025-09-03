import React, { useState } from "react";
import { useAuth } from "../context/AuthContext";
import api from "../api/api";
import "./ExitRequest.css";

export default function ExitRequestForm() {
  const { user } = useAuth();
  const [exitDate, setExitDate] = useState("");
  const [remarks, setRemarks] = useState("");
  const [status, setStatus] = useState("idle");
  const [message, setMessage] = useState("");
  const [showConfirm, setShowConfirm] = useState(false);
  const [pendingRequest, setPendingRequest] = useState(null);

  const today = new Date().toISOString().split("T")[0];

  const handleSubmit = async (e, cancelFutureLeaves = false) => {
    if (e) e.preventDefault();
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
      await api.post(
        `/exit-requests/create?cancelFutureLeaves=${cancelFutureLeaves}`, { withCredentials: true },
        requestData
      );
      setMessage("Exit request submitted successfully.");
      setStatus("success");
      setExitDate("");
      setRemarks("");
      setShowConfirm(false);
      setPendingRequest(null);
    } catch (err) {
      const errorMsg =
        err.response?.data?.message || "Failed to submit exit request.";
      if (errorMsg.includes("You have approved future leaves")) {
        setShowConfirm(true);
        setPendingRequest(requestData);
      } else {
        setMessage(errorMsg);
        setStatus("error");
      }
    }
  };

  const handleConfirmYes = async () => {
    if (pendingRequest) {
      try {
        await api.post(`/exit-requests/create?cancelFutureLeaves=true`, pendingRequest, { withCredentials: true });
        setMessage("Exit request submitted and future leaves cancelled.");
        setStatus("success");
        setExitDate("");
        setRemarks("");
      } catch (err) {
        setMessage(err.response?.data?.message || "Failed to submit exit request.");
        setStatus("error");
      }
    }
    setShowConfirm(false);
    setPendingRequest(null);
  };

  const handleConfirmNo = () => {
    setShowConfirm(false);
    setPendingRequest(null);
    setExitDate("");
    setRemarks("");
    setMessage("Exit request cancelled by user.");
    setStatus("idle");
  };

  return (
    <div className="exit-request-tabs-container">
      <h2>Create Exit Request</h2>
      {message && <div className={`status-message ${status}`}>{message}</div>}

      <form className="exit-form" onSubmit={handleSubmit}>
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


        <div className="exit-form-buttons" style={{ display: "flex", gap: "10px", marginTop: "10px" }}>
          <button type="submit" disabled={status === "loading"} className="submit-button">
            {status === "loading" ? "Submitting..." : "Submit"}
          </button>
          <button
            type="button"
            className="cancel-button"
            disabled={showConfirm}
            onClick={() => {
              setExitDate("");
              setRemarks("");
              setMessage("");
              setStatus("idle");
            }}
          >
            Clear
          </button>
        </div>
      </form>


      {showConfirm && (
        <div className="confirm-popup" style={{ marginTop: "15px" }}>
          <p>
            You have approved future leaves. Submitting exit will cancel them. Proceed?
          </p>
          <div style={{ display: "flex", gap: "10px", marginTop: "10px" }}>
            <button onClick={handleConfirmYes} className="submit-button">
              Yes
            </button>
            <button onClick={handleConfirmNo} className="cancel-button">
              No
            </button>
          </div>
        </div>
      )}
    </div>
  );
}
