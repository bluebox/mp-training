import React, { useEffect, useState } from 'react';
import Axios from '../utils/Axios';
import { Formik,Form,Field,ErrorMessage } from 'formik';
import * as Yup from 'yup'
import { FcPrevious,FcNext } from "react-icons/fc";

const Issues = () => {
  const [issues, setIssues] = useState([]);
  const [showDialog, setShowDialog] = useState(false);
  const token=localStorage.getItem('access_token')
  const [page,setPage]=useState(1)
  const [pagination,setPagination]=useState({
    count:'',
    next:null,
    prev:null
  })
  const fetchIssues = async (page) => {
    try {
      const res = await Axios.get(`issue/allrecords/?page=${page}`);
      setIssues(res.data.results);
      setPagination(prevPagination => ({
      count: res.data.count,
      next: res.data.next,
      prev: res.data.previous
    }));
    } catch (err) {
       const errors = err.response.data;
        const messages = Object.values(errors).flat().join('\n');
        alert(messages);
    }
  };

  useEffect(() => {
    fetchIssues(page);
  }, [page]);

 
  const handleSubmit = async (values,{setSubmitting,resetForm}) => {
    if(!window.confirm('Do you want to issue this book')){
      return 
    }
    setSubmitting(true)
    try {
      await Axios.post('issue/issuebook/', values);
      fetchIssues(page);
      resetForm()
      alert('issued Added')
    } catch (err) {
        const errors = err.response.data;
        const messages = Object.values(errors).flat().join('\n');
        alert(messages);
    }
    finally{
        setShowDialog(false);
        setSubmitting(false)
    }
  };

  const handleReturn = async (id) => {
    if(!window.confirm('Do you want to return ')){
      return 
    }
    const today = new Date().toISOString().split('T')[0];
    console.log(today);
    try {
      await Axios.patch(`issue/returnbook/${id}/`,{});
      fetchIssues(page);
      alert('Book returned')
    } catch (err) {
        const errors = err.response.data;
        const messages = Object.values(errors).flat().join('\n');
        alert(messages);
    }
  };

  return (
    <div className="p-6 text-gray-800">
      <div className="flex justify-between items-center mb-4">
        <h1 className="text-2xl font-bold">Issued Books</h1>
        <button
          onClick={() => setShowDialog(true)}
          className="bg-blue-600 text-white px-4 py-2 rounded hover:bg-blue-700"
        >
          Issue Book
        </button>
      </div>

      <div className="overflow-x-auto">
        <table className="w-full table-auto border border-gray-300">
          <thead className="bg-gray-200">
            <tr>
              <th className="px-4 py-2 border-b">Issue ID</th>
              <th className="px-4 py-2 border-b">Book ID</th>
              <th className="px-4 py-2 border-b">Member ID</th>
              <th className="px-4 py-2 border-b">Status</th>
              <th className="px-4 py-2 border-b">Issue Date</th>
              <th className="px-4 py-2 border-b">Return Date</th>
              <th className="px-4 py-2 border-b">Return</th>
            </tr>
          </thead>
          <tbody>
            {issues.map((issue) => (
              <tr  key={issue.id}className='odd:bg-white even:bg-gray-100'>
                <td className="px-4 py-2 border-b text-center">{issue.id}</td>
                <td className="px-4 py-2 border-b text-center">{issue.book}</td>
                <td className="px-4 py-2 border-b text-center">{issue.member}</td>
                <td className="px-4 py-2 border-b text-center">{issue.status}</td>
                <td className="px-4 py-2 border-b text-center">{issue.issueDate}</td>
                <td className="px-4 py-2 border-b text-center">{issue.returnDate}</td>
                <td className="px-4 py-2 border-b text-center">
                  {issue.status === 'I' ? (
                    <button
                      onClick={() => handleReturn(issue.id)}
                      className="bg-green-600 text-white px-3 py-1 rounded hover:bg-green-700"
                    >
                      Return
                    </button>
                  ) : (
                    <span className="text-red-500">Returned</span>
                  )}
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

      {showDialog && (
        <div className="fixed inset-0 bg-black bg-opacity-40 flex items-center justify-center z-50 p-4">
          <div className="bg-white w-auto md:w-full md:max-w-xl rounded-lg shadow-lg p-6">
            <h2 className="text-xl font-bold mb-4 text-gray-700">Add Issue Record</h2>
            <Formik
            initialValues={{
              book:'',
              member:''
            }}
            validationSchema={Yup.object({
              book:Yup.string().matches(/^[+]?\d*\.?\d+$/,'Positive Number Only Allowed').required('BookId is required'),
              member:Yup.string().matches(/^[+]?\d*\.?\d+$/,'Positive Number Only Allowed').required('MemberId is required'),
            })
            }
            onSubmit={handleSubmit}
            >
            <Form  className="space-y-4">
              <div>
                <Field
                  type="number"
                  name="book"
                  placeholder="Book ID"
                  required
                  className="w-full border border-gray-300 rounded px-3 py-2"
                />
                <ErrorMessage name='book' component='div' className='text-red-500'/>
              </div>
              <div>
                 <Field
                  type="number"
                  name="member"
                  placeholder="Member ID"
                  required
                  className="w-full border border-gray-300 rounded px-3 py-2"
                />
                <ErrorMessage name='member' component='div' className='text-red-500'/>
              </div>
              <div className="flex justify-end gap-2">
                <button
                  type="button"
                  onClick={() => setShowDialog(false)}
                  className="px-4 py-2 bg-gray-300 rounded hover:bg-gray-400"
                >
                  Cancel
                </button>
                <button
                  type="submit"
                  className="px-4 py-2 bg-blue-500 text-white rounded hover:bg-blue-600"
                >
                  Add
                </button>
              </div>
            </Form>
            </Formik>
          </div>
        </div>
      )}
    </div>
  );
};

export default Issues;
