import React, { useState } from "react";

const OrderCard = ({ order, onUpdateAddress, onClose }) => {
  const [newAddress, setNewAddress] = useState(order.address || "");

  const handleUpdate = () => {
    if (!newAddress.trim()) {
      alert("Address cannot be empty.");
      return;
    }
    onUpdateAddress(newAddress.trim());
  };

  return (
    <div className="mt-6 bg-gray-900 text-white p-6 rounded-xl shadow-md relative">
      <button
        onClick={onClose}
        className="absolute top-2 right-3 text-sm text-red-400 hover:text-red-500"
      >
        ✕ Close
      </button>

      <h2 className="text-xl font-bold mb-4">Order #{order.orderId}</h2>

      <div className="grid grid-cols-2 gap-4">
        <div>
          <p>
            <strong>Status:</strong> {order.status}
          </p>
          <p>
            <strong>Total:</strong> ₹{order.totalAmount}
          </p>
          <p>
            <strong>Placed:</strong>{" "}
            {new Date(order.placedAtDate).toLocaleString()}
          </p>
        </div>
        <div>
          <p>
            <strong>Current Address:</strong>
          </p>
          <textarea
            className="w-full mt-1 p-2 bg-gray-800 border border-gray-600 rounded text-sm"
            rows={3}
            value={newAddress}
            onChange={(e) => setNewAddress(e.target.value)}
          />
          <button
            onClick={handleUpdate}
            className="mt-2 px-4 py-1 bg-green-600 hover:bg-green-700 rounded text-sm"
          >
            Update Address
          </button>
        </div>
      </div>

      <hr className="my-4 border-gray-600" />

      <h3 className="text-lg font-semibold mb-2">Items:</h3>
      <ul className="space-y-2">
        {order.orderItems.map((item) => (
          <li
            key={item.orderItemId}
            className="p-2 bg-gray-800 rounded border border-gray-700"
          >
            <div className="flex justify-between items-center text-sm">
              <div>
                <p>
                  <strong>{item.productName}</strong>
                </p>
                <p>Qty: {item.quantity}</p>
              </div>
              <div className="text-right">
                <p>₹{item.price}</p>
                <p>ID: {item.productId}</p>
              </div>
            </div>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default OrderCard;
