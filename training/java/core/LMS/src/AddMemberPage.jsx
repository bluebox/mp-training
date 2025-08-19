import axios from "axios";
import React, { useState } from "react";
import { useNavigate } from "react-router-dom";

function AddMemberPage() {
  const nav = useNavigate();


  const [form, setForm] = useState({
    name: "",
    email: "",
    mobile: "",
    gender: "",
    address: "",
  });


  const [errors, setErrors] = useState({});

  
  const handleChange = (e) => {
    const { name, value } = e.target;
    setForm({ ...form, [name]: value });
  };


  const validate = () => {
    let newErrors = {};

    if (!form.name) newErrors.name = "Name is required";
    if (!form.email) newErrors.email = "Email is required";
    else if (!/\S+@\S+\.\S+/.test(form.email))
      newErrors.email = "Invalid email format";

    if (!form.mobile) newErrors.mobile = "Mobile number is required";
    else if (!/^[0-9]{10}$/.test(form.mobile))
      newErrors.mobile = "Enter 10 digit number";

    if (!form.gender) newErrors.gender = "Please select gender";
    if (!form.address) newErrors.address = "Address is required";

    setErrors(newErrors);
    return Object.keys(newErrors).length === 0;
  };

  
  const handleSubmit = async(e) => {
    e.preventDefault();
    if (validate()) {
    //    const newMember={ name,
    // email,
    // mobile,
    // gender,
    // address};
    console.log(form);
       const response =await axios.post("http://localhost:8082/members",form);
       const data=response.data;
      alert(`member added successsfully id : ${data}`);
     //alert("hru");
    }
  };

  return (
    <div
      style={{
        display: "flex",
        justifyContent: "center",
        padding: "40px",
      }}
    >
      <div
        style={{
          width: "500px",
          padding: "30px",
          border: "1px solid #ccc",
          borderRadius: "12px",
          boxShadow: "0 4px 8px rgba(0,0,0,0.1)",
        }}
      >
        <h2 style={{ textAlign: "center", marginBottom: "20px" }}>
          Register Member
        </h2>

        <form onSubmit={handleSubmit}>
        
          <div style={{ marginBottom: "15px" }}>
            <label style={{ display: "block", fontWeight: "bold" }}>Name:</label>
            <input
              type="text"
              name="name"
              placeholder="Enter full name"
              value={form.name}
              onChange={handleChange}
              style={{ width: "100%", padding: "8px" }}
            />
            {errors.name && (
              <span style={{ color: "red", fontSize: "12px" }}>
                {errors.name}
              </span>
            )}
          </div>

     
          <div style={{ marginBottom: "15px" }}>
            <label style={{ display: "block", fontWeight: "bold" }}>
              Email:
            </label>
            <input
              type="text"
              name="email"
              placeholder="Enter email"
              value={form.email}
              onChange={handleChange}
              style={{ width: "100%", padding: "8px" }}
            />
            {errors.email && (
              <span style={{ color: "red", fontSize: "12px" }}>
                {errors.email}
              </span>
            )}
          </div>

   
          <div style={{ marginBottom: "15px" }}>
            <label style={{ display: "block", fontWeight: "bold" }}>
              Mobile:
            </label>
            <input
              type="text"
              name="mobile"
              placeholder="Enter mobile number"
              value={form.mobile}
              onChange={handleChange}
              style={{ width: "100%", padding: "8px" }}
            />
            {errors.mobile && (
              <span style={{ color: "red", fontSize: "12px" }}>
                {errors.mobile}
              </span>
            )}
          </div>

 
          <div style={{ marginBottom: "15px" }}>
            <label style={{ display: "block", fontWeight: "bold" }}>
              Gender:
            </label>
            <select
              name="gender"
              value={form.gender}
              onChange={handleChange}
              style={{ width: "100%", padding: "8px" }}
            >
              <option value="">Select gender</option>
              <option value="M">M</option>
              <option value="F">F</option>
            </select>
            {errors.gender && (
              <span style={{ color: "red", fontSize: "12px" }}>
                {errors.gender}
              </span>
            )}
          </div>

       
          <div style={{ marginBottom: "15px" }}>
            <label style={{ display: "block", fontWeight: "bold" }}>
              Address:
            </label>
            <textarea
              name="address"
              placeholder="Enter address"
              value={form.address}
              onChange={handleChange}
              style={{ width: "100%", padding: "8px" }}
              rows="3"
            ></textarea>
            {errors.address && (
              <span style={{ color: "red", fontSize: "12px" }}>
                {errors.address}
              </span>
            )}
          </div>

        
          <div
            style={{
              display: "flex",
              justifyContent: "space-between",
              marginTop: "20px",
            }}
          >
            <button
              type="submit"
              style={{
                backgroundColor: "#3498db",
                color: "white",
                border: "none",
                padding: "10px 20px",
                borderRadius: "8px",
                cursor: "pointer",
              }}
            >
              Register
            </button>

            <button
              type="button"
              onClick={() => nav("/members")}
              style={{
                backgroundColor: "#e74c3c",
                color: "white",
                border: "none",
                padding: "10px 20px",
                borderRadius: "8px",
                cursor: "pointer",
              }}
            >
              Back
            </button>
          </div>
        </form>
      </div>
    </div>
  );
}

export default AddMemberPage;
