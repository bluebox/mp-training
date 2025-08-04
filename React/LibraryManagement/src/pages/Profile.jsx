import { useContext, useState } from 'react';
import { UserContext } from '../context/UserContext';
import { Formik,Form,Field,ErrorMessage } from 'formik';
import * as Yup from 'yup'
import Axios from '../utils/Axios';
import { useDispatch, useSelector } from 'react-redux';
import { setIsAuthenticated,setuser} from '../store/slices/AuthSlice';

const Profile = () => {
  const { user, setUser } = useContext(UserContext);
  const [isEditing, setIsEditing] = useState(false);
  const isAuthenticated=useSelector((state)=>state.auth.isAuthenticated)
  // console.log(isAuthenticated);
  // const dispatch=useDispatch()
  // dispatch(setuser(user))
  // const User=useSelector((state)=>state.auth.user)
  // console.log(User);
  if(!user){
    <div>Loading...</div>
    return
  }
  const token =localStorage.getItem('access_token')
  const HandleEdit = async(values,{setSubmitting,resetForm}) => {
      if(!window.confirm('Do want to Update')){
        return 
      }
      setSubmitting(true)
      try{
          const val={ 
            email:user?.email,
            address:user?.address,
            gender:user?.gender,
            mobile:user?.mobile
          }
          if(JSON.stringify(val)===JSON.stringify(values)){
            alert('Nothing to update')
            return 
          }
          await Axios.patch(`member/crud/${user.id}/`,values)
          setUser({ ...user, ...values });
          alert('Profile updated successfully!')
          resetForm()
      }
      catch(err){
           const error=err.response.data
           const msg=Object.values(error).flat().join('\n')
           alert(msg)
      }
      finally{
          setIsEditing(false);
          setSubmitting(false)
      }
  };
  const closeDialog = () => setIsEditing(false);
  const shortAddress=(s)=>{
    return s.length>20?s.slice(0,20)+"...":s;
  }
  return (
    <div className="max-w-4xl mx-auto mt-10 p-6 bg-white shadow-lg rounded-lg border">
      <h2 className="text-center text-2xl font-bold mb-6 border-b pb-2 text-blue-600">Profile</h2>
      
        <div>
          <h3 className="text-xl font-semibold text-blue-600">{user?.email}</h3>
        </div>

      <div className="space-y-6 mt-2">
        <ul className="text-gray-700 space-y-4">
          <li><strong>ID:</strong> {user.id}</li>
          <li><strong>Address:</strong> {shortAddress(user.address || '-')}</li>
          <li><strong>Gender:</strong> {user.gender}</li>
          <li><strong>Mobile:</strong> {user.mobile}</li>
          <li><strong>Admin Status:</strong> {user.is_admin ? 'Admin' : 'User'}</li>
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

          <Formik
            initialValues={{
              email:user?.email,
              address:user?.address,
              gender:user?.gender,
              mobile:user?.mobile
            }}
            validationSchema={Yup.object({
              email:Yup.string().matches(/^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/,'Invalid email address').required('Email is required'),
              address:Yup.string().required('Address is required'),
              gender:Yup.string().required("Gender is required"),
              mobile:Yup.string().matches(/^[6-9]\d{9}$/,'Enter valid Mobile Number').required('Mobile Number is Required')
            })}
            onSubmit={HandleEdit}
          >
          <Form className="bg-white p-8 rounded-lg shadow-lg w-auto md:w-full md:max-w-2xl">
             <h3 className="text-2xl font-semibold mb-4 text-blue-600 text-center">Edit Profile</h3>
            <div className="space-y-4">
              <div>
                <label htmlFor="email" className="block text-gray-700">Email</label>
                <Field
                  type="email"
                  id="email"
                  name="email"
                  className="w-full border border-gray-300 p-2 rounded"
                />
                <ErrorMessage name='email' component='div' className='text-red-500'/>
              </div>
              <div>
                <label htmlFor="address" className="block text-gray-700">Address</label>
                <Field
                  id="address"
                  name="address"
                  as='textarea'
                  className="w-full border border-gray-300 p-2 rounded"
                />
                <ErrorMessage name='address' component='div' className='text-red-500'/>
              </div>
              <div>
                <label htmlFor="gender" className="block text-gray-700">Gender</label>
                <Field
                  id="gender"
                  name="gender"
                  as='select'
                  className="w-full border border-gray-300 p-2 rounded"
                >
                  <option value="">Select Gender</option>
                  <option value="male">Male</option>
                  <option value="female">Female</option>
                  <option value="other">Other</option>
                </Field>
                <ErrorMessage name='gender' component='div' className='text-red-500'/>
              </div>
              <div>
                <label htmlFor="mobile" className="block text-gray-700">Mobile</label>
                <Field
                  type="text"
                  id="mobile"
                  name="mobile"
                  className="w-full border border-gray-300 p-2 rounded"
                  maxLength={10}
                />
                <ErrorMessage name='mobile' component='div' className='text-red-500'/>
              </div>

              <div className="flex justify-end space-x-4 mt-4">
                <button
                  onClick={closeDialog}
                  className="px-4 py-2 bg-gray-600 text-white rounded hover:bg-gray-700"
                >
                  Cancel
                </button>
                <button   
                  type='submit'
                  className="px-4 py-2 bg-blue-600 text-white rounded hover:bg-blue-700"
                >
                  submit
                </button>
              </div>
            </div>
          </Form>
          </Formik>
        </div>
      )}
    </div>
  );
};

export default Profile;
