import axios from "axios";
const BASE_URL = "http://localhost:8080/api/books";


export const addBook = async (book) => {
  const response = await axios.post(`${BASE_URL}/addBook`,book);
  return response.data;
};

export const updateBook = async (book,bookId) => {
  const response = await axios.post(`${BASE_URL}/updateBook`,book);
  return response.data;
};

export const getBooks = async () =>{
    const response = await axios.get(`${BASE_URL}/getBooks`);
    return response.data;
}


export const deleteBook = async (bookId) =>{
    const response = await axios.delete(`${BASE_URL}/deleteBook/${bookId}`);
    return response.data;
}

export const getCategories = async () =>{
  const response = await axios.get(`${BASE_URL}/categories`);
  return response.data;
}

export const getAvailableBooks = async () =>{
    const response = await axios.get(`${BASE_URL}/getAvailableBooks`);
    return response.data;
}


