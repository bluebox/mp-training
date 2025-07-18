import React, { useState } from "react";

export const AddBook = () => {
  const [formData, setFormData] = useState({
    title: "",
    author: "",
    category: "",
    status: "",
    availability: "",
  });

  const [message, setMessage] = useState("");

  const handleChange = (e) => {
    setFormData((prev) => ({
      ...prev,
      [e.target.name]: e.target.value,
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const response = await fetch("http://localhost:8080/api/books/add", {
        method: "POST",
        credentials: "include",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(formData),
      });

      if (!response.success) {
        throw new Error("Failed to add book");
      }

      setMessage("Book added successfully!");
      setFormData({
        title: "",
        author: "",
        category: "",
        status: "",
        availability: "",
      });
    } catch (error) {
      console.error(error);
      setMessage("Failed to add book.");
    }
  };

  return (
    <form
      onSubmit={handleSubmit}
      className="grid grid-cols-1 md:grid-cols-2 gap-x-4 gap-y-3 mt-10 p-6"
    >
      <label htmlFor="title" className="font-medium">
        Title
      </label>
      <input
        id="title"
        name="title"
        type="text"
        value={formData.title}
        onChange={handleChange}
        required
        className="p-2 border rounded"
      />

      <label htmlFor="author" className="font-medium">
        Author
      </label>
      <input
        id="author"
        name="author"
        type="text"
        value={formData.author}
        onChange={handleChange}
        required
        className="p-2 border rounded"
      />

      <label htmlFor="category" className="font-medium">
        Category
      </label>
      <input
        id="category"
        name="category"
        type="text"
        value={formData.category}
        onChange={handleChange}
        required
        className="p-2 border rounded"
      />

      <label htmlFor="status" className="font-medium">
        Status
      </label>
      <select
        id="status"
        name="status"
        value={formData.status}
        onChange={handleChange}
        required
        className="p-2 border rounded"
      >
        <option value="" disabled>
          Select status
        </option>
        <option value="A">Active</option>
        <option value="I">Inactive</option>
      </select>

      <label htmlFor="availability" className="font-medium">
        Availability
      </label>
      <select
        id="availability"
        name="availability"
        value={formData.availability}
        onChange={handleChange}
        required
        className="p-2 border rounded"
      >
        <option value="" disabled>
          Select availability
        </option>
        <option value="A">Available</option>
        <option value="I">Issued</option>
      </select>

      <div className="col-span-2 text-center mt-2 font-semibold ">
        {message}
      </div>

      <button
        type="submit"
        className="col-span-2 bg-blue-600 text-white py-2 rounded hover:bg-blue-700 transition cursor-pointer"
      >
        Add Book
      </button>
    </form>
  );
};
