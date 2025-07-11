import { useState } from 'react';
import { Link } from 'react-router-dom';
import { useNavigate } from "react-router-dom";
import axios from "axios";

function issueLoan() {
    const navigate = useNavigate();
    const today = new Date().toISOString().split('T')[0];
    const [data, setData] = useState({
        personId: '',
        loanId: '',
        startDate: today
    });
 const idConstraint=/^[0-9]+$/;
    const handleChange = (e) => {
        const { name, value } = e.target;
        setData((prevData) => ({
            ...prevData, [name]: value
        }))
    }
    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            if(!idConstraint.test(data.personId)||!idConstraint.test(data.loanId)){
                alert("you have entered invalid id")
                return ;
            }
            const response = await axios.post("http://localhost:8080/loanmanagement/data/issue-loan", data);


            console.log("this is issual response", response);
            setData({
                personId: '',
                loanId: '',
                startDate: today


            })
            alert("Loan issued");
            navigate("/");
        }
        catch (error) {
            if(error.response)
                alert(error.response.data)
            console.error(error);
        }

    }

    return (
        <div>
            <h1>Proceed Loan Issual</h1>
            <form onSubmit={handleSubmit}>
                <input type="text" name="personId" placeholder="Enter userId" value={data.personId} onChange={handleChange} />
                <br></br>  <br></br>
                <input type="text" name="loanId" placeholder="Enter loanId" value={data.loanId} onChange={handleChange} />
                <br></br> <br></br>
                <input
                    type="date"
                    name="startDate"
                    value={data.startDate}
                    onChange={handleChange}
                    min={today}
                />
                <br></br> <br></br>
                <button type="submit">Issue Loan</button>

            </form>
        </div>
    )
}

export default issueLoan;