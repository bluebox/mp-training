import axios from "axios";
const BASE_URL = "http://localhost:8080/api/reports";

export const bookCategoryCount = async () =>{
  const response = await axios.get(`${BASE_URL}/bookCategoryCount`);
  return response.data;
}

export const activeIssuedBooks = async () =>{
  const response = await axios.get(`${BASE_URL}/activeIssuedBooks`);
  return response.data;
}

export const overDueBooks = async () =>{
  const response = await axios.get(`${BASE_URL}/overDueBooks`);
  return response.data;
}