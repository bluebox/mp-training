import React, { useState, useEffect } from 'react';
import { memberService } from '../../services/memberService';
import { bookService } from '../../services/bookService';
import { transactionService } from '../../services/transactionService';

const IssueBook = ({ onBookIssued }) => {
  const [members, setMembers] = useState([]);
  const [books, setBooks] = useState([]);
  const [formData, setFormData] = useState({
    memberId: '',
    bookId: '',
    returnDate: ''
  });
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState('');
  const [success, setSuccess] = useState('');

  useEffect(() => {
    fetchData();
    const returnDate = new Date();
    returnDate.setDate(returnDate.getDate() + 13);
    setFormData(prev => ({
      ...prev,
      returnDate: returnDate.toISOString().split('T')[0]
    }));
  }, []);

  const fetchData = async () => {
    try {
      const membersRes = await memberService.getAllMembers();
      const activeMembers = (membersRes.data.data || []).filter(m => m.active);
      setMembers(activeMembers);
      
      const booksRes = await bookService.getAllBooks();
      const availableBooks = (booksRes.data.data || []).filter(b => b.available);
      setBooks(availableBooks);
    } catch (err) {
      setError('Failed to load data');
    }
  };

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    
    if (!formData.memberId || !formData.bookId) {
      setError('Please select both member and book');
      return;
    }

    setLoading(true);
    setError('');
    setSuccess('');

    try {
      const issueData = {
        memberId: parseInt(formData.memberId),
        bookId: formData.bookId,
        returnDate: formData.returnDate
      };

      await transactionService.issueBook(issueData);
      setSuccess('Book issued successfully!');
      setFormData({
        memberId: '',
        bookId: '',
        returnDate: formData.returnDate
      });
      
      await fetchData();
      
      if (onBookIssued) onBookIssued();
    } catch (err) {
      console.error('Issue error:', err.response?.data);
      setError(err.response?.data?.message || 'Failed to issue book');
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="card">
      <div className="card-header">
        <h5>Issue Book to Member</h5>
      </div>
      <div className="card-body">
        {error && <div className="alert alert-danger">{error}</div>}
        {success && <div className="alert alert-success">{success}</div>}
        
        <form onSubmit={handleSubmit}>
          <div className="row">
            <div className="col-md-6 mb-3">
              <label className="form-label">Select Member *</label>
              <select
                className="form-control"
                name="memberId"
                value={formData.memberId}
                onChange={handleChange}
                required
              >
                <option value="">Choose Member...</option>
                {members.map(member => (
                  <option key={member.memberId} value={member.memberId}>
                    {member.name} (ID: M{member.memberId.toString().padStart(4, '0')})
                  </option>
                ))}
              </select>
            </div>
            
            <div className="col-md-6 mb-3">
              <label className="form-label">Select Book *</label>
              <select
                className="form-control"
                name="bookId"
                value={formData.bookId}
                onChange={handleChange}
                required
              >
                <option value="">Choose Book...</option>
                {books.map(book => (
                  <option key={book.bookId} value={book.bookId}>
                    {book.title} ({book.bookId}) - {book.author}
                  </option>
                ))}
              </select>
            </div>
          </div>

          <div className="mb-3">
            <label className="form-label">Expected Return Date</label>
            <input
              type="date"
              className="form-control"
              name="returnDate"
              value={formData.returnDate}
              onChange={handleChange}
              required
            />
          </div>

          <button 
            type="submit" 
            className="btn btn-primary"
            disabled={loading || members.length === 0 || books.length === 0}
          >
            {loading ? 'Issuing...' : 'Issue Book'}
          </button>
          
          {members.length === 0 && (
            <small className="text-muted ms-2">No active members available</small>
          )}
          {books.length === 0 && (
            <small className="text-muted ms-2">No available books</small>
          )}
        </form>
      </div>
    </div>
  );
};

export default IssueBook;
