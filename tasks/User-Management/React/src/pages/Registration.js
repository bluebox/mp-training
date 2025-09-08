import React, { useState, useEffect } from 'react';
import DashboardLayout from '../layouts/DashboardLayout';
import { useNavigate } from 'react-router-dom';

const API_KEY = "RzRLZGVseDhBMDd5bm9jOE8wNXk5elFjN1NGcVUzSnZNalRqRWNyTA==";

function Registration() {
  const navigate = useNavigate();
  const [form, setForm] = useState({
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
      console.log(countries)
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
      
      console.log("Submitting:", submitForm);
      const response = await fetch('http://localhost:8080/api/users/createUser', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(submitForm)
      });
      if (response.ok) {
        alert('Registration successful!');
        setForm({
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
      } else {
        const error = await response.text();
        alert('Registration failed: ' + error);
      }
    } catch (err) {
      alert('Error: ' + err.message);
    }
  };

  return (
    <DashboardLayout>
      <div className="login-container dashboard">
        <form onSubmit={handleSubmit} className="register-form">
          <h2>Registration Form</h2>
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
            </div>
            <div className="form-column">
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
          <button type="submit">Register</button>
          <button type="button" onClick={() => navigate('/home')}>Back</button>
        </form>
      </div>
    </DashboardLayout>
  );
}

export default Registration;
