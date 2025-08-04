import axios from "axios";
import store from "../store/store";
import { setIsAuthenticated } from "../store/slices/AuthSlice";

const Axios = axios.create({
  baseURL: "http://127.0.0.1:8000/api/",
  withCredentials: true,
});

let isRefreshing = false;
let refreshSubscribers = [];

const subscribeTokenRefresh = (callback) => {
  refreshSubscribers.push(callback);
};

const onTokenRefreshed = (newToken) => {
  refreshSubscribers.forEach((callback) => callback(newToken));
  refreshSubscribers = [];
};

Axios.interceptors.request.use(
  (config) => {
    const token = localStorage.getItem("access_token");
    if (token) {
      config.headers["Authorization"] = `Bearer ${token}`;
    }
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

Axios.interceptors.response.use(
  (response) => {
    return response;
  },
  async (error) => {
    const originalRequest = error.config;

    if (error.response && error.response.status === 401) {
      const refresh = localStorage.getItem("refresh_token");

      if (!refresh) {
        localStorage.removeItem("access_token");
        localStorage.removeItem("refresh_token");
        store.dispatch(setIsAuthenticated(false));
        alert("No refresh token. Please log in again.");
        window.location.href = "/login";
        return Promise.reject(error);
      }

      if (isRefreshing) {
        return new Promise((resolve) => {
          subscribeTokenRefresh((newToken) => {
            originalRequest.headers["Authorization"] = `Bearer ${newToken}`;
            resolve(axios(originalRequest));
          });
        });
      }

      isRefreshing = true;

      try {
        const res = await Axios.post("/member/token/refresh/", { refresh });
        const newAccess = res.data.access;
        localStorage.setItem("access_token", newAccess);
        originalRequest.headers["Authorization"] = `Bearer ${newAccess}`;
        onTokenRefreshed(newAccess);
        store.dispatch(setIsAuthenticated(true));
        return axios(originalRequest);
      } catch (refreshError) {
        localStorage.removeItem("access_token");
        localStorage.removeItem("refresh_token");
        store.dispatch(setIsAuthenticated(false));
        alert("Session expired. Please log in again.");
        window.location.href = "/login";
        return Promise.reject(refreshError);
      } finally {
        isRefreshing = false;
      }
    }

    return Promise.reject(error);
  }
);

export default Axios;
