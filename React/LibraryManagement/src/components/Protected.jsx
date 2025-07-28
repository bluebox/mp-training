import { useContext, useEffect, useState } from "react";
import { Navigate } from "react-router-dom";
import { AuthContext } from "../context/AuthContext.jsx";
import { jwtDecode } from "jwt-decode";
import axios from "axios";

const ProtectedRoute = ({ children }) => {
  const { isLoggined, setIsLoggined } = useContext(AuthContext);
  const [loading, setLoading] = useState(true);

  const verifyToken = async () => {
    const access = localStorage.getItem("access_token");
    const refresh = localStorage.getItem("refresh_token");

    if (!access) {
      setIsLoggined(false);
      setLoading(false);
      return;
    }

    try {
      const decoded = jwtDecode(access); 
      const now = Date.now() / 1000;

      if (decoded.exp < now) {
        if (!refresh) {
          setIsLoggined(false);
          setLoading(false);
          return;
        }

        const res = await axios.post("http://127.0.0.1:8000/api/member/token/refresh/", {
          refresh,
        });

        localStorage.setItem("access_token", res.data.access);
        setIsLoggined(true);
      } else {
        setIsLoggined(true);
      }
    } catch (err) {
      localStorage.removeItem("access_token");
      localStorage.removeItem("refresh_token");
      setIsLoggined(false);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    verifyToken();
  }, [isLoggined,setIsLoggined]);

  if (loading) return <div className="text-center p-4">Checking authentication...</div>;

  return isLoggined ? children : <Navigate to="/login" />;
};

export default ProtectedRoute;
