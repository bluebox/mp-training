import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import LowStock from './LowStock';
import axios from 'axios';

function LowStockPage() {
  const navigate = useNavigate();
  const [error, setError] = useState(null);

  const handleAddItem = (item) => {
    // const existing = localStorage.getItem('fromLowStock');
    // const parsed = existing ? JSON.parse(existing) : [];
    // parsed.push(item);
    // localStorage.setItem('fromLowStock', JSON.stringify(parsed));
    // navigate('/create-order');

    axios.post('http://localhost:8080/user-stock/fetch-product-by-name', item)
      .then(res => {
        navigate(`/creating-new-order/${0}/?showStock=false`);
      })
      .catch(err => {
        console.error('View order error:', err);
        setError('Failed to fetch order details to edit');
      });
  };

  return (
    <div>
      <LowStock onAddItem={handleAddItem} />
    </div>
  );
}

export default LowStockPage;
