import { useState } from 'react';
import axios from "axios";
import { useNavigate } from "react-router-dom";


function CustomerEnrollment(){

    const navigate = useNavigate();
     const [member, setMember] = useState({
            name: "",
            email: "",
            mobile: "",
            address: ""
    
        })

       const handleChange = (e) => {
        const { name, value } = e.target;
        setMember((prevData) => ({
            ...prevData, [name]: value
        }))
    }

     const validate = () => {
        const nameRegex = /^[A-Za-z\s]+$/;
        const emailRegex = /^[^\s@!#%^&*()-_={}]+@[^\s@]+\.[^\s@]+$/;
        const mobileRegex = /^[0-9]{10}$/;

        
        

        if (!member.name.trim()) {
            alert("Name cannot be empty.");
            return false;
        }
        if (!nameRegex.test(member.name)) {
            alert("Name must contain only letters and spaces.");
            return false;
        }
        if (!emailRegex.test(member.email)) {
            alert("Please enter a valid email address.");
            return false;
        }
        if (!mobileRegex.test(member.mobile)) {
            alert("Mobile number must be exactly 10 digits.");
            return false;
        }
        if (!member.address.trim()) {
            alert("Address cannot be empty.");
            return false;
        }

        return true;
    };
    

     const handleSubmit = async (e) => {
        e.preventDefault();
           if(!validate())
            return 
            
        try {
            
            const response = await axios.post("http://localhost:8080/loanmanagement/member/create-customer", member);


            console.log("this is signup response", response);
            setMember({
                name: "",
                email: "",
                mobile: "",
                phone_number: ""

            })
            alert("Enrollment successful");
            navigate("/");
        }
        catch (error) {
          
            if(error.response){ 
           alert(error.response.data)
            }

        
            
        }

    }


    return (
        <div>
            <div className="enrollment">
             <h3>Enroll as Customer</h3>
             name:   <input type="text" name="name" placeholder="raghav" value={member.name} onChange={handleChange} /><br></br><br></br>
             email:  <input type="text" name="email" placeholder="abc.@gmail.com" value={member.email} onChange={handleChange} /><br></br><br></br>
            Mobile: <input type="text" name="mobile" placeholder="7337584295" value={member.mobile} onChange={handleChange}/><br></br><br></br>
     Address: <input type="text" name="address" placeholder="gachibowli" value={member.address} onChange={handleChange}/><br></br>
             <button style={{marginLeft:"25%",marginTop:"12%"} }onClick={handleSubmit}>Submit</button>
            </div>
        </div>
    )
}
export default CustomerEnrollment;