import { useEffect, useState } from 'react';
import DashboardLayout from '../layouts/DashboardLayout';

function MainUsers() {
  const [approvedUsers, setApprovedUsers] = useState([]);
  const [loading, setLoading] = useState(false);
  const [searchText, setSearchText] = useState('');
  const [searchField, setSearchField] = useState('all'); 

  const fetchApprovedUsersData = async () => {
    setLoading(true);
    try {
      const userName = 'U002';
      const password = 'tBfzr500';
      const encodedCredentials = btoa(`${userName}:${password}`);
      const usersResponse = await fetch('http://localhost:8080/api/users/getMainUsers', {
        method: 'GET',
        headers: {
          'Authorization': `Basic ${encodedCredentials}`
        }
      });
      const usersData = usersResponse.ok ? await usersResponse.json() : [];
      setApprovedUsers(usersData);
    } catch (error) {
      console.error('Error occurred while getting approved users:', error);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchApprovedUsersData();
  }, []);

  const handleStatusChange = async (userName) => {
    try {
      const response = await fetch(
        `http://localhost:8080/api/users/changeStatus?userName=${userName}`,
        { method: 'PUT' }
      );
      if (response.ok) {
        alert("User status changed successfully");
        fetchApprovedUsersData();
      } else {
        const errorText = await response.text();
        alert(`Failed to change user status: ${errorText}`);
      }
    } catch (error) {
      alert("Error occurred: " + error.message);
    }
  };
  
  const capitalizeFirst = (str) => {
    if (!str) return "";
    return str.charAt(0).toUpperCase() + str.slice(1).toLowerCase();
  };
  
  const getFilteredApprovedUsers = () => {
    if (!searchText.trim()) {
      return approvedUsers;
    }
    return approvedUsers.filter(user => {
      if (searchField === 'all') {
        const values = [
          user.userName,
          user.firstName,
          user.lastName,
          user.email,
          user.phoneNumber,
          user.gender,
          user.country,
          user.state,
          user.city,
          user.pinCode,
          user.status
        ];
        return values.some(val => val && val.toString().toLowerCase().includes(searchText.toLowerCase()));
      } else {
        const val = user[searchField];
        return val && val.toString().toLowerCase().includes(searchText.toLowerCase());
      }
    });
  };

  const filteredApprovedUsers = getFilteredApprovedUsers();

  return (
    <DashboardLayout>
      <h2>View Main Users</h2>
      {loading ? (
        <p>Loading...</p>
      ) : (
        <>
          <div style={{ display: 'flex', gap: '1rem', alignItems: 'center' }}>
            <input
              type="text"
              placeholder="Search users..."
              value={searchText}
              onChange={e => setSearchText(e.target.value)}
              className="search-input"
            />
            <select
              value={searchField}
              onChange={e => setSearchField(e.target.value)}
              className="search-dropdown"
            >
              <option value="all">All fields</option>
              <option value="userName">User Name</option>
              <option value="firstName">First Name</option>
              <option value="lastName">Last Name</option>
              <option value="email">Email</option>
              <option value="phoneNumber">Phone</option>
              <option value="gender">Gender</option>
              <option value="country">Country</option>
              <option value="state">State</option>
              <option value="city">City</option>
              <option value="pinCode">Pin Code</option>
              <option value="status">Status</option>
            </select>
          </div>
          <table className="data-table">
            <thead>
              <tr>
                <th>User Name</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Email</th>
                <th>Phone Number</th>
                <th>Gender</th>
                <th>Country</th>
                <th>State</th>
                <th>City</th>
                <th>Pin Code</th>
                <th>Roles</th>
                <th>Status</th>
              </tr>
            </thead>
            <tbody>
              {filteredApprovedUsers.length === 0 ? (
                <tr>
                  <td colSpan={13} style={{ textAlign: 'center' }}>No approved users found</td>
                </tr>
              ) : (
                filteredApprovedUsers
                  .map(user => (
                    <tr key={user.userName}>
                      <td>{user.userName}</td>
                      <td>{user.firstName}</td>
                      <td>{user.lastName}</td>
                      <td>{user.email}</td>
                      <td>{user.phoneNumber}</td>
                      <td>{capitalizeFirst(user.gender)}</td>
                      <td>{user.country}</td>
                      <td>{user.state}</td>
                      <td>{user.city}</td>
                      <td>{user.pinCode}</td>
                      <td>
                        <button
                          onClick={() => window.location.href = `/updateUserRoles/${user.userName}`}
                        >
                          View / Update Roles
                        </button>
                      </td>
                      <td>
                        <button
                          onClick={() => handleStatusChange(user.userName)}
                        >
                          {user.status === "ACTIVE" ? "Make Inactive" : "Make Active"}
                        </button>
                      </td>
                    </tr>
                  ))
              )}
            </tbody>
          </table>
        </>
      )}
    </DashboardLayout>
  );
}

export default MainUsers;