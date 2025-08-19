import React, { useState } from 'react';
import './MembersBooks.css';

const MembersBooks = () => {
  const [email, setEmail] = useState('');
  const [selectedMember, setSelectedMember] = useState(null);
  const [memberBooks, setMemberBooks] = useState([]);
  const [isLoading, setIsLoading] = useState(false);
  const [message, setMessage] = useState({ type: '', text: '' });

  const handleSearch = async (e) => {
    e.preventDefault();
    if (!email) return;

    try {
      setIsLoading(true);
      // First find member by email
      const memberResponse = await fetch(`http://localhost:8080/searchmember?email=${email}`);
      if (!memberResponse.ok) throw new Error('Member not found');
      
      const member = await memberResponse.json();
      setSelectedMember(member);

      // Then fetch member's books
      const booksResponse = await fetch(`http://localhost:8080/membersbooks?memberid=${member.member_Id}`);
      if (!booksResponse.ok) throw new Error('Failed to fetch member\'s books');
      
      const books = await booksResponse.json();
      setMemberBooks(books);
      setMessage({ type: '', text: '' });
    } catch (error) {
      setMessage({ type: 'error', text: error.message });
      setSelectedMember(null);
      setMemberBooks([]);
    } finally {
      setIsLoading(false);
    }
  };

  const handleClear = () => {
    setEmail('');
    setSelectedMember(null);
    setMemberBooks([]);
    setMessage({ type: '', text: '' });
  };

  return (
    <div className="content-page">
      <h2>Members Books Report</h2>
      
      {message.text && (
        <div className={`message ${message.type}`}>
          {message.text}
        </div>
      )}

      <div className="search-section">
        <form onSubmit={handleSearch} className="email-search-form">
          <div className="form-group">
            <label htmlFor="email">Member Email</label>
            <div className="search-input-group">
              <input
                type="email"
                id="email"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
                placeholder="Enter member email"
                pattern="^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$"
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

      {selectedMember && (
        <div className="member-report-card">
          <div className="member-info">
            <h3>{selectedMember.member_Name}</h3>
            <p>{selectedMember.email}</p>
            <p>Member ID: {selectedMember.member_Id}</p>
          </div>

          <div className="books-section">
            <h4>Books</h4>
            {memberBooks.length > 0 ? (
              <table className="data-table">
                <thead>
                  <tr>
                    <th>Book Title</th>
                    <th>Author</th>
                    <th>Category</th>
                    <th>Status</th>
                  </tr>
                </thead>
                <tbody>
                  {memberBooks.map(book => (
                    <tr key={book.book_Id}>
                      <td>{book.book_Title}</td>
                      <td>{book.book_Author}</td>
                      <td>{book.book_Category}</td>
                      <td>
                        <span className={`status-badge ${book.book_Status === 'I' ? 'issued' : 'returned'}`}>
                          {book.book_Status === 'I' ? 'Issued' : 'Returned'}
                        </span>
                      </td>
                    </tr>
                  ))}
                </tbody>
              </table>
            ) : (
              <p className="no-books">No books found for this member</p>
            )}
          </div>
        </div>
      )}
    </div>
  );
};

export default MembersBooks;
