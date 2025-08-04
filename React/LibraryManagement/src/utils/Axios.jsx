import axios from "axios";

const Axios = axios.create({
  baseURL: 'http://127.0.0.1:8000/api/',
  withCredentials: true
});

let isRefreshing = false;
let refreshSubscribers = [];

const subscribeTokenRefresh = (callback) => {
  refreshSubscribers.push(callback);
};

const onTokenRefreshed = (newToken) => {
  refreshSubscribers.forEach(callback => callback(newToken));
  refreshSubscribers = [];
};

Axios.interceptors.request.use(
  (config) => {
    const token = localStorage.getItem('access_token');
    if (token) {
      config.headers['Authorization'] = `Bearer ${token}`;
    }
    return config;
  },
  (error) => Promise.reject(error)
);

Axios.interceptors.response.use(
  (response) => {
    return response;
  },
  async (error) => {
    if (error.response && error.response.status === 401) {        
      const refresh = localStorage.getItem('refresh_token');
      
      if (isRefreshing) {
        return new Promise((resolve) => {
          subscribeTokenRefresh((newToken) => {
            error.config.headers['Authorization'] = `Bearer ${newToken}`;
            resolve(axios(error.config));
          });
        });
      }

      if (!refresh) {
        localStorage.removeItem('access_token');
        localStorage.removeItem('refresh_token');
        window.location.href = '/login';
        alert('No refresh token available. Please log in again.');
        return Promise.reject(error);
      }

      isRefreshing = true;

      try {
        const res = await Axios.post('/member/token/refresh/', { refresh: refresh });
        localStorage.setItem('access_token', res.data.access);
        error.config.headers['Authorization'] = `Bearer ${res.data.access}`;
        onTokenRefreshed(res.data.access);
        return axios(error.config);
      } catch (refreshError) {
        localStorage.removeItem('access_token');
        localStorage.removeItem('refresh_token');
        window.location.href = '/login';
        alert('Token expired. Please log in again.');
        return Promise.reject(refreshError);
      } finally {
        isRefreshing = false;
      }
    }

    localStorage.removeItem('access_token');
    localStorage.removeItem('refresh_token');
    window.location.href = '/login';
    alert('Session expired. Please log in again.');
    return Promise.reject(error);
  }
);

export default Axios;
