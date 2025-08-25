import React, { useEffect, useState } from 'react';
import { useLocation } from 'react-router-dom';
import axios from 'axios';
import './App.css';

function IssueInsuranceDashboard() {
    const location = useLocation();
    const user = location.state?.user; 
    const [insurances, setInsurances] = useState([]);
    const [issuedInsurances, setIssuedInsurances] = useState([]);

    useEffect(() => {
        const fetchInsurances = async () => {
            try {
                const res = await axios.get('http://localhost:8080/api/insurance/getAllInsurances');
                setInsurances(res.data);
            } catch (err) {
                console.error(err);
            }
        };

        const fetchIssued = async () => {
            try {
                const res = await axios.get(`http://localhost:8080/api/insurance/issued/${user.UserId}`);
                setIssuedInsurances(res.data);
            } catch (err) {
                console.error(err);
            }
        };

        fetchInsurances();
        if (user) fetchIssued();
    }, [user]);

    const handleClaim = (insuranceId) => {
        alert(`Claim insurance: ${insuranceId}`);
    };

    const handleUpgrade = (insuranceId) => {
        alert(`Upgrade insurance: ${insuranceId}`);
    };

    return (
        <div className="dashboard">
            <h1>Welcome {user?.UserFirstname}</h1>

            <div className="insurance-charts">
                <h2>Available Insurances</h2>
                <div className="chart-container">
                    {insurances.map((i) => (
                        <div key={i.insuranceId} className="chart-item">
                            <p>{i.type} - ₹{i.insuranceAmount}</p>
                            <p>{i.validPeriod} years</p>
                        </div>
                    ))}
                </div>
            </div>

            <div className="issued-insurances">
                <h2>Your Issued Insurances</h2>
                <table className="viewInsurances">
                    <thead>
                        <tr>
                            <th>InsuranceId</th>
                            <th>Insurance</th>
                            <th>ValidityPeriod</th>
                            <th>Amount</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        {issuedInsurances.map((i) => (
                            <tr key={i.insuranceId}>
                                <td>{i.insuranceId}</td>
                                <td>{i.type}</td>
                                <td>{i.validPeriod} years</td>
                                <td>{i.insuranceAmount}</td>
                                <td>
                                    <button onClick={() => handleClaim(i.insuranceId)}>Claim</button>
                                    <button onClick={() => handleUpgrade(i.insuranceId)}>Upgrade</button>
                                </td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            </div>
        </div>
    );
}



export default IssueInsuranceDashboard;
