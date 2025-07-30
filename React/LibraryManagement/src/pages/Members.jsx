import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { useContext } from 'react';
import { UserContext } from '../context/UserContext';
import { Formik,Form,Field,ErrorMessage } from 'formik';
import * as Yup from 'yup'

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
    password: 123123
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
    setFormData({...formData,
      email: member.email ,
      address: member.address ,
      mobile: member.mobile,
      gender: member.gender,
    });
    setEditDialog(true);
  };

  const handleEditSubmit = async(values,{setSubmitting,resetForm}) => {
    if(!window.confirm('Do you want to continue Edit')){
      return
    }
    setSubmitting(true)
    try{
        if(JSON.stringify(values)===JSON.stringify(formData)){
          alert('Nothing to update')
          return
        }
        await axios.patch(`http://127.0.0.1:8000/api/member/crud/${currentMemberId}/`, values,{
        withCredentials:true,
         headers: {
         'Authorization': `Bearer ${token}`
         }
       })
        fetchMembers();
        resetForm()
        alert('Data updated')
    }
    catch(err ) {
       const errors = err.response.data;
        const messages = Object.values(errors).flat().join('\n');
        alert(messages);
      }
      finally{
        setSubmitting(false)
        setEditDialog(false);
      }
  };

  const openAddDialog = () => {
    setShowDialog(true);
  };

  const handleSubmit = async(values,{setSubmitting,resetForm}) => {
    if(!window.confirm('Do you want to Add this user')){
      return
    }
    setSubmitting(true)
    try{
        const res=await axios.post('http://127.0.0.1:8000/api/member/crud/', values,{
        withCredentials:true,
         headers: {
         'Authorization': `Bearer ${token}`
         }
       })
      setMembers([...members, res.data]);
      alert('user added successfully')
      resetForm();
    }
    catch(err){
        const error = err.response.data;
        const msg = Object.values(error).flat().join('\n');
        alert(msg);
    }
    finally{
      setShowDialog(false);
      setSubmitting(false)
    }
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

      {(showDialog || editDialog) && (
        <div className="fixed inset-0 bg-black bg-opacity-40 flex items-center justify-center z-50 p-4">
          <div className="bg-white w-full max-w-2xl rounded-lg shadow-lg p-6">
            <h2 className="text-2xl font-bold mb-4 text-gray-700">Add New Member</h2>
            <Formik
               initialValues={{
                email:editDialog ? formData.email:'',
                address:editDialog?formData.address:'',
                mobile:editDialog?formData.mobile:'',
                gender:editDialog?formData.gender:'',
                password:123123
               }}
               validationSchema={Yup.object({
                 email:Yup.string().matches(/^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/,'Invalid email address').required('Email is required'),
                 address:Yup.string().required('Address Required'),
                 mobile:Yup.string().matches(/^[6-9]\d{9}$/,'Enter valid Mobile Number').required('Mobile Number is Required'),
                 gender:Yup.string().required('Gender required')
               })}
               onSubmit={editDialog?handleEditSubmit:handleSubmit}
            >
            <Form className="space-y-4">
              <div>
                 <Field type="email" name="email" placeholder="Email" required className="w-full border border-gray-300 rounded px-3 py-2" />
                 <ErrorMessage name="email" component='div' className='text-red-500'/>
              </div>
              <div>
                <Field type="text" name="address" placeholder="Address" className="w-full border border-gray-300 rounded px-3 py-2" />
                 <ErrorMessage name="address" component='div' className='text-red-500'/>
              </div>
              <div>
                <Field type="text" name="mobile" placeholder="Mobile"  maxLength={10} className="w-full border border-gray-300 rounded px-3 py-2" />
                 <ErrorMessage name="mobile" component="div" className="text-red-500" />
              </div>
               <div>
                <Field
                  name="gender"
                  as='select'
                  className="w-full border border-gray-300 rounded px-3 py-2"
                >
                  <option value="">Select Gender</option>
                  <option value="male">male</option>
                  <option value="female">female</option>
                  <option value="other">other</option>
                </Field>  
                 <ErrorMessage name="gender" component="div" className="text-red-500" />
               </div>
              <div className="flex justify-end gap-2">
                <button type="button" onClick={()=>{
                  if(showDialog)
                    setShowDialog(false)
                  if(editDialog)
                    setEditDialog(false)
                }} className="px-4 py-2 bg-gray-300 rounded hover:bg-gray-400">Cancel</button>
                <button type="submit" className="px-4 py-2 bg-blue-500 text-white rounded hover:bg-blue-600">{editDialog?'Edit':'Add'}</button>
              </div>
            </Form>
            </Formik>
          </div>
        </div>
      )}
    </div>
  );
};

export default Members;
