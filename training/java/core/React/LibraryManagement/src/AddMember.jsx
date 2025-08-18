import axios from "axios";
import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";

export default function AddMember() {
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [mobile, setMobile] = useState("");
  const [gender, setGender] = useState("");
  const [address, setAddress] = useState("");
  const [errors, setErrors] = useState({});
  const [successMessage, setSuccessMessage] = useState("");

  const navigate = useNavigate();

  const validateInputs = () => {
    const newErrors = {};
    const nameRegex = /^[A-Za-z\s]+$/;
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    const mobileRegex = /^\d{10}$/;

    if (!name.trim()) {
      newErrors.name = "Name cannot be empty.";
    } else if (!nameRegex.test(name)) {
      newErrors.name = "Invalid Name";
    }

    if (!email.trim()) {
      newErrors.email = "Email cannot be empty.";
    } else if (!emailRegex.test(email)) {
      newErrors.email = "Invalid email format.";
    }

    if (!mobile.trim()) {
      newErrors.mobile = "Mobile number cannot be empty.";
    } else if (!mobileRegex.test(mobile)) {
      newErrors.mobile = "Mobile number must be exactly 10 digits.";
    }

    if (!gender) {
      newErrors.gender = "Please select a gender.";
    }

    if (!address.trim()) {
      newErrors.address = "Address cannot be empty.";
    }

    setErrors(newErrors);
    return Object.keys(newErrors).length === 0;
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (!validateInputs()) return;

    const newMember = { name, email, mobile, gender, address };

    try {
      const response = await axios.post("http://localhost:8080/members", newMember);
      const savedMember = response.data;

      if (savedMember.memberId) {
        setSuccessMessage(
          `Member registered successfully! Member ID: ${savedMember.memberId}`
        );

        setName("");
        setEmail("");
        setMobile("");
        setGender("");
        setAddress("");
        setErrors({});

        setTimeout(() => navigate("/members"), 1500);
      }
    } catch (error) {
      console.error("Error adding member:", error);
      setErrors({ form: "Failed to register member. Please try again." });
    }
  };

  return (
    <div
      className="d-flex align-items-center justify-content-center vh-100"
      style={{
        backgroundImage: "url('/library.jpg')",
        backgroundSize: "cover",
        backgroundPosition: "center",
        backgroundAttachment: "fixed",
      }}
    >
      <div className="bg-white p-5 rounded shadow" style={{ width: "450px" }}>
        <h3 className="fw-bold text-center mb-4">Register a Member</h3>

        {errors.form && <div className="alert alert-danger">{errors.form}</div>}
        {successMessage && (
          <div className="alert alert-success">{successMessage}</div>
        )}

        <form onSubmit={handleSubmit}>
          <div className="mb-3 text-start">
            <label className="form-label">Enter Name:</label>
            <input
              type="text"
              className={`form-control ${errors.name ? "is-invalid" : ""}`}
              value={name}
              onChange={(e) => setName(e.target.value)}
            />
            {errors.name && <div className="text-danger small">{errors.name}</div>}
          </div>

          <div className="mb-3 text-start">
            <label className="form-label">Enter Email:</label>
            <input
              type="text"
              className={`form-control ${errors.email ? "is-invalid" : ""}`}
              value={email}
              onChange={(e) => setEmail(e.target.value)}
            />
            {errors.email && (
              <div className="text-danger small">{errors.email}</div>
            )}
          </div>

          <div className="mb-3 text-start">
            <label className="form-label">Enter Mobile Number:</label>
            <input
              type="text"
              className={`form-control ${errors.mobile ? "is-invalid" : ""}`}
              value={mobile}
              onChange={(e) => setMobile(e.target.value)}
            />
            {errors.mobile && (
              <div className="text-danger small">{errors.mobile}</div>
            )}
          </div>

          <div className="mb-4 text-start">
            <label className="form-label">Select Gender:</label>
            <select
              className={`form-select ${errors.gender ? "is-invalid" : ""}`}
              value={gender}
              onChange={(e) => setGender(e.target.value)}
            >
              <option value="">Gender</option>
              <option value="M">Male</option>
              <option value="F">Female</option>
            </select>
            {errors.gender && (
              <div className="text-danger small">{errors.gender}</div>
            )}
          </div>

          <div className="mb-3 text-start">
            <label className="form-label">Enter Address:</label>
            <input
              type="text"
              className={`form-control ${errors.address ? "is-invalid" : ""}`}
              value={address}
              onChange={(e) => setAddress(e.target.value)}
            />
            {errors.address && (
              <div className="text-danger small">{errors.address}</div>
            )}
          </div>

          <button type="submit" className="btn btn-success w-100 mb-3">
            Register
          </button>
          <Link to="/members" className="btn btn-danger w-100">
            Back to Dashboard
          </Link>
        </form>
      </div>
    </div>
  );
}
