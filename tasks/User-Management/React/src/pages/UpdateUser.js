import { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';

const API_KEY = "RzRLZGVseDhBMDd5bm9jOE8wNXk5elFjN1NGcVUzSnZNalRqRWNyTA==";

function UpdateUser() {
  const { userName } = useParams();
  const navigate = useNavigate();
  const [user, setUser] = useState(null);
  const [loading, setLoading] = useState(true);
  // eslint-disable-next-line
  const [allRoles, setAllRoles] = useState([]);
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
        const [allUsersRes, allRolesRes, allUserRolesRes] = await Promise.all([
          fetch('http://localhost:8080/api/users/getMainUsers'),
          fetch('http://localhost:8080/api/roles/getRoles'),
          fetch('http://localhost:8080/api/roles/getUserRoles'),
        ]);

        const allUsersData = await allUsersRes.json();
        const allRolesData = await allRolesRes.json();
        const allUserRolesData = await allUserRolesRes.json();

        const foundUser = allUsersData.find(u => u.userName === userName);
        setUser(foundUser || null);
        setAllRoles(allRolesData || []);

        const userRolesData = allUserRolesData.filter(ur => ur.userName === userName);
        const assignedRoleIds = userRolesData.map(role => role.roleId);

        const assigned = allRolesData.filter(role => assignedRoleIds.includes(role.roleId));
        const available = allRolesData.filter(role => !assignedRoleIds.includes(role.roleId));

        setAssignedRoles(assigned);
        setAvailableRoles(available);

        if (foundUser) {
          const countriesRes = await fetch('https://api.countrystatecity.in/v1/countries', { headers: { "X-CSCAPI-KEY": API_KEY } });
          const allCountries = await countriesRes.json();
          const foundCountry = allCountries.find(c => c.name === foundUser.country);

          if (foundCountry) {
            setSelectedCountry(foundCountry.iso2);

            const statesRes = await fetch(`https://api.countrystatecity.in/v1/countries/${foundCountry.iso2}/states`, { headers: { "X-CSCAPI-KEY": API_KEY } });
            const allStates = await statesRes.json();
            const foundState = allStates.find(s => s.name === foundUser.state);

            if (foundState) {
              setSelectedState(foundState.iso2);
              setSelectedCity(foundUser.city);
            }
          }
        }
      } catch (error) {
      } finally {
        setLoading(false);
      }
    };
    fetchData();
  }, [userName]);

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
  const handleUpdate = async () => {
    if (!user || assignedRoles.length === 0) {
      alert('Cannot update without roles');
      return;
    }
    if (!selectedCountry || !selectedState || !selectedCity) {
      alert('Please select country, state, and city before updating');
      return;
    }

    try {
      const countryName = countries.find(c => c.iso2 === selectedCountry)?.name || selectedCountry;
      const stateName = states.find(s => s.iso2 === selectedState)?.name || selectedState;

      const rolesToAssign = assignedRoles.map(role => ({
        userName: userName,
        roleId: role.roleId,
        roleName: role.roleName,
        country: countryName,
        state: stateName,
        city: selectedCity,
      }));

      const response = await fetch(`http://localhost:8080/api/roles/updateUserRoles?userName=${userName}`, {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(rolesToAssign)
      });
      if (response.ok) {
        alert('User updated successfully!');
        navigate('/home');
      } else {
        const errorText = await response.text();
        alert('Failed to update user: ' + errorText);
      }
    } catch (err) {
      alert('Error during update process: ' + err.message);
    }
  };
  return (loading ?
    <p className="update-content">Loading...</p> : (
      <div className="approve-container">
        <button
          style={{ width: '120px' }}
          onClick={() => navigate('/home')}
        >
          Back
        </button>
        <h2 >Update Member: {userName}</h2>
        {user ? (
          <div className="update-content">
            <p>Name: {user.firstName} {user.lastName}</p>
            <h3>Assign Role Location</h3>
            <div className="assign-geography">
              <select
                className="form-group"
                onChange={(e) => setSelectedCountry(e.target.value)}
                value={selectedCountry}
                required
              >
                <option value="">Select Country</option>
                {countries.map(c => <option key={c.iso2} value={c.iso2}>{c.name}</option>)}
              </select>
              <select
                className="form-group"
                onChange={(e) => setSelectedState(e.target.value)}
                value={selectedState}
                required
                disabled={!selectedCountry || states.length === 0}
              >
                <option value="">Select State</option>
                {states.map(s => <option key={s.iso2} value={s.iso2}>{s.name}</option>)}
              </select>
              <select
                className="form-group"
                onChange={(e) => setSelectedCity(e.target.value)}
                value={selectedCity}
                required
                disabled={!selectedState || cities.length === 0}
              >
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
            <button onClick={handleUpdate} className="update-btn">
              Update User
            </button>
          </div>
        ) : (
          <p className="update-content">User not found.</p>
        )}
      </div>
    )
  );
}

export default UpdateUser;