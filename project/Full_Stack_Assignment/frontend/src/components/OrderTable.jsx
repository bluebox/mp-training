import React from "react";

const statusLabels = {
  P: "Processing",
  C: "Cancelled",
  D: "Delivered",
};

const statusBadgeClass = {
  P: "bg-warning text-dark",
  C: "bg-danger",
  D: "bg-success",
};

const OrderTable = ({ orders, onView, onCancel }) => {
  if (!Array.isArray(orders) || orders.length === 0) {
    return (
      <div className="bg-dark text-secondary p-4 rounded border border-secondary text-center">
        No orders to display.
      </div>
    );
  }

  return (
    <div className="table-responsive rounded shadow border border-secondary bg-dark">
      <table className="table table-dark table-hover align-middle mb-0 text-sm">
        <thead className="table-secondary text-uppercase text-muted small">
          <tr>
            <th>Order ID</th>
            <th>Status</th>
            <th>Total</th>
            <th>Address</th>
            <th>Placed At</th>
            <th className="text-center">Actions</th>
          </tr>
        </thead>
        <tbody>
          {orders.map((order) => {
            const label = statusLabels[order.status] || order.status;
            const badgeClass = statusBadgeClass[order.status] || "bg-secondary";

            return (
              <tr key={order.orderId}>
                <td className="fw-medium">{order.orderId}</td>
                <td>
                  <span
                    className={`badge rounded-pill text-uppercase ${badgeClass}`}
                  >
                    {label}
                  </span>
                </td>
                <td>₹{order.totalAmount}</td>
                <td
                  title={order.address}
                  className="text-truncate"
                  style={{ maxWidth: "250px" }}
                >
                  {order.address}
                </td>
                <td>{new Date(order.placedAtDate).toLocaleString()}</td>
                <td className="text-center">
                  <button
                    onClick={() => onView(order.orderId)}
                    className="btn btn-sm btn-primary me-2"
                  >
                    View
                  </button>
                  <button
                    onClick={() => onCancel(order.orderId)}
                    disabled={order.status === "C"}
                    className={`btn btn-sm ${
                      order.status === "C" ? "btn-secondary disabled" : "btn-danger"
                    }`}
                  >
                    Cancel
                  </button>
                </td>
              </tr>
            );
          })}
        </tbody>
      </table>
    </div>
  );
};

export default OrderTable;
