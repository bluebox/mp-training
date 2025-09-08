import React, { createContext, useContext, useState, useEffect } from "react";

const AuthContext = createContext(null);

export function AuthProvider({ children }) {
  const [user, setUser] = useState(() => {
    const savedUser = sessionStorage.getItem("user");
    return savedUser ? JSON.parse(savedUser) : null;
  });

  useEffect(() => {
    if (user) {
      sessionStorage.setItem("user", JSON.stringify(user));
    } else {
      sessionStorage.removeItem("user");
    }
  }, [user]);

  const login = (userObj) => {
    setUser({
      id: String(userObj.id || ""),
      firstName: userObj.firstName || "",
      lastName: userObj.lastName || "",
      roles: userObj.roles || [],  
      username: userObj.username || userObj.userName || "",
      password: userObj.password || "", 
    });
  };

  const logout = () => {
    setUser(null);
  };

  const getAuthHeader = () => {
    if (!user?.username || !user?.password) return {};
    const token = btoa(`${user.username}:${user.password}`);
    return { Authorization: `Basic ${token}` };
  };

  const isLoggedIn = () => Boolean(user);
  const hasRole = (role) => user?.roles?.includes(role); 

  return (
    <AuthContext.Provider value={{ user, login, logout, getAuthHeader, isLoggedIn, hasRole }}>
      {children}
    </AuthContext.Provider>
  );
}

export function useAuth() {
  return useContext(AuthContext);
}
