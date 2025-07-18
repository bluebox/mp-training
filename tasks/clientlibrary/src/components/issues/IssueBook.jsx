import React, { useState } from "react";

const IssueBook = () => {
  const [formData, setFormData] = useState({
    bookId: "",
    memberId: "",
    issueDate: "",
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
      const response = await fetch("http://localhost:8080/api/issues/issue", {
        method: "POST",
        credentials: "include",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(formData),
      });
      if (!response.ok) {
        throw new Error("Failed to issue book");
      }
      setMessage("Book issued successfully!");
      setFormData({ bookId: "", memberId: "", issueDate: "" });
    } catch (error) {
      console.error(error);
      setMessage("Failed to issue book.");
    }
  };

  return (
    <form
      onSubmit={handleSubmit}
      className="grid grid-cols-1 md:grid-cols-2 gap-x-4 gap-y-3 mt-10 p-6"
    >
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

      <label htmlFor="memberId" className="font-medium">
        Member ID:
      </label>
      <input
        id="memberId"
        name="memberId"
        type="text"
        value={formData.memberId}
        onChange={handleChange}
        required
        className="p-2 border rounded"
      />

      <label htmlFor="issueDate" className="font-medium">
        Issue Date:
      </label>
      <input
        id="issueDate"
        name="issueDate"
        type="date"
        value={formData.issueDate}
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
        Issue Book
      </button>
    </form>
  );
};

export default IssueBook;
