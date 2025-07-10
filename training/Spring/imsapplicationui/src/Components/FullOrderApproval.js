import React, { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";

function FullOrderApproval() {
  const { orderId } = useParams();
  const navigate = useNavigate();
  const [fullOrder, setFullOrder] = useState(null);
  const [statusMessage, setStatusMessage] = useState("");
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    setLoading(true);
    fetch("http://localhost:8080/admin/get-orders-details", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({ orderId: parseInt(orderId, 10) }),
    })
      .then(async (res) => {
        const data = await res.json();
        {console.log("data",data)};
        if (!res.ok || data.status === "error") {
          throw new Error(data.message || "Failed to fetch full order");
        }
        setFullOrder(data);
        setStatusMessage("");
        setLoading(false);
      })
      .catch((e) => {
        setStatusMessage(`Error: ${e.message}`);
        setFullOrder(null);
        setLoading(false);
      });
  }, [orderId]);

  const handleStatusUpdate = (newStatus) => {
    const updatePayload = {
      orderId: parseInt(orderId, 10),
      orderStatus: newStatus,
    };

    fetch("http://localhost:8080/admin/update-order-status", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(updatePayload),
    })
      .then(async (res) => {
        const data = await res.json();
        if (!res.ok || data.status === "error") {
          throw new Error(data.message || "Failed to update order status");
        }
        setStatusMessage(`Order ${newStatus.toLowerCase()} successfully.`);
        // Go back after update
        setTimeout(() => navigate("/"), 1500);
      })
      .catch((e) => {
        setStatusMessage(`Error: ${e.message}`);
      });
  };

  return (
    <div>
      <h2>View Full Order Details</h2>
      {statusMessage && <p style={{ color: "green" }}>{statusMessage}</p>}
      {loading && <p>Loading...</p>}

      {fullOrder && (
        <div style={{ marginTop: "1rem" }}>
          <h3>Order Information</h3>
          <p><strong>Order ID:</strong> {fullOrder.orders.orderId}</p>
          <p><strong>Date:</strong> {new Date(fullOrder.orders.orderDate).toLocaleDateString()}</p>
          <p><strong>Cost:</strong> ₹{fullOrder.orders.orderCost.toFixed(2)}</p>
          <p><strong>Discount:</strong> {fullOrder.orders.orderDiscount}%</p>
          <p><strong>Status:</strong> {fullOrder.orders.orderStatus}</p>

          <h3>Product Details</h3>
          <table border="1" cellPadding="6" cellSpacing="0">
            <thead>
              <tr>
                <th>Product</th>
                <th>Supplier</th>
                <th>Quantity</th>
                <th>Cost</th>
              </tr>
            </thead>
            <tbody>
              {fullOrder.orderProductDetails.map((prod, index) => (
                <tr key={index}>
                  <td>{prod.product}</td>
                  <td>{prod.supplier}</td>
                  <td>{prod.productQuantity}</td>
                  <td>₹{prod.productCost.toFixed(2)}</td>
                </tr>
              ))}
            </tbody>
          </table>

          <div style={{ marginTop: "1rem" }}>
            <button onClick={() => handleStatusUpdate("APPROVED")}>
              Approve
            </button>
            <button
              onClick={() => handleStatusUpdate("REJECTED")}
              style={{ marginLeft: "10px", backgroundColor: "#f55", color: "#fff" }}
            >
              Reject
            </button>
            <button
              onClick={() => navigate("/")}
              style={{ marginLeft: "10px", backgroundColor: "#ccc" }}
            >
              Cancel
            </button>
          </div>
        </div>
      )}
    </div>
  );
}

export default FullOrderApproval;
