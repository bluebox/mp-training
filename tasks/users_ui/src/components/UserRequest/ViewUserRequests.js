import React, { useState, useEffect, useMemo } from 'react';
import './ViewUserRequests.css';

const ViewUserRequests = ({ onUpdateClick, onNavigate }) => {
  const [members, setMembers] = useState([]);
  const [isLoading, setIsLoading] = useState(true);
  const [error, setError] = useState(null);
  const [selectedMembers, setSelectedMembers] = useState([]);
  const [message, setMessage] = useState('');
  const [messageType, setMessageType] = useState('success');
  const [searchText, setSearchText] = useState('');

  const [filterCountry, setFilterCountry] = useState('');
  const [filterState, setFilterState] = useState('');
  const [filterCity, setFilterCity] = useState('');
  const [filterStatus, setFilterStatus] = useState('');
  const [filterUserStatus, setFilterUserStatus] = useState('');
  const [filterGender, setFilterGender] = useState('');
  
  const [showFilters, setShowFilters] = useState(false);
  
  	const [currentPage, setCurrentPage] = useState(1);

  	 const itemsPerPage = 5; 
  	 const totalPages = Math.ceil(members.length / itemsPerPage);

  	 const currentMembers = useMemo(() => {
  	   const startIndex = (currentPage - 1) * itemsPerPage;
  	   const endIndex = startIndex + itemsPerPage;
  	   return members.slice(startIndex, endIndex);
  	 }, [members, currentPage, itemsPerPage]);
  	 
    

  const fetchMembers = async () => {
    setIsLoading(true);
    setError(null);
    setMessage('');
    try {
      const response = await fetch('/user/getusers', {
        method: 'GET',
        headers: { 'Content-Type': 'application/json' },
      });
      if (response.ok) {
        const data = await response.json();
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
    fetchMembers();
  }, []);

  const handleSelectMember = (requestId) => {
    setSelectedMembers(prev =>
      prev.includes(requestId) ? prev.filter(id => id !== requestId) : [...prev, requestId]
    );
  };

  const handleSelectAll = (event) => {
    if (event.target.checked) {
      setSelectedMembers(members.map(m => m.requestId));
    } else {
      setSelectedMembers([]);
    }
  };

  const handleDelete = async (requestId) => {
    if (!window.confirm(`Are you sure you want to Reject User ${requestId}?`)) return;
    try {
      const response = await fetch(`/user/reject/${requestId}`, { method: 'PUT' });
      if (response.ok) {
        setMessage(`User ${requestId} rejected.`);
        setMessageType('success');
        await fetchMembers();
        setSelectedMembers(prev => prev.filter(id => id !== requestId));
      } else {
		const errorData = await response.text();
		 setMessage(errorData);
		 setMessageType('error');
      }
    } catch (err) {
      setMessage(`Error Rejecting User ${requestId}.`);
      setMessageType('error');
    }
  };

  const handleUpdate = async (requestId) => {
    if (!window.confirm(`Are you sure you want to Approve User ${requestId}?`)) return;
    try {
      const response = await fetch(`/user/aprove/${requestId}`, { method: 'PUT' });
      if (response.ok) {
        setMessage(`User ID ${requestId} approved.`);
        setMessageType('success');
        await fetchMembers();
        setSelectedMembers(prev => prev.filter(id => id !== requestId));
      } else {
		const errorData = await response.text();
		 setMessage(errorData);
		 setMessageType('error');
      }
    } catch (err) {
      console.error(err);
      setMessage(`Error Approving User ID ${requestId}.`);
      setMessageType('error');
    }
  };
  
  
  const handleChangeStatus = async (requestId, currentStatus) => {
    const newStatus = currentStatus === 'ACTIVE' ? 'INACTIVE' : 'ACTIVE';
    if (!window.confirm(`Are you sure you want to change status of User ${requestId} to ${newStatus.charAt(0).toUpperCase() + newStatus.slice(1).toLowerCase() }?`)) return;

    try {
      const response = await fetch(`/user/changestatus/${requestId}`, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ status: newStatus }),
      });
      if (response.ok) {
        setMessage(`User ${requestId} status updated to ${newStatus}.`);
        setMessageType('success');
        await fetchMembers();
      } else {
		const errorData = await response.text();
		 setMessage(errorData);
		 setMessageType('error');
      }
    } catch (err) {
      setMessage(`Error updating status for User ${requestId}.`);
      setMessageType('error');
    }
  };


  const formatDate = (dateString) => {
    if (!dateString) return 'N/A';
    const date = new Date(dateString);
    return date.toLocaleString();
  };
  
  const uniqueGenders = useMemo(() => {
	return [...new Set(currentMembers.map(m => m.gender).filter(Boolean))].sort();
  },[currentMembers]);

  const uniqueCountries = useMemo(() => {
    return [...new Set(currentMembers.map(m => m.country).filter(Boolean))].sort();
  }, [currentMembers]);

  const uniqueStates = useMemo(() => {
    const states = currentMembers
      .filter(m => (filterCountry ? m.country === filterCountry : true))
      .map(m => m.state)
      .filter(Boolean);
    return [...new Set(states)].sort();
  }, [currentMembers, filterCountry]);

  const uniqueCities = useMemo(() => {
    const cities = currentMembers
      .filter(m => (filterCountry ? m.country === filterCountry : true))
      .filter(m => (filterState ? m.state === filterState : true))
      .map(m => m.city)
      .filter(Boolean);
    return [...new Set(cities)].sort();
  }, [currentMembers, filterCountry, filterState]);

  const uniqueStatuses = useMemo(() => {
    return [...new Set(currentMembers.map(m => m.status).filter(Boolean))].sort();
  }, [currentMembers]);

  const uniqueUserStatuses = useMemo(() => {
    return [...new Set(currentMembers.map(m => m.aprovedStatus).filter(Boolean))].sort();
  }, [currentMembers]);

  const filteredMembers = useMemo(() => {
    const txt = searchText.trim().toLowerCase();
    return currentMembers.filter(m => {
      const row = `${m.requestId} ${m.username} ${m.email} ${m.firstName} ${m.lastName} ${m.gender} ${m.phoneNumber} ${m.country} ${m.state} ${m.city} ${m.postalCode} ${m.status} ${m.aprovedStatus}`.toLowerCase();

      return (
        (!txt || row.includes(txt)) &&
		(!filterGender || m.gender === filterGender) &&
        (!filterCountry || m.country === filterCountry) &&
        (!filterState || m.state === filterState) &&
        (!filterCity || m.city === filterCity) &&
        (!filterStatus || m.status === filterStatus) &&
        (!filterUserStatus || m.aprovedStatus === filterUserStatus)
      );
    });
  }, [currentMembers, searchText, filterGender ,filterCountry, filterState, filterCity, filterStatus, filterUserStatus]);

 
  if (isLoading) return <div className="members-loading appnav-content">Loading members...</div>;
  if (error) return <div className="members-error appnav-content">Error: {error}</div>;

  return (
    <div className="members-container">
      <div className="members-card">

        <div className="members-topbar">
          <h1 className="members-header">All Users Requests</h1>

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
			 <button className="filter-btn" onClick={() => setShowFilters(true)}>Filters</button>
          </div>
		  
        </div>
		
		{showFilters && (
		  <div className="filter-modal">
		    <div className="filter-content">
		      <h2>Filters</h2>

		      <div className="filter-item">
		        <label>Gender:</label>
		        <select value={filterGender} onChange={(e) => setFilterGender(e.target.value)}>
		          <option value="">All</option>
				  {uniqueGenders.map(g => <option key={g} value={g}>{g.charAt(0).toUpperCase() + g.slice(1).toLowerCase()}</option>)}
		        </select>
		      </div>

		      <div className="filter-item">
		        <label>Country:</label>
		        <select value={filterCountry} onChange={(e) => { setFilterCountry(e.target.value); setFilterState(''); setFilterCity(''); }}>
		          <option value="">All</option>
		          {uniqueCountries.map(c => <option key={c} value={c}>{c}</option>)}
		        </select>
		      </div>

		      <div className="filter-item">
		        <label>State:</label>
		        <select value={filterState} onChange={(e) => { setFilterState(e.target.value); setFilterCity(''); }}>
		          <option value="">All</option>
		          {uniqueStates.map(s => <option key={s} value={s}>{s}</option>)}
		        </select>
		      </div>

		      <div className="filter-item">
		        <label>City:</label>
		        <select value={filterCity} onChange={(e) => setFilterCity(e.target.value)}>
		          <option value="">All</option>
		          {uniqueCities.map(c => <option key={c} value={c}>{c}</option>)}
		        </select>
		      </div>

		      <div className="filter-item">
		        <label>Status:</label>
		        <select value={filterStatus} onChange={(e) => setFilterStatus(e.target.value)}>
		          <option value="">All</option>
				  {uniqueStatuses.map(s => <option key={s} value={s}>{s.charAt(0).toUpperCase() + s.slice(1).toLowerCase()}</option>)}
		        </select>
		      </div>

		      <div className="filter-item">
		        <label>Request Status:</label>
		        <select value={filterUserStatus} onChange={(e) => setFilterUserStatus(e.target.value)}>
		          <option value="">All</option>
				  {uniqueUserStatuses.map(us => <option key={us} value={us}>{us.charAt(0).toUpperCase() + us.slice(1).toLowerCase()}</option>)}
		        </select>
		      </div>

		      <div className="filter-actions">
		        <button onClick={() => {
		          setFilterCountry('');
		          setFilterState('');
		          setFilterCity('');
		          setFilterStatus('');
		          setFilterUserStatus('');
		          setFilterGender('');
				  setShowFilters(false);
		        }} className="delete-btn">Reset</button>

		        <button onClick={() => setShowFilters(false)} className="update-btn">Apply</button>
		      </div>
		    </div>
		</div>
		)}

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
                <th>Request ID</th>
                <th>USER NAME</th>
                <th>Email ID</th>
                <th>First Name</th>
                <th>Last Name</th>
				<th>Gender</th>
                <th>Phone Number</th>
                <th>Country</th>
                <th>State</th>
                <th>City</th>
                <th>Postal Code</th>
                <th>Status</th>
                <th>User Status</th>
				<th>Request Status</th>
                <th>Actions</th>
                <th>Created At</th>
                <th>Updated At</th>
              </tr>
            </thead>
            <tbody>
              {filteredMembers.length > 0 ? (
                filteredMembers.map(member => (
                  <tr key={member.requestId}>
                    <td>
                      <input
                        type="checkbox"
                        checked={selectedMembers.includes(member.requestId)}
                        onChange={() => handleSelectMember(member.requestId)}
                      />
                    </td>
                    <td>{member.requestId}</td>
                    <td>{member.username}</td>
                    <td>{member.email}</td>
                    <td>{member.firstName}</td>
                    <td>{member.lastName}</td>
					<td>{member.gender !== null ? member.gender.charAt(0).toUpperCase() + member.gender.slice(1).toLowerCase() : member.gender}</td>
                    <td>{member.phoneNumber}</td>
                    <td>{member.country}</td>
                    <td>{member.state}</td>
                    <td>{member.city}</td>
                    <td>{member.postalCode}</td>
					<td>{member.status !== null ? member.status.charAt(0).toUpperCase() + member.status.slice(1).toLowerCase() : member.status}</td>

					<td>
				      <button 
				        onClick={() => handleChangeStatus(member.requestId, member.status)}
				        className={member.status === 'ACTIVE' ? 'delete-btn' : 'update-btn'}
						disabled={member.aprovedStatus === "APROVED" || member.aprovedStatus === "REJECTED"  }
				      >
				        {member.status === 'ACTIVE' ? 'Deactivate' : 'Activate'}
				      </button>
				    </td>
					<td>{member.aprovedStatus !== null ? member.aprovedStatus.charAt(0).toUpperCase() + member.aprovedStatus.slice(1).toLowerCase() : member.aprovedStatus}</td>

                    <td className="view-members-actions-column">
                      <button
                        onClick={() => handleUpdate(member.requestId)}
                        disabled={member.aprovedStatus === "APROVED" || member.aprovedStatus === "REJECTED" 
							           || member.status !== "ACTIVE"}
                        className="update-btn"
                      >
                        Approve
                      </button>
                      <button
                        onClick={() => handleDelete(member.requestId)}
                        disabled={member.aprovedStatus === "APROVED" || member.aprovedStatus === "REJECTED" 
							|| member.status !== "ACTIVE"}
                        className="delete-btn"
                      >
                        Reject
                      </button>
                    </td>
                    <td>{formatDate(member.created_at)}</td>
                    <td>{formatDate(member.updated_at)}</td>
                  </tr>
                ))
              ) : (
                <tr><td colSpan="18" className="no-members">No User Requests found.</td></tr>
              )}
            </tbody>
          </table>
		  
		  <div className="page-contol">
			<div className="pagination-controls">
			  <button onClick={() => setCurrentPage(prev => Math.max(1, prev - 1))} disabled={currentPage === 1}>
			    Previous
			  </button>
			  {Array.from({ length: totalPages }, (_, i) => (
			    <button
			      key={i + 1}
			      onClick={() => setCurrentPage(i + 1)}
			      className={currentPage === i + 1 ? 'active' : ''}
			    >
			      {i + 1}
			    </button>
			  ))}
			  <button onClick={() => setCurrentPage(prev => Math.min(totalPages, prev + 1))} disabled={currentPage === totalPages}>
			    Next
			  </button>
			</div>
			</div>
		  
        </div>

        <p className="message-label" style={{ color: messageType === 'error' ? 'red' : 'green' }}>
          {message}
        </p>

        <div className="button-group" >
          
          <button onClick={fetchMembers} className="refresh-btn">Refresh</button>
        </div>
      </div>
    </div>
  );
};

export default ViewUserRequests;
