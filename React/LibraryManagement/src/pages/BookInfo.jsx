import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import Axios from '../utils/Axios';
const BookInfo = () => {
  const { id } = useParams();
  const [bookDetails, setBookDetails] = useState(null);
  const [users, setUsers] = useState([]);

  const token=localStorage.getItem('access_token')
  const fetchBookDetails = async () => {
    try {
      const res = await Axios.get(`book/crud/${id}/`,{
        withCredentials:true,
         headers: {
         'Authorization': `Bearer ${token}`
         }
      });
      setBookDetails(res.data);
    } catch (err) {
      alert(err.response?.data);
    }
  };

  const fetchUsersBorrowers = async () => {
    try {
      const res = await Axios.get(`book/alluserborrowers/${id}`,{
        withCredentials:true,
         headers: {
         'Authorization': `Bearer ${token}`
         }
      });
      setUsers(res.data);
    } catch (err) {
      alert(err.response?.data);
    }
  };

  useEffect(() => {
    fetchBookDetails();
    fetchUsersBorrowers();
  }, [id]);

  if (!bookDetails) {
    return <p className="text-center mt-10 text-lg">Loading...</p>;
  }

  return (
    <div className="max-w-4xl mx-auto mt-5 mb-5 p-6 bg-white shadow-lg rounded-lg border ">
      <h2 className="text-2xl font-bold mb-6 border-b pb-2 text-blue-600">Book Details</h2>
      <ul className="mb-8 text-gray-700 space-y-2">
        <li><strong>ID:</strong> {bookDetails.id}</li>
        <li><strong>Title:</strong> {bookDetails.title}</li>
        <li><strong>Author:</strong> {bookDetails.author}</li>
        <li><strong>category: </strong>{bookDetails.category}</li>
      </ul>

      <h3 className="text-xl font-semibold mb-4 text-green-700">Issued Users</h3>
      {users.length > 0 ? (
        <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
          {users.map((user, index) => (
            <div key={index} className="border rounded-lg p-4 bg-gray-50 shadow-lg">
              <p><strong>ID:</strong> {user.id}</p>
              <p><strong>Email:</strong> {user.email}</p>
              <p><strong>Address:</strong> {user.address}</p>
              <p><strong>Mobile:</strong> {user.mobile}</p>
              <p><strong>Gender:</strong> {user.gender}</p>
            </div>
          ))}
        </div>
      ) : (
        <p className="text-gray-500">This books is not issued to any user</p>
      )}
    </div>
  );
};

export default BookInfo;
