import React, { useEffect, useState } from 'react';
import axios from 'axios';

function LowStock({ onAddItem }) {
  const [items, setItems] = useState([]);
  const [selQty, setSelQty] = useState({});
  const [error, setError] = useState(null);

  useEffect(() => {
    axios.post('http://localhost:8080/low-stock')
      .then(res => {
        if (Array.isArray(res.data)) setItems(res.data);
        else setError('Invalid low-stock data');
      })
      .catch(err => {
        console.error('Low stock error:', err);
        setError('Failed to load low-stock items');
      });
  }, []);

  const handlePartial = (item) => {
    const qty = selQty[item.stockId];
    if (!qty || qty <= 0 || qty > item.maxQuantity) {
      alert(`Enter valid quantity (1–${item.maxQuantity})`);
      return;
    }
    onAddItem({
      supplier: item.supplier,
      product: item.productName,
      productQuantity: qty,
      productCost: 0
    });
  };

  const handleFull = (item) => {
    onAddItem({
      supplier: item.supplier,
      product: item.productName,
      productQuantity: item.maxQuantity,
      productCost: 0
    });
  };

  return (
    <div style={{ padding: 20 }}>
      <h2>Low‑Stock Products</h2>
      {error && <p style={{ color: 'red' }}>{error}</p>}

      <table border={1} cellPadding={6}>
        <thead>
          <tr>
            <th>Product</th>
            <th>Supplier</th>
            <th>Qty</th>
            <th>Min</th>
            <th>Max</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {items.map(item => (
            <tr key={item.stockId}>
              <td>{item.productName}</td>
              <td>{item.supplier}</td>
              <td>{item.quantity}</td>
              <td>{item.minQuantity}</td>
              <td>{item.maxQuantity}</td>
              <td>
                <input
                  type="number"
                  min={1}
                  max={item.maxQuantity}
                  value={selQty[item.stockId] || ''}
                  placeholder="Qty"
                  style={{ width: 60 }}
                  onChange={e =>
                    setSelQty(prev => ({
                      ...prev,
                      [item.stockId]: Number(e.target.value)
                    }))
                  }
                />
                <button onClick={() => handlePartial(item)}>Partial</button>
                <button onClick={() => handleFull(item)}>Full</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default LowStock;
