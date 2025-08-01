import { createContext, useContext, useEffect, useState } from "react";
import { AuthContext } from "./AuthContext";
import Axios from "../utils/Axios";

export const UserContext = createContext();

export const UserProvider = ({ children }) => {
  const { isLoggined } = useContext(AuthContext);
  const [user, setUser] = useState(null);

  const fetchUser = async () => {
      const token = localStorage.getItem('access_token');
      if (token) {
        const res = await Axios.get('member/fetchParicularUser/', {
          withCredentials: true,
          headers: {
            'Authorization': `Bearer ${token}`,
          },
        });
        setUser(res.data);
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
