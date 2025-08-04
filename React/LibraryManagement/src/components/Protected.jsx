import { useContext, useEffect, useState } from "react";
import { Navigate, useNavigate } from "react-router-dom";
import { AuthContext } from "../context/AuthContext.jsx";
import Axios from "../utils/Axios.jsx";
const ProtectedRoute = ({ children }) => {
  const { isLoggined, setIsLoggined } = useContext(AuthContext);
  return isLoggined ? children : <Navigate to="/login" />;
};

export default ProtectedRoute;
