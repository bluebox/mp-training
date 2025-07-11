import { useEffect, useState } from 'react';
import axios from "axios";

function ViewPayment(){
        const [userId, setUserId] = useState("");
    const [summary, setSummary] = useState([]);
    const constraint=/^[0-9]+$/;
    const handleChange = (e) => {
        setUserId(e.target.value);
    };
    useEffect(()=>{
        setSummary([])
    },[userId])

    const fetchSummary = async () => {
        
        if (!userId) return; 
        if(!constraint.test(userId)){
            alert("invalid userid ")
            return;
        }

        try {
            const res = await axios.get(`http://localhost:8080/loanmanagement/member/${userId}`);
            console.log("Fetched data:", res.data);
         
            const data = Array.isArray(res.data) ? res.data : [res.data];
            setSummary(data);
        } catch (err) {
            if(err.response)
                alert(err.response.data)
            console.error("Error fetching summary:", err);
        }
    };
    return (
<div>
            <h2>Member Details</h2>
            <input
                type="text"
                placeholder="Enter User ID"
                value={userId}
                onChange={handleChange}
            />
            <button onClick={fetchSummary}>Fetch Payments</button>
 
            <div>
                {summary.length > 0 ? (

                    
                    summary.map((item, index) => (
                        <div key={index} style={{ display: 'flex',
                        gap: '15px',
                        padding: '8px',
                        borderBottom: '1px solid #ccc'}}>
                            <span><strong>Person Id:</strong> {item.id}</span>
                            <span><strong>Name:</strong> {item.name}</span>
                            <span><strong>Email:</strong> {item.email}</span>
                            <span><strong>Mobile:</strong> {item.mobile}</span>
                            <span><strong>Address:</strong> {item.address}</span>
                                 
                        </div>
                    ))
                ) : (
                    <p>No Member available.</p>
                )}
            </div>
        </div>
    );
}
export default ViewPayment