import React from "react";

const Issue = ({ record }) => {
  return (
    <tr>
      <td className="px-4 py-3">{record.issueId}</td>
      <td className="px-4 py-3">{record.bookId}</td>
      <td className="px-4 py-3">{record.memberId}</td>
      <td className="px-4 py-3">{record.issueDate}</td>
      <td className="px-4 py-3">
        {record.returnDate ? record.returnDate : "-"}
      </td>
      <td className="px-4 py-3">{record.status}</td>
    </tr>
  );
};

export default Issue;
