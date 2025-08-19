import api from "../api/axios";

// GET all books
export const getBooks = () => api.get("/books/viewallbooks");

// ADD new book
export const addBook = (book) => api.post("/books/addbook", book);

// UPDATE book
export const updateBook = (id, book) => api.post(`/books/updatebook/${id}`, book);

// GET book by id
export const getBookById = (id) => api.get(`/books/bookbyid/${id}`);
