import { useParams, useNavigate } from "react-router-dom";
import { useState, useEffect } from 'react';

const API_KEY = "RzRLZGVseDhBMDd5bm9jOE8wNXk5elFjN1NGcVUzSnZNalRqRWNyTA==";

function Approval() {
  const { requestId } = useParams();
  const navigate = useNavigate();
  const [user, setUser] = useState(null);
  const [loading, setLoading] = useState(true);
  const [availableRoles, setAvailableRoles] = useState([]);
  const [assignedRoles, setAssignedRoles] = useState([]);
  const [countries, setCountries] = useState([]);
  const [states, setStates] = useState([]);
  const [cities, setCities] = useState([]);
  const [selectedCountry, setSelectedCountry] = useState('');
  const [selectedState, setSelectedState] = useState('');
  const [selectedCity, setSelectedCity] = useState('');

  useEffect(() => {
    const fetchData = async () => {
      setLoading(true);
      try {
        const [usersRes, rolesRes] = await Promise.all([
          fetch('http://localhost:8080/api/users/getUserRequests'),
          fetch('http://localhost:8080/api/roles/getRoles')
        ]);
        const usersData = await usersRes.json();
        const rolesData = await rolesRes.json();
        const foundUser = usersData.find(u => String(u.requestId) === String(requestId));
        setUser(foundUser || null);
        setAvailableRoles(rolesData || []);
      } catch (error) {
      } finally {
        setLoading(false);
      }
    };
    fetchData();
  }, [requestId]);

  useEffect(() => {
    fetch('https://api.countrystatecity.in/v1/countries', {
      headers: { "X-CSCAPI-KEY": API_KEY }
    })
      .then(res => res.json())
      .then(data => setCountries(data))
      .catch(() => setCountries([]));
  }, []);

  useEffect(() => {
    if (selectedCountry) {
      fetch(`https://api.countrystatecity.in/v1/countries/${selectedCountry}/states`, {
        headers: { "X-CSCAPI-KEY": API_KEY }
      })
        .then(res => res.json())
        .then(data => setStates(data.sort((a, b) => a.name.localeCompare(b.name))))
        .catch(() => setStates([]));
      setSelectedState('');
      setCities([]);
    } else {
      setStates([]);
      setCities([]);
      setSelectedState('');
      setSelectedCity('');
    }
  }, [selectedCountry]);

  useEffect(() => {
    if (selectedCountry && selectedState) {
      fetch(`https://api.countrystatecity.in/v1/countries/${selectedCountry}/states/${selectedState}/cities`, {
        headers: { "X-CSCAPI-KEY": API_KEY }
      })
        .then(res => res.json())
        .then(data => setCities(data.sort((a, b) => a.name.localeCompare(b.name))))
        .catch(() => setCities([]));
      setSelectedCity('');
    } else {
      setCities([]);
      setSelectedCity('');
    }
  }, [selectedState, selectedCountry]);

  const assignRole = (role) => {
    setAvailableRoles(prevRoles => prevRoles.filter(r => r.roleId !== role.roleId));
    setAssignedRoles(prev => [...prev, role].sort((a, b) => a.roleName.localeCompare(b.roleName)));
  };

  const removeRole = (role) => {
    setAssignedRoles(prev => prev.filter(r => r.roleId !== role.roleId));
    setAvailableRoles(prevRoles => [...prevRoles, role].sort((a, b) => a.roleName.localeCompare(b.roleName)));
  };

  const handleApprove = async () => {
    if (!user || assignedRoles.length === 0) {
      alert('Cannot approve without roles.');
      return;
    }
    if (!selectedCountry || !selectedState || !selectedCity) {
      alert('Please select country, state, and city before approving.');
      return;
    }

    try {
      const rolesToAssign = assignedRoles.map(role => ({
        roleId: role.roleId,
        roleName: role.roleName,
        country: countries.find(c => c.iso2 === selectedCountry)?.name || selectedCountry,
        state: states.find(s => s.iso2 === selectedState)?.name || selectedState,
        city: selectedCity,
      }));

      const response = await fetch(`http://localhost:8080/api/users/approveAndAssignRoles?requestId=${requestId}`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(rolesToAssign)
      });

      if (response.ok) {
        alert('User approved and roles assigned successfully');
        navigate('/');
      } else {
        const errorText = await response.text();
        alert('Failed to approve user and assign roles: ' + errorText);
      }
    } catch (err) {
      alert('Error during approval process: ' + err.message);
    }
  };

  return (loading ?
    <p>Loading...</p> : (
      <div className="approve-container">
        <h2>Approve Member</h2>
        {user ? (
          <div className="approve-content">
            <p>Name: {user.firstName} {user.lastName}</p>
            <p>Place: {user.city}, {user.state}, {user.country}</p>
            <h3>Assign Role Location</h3>
            <div className="assign-geography">
              <select onChange={(e) => setSelectedCountry(e.target.value)} value={selectedCountry} required>
                <option value="">Select Country</option>
                {countries.map(c => <option key={c.iso2} value={c.iso2}>{c.name}</option>)}
              </select>
              <select onChange={(e) => setSelectedState(e.target.value)} value={selectedState} required disabled={!selectedCountry || states.length === 0}>
                <option value="">Select State</option>
                {states.map(s => <option key={s.iso2} value={s.iso2}>{s.name}</option>)}
              </select>
              <select onChange={(e) => setSelectedCity(e.target.value)} value={selectedCity} required disabled={!selectedState || cities.length === 0}>
                <option value="">Select City</option>
                {cities.map(c => <option key={c.name} value={c.name}>{c.name}</option>)}
              </select>
            </div>
            <h2>Press on any role to assign</h2>
            <div className="assign-roles-container">
              <div className="assign-box">
                <h3>Available Roles</h3>
                {availableRoles.length > 0 ? availableRoles.map(role => (
                  <button key={role.roleId} className="role-btn"
                    onClick={() => assignRole(role)}
                  >{role.roleName}</button>
                )) : <p>No available roles.</p>}
              </div>
              <div className="assign-box">
                <h3>Assigned Roles</h3>
                {assignedRoles.length > 0 ? assignedRoles.map(role => (
                  <button key={role.roleId} className="role-btn"
                    onClick={() => removeRole(role)}>{role.roleName}</button>
                )) : <p>No roles assigned.</p>}
              </div>
            </div>
            <button onClick={handleApprove} className="approve-btn">
              Approve
            </button>
          </div>
        ) : (
          <p>User not found or already approved.</p>
        )}
      </div>
    )
  );
}

export default Approval;
