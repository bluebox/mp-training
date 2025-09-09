import React, { useState, useEffect } from 'react';
import { reportService } from '../../services/reportService';

const MemberActivityReport = () => {
  const [memberStats, setMemberStats] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');

  useEffect(() => {
    fetchMemberActivityReport();
  }, []);

  const fetchMemberActivityReport = async () => {
    try {
      setLoading(true);
      const response = await reportService.getMemberActivityReport();
      setMemberStats(response.data.data);
      setError('');
    } catch (err) {
      setError('Failed to load member activity report');
    } finally {
      setLoading(false);
    }
  };

  const handleExportCSV = () => {
    reportService.exportToCSV(memberStats, 'member_activity_report');
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
        <h6 className="m-0 font-weight-bold text-white">👥 Member Activity Report</h6>
        <button className="btn btn-success btn-sm" onClick={handleExportCSV}>
          📥 Export CSV
        </button>
      </div>
      <div className="card-body">
        {error && <div className="alert alert-danger">{error}</div>}
        
        <div className="table-responsive">
          <table className="table table-striped table-hover">
            <thead>
              <tr>
                <th>Member ID</th>
                <th>Name</th>
                <th>Email</th>
                <th>Total Issued</th>
                <th>Currently Issued</th>
                <th>Books Returned</th>
                <th>Overdue Books</th>
                <th>Status</th>
              </tr>
            </thead>
            <tbody>
              {memberStats.map(member => (
                <tr key={member.memberId}>
                  <td>M{member.memberId.toString().padStart(4, '0')}</td>
                  <td>{member.name}</td>
                  <td>{member.email}</td>
                  <td>
                    <span className="badge bg-primary">{member.totalBooksIssued}</span>
                  </td>
                  <td>
                    <span className="badge bg-info">{member.currentlyIssued}</span>
                  </td>
                  <td>
                    <span className="badge bg-success">{member.booksReturned}</span>
                  </td>
                  <td>
                    {member.overdueBooks > 0 ? (
                      <span className="badge bg-danger">{member.overdueBooks}</span>
                    ) : (
                      <span className="badge bg-light text-dark">0</span>
                    )}
                  </td>
                  <td>
                    <span className={`badge ${member.status === 'ACTIVE' ? 'bg-success' : 'bg-secondary'}`}>
                      {member.status}
                    </span>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>

        <div className="mt-3">
          <small className="text-muted">
            Total Members: {memberStats.length} | 
            Active Borrowers: {memberStats.filter(m => m.totalBooksIssued > 0).length} |
            Members with Overdue Books: {memberStats.filter(m => m.overdueBooks > 0).length}
          </small>
        </div>
      </div>
    </div>
  );
};

export default MemberActivityReport;
