import { useState } from 'react';
import ShowCustomers from './ShowCustomers';
import ShowVehicle from './Vehicle';
// import ShowClaim from './ShowClaim';
import { Link } from 'react-router-dom';
import ShowUser from './ShowUser';
import ManagePolicy from './ManagePolicy';
import ManageClaim from './ManageClaim';
const AdminDashboard = () => {
  const [activeTab, setActiveTab] = useState(null);
  const adminName = localStorage.getItem("username");

  return (
    <div style={{ marginLeft:"200px",marginRight:"200px",textAlign: 'center'}}>
      <h2>Welcome, {adminName} (Admin)</h2>
      <div style={{ margin: '20px', textAlign:'center'}}>
        <button onClick={() => setActiveTab('customer')}>Manage Customers</button>&nbsp;&nbsp;
        <button onClick={() => setActiveTab('vehicle')}>Manage Vehicles</button>&nbsp;&nbsp;
        <button onClick={() => setActiveTab('policy')}>Manage Policies</button>&nbsp;&nbsp;
        <button onClick={() => setActiveTab('claim')}>Manage Claims</button>&nbsp;&nbsp;
        <button onClick={() => setActiveTab('users')}>Manage Users</button>
      </div>
      <div style={{ marginTop: '20px' }}>
        {activeTab === 'customer' && <ShowCustomers />}
        {activeTab === 'vehicle' && <ShowVehicle />}
        {activeTab === 'policy' && <ManagePolicy />}
        {activeTab === 'claim' && <ManageClaim />}
        {activeTab === 'users' && <ShowUser />}
      </div>
      <br/>
      <Link to="/logout"><button>Logout</button></Link>
    </div>
  );
};

export default AdminDashboard;
