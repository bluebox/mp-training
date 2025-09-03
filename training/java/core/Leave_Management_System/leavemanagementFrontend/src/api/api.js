import axios from "axios";

const api = axios.create({
  baseURL: "http://localhost:8080/api",
  withCredentials: true, 
});




export const getuserDetails = (email) => {
  return api.get(`/employee/getUserdetails?email=${email}`);
};


export default api;
