import axios from "axios";
import  store  from "../app/store";
import { refreshToken, logout } from "../features/feature/auth/authSlice";

const baseURL = "http://127.0.0.1:8000";

const axiosInstance = axios.create({
  baseURL,
  headers: { "Content-Type": "application/json" },
});

axiosInstance.interceptors.request.use(
  (config) => {
    const token = store.getState().auth.access;
    if (token) config.headers["Authorization"] = `Bearer ${token}`;
    return config;
  },
  (error) => Promise.reject(error)
);

axiosInstance.interceptors.response.use(
  (response) => response,
  async (error) => {
    const originalRequest = error.config;
    if (
      error.response?.status === 401 &&
      !originalRequest._retry &&
      store.getState().auth.refresh
    ) {
      originalRequest._retry = true;
      try {
        const res = await store.dispatch(refreshToken()).unwrap();
        axios.defaults.headers.common["Authorization"] = `Bearer ${res.access}`;
        return axiosInstance(originalRequest);
      } catch (err) {
        store.dispatch(logout());
        return Promise.reject(err);
      }
    }
    return Promise.reject(error);
  }
);

export default axiosInstance;
