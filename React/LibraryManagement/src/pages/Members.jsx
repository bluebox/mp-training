import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { useContext } from 'react';
import { UserContext } from '../context/UserContext';

const Members = () => {
  const {user}=useContext(UserContext)
  const [members, setMembers] = useState([]);
  const [loading, setLoading] = useState(true);
  const [showDialog, setShowDialog] = useState(false);
  const [editDialog, setEditDialog] = useState(false);
  const [currentMemberId, setCurrentMemberId] = useState(null);
  const [formData, setFormData] = useState({
    email: '',
    address: '',
    mobile: '',
    gender: '',
    password: ''
  });
  const token=localStorage.getItem('access_token')
  const fetchMembers = () => {
    axios.get('http://127.0.0.1:8000/api/member/crud/',{
        withCredentials:true,
         headers: {
         'Authorization': `Bearer ${token}`
         } 
      })
      .then(res => {
        setMembers(res.data);
      })
      .catch(err => {
        alert("Error fetching members.");
        console.error(err);
      })
      .finally(() => {
        setLoading(false);
      });
  };

  useEffect(() => {
    fetchMembers();
  }, []);

  const deleteMember = (id) => {
    if (!window.confirm("Are you sure you want to delete this member?")) return;
    axios.delete(`http://127.0.0.1:8000/api/member/crud/${id}/`,{
        withCredentials:true,
         headers: {
         'Authorization': `Bearer ${token}`
         }
      })
      .then(() => {
        setMembers(members.filter((m) => m.id !== id));
      })
      .catch(err => {
        const errors = err.response.data;
        const messages = Object.values(errors).flat().join('\n');
        alert(messages);
      });
  };

  const openEditDialog = (member) => {
    setCurrentMemberId(member.id);
    setFormData({
      email: member.email || '',
      address: member.address || '',
      mobile: member.mobile || '',
      gender: member.gender || '',
      password: ''
    });
    setEditDialog(true);
  };

  const handleEditSubmit = (e) => {
    e.preventDefault();
    if(!window.confirm('Do you want to continue Edit')){
      return
    }
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if(!formData.email && !emailRegex.test(formData.email)){
        alert('enter valid email')
        return 
    }
    const phoneRegex = /^[6-9]\d{9}$/;
    if (formData.mobile && !phoneRegex.test(formData.mobile)) {
      alert('Please enter a valid 10-digit mobile number starting with 6-9.');
      return;
    }
    const updatedData = { ...formData };
    if (!updatedData.password) delete updatedData.password;
    axios.patch(`http://127.0.0.1:8000/api/member/crud/${currentMemberId}/`, updatedData,{
        withCredentials:true,
         headers: {
         'Authorization': `Bearer ${token}`
         }
      })
      .then(() => {
        fetchMembers();
        setEditDialog(false);
      })
      .catch(err => {
       const errors = err.response.data;
        const messages = Object.values(errors).flat().join('\n');
        alert(messages);
      });
  };

  const openAddDialog = () => {
    setFormData({
      email: '',
      address: '',
      mobile: '',
      gender: '',
      password: 123123
    });
    setShowDialog(true);
  };

  const handleInputChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    if(!window.confirm('Do you want to Add this user')){
      return
    }
    const phoneRegex = /^[6-9]\d{9}$/;
    if (formData.mobile && !phoneRegex.test(formData.mobile)) {
      alert('Please enter a valid 10-digit mobile number starting with 6-9.');
      return;
    }
    axios.post('http://127.0.0.1:8000/api/member/crud/', formData,{
        withCredentials:true,
         headers: {
         'Authorization': `Bearer ${token}`
         }
      })
      .then(res => {
        setMembers([...members, res.data]);
        alert('user added successfully')
      })
      .catch(err => {
        const errors = err.response.data;
        const messages = Object.values(errors).flat().join('\n');
        alert(messages);
      });
      setShowDialog(false);

  };

  useEffect(() => {
    fetchMembers();
  }, []);
  
  const shortAddress=(s)=>{
    return s.length>20?s.slice(0,20)+"...":s
  }
  if (loading) return <div className="text-center mt-10 text-gray-600">Loading members...</div>;

  return (
    <div className="p-6 overflow-x-auto">
      <div className='flex justify-between mb-4'>
        <h1 className="text-2xl font-bold text-gray-800">Members List</h1>
        <button className='px-3 py-2 bg-blue-500 text-white rounded-lg hover:bg-blue-600' onClick={openAddDialog}>
          Add Member
        </button>
      </div>

      {members.length === 0 ? (
        <p className="text-gray-500">No members found.</p>
      ) : (
        <table className="min-w-full bg-white border border-gray-300 rounded-lg shadow">
          <thead className="bg-gray-200">
            <tr>
              <th className="py-2 px-4 border-b text-left">ID</th>
              <th className="py-2 px-4 border-b text-left">Email</th>
              <th className="py-2 px-4 border-b text-left">Address</th>
              <th className="py-2 px-4 border-b text-left">Mobile</th>
              <th className="py-2 px-4 border-b text-left">Gender</th>
              <th className="py-2 px-4 border-b text-left">Actions</th>
            </tr>
          </thead>
          <tbody>
            {members.map((member) => (
              <tr key={member.id} className="odd:bg-white even:bg-gray-100">
                <td className="py-2 px-4 border-b">{member.id}</td>
                <td className="py-2 px-4 border-b">{member.email}</td>
                <td className="py-2 px-4 border-b">{shortAddress(member.address || '-')}</td>
                <td className="py-2 px-4 border-b">{member.mobile || '-'}</td>
                <td className="py-2 px-4 border-b">{member.gender || '-'}</td>
                <td className="py-2 px-4 border-b space-x-2">
                  <button
                    onClick={() => openEditDialog(member)}
                    className="bg-blue-500 text-white px-3 py-1 rounded hover:bg-blue-600"
                  >
                    Edit
                  </button>
                  {user.id!=member.id &&(
                    <button
                    onClick={() => deleteMember(member.id)}
                    className="bg-red-500 text-white px-3 py-1 rounded hover:bg-red-600"
                    >
                      Delete
                    </button>
                  )}
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      )}

      {showDialog && (
        <div className="fixed inset-0 bg-black bg-opacity-40 flex items-center justify-center z-50 p-4">
          <div className="bg-white w-full max-w-2xl rounded-lg shadow-lg p-6">
            <h2 className="text-2xl font-bold mb-4 text-gray-700">Add New Member</h2>
            <form onSubmit={handleSubmit} className="space-y-4">
              <input type="email" name="email" placeholder="Email" value={formData.email} onChange={handleInputChange} required className="w-full border border-gray-300 rounded px-3 py-2" />
              <input type="text" name="address" placeholder="Address" value={formData.address} onChange={handleInputChange} className="w-full border border-gray-300 rounded px-3 py-2" />
              <input type="text" name="mobile" placeholder="Mobile" value={formData.mobile} onChange={handleInputChange} maxLength={10} className="w-full border border-gray-300 rounded px-3 py-2" />
              <select
              name="gender"
              value={formData.gender}
              onChange={handleInputChange}
              className="w-full border border-gray-300 rounded px-3 py-2"
            >
              <option value="">Select Gender</option>
              <option value="male">male</option>
              <option value="female">female</option>
              <option value="other">other</option>
            </select>
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
            <h2 className="text-2xl font-bold mb-4 text-gray-700">Edit Member</h2>
            <form onSubmit={handleEditSubmit} className="space-y-4">
              <input type="email" name="email" placeholder="Email" value={formData.email} onChange={handleInputChange} required className="w-full border border-gray-300 rounded px-3 py-2" />
              <input type="text" name="address" placeholder="Address" value={formData.address} onChange={handleInputChange} className="w-full border border-gray-300 rounded px-3 py-2" />
              <input type="text" name="mobile" placeholder="Mobile" value={formData.mobile} onChange={handleInputChange} className="w-full border border-gray-300 rounded px-3 py-2" />
              <select
                name="gender"
                value={formData.gender}
                onChange={handleInputChange}
                className="w-full border border-gray-300 rounded px-3 py-2"
              >
                <option value="">Select Gender</option>
                <option value="male">male</option>
                <option value="female">female</option>
                <option value="other">other</option>
              </select>
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

export default Members;
