import axios from "axios";

const BASE_URL = "http://localhost:8080/api/issuerecords";

export const getRecords = () => axios.get(BASE_URL);

export const issueBook = (record) => axios.post(`${BASE_URL}/issue`, record);

export const returnBook = (id) => axios.put(`${BASE_URL}/return/${id}`);
