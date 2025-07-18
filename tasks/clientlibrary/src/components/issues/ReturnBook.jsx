import React, { useState } from "react";

const ReturnBook = () => {
  const [formData, setFormData] = useState({
    issueId: "",
    bookId: "",
    returnDate: "",
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
      const response = await fetch(
        `http://localhost:8080/api/issues/return?bookId=${formData.bookId}&issueId=${formData.issueId}&returnDate=${formData.returnDate}`,
        {
          method: "POST",
          credentials: "include",
        }
      );
      if (!response.ok) {
        throw new Error("Failed to return book");
      }
      setMessage("Book returned successfully!");
      setFormData({ issueId: "", bookId: "", returnDate: "" });
    } catch (error) {
      console.error(error);
      setMessage("Failed to return book.");
    }
  };

  return (
    <form
      onSubmit={handleSubmit}
      className="grid grid-cols-1 md:grid-cols-2 gap-x-4 gap-y-3 mt-10 p-6"
    >
      <label htmlFor="issueId" className="font-medium">
        Issue ID:
      </label>
      <input
        id="issueId"
        name="issueId"
        type="text"
        value={formData.issueId}
        onChange={handleChange}
        required
        className="p-2 border rounded"
      />

      <label htmlFor="bookId" className="font-medium">
        Book ID:
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

      <label htmlFor="returnDate" className="font-medium">
        Return Date:
      </label>
      <input
        id="returnDate"
        name="returnDate"
        type="date"
        value={formData.returnDate}
        onChange={handleChange}
        required
        className="p-2 border rounded"
      />

      <div className="col-span-2 text-center mt-2 font-semibold ">
        {message}
      </div>

      <button
        type="submit"
        className="col-span-2 bg-blue-600 text-white py-2 rounded hover:bg-blue-700 transition cursor-pointer"
      >
        Return Book
      </button>
    </form>
  );
};

export default ReturnBook;
