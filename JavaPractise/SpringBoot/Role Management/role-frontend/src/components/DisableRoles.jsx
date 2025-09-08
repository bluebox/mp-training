import React, { useState, useEffect } from "react";
import { useParams, useNavigate } from "react-router-dom";
import { useAuth } from "../context/AuthContext";

export default function DisableRoles() {
  const { getAuthHeader } = useAuth();
  const { id } = useParams();
  const navigate = useNavigate();

  const [rows, setRows] = useState([]); 
  const [selectedMap, setSelectedMap] = useState({}); 

  const load = async () => {
  try {
    const res = await fetch(
      `http://localhost:8080/api/users/${id}/active-role-details`,
      { headers: { "Content-Type": "application/json", ...getAuthHeader() } }
    );

    const contentType = res.headers.get("content-type") || "";
    if (!res.ok) {
      const msg = contentType.includes("application/json")
        ? (await res.json())?.message
        : await res.text();
      alert(msg || `Failed to fetch active roles (${res.status})`);
      return;
    }

    const json = contentType.includes("application/json") ? await res.json() : {};
    setRows(Array.isArray(json.data) ? json.data : []);
    setSelectedMap({});
  } catch (err) {
    console.error("Error fetching user roles:", err);
    alert("Error fetching user roles");
  }
};


  useEffect(() => {
    load();
  }, [id]);

  const toggleRow = (idx) => {
    setSelectedMap((prev) => ({ ...prev, [idx]: !prev[idx] }));
  };

  const handleDisable = async () => {
    const picked = rows
      .map((r, idx) => ({ r, idx }))
      .filter(({ idx }) => selectedMap[idx])
      .map(({ r }) => r);

    if (picked.length === 0) {
      alert("Select at least one role+location to disable");
      return;
    }

    const payload = picked.map((r) => ({
      role: r.role,
      country: r.country,
      state: r.state,
      city: r.city,
    }));

    try {
      const res = await fetch(
        `http://localhost:8080/api/users/${id}/disable-roles-by-location`,
        {
          method: "POST",
          headers: { "Content-Type": "application/json", ...getAuthHeader() },
          body: JSON.stringify(payload),
        }
      );

      const contentType = res.headers.get("content-type") || "";
      let serverMsg = "";
      if (contentType.includes("application/json")) {
        const data = await res.json();
        serverMsg = data?.message || `Disabled: ${data?.disabledCount ?? 0}`;
      } else {
        serverMsg = await res.text();
      }

      if (!res.ok) {
        alert(serverMsg || `Failed to disable roles (${res.status})`);
        return;
      }

      alert(serverMsg || "Roles disabled successfully!");
      await load();
    } catch (err) {
      console.error("Error disabling roles:", err);
      alert("Error disabling roles");
    }
  };

  return (
    <div style={{ padding: 20 }}>
      <h2>Disable Roles for User</h2>

      {rows.length === 0 ? (
        <p>No active roles found.</p>
      ) : (
        <table border="1" cellPadding="5" style={{ width: "100%" }} className="user-table">
          <thead >
            <tr className="table-header">
              <th>Select</th>
              <th>Role</th>
              <th>Country</th>
              <th>State</th>
              <th>City</th>
              <th>Status</th>
            </tr>
          </thead>
          <tbody>
            {rows.map((r, idx) => (
              <tr key={idx}>
                <td>
                  <input
                    type="checkbox"
                    checked={!!selectedMap[idx]}
                    onChange={() => toggleRow(idx)}
                  />
                </td>
                <td>{r.role}</td>
                <td>{r.country}</td>
                <td>{r.state}</td>
                <td>{r.city}</td>
                <td>{r.status}</td>
              </tr>
            ))}
          </tbody>
        </table>
      )}

      <hr />
      <div style={{ display: "flex", gap: 12 }}>
        <button onClick={handleDisable} className="action-btn danger">Disable Selected Roles</button>
        <button onClick={() => navigate(`/assign-roles/${id}`)} className="action-btn">
          Go to Assign
        </button>
      </div>
    </div>
  );
}
