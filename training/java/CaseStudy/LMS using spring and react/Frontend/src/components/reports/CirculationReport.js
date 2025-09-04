import React, { useState, useEffect } from 'react';
import { reportService } from '../../services/reportService';

const CirculationReport = () => {
  const [reportData, setReportData] = useState(null);
  const [loading, setLoading] = useState(false);
  const [dateRange, setDateRange] = useState({
    startDate: new Date(new Date().setMonth(new Date().getMonth() - 1)).toISOString().split('T')[0],
    endDate: new Date().toISOString().split('T')[0]
  });
  const [error, setError] = useState('');

  const handleGenerateReport = async () => {
    try {
      setLoading(true);
      setError('');
      const response = await reportService.getCirculationReport(dateRange.startDate, dateRange.endDate);
      setReportData(response.data);
    } catch (err) {
      console.error('Report generation error:', err);
      setError('Failed to generate circulation report');
    } finally {
      setLoading(false);
    }
  };

  const handleExportCSV = () => {
    if (reportData && reportData.transactions && reportData.transactions.length > 0) {
      reportService.exportToCSV(reportData.transactions, `circulation_report_${dateRange.startDate}_to_${dateRange.endDate}`);
    } else {
      alert('No data available to export');
    }
  };

  useEffect(() => {
    handleGenerateReport();
  }, []);

  return (
    <div className="card shadow mb-4">
      <div className="card-header py-3">
        <h6 className="m-0 font-weight-bold text-white">📊 Circulation Report</h6>
      </div>
      <div className="card-body">
        <div className="row mb-3">
          <div className="col-md-4">
            <label className="form-label">Start Date</label>
            <input
              type="date"
              className="form-control"
              value={dateRange.startDate}
              onChange={(e) => setDateRange({ ...dateRange, startDate: e.target.value })}
            />
          </div>
          <div className="col-md-4">
            <label className="form-label">End Date</label>
            <input
              type="date"
              className="form-control"
              value={dateRange.endDate}
              onChange={(e) => setDateRange({ ...dateRange, endDate: e.target.value })}
            />
          </div>
          <div className="col-md-4">
            <label className="form-label">&nbsp;</label>
            <div>
              <button
                className="btn btn-primary me-2"
                onClick={handleGenerateReport}
                disabled={loading}
              >
                {loading ? 'Generating...' : 'Generate Report'}
              </button>
              {reportData && (
                <button
                  className="btn btn-success"
                  onClick={handleExportCSV}
                >
                  📥 Export CSV
                </button>
              )}
            </div>
          </div>
        </div>

        {error && <div className="alert alert-danger">{error}</div>}

        {loading && (
          <div className="text-center py-4">
            <div className="spinner-border" role="status">
              <span className="visually-hidden">Loading...</span>
            </div>
            <p className="mt-2">Generating circulation report...</p>
          </div>
        )}

        {reportData && (
          <>
            <div className="row mb-4">
              <div className="col-md-3">
                <div className="card bg-primary text-white">
                  <div className="card-body">
                    <div className="text-center">
                      <div className="h4">{reportData.totalIssued || 0}</div>
                      <div>Books Issued</div>
                    </div>
                  </div>
                </div>
              </div>
              <div className="col-md-3">
                <div className="card bg-success text-white">
                  <div className="card-body">
                    <div className="text-center">
                      <div className="h4">{reportData.totalReturned || 0}</div>
                      <div>Books Returned</div>
                    </div>
                  </div>
                </div>
              </div>
              <div className="col-md-3">
                <div className="card bg-info text-white">
                  <div className="card-body">
                    <div className="text-center">
                      <div className="h4">{reportData.stillActive || 0}</div>
                      <div>Still Active</div>
                    </div>
                  </div>
                </div>
              </div>
              <div className="col-md-3">
                <div className="card bg-warning text-white">
                  <div className="card-body">
                    <div className="text-center">
                      <div className="h4">{reportData.overdueCount || 0}</div>
                      <div>Overdue</div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
{/* 
            {reportData.summary && (
              <div className="alert alert-info">
                <strong>📈 Report Summary:</strong>
                {reportData.summary.dateRange} |
                Average Days Issued: {reportData.summary.averageDaysIssued} days |
                On-Time Return Rate: {reportData.summary.onTimeReturnRate}
              </div>
            )} */}

            {reportData.transactions && reportData.transactions.length > 0 ? (
              <div className="table-responsive">
                <table className="table table-striped table-sm">
                    <tr>
                      <th>Issue ID</th>
                      <th>Member</th>
                      <th>Book</th>
                      <th>Issue Date</th>
                      <th>Expected Return</th>
                      <th>Actual Return</th>
                      <th>Status</th>
                      <th>Days Issued</th>
                    </tr>
                  <tbody>
                    {reportData.transactions.slice(0, 15).map(transaction => (
                      <tr key={transaction.issueId}>
                        <td>
                          <strong>#{transaction.issueId}</strong>
                        </td>
                        <td>
                          <div>
                            <strong>{transaction.memberName || 'Unknown Member'}</strong>
                          </div>
                          <small className="text-muted">
                            {transaction.memberId}
                          </small>
                        </td>
                        <td>
                          <div>
                            <strong>{transaction.bookTitle || 'Unknown Book'}</strong>
                          </div>
                          <small className="text-muted">
                            {transaction.bookId} - {transaction.bookAuthor || 'Unknown Author'}
                          </small>
                        </td>
                        <td>
                          <span className="badge bg-light text-dark">
                            {transaction.issueDate}
                          </span>
                        </td>
                        <td>
                          <span className="badge bg-secondary">
                            {transaction.expectedReturnDate}
                          </span>
                        </td>
                        <td>
                          {transaction.actualReturnDate === 'Not Returned' ? (
                            <span className="badge bg-warning">Not Returned</span>
                          ) : (
                            <span className="badge bg-success">
                              {transaction.actualReturnDate}
                            </span>
                          )}
                        </td>
                        <td>
                          <span className={`badge ${
                            transaction.transactionStatus === 'RETURNED' ? 'bg-success' :
                            transaction.isOverdue === 'YES' ? 'bg-danger' : 'bg-primary'
                          }`}>
                            {transaction.transactionStatus}
                          </span>
                          {transaction.isOverdue === 'YES' && (
                            <div>
                              <small className="text-danger">
                                {transaction.daysOverdue} days overdue
                              </small>
                            </div>
                          )}
                        </td>
                        <td>
                          <span className="badge bg-info">
                            {transaction.totalDaysIssued} days
                          </span>
                          {transaction.returnedOnTime === 'YES' && (
                            <div>
                              <small className="text-success">✓ On time</small>
                            </div>
                          )}
                        </td>
                      </tr>
                    ))}
                  </tbody>
                </table>

                {reportData.transactions.length > 15 && (
                  <div className="text-center mt-3">
                    <small className="text-muted">
                      Showing first 15 of {reportData.transactions.length} transactions.
                      Export CSV for complete data.
                    </small>
                  </div>
                )}
              </div>
            ) : (
              <div className="text-center py-4">
                <div className="h5 text-muted">📚</div>
                <h5 className="text-muted">No Transactions Found</h5>
                <p className="text-muted">
                  No circulation activity found for the selected date range.
                </p>
              </div>
            )}

            <div className="mt-4 text-center">
              <div className="btn-group" role="group">
                <button
                  className="btn btn-outline-primary"
                  onClick={() => handleGenerateReport()}
                >
                  🔄 Refresh Data
                </button>
                <button
                  className="btn btn-outline-info"
                  onClick={() => setDateRange({
                    startDate: new Date(new Date().getFullYear(), 0, 1).toISOString().split('T')[0],
                    endDate: new Date().toISOString().split('T')[0]
                  })}
                >
                  📅 Year to Date
                </button>
                <button
                  className="btn btn-outline-secondary"
                  onClick={() => setDateRange({
                    startDate: new Date(new Date().getFullYear(), new Date().getMonth(), 1).toISOString().split('T')[0],
                    endDate: new Date().toISOString().split('T')[0]
                  })}
                >
                  📆 This Month
                </button>
              </div>
            </div>
          </>
        )}
      </div>
    </div>
  );
};

export default CirculationReport;
