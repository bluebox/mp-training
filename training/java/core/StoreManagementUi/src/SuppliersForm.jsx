import React, { useEffect, useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import api from "./api/axios";

const SuppliersForm = () => {
  const navigate = useNavigate();

  const [formData, setFormData] = useState({
    name: "",
    gender: "",
    mobile: "",
    email: "",
    country: "",
    state: "",
    city: "",
    address: "",
    createdBy: "", 
  });

  const [countries, setCountries] = useState([]);
  const [states, setStates] = useState([]);
  const [cities, setCities] = useState([]);

  const [responseId, setResponseId] = useState(null);
  const [error, setError] = useState("");

  
  useEffect(() => {
    axios
      .get("https://countriesnow.space/api/v0.1/countries/positions")
      .then((res) => {
        if (res.data?.data) {
          setCountries(res.data.data.map((c) => c.name));
        }
      })
      .catch((err) => {
        console.error("Error fetching countries:", err);
      });
  }, []);

  
  useEffect(() => {
    const username = localStorage.getItem("username");
    if (username) {
      setFormData((prev) => ({ ...prev, createdBy: username }));
    }
  }, []);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({ ...prev, [name]: value }));
  };

  const handleCountryChange = async (e) => {
    const country = e.target.value;
    setFormData((prev) => ({ ...prev, country, state: "", city: "" }));
    setStates([]);
    setCities([]);

    try {
      const res = await axios.post("https://countriesnow.space/api/v0.1/countries/states", { country });
      if (res.data?.data?.states) {
        setStates(res.data.data.states.map((s) => s.name));
      }
    } catch (err) {
      console.error("Error fetching states:", err);
    }
  };

  const handleStateChange = async (e) => {
    const state = e.target.value;
    setFormData((prev) => ({ ...prev, state, city: "" }));
    setCities([]);

    try {
      const res = await axios.post("https://countriesnow.space/api/v0.1/countries/cities", {
        country: formData.country,
        state,
      });
      if (res.data?.data) {
        setCities(res.data.data);
      }
    } catch (err) {
      console.error("Error fetching cities:", err);
    }
  };

  const validateForm = () => {
    const nameRegex = /^[A-Za-z\s]+$/;
    const createdByRegex = /^[A-Za-z\s]+$/;
    const mobileRegex = /^[0-9]{10}$/;
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

    if (!nameRegex.test(formData.name.trim()) || formData.name.trim().length < 3) {
      return "Name must be at least 3 characters long and contain only alphabets.";
    }
    if (!["M", "F"].includes(formData.gender)) {
      return "Please select a valid gender.";
    }
    if (!mobileRegex.test(formData.mobile)) {
      return "Mobile must be 10 digits.";
    }
    if (!emailRegex.test(formData.email)) {
      return "Please enter a valid email address.";
    }
    if (!formData.country) {
      return "Country is required.";
    }
    if (!formData.state) {
      return "State is required.";
    }
    if (!formData.city) {
      return "City is required.";
    }
    if (formData.address.trim().length < 5) {
      return "Address should be at least 5 characters long.";
    }
    if (!createdByRegex.test(formData.createdBy.trim())) {
      return "Created By must contain only alphabets.";
    }

    return null;
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError("");
    setResponseId(null);

    const validationError = validateForm();
    if (validationError) {
      setError(validationError);
      return;
    }

    try {
      const response = await api.post("/suppliers", formData);
      setResponseId(response.data);
      setFormData({
        name: "",
        gender: "",
        mobile: "",
        email: "",
        country: "",
        state: "",
        city: "",
        address: "",
         //createdBy: "", 
      });
      setStates([]);
      setCities([]);
    } catch (err) {
      console.error(err);
      alert(err.response.data.message)
    }
  };

  const handleClear = () => {
    setFormData({
      name: "",
      gender: "",
      mobile: "",
      email: "",
      country: "",
      state: "",
      city: "",
      address: "",
     // createdBy: "", 
    });
    setStates([]);
    setCities([]);
    setError("");
    setResponseId(null);
  };

  return (
    <div style={styles.wrapper}>
      <div style={styles.card}>
        <button onClick={() => navigate(-1)} style={styles.backButton}>
          ← Back
        </button>
        <h2 style={styles.title}>Add Supplier</h2>
        <form style={styles.form} onSubmit={handleSubmit}>
          <div style={styles.grid}>
            {[{ label: "Name", name: "name", type: "text" },
              { label: "Mobile", name: "mobile", type: "tel", placeholder: "1234567890" },
              { label: "Email", name: "email", type: "email", placeholder: "example@mail.com" },
              // { label: "Created By", name: "createdBy", type: "text" },
            ].map(({ label, name, type, placeholder }) => (
              <div key={name} style={styles.formGroup}>
                <label style={styles.label}>{label}</label>
                <input
                  style={styles.input}
                  name={name}
                  type={type}
                  placeholder={placeholder}
                  value={formData[name]}
                  onChange={handleChange}
                  required
                  readOnly={name === "createdBy"} 
                />
              </div>
            ))}

            <div style={styles.formGroup}>
              <label style={styles.label}>Country</label>
              <select
                style={styles.input}
                name="country"
                value={formData.country}
                onChange={handleCountryChange}
                required
              >
                <option value="">Select Country</option>
                {countries.map((c) => (
                  <option key={c} value={c}>
                    {c}
                  </option>
                ))}
              </select>
            </div>

            <div style={styles.formGroup}>
              <label style={styles.label}>State</label>
              <select
                style={styles.input}
                name="state"
                value={formData.state}
                onChange={handleStateChange}
                required
                disabled={states.length === 0}
              >
                <option value="">{states.length ? "Select State" : "Select a country first"}</option>
                {states.map((s) => (
                  <option key={s} value={s}>
                    {s}
                  </option>
                ))}
              </select>
            </div>

            <div style={styles.formGroup}>
              <label style={styles.label}>City</label>
              <select
                style={styles.input}
                name="city"
                value={formData.city}
                onChange={handleChange}
                required
                disabled={cities.length === 0}
              >
                <option value="">{cities.length ? "Select City" : "Select a state first"}</option>
                {cities.map((c) => (
                  <option key={c} value={c}>
                    {c}
                  </option>
                ))}
              </select>
            </div>

            <div style={styles.formGroup}>
              <label style={styles.label}>Gender</label>
              <select
                style={styles.input}
                name="gender"
                value={formData.gender}
                onChange={handleChange}
                required
              >
                <option value="">Select Gender</option>
                <option value="M">Male</option>
                <option value="F">Female</option>
              </select>
            </div>

            <div style={styles.formGroupFull}>
              <label style={styles.label}>Address</label>
              <textarea
                style={{ ...styles.input, resize: "vertical" }}
                name="address"
                value={formData.address}
                onChange={handleChange}
                required
                rows={3}
              />
            </div>
          </div>

          <div style={styles.buttonRow}>
            <button type="submit" style={styles.submitButton}>
              Add Supplier
            </button>
            <button type="button" style={styles.clearButton} onClick={handleClear}>
              Clear Form
            </button>
          </div>

          {responseId && <p style={styles.successMsg}>Supplier added successfully! ID: {responseId}</p>}
          {error && <p style={styles.errorMsg}>{error}</p>}
        </form>
      </div>
    </div>
  );
};

