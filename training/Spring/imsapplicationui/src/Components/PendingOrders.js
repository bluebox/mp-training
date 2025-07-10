import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

function PendingOrders() {
  const [pending, setPending] = useState([]);
  const [error, setError] = useState(null);
  const navigate = useNavigate();

  useEffect(() => {
    axios.post('http://localhost:8080/orders/pending-orders')
      .then(res => {
        console.log('API response:', res.status, res.data);
        if (Array.isArray(res.data)) {
          setPending(res.data);
          setError(null);
        } else if (res.data.status === 'error') {
          setError(res.data.message || 'Error from backend');
        } else {
          setError('Unexpected API result');
        }
      })
      .catch(err => {
        console.error('Error calling pending-orders:', err);
        if (err.response) {
          setError(`Request failed: ${err.response.status} ${err.response.data}`);
        } else {
          setError('Network or CORS error');
        }
      });
  }, []);

  const handleWithdraw = (order) => {
    axios.post('http://localhost:8080/orders/withdraw-order', order)
      .then(() => {
        setPending(pending.filter(o => o.orderId !== order.orderId));
      })
      .catch(err => {
        console.error('Withdraw error:', err);
        setError('Withdraw failed');
      });
  };

  const handleEdit = (order) => {
    axios.post('http://localhost:8080/orders/view-order', order)
      .then(res => {
        localStorage.setItem('editOrder', JSON.stringify({ orders: order, orderProductDetails: res.data }));
        navigate(`/edit-order/${order.orderId}?showStock=false`);
      })
      .catch(err => {
        console.error('View order error:', err);
        setError('Failed to fetch order details to edit');
      });
  };

  return (
    <div style={{ padding: '20px' }}>
      <h2>Pending Orders</h2>
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
          {pending.map((order, i) => (
            <tr key={i}>
              <td>{order.orderId}</td>
              <td>{new Date(order.orderDate).toLocaleString()}</td>
              <td>{order.orderCost.toFixed(2)}</td>
              <td>{order.orderStatus}</td>
              <td>
                <button onClick={() => handleWithdraw(order)}>Withdraw</button>{' '}
                <button onClick={() => handleEdit(order)}>Edit</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default PendingOrders;
