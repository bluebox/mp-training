import React, { useState } from 'react';
import PendingRequestsTable from '../components/PendingRequestsTable';
import ApprovedProductsTable from '../components/ApprovedProductsTable';
const ProductApprovalDashboard = () => {
     const [refreshCount, setRefreshCount] = useState(0);

  const handleApprovalSuccess = () => {
    setRefreshCount((prev) => prev + 1); 
  };
  return (
    <div className="container-fluid bg-dark text-light min-vh-100 py-4 px-3">
      <h2 className="text-white bg-dark mb-4">Product Approval Dashboard</h2>
      <PendingRequestsTable onApproveSuccess={handleApprovalSuccess} />
      <ApprovedProductsTable  refresh={refreshCount}/>
    </div>
  );
};

export default ProductApprovalDashboard;
