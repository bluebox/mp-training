import { createContext, useContext, useEffect, useState } from "react";
import { AuthContext } from "./AuthContext";
import axios from "axios";

export const UserContext = createContext();

export const UserProvider = ({ children }) => {
  const { isLoggined } = useContext(AuthContext);
  const [user, setUser] = useState(null);

  const fetchUser = async () => {
    try {
      const token = localStorage.getItem('access_token');
      if (token) {
        const res = await axios.get('http://127.0.0.1:8000/api/member/fetchParicularUser/', {
          withCredentials: true,
          headers: {
            'Authorization': `Bearer ${token}`,
          },
        });
        setUser(res.data);
      }
    } catch (err) {
      const error = err.response?.data || {};
      const msg = Object.values(error).flat().join('\n');
      alert(msg);
    }
  };

  useEffect(() => {
    if (isLoggined) {
      fetchUser();
    }
  }, [isLoggined]);

  return (
    <UserContext.Provider value={{ user, setUser }}>
      {children}
    </UserContext.Provider>
  );
};
