import React, { useState, useEffect } from 'react';

function AddOrder() {
  const [memberId, setMemberId] = useState('');
  const [purchaseDate, setPurchaseDate] = useState('');
  const [items, setItems] = useState([]);
  const [books, setBooks] = useState([]);
  const [members, setMembers] = useState([]);
  const [totalCost, setTotalCost] = useState(0);

  useEffect(() => {
    fetch('http://localhost:8080/books/list')
      .then(res => res.json())
      .then(data => setBooks(data));

    fetch('http://localhost:8080/members/list')
      .then(res => res.json())
      .then(data => setMembers(data));
  }, []);

  const handleAddItem = () => {
    setItems([...items, { bookId: '', quantity: 1, totalCost: 0 }]);
  };

  const handleItemChange = (index, field, value) => {
    const updatedItems = [...items];
    const item = updatedItems[index];

    if (field === 'bookId') {
      const selectedBook = books.find(b => b.bookId === parseInt(value));
      item.bookId = parseInt(value);
      item.quantity = 1;
      item.totalCost = selectedBook ? selectedBook.cost : 0;
    } else if (field === 'quantity') {
      item.quantity = parseInt(value);
      const selectedBook = books.find(b => b.bookId === item.bookId);
      if (selectedBook) {
        item.totalCost = selectedBook.cost * item.quantity;
      }
    }

    setItems(updatedItems);
    calculateTotal(updatedItems);
  };

  const calculateTotal = (itemsList) => {
    const total = itemsList.reduce((sum, item) => sum + (parseFloat(item.totalCost) || 0), 0);
    setTotalCost(total.toFixed(2));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    const payload = {
      order: {
        memberId: parseInt(memberId),
        totalCost: parseFloat(totalCost),
        purchaseDate: purchaseDate
      },
      items: items.map(item => ({
        bookId: item.bookId,
        quantity: item.quantity,
        totalCost: parseFloat(item.totalCost)
      }))
    };

    try {
      const response = await fetch('http://localhost:8080/orders/place', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(payload)
      });

      if (response.ok) {
        const message = await response.text();
        alert(message);
        // Reset form
        setMemberId('');
        setPurchaseDate('');
        setItems([]);
        setTotalCost(0);
      } else {
        const error = await response.text();
        alert(`Error: ${error}`);
      }
    } catch (err) {
      alert('Failed to place order');
      console.error(err);
    }
  };

  return (
    <div style={{ padding: '20px' }}>
      <h2>Place Order</h2>
      <form onSubmit={handleSubmit}>
        <label>Member:</label><br />
        <select value={memberId} onChange={(e) => setMemberId(e.target.value)} required>
          <option value="">-- Select Member --</option>
          {members.map((m) => (
            <option key={m.memberId} value={m.memberId}>
              {m.name} ({m.memberId})
            </option>
          ))}
        </select><br /><br />

        <label>Purchase Date:</label><br />
        <input
          type="date"
          value={purchaseDate}
          onChange={(e) => setPurchaseDate(e.target.value)}
          required
        /><br /><br />

        <h4>Order Items</h4>
        {items.map((item, index) => (
          <div key={index} style={{ marginBottom: '10px' }}>
            <label>Book:</label>
            <select
              value={item.bookId}
              onChange={(e) => handleItemChange(index, 'bookId', e.target.value)}
              required
            >
              <option value="">-- Select Book --</option>
              {books.map((b) => (
                <option key={b.bookId} value={b.bookId}>
                  {b.title} (₹{b.cost})
                </option>
              ))}
            </select>

            <label style={{ marginLeft: '10px' }}>Quantity:</label>
            <input
              type="number"
              min="1"
              value={item.quantity}
              onChange={(e) => handleItemChange(index, 'quantity', e.target.value)}
              required
              style={{ width: '60px' }}
            />

            <label style={{ marginLeft: '10px' }}>Total Cost:</label>
            <input
              type="number"
              value={item.totalCost}
              disabled
              style={{ width: '80px' }}
            />
          </div>
        ))}

        <button type="button" onClick={handleAddItem}>+ Add Item</button><br /><br />

        <strong>Total Order Cost: ₹{totalCost}</strong><br /><br />
        <button type="submit">Place Order</button>
      </form>
    </div>
  );
}

export default AddOrder;
