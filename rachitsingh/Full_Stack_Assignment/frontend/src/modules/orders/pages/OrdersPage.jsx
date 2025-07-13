import React, { useState, useEffect } from "react";
import OrderTable from "../components/OrderTable";
import OrderCard from "../components/OrderCard";
import { fetchOrders, fetchOrderDetails, updateOrder } from "../api/orderApi";

const OrdersPage = () => {
  const [orders, setOrders] = useState([]);
  const [selectedOrder, setSelectedOrder] = useState(null);
  const customerId = 101;

  useEffect(() => {
    fetchOrders(customerId)
      .then((data) => setOrders(data))
      .catch((err) => console.error("Error fetching orders:", err));
  }, []);

  const handleView = async (orderId) => {
    try {
      const data = await fetchOrderDetails(orderId, customerId);
      setSelectedOrder(data);
    } catch (err) {
      console.error("Failed to fetch order details", err);
    }
  };

  const handleCancel = async (orderId) => {
    try {
      const payload = {
        customerId,
        orderIds: [orderId],
      };
      await updateOrder(payload);

      setOrders((prevOrders) =>
        prevOrders.map((order) =>
          order.orderId === orderId ? { ...order, status: "CANCELLED" } : order
        )
      );

      if (selectedOrder && selectedOrder.orderId === orderId) {
        setSelectedOrder((prev) => ({ ...prev, status: "CANCELLED" }));
      }

      alert("Order cancelled successfully!");
    } catch (err) {
      console.error("Cancel failed", err);
      alert("Failed to cancel order.");
    }
  };

  const handleAddressUpdate = async (newAddress) => {
    try {
      const payload = {
        customerId,
        orderIds: [selectedOrder.orderId],
        address: newAddress,
      };
      await updateOrder(payload);

      setOrders((prevOrders) =>
        prevOrders.map((order) =>
          order.orderId === selectedOrder.orderId
            ? { ...order, address: newAddress }
            : order
        )
      );

      setSelectedOrder((prev) => ({ ...prev, address: newAddress }));
      alert("Address updated successfully!");
    } catch (err) {
      console.error("Address update failed", err);
      alert("Failed to update address.");
    }
  };

  return (
    <div className="p-6 text-white">
      <h1 className="text-2xl font-semibold mb-4">My Orders</h1>
      <OrderTable orders={orders} onView={handleView} onCancel={handleCancel} />
      {selectedOrder && (
        <OrderCard
          order={selectedOrder}
          onUpdateAddress={handleAddressUpdate}
          onClose={() => setSelectedOrder(null)}
        />
      )}
    </div>
  );
};

export default OrdersPage;
