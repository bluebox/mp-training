import { useState } from 'react';
import axios from "axios";

import { useNavigate } from "react-router-dom";

function MakePayment() {
 const navigate = useNavigate();
    const [data, setData] = useState({
        memberId: "",
        loanId: "",
        amountPaid: "",
        type: "",

    })

        const idConstraint=/^[0-9]+$/;
        const amountConstraint = /^\d+(\.\d+)?$/;

    const handleChange = (e) => {
        const { name, value } = e.target;
        setData((prevData) => ({
            ...prevData, [name]: value
        }))
    }
    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
           if(!idConstraint.test(data.memberId)){
            alert("member id not valid")
            return 
           }
           
            if(!idConstraint.test(data.loanId)){
            alert("loan id not valid")
            return 
           }
           if(!amountConstraint.test(data.amountPaid)){
            alert("invalid amount")
            return 
           }

            const response = await axios.post("http://localhost:8080/loanmanagement/data/payments", data);


            console.log("this is payment response", response);
            setData({
                userId: "",
                loanId: "",
                amountPaid: "",
                type: "",


            })
            alert("payment successful");
            navigate("/payments");
        }
        catch (error) {
            if(error.response)
                alert(error.response.data)
            console.error(error);
        }

    }

    return (
        <div>
            <h1>Proceed payment</h1>
            <form onSubmit={handleSubmit}>
                <input type="text" name="memberId" placeholder="Enter userId" value={data.memberId} onChange={handleChange} />
                <input type="text" name="loanId" placeholder="Enter loanId" value={data.loanId} onChange={handleChange} />
                <input type="text" name="amountPaid" placeholder="Amount paid" value={data.amountPaid} onChange={handleChange} />
                <select value={data.type} name="type" onChange={handleChange}>
                    <option value="">-- Select payment type --</option>
                    <option value="emi">EMI</option>
                    <option value="principle">Principle</option>
                    <option value="foreclosure">Foreclosure</option>
                    <option value="latefee">Latefee</option>
                </select>

                <button type="submit">pay</button>

            </form>

        </div>
    )
}
export default MakePayment