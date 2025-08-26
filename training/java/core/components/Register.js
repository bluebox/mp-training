

import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import "./Auth.css";
import "./LoginPage"
function Register({ token, onFormSwitch }) {
  const navigate = useNavigate();
  const [formData, setFormData] = useState({
    userId: "", userName: "", email: "", mobileNumber: "", password: "", usertype: "STUDENT"
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData(prev => ({ ...prev, [name]: value }));
  };

  const handleSubmit = async (e) => {
    console.log("formdata ",JSON.stringify(formData));
    e.preventDefault();
    try {
     const response = await axios.post(

         "http://localhost:8090/auth/signup",
         formData);
         console.log("response",response.data);
      alert("User registered successfully");
      navigate("/login");
    } catch (err) {
      alert("Registration failed: " + (err.response?.data || err.message));
    }
  };

  return (
    <div className="auth-container">
      <div className="auth-card">
        <h2 className="auth-title">Register</h2>
        <form onSubmit={handleSubmit} className="auth-form">
          <input name="userId" placeholder="User ID" onChange={handleChange} required />
          <input name="userName" placeholder="Full Name" onChange={handleChange} required />
          <input name="email" type="email" placeholder="Email" onChange={handleChange} required />
          <input name="mobileNumber" placeholder="Mobile Number" onChange={handleChange} />
          <input name="password" type="password" placeholder="Password" onChange={handleChange} required />
          <select name="usertype" onChange={handleChange}>
            <option value="STUDENT">Student</option>
            <option value="FACULTY">Faculty</option>
            <option value="ADMIN">Admin</option>
          </select>
          <button type="submit" className="btn-primary" onClick={() => navigate("/register")}>Register</button>
        </form>
        <p className="switch-text">
          Already have an account?{" "}
          <span onClick={() => navigate("/login")} className="switch-link">
            Login
          </span>
        </p>
      </div>
    </div>
  );
}

export default Register;







// import React, { useState } from "react";
// import axios, { formToJSON } from "axios";
// import { useNavigate } from "react-router-dom";
// import { BrowserRouter } from "react-router-dom";

// function Register({ token, onFormSwitch }) {
//   const navigate = useNavigate();
//   const [error, setError] = useState("");
//   const [formData, setFormData] = useState({
//     userId: "",
//     userName: "",
//     email: "",
//     mobileNumber: "",
//     password: "",
//     usertype: "",
//   });

//   // Handle input change
//   const handleChange = (e) => {
//     const { name, value } = e.target;
//     setFormData((prev) => ({
//       ...prev,
//       [name]: value,
//     }));
//   };

//   // Submit handler
//   const handleSubmit = async (e) => {
//     e.preventDefault();
//     setError(""); 
//     console.log("data is "+formData.userName);
    
//     try {
//       const response = await axios.post(
//         "http://localhost:8090/auth/signup",
//         formData
//         // {
//         //   headers: {
//         //     "Content-Type": "application/json",
//         //     "X-XSRF-TOKEN": token, 
//         //   },
//         //   withCredentials: true, 
//         // }
//       );

//       console.log("Response:", response.data);
//       alert("User registered successfully ");
//       navigate("/login"); 
//     } catch (err) {
//       console.error("Error:", err.response?.data || err.message);
//       setError(err.response?.data || "Something went wrong");
//       alert("Registration failed  " + (err.response?.data || err.message));
//     }
//   };

//   return (
//     <div className="row">
//       <div className="col-sm-4">
//         <h2>User Register</h2>

//         <form className="register-form" onSubmit={handleSubmit}>
//           <div className="form-group">
//             <label htmlFor="userId">User ID</label>
//             <input
//               className="form-control"
//               value={formData.userId}
//               onChange={handleChange}
//               type="text"
//               placeholder="User ID"
//               id="userId"
//               name="userId"
//               required
//             />
//           </div>

//           <div className="form-group">
//             <label htmlFor="userName">Username</label>
//             <input
//               className="form-control"
//               value={formData.userName}
//               onChange={handleChange}
//               type="text"
//               placeholder="User Full Name"
//               id="userName"
//               name="userName"
//               required
//             />
//           </div>

//           <div className="form-group">
//             <label htmlFor="email">Email</label>
//             <input
//               className="form-control"
//               value={formData.email}
//               onChange={handleChange}
//               type="email"
//               placeholder="youremail@gmail.com"
//               id="email"
//               name="email"
//               required
//             />
//           </div>

//           <div className="form-group">
//             <label htmlFor="mobileNumber">Mobile Number</label>
//             <input
//               className="form-control"
//               value={formData.mobileNumber}
//               onChange={handleChange}
//               type="tel"
//               placeholder="123-456-7890"
//               id="mobileNumber"
//               name="mobileNumber"
//               pattern="[0-9]{3}-[0-9]{3}-[0-9]{4}"
//             />
//           </div>

//           <div className="form-group">
//             <label htmlFor="password">Password</label>
//             <input
//               className="form-control"
//               value={formData.password}
//               onChange={handleChange}
//               type="password"
//               placeholder="Password"
//               id="password"
//               name="password"
//               required
//             />
//           </div>

//           <div className="form-group">
//             <label htmlFor="userType">User Type</label>
//             <select
//               id="usertype"
//               name="usertype"
//               value={formData.usertype}
//               onChange={handleChange}
//               className="form-control"
//             >
//               <option  value="STUDENT">Student</option>
//               <option  value="FACULTY">Faculty</option>
//               <option value="ADMIN">Admin</option>
//             </select>
//           </div>

//           <button type="submit" className="btn btn-primary mt-3">
//             Register
//           </button>
//         </form>

//         {error && <p className="text-danger mt-2">{error}</p>}

//         <br />
//         <button
//           className="btn btn-secondary"
//           onClick={() => onFormSwitch("login")}
//         >
//           Already have an account? Login here.
//         </button>
//       </div>
//     </div>
//   );
// }

// export default Register;