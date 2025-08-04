import { useContext, useEffect, useState } from 'react'
import { UserContext } from '../context/UserContext'
import Axios from '../utils/Axios'

const BorrowedBooks = () => {
  const { user } = useContext(UserContext)
  const token = localStorage.getItem('access_token')
  const [books, setBooks] = useState([])
  const [loading, setLoading] = useState(true)

  const fetchAllBookOfUser = async () => {
    try {
      const res = await Axios.get(`issue/allBooksWithMemberId/${user?.id}/`)
      setBooks(res.data)
    } catch (err) {
      const error = err.response?.data
      const msg = Object.values(error).flat().join('\n')
      alert(msg)
    }
    finally{
      setLoading(false)
    }
  }

  useEffect(() => {
    if (user) {
      fetchAllBookOfUser()
    }
  }, [user])

  const HandleReturn=async(id)=>{
    if(!window.confirm('Do you want to return')){
      return 
    }
    try {
      await Axios.patch(`issue/returnbook/${id}/`,{});
      fetchAllBookOfUser();
    } catch (err) {
        const errors = err.response.data;
        const messages = Object.values(errors).flat().join('\n');
        alert(messages);
    }
  }

  if (loading) {
    return <p className="text-center text-lg">Loading borrowed books...</p>
  }
  return (
    <div className="container mx-auto p-6 overflow-x-auto">
      <h1 className="text-2xl font-bold mb-4 text-center">Borrowed Books</h1>
      {books.length > 0 ? (
        <div className='mb-10 overflow-x-auto'>
        <table className="min-w-full table-auto border-collapse border border-gray-200">
          <thead className="text-left">
            <tr>
              <th className="border px-4 py-2 bg-gray-200">Issue ID</th>
              <th className="border px-4 py-2 bg-gray-200">Title</th>
              <th className="border px-4 py-2 bg-gray-200">Author</th>
              <th className="border px-4 py-2 bg-gray-200">Category</th>
              <th className="border px-4 py-2 bg-gray-200">Issue Status</th>
              <th className="border px-4 py-2 bg-gray-200">Actions</th>
            </tr>
          </thead>
          <tbody>
            {books.map((book) => (
              <tr key={book.issue_id} className="odd:bg-white even:bg-gray-100">
                <td className="px-4 py-2">{book.issue_id}</td>
                <td className="px-4 py-2">{book.title}</td>
                <td className="px-4 py-2">{book.author}</td>
                <td className="px-4 py-2">{book.category}</td>
                <td className="px-4 py-2">{book.issue_status}</td>
                <td className="px-4 py-2">
                  {book.issue_status=='I'?(
                     <button className="px-3 py-2 bg-blue-500 text-white rounded-lg" onClick={()=>HandleReturn(book.issue_id)}>
                       Return
                  </button>
                  ):(
                    <p>Returned</p>
                  )}
                </td>
              </tr>
            ))}
          </tbody>
        </table>
        </div>
      ) : (
        <p className="text-center">Not Borrowed Yet.</p>
      )}
    </div>
  )
}

export default BorrowedBooks
