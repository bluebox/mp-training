import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import LowStock from './LowStock';
import axios from 'axios';

function LowStockPage() {
  const navigate = useNavigate();
  const [error, setError] = useState(null);

  const handleAddItem = (item) => {
    console.log("item",item)
    // Save selected items to localStorage
    const existing = localStorage.getItem('fromLowStock');
    const parsed = existing ? JSON.parse(existing) : [];
    parsed.push(item);
    localStorage.setItem('fromLowStock', JSON.stringify(parsed));

    // Navigate to create order page
    navigate('/create-order');

    // Send stockId properly wrapped in an object to backend
    axios.post('http://localhost:8080/user-stock/fetch-product-by-name', { stockId: item.stockId })
      .then(res => {
        navigate(`/creating-new-order/${item.stockId}/?showStock=false`);
      })
      .catch(err => {
        console.error('View order error:', err);
        setError('Failed to fetch order details to edit');
      });
  };

  return (
    <div>
      {error && <p style={{ color: 'red' }}>{error}</p>}
      <LowStock onAddItem={handleAddItem} />
    </div>
  );
}

export default LowStockPage;
