import React from 'react';
import Sidebar from '../components/Sidebar';

function DashboardLayout({ children }) {
  return (
    <div className="dashboard-container">
      <Sidebar />
      <div className="dashboard-content">
        {children}
      </div>
    </div>
  );
}

export default DashboardLayout;
