import { useEffect, useState } from "react"
import api from "../api/axiosConfig"
import Navbar from "../components/Navbar"

export default function IssueReturnPage() {
  const [members, setMembers] = useState([])
  const [books, setBooks] = useState([])
  const [availableBooks, setAvailableBooks] = useState([])
  const [issues, setIssues] = useState([])
  const [modalOpen, setModalOpen] = useState(false)
  const [form, setForm] = useState({ memberId: "", bookId: "" })
  const [errors, setErrors] = useState({})

  const fetchMembers = async () => {
    try {
      const res = await api.get("/issues/members")
      setMembers(res.data)
    } catch (err) {
      console.error(err)
    }
  }

  const fetchBooks = async () => {
    try {
      const res = await api.get("/issues/books")
      setBooks(res.data)
      const available = res.data.filter(b => b.availability === "Available")
      setAvailableBooks(available)
    } catch (err) {
      console.error(err)
    }
  }

  const fetchIssues = async () => {
    try {
      const res = await api.get("/reports/activeIssuedBooks")
      setIssues(res.data)
    } catch (err) {
      console.error(err)
    }
  }

  useEffect(() => {
    fetchMembers()
    fetchBooks()
    fetchIssues()
  }, [])

  const openIssueModal = () => {
    setForm({ memberId: "", bookId: "" })
    setErrors({})
    setModalOpen(true)
  }

  const handleChange = (e) => setForm({ ...form, [e.target.name]: e.target.value })

  const handleIssue = async (e) => {
    e.preventDefault()
    await api.post("/issues/issue", { memberId: form.memberId, bookId: form.bookId })
      .then((res) => {
        alert(res.data.message);
        fetchBooks()
        fetchIssues()
        setModalOpen(false);
      })
      .catch((err) => {
        alert(err.response.data.message || err.response.data.messages[0] || "Failed to add book");
      });
  }

  const handleReturn = async (issueId, memberId, bookId) => {
    if (window.confirm("Return this book?")) {
      try {
        await api.post(`/issues/return/${issueId}`, { memberId, bookId })
        fetchBooks()
        fetchIssues()
      } catch (err) {
        console.error(err)
      }
    }
  }

  return (
    <div className="min-h-screen bg-gray-100 w-[100vw]">
      <Navbar />
      <main className="pt-20 px-8">
        <div className="flex justify-between items-center mb-4">
          <h2 className="text-2xl font-semibold text-gray-800">Issue / Return</h2>
          <button onClick={openIssueModal} className="bg-indigo-600 text-indigo-600 px-4 py-2 rounded hover:bg-indigo-700">
            Issue Book
          </button>
        </div>

        <div className="overflow-x-auto bg-white rounded-xl shadow mb-4">
          <table className="min-w-full divide-y divide-gray-200">
            <thead className="bg-gray-50">
              <tr>
                <th className="px-6 py-3 text-left text-sm font-medium text-gray-500">Issue ID</th>
                <th className="px-6 py-3 text-left text-sm font-medium text-gray-500">Member Name</th>
                <th className="px-6 py-3 text-left text-sm font-medium text-gray-500">Book Title</th>
                <th className="px-6 py-3 text-left text-sm font-medium text-gray-500">Issue Date</th>
                <th className="px-6 py-3 text-left text-sm font-medium text-gray-500">Actions</th>
              </tr>
            </thead>
            <tbody className="divide-y divide-gray-200">
              {issues.map((issue) => (
                <tr key={issue.issueId}>
                  <td className="px-6 py-4">{issue.issueId}</td>
                  <td className="px-6 py-4">{issue.memberName}</td>
                  <td className="px-6 py-4">{issue.bookTitle}</td>
                  <td className="px-6 py-4">{issue.issueDate}</td>
                  <td className="px-6 py-4 space-x-2">
                    <button
                      onClick={() => handleReturn(issue.issueId, issue.memberId, issue.bookId)}
                      className="text-red-600 hover:underline"
                    >
                      Return
                    </button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>

        {modalOpen && (
          <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center z-50">
            <div className="bg-white rounded-xl w-96 p-6 relative">
              <h3 className="text-xl font-semibold mb-4">Issue Book</h3>
              {errors.general && <p className="text-red-600 mb-2">{errors.general}</p>}
              <form onSubmit={handleIssue} className="space-y-4">
                <div>
                  <label className="block text-sm font-medium text-gray-700">Member</label>
                  <select
                    name="memberId"
                    value={form.memberId}
                    onChange={handleChange}
                    className="mt-1 block w-full border border-gray-300 rounded px-3 py-2"
                  >
                    <option value="">Select Member</option>
                    {members.map(m => (
                      <option key={m.memberId} value={m.memberId}>{m.name}</option>
                    ))}
                  </select>
                  {errors.memberId && <p className="text-red-600 text-sm">{errors.memberId}</p>}
                </div>

                <div>
                  <label className="block text-sm font-medium text-gray-700">Book</label>
                  <select
                    name="bookId"
                    value={form.bookId}
                    onChange={handleChange}
                    className="mt-1 block w-full border border-gray-300 rounded px-3 py-2"
                  >
                    <option value="">Select Book</option>
                    {availableBooks.map(b => (
                      <option key={b.bookId} value={b.bookId}>{b.title}</option>
                    ))}
                  </select>
                  {errors.bookId && <p className="text-red-600 text-sm">{errors.bookId}</p>}
                </div>

                <div className="flex justify-end space-x-2">
                  <button type="button" onClick={() => setModalOpen(false)} className="px-4 py-2 rounded bg-gray-300 hover:bg-gray-400">Cancel</button>
                  <button type="submit" className="px-4 py-2 rounded bg-indigo-600 text-indigo-600 hover:bg-indigo-700">Issue</button>
                </div>
              </form>
            </div>
          </div>
        )}
      </main>
    </div>
  )
}
