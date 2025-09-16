import React, { useState, useEffect, useMemo } from 'react';
/*import './ViewUserRequests.css';
*/
import { useParams } from 'react-router-dom';




const UserAssignedRoles = ({auth , onNavigate} ) => {
  const [members, setMembers] = useState([]);
  const [isLoading, setIsLoading] = useState(true);
  const [error, setError] = useState(null);
  const [selectedMembers, setSelectedMembers] = useState([]);
  const [message, setMessage] = useState('');
  const [messageType, setMessageType] = useState('success');
  const [searchText, setSearchText] = useState('');
  
  const { userId } = useParams();


  const fetchMembers = async () => {
    setIsLoading(true);
    setError(null);
    setMessage('');
    try {
		
		const credentials = btoa(`${auth.userCode}:${auth.password}`);


		const response = await fetch(`/role/getUserAssignedRoles/${userId}`,{
		  method: 'GET',
		  headers: {
		    'Content-Type': 'application/json',
		    'Authorization': `Basic ${credentials}`
		  },
		});
		
/*      const response = await fetch(`/role/getUserAssignedRoles/${userId}`);
*/      if (response.ok) {
        const data = await response.json();
		console.log(data);
        setMembers(Array.isArray(data) ? data : []);
      } else {
		const errorData = await response.text();
		 setMessage(errorData);
		 setMessageType('error');
      }
    } catch (err) {
      setError('Failed to fetch members.');
      setMessage('Failed to fetch members.');
      setMessageType('error');
    } finally {
      setIsLoading(false);
    }
  };

  useEffect(() => {
	if(!auth) return;
    fetchMembers();
  }, [auth]);

  const handleSelectMember = (userId) => {
    setSelectedMembers(prev =>
      prev.includes(userId) ? prev.filter(id => id !== userId) : [...prev, userId]
    );
  };

  const handleSelectAll = (event) => {
    if (event.target.checked) {
      setSelectedMembers(members.map(m => m.userId));
    } else {
      setSelectedMembers([]);
    }
  };


  const formatDate = (dateString) => {
    if (!dateString) return 'N/A';
    const date = new Date(dateString);
    return date.toLocaleString();
  };

  const filteredMembers = useMemo(() => {
    const txt = searchText.trim().toLowerCase();
    if (!txt) return members;
    return members.filter(m => {
      const row = `${m.userId} ${m.username} ${m.email} ${m.firstName} ${m.lastName} ${m.phoneNumber} ${m.country} ${m.state} ${m.city} ${m.postalCode} ${m.status} ${m.aprovedStatus}`.toLowerCase();
      return row.includes(txt);
    });
  }, [members, searchText]);
  

  if (isLoading) return <div>Loading roles...</div>;
  if (error) return <div>Error: {error}</div>;

  return (
    <div className="members-container">
      <h1 className="members-header">Roles Assigned to User {userId}</h1>

      <div className="right-section">
        <div className="search-container">
          <label htmlFor="search" className="form-label">Search:</label>
          <input
            type="text"
            id="search"
            placeholder="Search by any field..."
            className="form-input"
            value={searchText}
            onChange={e => setSearchText(e.target.value)}
          />
        </div>
      </div>

      <div className="table-wrapper">
        <table className="members-table" id="dataTable">
          <thead>
            <tr>
              <th>
                <input
                  type="checkbox"
                  onChange={handleSelectAll}
                  checked={selectedMembers.length === members.length && members.length > 0}
                />
              </th>
              <th>User ID</th>
			  <th>Name of User</th>
              <th>Role ID</th>
			  <th>Role Name</th>
			  <th>Role Status</th>
			  <th>Change Role Status</th>
              <th>Country</th>
              <th>State</th>
              <th>City</th>
			  <th>Created At</th>
			  <th>Updated At</th>
            </tr>
          </thead>
          <tbody>
            {filteredMembers.length > 0 ? (
              filteredMembers.map(member => (
                <tr key={member.userId}>
                  <td>
                    <input
                      type="checkbox"
                      checked={selectedMembers.includes(member.userId)}
                      onChange={() => handleSelectMember(member.userId)}
                    />
                  </td>
                  <td>{member.usercode}</td>
				  <td>{member.usernameString}</td>
                  <td>{member.rolecodes}</td>
				  <td>{member.roleName}</td>
				  <td>{member.statusString.charAt(0).toUpperCase() + member.statusString.slice(1).toLowerCase() }</td>
				  <td className="view-members-actions-column">
	  			    <button
	  			      onClick={async () => {
	  			        try {
						console.log(member);
	  					 const credentials = btoa(`${auth.userCode}:${auth.password}`);

	  			         const res = await fetch(`/role/changeUserRoleStatus` ,{
	  				 		  method: 'POST',
	  				 		  headers: {
	  				 		    'Content-Type': 'application/json',
	  				 		    'Authorization': `Basic ${credentials}`
	  				 		  },
							  body: JSON.stringify(member),
	  				 		});
						
						if (res.ok) {
					        setMessage("Status updated successfully.");
					        setMessageType("success");
					        fetchMembers();
					      } else {
					        const errorText = await res.text();
					        setMessage(`Failed to change status: ${errorText}`);
					        setMessageType("error");
					      }
					    } catch (err) {
					      setMessage("Failed to change status.");
					      setMessageType("error");
					    }
	  			      }}
	  			      className={member.statusString === "ACTIVE" ? "delete-btn" : "update-btn"}
	  			    >
	  			      {member.statusString === "ACTIVE" ? "Deactivate" : "Activate"}
	  			    </button>
					</td>
                  <td>{member.country}</td>
                  <td>{member.state}</td>
                  <td>{member.city}</td>
				  <td>{formatDate(member.created_at)}</td>
				  <td>{formatDate(member.updated_at)}</td>
                </tr>
              ))
            ) : (
              <tr><td colSpan="17" className="noResultsMessage">No Assigned Roles found.</td></tr>
            )}
          </tbody>
        </table>
      </div>

      <p className="message-label" style={{ color: messageType === 'error' ? 'red' : 'green' }}>
        {message}
      </p>

      <div className="button-group">
        <button onClick={fetchMembers} className="refresh-btn">Refresh</button>
        <button onClick={() => onNavigate('mainUsers')} className="back-btn">Back to Users</button>
      </div>
    </div>
  );
};

export default UserAssignedRoles;
