// src/pages/Home.jsx
import React from 'react';
import { Link } from 'react-router-dom';

function Home() {
  return (
    <div style={{ padding: '30px' }}>
      <h2>Book Store Management</h2>
      <ul>
        <li><Link to="/add-book">Add Book</Link></li>
        <li><Link to="/add-member">Add Member</Link></li>
        <li><Link to="/add-order">Add Order</Link></li>
        <li><Link to="/view-books">View All Books</Link></li>
        <li><Link to="/view-members">View All Members</Link></li>
        <li><Link to="/view-orders">View Order History</Link></li>
      </ul>
    </div>
  );
}

export default Home;
