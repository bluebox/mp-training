import React, { useState } from "react";
import { createCategoryRequest } from "../../services/CategoryRequestService";
import { useAuth } from "../../context/AuthContext";

const CreateCategoryRequestForm = () => {
  const { currentUser } = useAuth();
  const [categoryName, setCategoryName] = useState("");
  const [loading, setLoading] = useState(false);

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      setLoading(true);
      await createCategoryRequest({
        categoryName,
        requestedBy: currentUser.userId,
      });
      alert("Category request submitted successfully!");
      setCategoryName("");
    } catch (error) {
      console.error("Error creating category request:", error);
      alert("Failed to create request.");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="container mt-4">
      <h2>Create Category Request</h2>
      <form onSubmit={handleSubmit}>
        <div className="form-group mb-2">
          <label>Category Name:</label>
          <input
            type="text"
            className="form-control"
            value={categoryName}
            onChange={(e) => setCategoryName(e.target.value)}
            required
          />
        </div>
        <button type="submit" className="btn btn-primary" disabled={loading}>
          {loading ? "Submitting..." : "Submit Request"}
        </button>
      </form>
    </div>
  );
};

export default CreateCategoryRequestForm;
