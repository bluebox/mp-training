import React, { useState, useEffect, useMemo } from 'react';
/*import './ViewUserRequests.css';
*/

const MainUsers = ({ auth, onUpdateClick , onViewAssignedRoles ,onNavigate }) => {
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
		
		const credentials = btoa(`${auth.userCode}:${auth.password}`);

		const response = await fetch('/main/getMainUsers',{
		  method: 'GET',
		  headers: {
		    'Content-Type': 'application/json',
		    'Authorization': `Basic ${credentials}`
		  },
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
	if (!auth) return;
    fetchMembers();
  }, [auth]);

  const handleSelectMember = (userCode) => {
    setSelectedMembers(prev =>
      prev.includes(userCode) ? prev.filter(id => id !== userCode) : [...prev, userCode]
    );
  };

  const handleSelectAll = (event) => {
    if (event.target.checked) {
      setSelectedMembers(members.map(m => m.userCode));
    } else {
      setSelectedMembers([]);
    }
  };


   const handleUpdate = async (userCode) => {
	   onUpdateClick(userCode);
  };
  
 const handleViewRoles = async (userCode) => {
	onViewAssignedRoles(userCode);
 };

  const formatDate = (dateString) => {
    if (!dateString) return 'N/A';
    const date = new Date(dateString);
    return date.toLocaleString();
  };

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

  const uniqueGenders = useMemo(() => {
  return [...new Set(currentMembers.map(m => m.gender).filter(Boolean))].sort();
  },[currentMembers]);
 
  const filteredMembers = useMemo(() => {
    const txt = searchText.trim().toLowerCase();
    const anyFilter = filterCountry || filterState || filterCity || filterStatus || filterUserStatus || filterGender;

    if (!txt && !anyFilter) return currentMembers;

    return currentMembers.filter(m => {
      const row = `${m.userCode} ${m.username} ${m.email} ${m.firstName} ${m.lastName} ${m.gender} ${m.phoneNumber} ${m.country} ${m.state} ${m.city} ${m.postalCode} ${m.status} ${m.aprovedStatus}`.toLowerCase();

      const matchesSearch = !txt || row.includes(txt);
      const matchesCountry = !filterCountry || m.country === filterCountry;
      const matchesState = !filterState || m.state === filterState;
      const matchesCity = !filterCity || m.city === filterCity;
      const matchesStatus = !filterStatus || m.status === filterStatus;
      const matchesUserStatus = !filterUserStatus || m.aprovedStatus === filterUserStatus;
	  const matchesGender = !filterGender || m.gender === filterGender

      return matchesSearch && matchesCountry && matchesState && matchesCity && matchesStatus && matchesUserStatus && matchesGender;
    });
  }, [currentMembers, searchText, filterCountry, filterState, filterCity, filterStatus, filterUserStatus,filterGender]);


  if (isLoading) return <div>Loading users...</div>;
  if (error) return <div>Error: {error}</div>;

  return (
    <div className="members-container">
      <h1 className="members-header">All Users</h1>

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
		  
		  <button className="filter-btn" onClick={() => setShowFilters(true)}>Filters</button>
		  
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
            <select
              value={filterCountry}
              onChange={(e) => {
                setFilterCountry(e.target.value);
                setFilterState('');
                setFilterCity('');
              }}
            >
              <option value="">All</option>
              {uniqueCountries.map(c => <option key={c} value={c}>{c}</option>)}
            </select>
          </div>

          <div className="filter-item">
            <label>State:</label>
            <select
              value={filterState}
              onChange={(e) => {
                setFilterState(e.target.value);
                setFilterCity('');
              }}
            >
              <option value="">All</option>
              {uniqueStates.map(s => <option key={s} value={s}>{s}</option>)}
            </select>
          </div>

          <div className="filter-item">
            <label>City:</label>
            <select
              value={filterCity}
              onChange={(e) => setFilterCity(e.target.value)}
            >
              <option value="">All</option>
              {uniqueCities.map(c => <option key={c} value={c}>{c}</option>)}
            </select>
          </div>

          <div className="filter-item">
            <label>Status:</label>
            <select
              value={filterStatus}
              onChange={(e) => setFilterStatus(e.target.value)}
            >
              <option value="">All</option>
			  {uniqueStatuses.map(s => <option key={s} value={s}>{s.charAt(0).toUpperCase() + s.slice(1).toLowerCase()}</option>)}
            </select>
          </div>

          <div className="filter-actions">
            <button onClick={() => {
              setFilterCountry('');
              setFilterState('');
              setFilterCity('');
              setFilterStatus('');
              setFilterUserStatus('');
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
              <th>User ID</th>
              <th>USER NAME</th>
              <th>Email</th>
              <th>First Name</th>
              <th>Last Name</th>
			  <th>Gender</th>
              <th>Phone Number</th>
              <th>Country</th>
              <th>State</th>
              <th>City</th>
              <th>Postal Code</th>
              <th>Status</th>
              <th>Actions</th>
              <th>Created At</th>
              <th>Updated At</th>
            </tr>
          </thead>
          <tbody>
            {filteredMembers.length > 0 ? (
              filteredMembers.map(member => (
                <tr key={member.userCode}>
                  <td>
                    <input
                      type="checkbox"
                      checked={selectedMembers.includes(member.userCode)}
                      onChange={() => handleSelectMember(member.userCode)}
                    />
                  </td>
                  <td>{member.userCode}</td>
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
                  <td className="view-members-actions-column">
                    <button
                      onClick={() => handleUpdate(member.userCode)}
                      className="update-btn"
                    >
                      Assign Roles
                    </button>
					<button
                      onClick={() => handleViewRoles(member.userCode)}
                      className="update-btn"
                    >
                      View Assign Roles
                    </button>
                  </td>
                  <td>{formatDate(member.created_at)}</td>
                  <td>{formatDate(member.updated_at)}</td>
                </tr>
              ))
            ) : (
              <tr><td colSpan="17" className="no-members">No users found.</td></tr>
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
		
      </div>

      <p className="message-label" style={{ color: messageType === 'error' ? 'red' : 'green' }}>
        {message}
      </p>

      <div className="button-group">
        <button onClick={fetchMembers} className="refresh-btn">Refresh</button>
      </div>
    </div>

  );
};

export default MainUsers;
