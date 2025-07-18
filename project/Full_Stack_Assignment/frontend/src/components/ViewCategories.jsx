import React, { useEffect, useState } from "react";
import { getAllCategories } from "../services/CategoryService";

const ViewCategories = () => {
  const [categories, setCategories] = useState([]);
  const [loading, setLoading] = useState(false);
  const [errorMsg, setErrorMsg] = useState(null);
  const [searchName, setSearchName] = useState("");
  const [searchId, setSearchId] = useState("");

  const fetchCategories = async () => {
    try {
      setLoading(true);
      setErrorMsg(null);
      const response = await getAllCategories();
      setCategories(response || []);
    } catch (error) {
      console.error("Error fetching categories:", error);
      setErrorMsg("Failed to load categories.");
      setCategories([]);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchCategories();
  }, []);

  // Filter categories based on search criteria
  const filteredCategories = categories.filter((cat) => {
    const matchesName = cat.categoryName
      .toLowerCase()
      .includes(searchName.toLowerCase());
    const matchesId =
      searchId === "" ||
      String(cat.categoryId).toLowerCase().includes(searchId.toLowerCase());
    return matchesName && matchesId;
  });

  return (
    <div className="container mt-4">
      <h2>Available Categories</h2>

      {/* Search Inputs */}
      <div className="mb-3 d-flex gap-3">
        <input
          type="text"
          className="form-control"
          style={{ maxWidth: 200 }}
          placeholder="Search by Category Name"
          value={searchName}
          onChange={(e) => setSearchName(e.target.value)}
        />
        <input
          type="text"
          className="form-control"
          style={{ maxWidth: 200 }}
          placeholder="Search by Category ID"
          value={searchId}
          onChange={(e) => setSearchId(e.target.value)}
        />
      </div>

      {loading && <p>Loading...</p>}

      {errorMsg && (
        <div className="alert alert-danger" role="alert">
          {errorMsg}
        </div>
      )}

      {!loading && !errorMsg && (
        <table border="1" cellPadding="10" className="table table-striped">
          <thead>
            <tr>
              <th>ID</th>
              <th>Category Name</th>
              <th>Created At</th>
              <th>Updated At</th>
            </tr>
          </thead>
          <tbody>
            {filteredCategories && filteredCategories.length > 0 ? (
              filteredCategories.map((cat) => (
                <tr key={cat.categoryId}>
                  <td>{cat.categoryId}</td>
                  <td>{cat.categoryName}</td>
                  <td>
                    {cat.createdAtDate
                      ? new Date(cat.createdAtDate).toLocaleString()
                      : "—"}
                  </td>
                  <td>
                    {cat.updatedAtDate
                      ? new Date(cat.updatedAtDate).toLocaleString()
                      : "—"}
                  </td>
                </tr>
              ))
            ) : (
              <tr>
                <td colSpan="4" style={{ textAlign: "center" }}>
                  No categories found.
                </td>
              </tr>
            )}
          </tbody>
        </table>
      )}
    </div>
  );
};

export default ViewCategories;

