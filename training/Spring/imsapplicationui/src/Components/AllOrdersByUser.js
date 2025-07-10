import React, { useEffect, useState } from 'react';
import axios from 'axios';

function AllOrdersByUser() {
  const [all, setAll] = useState([]);
  const [detail, setDetail] = useState(null);
  const [error, setError] = useState(null);

  useEffect(() => {
    axios.post('http://localhost:8080/orders/get-orders')
      .then(res => {
        console.log('All orders list:', res.data);
        setAll(Array.isArray(res.data) ? res.data : []);
      })
      .catch(err => {
        console.error(err);
        setError('Failed to load orders');
      });
  }, []);

  const handleView = (order) => {
    axios.post('http://localhost:8080/orders/view-order', { orderId: order.orderId })
      .then(res => {
        console.log('View-order response:', res.data);
        if (res.data.orders && Array.isArray(res.data.orderProductDetails)) {
          setDetail(res.data);
        } else {
          setError('Invalid FullOrder format');
        }
      })
      .catch(err => {
        console.error(err);
        setError('Failed to fetch order details');
      });
  };

  return (
    <div style={{ padding: '20px' }}>
      <h2>All Orders</h2>
      {error && <p style={{ color: 'red' }}>{error}</p>}

      <table border={1} cellPadding={8}>
        <thead>
          <tr>
            <th>Order ID</th>
            <th>Date</th>
            <th>Cost</th>
            <th>Status</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {all.map((o, idx) => (
            <tr key={idx}>
              <td>{o.orderId}</td>
              <td>{new Date(o.orderDate).toLocaleString()}</td>
              <td>{(o.orderCost || 0).toFixed(2)}</td>
              <td>{o.orderStatus}</td>
              <td>
                <button onClick={() => handleView(o)}>View</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>

      {detail && (
        <div style={{ marginTop: 20 }}>
          <h3>Order #{detail.orders.orderId} Details</h3>
          <p>Status: {detail.orders.orderStatus}</p>
          <p>Discount: {detail.orders.orderDiscount || 0}%</p>
          <p>
            Base Cost:{" "}
            {(detail.orders.orderCost / (1 - (detail.orders.orderDiscount || 0) / 100)).toFixed(2)}
          </p>
          <h4>Products</h4>
          {detail.orderProductDetails.length === 0 ? (
            <p>No products found</p>
          ) : (
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
                {detail.orderProductDetails.map((item, ii) => (
                  <tr key={ii}>
                    <td>{item.product}</td>
                    <td>{item.supplier}</td>
                    <td>{item.productQuantity}</td>
                    <td>{item.productCost.toFixed(2)}</td>
                  </tr>
                ))}
              </tbody>
            </table>
          )}
        </div>
      )}
    </div>
  );
}

export default AllOrdersByUser;
