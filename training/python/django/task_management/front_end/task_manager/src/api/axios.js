import axios from "axios";

const api = axios.create({
  baseURL: "http://localhost:8000/api/",
  headers: {
    "Content-Type": "application/json",
  },
});


api.interceptors.request.use(
  (config) => {
    const token = localStorage.getItem("access");
    if (token) {
      config.headers.Authorization = 'Bearer '+token;
    }
    return config;
  },
  (error) => Promise.reject(error)
);

api.interceptors.response.use(
  response => response,
  async error => {
    if (error.response?.status === 401) {
      const refreshToken = localStorage.getItem("refresh");
      if (!refreshToken) return Promise.reject(error);

      try {
        const { data } = await axios.post("http://localhost:8000/api/token/refresh/", { refresh: refreshToken });
        localStorage.setItem("access", data.access);
        error.config.headers.Authorization = 'Bearer ' + data.access;
        return api(error.config);
      } catch {
        localStorage.removeItem("access");
        localStorage.removeItem("refresh");
        localStorage.removeItem("user");
        return Promise.reject(error);
      }
    }
    return Promise.reject(error);
  }
);

export default api;
