import React, { useEffect } from "react";
import { useNavigate } from "react-router-dom";

export default function Logout() {
  const navigate = useNavigate();

  useEffect(() => {
    localStorage.setItem('IsLogin', 'false');
    navigate('/login');
  }, [navigate]);

  return null;
}
