import React, { useState, useEffect } from 'react';
import axios from 'axios';
import jsPDF from 'jspdf';
import './UserDashboard.css';

export default function UserDashboard() {
  const [customer, setCustomer] = useState({});
  const [customerId, setCustomerId] = useState(null);

  const [vehicles, setVehicles] = useState([]);
  const [selectedVehicleId, setSelectedVehicleId] = useState(null);

  const [policies, setPolicies] = useState([]);
  const [claims, setClaims] = useState([]);

  const [newRegNum, setNewRegNum] = useState('');
  const [policyForm, setPolicyForm] = useState({ policyTerm: '', policyType: 'Silver', vehicleId: '', approvedBy: '' });
  const [claimForm, setClaimForm] = useState({ reqAmount: '', damageType: '', policyId: '' });

  const [customerForm, setCustomerForm] = useState({});
  const [newPassword, setNewPassword] = useState('');
  const [showProfile, setShowProfile] = useState(false);

  const username = localStorage.getItem("username");

  useEffect(() => {
    fetchCustomerId();
    fetchPoliciesByUser();
    fetchClaimsByUser();
  }, []);

  const fetchCustomerId = () => {
    axios.get(`http://localhost:8000/user/show?username=${username}`, { withCredentials: true })
      .then(res => {
        setCustomerId(res.data.customerId);
        fetchCustomerDetails(res.data.customerId);
        fetchVehicles(res.data.customerId);
      })
      .catch(err => console.error(err));
  };

  const fetchCustomerDetails = (id) => {
    axios.get(`http://localhost:8000/customer/show?customerId=${id}`, { withCredentials: true })
      .then(res => {
        setCustomer(res.data);
        setCustomerForm(res.data);
      })
      .catch(err => console.error(err));
  };

  const fetchVehicles = (id) => {
    axios.get(`http://localhost:8000/vehicle/showByCustomerId?customerId=${id}`, { withCredentials: true })
      .then(res => setVehicles(res.data))
      .catch(err => console.error(err));
  };

  const fetchPoliciesByUser = () => {
    axios.get(`http://localhost:8000/policy/showByUser?username=${username}`, { withCredentials: true })
      .then(res => setPolicies(res.data))
      .catch(err => console.error(err));
  };

  const fetchClaimsByUser = () => {
    axios.get(`http://localhost:8000/claim/claimByUser?username=${username}`, { withCredentials: true })
      .then(res => setClaims(res.data))
      .catch(err => console.error(err));
  };

  const updateVehicleReg = () => {
    axios.put(`http://localhost:8000/vehicle/update?vehicleId=${selectedVehicleId}&regNum=${newRegNum}&updatedBy=${username}`,{}, { withCredentials: true })
      .then((response) => {
        alert(response.data);
        fetchVehicles(customerId);
      })
      .catch(err => console.error(err));
  };

  const submitPolicyRequest = () => {
    axios.post(`http://localhost:8000/policy/add`, {
      vehicleId: policyForm.vehicleId,
      policyTerm: policyForm.policyTerm,
      policyType: policyForm.policyType,
      approvedBy: username
    }, { withCredentials: true })
      .then((response) => {
        alert(response.data);
        fetchPoliciesByUser();
      })
      .catch(err => console.error(err));
  };

  const submitClaimRequest = () => {
    axios.post(`http://localhost:8000/claim/add`, {
      policyId: claimForm.policyId,
      reqAmount: claimForm.reqAmount,
      damageType: claimForm.damageType,
      approvedBy: username
    }, { withCredentials: true })
      .then((response) => {
        alert(response.data);
        fetchClaimsByUser();
      })
      .catch(err => console.error(err));
  };

  const payPolicyDue = (policyId) => {
    axios.put(`http://localhost:8000/policy/payDue?policyId=${policyId}`, {}, { withCredentials: true })
      .then((response) => {
        alert(response.data);
        fetchPoliciesByUser();
      })
      .catch(err => console.error(err));
  };

  const updateCustomerDetails = () => {
    axios.put(`http://localhost:8000/customer/update`, { ...customerForm, customerUpdatedBy: username }, { withCredentials: true })
      .then((response) => {
        alert(response.data);
        fetchCustomerDetails(customerId);
      })
      .catch(err => console.error(err));
  };

  const updatePassword = () => {
    axios.put(`http://localhost:8000/user/updatePassword?username=${username}&password=${newPassword}`, {}, { withCredentials: true })
      .then((response) => {
        alert(response.data);
        setNewPassword('');
      })
      .catch(err => console.error(err));
  };

  const generatePolicyReport = (policyId) => {
  axios.get(`http://localhost:8000/policy/report?policyId=${policyId}`, { withCredentials: true })
    .then(res => {
      const doc = new jsPDF();
      const [customer, vehicle, policy] = res.data;
      console.log(res.data);
      doc.setFontSize(18);
      doc.text(`Policy Report - Policy ID: ${policy.policyId}`, 10, 20);

      doc.setFontSize(14);
      doc.text("Customer Details:", 10, 40);
      doc.setFontSize(12);
      doc.text(`Name: ${customer.name}`, 10, 50);
      doc.text(`Contact: ${customer.contact}`, 10, 60);
      doc.text(`Email: ${customer.email}`, 10, 70);

      doc.setFontSize(14);
      doc.text("Vehicle Details:", 10, 90);
      doc.setFontSize(12);
      doc.text(`Reg Number: ${vehicle.regNum}`, 10, 100);
      doc.text(`Type: ${vehicle.vehicleModel}`, 10, 110);

      doc.setFontSize(14);
      doc.text("Policy Details:", 10, 130);
      doc.setFontSize(12);
      doc.text(`Type: ${policy.policyType}`, 10, 140);
      doc.text(`Start Date: ${policy.startDate}`, 10, 150);
      doc.text(`End Date: ${policy.endDate}`, 10, 160);
      doc.text(`Status: ${policy.policyStatus}`, 10, 170);

      doc.save(`Policy_Report_${policyId}.pdf`);
    })
    .catch(err => {
      console.error(err);
      alert("Failed to fetch policy report");
    });
};

const generateClaimReport = (claimId) => {
  axios.get(`http://localhost:8000/claim/claimReports?claimId=${claimId}`, { withCredentials: true })
    .then(res => {
      const doc = new jsPDF();
      const [customer, vehicle, policy, claim] = res.data;

      doc.setFontSize(18);
      doc.text(`Claim Report - Claim ID: ${claim.claimId}`, 10, 20);

      doc.setFontSize(14);
      doc.text("Customer Details:", 10, 40);
      doc.setFontSize(12);
      doc.text(`Name: ${customer.name}`, 10, 50);
      doc.text(`Contact: ${customer.contact}`, 10, 60);
      doc.text(`Email: ${customer.email}`, 10, 70);

      doc.setFontSize(14);
      doc.text("Vehicle Details:", 10, 90);
      doc.setFontSize(12);
      doc.text(`Reg Number: ${vehicle.regNum}`, 10, 100);
      doc.text(`Type: ${vehicle.vehicleModel}`, 10, 110);

      doc.setFontSize(14);
      doc.text("Policy Details:", 10, 130);
      doc.setFontSize(12);
      doc.text(`Type: ${policy.policyType}`, 10, 140);
      doc.text(`Start Date: ${policy.startDate}`, 10, 150);
      doc.text(`End Date: ${policy.endDate}`, 10, 160);
      doc.text(`Status: ${policy.policyStatus}`, 10, 170);

      doc.setFontSize(14);
      doc.text("Claim Details:", 10, 190);
      doc.setFontSize(12);
      doc.text(`Requested Amount: ${claim.reqAmount}`, 10, 200);
      doc.text(`Damage Type: ${claim.damageType}`, 10, 210);
      doc.text(`Status: ${claim.claimStatus}`, 10, 220);
      doc.text(`Date: ${claim.claimDate}`, 10, 230);

      doc.save(`Claim_Report_${claimId}.pdf`);
    })
    .catch(err => {
      console.error(err);
      alert("Failed to fetch claim report");
    });
};


  return (
    <div className="dashboard-container">
      <div className="dashboard-header">
        <h2>User Dashboard</h2>
        <h2>Welcome {username}</h2>
        <button onClick={() => setShowProfile(!showProfile)}>
          {showProfile ? "Hide Profile" : "Profile"}
        </button>
      </div>

      {showProfile && (
        <div className="profile-section">
          <h3>Customer Info</h3>
          <table className="dashboard-table">
            <tbody>
              <tr><td>ID</td><td>{customer.customerId}</td></tr>
              <tr><td>Name</td><td><input value={customerForm.name || ''} onChange={e => setCustomerForm({ ...customerForm, name: e.target.value })} /></td></tr>
              <tr><td>Age</td><td><input type="number" value={customerForm.age || ''} onChange={e => setCustomerForm({ ...customerForm, age: e.target.value })} /></td></tr>
              <tr><td>Gender</td><td><input value={customerForm.gender || ''} onChange={e => setCustomerForm({ ...customerForm, gender: e.target.value })} /></td></tr>
              <tr><td>Phone</td><td><input value={customerForm.contact || ''} onChange={e => setCustomerForm({ ...customerForm, contact: e.target.value })} /></td></tr>
              <tr><td>Email</td><td><input value={customerForm.email || ''} onChange={e => setCustomerForm({ ...customerForm, email: e.target.value })} /></td></tr>
              <tr><td>Address</td><td><input value={customerForm.address || ''} onChange={e => setCustomerForm({ ...customerForm, address: e.target.value })} /></td></tr>
              <tr><td>Occupation</td><td><input value={customerForm.occupation || ''} onChange={e => setCustomerForm({ ...customerForm, occupation: e.target.value })} /></td></tr>
            </tbody>
          </table>
          <button onClick={updateCustomerDetails}>Update Customer Info</button>

          <div style={{ marginTop: '15px' }}>
            <h4>Update Password</h4>
            <input type="password" placeholder="New Password" value={newPassword} onChange={e => setNewPassword(e.target.value)} />
            <button onClick={updatePassword}>Update Password</button>
          </div>
        </div>
      )}

      <div className="dashboard-flex">
        <div className="dashboard-card">
          <h3>Your Vehicles</h3>
          <select value={selectedVehicleId || ''} onChange={e => setSelectedVehicleId(e.target.value)}>
            <option value="">Select Vehicle to Update Reg</option>
            {vehicles.map(v => (
              <option key={v.vehicleId} value={v.vehicleId}>{v.vehicleId}</option>
            ))}
          </select>
          <input type="text" placeholder="New Reg Number" value={newRegNum} onChange={e => setNewRegNum(e.target.value)} />
          <button onClick={updateVehicleReg}>Update Reg Number</button>
        </div>

        <div className="dashboard-card">
          <h4>Request New Policy</h4>
          <select value={policyForm.vehicleId} onChange={e => setPolicyForm({ ...policyForm, vehicleId: e.target.value })}>
            <option value="">Select Vehicle for Policy</option>
            {vehicles.map(v => (
              <option key={v.vehicleId} value={v.vehicleId}>{v.vehicleId}</option>
            ))}
          </select>
          <input type="number" placeholder="Policy Term (years)" value={policyForm.policyTerm} onChange={e => setPolicyForm({ ...policyForm, policyTerm: e.target.value })} />
          <select value={policyForm.policyType} onChange={e => setPolicyForm({ ...policyForm, policyType: e.target.value })}>
            <option value="Silver">Silver</option>
            <option value="Gold">Gold</option>
            <option value="Platinum">Platinum</option>
          </select>
          <button onClick={submitPolicyRequest}>Request Policy</button>
        </div>
      </div>

      <div className="dashboard-section">
        <h3>Your Policies</h3>
        <ul>
          { policies!==null?policies.map(p => (
            <li key={p.policyId}>
              #{p.policyId} - {p.policyType} - Vehicle ID: {p.vehicleId} - Status: {p.policyStatus}
              <button onClick={() => payPolicyDue(p.policyId)} style={{ marginLeft: '10px' }}>Pay Yearly Premium</button>
              <button onClick={() => generatePolicyReport(p.policyId)}>Download Report</button>

            </li>
          )):""}
        </ul>
      </div>

      <div className="dashboard-section">
        <h4>Request New Claim</h4>
        <select value={claimForm.policyId} onChange={e => setClaimForm({ ...claimForm, policyId: e.target.value })}>
          <option value="">Select Policy for Claim</option>
          {policies!==null?policies.map(p => (
            <option key={p.policyId} value={p.policyId}>
              PolicyId #{p.policyId} of {p.policyType} for vehicleId {p.vehicleId}
            </option>
          )):""}
        </select>
        <input type="number" placeholder="Request Amount" value={claimForm.reqAmount} onChange={e => setClaimForm({ ...claimForm, reqAmount: e.target.value })} />
        <input type="text" placeholder="Damage Type" value={claimForm.damageType} onChange={e => setClaimForm({ ...claimForm, damageType: e.target.value })} />
        <button onClick={submitClaimRequest}>Request Claim</button>
      </div>

      <div className="dashboard-section">
        <h3>Your Claims</h3>
        <ul>
          {claims!==null?claims.map(c => (
            <li key={c.claimId}>
              ClaimId #{c.claimId} on Policy #{c.policyId} --- Status: {c.claimStatus}
              <button onClick={() => generateClaimReport(c.claimId)}>Download Report</button>

            </li>
          )):""}
        </ul>
      </div>
    </div>
  );
}
