import React, { useState } from "react";
import axios from "axios";

const UpdateMember = () => {
  const [searchMobile, setSearchMobile] = useState("");
  const [member, setMember] = useState(null);
  const [loading, setLoading] = useState(false);
  const [message, setMessage] = useState("");
  const [error, setError] = useState("");

  const fetchMember = async (e) => {
    e.preventDefault();
    setLoading(true);
    setMessage("");
    setError("");
    try {
      const response = await axios.get(
        `http://localhost:8080/api/members/mobile/${searchMobile}`
      );
      setMember(response.data);
    } catch {
      setMember(null);
      setError("❌ Member not found or error occurred.");
    } finally {
      setLoading(false);
    }
  };

  const updateMember = async (e) => {
    e.preventDefault();
    setLoading(true);
    setMessage("");
    setError("");
    try {
      await axios.put(
        `http://localhost:8080/api/members/${member.memberId}`,
        member
      );
      setMessage("✅ Member updated successfully!");
    } catch {
      setError("❌ Failed to update member.");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="container mt-4">
      <div className="card shadow p-4">
        <h2 className="card-title mb-3">Update Member</h2>

        {/* Search Form */}
        <form onSubmit={fetchMember} className="mb-3">
          <div className="mb-2">
            <label className="form-label">Search by Mobile Number:</label>
            <input
              type="text"
              value={searchMobile}
              onChange={(e) => setSearchMobile(e.target.value)}
              required
              pattern="\d{10}"
              title="Enter 10-digit mobile number"
              className="form-control"
            />
          </div>
          <button type="submit" className="btn btn-primary" disabled={loading}>
            {loading ? "Searching..." : "Fetch Member"}
          </button>
        </form>

        <hr />

        {/* Update Form */}
        {member && (
          <form onSubmit={updateMember}>
            <div className="mb-2">
              <label className="form-label">Name:</label>
              <input
                type="text"
                value={member.name}
                onChange={(e) => setMember({ ...member, name: e.target.value })}
                className="form-control"
                required
              />
            </div>

            <div className="mb-2">
              <label className="form-label">Email:</label>
              <input
                type="email"
                value={member.email}
                onChange={(e) => setMember({ ...member, email: e.target.value })}
                className="form-control"
                required
              />
            </div>

            <div className="mb-2">
              <label className="form-label">Mobile:</label>
              <input
                type="text"
                value={member.mobile}
                onChange={(e) => setMember({ ...member, mobile: e.target.value })}
                className="form-control"
                required
                pattern="\d{10}"
                title="Enter 10-digit mobile number"
              />
            </div>

            <div className="mb-2">
              <label className="form-label">Gender:</label>
              <select
                value={member.gender}
                onChange={(e) => setMember({ ...member, gender: e.target.value })}
                className="form-select"
                required
              >
                <option value="">--Select--</option>
                <option value="Male">Male</option>
                <option value="Female">Female</option>
                <option value="Other">Other</option>
              </select>
            </div>

            <div className="mb-3">
              <label className="form-label">Address:</label>
              <textarea
                rows="3"
                value={member.address}
                onChange={(e) => setMember({ ...member, address: e.target.value })}
                className="form-control"
                required
              ></textarea>
            </div>

            <button type="submit" className="btn btn-success" disabled={loading}>
              {loading ? "Updating..." : "Update Member"}
            </button>
          </form>
        )}

        {/* Messages */}
        {message && <div className="alert alert-success mt-3">{message}</div>}
        {error && <div className="alert alert-danger mt-3">{error}</div>}
      </div>
    </div>
  );
};

export default UpdateMember;
