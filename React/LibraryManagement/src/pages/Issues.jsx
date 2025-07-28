import React, { useEffect, useState } from 'react';
import axios from 'axios';

const Issues = () => {
  const [issues, setIssues] = useState([]);
  const [showDialog, setShowDialog] = useState(false);
  const [formData, setFormData] = useState({
    book: '',
    member: '',
  });
  const token=localStorage.getItem('access_token')
  const fetchIssues = async () => {
    try {
      const res = await axios.get('http://127.0.0.1:8000/api/issue/allrecords/',{
        withCredentials:true,
         headers: {
         'Authorization': `Bearer ${token}`
         }
      });
      setIssues(res.data);
    } catch (err) {
       const errors = err.response.data;
        const messages = Object.values(errors).flat().join('\n');
        alert(messages);
    }
  };

  useEffect(() => {
    fetchIssues();
    console.log(issues);
  }, []);

  const handleChange = (e) => {
    setFormData((prev) => ({
      ...prev,
      [e.target.name]: e.target.value,
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await axios.post('http://127.0.0.1:8000/api/issue/issuebook/', formData,{
        withCredentials:true,
         headers: {
         'Authorization': `Bearer ${token}`
         }
      });
      fetchIssues();
    } catch (err) {
        const errors = err.response.data;
        const messages = Object.values(errors).flat().join('\n');
        alert(messages);
    }
    finally{
        setShowDialog(false);
         setFormData({
        book:'',
        member:''
      })
    }
  };

  const handleReturn = async (id) => {
    const today = new Date().toISOString().split('T')[0];
    console.log(today);
    try {
      await axios.patch(`http://127.0.0.1:8000/api/issue/returnbook/${id}/`,{},{
        withCredentials:true,
         headers: {
         'Authorization': `Bearer ${token}`
         }
      });
      fetchIssues();
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
      </div>

      {showDialog && (
        <div className="fixed inset-0 bg-black bg-opacity-40 flex items-center justify-center z-50 p-4">
          <div className="bg-white w-full max-w-xl rounded-lg shadow-lg p-6">
            <h2 className="text-xl font-bold mb-4 text-gray-700">Add Issue Record</h2>
            <form onSubmit={handleSubmit} className="space-y-4">
              <input
                type="number"
                name="book"
                placeholder="Book ID"
                value={formData.book}
                onChange={handleChange}
                required
                className="w-full border border-gray-300 rounded px-3 py-2"
              />
              <input
                type="number"
                name="member"
                placeholder="Member ID"
                value={formData.member}
                onChange={handleChange}
                required
                className="w-full border border-gray-300 rounded px-3 py-2"
              />
             
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
            </form>
          </div>
        </div>
      )}
    </div>
  );
};

export default Issues;
