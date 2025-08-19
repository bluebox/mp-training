import axios from "axios";
const BASE_URL = "http://localhost:8080/api/issues";

export const addIssue = async (issue) => {
  const response = await axios.post(`${BASE_URL}/issue`,issue);
  return response.data;
};

export const returnBook = async (returnIssue) => {
  const response = await axios.post(`${BASE_URL}/return`,returnIssue);
  return response.data;
};

export const getIssues = async () => {
    const response = await axios.get(`${BASE_URL}/getIssues`);
    return response.data;
}