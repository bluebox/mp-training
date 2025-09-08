import React, { useEffect, useState, useMemo } from "react";
import { useNavigate } from "react-router-dom";
import { useAuth } from "../context/AuthContext";
import { Button, Space, Tag } from "antd";
import { FunnelPlotOutlined, CloseCircleOutlined } from "@ant-design/icons";
import dayjs from "dayjs";
import isBetween from "dayjs/plugin/isBetween"; 
import FilterModal from "./FilterModal";
import "../styles/ActiveUsers.css";

export default function ActiveUsers() {
  const [users, setUsers] = useState([]);
  const [filters, setFilters] = useState({});
  const [filterModalOpen, setFilterModalOpen] = useState(false);
  const navigate = useNavigate();
  const { getAuthHeader } = useAuth();
  dayjs.extend(isBetween);

  const fetchUsers = async () => {
  try {
    const res = await fetch("http://localhost:8080/api/enrollments/getallactiveusers", {
      headers: { "Content-Type": "application/json", ...getAuthHeader() },
    });
    const json = await res.json();
    setUsers(Array.isArray(json.data) ? json.data : []);
  } catch (err) {
    console.error("Error fetching users:", err);
  }
};


  useEffect(() => {
    fetchUsers();
  }, []);

  const filteredUsers = useMemo(() => {
    return users.filter((u) => {
      if (filters.userId && !String(u.userId)?.toLowerCase().includes(filters.userId)) return false;
      if (filters.username && !u.username?.toLowerCase().includes(filters.username.toLowerCase())) return false;
      if (filters.firstName && !u.firstName?.toLowerCase().includes(filters.firstName.toLowerCase())) return false;
      if (filters.lastName && !u.lastName?.toLowerCase().includes(filters.lastName.toLowerCase())) return false;
      if (filters.age && u.age !== Number(filters.age)) return false;
      if (filters.gender && u.gender !== filters.gender) return false;
      if (filters.empId && !u.empId?.includes(filters.empId)) return false;
      if (filters.mobile && !u.mobile?.includes(filters.mobile)) return false;
      if (filters.email && !u.email?.toLowerCase().includes(filters.email.toLowerCase())) return false;
      if (filters.state && !u.state?.toLowerCase().includes(filters.state.toLowerCase())) return false;
      if (filters.city && !u.city?.toLowerCase().includes(filters.city.toLowerCase())) return false;
      if (filters.status && u.activeStatus !== filters.status) return false;
      
      if (filters.createdRange) {
        let [start, end] = filters.createdRange;
        if (end.isBefore(start, "day")) [start, end] = [end, start];

        const created = dayjs(u.createdAt);
        if (
          !created.isBetween(
            start.startOf("day"), 
            end.endOf("day"), 
            null, 
            "[]"
          )
        ) return false;
      }

      if (filters.updatedRange) {
        let [start, end] = filters.updatedRange;
        if (end.isBefore(start, "day")) [start, end] = [end, start];

        const updated = dayjs(u.updatedAt);
        if (
          !updated.isBetween(
            start.startOf("day"), 
            end.endOf("day"), 
            null, 
            "[]"
          )
        ) return false;
      }


      return true;
    });
  }, [users, filters]);

  const appliedTags = Object.entries(filters).filter(([_, v]) => v);

  const clearSingle = (key) => {
    const next = { ...filters };
    delete next[key];
    setFilters(next);
  };

  return (
    <div className="active-users">
      <div className="header-bar">
        <h2>Active Users</h2>
        <Button
          type="primary"
          icon={<FunnelPlotOutlined />}
          onClick={() => setFilterModalOpen(true)}
          className="filter-btn"
        >
          Filters
        </Button>
      </div>

      {appliedTags.length > 0 && (
        <div className="applied-tags">
          <Space wrap>
            {appliedTags.map(([key, val]) => (
              <Tag
                key={key}
                closable
                onClose={() => clearSingle(key)}
                closeIcon={<CloseCircleOutlined />}
              >
                <b>{key}:</b>{" "}
                {Array.isArray(val)
                  ? `${val[0].format("YYYY-MM-DD")} - ${val[1].format("YYYY-MM-DD")}`
                  : val}
              </Tag>
            ))}
          </Space>
        </div>
      )}

      <table className="user-table">
        <thead>
          <tr className="table-header">
            <th>User ID</th><th>First Name</th><th>Last Name</th><th>Username</th>
            <th>Age</th><th>Gender</th><th>Emp ID</th><th>Mobile</th>
            <th>Email</th><th>State</th><th>City</th><th>User Status</th>
            <th>Created Time</th><th>Updated Time</th><th>Created By</th><th>Updated By</th><th>Roles</th><th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {filteredUsers.length > 0 ? (
            filteredUsers.map((u) => (
              <tr key={u.userId}>
                <td>{u.userId}</td><td>{u.firstName}</td><td>{u.lastName}</td>
                <td>{u.username}</td><td>{u.age}</td><td>{u.gender.charAt(0).toUpperCase() + u.gender.slice(1).toLowerCase()}</td>
                <td>{u.empId}</td><td>{u.mobile}</td><td>{u.email}</td>
                <td>{u.state}</td><td>{u.city}</td><td>{u.activeStatus.charAt(0).toUpperCase() + u.activeStatus.slice(1).toLowerCase()}</td>
                <td>{u.createdAt}</td><td>{u.updatedAt}</td>
                <td>{u.createdBy}</td><td>{u.updatedBy}</td>
                <td>{Array.isArray(u.roles) ? u.roles.join(", ") : "-"}</td>
                <td>
                  <button className="action-btn" onClick={() => navigate(`/assign-roles/${u.userId}`)}>Assign Roles</button>
                  <button className="action-btn danger" onClick={() => navigate(`/disable-roles/${u.userId}`)}>Disable Roles</button>
                </td>
              </tr>
            ))
          ) : (
            <tr>
              <td colSpan="16" style={{ textAlign: "center", color: "red" }}>
                No matching users found
              </td>
            </tr>
          )}
        </tbody>
      </table>

      <FilterModal
        open={filterModalOpen}
        onClose={() => setFilterModalOpen(false)}
        filters={filters}
        setFilters={setFilters}
      />
    </div>
  );
}
