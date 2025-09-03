import React, { useEffect, useState } from "react";
import api from "../config/axiosConfig";
import Swal from "sweetalert2";

function ItemsManagement() {
  const [items, setItems] = useState([]);
  const [loading, setLoading] = useState(false);

  const fetchItems = async () => {
    setLoading(true);
    try {
      const res = await api.get("/api/items/all");
      if (res.data.success) {
        setItems(res.data.data);
      } else {
        Swal.fire("Failed!", "Failed to fetch items", "error");
      }
      
    } catch (error) {
      console.error(error);
      Swal.fire("Error!", "Error fetching items", "error");
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchItems();
  }, []);


  const handleToggle = async (id) => {
    try {
      const res = await api.post(`/api/items/availability/${id}`);
      if (res.data.success) {
        setItems((prev) =>
          prev.map((item) =>
            item.id === id
              ? {
                  ...item,
                  available:
                    item.available === "Available"
                      ? "Unavailable"
                      : "Available",
                }
              : item
          )
        );

        Swal.fire({
          icon: "success",
          title: "Updated!",
          text: "Item availability updated successfully.",
          timer: 2000,
          showConfirmButton: false,
        });
      } else {
        Swal.fire("Failed!", "Failed to update availability", "error");
      }
    } catch (error) {
      console.error(error);
      Swal.fire("Error!", "Error updating availability", "error");
    }
  };

  return (
    <div className="container mt-4">
      <h2 className="mb-4 text-center">Items Availability</h2>

      {loading && <p className="text-center">Loading...</p>}

      <div className="row">
        {items.map((item) => {
          const isAvailable = item.available === "Available";
          return (
            <div key={item.id} className="col-md-4 mb-4">
              <div className="card h-100 shadow">
                <img
                  src={item.imageUrl}
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

                  <div className="mt-auto d-flex align-items-center justify-content-between">
                    <span
                      className={`fw-bold ${
                        isAvailable ? "text-success" : "text-danger"
                      }`}
                    >
                      {isAvailable ? "Available" : "Unavailable"}
                    </span>
                    <div className="form-check form-switch">
                      <input
                        className="form-check-input"
                        type="checkbox"
                        checked={isAvailable}
                        onChange={() => handleToggle(item.id)}
                      />
                    </div>
                  </div>
                </div>
              </div>
            </div>
          );
        })}
      </div>
    </div>
  );
}
export default ItemsManagement;