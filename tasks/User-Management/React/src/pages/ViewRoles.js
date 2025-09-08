import React, { useEffect, useState } from 'react';
import DashboardLayout from '../layouts/DashboardLayout';

function ViewRoles() {
  const [roles, setRoles] = useState([]);
  const [loading, setLoading] = useState(false);
  const [conflictingRoles, setConflictingRoles] = useState([]);

  const fetchRoles = async () => {
    setLoading(true);
    try {
      const response = await fetch('http://localhost:8080/api/roles/getRoles');
      if (response.ok) {
        const data = await response.json();
        setRoles(data);
      } else {
        console.error('Failed to fetch roles:', response.statusText);
      }
    } catch (error) {
      console.error('Error fetching roles:', error);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchRoles();
    fetch('http://localhost:8080/api/roles/getConflictingRoles')
      .then(res => res.ok ? res.json() : [])
      .then(data => setConflictingRoles(data || []))
      .catch(() => setConflictingRoles([]));
  }, []);

  const handleStatusChange = async (roleId) => {
    try {
      const response = await fetch(
        `http://localhost:8080/api/roles/changeRoleStatus?roleId=${roleId}`,
        {
          method: 'PUT',
        }
      );
      if (response.ok) {
        alert("Role status changed successfully");
        fetchRoles();
      } else {
        const errorText = await response.text();
        alert(`Failed to change role status: ${errorText}`);
      }
    } catch (error) {
      alert("Error occurred: " + error.message);
    }
  };

  if (loading) {
    return (
      <DashboardLayout>
        <p>Loading roles...</p>
      </DashboardLayout>
    );
  }
  const capitalizeFirst = (str) => {
    if (!str) return "";
    return str.charAt(0).toUpperCase() + str.slice(1).toLowerCase();
  };

  const getRoleName = (roleId) => {
    const role = roles.find(r => String(r.roleId) === String(roleId));
    return role ? role.roleName : roleId;
  };

  return (
    <DashboardLayout>
      <div style={{ display: 'flex', alignItems: 'flex-start' }}>
        <div className="view-roles-container">
          <h2>Roles</h2>
          <table border="1" cellPadding="8" className='data-table'>
            <thead>
              <tr>
                <th>Role ID</th>
                <th>Role Name</th>
                <th>Status</th>
                <th>Action</th>
              </tr>
            </thead>
            <tbody>
              {roles.length === 0 ? (
                <tr>
                  <td colSpan="4" style={{ textAlign: 'center' }}>No roles found</td>
                </tr>
              ) : (
                roles.map(role => (
                  <tr key={role.roleId}>
                    <td>{role.roleId}</td>
                    <td>{capitalizeFirst(role.roleName)}</td>
                    <td>{capitalizeFirst(role.status)}</td>
                    <td>
                      <button onClick={() => handleStatusChange(role.roleId)}>
                        {role.status === 'ACTIVE' ? 'Deactivate' : 'Activate'}
                      </button>
                    </td>
                  </tr>
                ))
              )}
            </tbody>
          </table>
        </div>
        <div className="view-roles-container">
          <h2>Conflicting Roles</h2>
          <table border="1" cellPadding="8" className='data-table'>
            <thead>
              <tr>
                <th>Role 1</th>
                <th>Role 2</th>
              </tr>
            </thead>
            <tbody>
              {conflictingRoles.length === 0 ? (
                <tr>
                  <td colSpan="2" style={{ textAlign: 'center' }}>No conflicts found</td>
                </tr>
              ) : (
                conflictingRoles.map((pair, idx) => {
                  const [id1, id2] = pair.split(',');
                  return (
                    <tr key={idx}>
                      <td>{capitalizeFirst(getRoleName(id1))}</td>
                      <td>{capitalizeFirst(getRoleName(id2))}</td>
                    </tr>
                  );
                })
              )}
            </tbody>
          </table>
        </div>
      </div>
    </DashboardLayout>
  );
}

export default ViewRoles;