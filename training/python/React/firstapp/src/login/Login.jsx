import { useState } from "react"
import { useNavigate } from "react-router-dom";
import "./Login.css";

function Login(){

    const [loginData, setLoginData] = useState({username:'', password:''});
    const data = localStorage.getItem('isLogin') === "true";
    const [isLogin, setIsLogin] = useState(data);
    const navigate = useNavigate();


    const handleChange = (e) => {
        e.preventDefault();
        setLoginData({...loginData, [e.target.name]: e.target.value });
    }

    function handleSubmit(){
        if (!isLogin){
            if (loginData.username === "admin" && loginData.password === "admin"){
                setIsLogin(true);
                localStorage.setItem('isLogin',JSON.stringify(true))
                navigate("/");
                window.location.reload();

            }
            else{
                alert("invlaid credintials");
            }
            
        }
        else{
            navigate("/");
        }
    }

    
    return (
    
    <div className="login-container">
        <div className="form">
            <h1>Login Here</h1>
            <table>
            <tr>
                <td>
                    <label htmlFor="username">username</label>
                </td>
                <td>
                    <input type="text" name="username" placeholder="enter username" onChange={handleChange} required/>
                </td>
            </tr>
            <tr>
                <td>
                    <label htmlFor="password">password</label>
                </td>
                <td>
                    <input type="password" name="password" placeholder="enter password" onChange={handleChange} required/>
                </td>
            </tr>
            <tr>
                <td colSpan={2}>
                    <input type="submit" onClick={handleSubmit}/>
                </td>
            </tr>
            </table>
        </div>

    </div>)
}

export default Login;