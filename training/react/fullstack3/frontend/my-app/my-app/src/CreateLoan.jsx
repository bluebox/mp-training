import { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
function CreateLoan() {

 const navigate = useNavigate();
    const [data, setData] = useState({
        loanType: "",
        principle: "",
        rateOfIntrest: "",
        tenureInDays: "",

    })
  
    const amountConstraint = /^\d+(\.\d+)?$/;
    const constraint=/^[0-9]+$/;

    const handleChange = (e) => {
        const { name, value } = e.target;
        setData((prevData) => ({
            ...prevData, [name]: value
        }))
    }

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
           
           if (
  data.loanType !== "car" &&
  data.loanType !== "home" &&
  data.loanType !== "personal"
) {
  alert("Invalid loan type");
  return;
}

if(!amountConstraint.test(data.principle)){
    alert("invalid value entered")
    return;
}
if(!amountConstraint.test(data.rateOfIntrest)){
    alert("invalid value entered")
    return;
}
if(!constraint.test(data.tenureInDays)){
    alert("invalid days entered")
    return;
}
            const response = await axios.post("http://localhost:8080/loanmanagement/loans/create-loan", data);


            console.log("this is the response", response);
            setData({
                loanType: "",
        principle: "",
        rateOfIntrest: "",
        tenureInDays: ""


            })
            alert("Loan created succesfully");
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
            <h1>Create new Loan Plan</h1>
            <form onSubmit={handleSubmit}>
                <select value={data.loanType} name="loanType" onChange={handleChange}>
                    <option value="">-- Select loan type --</option>
                    <option value="home">Home Loan</option>
                    <option value="car">Car Loan</option>
                    <option value="personal">Personal Loan</option>
                </select>
                <br></br> <br></br>

                <input type="text" name="principle" placeholder="Enter principle amount" value={data.principle} onChange={handleChange} />
                <br></br> <br></br>

                <input type="text" name="rateOfIntrest" placeholder="Enter intrest" value={data.rateOfIntrest} onChange={handleChange} />
                <br></br> <br></br>

                <input type="text" name="tenureInDays" placeholder="Enter tenure in days" value={data.tenureInDays} onChange={handleChange} />
                <br></br> <br></br>

                <button type="submit">Create Loan</button>
            </form>
        </div>
    )
}

export default CreateLoan;