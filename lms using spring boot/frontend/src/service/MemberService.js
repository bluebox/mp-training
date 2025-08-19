import axios from "axios";

const API_URL = "http://localhost:8081/members";

export const getAllMembers = () => axios.get(API_URL);
export const getMemberById = (id) => axios.get(`${API_URL}/${id}`);
export const addMember = (member) => axios.post(API_URL, member);
export const updateMember = (id, member) =>
  axios.put(`${API_URL}/${id}`, member);