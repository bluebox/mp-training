
import { BrowserRouter as Router, Route, Routes,Redirect } from 'react-router-dom';
import Dashboard from './Dashboard';
import Cookies from 'js-cookie';
import { useNavigate } from 'react-router-dom';
import { useEffect } from 'react';
  const DashboardRoute = () => {
    const navigate=useNavigate();
        const isAuthenticated = Cookies.get('jwt_token');
        const userRole = localStorage.getItem('role');
        console.log("userrole",userRole);
        useEffect(()=>{
            
            if(isAuthenticated && userRole==="Admin"){
                        navigate("/admindashboard");
                    }
                    else if (isAuthenticated && userRole==="Faculty"){
                        navigate("/facultydashboard");
                    }
                     else if (isAuthenticated && userRole==="Student"){
                        navigate("/studentdashboard");
                    }
                    else{
                        navigate("/") ;
                    }
},[navigate]);
        
        return ;
    };

    export default DashboardRoute;