import React, { useState } from 'react';
import CirculationReport from '../components/reports/CirculationReport';
import MemberActivityReport from '../components/reports/MemberActivityReport';
import BookPopularityReport from '../components/reports/BookPopularityReport';
import OverdueReport from '../components/reports/OverdueReport';

const Reports = () => {
  const [activeReport, setActiveReport] = useState('circulation');

  const reports = [
    { id: 'circulation', name: '📊 Circulation Report', icon: '📊' },
    { id: 'members', name: '👥 Member Activity', icon: '👥' },
    { id: 'books', name: '📚 Book Popularity', icon: '📚' },
    { id: 'overdue', name: '⚠️ Overdue Books', icon: '⚠️' }
  ];

  const renderActiveReport = () => {
    switch(activeReport) {
      case 'circulation':
        return <CirculationReport />;
      case 'members':
        return <MemberActivityReport />;
      case 'books':
        return <BookPopularityReport />;
      case 'overdue':
        return <OverdueReport />;
      default:
        return <CirculationReport />;
    }
  };

  return (
    <div className="container-fluid mt-4">
      <div className="d-sm-flex align-items-center justify-content-between mb-4">
        <h1 className="h3 mb-0 text-white">📊 Library Reports</h1>
        
      </div>

      <ul className="nav nav-tabs mb-4">
        {reports.map(report => (
          <li key={report.id} className="nav-item">
            <button 
              className={`nav-link ${activeReport === report.id ? 'active' : ''}`}
              onClick={() => setActiveReport(report.id)}
            >
              {report.name}
            </button>
          </li>
        ))}
      </ul>

      <div className="tab-content">
        {renderActiveReport()}
      </div>

      <div className="row mt-4">
        <div className="col-12">
          <div className="card shadow">
            <div className="card-body">
              <div className="row text-center">
                <div className="col-md-3">
                  <div className="text-primary">
                    <div className="h4">📊</div>
                    <div>Circulation Analysis</div>
                    <small className="text-muted">Track book borrowing patterns</small>
                  </div>
                </div>
                <div className="col-md-3">
                  <div className="text-success">
                    <div className="h4">👥</div>
                    <div>Member Insights</div>
                    <small className="text-muted">Monitor user engagement</small>
                  </div>
                </div>
                <div className="col-md-3">
                  <div className="text-info">
                    <div className="h4">📚</div>
                    <div>Collection Analytics</div>
                    <small className="text-muted">Optimize book inventory</small>
                  </div>
                </div>
                <div className="col-md-3">
                  <div className="text-warning">
                    <div className="h4">⚠️</div>
                    <div>Overdue Management</div>
                    <small className="text-muted">Reduce late returns</small>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Reports;
