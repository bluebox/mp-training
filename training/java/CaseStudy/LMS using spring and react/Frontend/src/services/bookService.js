import api from './api';

export const bookService = {
  getAllBooks: () => api.get('/books'),
  
  createBook: (bookData) => {
    return api.post('/books', bookData);
  },
  
  updateBook: (bookId, bookData) => {
    return api.put(`/books/${bookId}`, bookData);
  },
  
  deleteBook: (bookId) => {
    return api.delete(`/books/${bookId}`);
  },
  
  getCategories: () => api.get('/books/categories/all')
};
