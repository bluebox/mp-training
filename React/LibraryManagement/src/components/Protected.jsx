import { useContext, useEffect, useState } from "react";
import { Navigate, useNavigate } from "react-router-dom";
import { AuthContext } from "../context/AuthContext.jsx";
import Axios from "../utils/Axios.jsx";
const ProtectedRoute = ({ children }) => {
  const { isLoggined, setIsLoggined } = useContext(AuthContext);
  const [loading, setLoading] = useState(true);
  const navigate = useNavigate();

  const verifyToken = async () => {
    const access = localStorage.getItem("access_token");
    const refresh = localStorage.getItem("refresh_token");

    if (!access || !refresh) {
      localStorage.removeItem('access_token');
      localStorage.removeItem('refresh_token');
      setIsLoggined(false);
      setLoading(false);
      return;
    }

    try {
      await Axios.post('member/verify_access_token/', {
        access_token: access,
      });
      setIsLoggined(true);
      setLoading(false);
    } catch (err) {
      // setIsLoggined(false)
      try {
        const res = await Axios.post('member/token/refresh/', {
          refresh: refresh,
        });
        localStorage.setItem('access_token', res.data.access);
        setIsLoggined(true);
        setLoading(false);
      } catch (err) {
        localStorage.removeItem('access_token');
        localStorage.removeItem('refresh_token');
        setIsLoggined(false);
        setLoading(false);
        alert('Token expired. Please log in again.');
        navigate('/login');
      }
    }
  };

  useEffect(() => {
    verifyToken();
  }, [isLoggined]);

  if (loading) {
    return <div className="text-center p-4">Checking authentication...</div>;
  }

  return isLoggined ? children : <Navigate to="/login" />;
};

export default ProtectedRoute;