const styles = {
  wrapper: {
    
    display: "flex",
    justifyContent: "center",
    alignItems: "center",
    padding: "20px",
    backgroundColor: "#ffffff",
   //minHeight: "100vh",
  },
  card: {
    height:"50%",
    width: "100%",
    maxWidth: "900px",
    backgroundColor: "#ffffff",
    padding: "30px",
    borderRadius: "12px",
     boxShadow: "0 8px 30px rgba(0,0,0,0.1)",
    position: "relative",
  },
  backButton: {
    position: "absolute",
    top: "20px",
    left: "20px",
    background: "none",
    padding: "10px 15px",
    borderRadius:"20px",
    backgroundColor: "#f0b222ff",
    border: "none",
    color: "#232930ff",
    fontSize: "16px",
    cursor: "pointer",
  },
  title: {
    textAlign: "center",
    marginBottom: "30px",
    fontSize: "24px",
    color: "#333",
    fontWeight: "600",
  },
  form: {
    display: "flex",
    flexDirection: "column",
  },
  grid: {
    display: "grid",
    gridTemplateColumns: "repeat(2, 1fr)",
    gap: "20px",
  },
  formGroup: {
    display: "flex",
    flexDirection: "column",
  },
  formGroupFull: {
    gridColumn: "1 / -1",
    display: "flex",
    flexDirection: "column",
  },
  label: {
    marginBottom: "6px",
    fontWeight: "500",
    color: "#555",
  },
  input: {
    padding: "10px",
    border: "1px solid #ccc",
    borderRadius: "8px",
    fontSize: "14px",
    outline: "none",
  },
  buttonRow: {
    display: "flex",
    justifyContent: "flex-end",
    marginTop: "20px",
  },
  submitButton: {
    backgroundColor: "#007bff",
    color: "#fff",
    fontSize: "16px",
    padding: "12px 24px",
    border: "none",
    borderRadius: "8px",
    cursor: "pointer",
  },
  clearButton: {
    backgroundColor: "#6c757d",
    color: "#fff",
    fontSize: "16px",
    padding: "12px 24px",
    border: "none",
    borderRadius: "8px",
    cursor: "pointer",
    marginLeft: "10px",
  },
  successMsg: {
    marginTop: "15px",
    color: "green",
    fontWeight: "bold",
    textAlign: "center",
  },
  errorMsg: {
    marginTop: "15px",
    color: "red",
    fontWeight: "bold",
    textAlign: "center",
  },
};

export default SuppliersForm;
