import React, { useState } from 'react';
import IssueBook from '../components/transactions/IssueBook';
import ReturnBook from '../components/transactions/ReturnBook';
import TransactionHistory from '../components/transactions/TransactionHistory';

const Transactions = () => {
  const [activeTab, setActiveTab] = useState('issue');
  const [refreshKey, setRefreshKey] = useState(0);

  const handleTransactionComplete = () => {
    setRefreshKey(prev => prev + 1);
  };

  return (
    <div className="container mt-4">
      <h1>Book Transactions</h1>
      
      <ul className="nav nav-tabs mb-4">
        <li className="nav-item">
          <button 
            className={`nav-link ${activeTab === 'issue' ? 'active' : ''}`}
            onClick={() => setActiveTab('issue')}
          >
            Issue Book
          </button>
        </li>
        <li className="nav-item">
          <button 
            className={`nav-link ${activeTab === 'return' ? 'active' : ''}`}
            onClick={() => setActiveTab('return')}
          >
            Return Book
          </button>
        </li>
        <li className="nav-item">
          <button 
            className={`nav-link ${activeTab === 'history' ? 'active' : ''}`}
            onClick={() => setActiveTab('history')}
          >
            Transaction History
          </button>
        </li>
      </ul>

      <div className="tab-content">
        {activeTab === 'issue' && (
          <IssueBook onBookIssued={handleTransactionComplete} />
        )}
        
        {activeTab === 'return' && (
          <ReturnBook onBookReturned={handleTransactionComplete} />
        )}
        
        {activeTab === 'history' && (
          <TransactionHistory key={refreshKey} />
        )}
      </div>
    </div>
  );
};

export default Transactions;
