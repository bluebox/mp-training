import React from 'react';
import DashboardLayout from '../layouts/DashboardLayout';

function HomePage() {
  return (
    <DashboardLayout>
      <div className="home-content">
        <h1>Welcome to User Management System</h1>
        <p>Use sidebar for different features</p>
        <div className="button-row">
          <button onClick={() => window.location.href = '/register'}>Create User</button>
          <button onClick={() => window.location.href = '/requests'}>View User Requests</button>
          <button onClick={() => window.location.href = '/mainUsers'}>View Main Users</button>
          <button onClick={() => window.location.href = '/viewRoles'}>View Roles</button>
        </div>
      </div>
    </DashboardLayout>
  );
}

export default HomePage;
