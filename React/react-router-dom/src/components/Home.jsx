import React, { useContext, useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { EditContext } from './EditContext';

const Home = () => {
  const {EditData,setEditData}=useContext(EditContext)
  const navigate=useNavigate()
  const [users, setUsers] = useState([]);

  useEffect(() => {
    const val = JSON.parse(localStorage.getItem('users') || '[]');
    setUsers(val);
  }, []);
  const handleDelete = (indexToDelete) => {
    const updatedUsers = users.filter((user,index) => index !== indexToDelete);
    setUsers(updatedUsers);
    localStorage.setItem('users', JSON.stringify(updatedUsers));
  };
  const handleEdit=(indexToEdit)=>{
       const edituser=users[indexToEdit]
       const updatedUsers = users.filter((user,index) => index !== indexToEdit);
       setUsers(updatedUsers);
       localStorage.setItem('users', JSON.stringify(updatedUsers));
       setEditData(edituser)
       navigate('/create')
  }
  return (
    <div className='flex justify-center items-center min-h-screen mt-5'>
      <div className='overflow-auto w-full max-w-4xl p-4 bg-white rounded border border-gray-200 shadow-2xl'>
        <h1 className='text-2xl font-bold mb-4 text-center'>User List</h1>
        {users.length > 0 ? (
          <table className='w-full table-auto border-collapse border border-gray-300'>
            <thead>
              <tr className='bg-gray-200'>
                <th className='border border-gray-300 px-4 py-2'>Sno</th>
                <th className='border border-gray-300 px-4 py-2'>Username</th>
                <th className='border border-gray-300 px-4 py-2'>Email</th>
                <th className='border border-gray-300 px-4 py-2'>Phone No</th>
                <th className='border border-gray-300 px-4 py-2'>Actions</th>
              </tr>
            </thead>
            <tbody>
              {users.map((user, index) => (
                <tr key={index} className='text-center'>
                  <td className='border border-gray-300 px-4 py-2'>{index + 1}</td>
                  <td className='border border-gray-300 px-4 py-2'>{user.username}</td>
                  <td className='border border-gray-300 px-4 py-2'>{user.email}</td>
                  <td className='border border-gray-300 px-4 py-2'>{user.phoneNo}</td>
                  <td className='border border-gray-300 px-4 py-2 space-x-4'>
                    <button className='px-4 py-2 bg-blue-400 rounded-lg text-center' onClick={()=>handleEdit(index)}>edit</button>
                    <button className='p-2 bg-red-400 rounded-lg' onClick={()=>handleDelete(index)}>delete</button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        ) : (
          <p className='text-center text-gray-500'>No users</p>
        )}
      </div>
    </div>
  );
};

export default Home;
