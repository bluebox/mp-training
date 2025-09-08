import React, { useState, useEffect, useRef } from 'react'
import { useLocation, useNavigate } from 'react-router-dom'
import { FaEye, FaEyeSlash } from 'react-icons/fa'

const API_KEY = "RzRLZGVseDhBMDd5bm9jOE8wNXk5elFjN1NGcVUzSnZNalRqRWNyTA==";

export default function UserProfile() {
  const location = useLocation();
  const navigate = useNavigate();
  const user = location.state?.data;
  const [showPassword, setShowPassword] = useState(false);
  const [showConfirmPassword, setConfirmShowPassword] = useState(false);
  const togglePasswordVisibility = () => {
    setShowPassword((prevShowPassword) => !prevShowPassword);
  };
  const toggleConfirmPasswordVisibility = () => {
    setConfirmShowPassword((prevShowPassword) => !prevShowPassword);
  };
  const [form, setForm] = useState(user || {
    firstName: '',
    lastName: '',
    email: '',
    phoneNumber: '',
    gender: '',
    country: '',
    state: '',
    city: '',
    pinCode: '',
  });
  const [countries, setCountries] = useState([]);
  const [states, setStates] = useState([]);
  const [cities, setCities] = useState([]);
  const [genders, setGenders] = useState([]);
  const passwordRef = useRef('')
  const confirmPasswordRef = useRef('')

  useEffect(() => {
    const fetchUserLocation = async () => {
      if (user) {
        delete user.password
        const countriesRes = await fetch('https://api.countrystatecity.in/v1/countries', { headers: { "X-CSCAPI-KEY": API_KEY } });
        const allCountries = await countriesRes.json();
        const foundCountry = allCountries.find(c => c.name === user.country);
        if (foundCountry) {
          setForm(f => ({ ...f, country: foundCountry.iso2 }));
          const statesRes = await fetch(`https://api.countrystatecity.in/v1/countries/${foundCountry.iso2}/states`, { headers: { "X-CSCAPI-KEY": API_KEY } });
          const allStates = await statesRes.json();
          const foundState = allStates.find(s => s.name === user.state);

          if (foundState) {
            setForm(f => ({ ...f, state: foundState.iso2, city: user.city }));
          }
        }
      }
    };
    fetchUserLocation();
  }, [user]);

  useEffect(() => {
    fetch('https://api.countrystatecity.in/v1/countries', {
      headers: { "X-CSCAPI-KEY": API_KEY }
    })
      .then(res => res.json())
      .then(data => setCountries(data))
      .catch(() => setCountries([]));
  }, []);

  useEffect(() => {
    if (form.country) {
      fetch(`https://api.countrystatecity.in/v1/countries/${form.country}/states`, {
        headers: { "X-CSCAPI-KEY": API_KEY }
      })
        .then(res => res.json())
        .then(data => setStates(data.sort((a, b) => a.name.localeCompare(b.name))))
        .catch(() => setStates([]));
      setForm(f => ({ ...f, state: '', city: '' }));
      setCities([]);
    } else {
      setStates([]);
      setCities([]);
      setForm(f => ({ ...f, state: '', city: '' }));
    }
  }, [form.country]);

  useEffect(() => {
    if (form.country && form.state) {
      fetch(`https://api.countrystatecity.in/v1/countries/${form.country}/states/${form.state}/cities`, {
        headers: { "X-CSCAPI-KEY": API_KEY }
      })
        .then(res => res.json())
        .then(data => setCities(data.sort((a, b) => a.name.localeCompare(b.name))))
        .catch(() => setCities([]));
      setForm(f => ({ ...f, city: '' }));
    } else {
      setCities([]);
      setForm(f => ({ ...f, city: '' }));
    }
  }, [form.state, form.country]);

  useEffect(() => {
    fetch('http://localhost:8080/api/users/getGenders')
      .then(res => res.json())
      .then(data => setGenders(data))
      .catch(() => setGenders([]));
  }, []);

  const handleChange = (e) => {
    const { name, value } = e.target;
    switch (name) {
      case 'country':
        setForm({ ...form, country: value, state: '', city: '' });
        break;
      case 'state':
        setForm({ ...form, state: value, city: '' });
        break;
      case 'city':
        setForm({ ...form, city: value });
        break;
      default:
        setForm({ ...form, [name]: value });
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const submitForm = {
        ...form,
        country: countries.find(c => c.iso2 === form.country)?.name || form.country,
        state: states.find(s => s.iso2 === form.state)?.name || form.state,
      };
      console.log(submitForm);
      const password = passwordRef.current.value;
      const confirmPassword = confirmPasswordRef.current.value;
      if (password.length !== 0 || confirmPassword.length !== 0) {
        if (password === confirmPassword) {
          if (password.length >= 8) {
            submitForm['password'] = password;
          } else {
            alert('password must be 8 characters');
            return;
          }
        } else {
          alert('Passwords do not match');
          return;
        }
      }
      const response = await fetch(`http://localhost:8080/api/users/updateUserProfile`, {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(submitForm)
      });
      if (response.ok) {
        alert('Profile updated successfully');
        if (passwordRef.current) passwordRef.current.value = '';
        if (confirmPasswordRef.current) confirmPasswordRef.current.value = '';
        setShowPassword(false);
        setConfirmShowPassword(false);
      } else {
        const error = await response.text();
        alert('Update failed: ' + error);
      }
    } catch (err) {
      alert('Error: ' + err.message);
    }
  };

  return (
    <div className="login-container dashboard">
      <form onSubmit={handleSubmit} className="register-form">
        <h2>User Profile</h2>
        <div className="form-row">
          <div className="form-column">
            <div className="form-group">
              <label>First Name*</label>
              <input
                type="text"
                name="firstName"
                value={form.firstName}
                onChange={handleChange}
                required
                pattern="^[A-Za-z ]{2,100}$"
                title="First Name must be 2-100 alphabets or spaces"
              />
            </div>
            <div className="form-group">
              <label>Last Name*</label>
              <input
                type="text"
                name="lastName"
                value={form.lastName}
                onChange={handleChange}
                required
                pattern="^[A-Za-z ]{2,100}$"
                title="Last Name must be 2-100 alphabets or spaces"
              />
            </div>
            <div className="form-group">
              <label>Email*</label>
              <input
                type="email"
                name="email"
                value={form.email}
                onChange={handleChange}
                required
              />
            </div>

            <div className="form-group">
              <label>New Password</label>
              <div style={{ position: 'relative' }}>
                <input
                  type={showPassword ? 'text' : 'password'}
                  name="password"
                  ref={passwordRef}
                  title='new password'
                />
                <span
                  onClick={togglePasswordVisibility}
                  style={{ position: 'absolute', right: '10px', top: '50%', transform: 'translateY(-50%)', cursor: 'pointer' }}
                >
                  {showPassword ? <FaEyeSlash /> : <FaEye />}
                </span>
              </div>

            </div>
            <div className="form-group">
              <label>Confirm New Password</label>
              <div style={{ position: 'relative' }}>
                <input
                  type={showConfirmPassword ? 'text' : 'password'}
                  name="confirm-password"
                  ref={confirmPasswordRef}
                  title='confirm new password'
                />
                <span
                  onClick={toggleConfirmPasswordVisibility}
                  style={{ position: 'absolute', right: '10px', top: '50%', transform: 'translateY(-50%)', cursor: 'pointer' }}
                >
                  {showConfirmPassword ? <FaEyeSlash /> : <FaEye />}
                </span>
              </div>
            </div>
          </div>
          <div className="form-column">
            <div className="form-group">
              <label>Phone Number*</label>
              <input
                type="number"
                name="phoneNumber"
                value={form.phoneNumber}
                onChange={handleChange}
                required
                min="1000000000"
                max="9999999999"
                title="Phone number must be exactly 10 digits"
              />
            </div>
            <div className="form-group">
              <label>Gender*</label>
              <select
                name="gender"
                value={form.gender}
                onChange={handleChange}
                required
              >
                <option value="">Select Gender</option>
                {genders.map(g => (
                  <option key={g} value={g}>{g}</option>
                ))}
              </select>
            </div>
            <div className="form-group">
              <label>Country*</label>
              <select
                name="country"
                value={form.country}
                onChange={handleChange}
                required
              >
                <option value="">Select Country</option>
                {countries.map(c => (
                  <option key={c.iso2} value={c.iso2}>{c.name}</option>
                ))}
              </select>
            </div>
            <div className="form-group">
              <label>State*</label>
              <select
                name="state"
                value={form.state}
                onChange={handleChange}
                required
                disabled={!form.country || states.length === 0}
              >
                <option value="">Select State</option>
                {states.map(s => (
                  <option key={s.iso2} value={s.iso2}>{s.name}</option>
                ))}
              </select>
            </div>
            <div className="form-group">
              <label>City*</label>
              <select
                name="city"
                value={form.city}
                onChange={handleChange}
                required
                disabled={!form.state || cities.length === 0}
              >
                <option value="">Select City</option>
                {cities.map(c => (
                  <option key={c.name} value={c.name}>{c.name}</option>
                ))}
              </select>
            </div>
            <div className="form-group">
              <label>Pin Code*</label>
              <input
                type="number"
                name="pinCode"
                value={form.pinCode}
                onChange={handleChange}
                required
                min="100000"
                max="999999"
                title="Pin Code must be exactly 6 digits"
              />
            </div>
          </div>
        </div>
        <button type="submit">Update Profile</button>
        <button type="button" onClick={() => navigate('/login')}>Logout</button>
      </form>
    </div>
  )
}