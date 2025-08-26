
import React from 'react';
import { useNavigate} from 'react-router-dom'; 
import axios from 'axios';
import Cookies from 'js-cookie';
function Dashboard({ username }) {
    const history = useNavigate();
    const handleviewregstudents=async()=>{
        //const accessToken = localStorage.getItem('accessToken');
        const accessToken=Cookies.get("jwt_token");
        try{
            const response = await axios.get(
        "http://localhost:8090/api/Registration/viewregstudents",
            {headers: {
            Authorization: `Bearer ${accessToken}`
          }
});
        console.log("response is "+JSON.stringify(response.data));
        }catch(err){
            console.log("error ",err);

        }
    }
    const handleLogout = () => {
          //localStorage.removeItem('accessToken');
        Cookies.remove('jwt_token'); 

        // Perform logout actions here (e.g., clear session, remove authentication token)
        // After logout, redirect to the login page
        history('/');
    };

    return (
        <div className="d-flex justify-content-center align-items-center vh-100">
            <div className="border rounded-lg p-4" style={{width: '500px', height: '400px'}}>
                <h2 className="mb-4 text-center">Welcome to Dashboard</h2>
                <p className="mb-4 text-center">Hello, {username}!</p>
                <p className="text-center">You are logged in successfully.</p>
                <div className="text-center">
                    <button type="button" className="btn btn-primary mt-3" onClick={handleLogout}>Logout</button>
                    <button onClick={handleviewregstudents}>viewregstudents</button>
                </div>
            </div>
        </div>
    );

}

export default Dashboard;