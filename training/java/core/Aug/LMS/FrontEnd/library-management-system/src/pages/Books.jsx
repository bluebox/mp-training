import React, { useEffect, useState } from "react";
import BookModal from "../components/BookModal";
import Swal from "sweetalert2";
import {
  addBook,
  deleteBook,
  getBooks,
  updateBook,
} from "../services/BookService";
import TableTemplate from "../components/TableTemplate";

function Books() {
  const [books, setBooks] = useState([]);
  const [filteredBooks, setFilteredBooks] = useState([]);
  const [showModal, setShowModal] = useState(false);
  const [editingBook, setEditingBook] = useState(null);
  const [formData, setFormData] = useState({
    title: "",
    author: "",
    category: "",
  });
  const [searchTerm, setSearchTerm] = useState("");
  const [error, setError] = useState(null);

  useEffect(() => {
    fetchBooks();
  }, []);

  useEffect(() => {
    if (searchTerm.trim() === "") {
      setFilteredBooks(books);
    } else {
      const lower = searchTerm.toLowerCase();
      setFilteredBooks(
        books.filter(
          (b) =>
            b.title.toLowerCase().includes(lower) ||
            b.author.toLowerCase().includes(lower)
        )
      );
    }
  }, [searchTerm, books]);

  const fetchBooks = async () => {
    try {
      const res = await getBooks();
      if (res.success) {
        setBooks(res.data);
      }
    } catch (err) {
      errorShow(err.response?.data?.message || "Failed to load books");
    }
  };

  const successShow = (message) => {
    Swal.fire({
      position: "top",
      icon: "success",
      text: message,
      timer: 1800,
      showConfirmButton: false,
      toast: true,
    });
  };

  const errorShow = (message) => {
    Swal.fire({
      position: "top",
      icon: "error",
      text: message,
      timer: 2000,
      showConfirmButton: false,
      toast: true,
    });
  };

  const openModal = (book = null) => {
    setEditingBook(book);
    setFormData(book ? book : { title: "", author: "", category: "" });
    setError(null);
    setShowModal(true);
  };

  const closeModal = () => {
    setShowModal(false);
    setEditingBook(null);
    setError(null);
  };

  const handleChange = (data) =>
    setFormData({ ...formData, ...data});

  const handleSave = async (data) => {
    try {
      if (!data.title || !data.author || !data.category) {
        setError("All fields are required!");
        return;
      }

      let res;
      if (editingBook) {
        res = await updateBook(data);
      } else {
        res = await addBook(data);
      }

      if (res.success) {
        successShow(res.message);
        fetchBooks();
        closeModal();
      } else {
        setError(res.message || "Something went wrong");
      }
    } catch (err) {
      setError(err.response?.data?.message || "Something went wrong");
    }
  };

  const handleDelete = async (id) => {
    Swal.fire({
      title: "Are you sure?",
      text: "This book will be permanently deleted!",
      icon: "warning",
      showCancelButton: true,
      confirmButtonColor: "#d33",
      cancelButtonColor: "#3085d6",
      confirmButtonText: "Yes, delete it!",
      cancelButtonText: "Cancel",
    }).then(async (result) => {
      if (result.isConfirmed) {
        try {
          const res = await deleteBook(id);
          if (res.success) {
            successShow(res.message);
          }
          fetchBooks();
        } catch (err) {
          errorShow(err.response?.data?.message || "Delete failed");
        }
      }
    });
  };

  return (
    <div className="p-3">
      <h2>📚 Books Management</h2>

      <div className="d-flex justify-content-between mb-3">
        <button className="btn btn-primary" onClick={() => openModal()}>
          ➕ Add Book
        </button>
        <input
          type="text"
          className="form-control w-50"
          placeholder="Search by title or author..."
          value={searchTerm}
          onChange={(e) => setSearchTerm(e.target.value)}
        />
      </div>

      <TableTemplate
        columns={[
          { key: "id", label: "ID" },
          { key: "title", label: "Title" },
          { key: "author", label: "Author" },
          { key: "category", label: "Category" },
          { key: "status", label: "Status" },
          { key: "availability", label: "Availability" },
        ]}
        data={filteredBooks}
        onEdit={openModal}
        onDelete={handleDelete}
      />

      <BookModal
        show={showModal}
        onClose={closeModal}
        onSave={handleSave}
        formData={formData}
        handleChange={handleChange}
        editingBook={editingBook}
        error={error}
      />
    </div>
  );
}

export default Books;
