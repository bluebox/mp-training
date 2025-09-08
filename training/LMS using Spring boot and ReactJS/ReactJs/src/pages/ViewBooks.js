import React, { useEffect, useState } from "react";

export default function ViewBooks() {
  const [books, setBooks] = useState([]);
  const [selected, setSelected] = useState([]);
  const [currentPage, setCurrentPage] = useState(1);
  const [searchTerm, setSearchTerm] = useState("");
  const [searchField, setSearchField] = useState("all");
  const pageSize = 5;

  useEffect(() => {
    fetch("http://localhost:8080/api/books/viewBooks")
      .then(async (res) => {
        if (!res.ok) {
          const errorText = await res.text();
          throw new Error(errorText);
        }
        return res.json();

      })
      .then((data) => {
        setBooks(data);
      })
      .catch((err) => {
        alert("Error fetching books:\n" + err.message);
      });
  }, []);

  const toggleSelect = (id) => {
    setSelected((prev) => {
      const updated = [...prev];
      const index = updated.indexOf(id);

      if (index !== -1) {
        updated.splice(index, 1);
      } else {
        updated.push(id);
      }
      return updated;
    });
  };

  const handleBatchDelete = async () => {
    if (selected.length === 0) {
      alert("Please select books to delete.");
      return;
    }
    if (!window.confirm(`Delete ${selected.length} selected books?`)) return;

    try {
      const response = await fetch(
        "http://localhost:8080/api/books/batchDeleteBooks",
        {
          method: "POST",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify(selected),
        }
      );

      const text = await response.text();
      if (!response.ok) {
        alert(`Delete failed: ${text}`);
        return;
      }

      alert("Deleted successfully");
      setBooks(books.filter((b) => !selected.includes(b.bookId)));
      setSelected([]);
    } catch (err) {
      alert("Error: " + err.message);
    }
  };

  const handleBatchUpdate = async () => {
    if (selected.length === 0) {
      alert("Please select books to update.");
      return;
    }
    if (!window.confirm(`Update status for ${selected.length} books?`))
      return;

    try {
      const res = await fetch(
        "http://localhost:8080/api/books/updateBooksStatusBatch",
        {
          method: "POST",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify([selected]),
        }
      );
      const text = await res.text();

      let responseBody;
      try {
        responseBody = JSON.parse(text);
      } catch {
        responseBody = text;
      }

      if (res.ok) {
        alert("Updated successfully");
        fetch("http://localhost:8080/api/books/viewBooks")
          .then(async (res) => {
            if (!res.ok) {
              const errorText = await res.text();
              throw new Error(errorText);
            }
            return res.json();
          })
          .then((data) => {
            setBooks(data);
            console.log(data);

          })
          .catch((err) => {
            alert("Error fetching books:\n" + err.message);
          });
        setSelected([]);
      } else {
        alert(typeof responseBody === "object" ? JSON.stringify(responseBody) : responseBody);
      }
    } catch (err) {
      alert("Error: " + err.message);
    }
  };

  const capitalizeFirst = (str) => {
    if (!str) return "";
    return str.charAt(0).toUpperCase() + str.slice(1).toLowerCase();
  };

  const filteredBooks = books.filter((book) => {
    if (!searchTerm.trim()) return true;
    const term = searchTerm.toLowerCase();
    if (searchField === "all") {
      return Object.values(book).some((value) =>
        value && String(value).toLowerCase().includes(term)
      );
    } else {
      const value = book[searchField];
      return value && String(value).toLowerCase().includes(term);
    }
  });

  const totalPages = Math.ceil(filteredBooks.length / pageSize);
  const paginatedBooks = filteredBooks.slice((currentPage - 1) * pageSize, currentPage * pageSize);

  const formatDateTime = (dateTime) => {
    if (!dateTime) return ''
    dateTime = String(dateTime)
    dateTime = dateTime.replace('T', '\n')
    return dateTime;
  }

  return (
    <div className="view-books">
      <h2>All Books</h2>
      <div style={{ marginBottom: "10px" }}>
        <input
          type="text"
          placeholder="Search books..."
          value={searchTerm}
          onChange={e => {
            setSearchTerm(e.target.value);
            setCurrentPage(1);
          }}
          style={{ width: "300px", padding: "5px" }}
        />
        <select
          value={searchField}
          onChange={e => setSearchField(e.target.value)}
          style={{ marginLeft: "10px", padding: "5px" }}
        >
          <option value="all">All Fields</option>
          <option value="bookId">Book ID</option>
          <option value="title">Title</option>
          <option value="author">Author</option>
          <option value="category">Category</option>
          <option value="status">Status</option>
          <option value="availability">Availability</option>
          <option value="createdAt">Created At</option>
          <option value="createdBy">Created By</option>
          <option value="updatedAt">Updated At</option>
          <option value="updatedBy">Updated By</option>
        </select>
      </div>
      {filteredBooks.length === 0 ? (
        <p className="no-books">No books found in the library.</p>
      ) : (
        <>
          <table className="books-table">
            <thead>
              <tr>
                <th></th>
                <th>Book ID</th>
                <th>Title</th>
                <th>Author</th>
                <th>Category</th>
                <th>Status</th>
                <th>Availability</th>
                <th>Created At</th>
                <th>Created By</th>
                <th>Updated At</th>
                <th>Updated By</th>
                <th>Action</th>
              </tr>
            </thead>
            <tbody>
              {paginatedBooks.map((book) => (
                <tr key={book.bookId}>
                  <td>
                    <input
                      type="checkbox"
                      checked={selected.includes(book.bookId)}
                      onChange={() => toggleSelect(book.bookId)}
                    />
                  </td>
                  <td>{book.bookId}</td>
                  <td>{book.title}</td>
                  <td>{book.author}</td>
                  <td>{capitalizeFirst(book.category)}</td>
                  <td>{capitalizeFirst(book.status)}</td>
                  <td>{capitalizeFirst(book.availability)}</td>
                  <td>{formatDateTime(book.createdAt)}</td>
                  <td>{capitalizeFirst(book.createdBy)}</td>
                  <td>{formatDateTime(book.updatedAt)}</td>
                  <td>{capitalizeFirst(book.updatedBy)}</td>
                  <td>
                    <button
                      type="button"
                      onClick={() => window.location.href = `/updateBook/${book.bookId}`}
                    >
                      Update
                    </button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>

          <div style={{ margin: "10px 0", display: "flex", justifyContent: "center", alignItems: "center" }}>
            <button
              type="button"
              className="pagination-btn"
              onClick={() => setCurrentPage((p) => Math.max(1, p - 1))}
              disabled={currentPage === 1}
              style={{ marginRight: "10px" }}
            >
              Prev
            </button>
            <span>
              Page {currentPage} of {totalPages}
            </span>
            <button
              type="button"
              className="pagination-btn"
              onClick={() => setCurrentPage((p) => Math.min(totalPages, p + 1))}
              disabled={currentPage === totalPages}
              style={{ marginLeft: "10px" }}
            >
              Next
            </button>
          </div>

          <div className="actions">
            {/* <button type="button" onClick={handleBatchDelete}>
              Delete Selected
            </button> */}
            <button type="button" onClick={handleBatchUpdate}
              disabled={selected.length === 0}>
              Change Status
            </button>
            <button type="button" onClick={() => (window.location.href = "/")} >
              Back to Main Menu
            </button>
          </div>
        </>
      )}
    </div>
  );
}