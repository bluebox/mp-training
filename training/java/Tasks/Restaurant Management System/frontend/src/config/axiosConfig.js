import axios from "axios";

const api = axios.create({
  baseURL: "http://localhost:8080",
});

// const api = axios.create({
//   baseURL: "http://10.129.240.247:8080",
// });
api.interceptors.request.use((config) => {
  const token = localStorage.getItem("token");
  if (token) {
    config.headers.Authorization = `Bearer ${token}`;
  }
  return config;
});

export default api;
