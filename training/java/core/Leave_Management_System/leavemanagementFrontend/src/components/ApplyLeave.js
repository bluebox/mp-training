import React, { useState, useEffect } from "react";
import { useAuth } from "../context/AuthContext";
import api from "../api/api";
import "./ApplyLeave.css";
import LeaveBalance from "./LeaveBalance";

export default function ApplyLeave() {
  const { user } = useAuth();
  const [leaveType, setLeaveType] = useState("");
  const [fromDate, setFromDate] = useState("");
  const [toDate, setToDate] = useState("");
  const [remarks, setRemarks] = useState("");
  const [status, setStatus] = useState("idle");
  const [message, setMessage] = useState("");
  const [refreshBalance, setRefreshBalance] = useState(0);
  const [msgtype, setmsgtype] = useState("");

  const [hasActiveExitRequest, setHasActiveExitRequest] = useState(false);

  const today = new Date().toISOString().split("T")[0];
const thisDate = new Date();
const fiveDaysBefore = new Date();
fiveDaysBefore.setDate(thisDate.getDate() - 5);
const startDate = fiveDaysBefore.toISOString().split("T")[0];

  useEffect(() => {
    const checkExitRequest = async () => {
      try {
        const response = await api.get(`/exit-requests/active/${user.empId}`, { withCredentials: true });
        if (response.data?.active) {
          setHasActiveExitRequest(true);
          setMessage("You have already submitted an exit request. Leave application is disabled.");
          setStatus("info");
        }
      } catch (err) {
        console.error("Error checking active exit request:", err);
      }
    };

    checkExitRequest();
  }, [user.empId]);

  const handleSubmit = async (e) => {
    e.preventDefault();
    setStatus("loading");
    setMessage("");

    if (!leaveType || !fromDate || !toDate || !remarks.trim()) {
      setMessage("All fields are required.");
      setStatus("error");
      return;
    }

    const payload = {
      employeeId: user?.empId,
      leaveType,
      startDate: fromDate,
      endDate: toDate,
      remarks,
      status: "PENDING",
    };

    try {
      await api.post("/leaves/apply", payload, { withCredentials: true });
      setMessage("Leave applied successfully.");
      setStatus("success");

      setLeaveType("");
      setFromDate("");
      setToDate("");
      setRemarks("");

      setRefreshBalance(prev => prev + 1);

    } catch (err) {
      console.error("Error applying leave:", err);
      setMessage(err.response?.data?.message || "Failed to apply leave.");
      setStatus("error");
    }
  };
  const messagecolour = { success: { color: "green" }, error: { color: "red" }, info: { color: "blue" } };

  return (
    <div className="apply-leave-container p-4">
      <LeaveBalance employeeId={user.empId} refreshTrigger={refreshBalance} />

      <h2 className="text-xl font-bold mb-4">Apply Leave</h2>

      <center><strong><p style={status ? messagecolour[status] : {}} >{message}</p></strong></center>

      { }
      {!hasActiveExitRequest && (
        <form onSubmit={handleSubmit} className="space-y-4">
          <div>
            <label className="block">Leave Type</label>
            <select
              className="border p-2 rounded w-full"
              value={leaveType}
              onChange={(e) => setLeaveType(e.target.value)}
              disabled={status === "loading"}
              required
            >
              <option value="">Select</option>
              <option value="CASUAL_LEAVE">Casual Leave</option>
              <option value="SICK_LEAVE">Sick Leave</option>
              <option value="OPTIONAL_LEAVE">Optional Leave</option>
              <option value="EARNED_LEAVE">Earned Leave</option>
            </select>
          </div>

          <div>
            <label className="block">From Date</label>
            <input
              type="date"
              className="border p-2 rounded w-full"
              value={fromDate}
              onChange={(e) => setFromDate(e.target.value)}
              disabled={status === "loading"}
              min={startDate}
              required
            />
          </div>

          <div>
            <label className="block">To Date</label>
            <input
              type="date"
              className="border p-2 rounded w-full"
              value={toDate}
              onChange={(e) => setToDate(e.target.value)}
              disabled={status === "loading"}
              min={fromDate || today}
              required
            />
          </div>

          <div>
            <label className="block">Remarks</label>
            <textarea
              className="border p-2 rounded w-full"
              value={remarks}
              onChange={(e) => setRemarks(e.target.value)}
              disabled={status === "loading"}
              rows={3}
              required
            />
          </div>

          <div className="form-buttons">
            <button
              type="submit"
              className="bg-blue-600 text-white px-4 py-2 rounded hover:bg-blue-700"
              disabled={status === "loading"}
            >
              {status === "loading" ? "Submitting..." : "Apply"}
            </button>
            <button
              type="button"
              className="bg-red-600 text-white px-4 py-2 rounded hover:bg-red-700"
              disabled={status === "loading" || hasActiveExitRequest}
              onClick={() => {
                setLeaveType("");
                setFromDate("");
                setToDate("");
                setRemarks("");
                setMessage("");
                setStatus("idle");
              }}
            >
              Clear
            </button>
          </div>
        </form>
      )}
    </div>
  );
}
