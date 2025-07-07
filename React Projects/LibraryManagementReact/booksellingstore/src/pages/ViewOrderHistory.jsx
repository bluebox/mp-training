import React, { useEffect, useState } from 'react';

function ViewOrderHistory() {
  const [orders, setOrders] = useState([]);

  useEffect(() => {
    fetch('http://localhost:8080/orders/list')
      .then(res => res.json())
      .then(data => setOrders(data))
      .catch(err => console.error('Error fetching orders:', err));
  }, []);

  // Helper function to format date
  const formatDate = (isoString) => {
    const date = new Date(isoString);
    return date.toLocaleDateString() + ' ' + date.toLocaleTimeString();
  };

  return (
    <div style={{ padding: '20px' }}>
      <h2>Order History</h2>
      <table border="1" cellPadding="10" cellSpacing="0">
        <thead>
          <tr>
            <th>Order ID</th>
            <th>Member ID</th>
            <th>Total Cost (₹)</th>
            <th>Purchase Date</th>
          </tr>
        </thead>
        <tbody>
          {orders.length > 0 ? (
            orders.map(order => (
              <tr key={order.orderId}>
                <td>{order.orderId}</td>
                <td>{order.memberId}</td>
                <td>{order.totalCost}</td>
                <td>{formatDate(order.purchaseDate)}</td>
              </tr>
            ))
          ) : (
            <tr>
              <td colSpan="4" style={{ textAlign: 'center' }}>No orders found</td>
            </tr>
          )}
        </tbody>
      </table>
    </div>
  );
}

export default ViewOrderHistory;
