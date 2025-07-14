import { useState } from 'react';
// import ShowClaim from './ShowClaim';
import { BrowserRouter, Link,Route,Routes,Router } from 'react-router-dom';
import ShowCustomers from "./ShowCustomers";

import ShowVehicle from "./Vehicle";

import ShowUser from "./ShowUser";

import ManagePolicy from "./ManagePolicy";

import ManageClaim from "./ManageClaim";

const AdminDashboard = () => {
  const [activeTab, setActiveTab] = useState(null);
  const adminName = localStorage.getItem("username");

  return (
    <div style={{ textAlign: 'center', marginTop: '30px' }}>
      <h2>Welcome, {adminName} (Admin)</h2>
      <div style={{ margin: '20px' }}>
        <Link to="/customer/show"><button onClick={() => setActiveTab('vehicle')}>Manage Customers</button></Link>
        <Link to="/vehicle/show"></Link><button onClick={() => setActiveTab('vehicle')}>Manage Vehicles</button>
        <Link to="/policy/manage"></Link><button onClick={() => setActiveTab('policy')}>Manage Policies</button>
        <Link to="/claim/manage"></Link><button onClick={() => setActiveTab('claim')}>Manage Claims</button>
        <Link to="/users/show"></Link><button onClick={() => setActiveTab('users')}>Manage Users</button>
      </div>

      <div style={{ marginTop: '20px' }}>
        {activeTab === 'customer' && <ShowCustomers />}
        {activeTab === 'vehicle' && <ShowVehicle />}
        {activeTab === 'policy' && <ManagePolicy />}
        {activeTab === 'claim' && <ManageClaim />}
        {activeTab === 'users' && <ShowUser />}
      </div>
    </div>
  );
};

export default AdminDashboard;
