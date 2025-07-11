import { useEffect, useState } from "react";
import axios from "axios";

function LoanSchedule() {
    const [userId, setUserId] = useState("");
    const [summary, setSummary] = useState([]);
const constraint=/^[0-9]+$/;
    const handleChange = (e) => {
        setUserId(e.target.value);
    };

    const fetchSummary = async () => {
        if (!userId) return; 

        if(!constraint.test(userId)){
            alert("invalid userid ")
            return;
        }

        try {
            const res = await axios.get(`http://localhost:8080/loanmanagement/data/member/${userId}`);
            console.log("Fetched data:", res.data);
         
            const data = Array.isArray(res.data) ? res.data : [res.data];
            setSummary(data);
        } catch (err) {
            if(err.response)
                alert(err.response.data)
            console.error("Error fetching summary:", err);
        }
    };
    useEffect(()=>{
        setSummary([])
    },[userId])

    return (
        <div>
            <h2>Loan Summary</h2>
            <input
                type="text"
                placeholder="Enter User ID"
                value={userId}
                onChange={handleChange}
            />
            <button onClick={fetchSummary}>Fetch Summary</button>
 
            <div>
                {summary.length > 0 ? (
                    summary.map((item, index) => (
                        <div key={index} style={{ border: "1px solid gray", padding: "10px", margin: "10px 0" }}>
                            <p><strong>Loan ID:</strong> {item.loanId}</p>
                              <p><strong>Person ID:</strong> {item.personId}</p>
                            <p><strong>Type:</strong> {item.loanType}</p>
                              <p><strong>principle:</strong> {item.principle}</p>
                               <p><strong>emi:</strong> {item.emi}</p>
                                <p><strong>Intrest:</strong> {item.rate}</p>
                                  <p><strong>Tenure:</strong> {item.tenureInDays}</p>
                                    <p><strong>Start Date:</strong> {item.startDate}</p>
                                    <p><strong>Amount paid:</strong> {item.amountPaid}</p>
                            <p><strong>Outstanding:</strong> {item.outstandingBalance}</p>
                              <p><strong>Due Date:</strong> {item.dueDate}</p>
                                <p><strong>late Fee:</strong> {item.lateFee}</p>
                                  <p><strong>status:</strong> {item.status}</p>
                        </div>
                    ))
                ) : (
                    <p>No summary available.</p>
                )}
            </div>
        </div>
    );
}

export default LoanSchedule;





