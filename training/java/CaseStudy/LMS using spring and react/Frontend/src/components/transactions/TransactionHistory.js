import React, { useState, useEffect } from 'react';
import { transactionService } from '../../services/transactionService';

const TransactionHistory = () => {
  const [transactions, setTransactions] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');
  const [filter, setFilter] = useState('ALL');

  useEffect(() => {
    fetchTransactions();
  }, []);

  const fetchTransactions = async () => {
    try {
      const response = await transactionService.getAllActiveIssues();
      setTransactions(response.data.data || []);
      setLoading(false);
    } catch (err) {
      setError('Failed to fetch transaction history');
      setLoading(false);
    }
  };

  const filteredTransactions = transactions.filter(transaction => {
    switch (filter) {
      case 'ACTIVE':
        return !transaction.returned;
      case 'RETURNED':
        return transaction.returned;
      case 'OVERDUE':
        return transaction.overdue && !transaction.returned;
      default:
        return true;
    }
  });

  if (loading) return <div className="text-center">Loading transaction history...</div>;
  if (error) return <div className="alert alert-danger">{error}</div>;

  return (
    <div className="card">
      <div className="card-header d-flex justify-content-between align-items-center">
        <h5>Transaction History</h5>
        <select
          className="form-select w-auto"
          value={filter}
          onChange={(e) => setFilter(e.target.value)}
        >
          <option value="ALL">All Transactions</option>
          <option value="ACTIVE">Active Issues</option>
          <option value="RETURNED">Returned Books</option>
          <option value="OVERDUE">Overdue Books</option>
        </select>
      </div>
      <div className="card-body">
        {filteredTransactions.length === 0 ? (
          <p className="text-muted text-center">No transactions found.</p>
        ) : (
          <div className="table-responsive">
            <table className="table table-striped">
              <thead>
                <tr>
                  <th>Issue ID</th>
                  <th>Member ID</th>
                  <th>Book ID</th>
                  <th>Issue Date</th>
                  <th>Return Date</th>
                  <th>Actual Return</th>
                  <th>Status</th>
                </tr>
              </thead>
              <tbody>
                {filteredTransactions.map(transaction => (
                  <tr key={transaction.issueId}>
                    <td>#{transaction.issueId}</td>
                    <td>M{transaction.memberId.toString().padStart(4, '0')}</td>
                    <td>{transaction.bookId}</td>
                    <td>{transaction.issueDate}</td>
                    <td>{transaction.returnDate}</td>
                    <td>{transaction.actualReturnDate || '-'}</td>
                    <td>
                      {transaction.returned ? (
                        <span className="badge bg-success">Returned</span>
                      ) : transaction.overdue ? (
                        <span className="badge bg-danger">
                          Overdue ({transaction.daysOverdue} days)
                        </span>
                      ) : (
                        <span className="badge bg-warning text-dark">Active</span>
                      )}
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        )}
        
        <div className="mt-3">
          <small className="text-muted">
            Showing {filteredTransactions.length} of {transactions.length} transactions
          </small>
        </div>
      </div>
    </div>
  );
};

export default TransactionHistory;
