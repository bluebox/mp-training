import React, { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import api from "./api/axios";

const ProductsForm = () => {
  const navigate = useNavigate();
  const [formData, setFormData] = useState({
    productId: "",
    name: "",
    status: "",
  });

  const [responseId, setResponseId] = useState(null);
  const [error, setError] = useState("");

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  const validateForm = () => {
    const { productId, name, status } = formData;

    if (!productId.trim()) return "Product ID is required.";
    if (!name.trim() || name.trim().length < 2)
      return "Name must be at least 2 characters long.";
    if (!["A", "I"].includes(status))
      return "Please select a valid status (Active or Inactive).";

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
      const response = await api.post("/products", formData);
      setResponseId(response.data);
      setFormData({
        productId: "",
        name: "",
        status: "",
      });
    } catch (err) {
      console.error(err);
      setError(err.response.data.message);
    }
  };

  return (
    <div style={styles.container}>
      <button onClick={() => navigate(-1)} style={styles.backButton}>
        ← Back
      </button>
      <h2 style={styles.title}>Add Product</h2>
      <form style={styles.form} onSubmit={handleSubmit}>
        <label style={styles.label}>
          Product ID:
          <input
            style={styles.input}
            name="productId"
            value={formData.productId}
            onChange={handleChange}
            required
          />
        </label>

        <label style={styles.label}>
          Name:
          <input
            style={styles.input}
            name="name"
            value={formData.name}
            onChange={handleChange}
            required
          />
        </label>

        <label style={styles.label}>
          Status:
          <select
            style={styles.input}
            name="status"
            value={formData.status}
            onChange={handleChange}
            required
          >
            <option value="">Select status</option>
            <option value="A">Active</option>
            <option value="I">Inactive</option>
          </select>
        </label>

        <div style={styles.buttonWrapper}>
          <button type="submit" style={styles.button}>
            Add Product
          </button>
        </div>
      </form>

      {responseId && (
        <p style={styles.successMsg}> {responseId}</p>
      )}
      {error && <p style={styles.errorMsg}>{error}</p>}
    </div>
  );
};

export default ProductsForm;

const styles = {
  container: {
    maxWidth: "600px",
    margin: "60px auto",
    padding: "25px",
    borderRadius: "10px",
    backgroundColor: "#f9fafb",
    boxShadow: "0 0 15px rgba(0, 0, 0, 0.1)",
    fontFamily: "'Segoe UI', Tahoma, Geneva, Verdana, sans-serif",
    position: "relative",
  },
  backButton: {
    position: "absolute",
    top: "20px",
    left: "20px",
    background: "none",
    padding: "10px 15px",
    backgroundColor: "#f0b222ff",
    border: "none",
    borderRadius: "20px",
    color: "#fff",
    fontSize: "16px",
    cursor: "pointer",
  },
  title: {
    textAlign: "center",
    color: "#333",
    marginBottom: "30px",
  },
  form: {
    display: "flex",
    flexDirection: "column",
    gap: "15px",
  },
  label: {
    display: "flex",
    flexDirection: "column",
    fontWeight: "500",
    color: "#444",
  },
  input: {
    padding: "10px",
    border: "1px solid #ccc",
    borderRadius: "6px",
    fontSize: "14px",
  },
  buttonWrapper: {
    display: "flex",
    justifyContent: "flex-end",
    marginTop: "10px",
  },
  button: {
    backgroundColor: "#4CAF50",
    color: "white",
    fontSize: "16px",
    padding: "12px 20px",
    border: "none",
    borderRadius: "6px",
    cursor: "pointer",
    transition: "background-color 0.3s ease",
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
