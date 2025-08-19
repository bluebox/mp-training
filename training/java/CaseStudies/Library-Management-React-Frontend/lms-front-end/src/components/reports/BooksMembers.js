import React, { useState } from 'react';
import './BooksMembers.css';

const BooksMembers = () => {
  const [bookId, setBookId] = useState('');
  const [selectedBook, setSelectedBook] = useState(null);
  const [borrowers, setBorrowers] = useState([]);
  const [isLoading, setIsLoading] = useState(false);
  const [message, setMessage] = useState({ type: '', text: '' });

  const handleSearch = async (e) => {
    e.preventDefault();
    if (!bookId) return;

    try {
      setIsLoading(true);
      const borrowersResponse = await fetch(`http://localhost:8080/booksmembers?bookid=${bookId}`);
      if (!borrowersResponse.ok) throw new Error('Failed to fetch borrowers');
      
      const members = await borrowersResponse.json();
      if (members.length === 0) {
        throw new Error('No borrowing history found for this book');
      }
      setBorrowers(members);
      setSelectedBook({ book_Id: bookId });
      setMessage({ type: '', text: '' });
    } catch (error) {
      setMessage({ type: 'error', text: error.message });
      setSelectedBook(null);
      setBorrowers([]);
    } finally {
      setIsLoading(false);
    }
  };

  const handleClear = () => {
    setBookId('');
    setSelectedBook(null);
    setBorrowers([]);
    setMessage({ type: '', text: '' });
  };

  return (
    <div className="content-page">
      <h2>Books Members Report</h2>
      
      {message.text && (
        <div className={`message ${message.type}`}>
          {message.text}
        </div>
      )}

      <div className="search-section">
        <form onSubmit={handleSearch} className="book-search-form">
          <div className="form-group">
            <label htmlFor="bookId">Book ID</label>
            <div className="search-input-group">
              <input
                type="number"
                id="bookId"
                value={bookId}
                onChange={(e) => setBookId(e.target.value)}
                placeholder="Enter book ID"
                required
              />
              <button type="submit" disabled={isLoading}>
                {isLoading ? 'Searching...' : 'Search'}
              </button>
              <button type="button" onClick={handleClear} className="clear-btn">
                Clear
              </button>
            </div>
          </div>
        </form>
      </div>

      {selectedBook && (
        <div className="book-report-card">
          <div className="book-info">
            <h3>Book ID: {selectedBook.book_Id}</h3>
          </div>

          <div className="members-section">
            <h4>Members who borrowed this book</h4>
            <table className="data-table">
              <thead>
                <tr>
                  <th>Member Name</th>
                  <th>Email</th>
                  <th>Mobile</th>
                  <th>Gender</th>
                </tr>
              </thead>
              <tbody>
                {borrowers.map(member => (
                  <tr key={member.member_Id}>
                    <td>{member.member_Name}</td>
                    <td>{member.email}</td>
                    <td>{member.mobile_No}</td>
                    <td>{member.gender}</td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        </div>
      )}
    </div>
  );
};

export default BooksMembers;
