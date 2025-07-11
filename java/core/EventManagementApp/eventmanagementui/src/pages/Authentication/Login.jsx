import axios from "axios";
import { useState } from "react";




function Login() {
    const [formData, setFormData] = useState({
        user_id: null,
        userName: "",
        password: "",
        role: ""
    });
    const [responseMessage, setResponseMessage] = useState("");

    const handleChange = event => {
        setFormData(prev => ({ ...prev, [event.target.name]: event.target.value }));
    };

    const handleClick = async (event) => {
        event.preventDefault();
        console.log("Login data : ", formData);
        try {
            const result = await axios.post("http://localhost:8080/api/register",formData            
            );
            setResponseMessage("Login Successfull!");
            
        }
        catch (error) {
            console.log("Error : ", error.response);
            setResponseMessage("Login Failed!");
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
                    <label for="userName">
                        Username:
                    </label>
                    <br></br>
                    <input type="text" id="userName" name="userName" value={formData.userName} onChange={handleChange}
                        placeholder="Enter your Username" required />
                    <br></br>
                    <label for="password">
                        Password:
                    </label>
                    <br></br>
                    <input type="password" id="password" name="password" value={formData.password} onChange={handleChange}
                        placeholder="Enter your Password" required />
                    <br></br>
                    <label for="role">Role</label>
                    <br></br>
                    <input type="text" id="role" name="role" value={formData.role} onChange={handleChange}
                        placeholder="Enter your Role" required />
                    <br></br>


                    <div class="wrap">
                        <button type="register">
                            Login
                        </button>
                    </div>
                </form>
            </>
        )

}

export default Login;