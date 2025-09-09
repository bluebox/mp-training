import React, { useState, useEffect } from 'react';
import StatsCards from '../components/dashboard/StatsCards';
import RecentTransactions from '../components/dashboard/RecentTransactions';
import PopularBooks from '../components/dashboard/PopularBooks';
import { dashboardService } from '../services/dashboardService';

const Dashboard = () => {
  const [dashboardData, setDashboardData] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');

  useEffect(() => {
    fetchDashboardData();
  }, []);

  const fetchDashboardData = async () => {
    try {
      setLoading(true);
      const response = await dashboardService.getDashboardStats();
      setDashboardData(response.data.data);
      setError('');
    } catch (err) {
      console.error('Failed to fetch dashboard data:', err);
      setError('Failed to load dashboard data. Please check if the server is running.');
    } finally {
      setLoading(false);
    }
  };

  if (loading) {
    return (
      <div className="container-fluid py-4">
        <div className="text-center">
          <div className="spinner-border text-primary" role="status">
            <span className="visually-hidden">Loading...</span>
          </div>
          <p className="mt-2">Loading dashboard...</p>
        </div>
      </div>
    );
  }

  return (
    <div className="container-fluid py-4">
      <div className="dashboard-header text-center mb-4">
        <h1 className="text-white">📚 Library Dashboard</h1>
        <p className="text-white">Monitor your library operations</p>
        <button 
          className="btn btn-outline-black text-white"
          onClick={fetchDashboardData}
          disabled={loading}
        >
          {loading ? 'Refreshing...' : '📦 Refresh Data'}
        </button>
      </div>

      {error && (
        <div className="alert alert-danger" role="alert">
          <strong>⚠️ Error:</strong> {error}
        </div>
      )}

      <StatsCards data={dashboardData} />
      
      <div className="row mt-4">
        <div className="col-md-6">
          <RecentTransactions 
            transactions={dashboardData?.recentTransactions || []} 
            loading={loading} 
          />
        </div>
        
        <div className="col-md-6">
          <PopularBooks 
            books={dashboardData?.popularBooks || []} 
            loading={loading} 
          />
        </div>
      </div>

      <div className="card mt-4">
        <div className="card-header">
          <h5>Quick Actions</h5>
        </div>
        <div className="card-body">
          <div className="row">
            <div className="col-md-3 mb-3">
              <a href="/books" className="btn btn-outline-primary w-100">
                Manage Books
              </a>
            </div>
            <div className="col-md-3 mb-3">
              <a href="/members" className="btn btn-outline-success w-100">
                Manage Members
              </a>
            </div>
            <div className="col-md-3 mb-3">
              <a href="/transactions" className="btn btn-outline-info w-100">
                Issue / Returns
              </a>
            </div>
            <div className="col-md-3 mb-3">
              <a href="/reports" className="btn btn-outline-warning w-100">
                View Reports
              </a>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Dashboard;
