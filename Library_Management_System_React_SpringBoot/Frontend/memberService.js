import axios from "axios";

const BASE_URL = "http://localhost:8080/api/members";

export const getAllMembers = () => axios.get(BASE_URL);
export const addMember = (member) => axios.post(BASE_URL, member);
export const updateMember = (id, member) => axios.put(`${BASE_URL}/${encodeURIComponent(id)}`, member);

