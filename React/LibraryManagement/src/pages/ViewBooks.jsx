import React, { useEffect, useState, useContext } from 'react';
import axios from 'axios';
import { UserContext } from '../context/UserContext';

const ViewBooks = () => {
  const token = localStorage.getItem('access_token');
  const { user } = useContext(UserContext);
  const [books, setBooks] = useState([]);

  const fetchBooks = async () => {
    try {
      const res = await axios.get('http://127.0.0.1:8000/api/book/viewbooksavailable/', {
        withCredentials: true,
        headers: {
          'Authorization': `Bearer ${token}`,
        },
      });
      setBooks(res.data);
      console.log(res.data);
      
    } catch (err) {
      console.log(err);
      const error = err.response?.data;
      const msg = Object.values(error).flat().join('\n');
      alert(msg);
    }
  };

  const HandleBorrowBook = async (id) => {
    if(!window.confirm('Do you want to borrow this book')){
      return 
    }
    try {
      await axios.post('http://127.0.0.1:8000/api/issue/issuebook/', {
        book: id,
        member: user?.id,
      }, {
        withCredentials: true,
        headers: {
          'Authorization': `Bearer ${token}`,
        },
      });
      alert('Book is borrowed Successfullly')
      fetchBooks();
    } catch (err) {
      const errors = err.response.data;
      const messages = Object.values(errors).flat().join('\n');
      alert(messages);
    }
  };

  useEffect(() => {
    fetchBooks();
  }, []);

  return (
    <div className="container mx-auto p-6 overflow-x-auto">
      <h1 className="text-2xl font-bold mb-4 text-center">Books Available</h1>

      {books.length > 0 ? (
        <table className="min-w-full table-auto border-collapse border border-gray-200">
          <thead className="text-left">
            <tr>
              <th className="border px-4 py-2 bg-gray-200">ID</th>
              <th className="border px-4 py-2 bg-gray-200">Title</th>
              <th className="border px-4 py-2 bg-gray-200">Author</th>
              <th className="border px-4 py-2 bg-gray-200">Category</th>
              <th className="border px-4 py-2 bg-gray-200">Status</th>
              <th className="border px-4 py-2 bg-gray-200">Availability</th>
              <th className="border px-4 py-2 bg-gray-200">Actions</th>
            </tr>
          </thead>
          <tbody>
            {books.map((book) => (
              <tr key={book.id} className="odd:bg-white even:bg-gray-100">
                <td className="px-4 py-2">{book.id}</td>
                <td className="px-4 py-2">{book.title}</td>
                <td className="px-4 py-2">{book.author}</td>
                <td className="px-4 py-2">{book.category}</td>
                <td className="px-4 py-2">{book.status ? 'Available' : 'Not Available'}</td>
                <td className="px-4 py-2">{book.availablity ? 'Available' : 'Not available'}</td>
                <td className="px-4 py-2">
                  <button
                    className="px-3 py-2 bg-blue-500 text-white rounded-lg"
                    onClick={() => HandleBorrowBook(book.id)} 
                  >
                    Borrow
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      ) : (
        <p>No books available.</p>
      )}
    </div>
  );
};

export default ViewBooks;
