import React, { useState, useEffect } from 'react';
import axios from 'axios';

axios.defaults.withCredentials = true;

const AdminVehicleDashboard = () => {
  const [customers, setCustomers] = useState([]);
  const [formData, setFormData] = useState({
    name: '',
    email: '',
    contact: '',
    gender: '',
    age: '',
    occupation: '',
    income: '',
    address: '',
    status: 'A',
    createdBy: localStorage.getItem('username'),
    customerUpdatedBy: localStorage.getItem('username'),
  });
  const [editingId, setEditingId] = useState(null);

  useEffect(() => {
    fetchCustomers();
  }, []);

  const fetchCustomers = () => {
    axios.get("http://localhost:8080/customer/showAll")
      .then(res => setCustomers(res.data))
      .catch(err => console.error(err));
  };

  const handleInputChange = e => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const submitForm = e => {
    e.preventDefault();
    if (editingId) {
      axios.put("http://localhost:8080/customer/update", { ...formData, customerId: editingId })
        .then(() => {
          fetchCustomers();
          resetForm();
        })
        .catch(err => console.error(err));
    } else {
      axios.post("http://localhost:8080/customer/add", formData)
        .then(() => {
          fetchCustomers();
          resetForm();
        })
        .catch(err => console.error(err));
    }
  };

  const editCustomer = c => {
    setEditingId(c.customerId);
    setFormData({
      name: c.name,
      email: c.email,
      contact: c.contact,
      gender: c.gender,
      age: c.age,
      occupation: c.occupation,
      income: c.income,
      address: c.address,
      status: c.status,
      createdBy: c.createdBy,
      customerUpdatedBy: localStorage.getItem('username'),
    });
  };

  const updateStatus = (id, status) => {
    axios.put(`http://localhost:8080/customer/updateStatus?customerId=${id}&status=${status}`)
      .then(() => fetchCustomers())
      .catch(err => console.error(err));
  };

  const deleteCustomer = id => {
    axios.put(`http://localhost:8080/customer/delete?customerId=${id}`)
      .then(() => fetchCustomers())
      .catch(err => console.error(err));
  };

  const resetForm = () => {
    setEditingId(null);
    setFormData({
      name: '',
      email: '',
      contact: '',
      gender: '',
      age: '',
      occupation: '',
      income: '',
      address: '',
      status: 'A',
      createdBy: localStorage.getItem('username'),
      customerUpdatedBy: localStorage.getItem('username'),
    });
  };

  return (
    <div>
      <h2>Customer Management</h2>

      <form onSubmit={submitForm}>
        <input name="name" placeholder="Name" value={formData.name} onChange={handleInputChange} required />
        <input name="email" placeholder="Email" value={formData.email} onChange={handleInputChange} required />
        <input name="contact" placeholder="Contact" value={formData.contact} onChange={handleInputChange} required />
        <input name="gender" placeholder="Gender (M/F)" value={formData.gender} onChange={handleInputChange} required />
        <input name="age" type="number" placeholder="Age" value={formData.age} onChange={handleInputChange} required />
        <input name="occupation" placeholder="Occupation" value={formData.occupation} onChange={handleInputChange} required />
        <input name="income" type="number" placeholder="Income" value={formData.income} onChange={handleInputChange} required />
        <input name="address" placeholder="Address" value={formData.address} onChange={handleInputChange} required />
        <button type="submit">{editingId ? "Update Customer" : "Add Customer"}</button>
        <button type="button" onClick={resetForm}>Reset</button>
      </form>

      <h3>Customer List</h3>
      <table border="1">
        <thead>
          <tr>
            <th>ID</th><th>Name</th><th>Email</th><th>Status</th><th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {customers.map(c => (
            <tr key={c.customerId}>
              <td>{c.customerId}</td>
              <td>{c.name}</td>
              <td>{c.email}</td>
              <td>{c.status}</td>
              <td>
                <button onClick={() => editCustomer(c)}>Edit</button>
                <button onClick={() => updateStatus(c.customerId, c.status === 'A' ? 'I' : 'A')}>
                  {c.status === 'A' ? 'A' : 'I'}
                </button>
                <button onClick={() => deleteCustomer(c.customerId)}>Delete</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default AdminVehicleDashboard;
