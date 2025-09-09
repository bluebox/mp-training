import React, { useState, useEffect } from 'react';
import { reportService } from '../../services/reportService';

const OverdueReport = () => {
  const [overdueBooks, setOverdueBooks] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');

  useEffect(() => {
    fetchOverdueReport();
  }, []);

  const fetchOverdueReport = async () => {
    try {
      setLoading(true);
      const response = await reportService.getOverdueReport();
      setOverdueBooks(response.data.data);
      setError('');
    } catch (err) {
      setError('Failed to load overdue report');
    } finally {
      setLoading(false);
    }
  };

  const handleExportCSV = () => {
    reportService.exportToCSV(overdueBooks, 'overdue_books_report');
  };

  if (loading) {
    return (
      <div className="card shadow mb-4">
        <div className="card-body text-center">
          <div className="spinner-border" role="status">
            <span className="visually-hidden">Loading...</span>
          </div>
        </div>
      </div>
    );
  }

  return (
    <div className="card shadow mb-4">
      <div className="card-header py-3 d-flex justify-content-between align-items-center">
        <h6 className="m-0 font-weight-bold text-white">⚠️ Overdue Books Report</h6>
        <div>
          <button 
            className="btn btn-primary btn-sm me-2"
            onClick={fetchOverdueReport}
          >
            🔄 Refresh
          </button>
          <button className="btn btn-success btn-sm" onClick={handleExportCSV}>
            📥 Export CSV
          </button>
        </div>
      </div>
      <div className="card-body">
        {error && <div className="alert alert-danger">{error}</div>}
        
        {overdueBooks.length === 0 ? (
          <div className="text-center py-4">
            <div className="h4 text-success">🎉</div>
            <h5 className="text-success">No Overdue Books!</h5>
            <p className="text-muted">All books have been returned on time.</p>
          </div>
        ) : (
          <>
            <div className="alert alert-warning">
              <strong>📊 Summary:</strong> {overdueBooks.length} book(s) are overdue. 
              Total overdue days: {overdueBooks.reduce((sum, book) => sum + book.daysOverdue, 0)} days.
            </div>

            <div className="table-responsive">
              <table className="table table-striped table-hover">
                <thead>
                  <tr>
                    <th>Issue ID</th>
                    <th>Book Details</th>
                    <th>Member Details</th>
                    <th>Issue Date</th>
                    <th>Due Date</th>
                    <th>Days Overdue</th>
                    <th>Contact</th>
                  </tr>
                </thead>
                <tbody>
                  {overdueBooks
                    .sort((a, b) => b.daysOverdue - a.daysOverdue)
                    .map(book => (
                    <tr key={book.issueId} className="table-warning">
                      <td>
                        <strong>#{book.issueId}</strong>
                      </td>
                      <td>
                        <div>
                          <strong>{book.bookTitle}</strong>
                        </div>
                        <small className="text-muted">
                          {book.bookId} - {book.bookAuthor}
                        </small>
                      </td>
                      <td>
                        <div>
                          <strong>{book.memberName}</strong>
                        </div>
                        <small className="text-muted">
                          M{book.memberId.toString().padStart(4, '0')}
                        </small>
                      </td>
                      <td>{book.issueDate}</td>
                      <td>
                        <span className="text-danger">
                          {book.returnDate}
                        </span>
                      </td>
                      <td>
                        <span className={`badge ${
                          book.daysOverdue > 7 ? 'bg-danger' : 
                          book.daysOverdue > 3 ? 'bg-warning' : 'bg-secondary'
                        }`}>
                          {book.daysOverdue} days
                        </span>
                      </td>
                      <td>
                        <div className="small">
                          <div>📧 {book.memberEmail}</div>
                          {book.memberMobile && (
                            <div>📱 {book.memberMobile}</div>
                          )}
                        </div>
                      </td>
                    </tr>
                  ))}
                </tbody>
              </table>
            </div>

            <div className="mt-3">
              <div className="row">
                <div className="col-md-6">
                  <small className="text-muted">
                    Most Overdue: {Math.max(...overdueBooks.map(book => book.daysOverdue))} days |
                    Average Overdue: {Math.round(overdueBooks.reduce((sum, book) => sum + book.daysOverdue, 0) / overdueBooks.length)} days
                  </small>
                </div>
                <div className="col-md-6 text-end">
                  <button className="btn btn-outline-primary btn-sm me-2">
                    📧 Send Reminder Emails
                  </button>
                  <button className="btn btn-outline-warning btn-sm">
                    📋 Generate Notice List
                  </button>
                </div>
              </div>
            </div>
          </>
        )}
      </div>
    </div>
  );
};

export default OverdueReport;
