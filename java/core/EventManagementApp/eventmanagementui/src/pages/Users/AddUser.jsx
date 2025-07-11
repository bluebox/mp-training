
import axios from "axios";
import { useState } from "react";

function AddUser() {
    const [formData, setFormData] = useState({
        user_id:null,
        name:"",
        phn_number:"",
        email:"",
        role:"",
        gender:"",
        status:"",
        dept:""
    });
    const [responseMessage, setResponseMessage] = useState("");

    const handleChange = event => {
        setFormData(prev => ({ ...prev, [event.target.name]: event.target.value }));
    };

    const handleClick = async (event) => {
        event.preventDefault();
        console.log("User data : ", formData);
        try {
            const result = await axios.post("http://localhost:8080/api/user/add", formData

            );
            setResponseMessage("User Added Successfull!");

        }
        catch (error) {
            console.log("Error : ", error.response);
            setResponseMessage("Creation of User Failed!");
        }
    };

    return (
        <>
            <form onSubmit={handleClick}>
                <label for="user_id">User_Id:</label>
                <br></br>
                <input type="text" id="user_id" name="user_id" value={formData.user_id} onChange={handleChange}
                    placeholder="Enter your User_Id" required />
                <br></br>
                <label for="name">
                    name:
                </label>
                <br></br>
                <input type="text" id="name" name="name" value={formData.name} onChange={handleChange}
                    placeholder="Enter the Name" required />
                <br></br>
                <label for="phn_number">
                    phn_number:
                </label>
                <br></br>
                <input type="text" id="phn_number" name="phn_number" value={formData.phn_number} onChange={handleChange}
                    placeholder="Enter The phn_number" required />
                <br></br>
                <label for="email">Email</label>
                <br></br>
                <input type="email" id="email" name="email" value={formData.email} onChange={handleChange}
                    placeholder="Enter the email" required />
                <br></br>
                 <label for="role">
                    role:
                </label>
                <br></br>
                <input type="text" id="role" name="role" value={formData.role} onChange={handleChange}
                    placeholder="Enter The role" required />
                <br></br>
                <label for="gender">Gender</label>
                <br></br>
                <input type="text" id="gender" name="gender" value={formData.gender} onChange={handleChange}
                    placeholder="Enter the gender" required />
                <br></br>
                <label for="status">
                    status:
                </label>
                <br></br>
                <input type="text" id="status" name="status" value={formData.status} onChange={handleChange}
                    placeholder="Enter The status" required />
                <br></br>
                <label for="dept">Department</label>
                <br></br>
                <input type="text" id="dept" name="dept" value={formData.dept} onChange={handleChange}
                    placeholder="Enter the Department" required />
                <br></br>


                <div class="wrap">
                    <button type="submit">
                        submit
                    </button>
                </div>
            </form>
        </>
    )

}

export default AddUser;