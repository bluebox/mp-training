import React from 'react';
import { Routes, Route, Link, Navigate } from 'react-router-dom';
import LoginPage from './Components/LoginPage';
import PendingOrders from './Components/PendingOrders';
import AllOrdersByUser from './Components/AllOrdersByUser';
import PurchaseCreation from './Components/PurchaseCreation';
import EditOrderModel from './Components/EditOrderModel';
import LowStockPage from './Components/LowStockPage';
import UpdateStatus from './Components/UpdateStatus';
import AllOrders from './Components/AllOrders';
import FullOrderApproval from './Components/FullOrderApproval';
import axios from 'axios';
import CreatingNewOrder from './Components/CreatingNewOrder';

axios.defaults.withCredentials = true;

function App() {
  return (
    <div style={{ padding: '20px' }}>
      <nav style={{ marginBottom: '20px', display: 'flex', gap: '20px' }}>
        <Link to="/">Pending Orders</Link>
        <Link to="/all-orders">All Orders</Link>
        <Link to="/create-order">New Purchase</Link>
        <Link to="/low-stock">Low Stock</Link>
        <Link to="/UpdateStatusByAdmin">UpdateStatusByAdmin</Link>
        <Link to="/AllOrdersAdmin">AllOrdersAdmin</Link>
        <Link to="/login">Login</Link>
      </nav>

      <Routes>
        <Route path="/login" element={<LoginPage />} />
        <Route path="/" element={<PendingOrders />} />
        <Route path="/all-orders" element={<AllOrdersByUser />} />
        <Route path="/create-order" element={<PurchaseCreation />} />
        <Route path="/creating-new-order/:orderId" element={<CreatingNewOrder />} />
        <Route path="/edit-order/:orderId" element={<EditOrderModel />} />
        <Route path="/low-stock" element={<LowStockPage />} />
        <Route path="/UpdateStatusByAdmin" element={<UpdateStatus />} />
        <Route path="/AllOrdersAdmin" element={<AllOrders />} />
        <Route path="/order/:orderId" element={<FullOrderApproval />} />
        <Route path="*" element={<Navigate to="/" />} />
      </Routes>
    </div>
  );
}

export default App;
