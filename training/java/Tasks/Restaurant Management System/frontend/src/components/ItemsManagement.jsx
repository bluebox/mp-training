import React, { useEffect, useState } from "react";
import axios from "axios";

function ItemsManagement() {
  const [items, setItems] = useState([]);
  const [loading, setLoading] = useState(false);

  const fetchItems = async () => {
    setLoading(true);
    try {
      const res = await axios.get("http://localhost:8080/api/items/all");
      if (res.data.success) {
        setItems(res.data.data);
      } else {
        alert("Failed to fetch items");
      }
    } catch (error) {
      console.error(error);
      alert("Error fetching items");
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchItems();
  }, []);
  const handleToggle = async (id) => {
    try {
      const res = await axios.post(
        `http://localhost:8080/api/items/availability/${id}`
      );
      if (res.data.success) {
        alert("Availability updated!");
        setItems((prev) =>
          prev.map((item) =>
            item.id === id
              ? { ...item, available: res.data.data.available }
              : item
          )
        );
      } else {
        alert("Failed to update availability");
      }
    } catch (error) {
      console.error(error);
      alert("Error updating availability");
    }
  };

  return (
    <div className="container mt-4">
      <h2 className="mb-4 text-center">Items Management</h2>

      {loading && <p className="text-center">Loading...</p>}
      <div className="row">
        {items.map((item) => (
          <div key={item.id} className="col-md-4 mb-4">
            <div className="card h-100 shadow">
              <img
                src={`http://localhost:8080${item.imageUrl}`}
                className="card-img-top"
                alt={item.name}
                style={{ height: "200px", objectFit: "cover" }}
              />
              <div className="card-body d-flex flex-column">
                <h5 className="card-title">{item.name}</h5>
                <p className="card-text">{item.description}</p>
                <p className="mb-1">
                  <strong>Price:</strong> ₹{item.price}
                </p>
                <p className="mb-2">
                  <strong>Category:</strong> {item.category}
                </p>

                <div className="mt-auto">
                  <label className="form-check-label me-2">
                    {item.available === "AVAILABLE"
                      ? "Available"
                      : "Unavailable"}
                  </label>
                  <div className="form-check form-switch d-inline-block">
                    <input
                      className="form-check-input"
                      type="checkbox"
                      checked={item.available === "AVAILABLE"}
                      onChange={() => handleToggle(item.id)}
                    />
                  </div>
                </div>
              </div>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
}

export default ItemsManagement;