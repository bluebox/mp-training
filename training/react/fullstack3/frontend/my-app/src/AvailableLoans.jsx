import React, { useEffect, useState } from 'react';
import axios from 'axios';

function AvailableLoans() {

    const [selectedLoanType, setSelectedLoanType] = useState(null);
    const [loans,setLoans]=useState([]);
    

    useEffect(() => {
      
        axios.get(`http://localhost:8080/loanmanagement/loans/type/${selectedLoanType}`)
            .then(res => {
                setLoans(res.data)
                console.log(res.data);
                console.log(loans)
            })
            .catch(err => console.error("Error fetching the loans:", err));

       }, [selectedLoanType]);

   
   const giveLoans = (loans, type) => (
    <div>
        <h3>{type} Loans</h3>
        {Array.isArray(loans) && loans.length > 0 ? (
            loans.map((loan, index) => (
                <div
                    key={index}
                    style={{
                        display: 'flex',
                        gap: '15px',
                        padding: '8px',
                        borderBottom: '1px solid #ccc'
                    }}
                >
                    <span><strong>Id:</strong> {loan.loanId}</span>
                    <span><strong>Type:</strong> {loan.loanType}</span>
                    <span><strong>Principle:</strong> ₹{loan.principle}</span>
                    <span><strong>Interest:</strong> {loan.rateOfIntrest}%</span>
                    <span><strong>Tenure:</strong> {loan.tenureInDays} days</span>
                </div>
            ))
        ) : (
            <p>No {type.toLowerCase()} loans available.</p>
        )}
    </div>
);


    return (
        <div style={{ padding: '20px' }}>
            <h2>Available Loan Types</h2>
            <button onClick={() => setSelectedLoanType('car')}>Car Loan</button>
            <button onClick={() => setSelectedLoanType('home')}>Home Loan</button>
            <button onClick={() => setSelectedLoanType('personal')}>Personal Loan</button>

            {selectedLoanType === 'car' && giveLoans(loans, 'Car')}
            {selectedLoanType === 'home' && giveLoans(loans, 'home')}
            {selectedLoanType === 'personal' && giveLoans(loans, 'Personal')}
        </div>
    );
}

export default AvailableLoans;



