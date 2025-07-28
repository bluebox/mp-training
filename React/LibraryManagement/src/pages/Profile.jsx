import React, { useContext, useState } from 'react';
import { UserContext } from '../context/UserContext';
import axios from 'axios';

const Profile = () => {
  const { user, setUser } = useContext(UserContext);
  const [isEditing, setIsEditing] = useState(false);
  const [formData, setFormData] = useState({
    email: user?.email || '',
    address: user?.address || '',
    gender: user?.gender || '',
    mobile: user?.mobile || '',
  });
  const token =localStorage.getItem('access_token')
  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  const HandleEdit = async() => {
      try{
          const res=await axios.patch(`http://127.0.0.1:8000/api/member/crud/${user.id}/`,formData,{
            withCredentials:true,
            headers:{
               'Authorization':`Bearer ${token}`
            }
          })
          setUser({ ...user, ...formData });
          alert('Profile updated successfully!');
      }
      catch(err){
           const error=err.response.data
           const msg=Object.values(error).flat().join('\n')
           alert(msg)
      }
      finally{
          setIsEditing(false);
      }
  };

  const closeDialog = () => setIsEditing(false);

  if (!user) {
    return <div>Loading...</div>;
  }

  return (
    <div className="max-w-4xl mx-auto mt-10 p-6 bg-white shadow-lg rounded-lg border">
      <h2 className="text-2xl font-bold mb-6 border-b pb-2 text-blue-600">Profile</h2>
      
        <div>
          <h3 className="text-xl font-semibold text-blue-600">{user?.email}</h3>
        </div>

      <div className="space-y-6">
        <ul className="text-gray-700 space-y-4">
          <li><strong>ID:</strong> {user.id}</li>
          <li><strong>Address:</strong> {user.address}</li>
          <li><strong>Gender:</strong> {user.gender}</li>
          <li><strong>Mobile:</strong> {user.mobile}</li>
          <li><strong>Admin Status:</strong> {user.is_staff ? 'Admin' : 'User'}</li>
        </ul>
        
        <div className="flex justify-between items-center">
          <button
            onClick={() => setIsEditing(true)}
            className="px-4 py-2 bg-blue-600 text-white rounded hover:bg-blue-700"
          >
            Edit Details
          </button>
        </div>
      </div>

      {isEditing && (
        <div className="fixed inset-0 flex justify-center items-center z-50 bg-black bg-opacity-50">
          <div className="bg-white p-8 rounded-lg w-96 shadow-lg md:w-full md:max-w-2xl">
            <h3 className="text-2xl font-semibold mb-4 text-blue-600">Edit Profile</h3>
            <div className="space-y-4">
              <div>
                <label htmlFor="email" className="block text-gray-700">Email</label>
                <input
                  type="email"
                  id="email"
                  name="email"
                  value={formData.email}
                  onChange={handleChange}
                  className="w-full border border-gray-300 p-2 rounded"
                />
              </div>
              <div>
                <label htmlFor="address" className="block text-gray-700">Address</label>
                <textarea
                  id="address"
                  name="address"
                  value={formData.address}
                  onChange={handleChange}
                  className="w-full border border-gray-300 p-2 rounded"
                />
              </div>
              <div>
                <label htmlFor="gender" className="block text-gray-700">Gender</label>
                <select
                  id="gender"
                  name="gender"
                  value={formData.gender}
                  onChange={handleChange}
                  className="w-full border border-gray-300 p-2 rounded"
                >
                  <option value="Male">Male</option>
                  <option value="Female">Female</option>
                  <option value="Other">Other</option>
                </select>
              </div>
              <div>
                <label htmlFor="mobile" className="block text-gray-700">Mobile</label>
                <input
                  type="text"
                  id="mobile"
                  name="mobile"
                  value={formData.mobile}
                  onChange={handleChange}
                  className="w-full border border-gray-300 p-2 rounded"
                />
              </div>

              <div className="flex justify-between mt-4">
                <button
                  onClick={HandleEdit}
                  className="px-4 py-2 bg-blue-600 text-white rounded hover:bg-blue-700"
                >
                  submit
                </button>
                <button
                  onClick={closeDialog}
                  className="px-4 py-2 bg-gray-600 text-white rounded hover:bg-gray-700"
                >
                  Cancel
                </button>
              </div>
            </div>
          </div>
        </div>
      )}
    </div>
  );
};

export default Profile;
