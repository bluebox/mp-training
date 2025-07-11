import { useState } from 'react';

function UserComponent(){
 
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

    return (
        <div>
            <div className="enrollment">
             <h3>Enroll as Customer</h3>
              <input type="text" name="name" placeholder="raghav" value={member.name} onChange={handleChange} />
              <input type="text" name="email" placeholder="abc.@gmail.com" value={member.email} onChange={handleChange} />
              <input type="text" name="mobile" placeholder="7337584295" value={member.mobile} onChange={handleChange}/>
              <input type="text" name="address" placeholder="gachibowli" value={member.address} onChange={handleChange}/>
            </div>
        </div>
    )
}
export default UserComponent;