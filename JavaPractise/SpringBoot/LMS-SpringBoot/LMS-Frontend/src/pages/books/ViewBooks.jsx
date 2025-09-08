import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

export default function ViewBooks() {
  const [books, setBooks] = useState([]);
  const navigate = useNavigate();

  const fetchBooks = async () => {
    try {
      const res = await fetch("http://localhost:8080/api/books/allbooks");
      if (!res.ok) throw await res.json();
      const data = await res.json();
      setBooks(data);
    } catch (err) {
      alert(err.message || "Failed to load books");
    }
  };
  const deleteBook = async (id) => {
    if (!window.confirm("Are you sure to delete?")) return;
    try {
      const res = await fetch(`http://localhost:8080/api/books/deletebook/${id}`, {
        method: "PUT",
      });
      if (!res.ok) throw await res.json();
      alert("Book deleted successfully!");
      fetchBooks();
    } catch (err) {
      alert(err.message || "Delete failed");
    }
  };
  useEffect(() => {
    fetchBooks();
  }, []);

  return (
    <div>
      <h3>All Books</h3>
      <table border="1" cellPadding="5">
        <thead>
          <tr>
            <th>ID</th><th>Title</th><th>Author</th>
            <th>Category</th><th>Status</th><th>Availability</th>
            <th>Created At</th><th>Updated At</th>
            <th>Created By</th>
            <th colSpan="3">Actions</th>
          </tr>
        </thead>
        <tbody>
          {books.map((b) => (
            <tr key={b.bookId}>
              <td>{b.bookId}</td>
              <td>{b.title}</td>
              <td>{b.author}</td>
              <td>{b.category}</td>
              <td>{b.status}</td>
              <td>{b.availability}</td>
              <td>{b.createdAt}</td>
              <td>{b.updatedAt}</td>
              <td>{b.createdBy}</td>
              <td>
                <button onClick={() => navigate(`/updatebook/${b.bookId}`)}>
                  Update
                </button>
              </td>
              <td>
                <button onClick={() => navigate(`/updateavailability/${b.bookId}`)}
                  disabled={b.availability !== "AVAILABLE"}>
                  Update Availability
                </button>
              </td>

              <td>
                <button onClick={() => deleteBook(b.bookId)}
                  disabled={b.status !== "ACTIVE"}>Delete</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
      <button onClick={() => navigate(-1)}>Back</button>
    </div>
  );
}
