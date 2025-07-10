import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

function UpdateStatus() {
  const [orders, setOrders] = useState([]);
  const [loading, setLoading] = useState(true);
  const [statusMessage, setStatusMessage] = useState("");
  const [error, setError] = useState("");
  const navigate = useNavigate();

  const fetchPendingOrders = () => {
    setLoading(true);
    fetch("http://localhost:8080/admin/pending-orders", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },credentials: "include",
    })
      .then(async (res) => {
        if (!res.ok) {
          const err = await res.json();
          throw new Error(err.message || "Failed to fetch pending orders");
        }
        return res.json();
      })
      .then((data) => {
        setOrders(data);
        setLoading(false);
      })
      .catch((e) => {
        setError(e.message);
        setLoading(false);
      });
  };

  useEffect(() => {
    fetchPendingOrders();
  }, []);

  const handleStatusUpdate = (orderId, newStatus) => {
    const updateData = {
      orderId,
      orderStatus: newStatus,
    };

    fetch("http://localhost:8080/admin/update-order-status", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },credentials: "include",
      body: JSON.stringify(updateData),
    })
      .then(async (res) => {
        const data = await res.json();
        if (!res.ok || data.status === "error") {
          throw new Error(data.message || "Update failed");
        }
        setStatusMessage(`Order ${orderId} ${newStatus.toLowerCase()} successfully`);
        fetchPendingOrders(); // Refresh list
      })
      .catch((e) => {
        setStatusMessage(`Error: ${e.message}`);
      });
  };

  if (loading) return <div>Loading pending orders...</div>;
  if (error) return <div>Error: {error}</div>;
  if (!orders.length) return <div>No pending orders found.</div>;

  return (
    <div>
      <h2>Pending Orders - Approve / Reject</h2>
      {statusMessage && <p style={{ color: "green" }}>{statusMessage}</p>}

      <table border="1" cellPadding="8" cellSpacing="0">
        <thead>
          <tr>
            <th>Order ID</th>
            <th>Order Date</th>
            <th>Cost</th>
            <th>Discount</th>
            <th>Status</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {orders.map((order) => (
            <tr key={order.orderId}>
              <td>{order.orderId}</td>
              <td>{new Date(order.orderDate).toLocaleDateString()}</td>
              <td>{order.orderCost.toFixed(2)}</td>
              <td>{order.orderDiscount}%</td>
              <td>{order.orderStatus}</td>
              <td>
                <button onClick={() => handleStatusUpdate(order.orderId, "APPROVED")}>
                  Approve
                </button>
                <button
                  onClick={() => handleStatusUpdate(order.orderId, "REJECTED")}
                  style={{ marginLeft: "10px", backgroundColor: "#f55", color: "#fff" }}
                >
                  Reject
                </button>
                <button
                  onClick={() => navigate(`/order/${order.orderId}`)}
                  style={{ marginLeft: "10px" }}
                >
                  View Full Details
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default UpdateStatus;
