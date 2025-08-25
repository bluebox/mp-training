import React, { useEffect, useState } from 'react';
import { useCookies } from 'react-cookie';
import axios from 'axios';
import './App.css';

function IssueInsuranceForm() {
  const [cookies] = useCookies(['userData']);
  const [userInsurances, setUserInsurances] = useState([]);
  const [showUpgradeForm, setShowUpgradeForm] = useState(false);
  const [showClaimForm, setShowClaimForm] = useState(false);
  const [upgradeData, setUpgradeData] = useState({ issueInsuranceId: '', insuranceId: '' });
  const [claimData, setClaimData] = useState({ issueInsuranceId: '', proof: '', amount: '' });

  const token = cookies.userData?.token;
  const userId = cookies.userData?.username?.userId;

  useEffect(() => {
    fetchUserInsurances();
  }, []);

  const fetchUserInsurances = async () => {
    try {
      const res = await axios.get(`http://localhost:8080/api/issueInsurance/all`, {
        headers: { Authorization: `Bearer ${token}` },
      });

      const userIssued = res.data.filter((i) => i.userId === userId);

      const mapped = userIssued.map((i) => ({
        issueInsuranceId: i.issueInsuranceId,
        insuranceId: i.insuranceId,
        insuranceType: i.insuranceType || i.type || `Type ${i.insuranceId}`,
        issueInsuranceStatus: i.issueStatus || i.issueInsuranceStatus || 'UNKNOWN',
        validPeriod: i.remainingperiod || i.validPeriod || 0,
        insuranceAmount: i.insuranceAmount || i.amountPaid || 0,
      }));

      setUserInsurances(mapped);
    } catch (err) {
      console.error('Error fetching insurances:', err);
      setUserInsurances([]);
    }
  };

  // Upgrade
  const handleUpgradeClick = (insurance) => {
    setUpgradeData({ issueInsuranceId: insurance.issueInsuranceId, insuranceId: '' });
    setShowUpgradeForm(true);
  };

  const submitUpgrade = async () => {
    if (!upgradeData.insuranceId) return alert('Enter new Insurance ID');

    try {
      const res = await axios.post(
        `http://localhost:8080/api/issueInsurance/upgrade/${upgradeData.insuranceId}/${upgradeData.issueInsuranceId}`,
        {},
        { headers: { Authorization: `Bearer ${token}` } }
      );
      setShowUpgradeForm(false);
      fetchUserInsurances();
      alert('Insurance upgraded successfully!');
    } catch (err) {
      console.error('Upgrade failed', err);
      alert('Upgrade failed!');
    }
  };

  // Claim
  const handleClaimClick = (insurance) => {
    setClaimData({ issueInsuranceId: insurance.issueInsuranceId, proof: '', amount: '' });
    setShowClaimForm(true);
  };

  const submitClaim = async () => {
    if (!claimData.proof || !claimData.amount) return alert('Enter proof and claim amount');

    try {
      const res = await axios.post(
        `http://localhost:8080/api/issueInsurance/claim/${claimData.proof}/${claimData.amount}/${claimData.issueInsuranceId}`,
        {},
        { headers: { Authorization: `Bearer ${token}` } }
      );
      setShowClaimForm(false);
      fetchUserInsurances();
      alert('Claim submitted successfully!');
    } catch (err) {
      console.error('Claim failed', err);
      alert('Claim failed!');
    }
  };

  return (
    <div className="user-dashboard">
      <h1>User Dashboard</h1>

      {/* Insurance Table */}
      {userInsurances.length > 0 ? (
        <table className="insurance-table">
          <thead>
            <tr>
              <th>IssueInsuranceId</th>
              <th>InsuranceId</th>
              <th>Type</th>
              <th>Status</th>
              <th>Valid Period</th>
              <th>Amount</th>
              <th>Actions</th>
            </tr>
          </thead>
          <tbody>
            {userInsurances.map((i) => (
              <tr key={i.issueInsuranceId}>
                <td>{i.issueInsuranceId}</td>
                <td>{i.insuranceId}</td>
                <td>{i.insuranceType}</td>
                <td>{i.issueInsuranceStatus}</td>
                <td>{i.validPeriod} years</td>
                <td>₹{i.insuranceAmount}</td>
                <td>
                  <button onClick={() => handleUpgradeClick(i)}>Upgrade</button>
                  <button onClick={() => handleClaimClick(i)}>Claim</button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      ) : (
        <p>No issued insurances found.</p>
      )}

      {/* Upgrade Form Modal */}
      {showUpgradeForm && (
        <div className="modal-overlay">
          <div className="modal-content">
            <h2>Upgrade Insurance</h2>
            <label>
              IssueInsuranceId:
              <input
                type="number"
                value={upgradeData.issueInsuranceId}
                readOnly
              />
            </label>
            <label>
              New InsuranceId:
              <input
                type="number"
                value={upgradeData.insuranceId}
                onChange={(e) => setUpgradeData({ ...upgradeData, insuranceId: e.target.value })}
              />
            </label>
            <button onClick={submitUpgrade}>Submit Upgrade</button>
            <button onClick={() => setShowUpgradeForm(false)}>Cancel</button>
          </div>
        </div>
      )}

      {/* Claim Form Modal */}
      {showClaimForm && (
        <div className="modal-overlay">
          <div className="modal-content">
            <h2>Claim Insurance</h2>
            <label>
              IssueInsuranceId:
              <input
                type="number"
                value={claimData.issueInsuranceId}
                readOnly
              />
            </label>
            <label>
              Proof:
              <input
                type="text"
                value={claimData.proof}
                onChange={(e) => setClaimData({ ...claimData, proof: e.target.value })}
              />
            </label>
            <label>
              Claim Amount:
              <input
                type="number"
                value={claimData.amount}
                onChange={(e) => setClaimData({ ...claimData, amount: e.target.value })}
              />
            </label>
            <button onClick={submitClaim}>Submit Claim</button>
            <button onClick={() => setShowClaimForm(false)}>Cancel</button>
          </div>
        </div>
      )}
    </div>
  );
}

export default IssueInsuranceForm;
