import api from "./api"; 

export const login = (userEmail, userPassword) => {
  return api.post("/auth/login", { userEmail, userPassword });
};

