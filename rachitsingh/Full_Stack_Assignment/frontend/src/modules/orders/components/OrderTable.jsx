import React from "react";

const OrderTable = ({ orders, onView, onCancel }) => {
  return (
    <div className="overflow-x-auto">
      <table className="min-w-full bg-gray-800 text-white rounded-lg overflow-hidden">
        <thead className="bg-gray-700 text-left">
          <tr>
            <th className="px-4 py-2">Order ID</th>
            <th className="px-4 py-2">Status</th>
            <th className="px-4 py-2">Total</th>
            <th className="px-4 py-2">Address</th>
            <th className="px-4 py-2">Placed At</th>
            <th className="px-4 py-2">Actions</th>
          </tr>
        </thead>
        <tbody>
          {orders.map((order) => (
            <tr
              key={order.orderId}
              className="border-t border-gray-600 hover:bg-gray-700"
            >
              <td className="px-4 py-2">{order.orderId}</td>
              <td className="px-4 py-2">{order.status}</td>
              <td className="px-4 py-2">₹{order.totalAmount}</td>
              <td className="px-4 py-2">{order.address}</td>
              <td className="px-4 py-2">
                {new Date(order.placedAtDate).toLocaleString()}
              </td>
              <td className="px-4 py-2 space-x-2">
                <button
                  onClick={() => onView(order.orderId)}
                  className="bg-blue-600 hover:bg-blue-700 px-3 py-1 rounded text-sm"
                >
                  View
                </button>
                <button
                  onClick={() => onCancel(order.orderId)}
                  disabled={order.status === "CANCELLED"}
                  className={`px-3 py-1 rounded text-sm ${
                    order.status === "CANCELLED"
                      ? "bg-gray-500 cursor-not-allowed"
                      : "bg-red-600 hover:bg-red-700"
                  }`}
                >
                  Cancel
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default OrderTable;
