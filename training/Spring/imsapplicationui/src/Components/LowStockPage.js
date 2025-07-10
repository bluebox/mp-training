import React from 'react';
import { useNavigate } from 'react-router-dom';
import LowStock from './LowStock';

function LowStockPage() {
  const navigate = useNavigate();

  const handleAddItem = (item) => {
    const existing = localStorage.getItem('fromLowStock');
    const parsed = existing ? JSON.parse(existing) : [];
    parsed.push(item);
    localStorage.setItem('fromLowStock', JSON.stringify(parsed));
    navigate('/create-order');
  };

  return (
    <div>
      <LowStock onAddItem={handleAddItem} />
    </div>
  );
}

export default LowStockPage;
