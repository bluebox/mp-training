import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import axios from 'axios';

function OrderDetailsAdmin() {
  const { orderId } = useParams();
  const [order, setOrder] = useState(null);
  const [error, setError] = useState('');

  useEffect(() => {
    axios.post('http://localhost:8080/orders/view-order', { orderId: parseInt(orderId) })
      .then(res => setOrder(res.data))
      .catch(err => {
        console.error('Order detail error:', err);
        setError('Failed to load order details');
      });
  }, [orderId]);

  if (error) return <p style={{ color: 'red' }}>{error}</p>;
  if (!order) return <p>Loading order details...</p>;

  return (
    <div style={{ padding: '20px' }}>
      <h2>Order #{order.orders.orderId} Details</h2>
      <p>Status: {order.orders.orderStatus}</p>
      <p>Discount: {order.orders.orderDiscount}%</p>
      <p>Total Cost: {order.orders.orderCost.toFixed(2)}</p>

      <h3>Products</h3>
      <table border={1} cellPadding={6}>
        <thead>
          <tr>
            <th>Product</th>
            <th>Supplier</th>
            <th>Qty</th>
            <th>Cost</th>
          </tr>
        </thead>
        <tbody>
          {order.orderProductDetails.map((item, i) => (
            <tr key={i}>
              <td>{item.product}</td>
              <td>{item.supplier}</td>
              <td>{item.productQuantity}</td>
              <td>{item.productCost.toFixed(2)}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default OrderDetailsAdmin;
