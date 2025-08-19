import api from "../api/axios";

// GET all members
export const getMembers = () => api.get("/members/viewallmembers");

// ADD new member
export const addMember = (member) => api.post("/members/addmember", member);

// UPDATE member
export const updateMember = (id, member) => api.post(`/members/updatemember/${id}`, member);

// GET member by id
export const getMemberById = (id) => api.get(`/members/memberbyid/${id}`);
