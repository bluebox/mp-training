import React, { useState } from "react";

const UpdateBookAvailability = () => {
  const [formData, setFormData] = useState({
    bookId: "",
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
      const response = await fetch("http://localhost:8080/api/books/update", {
        method: "POST",
        credentials: "include",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(formData),
      });

      if (!response.ok) {
        throw new Error("Failed to add book");
      }

      setMessage("Book added successfully!");
      setFormData({
        bookId: "",
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
      <label htmlFor="bookid" className="font-medium">
        bookId
      </label>
      <input
        id="bookId"
        name="bookId"
        type="text"
        value={formData.bookId}
        onChange={handleChange}
        required
        className="p-2 border rounded"
      />

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
        update Book
      </button>
    </form>
  );
};

export default UpdateBookAvailability;
