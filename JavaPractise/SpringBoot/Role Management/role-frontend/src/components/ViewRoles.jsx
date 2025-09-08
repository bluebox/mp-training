import React, { useEffect, useState } from "react";
import { Button } from "antd";
import { useAuth } from "../context/AuthContext";

export default function ViewRoles() {
  const [roles, setRoles] = useState([]);
  const { getAuthHeader } = useAuth();

  const fetchRoles = async () => {
    try {
      const res = await fetch("http://localhost:8080/api/users/getallroles", {
        headers: { "Content-Type": "application/json", ...getAuthHeader() },
      });

      if (!res.ok) {
        throw new Error(`Server error: ${res.status}`);
      }

      const json = await res.json();

      if (json.success) {
        setRoles(Array.isArray(json.data) ? json.data : []);
      } else {
        console.error("API returned error:", json.message);
        setRoles([]);
      }
    } catch (err) {
      console.error("Error fetching roles:", err);
      setRoles([]);
    }
  };

  const toggleRoleStatus = async (roleId, currentStatus) => {
    const newStatus = currentStatus === "ACTIVE" ? "INACTIVE" : "ACTIVE";

    try {
      const res = await fetch(`http://localhost:8080/api/users/roles/${roleId}/status`, {
        method: "PUT",
        headers: {
          "Content-Type": "application/json",
          ...getAuthHeader(),
        },
        body: JSON.stringify({ status: newStatus }),
      });

      const json = await res.json();

      if (json.success) {
        fetchRoles(); 
      } else {
        console.error("Failed to update role:", json.message);
      }
    } catch (err) {
      console.error("Error updating role:", err);
    }
  };

  useEffect(() => {
    fetchRoles();
  }, []);

  return (
    <div className="roles-page">
      <h2>Roles</h2>
      <table className="roles-table">
        <thead>
          <tr className="table-header">
            <th>Role ID</th>
            <th>Role Name</th>
            <th>Status</th>
            <th>Action</th>
          </tr>
        </thead>
        <tbody>
          {roles.length > 0 ? (
            roles.map((r) => (
              <tr key={r.roleId}>
                <td>{r.roleId}</td>
                <td>{r.roleName}</td>
                <td>{r.status === "ACTIVE" ? "Active" : "Inactive"}</td>
                <td>
                  <Button
                    type={r.status === "ACTIVE" ? "primary" : "default"}
                    danger={r.status === "ACTIVE"}
                    onClick={() => toggleRoleStatus(r.roleId, r.status)}
                  >
                    {r.status === "ACTIVE" ? "Inactivate" : "Activate"}
                  </Button>
                </td>
              </tr>
            ))
          ) : (
            <tr>
              <td colSpan="4" style={{ textAlign: "center", color: "red" }}>
                No roles found
              </td>
            </tr>
          )}
        </tbody>
      </table>
    </div>
  );
}
