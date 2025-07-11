import { useEffect, useState } from 'react';
import axios from 'axios';

function ALLMembers() {
    const [summary, setSummary] = useState([]);

    useEffect(() => {
        
        fetchMembers();
    });

    const fetchMembers = async () => {
        try {
            const response = await axios.get("http://localhost:8080/loanmanagement/member");
            setSummary(response.data);
        } catch (error) {
            console.error("Error fetching members:", error);
            alert("Failed to load members");
        }
    };

    return (
        <div>
            <h2>Members Enrolled</h2>
            <div style={{marginRight:"230px"}}>
                {summary.length > 0 ? (
                    summary.map((item, index) => (
                        <div key={index} style={{
                            display: 'flex',
                            gap: '15px',
                            padding: '8px',
                            borderBottom: '1px solid #ccc'
                        }}>
                            <span><strong>Person Id:</strong> {item.id}</span>
                            <span><strong>Name:</strong> {item.name}</span>
                            <span><strong>Email:</strong> {item.email}</span>
                            <span><strong>Mobile:</strong> {item.mobile}</span>
                            <span><strong>Address:</strong> {item.address}</span>
                            <span><strong>Creditscore:</strong> {item.creditScore}</span>
                        </div>
                    ))
                ) : (
                    <p>No Members enrolled</p>
                )}
            </div>
        </div>
    );
}

export default ALLMembers;
