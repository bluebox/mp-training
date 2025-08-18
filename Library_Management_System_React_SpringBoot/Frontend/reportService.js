import axios from "axios";

const API_BASE = "http://localhost:8080/api/reports"; 

export const getOverdueBooks = () => {
  return axios.get(`${API_BASE}/overdue`);
};

export const getBooksPerCategory = () => {
  return axios.get(`${API_BASE}/books-per-category`); 
};

export const getActiveMembers = () => {
  return axios.get(`${API_BASE}/active-members`); 
};
