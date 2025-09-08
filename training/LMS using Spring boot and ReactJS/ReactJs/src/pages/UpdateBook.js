import React, { useEffect, useState } from 'react'
import { useParams } from 'react-router-dom';

function UpdateBook() {
  const params = useParams();
  const bookId = params.bookId;

  const [title, setTitle] = useState('');
  const [author, setAuthor] = useState('');
  const [category, setCategory] = useState('');
  const [status, setStatus] = useState('');
  const [availability, setAvailability] = useState('');
  const [statuses, setStatuses] = useState([]);

  useEffect(() => {
    if (!bookId) {
      alert("Missing bookId in URL parameters.");
      return;
    }
    const fetchBook = async () => {
      try {
        const res = await fetch("http://localhost:8080/api/books/viewBooks", {
          method: "POST",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify({ bookId })
        });

        if (!res.ok) throw new Error(`Error: ${res.status}`);
        const data = await res.json();

        if (data.length === 0) {
          alert("Book not found");
        } else {
          const book = data[0];
          setTitle(book.title);
          setAuthor(book.author);
          setCategory(book.category);
          setStatus(book.status);
          setAvailability(book.availability);
          console.log(book)
        }
      } catch (err) {
        alert("Error: " + err.message);
      }
    };

    const fetchStatuses = async () => {
      try {
        const res = await fetch("http://localhost:8080/api/books/getStatuses");
        if (!res.ok) throw new Error("Failed to fetch statuses");
        const data = await res.json();
        setStatuses(data);
      } catch (err) {
        setStatuses([]);
      }
    };

    fetchBook();
    fetchStatuses();
  }, [bookId]);

  // useEffect(() => {
  //   const fetchCategories = async () => {
  //     try {
  //       const res = await fetch("http://localhost:8080/api/books/getCategories");
  //       if (!res.ok) throw new Error("Failed to fetch categories");
  //       const data = await res.json();
  //       setCategories(data);
  //     } catch (err) {
  //       setCategories([]);
  //     }
  //   };
  //   fetchCategories();
  // }, []);

  const validate = (status) => {
    if (!status) {
      alert("Status can't be null");
      return false;
    }
    return true;
  }
  
  const handleSubmit = async (e) => {
    e.preventDefault();
    const formStatus = status.toUpperCase();
    
    if (!validate(formStatus)) {
      return;
    }
    
    try {
      const res = await fetch("http://localhost:8080/api/books/updateBook", {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
          bookId,
          title,
          author,
          category,
          status: formStatus,
          availability
        })
      });
      // const statusCode = res.status;
      const text = await res.text();

      let responseBody;
      try {
        responseBody = JSON.parse(text);
      } catch {
        responseBody = text;
      }
      

      if (res.ok) {
        alert("Book updated successfully!")
        window.location.href = '/viewBooks'
      } 
      else {
        alert(typeof responseBody === "object" ? JSON.stringify(responseBody) : responseBody);
      }
    } catch (err) {
      alert("Error: " + err.message);
    }
  };

  const handleBack = () => {
    window.location.href = '/viewBooks';
  };

  return (
    <div className="form-container">
      <h2>Update book</h2>
      <form onSubmit={handleSubmit}>
        <div className="form-group">
          <label>Title: </label>
          <div className="field-value">{title}</div>
        </div>

        <div className="form-group">
          <label>Author:</label>
          <div className="field-value">{author}</div>
        </div>

        <div className="form-group">
          <label>Category:</label>
          <div className="field-value">
            {category ? category.charAt(0).toUpperCase() + category.slice(1).toLowerCase() : ''}
          </div>
        </div>

        <div className="form-group">
          <label htmlFor="statusComboBox">Status:</label>
          <select
            id="statusComboBox"
            name="status"
            required
            value={status}
            onChange={e => setStatus(e.target.value)}
            onInvalid={e => e.target.setCustomValidity('status is required')}
            onInput={e => e.target.setCustomValidity('')}
          >
            <option value="" disabled>Select status</option>
            {statuses.map(stat => (
              <option key={stat} value={stat}>
                {stat.charAt(0).toUpperCase() + stat.slice(1).toLowerCase()}
              </option>
            ))}
          </select>
        </div>

        <div className="form-group">
          <label>Availability:</label>
          <div className="field-value">
            {availability ? availability.charAt(0).toUpperCase() + availability.slice(1).toLowerCase() : ''}
          </div>
        </div>

        <div className="button-row">
          <input type="submit" value="Update Book" />
          <button type="button" className="back-button" onClick={handleBack}>Go Back</button>
        </div>
      </form>
    </div>
  )
}

export default UpdateBook
