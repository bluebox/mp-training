import React, { useEffect, useState } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import {Formik,Form,Field, ErrorMessage} from 'formik'
import * as Yup from 'yup';
import Axios from '../utils/Axios';
import { FcPrevious,FcNext } from "react-icons/fc";

const Books = () => {
  const navigate=useNavigate()
  const location=useLocation()
  const [books, setBooks] = useState([]);
  const [loading, setLoading] = useState(true);
  const [showDialog, setShowDialog] = useState(false);
  const [editDialog, setEditDialog] = useState(false);
  const [currentBookId, setCurrentBookId] = useState(null);
  const [editData, setEditData] = useState({
    title:'',
    author:'',
    category:'',
    status: true,
    availablity: true
  });
  const [page,setPage]=useState(1)
  const [pagination,setPagination]=useState({
    count:'',
    next:null,
    prev:null
  })
  const [text,setText]=useState('')
  const token=localStorage.getItem('access_token')
  const fetchBooks = async (page) => {
    try {
      var res=null
      if(text.length==0){
         res =await Axios.get(`book/crud/?page=${page}`);
      }
      else{
         res=await Axios.get(`book/${text}/?page=${page}`)
      }
      setBooks(res.data.results);
      setPagination(prevPagination => ({
        count: res.data.count,
        next: res.data.next,
        prev: res.data.previous
      }));
      console.log(res.data.next,res.data.previous);
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
      await Axios.delete(`book/crud/${id}/`);
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
      title:book.title,
      author:book.author,
      category:book.category,
      status: book.status,
      availablity: book.availablity
    });
    setEditDialog(true);
  };

  const handleEditSubmit = async (values,{setSubmitting,resetForm}) => {
    if(!window.confirm('Do you want to update')){
      return 
    }
    setSubmitting(true)
    try {
      if(JSON.stringify(values)===JSON.stringify(editData)){
        alert('Nothing updated')
        return 
      }
      await Axios.patch(`book/crud/${currentBookId}/`, values);
      fetchBooks(page);
      alert('successfully updated')
      resetForm()
    } catch (err) {
        const errors = err.response.data;
        const messages = Object.values(errors).flat().join('\n');
        alert(messages);
    }
    finally{
         setEditDialog(false);
         setSubmitting(false)
    }
  };

  const OpenDialog = () => {
    setShowDialog(true);
  };
  const handleSubmit=async (values,{setSubmitting,resetForm})=>{
      setSubmitting(true)
      if(!window.confirm('Do you want to add book')){
        setSubmitting(false)
        return 
      }
      try{
           const res = await Axios.post('book/crud/', values);
          // setBooks([...books, res.data]);
          setShowDialog(false);
          resetForm()
          alert('Book Added Successfully')
      }
      catch(err){
        const error=err.response.data
        const msg=Object.values(error).flat().join('\n')
        alert(msg)
      }
      finally{
        setSubmitting(false)
      }
  }
  const handleDetails=(id)=>{
    navigate(`${location.pathname}/${id}`)
  }
  useEffect(() => {
    fetchBooks(page);
  }, [page,text]);
  
  if (loading) return <div className="text-center mt-10 text-gray-600">Loading books...</div>;

  return (
    <div className="p-6">
      <div className='flex justify-between items-center mb-4'>
        <h1 className="text-2xl font-bold text-gray-800">Books List</h1>
        <div className='space-x-3'>
          <input type="text" className='border border-gray-300 py-2 px-3 rounded-2xl' placeholder='Search' value={text} onChange={(e)=>{
            setText(e.target.value)
          }}/>
          <button className='px-3 py-2 bg-blue-500 text-white rounded-lg hover:bg-blue-600' onClick={OpenDialog}>
            Add Book
          </button>
        </div>      
      </div>

      {books.length === 0 ? (
        <p className="text-gray-500">No books found.</p>
      ) : (
        <div className='mb-10 overflow-x-auto'>
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
                    className="bg-blue-500 text-white px-3 py-1 rounded hover:bg-blue-600 m-2"
                  >
                    Edit
                  </button>
                  <button
                    onClick={() => deleteBook(book.id)}
                    className="bg-red-500 text-white px-3 py-1 rounded hover:bg-red-600 m-1"
                  >
                    Delete
                  </button>
                   <button
                    onClick={() => handleDetails(book.id)}
                    className="bg-red-500 text-white px-3 py-1 rounded hover:bg-red-600 m-1"
                  >
                    Details
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
        <div className='flex justify-end m-3 space-x-2'>
           <h1 className='mt-1 font-bold'>Total Records {pagination.count}</h1>
           <button onClick={()=>{setPage(page-1)}} disabled={!pagination.prev}className={`text-2xl ${pagination.prev?null:'cursor-not-allowed'}`}><FcPrevious /></button>
            <h1 className='text-2xl'>{page}</h1>
           <button onClick={()=>setPage(page+1)} disabled={!pagination.next} className={`text-2xl ${pagination.next?null:'cursor-not-allowed'}`}><FcNext /></button>
        </div>
        </div>
      )}

      {showDialog && (
        <div className="fixed inset-0 bg-black bg-opacity-40 flex items-center justify-center z-50 p-4">
          <div className="bg-white w-auto md:w-full md:max-w-2xl rounded-lg shadow-lg p-6">
            <h2 className="text-2xl font-bold mb-4 text-gray-700">Add New Book</h2>
           <Formik 
            initialValues={{
              title: '',
              author:'',
              category:''
            }}
            validationSchema={Yup.object({
              title: Yup.string().required('Enter title'),
              author: Yup.string().required('Enter author'),
              category: Yup.string().required('Enter category')
            })}
            onSubmit={handleSubmit}
          >
            {(formik) => (
              <form onSubmit={formik.handleSubmit}>
                <div className='m-2'>
                    <input
                    type="text"
                    name="title"
                    id="title"
                    className="w-full border border-gray-300 rounded-lg px-2 py-1"
                    placeholder='Title'
                    {...formik.getFieldProps('title')}
                   />
                  {formik.touched.title && formik.errors.title ? (
                    <div className="text-red-600">{formik.errors.title}</div>
                  ) : null}
                </div>
                <div className='m-2'>
                    <input
                    type="text"
                    name="author"
                    id="author"
                    className="w-full border border-gray-300 rounded-lg px-2 py-1"
                    placeholder='Author'
                    {...formik.getFieldProps('author')}
                   />
                  {formik.touched.author && formik.errors.author ? (
                    <div className="text-red-600">{formik.errors.author}</div>
                  ) : null}
                </div>
                <div className='m-2'>
                    <input
                    type="text"
                    name="category"
                    id="category"
                    className="w-full border border-gray-300 rounded-lg px-2 py-1"
                    placeholder='category'
                    {...formik.getFieldProps('category')}
                   />
                  {formik.touched.category && formik.errors.category ? (
                    <div className="text-red-600">{formik.errors.category}</div>
                  ) : null}
                </div>
                <div className='flex justify-end space-x-4'>
                    <button type="button" className="bg-gray-500 px-3 py-2 rounded-lg" onClick={()=>(setShowDialog(false))}>Cancel</button>
                   <button type="submit" className="bg-blue-500 px-3 py-2 rounded-lg" disabled={formik.isSubmitting}>Add</button>
                </div>
              </form>
            )}
          </Formik>
          </div>
        </div>
      )}

      {editDialog && (
        <div className="fixed inset-0 bg-black bg-opacity-40 flex items-center justify-center z-50 p-4">
          <div className="bg-white w-auto md:w-full md:max-w-2xl rounded-lg shadow-lg p-6">
            <h2 className="text-2xl font-bold mb-4 text-gray-700">Edit Book</h2>
             <Formik
                initialValues={{
                  title:editData.title,
                  author:editData.author,
                  category:editData.category,
                  status:editData.status,
                  availablity:editData.availablity
                }}
                validationSchema={Yup.object({
                   title:Yup.string().required('Title is required'),
                  author:Yup.string().required('Authro is required'),
                  category:Yup.string().required('Category is required'),
                  status:Yup.string().required('Status is required'),
                  availablity:Yup.string().required('Availability is required'),
                })}
                onSubmit={handleEditSubmit}
             >
              <Form className="space-y-4">
              <div>
                <label htmlFor="title" className='block '>Title<span className='text-red-700'> *</span></label>
                <Field name='title'type="text"  className="w-full rounded-lg border py-2 mt-1 p-2"/>
                <ErrorMessage name='title' component='p' className='text-red-500'/>
              </div>
               <div>
                <label htmlFor="author" className='block '>Author <span className='text-red-700'> *</span></label>
                <Field type="text" name='author'className="w-full rounded-lg border py-2 mt-1 p-2"id='author' />
                <ErrorMessage name='author' component='p' className='text-red-500'/>
                </div>
               <div>
                <label htmlFor="category" className='block'>Category <span className='text-red-700'> *</span></label>
                <Field name='category' type="text" className="w-full rounded-lg border py-2 mt-1 p-2"id='category'/>
                <ErrorMessage name='category' component='p' className='text-red-500'/>
              </div>
              <div>
                <label className="block mb-1">Status <span className='text-red-700'> *</span></label>
                <Field name="status" as='select' className="w-full border border-gray-300 rounded px-3 py-2">
                  <option value={true}>Active</option>
                  <option value={false}>Inactive</option>
                </Field>
                <ErrorMessage name='status' component='p' className='text-red-500'/>
              </div>
              <div>
                <label className="block mb-1">Availability <span className='text-red-700'> *</span></label>
                <Field name="availablity" as='select' className="w-full border border-gray-300 rounded px-3 py-2">
                  <option value={true}>Available</option>
                  <option value={false}>Unavailable</option>
                </Field>
                <ErrorMessage name='availablity' component='p' className='text-red-500'/>
              </div>
              <div className="flex justify-end gap-2">
                <button type="button" onClick={() => setEditDialog(false)} className="px-4 py-2 bg-gray-300 rounded hover:bg-gray-400">Cancel</button>
                <button type="submit" className="px-4 py-2 bg-green-500 text-white rounded hover:bg-green-600">Update</button>
              </div>
              </Form> 
             </Formik>
          </div>
        </div>
      )}
    </div>
  );
};

export default Books;
