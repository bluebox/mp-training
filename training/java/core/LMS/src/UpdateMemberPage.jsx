import axios from "axios";
import React, { useState, useEffect } from "react";
import { useLocation, useNavigate, useParams } from "react-router-dom";

function UpdateMemberPage() {
     const location = useLocation();

  const { id } = useParams(); 
//   console.log(id);
  const nav = useNavigate();
// console.log("aslknasdn");
const [member, setMember] = useState({
    id: "",
    name: "",
    email: "",
    mobile: "",
    gender: "",
    address: "",
});

console.log(member);
 
  const [errors, setErrors] = useState({
    name: "",
    email: "",
    mobile: "",
    gender: "",
    address: "",
  });

  
 useEffect(() => {
    fetchMem();
  }, []);

  const fetchMem = async () => {
    try {
       
            const response =await axios.get(`http://localhost:8082/members/${id}`);
            const data=response.data;
          //  console.log("asdasdasd");
           // console.log(data);
      setMember(data);
    } catch {
      alert("Failed to fetch member");
    }
  };

 
  const handleChange = (e) => {
    const { name, value } = e.target;
    setMember({ ...member, [name]: value });
  };

 
  const handleUpdate = async () => {
    let valid = true;
    let newErrors = { name: "", email: "", mobile: "", gender: "", address: "" };

    if (!member.name) {
      newErrors.name = "Name is required";
      valid = false;
    }
    if (!member.email.includes("@")) {
      newErrors.email = "Valid email is required";
      valid = false;
    }
    if (member.mobile.length !== 10) {
      newErrors.mobile = "Mobile must be 10 digits";
      valid = false;
    }
    if (!member.gender) {
      newErrors.gender = "Please select gender";
      valid = false;
    }
    if (!member.address) {
      newErrors.address = "Address required";
      valid = false;
    }

    setErrors(newErrors);

    if (valid) {
     
            const response =await axios.put(`http://localhost:8082/members/${id}`,member);
            const data=response.data;
            console.log(data);
            alert("member updated successfully");
            
    }
  };

  return (
    <div style={container}>
      <h2 style={{ marginBottom: "15px" }}>Update Member Details</h2>

      <div style={formGroup}>
        <label style={labelStyle}>Member ID:</label>
        <input
          type="text"
          name="id"
          value={member.memberId}
          disabled
          style={inputStyle}
        />
      </div>

      <div style={formGroup}>
        <label style={labelStyle}>Name:</label>
        <div>
          <input
            type="text"
            name="name"
            value={member.name}
            onChange={handleChange}
            style={inputStyle}
          />
          <div style={errorStyle}>{errors.name}</div>
        </div>
      </div>

      <div style={formGroup}>
        <label style={labelStyle}>Email:</label>
        <div>
          <input
            type="email"
            name="email"
            value={member.email}
            onChange={handleChange}
            style={inputStyle}
          />
          <div style={errorStyle}>{errors.email}</div>
        </div>
      </div>

      <div style={formGroup}>
        <label style={labelStyle}>Mobile:</label>
        <div>
          <input
            type="text"
            name="mobile"
            value={member.mobile}
            onChange={handleChange}
            style={inputStyle}
          />
          <div style={errorStyle}>{errors.mobile}</div>
        </div>
      </div>

      <div style={formGroup}>
        <label style={labelStyle}>Gender:</label>
        <div>
          <select
            name="gender"
            value={member.gender}
            onChange={handleChange}
            style={inputStyle}
          >
            <option value="">Select gender</option>
            <option value="Male">Male</option>
            <option value="Female">Female</option>
          </select>
          <div style={errorStyle}>{errors.gender}</div>
        </div>
      </div>

      <div style={formGroup}>
        <label style={labelStyle}>Address:</label>
        <div>
          <textarea
            name="address"
            value={member.address}
            onChange={handleChange}
            rows={2}
            style={inputStyle}
          />
          <div style={errorStyle}>{errors.address}</div>
        </div>
      </div>

      <div style={{ marginTop: "15px" }}>
        <button onClick={handleUpdate} style={btnStyleBlue}>
          Update
        </button>
        <button onClick={() => nav("/view-members")} style={btnStyleRed}>
          Back
        </button>
      </div>
    </div>
  );
}


const container = {
  maxWidth: "400px",
  margin: "30px auto",
  padding: "20px",
  border: "1px solid #ddd",
  borderRadius: "8px",
  backgroundColor: "#fafafa",
};
const formGroup = { marginBottom: "12px", display: "flex", gap: "6px" };
const labelStyle = { minWidth: "80px", fontWeight: "bold" };
const inputStyle = { padding: "6px", width: "200px" };
const errorStyle = { color: "red", fontSize: "12px" };
const btnStyleBlue = {
  backgroundColor: "#3498db",
  color: "white",
  padding: "8px 15px",
  border: "none",
  borderRadius: "6px",
  cursor: "pointer",
  marginRight: "10px",
};
const btnStyleRed = {
  backgroundColor: "#e74c3c",
  color: "white",
  padding: "8px 15px",
  border: "none",
  borderRadius: "6px",
  cursor: "pointer",
};

export default UpdateMemberPage;
