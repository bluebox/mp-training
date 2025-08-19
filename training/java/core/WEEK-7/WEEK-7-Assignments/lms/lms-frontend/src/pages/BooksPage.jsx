import { useEffect, useState } from "react"
import api from "../api/axiosConfig"
import Navbar from "../components/Navbar"

export default function BooksPage() {
  const [books, setBooks] = useState([])
  const [categories, setCategories] = useState([])
  const [modalOpen, setModalOpen] = useState(false)
  const [editBook, setEditBook] = useState(null)
  const [form, setForm] = useState({ title: "", author: "", category: "", quantity: 1 })
  const [errors, setErrors] = useState({})

  const fetchBooks = async () => {
    try {
      const res = await api.get("/books")
      setBooks(res.data.data || res.data)
    } catch (err) {
      console.error(err)
    }
  }

  const fetchCategories = async () => {
    try {
      const res = await api.get("/books/categories")
      setCategories(res.data.data || res.data)
    } catch (err) {
      console.error(err)
    }
  }

  useEffect(() => {
    fetchBooks()
    fetchCategories()
  }, [])

  const openAddModal = () => {
    setEditBook(null)
    setForm({ title: "", author: "", category: null})
    setErrors({})
    setModalOpen(true)
  }

  const openEditModal = (book) => {
    setEditBook(book)
    setForm(book)
    setErrors({})
    setModalOpen(true)
  }

  const handleChange = (e) => {
    if (e.target.value==="default") {
      setForm({ ...form, [e.target.name]: null })
    } else {
      setForm({ ...form, [e.target.name]: e.target.value })
    }
  }

  const handleSubmit = async (e) => {
    e.preventDefault()
    if (editBook) {
        await api.put(`/books/${editBook.bookId}`, form)
          .then((res) => {
            alert(res.data.message);
            fetchBooks();
            setModalOpen(false);
          })
          .catch((err) => {
            console.error(err)
            alert(err.response.data.message || err.response.data.messages[0] || "Failed to add book");
          });
      } else {
        api.post("/books", form)
          .then((res) => {
            alert(res.data.message);
            fetchBooks();
            setModalOpen(false);
          })
          .catch((err) => {
            alert(err.response.data.message || err.response.data.messages[0] || "Failed to add book");
          });
      }
      fetchBooks()
    }
  const handleDelete = async (id) => {
    if (window.confirm("Are you sure you want to delete this book?")) {
      try {
        await api.delete(`/books/${id}`)
        fetchBooks()
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
          <h2 className="text-2xl font-semibold text-gray-800">Books ({books.length})</h2>
          <button onClick={openAddModal} className="bg-indigo-600 text-indigo-700 px-4 py-2 rounded hover:bg-indigo-700">Add Book</button>
        </div>

        <div className="overflow-x-auto bg-white rounded-xl shadow">
          <table className="min-w-full divide-y divide-gray-200">
            <thead className="bg-gray-50">
              <tr>
                <th className="px-6 py-3 text-left text-sm font-medium text-gray-500">Title</th>
                <th className="px-6 py-3 text-left text-sm font-medium text-gray-500">Author</th>
                <th className="px-6 py-3 text-left text-sm font-medium text-gray-500">Category</th>
                <th className="px-6 py-3 text-left text-sm font-medium text-gray-500">Actions</th>
              </tr>
            </thead>
            <tbody className="divide-y divide-gray-200">
              {books.map((book) => (
                <tr key={book.id}>
                  <td className="px-6 py-4">{book.title}</td>
                  <td className="px-6 py-4">{book.author}</td>
                  <td className="px-6 py-4">{book.category}</td>
                  <td className="px-6 py-4 space-x-2">
                    <button onClick={() => openEditModal(book)} className="text-indigo-600 hover:underline">Edit</button>
                    <button onClick={() => handleDelete(book.bookId)} className="text-red-600 hover:underline">Delete</button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>

        {modalOpen && (
          <div className="fixed inset-0 bg-black/30 bg-opacity-50 flex items-center justify-center z-50">
            <div className="bg-white rounded-xl w-96 p-6 relative">
              <h3 className="text-xl font-semibold mb-4">{editBook ? "Update Book" : "Add Book"}</h3>
              {errors.general && <p className="text-red-600 mb-2">{errors.general}</p>}
              <form onSubmit={handleSubmit} className="space-y-4">
                <div>
                  <label className="block text-sm font-medium text-gray-700">Title</label>
                  <input
                    name="title"
                    value={form.title}
                    onChange={handleChange}
                    className="mt-1 block w-full border border-gray-300 rounded px-3 py-2"
                  />
                  {errors.title && <p className="text-red-600 text-sm">{errors.title}</p>}
                </div>

                <div>
                  <label className="block text-sm font-medium text-gray-700">Author</label>
                  <input
                    name="author"
                    value={form.author}
                    onChange={handleChange}
                    className="mt-1 block w-full border border-gray-300 rounded px-3 py-2"
                  />
                  {errors.author && <p className="text-red-600 text-sm">{errors.author}</p>}
                </div>

                <div>
                  <label className="block text-sm font-medium text-gray-700">Category</label>
                  <select
                    name="category"
                    value={form.category}
                    onChange={handleChange}
                    className="mt-1 block w-full border border-gray-300 rounded px-3 py-2"
                  >
                    <option value="default">Select Category</option>
                    {categories.map((cat) => (
                      <option key={cat} value={cat}>{cat}</option>
                    ))}
                  </select>
                  {errors.category && <p className="text-red-600 text-sm">{errors.category}</p>}
                </div>

                <div className="flex justify-end space-x-2">
                  <button type="button" onClick={() => setModalOpen(false)} className="px-4 py-2 rounded bg-gray-300 hover:bg-gray-400">Cancel</button>
                  <button type="submit" className="px-4 py-2 rounded bg-indigo-600 hover:bg-indigo-700 text-indigo-600">{editBook ? "Update" : "Add"}</button>
                </div>
              </form>
            </div>
          </div>
        )}
      </main>
    </div>
  )
}
