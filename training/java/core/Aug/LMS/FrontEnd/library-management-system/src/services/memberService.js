import axios from "axios";
const BASE_URL = "http://localhost:8080/api/members";


export const addMember = async (member) => {
  const response = await axios.post(`${BASE_URL}/addMember`,member);
  return response.data;
};

export const updateMember = async (member) => {
  const response = await axios.post(`${BASE_URL}/updateMember`,member);
  return response.data;
};

export const getMembers = async () =>{
    const response = await axios.get(`${BASE_URL}/getMembers`);
    return response.data;
}


export const deleteMember = async (memberId) =>{
    const response = await axios.delete(`${BASE_URL}/deleteMember/${memberId}`);
    return response.data;
}

export const getGenderData = async () =>{
  const response = await axios.get(`${BASE_URL}/genderList`);
  return response.data;
}
