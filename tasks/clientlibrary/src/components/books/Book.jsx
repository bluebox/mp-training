import React from "react";

const Book = ({ book }) => {
  const getStatus = (status) => {
    if (status === "A") return "Active";
    if (status === "I") return "Inactive";
    return status;
  };

  const getAvailability = (availability) => {
    if (availability === "A") return "Available";
    if (availability === "I") return "Issued";
    return availability;
  };

  return (
    <tr>
      <td className="px-4 py-3">{book.bookId}</td>
      <td className="px-4 py-3">{book.title}</td>
      <td className="px-4 py-3">{book.author}</td>
      <td className="px-4 py-3">{book.category}</td>
      <td className="px-4 py-3">{getStatus(book.status)}</td>
      <td className="px-4 py-3">{getAvailability(book.availability)}</td>
    </tr>
  );
};

export default Book;
