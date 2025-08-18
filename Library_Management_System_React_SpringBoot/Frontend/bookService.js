import axios from "axios";

const BASE_URL = "http://localhost:8080/api/books"; // no trailing newline or spaces

export const getAllBooks = () => axios.get(BASE_URL);
export const addBook = (book) => axios.post(BASE_URL, book);
export const updateBook = (id, book) => axios.put(`${BASE_URL}/${id}`, book);
export const updateAvailability = (id, value) =>
  axios.patch(`${BASE_URL}/${id}/availability?value=${value}`);
