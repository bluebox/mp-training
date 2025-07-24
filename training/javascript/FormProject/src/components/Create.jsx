import React, { useState, useEffect } from "react";
import { useNavigate, useParams } from "react-router-dom";

const Create = ({ records, setRecords, config }) => {
  const { id } = useParams();
  const navigate = useNavigate();
  const isEdit = Boolean(id);
  const existing = records.find(r => r.id === id);

  const [form, setForm] = useState({ name: "", phone: "", email: "", department: "", joinDate: "" });

  useEffect(() => {
    if (isEdit && existing) {
      setForm(existing);
    }
  }, [isEdit, existing]);

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const validate = () => {
    if (!form.name.trim() || !form.phone.trim() || !form.email.trim()) return false;
    if (!/^\d{10}$/.test(form.phone)) return false;
    if (!/^[\w.-]+@[\w.-]+\.[a-zA-Z]{2,}$/.test(form.email)) return false;
    return true;
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    if (!validate()) {
      alert("Please enter valid Name, Phone (10 digits), and Email");
      return;
    }
    if (isEdit) {
      setRecords(records.map(r => (r.id === id ? form : r)));
    } else {
      if (config.uniquePhone && records.some(r => r.phone === form.phone)) {
        alert("Phone number must be unique");
        return;
      }
      setRecords([...records, { ...form, id: Date.now().toString() }]);
    }
    navigate("/");
  };

  return (
    <div>
      <h1>{isEdit ? "Edit" : "Create"} User</h1>
      <form onSubmit={handleSubmit}>
        <input name="name" value={form.name} onChange={handleChange} placeholder="Name" required />
        <input name="phone" value={form.phone} onChange={handleChange} placeholder="Phone" required />
        <input name="email" value={form.email} onChange={handleChange} placeholder="Email" required />
        <input name="department" value={form.department} onChange={handleChange} placeholder="Department" />
        <input name="joinDate" type="date" value={form.joinDate} onChange={handleChange} placeholder="Join Date" />
        <button type="submit">Save</button>
      </form>
    </div>
  );
};

export default Create;