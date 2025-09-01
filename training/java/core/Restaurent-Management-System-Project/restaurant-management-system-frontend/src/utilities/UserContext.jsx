import { createContext, useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { jwtDecode } from "jwt-decode";

export const UserContext=createContext();

const UserProvider = ({ children }) => {
  const [user, setUser] = useState(null);
  const navigate = useNavigate();

  useEffect(() => {
    const token = localStorage.getItem("token");
    if (token) {
      const decoded = jwtDecode(token);
      try {
        if (decoded.exp * 1000 < Date.now()) {
          alert("Session expired. Please login again.");
          navigate("/");
          return;
        }
      } catch (error) {
        alert("Invalid token. Please login again.");
        navigate("/");
        return;
      }
      setUser(decoded);
    }
  }, []);

  return (
    <UserContext.Provider value={{ user, setUser }}>
      {children}
    </UserContext.Provider>
  );
};

export default UserProvider;