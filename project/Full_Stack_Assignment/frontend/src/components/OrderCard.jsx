import React, { useState } from "react";

const statusLabels = {
  P: "Processing",
  C: "Cancelled",
  D: "Delivered",
};

const statusClass = {
  P: "text-warning",
  C: "text-danger",
  D: "text-success",
};

const OrderCard = ({ order, onUpdateAddress, onClose }) => {
  const [newAddress, setNewAddress] = useState(order.address || "");

  const handleUpdate = (e) => {
    e.stopPropagation();
    e.preventDefault();

    if (!newAddress.trim()) {
      alert("Address cannot be empty.");
      return;
    }

    onUpdateAddress(newAddress.trim());
  };

  return (
    <div
      className="position-relative bg-dark text-light p-4 rounded shadow-lg border border-secondary overflow-auto"
      style={{ maxHeight: "90vh" }}
    >
      <button
        onClick={onClose}
        className="btn-close position-absolute top-0 end-0 m-3"
        aria-label="Close"
        style={{ filter: "invert(1)" }}
      ></button>

      <h2 className="h4 fw-semibold mb-4 border-bottom pb-2 border-secondary">
        Order #{order.orderId}
      </h2>

      <div className="row g-4">
        <div className="col-md-6">
          <p className="mb-2">
            <strong>Status:</strong>{" "}
            <span
              className={`text-uppercase fw-medium ${
                statusClass[order.status]
              }`}
            >
              {statusLabels[order.status] || order.status}
            </span>
          </p>
          <p className="mb-2">
            <strong>Total:</strong> ₹{order.totalAmount}
          </p>
          <p className="mb-2">
            <strong>Placed:</strong>{" "}
            {new Date(order.placedAtDate).toLocaleString()}
          </p>
        </div>

        {order.status === Object.keys(statusLabels)[0] && (
          <div className="col-md-6">
            <label className="form-label text-muted small">
              Update Address
            </label>
            <textarea
              className="form-control bg-secondary text-light border-dark"
              rows={3}
              value={newAddress}
              onChange={(e) => setNewAddress(e.target.value)}
            />
            <button
              onClick={handleUpdate}
              className="btn btn-success btn-sm mt-3"
            >
              Save Address
            </button>
          </div>
        )}
      </div>

      <div className="mt-5">
        <h5 className="fw-semibold border-bottom pb-2 border-secondary mb-3">
          Items in this Order
        </h5>
        <div className="row g-3">
          {order.orderItems.map((item) => (
            <div key={item.orderItemId} className="col-sm-6">
              <div className="p-3 bg-secondary text-light border border-dark rounded shadow-sm">
                <div className="d-flex justify-content-between align-items-start small">
                  <div>
                    <p className="fw-semibold mb-1">{item.productName}</p>
                    <p className="text-light mb-0">Qty: {item.quantity}</p>
                  </div>
                  <div className="text-end">
                    <p className="mb-1">₹{item.price}</p>
                    <p className="text-light small mb-0">
                      ID: {item.productId}
                    </p>
                  </div>
                </div>
              </div>
            </div>
          ))}
        </div>
      </div>
    </div>
  );
};

export default OrderCard;
