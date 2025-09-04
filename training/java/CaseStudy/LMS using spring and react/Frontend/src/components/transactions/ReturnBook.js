import React, { useState, useEffect } from 'react';
import { memberService } from '../../services/memberService';
import { transactionService } from '../../services/transactionService';

const ReturnBook = ({ onBookReturned }) => {
  const [members, setMembers] = useState([]);
  const [allIssues, setAllIssues] = useState([]);
  const [issuedBooks, setIssuedBooks] = useState([]);
  const [formData, setFormData] = useState({
    memberId: '',
    issueId: '',
    actualReturnDate: ''
  });
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState('');
  const [success, setSuccess] = useState('');

  useEffect(() => {
    const fetchInitialData = async () => {
      try {
        const membersResponse = await memberService.getAllMembers();
        const activeMembers = (membersResponse.data.data || []).filter(m => m.active);
        setMembers(activeMembers);

        const issuesResponse = await transactionService.getAllActiveIssues();
        setAllIssues(issuesResponse.data.data || []);
      } catch (err) {
        setError('Failed to load initial data');
      }
    };

    fetchInitialData();
    
    const today = new Date().toISOString().split('T')[0];
    setFormData(prev => ({ ...prev, actualReturnDate: today }));
  }, []);

  useEffect(() => {
    if (formData.memberId) {
      fetchIssuedBooks(formData.memberId);
    } else {
      setIssuedBooks([]);
    }
  }, [formData.memberId]);

  const fetchIssuedBooks = async (memberId) => {
    try {
      const response = await transactionService.getIssuedBooksByMember(memberId);
      const activeIssues = (response.data.data || []).filter(issue => !issue.returned);
      setIssuedBooks(activeIssues);
    } catch (err) {
      console.error('Failed to fetch issued books:', err);
      setIssuedBooks([]);
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
    
    if (!formData.memberId || !formData.issueId || !formData.actualReturnDate) {
      setError('Please fill in all required fields');
      return;
    }

    setLoading(true);
    setError('');
    setSuccess('');

    try {
      await transactionService.returnBook(
        parseInt(formData.issueId),
        formData.actualReturnDate
      );
      
      setSuccess('Book returned successfully!');
      setFormData({ 
        memberId: '', 
        issueId: '', 
        actualReturnDate: new Date().toISOString().split('T')[0]
      });
      setIssuedBooks([]);
      
      const issuesResponse = await transactionService.getAllActiveIssues();
      setAllIssues(issuesResponse.data.data || []);
      
      if (onBookReturned) onBookReturned();
    } catch (err) {
      console.error('Return error:', err.response?.data);
      setError(err.response?.data?.message || 'Failed to return book');
    } finally {
      setLoading(false);
    }
  };

  const activeMemberIds = allIssues
    .filter(issue => issue.returned === false)
    .map(issue => issue.memberId);

  const membersWithActiveIssues = members.filter(member =>
    activeMemberIds.includes(member.memberId)
  );

  const selectedIssue = issuedBooks.find(issue => issue.issueId.toString() === formData.issueId);

  return (
    <div className="card">
      <div className="card-header">
        <h5>Return Book from Member</h5>
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
                {membersWithActiveIssues.map(member => (
                  <option key={member.memberId} value={member.memberId}>
                    {member.name} (ID: M{member.memberId.toString().padStart(4, '0')})
                  </option>
                ))}
              </select>
              {membersWithActiveIssues.length === 0 && (
                <small className="text-muted">No members currently have active issued books</small>
              )}
            </div>

            <div className="col-md-6 mb-3">
              <label className="form-label">Actual Return Date *</label>
              <input
                type="date"
                className="form-control"
                name="actualReturnDate"
                value={formData.actualReturnDate}
                onChange={handleChange}
                required
              />
            </div>
          </div>

          <div className="mb-3">
            <label className="form-label">Select Issued Book *</label>
            <select
              className="form-control"
              name="issueId"
              value={formData.issueId}
              onChange={handleChange}
              required
              disabled={!formData.memberId}
            >
              <option value="">
                {!formData.memberId ? 'Select member first...' : 'Choose book to return...'}
              </option>
              {issuedBooks.map(issue => (
                <option key={issue.issueId} value={issue.issueId}>
                  {issue.bookTitle || `Book ${issue.bookId}`} ({issue.bookId}) - 
                  Issued: {issue.issueDate}
                  {issue.overdue && ' - OVERDUE'}
                </option>
              ))}
            </select>
            {formData.memberId && issuedBooks.length === 0 && (
              <small className="text-muted">This member has no active issued books</small>
            )}
          </div>

          {selectedIssue && (
            <div className="card bg-light mb-3">
              <div className="card-body">
                <h6>Return Details:</h6>
                <p className="mb-1"><strong>Issue ID:</strong> #{selectedIssue.issueId}</p>
                <p className="mb-1"><strong>Book ID:</strong> {selectedIssue.bookId}</p>
                <p className="mb-1"><strong>Issue Date:</strong> {selectedIssue.issueDate}</p>
                <p className="mb-1"><strong>Expected Return:</strong> {selectedIssue.returnDate}</p>
                {selectedIssue.overdue && (
                  <p className="mb-0 text-danger">
                    <strong>OVERDUE by {selectedIssue.daysOverdue} days</strong>
                  </p>
                )}
              </div>
            </div>
          )}

          <button 
            type="submit" 
            className="btn btn-success"
            disabled={loading || !formData.memberId || !formData.issueId || !formData.actualReturnDate}
          >
            {loading ? 'Processing...' : 'Return Book'}
          </button>
        </form>
      </div>
    </div>
  );
};

export default ReturnBook;
