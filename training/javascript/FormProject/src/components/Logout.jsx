import React, { useEffect } from "react";
import { useNavigate } from "react-router-dom";

const Logout = ({ setIsLoggedIn }) => {
  const navigate = useNavigate();

  useEffect(() => {
    setIsLoggedIn(false);
    navigate("/login");
  }, [setIsLoggedIn, navigate]);

  return null;
};

export default Logout;
