import React, { useState, useEffect } from 'react';
import './Role.css';
import $ from 'jquery';
import { useParams } from 'react-router-dom';


const RoleManagement = ({ auth , onNavigate}) => {
  const [availableRoles, setAvailableRoles] = useState([]);
  const [selectedAvailableRoles, setSelectedAvailableRoles] = useState([]);
  const [assignedRoles, setAssignedRoles] = useState([]);
  const [selectedAssignedRoles, setSelectedAssignedRoles] = useState([]);
  const [AvailRoles, setAvailRoles]=useState([]);
  
  const { userId } = useParams();

  
  const [countries, setCountries] = useState([]);
  const [states, setStates] = useState([]);
  const [cities, setCities] = useState([]);
  
  const [message, setMessage] = useState('');
  const [messageType, setMessageType] = useState('');
  
   
   const[userRole, setuserRole] = useState({
	usercode: `${userId}`,
	usernameString: '',
	rolecodes: [],
	country: '',
	state:'',
	city:''
   });
   
  const API_KEY = 'ME95TTJOZERmVG9nb3hKNFNtcEJIWHRNMmNqNzdqVFRNclFnTHhRNw==';
  
	  useEffect(() => {
		if(!auth) return;
	     fetchroles();
	   }, [auth]);
	   
	   const fetchroles = async () => {
	     try {
		   const credentials = btoa(`${auth.userCode}:${auth.password}`);
	       
		   const response = await fetch('/role/getroles' ,{
		   		  method: 'GET',
		   		  headers: {
		   		    'Content-Type': 'application/json',
		   		    'Authorization': `Basic ${credentials}`
		   		  },
		   	});
			
	       const assignedResponse = await fetch(`/role/getUserAssignedRoles/${userId}`,{
			  method: 'GET',
			  headers: {
			    'Content-Type': 'application/json',
			    'Authorization': `Basic ${credentials}`
			  },
			});
		   
		   /*fetch('/role/getroles').then(res => res.json()).then(console.log)
		   fetch(`/role/getUserAssignedRoles/${userId}`).then(res => res.json()).then(console.log)
*/
	       
	       if (response.ok && assignedResponse.ok) {
	         const allRoles = await response.json();
	         const assignedRolesData = await assignedResponse.json();
			 
			 console.log(assignedRolesData);
			 
			const activeassignedRolesData = assignedRolesData.filter(u => u.statusString === 'ACTIVE' );
					  
			console.log(activeassignedRolesData);

			
		  const assignedRoleIds = new Set(
			activeassignedRolesData.flatMap(r => r.rolecodes)
		  );
		  
		  
		  console.log(assignedRoleIds);

		  const activeRoles = allRoles.filter(role => role.status === 'ACTIVE');
		  

		  const assignedRolesList = activeRoles.filter(
		    role => assignedRoleIds.has(role.rolecode )
		  );
		  
		  
		  
		  setAssignedRoles(assignedRolesList);
		
		  const filteredAvailableRoles = activeRoles.filter(
		    role => !assignedRoleIds.has(role.rolecode)
		  );
		  setAvailableRoles(filteredAvailableRoles);
		  
		  const setAvailroles = availableRoles.map(r => r.rolecode);
		  setAvailRoles(setAvailroles)
		  console.log(AvailRoles)
		/*
		  console.log('Assigned Roles:', assignedRolesList);
		  console.log('Available Roles:', filteredAvailableRoles);*/
	       } else {
	         const errorData = await response.text();
			 setMessage(errorData);
			 setMessageType('error');
	         console.log(`${errorData}`);
	       }
	     } catch (err) {
	       console.log(err);
	     }
	   };

  

      useEffect(() => {
          fetchCountries();
      }, []);

      useEffect(() => {
          if (userRole.country) {
              fetchStates(userRole.country);
          } else {
              setStates([]);
              setCities([]);
          }
      }, [userRole.country]);

      useEffect(() => {
          if (userRole.country && userRole.state) {
              fetchCities(userRole.country, userRole.state);
          } else {
              setCities([]);
          }
      }, [userRole.state,userRole.country]);

      const fetchCountries = () => {
          $.ajax({
              url: "https://api.countrystatecity.in/v1/countries",
              method: "GET",
              headers: {
                  "X-CSCAPI-KEY": API_KEY
              },
              success: (data) => {
                  setCountries(data);
              },
              error: (jqXHR, textStatus, errorThrown) => {
                  console.error("Error fetching countries:", textStatus, errorThrown);
              }
          });
      };

      const fetchStates = (countryIso2) => {
          $.ajax({
              url: `https://api.countrystatecity.in/v1/countries/${countryIso2}/states`,
              method: "GET",
              headers: {
                  "X-CSCAPI-KEY": API_KEY
              },
              success: (data) => {
                  setStates(data);
              },
              error: (jqXHR, textStatus, errorThrown) => {
                  console.error("Error fetching states:", textStatus, errorThrown);
              }
          });
      };

      const fetchCities = (countryIso2, stateIso2) => {
          $.ajax({
              url: `https://api.countrystatecity.in/v1/countries/${countryIso2}/states/${stateIso2}/cities`,
              method: "GET",
              headers: {
                  "X-CSCAPI-KEY": API_KEY
              },
              success: (data) => {
                  setCities(data);
              },
              error: (jqXHR, textStatus, errorThrown) => {
                  console.error("Error fetching cities:", textStatus, errorThrown);
              }
          });
      };


  const handleAvailableRoleSelect = (roleId) => {
    setSelectedAvailableRoles((prevSelected) =>
      prevSelected.includes(roleId)
        ? prevSelected.filter((id) => id !== roleId)
        : [...prevSelected, roleId]
    );
  };

  const handleAssignedRoleSelect = (roleId) => {
    setSelectedAssignedRoles((prevSelected) =>
      prevSelected.includes(roleId)
        ? prevSelected.filter((id) => id !== roleId)
        : [...prevSelected, roleId]
    );
  };

  const assignRoles = () => {
    const rolesToAssign = availableRoles.filter((role) =>
      selectedAvailableRoles.includes(role.rolecode)
    );
    setAssignedRoles((prevAssigned) => [...prevAssigned, ...rolesToAssign]);
    setAvailableRoles((prevAvailable) =>
      prevAvailable.filter((role) => !selectedAvailableRoles.includes(role.rolecode))
    );
    setSelectedAvailableRoles([]);
  };

  const unassignRoles = () => {
    const rolesToUnassign = assignedRoles.filter((role) =>
      selectedAssignedRoles.includes(role.rolecode)
    );
    setAvailableRoles((prevAvailable) => [...prevAvailable, ...rolesToUnassign]);
    setAssignedRoles((prevAssigned) =>
      prevAssigned.filter((role) => !selectedAssignedRoles.includes(role.rolecode))
    );
    setSelectedAssignedRoles([]);
  };
  
  const handleChange = (e) => {
      setuserRole({
        ...userRole,
        [e.target.name]: e.target.value,
      });
    };

  const handleSubmit = async(e) => {
	 
	e.preventDefault();
	try{
	const payload ={
		...userRole,
		rolecodes: assignedRoles.map(r => r.rolecode),
	};
	
	if(payload === null || payload.rolecodes.length === 0){
		throw new Error('Please select and assign the roles');
	}
	if(payload.country === '' || payload.state=== '' || payload.city=== ''){
		throw new Error('Please assign the location');
	}
	
	console.log(`User ID: `, userRole.usercode);
	 console.log('Assigned Roles:', userRole.roleId);
	 console.log('Geographical Coverage:', {
	   country: userRole.country,
	   state: userRole.state,
	   city: userRole.city,
	 });
	 
	const credentials = btoa(`${auth.userCode}:${auth.password}`);

	
	const response = await fetch('http://localhost:8080/role/addUserRole', {
   		  method: 'POST',
   		  headers: {
   		    'Content-Type': 'application/json',
   		    'Authorization': `Basic ${credentials}`
   		  },
		body: JSON.stringify(payload),
		});
	
	if (!response.ok) {
	      const text = await response.text(); 
		   throw new Error(`${text}`);
	  }
	  
	  
	  const availroles = availableRoles.map(r => r.rolecode);
	  console.log(availroles)
	  
	  

	setMessage(`Roles Assigned to ${userId}`);
	setMessageType('success');
		
	 /*const result = await response.json();
	 console.log('Success:', result);*/
	 
	 setuserRole({
		usercode: `${userId}`,
		usernameString: '',
		rolecodes: [],
		country: '',
		state:'',
		city:''
	 	});
		setAssignedRoles([]);
	    setAvailableRoles([]);
		setSelectedAssignedRoles([]);
		setSelectedAvailableRoles([]);
	    fetchroles();
		
	 } catch (error){
		console.error('Error:', error);
	    setMessage(`${error}`);
	    setMessageType('error');
	 }
	
  };
  
  

  return (
    <div className="role-management-container">
      <div className="navbar">
        <h1>Role Management - {userId}</h1>
      </div>

      <div className="middle-section">
        <div className="roles-panel available-roles">
          <h2>Available Roles</h2>
          <div className="roles-list">
            {availableRoles.map((role) => (
              <button
                key={role.rolecode}
                className={`role-button ${selectedAvailableRoles.includes(role.rolecode) ? 'selected' : ''}`}
                onClick={() => handleAvailableRoleSelect(role.rolecode)}
              >
                {role.roleName}
              </button>
            ))}
          </div>
        </div>

        <div className="transfer-buttons">
          <button onClick={assignRoles} disabled={selectedAvailableRoles.length === 0}>
            &gt;
          </button>
          <button onClick={unassignRoles} disabled={selectedAssignedRoles.length === 0}>
            &lt;
          </button>
        </div>

        <div className="roles-panel assigned-roles">
          <h2>Assigned Roles</h2>
          <div className="roles-list">
            {assignedRoles.map((role) => (
              <button
                key={role.rolecode}
                className={`role-button ${selectedAssignedRoles.includes(role.rolecode) ? 'selected' : ''}`}
                onClick={() => handleAssignedRoleSelect(role.rolecode)}
              >
                {role.roleName}
              </button>
            ))}
          </div>
        </div>
      </div>

      <div className="geographical-coverage">
        <h2>Assign Location</h2>
        <div className="dropdown-group">
		
          <label htmlFor="country">Country:</label>
		  <select name="country" id="country" value={userRole.country} onChange={handleChange} required>
              <option value="">Select Country</option>
              {countries.map(country => (
                  <option key={country.iso2} value={country.iso2}>{country.name}</option>
              ))}
          </select>
		  
		  <label htmlFor="state">State:</label>
		  <select name="state" id="state" value={userRole.state} onChange={handleChange}  required>
              <option value="">Select State</option>
              {states.map(state => (
                  <option key={state.iso2} value={state.iso2}>{state.name}</option>
              ))}
          </select>


          <label htmlFor="city">City:</label>
		  <select name="city" id="city" value={userRole.city} onChange={handleChange} required>
              <option value="">Select City</option>
              {cities.map(city => (
                  <option key={city.name} value={city.name}>{city.name}</option>
              ))}
          </select>
		  
        </div>
      </div>

      <button className="submit-button" onClick={handleSubmit}>
        Assign Roles
      </button>
	  <button onClick={() => onNavigate('mainUsers')} className="back-button">Back to Users</button>
	  {message && <p className="message" style={{ color: messageType === 'error' ? 'red' : 'green' }}>{message}</p>}

    </div>
  );
};

export default RoleManagement;