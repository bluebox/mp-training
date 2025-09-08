import React, { useRef, useState, useEffect } from 'react'

function AddBook() {
  const titleRef = useRef();
  const authorRef = useRef();
  const categoryRef = useRef();
  const statusRef = useRef();
  // const availabilityRef = useRef();

  const [categories, setCategories] = useState([]);
  const [statuses, setStatuses] = useState([]);

  useEffect(() => {
    const fetchCategories = async () => {
      try {
        const res = await fetch("http://localhost:8080/api/books/getCategories");
        if (!res.ok) throw new Error("Failed to fetch categories");
        const data = await res.json();
        setCategories(data);
      } catch (err) {
        setCategories([]);
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
    fetchCategories();
    fetchStatuses();
  }, []);

  const validate = ({ title, author, category, status }) => {
    if (!title) {
      alert("Book title can't be null");
      return false;
    }
    const titleTrimmed = title.trim();
    if (titleTrimmed.length < 2 || titleTrimmed.length > 100) {
      alert("Book title must be between 2 and 100 characters");
      return false;
    }
    if (!author) {
      alert("Author can't be null");
      return false;
    }
    const authorTrimmed = author.trim();
    if (authorTrimmed.length < 2 || authorTrimmed.length > 100) {
      alert("Author name must be between 2 and 100 characters");
      return false;
    }
    if (!category) {
      alert("Category can't be null");
      return false;
    }
    if (!status) {
      alert("Status can't be null");
      return false;
    }
    return true;
  }
  const handleSubmit = async (e) => {
    e.preventDefault();
    const title = titleRef.current.value;
    const author = authorRef.current.value;
    const category = categoryRef.current.value.toUpperCase();
    const status = statusRef.current.value.toUpperCase();
    // const availability = availabilityRef.current.value.toUpperCase();

    if (!validate({ title, author, category, status })) {
      return;
    }
    try {
      const res = await fetch("http://localhost:8080/api/books/addBook", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ title, author, category, status, availability: "AVAILABLE" })
      });

      const text = await res.text();

      let responseBody;
      try {
        responseBody = JSON.parse(text);
      } catch {
        responseBody = text;
      }

      if (res.ok) {
        alert("Book added successfully!");
        titleRef.current.value = "";
        authorRef.current.value = "";
        categoryRef.current.value = "";
        statusRef.current.value = "";
        // availabilityRef.current.value = "";
      } else {
        alert(typeof responseBody === "object" ? JSON.stringify(responseBody) : responseBody);
      }
    } catch (err) {
      alert("Error: " + err.message);
    }
  };

  const handleBack = () => {
    window.location.href = '/';
  };

  return (
    <div>
      <h2>Add New Book</h2>
      <form onSubmit={handleSubmit}>
        <div className="form-row">
          <label htmlFor="titleField">Title:</label>
          <input
            type="text"
            id="titleField"
            name="title"
            placeholder="Enter book title"
            required
            ref={titleRef}
            onInvalid={e => e.target.setCustomValidity('title is required')}
            onInput={e => e.target.setCustomValidity('')}
          />
        </div>

        <div className="form-row">
          <label htmlFor="authorField">Author:</label>
          <input
            type="text"
            id="authorField"
            name="author"
            placeholder="Enter author name"
            required
            ref={authorRef}
            onInvalid={e => e.target.setCustomValidity('author is required')}
            onInput={e => e.target.setCustomValidity('')}
          />
        </div>

        <div className="form-row">
          <label htmlFor="categoryComboBox">Category:</label>
          <select
            id="categoryComboBox"
            name="category"
            required
            ref={categoryRef}
            onInvalid={e => e.target.setCustomValidity('category is required')}
            onInput={e => e.target.setCustomValidity('')}
          >
            <option value="" disabled selected>Select category</option>
            {categories.map(category => (
              <option key={category} value={category}>
                {category.charAt(0).toUpperCase() + category.slice(1).toLowerCase()}
              </option>
            ))}
          </select>
        </div>

        <div className="form-row">
          <label htmlFor="statusComboBox">Status:</label>
          <select
            id="statusComboBox"
            name="status"
            required
            ref={statusRef}
            onInvalid={e => e.target.setCustomValidity('status is required')}
            onInput={e => e.target.setCustomValidity('')}
          >
            <option value="" disabled selected>Select status</option>
            {statuses.map(status => (
              <option key={status} value={status}>
                {status.charAt(0).toUpperCase() + status.slice(1).toLowerCase()}
              </option>
            ))}
          </select>
        </div>

        {/* <div className="form-row">
          <label htmlFor="availabilityComboBox">Availability:</label>
          <select
            id="availabilityComboBox"
            name="availability"
            required
            ref={availabilityRef}
            onInvalid={e => e.target.setCustomValidity('Availability is required')}
            onInput={e => e.target.setCustomValidity('')}
          >
            <option value="" disabled selected>Select availability</option>
            {availabilities.map(availability => (
              <option key={availability} value={availability}>
                {availability.charAt(0).toUpperCase() + availability.slice(1).toLowerCase()}
              </option>
            ))}
          </select>
        </div> */}

        <div className="button-row">
          <button type="submit" className="add-button">Add Book</button>
          <button type="button" onClick={handleBack} className="back-button">Back to Main Menu</button>
        </div>
      </form>
    </div>
  );
}

export default AddBook;
