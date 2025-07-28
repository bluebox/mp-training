import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { useLocation, useNavigate } from 'react-router-dom';
import EditBook from '../components/EditBook';

const Books = () => {
  const navigate=useNavigate()
  const location=useLocation()
  const [books, setBooks] = useState([]);
  const [loading, setLoading] = useState(true);
  const [showDialog, setShowDialog] = useState(false);
  const [editDialog, setEditDialog] = useState(false);
  const [currentBookId, setCurrentBookId] = useState(null);
  const [editData, setEditData] = useState({
    status: true,
    availablity: true
  });
  const [formData, setFormData] = useState({
    title: '',
    author: '',
    category: ''
  });
  const token=localStorage.getItem('access_token')
  const fetchBooks = async () => {
    try {
      const res = await axios.get('http://127.0.0.1:8000/api/book/crud/',{
        withCredentials:true,
         headers: {
         'Authorization': `Bearer ${token}`
         }
      });
      setBooks(res.data);
    } catch (err) {
        const errors = err.response.data;
        const messages = Object.values(errors).flat().join('\n');
        alert(messages);
    } finally {
      setLoading(false);
    }
  };

  const deleteBook = async (id) => {
    const confirm = window.confirm("Are you sure you want to delete this book?");
    if (!confirm) return;

    try {
      await axios.delete(`http://127.0.0.1:8000/api/book/crud/${id}/`,{
        withCredentials:true,
         headers: {
         'Authorization': `Bearer ${token}`
         }
      });
      setBooks(books.filter((book) => book.id !== id));
    } catch (err) {
        const errors = err.response.data;
        const messages = Object.values(errors).flat().join('\n');
        alert(messages);
    }
  };

  const openEditDialog = (book) => {
    setCurrentBookId(book.id);
    setEditData({
      status: book.status,
      availablity: book.availablity
    });
    setEditDialog(true);
  };

  const handleEditSubmit = async (e) => {
    e.preventDefault();
    try {
      await axios.patch(`http://127.0.0.1:8000/api/book/crud/${currentBookId}/`, editData,{
        withCredentials:true,
         headers: {
         'Authorization': `Bearer ${token}`
         }
      });
      fetchBooks();
    } catch (err) {
        const errors = err.response.data;
        const messages = Object.values(errors).flat().join('\n');
        alert(messages);
    }
    finally{
         setEditDialog(false);
    }
  };

  const handleEditChange = (e) => {
    const { name, value } = e.target;
    const val = name === 'status' || name === 'availablity' ? value === 'true' : value;
    setEditData({ ...editData, [name]: val });
  };

  const OpenDialog = () => {
    setFormData({ title: '', author: '', category: '' });
    setShowDialog(true);
  };

  const handleInputChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const res = await axios.post('http://127.0.0.1:8000/api/book/crud/', formData,{
        withCredentials:true,
         headers: {
         'Authorization': `Bearer ${token}`
         }
      }
      );
      setBooks([...books, res.data]);
      setShowDialog(false);
    } catch (err) {
        const errors = err.response.data;
        const messages = Object.values(errors).flat().join('\n');
        alert(messages);
    }
  };
  const handleDetails=(id)=>{
    navigate(`${location.pathname}/${id}`)
  }
  useEffect(() => {
    fetchBooks();
  }, []);

  if (loading) return <div className="text-center mt-10 text-gray-600">Loading books...</div>;

  return (
    <div className="p-6 overflow-x-auto">
      <div className='flex justify-between items-center mb-4'>
        <h1 className="text-2xl font-bold text-gray-800">Books List</h1>
        <button className='px-3 py-2 bg-blue-500 text-white rounded-lg hover:bg-blue-600' onClick={OpenDialog}>
          Add Book
        </button>
      </div>

      {books.length === 0 ? (
        <p className="text-gray-500">No books found.</p>
      ) : (
        <table className="min-w-full bg-white border border-gray-300 rounded-md shadow">
          <thead className="bg-gray-200">
            <tr>
              <th className="py-2 px-4 border-b text-left">ID</th>
              <th className="py-2 px-4 border-b text-left">Title</th>
              <th className="py-2 px-4 border-b text-left">Author</th>
              <th className="py-2 px-4 border-b text-left">Category</th>
              <th className="py-2 px-4 border-b text-left">Status</th>
              <th className="py-2 px-4 border-b text-left">Availability</th>
              <th className="py-2 px-4 border-b text-left">Actions</th>
            </tr>
          </thead>
          <tbody>
            {books.map((book) => (
              <tr key={book.id}className="odd:bg-white even:bg-gray-100">
                <td className="py-2 px-4 border-b">{book.id}</td>
                <td className="py-2 px-4 border-b">{book.title}</td>
                <td className="py-2 px-4 border-b">{book.author}</td>
                <td className="py-2 px-4 border-b">{book.category}</td>
                <td className={book.status ? 'py-2 px-4 border-b text-green-600':'py-2 px-4 border-b text-red-600'}>{book.status ? 'Active' : 'Inactive'}</td>
                <td className="py-2 px-4 border-b">
                  <span className={book.availablity ? 'text-green-600' : 'text-red-600'}>
                    {book.availablity ? 'Available' : 'Unavailable'}
                  </span>
                </td>
                <td className="py-2 px-4 border-b space-x-2">
                  <button
                    onClick={() => openEditDialog(book)}
                    className="bg-blue-500 text-white px-3 py-1 rounded hover:bg-blue-600"
                  >
                    Edit
                  </button>
                  <button
                    onClick={() => deleteBook(book.id)}
                    className="bg-red-500 text-white px-3 py-1 rounded hover:bg-red-600"
                  >
                    Delete
                  </button>
                   <button
                    onClick={() => handleDetails(book.id)}
                    className="bg-red-500 text-white px-3 py-1 rounded hover:bg-red-600"
                  >
                    Details
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      )}

      {showDialog && (
        <div className="fixed inset-0 bg-black bg-opacity-40 flex items-center justify-center z-50 p-4">
          <div className="bg-white w-full max-w-2xl rounded-lg shadow-lg p-6">
            <h2 className="text-2xl font-bold mb-4 text-gray-700">Add New Book</h2>
            <form onSubmit={handleSubmit} className="space-y-4">
              <input type="text" name="title" placeholder="Title" value={formData.title} onChange={handleInputChange} required className="w-full border border-gray-300 rounded px-3 py-2" />
              <input type="text" name="author" placeholder="Author" value={formData.author} onChange={handleInputChange} required className="w-full border border-gray-300 rounded px-3 py-2" />
              <input type="text" name="category" placeholder="Category" value={formData.category} onChange={handleInputChange} required className="w-full border border-gray-300 rounded px-3 py-2" />
              <div className="flex justify-end gap-2">
                <button type="button" onClick={() => setShowDialog(false)} className="px-4 py-2 bg-gray-300 rounded hover:bg-gray-400">Cancel</button>
                <button type="submit" className="px-4 py-2 bg-blue-500 text-white rounded hover:bg-blue-600">Add</button>
              </div>
            </form>
          </div>
        </div>
      )}

      {editDialog && (
        <div className="fixed inset-0 bg-black bg-opacity-40 flex items-center justify-center z-50 p-4">
          <div className="bg-white w-full max-w-2xl rounded-lg shadow-lg p-6">
            <h2 className="text-2xl font-bold mb-4 text-gray-700">Edit Book</h2>
            <form onSubmit={handleEditSubmit} className="space-y-4">
              <div>
                <label className="block mb-1">Status</label>
                <select name="status" value={editData.status} onChange={handleEditChange} className="w-full border border-gray-300 rounded px-3 py-2">
                  <option value={true}>Active</option>
                  <option value={false}>Inactive</option>
                </select>
              </div>
              <div>
                <label className="block mb-1">Availability</label>
                <select name="availablity" value={editData.availablity} onChange={handleEditChange} className="w-full border border-gray-300 rounded px-3 py-2">
                  <option value={true}>Available</option>
                  <option value={false}>Unavailable</option>
                </select>
              </div>
              <div className="flex justify-end gap-2">
                <button type="button" onClick={() => setEditDialog(false)} className="px-4 py-2 bg-gray-300 rounded hover:bg-gray-400">Cancel</button>
                <button type="submit" className="px-4 py-2 bg-green-500 text-white rounded hover:bg-green-600">Update</button>
              </div>
            </form>
          </div>
        </div>
      )}
    </div>
  );
};

export default Books;
