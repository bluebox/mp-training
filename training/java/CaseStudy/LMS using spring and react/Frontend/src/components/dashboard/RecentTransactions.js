import React from 'react';

const RecentTransactions = ({ transactions, loading }) => {
  if (loading) {
    return (
      <div className="card">
        <div className="card-header">
          <h6 className="m-0">📋 Recent Transactions</h6>
        </div>
        <div className="card-body text-center">
          <div className="spinner-border spinner-border-sm" role="status">
            <span className="visually-hidden">Loading...</span>
          </div>
        </div>
      </div>
    );
  }

  return (
    <div className="card">
      <div className="card-header">
        <h6 className="m-0">📋 Recent Transactions</h6>
      </div>
      <div className="card-body">
        {transactions && transactions.length > 0 ? (
          <div className="list-group list-group-flush">
            {transactions.map((transaction, index) => (
              <div key={index} className="list-group-item d-flex justify-content-between align-items-start">
                <div>
                  <div className="fw-bold">{transaction.bookTitle || `Book ID: ${transaction.bookId}`}</div>
                  <small className="text-muted">{transaction.memberName || `Member ID: ${transaction.memberId}`}</small>
                </div>
                <small className="text-muted">
                  {transaction.issueDate || transaction.returnDate || 'Recent'}
                </small>
              </div>
            ))}
          </div>
        ) : (
          <p className="text-muted mb-0">No recent transactions</p>
        )}
      </div>
    </div>
  );
};

export default RecentTransactions;
