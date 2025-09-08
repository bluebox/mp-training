import React, { useState, useEffect } from "react";

export default function EnrollForm() {
  const [form, setForm] = useState({
    firstName: "",
    lastName: "",
    username: "",
    age: "",
    gender: "",
    empId: "",
    mobile: "",
    email: "",
    country: "",
    state: "",
    city: "",
    activeStatus: "",
  });

  const [enums, setEnums] = useState({
    Gender: [],
    ActiveStatus: [],
  });

  useEffect(() => {
  const fetchEnums = async () => {
    try {
      const res = await fetch("http://localhost:8080/api/enums");
      if (!res.ok) {
        const errMsg = await res.text();
        throw new Error(errMsg || `Failed to fetch enums (${res.status})`);
      }
      const data = await res.json();
      setEnums(data.data?.enums || {}); 
    } catch (err) {
      console.error("Error fetching enums:", err);
      alert(err.message || "Something went wrong while loading enums!");
    }
  };
  fetchEnums();
}, []);


  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };


    const validateForm = () => {
  const {
    firstName,
    lastName,
    username,
    age,
    gender,
    empId,
    mobile,
    email,
    country,
    state,
    city,
    activeStatus,
  } = form;

  if (!firstName?.trim() || firstName.trim().length > 25)
    return "First Name is required and should not exceed 25 characters.";
  if (!lastName?.trim() || lastName.trim().length > 25)
    return "Last Name is required and should not exceed 25 characters.";
  if (!username?.trim() || username.trim().length > 25)
    return "Username is required and should not exceed 25 characters.";
  if (!age?.trim() || isNaN(age) || Number(age) < 0 || Number(age)>100)
    return "Age is required and must be a non-negative number less than equal to 100.";
  if (!gender) return "Gender is required.";
  if (!empId?.trim()) return "Employee ID is required.";
  if (!mobile?.trim() || !/^\d{10}$/.test(mobile.trim()))
    return "Mobile is required and must be 10 digits.";
  if (!email?.trim() || !/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email.trim()))
    return "A valid email is required.";
  if (!country?.trim()) return "Country is required.";
  if (!state?.trim()) return "State is required.";
  if (!city?.trim()) return "City is required.";
  if (!activeStatus) return "Active Status is required.";

  return null;
};

  const handleSubmit = async (e) => {
  e.preventDefault();
  const errorMsg = validateForm();
  if (errorMsg) {
    alert(errorMsg);
    return;
  }
  try {
    const res = await fetch("http://localhost:8080/api/enrollments/requests", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(form),
    });

    const data = await res.json(); 

    if (!res.ok || !data.success) {
      throw new Error(data.message || `Failed (${res.status})`);
    }

    alert(data.message || "Request submitted successfully!");
    setForm({
      firstName: "",
      lastName: "",
      username: "",
      age: "",
      gender: "",
      empId: "",
      mobile: "",
      email: "",
      country: "",
      state: "",
      city: "",
      activeStatus: "",
    });
  } catch (error) {
    console.error("Error submitting form:", error);
    alert(error.message || "Something went wrong while submitting!");
  }
};


  return (
    <div style={{ maxWidth: "500px", margin: "20px auto" }}>
      <h2>Create User</h2>
      <form onSubmit={handleSubmit}>
        <div style={{ display: "flex", marginBottom: "10px" }}>
          <label style={{ width: "150px" }}>First Name:</label>
          <input name="firstName" value={form.firstName} onChange={handleChange}  />
        </div>
        <div style={{ display: "flex", marginBottom: "10px" }}>
          <label style={{ width: "150px" }}>Last Name:</label>
          <input name="lastName" value={form.lastName} onChange={handleChange} />
        </div>
        <div style={{ display: "flex", marginBottom: "10px" }}>
          <label style={{ width: "150px" }}>Username:</label>
          <input name="username" value={form.username} onChange={handleChange}  />
        </div>
        <div style={{ display: "flex", marginBottom: "10px" }}>
          <label style={{ width: "150px" }}>Age:</label>
          <input  name="age" value={form.age} onChange={handleChange}  />
        </div>

        <div style={{ display: "flex", marginBottom: "10px" }}>
          <label style={{ width: "150px" }}>Gender:</label>
          <select name="gender" value={form.gender} onChange={handleChange} >
            <option value="">--Select--</option>
            {enums.Gender?.map((g) => (
              <option key={g.code} value={g.name}>
                {g.description}
              </option>
            ))}
          </select>
        </div>

        <div style={{ display: "flex", marginBottom: "10px" }}>
          <label style={{ width: "150px" }}>Employee ID:</label>
          <input name="empId" value={form.empId} onChange={handleChange}  />
        </div>
        <div style={{ display: "flex", marginBottom: "10px" }}>
          <label style={{ width: "150px" }}>Mobile:</label>
          <input name="mobile" value={form.mobile} onChange={handleChange}  />
        </div>
        <div style={{ display: "flex", marginBottom: "10px" }}>
          <label style={{ width: "150px" }}>Email:</label>
          <input  name="email" value={form.email} onChange={handleChange}  />
        </div>
        <div style={{ display: "flex", marginBottom: "10px" }}>
          <label style={{ width: "150px" }}>Country:</label>
          <input name="country" value={form.country} onChange={handleChange} />
        </div>
        <div style={{ display: "flex", marginBottom: "10px" }}>
          <label style={{ width: "150px" }}>State:</label>
          <input name="state" value={form.state} onChange={handleChange} />
        </div>
        <div style={{ display: "flex", marginBottom: "10px" }}>
          <label style={{ width: "150px" }}>City:</label>
          <input name="city" value={form.city} onChange={handleChange} />
        </div>

        <div style={{ display: "flex", marginBottom: "10px" }}>
          <label style={{ width: "150px" }}>Active Status:</label>
          <select name="activeStatus" value={form.activeStatus} onChange={handleChange} >
            <option value="">--Select--</option>
            {enums.ActiveStatus?.map((s) => (
              <option key={s.code} value={s.name}>
                {s.description}
              </option>
            ))}
          </select>
        </div>

        <br />
        <button type="submit">Submit</button>
      </form>
    </div>
  );
}
