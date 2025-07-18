import React from "react";

const MemberWithBook = ({ dto }) => {
  return (
    <tr>
      <td className="px-4 py-3">{dto.memberId}</td>
      <td className="px-4 py-3">{dto.memberName}</td>
      <td className="px-4 py-3">{dto.bookId}</td>
      <td className="px-4 py-3">{dto.bookName}</td>
      <td className="px-4 py-3">{dto.mobile}</td>
      <td className="px-4 py-3">{dto.address}</td>
      <td className="px-4 py-3">{dto.issueDate}</td>
    </tr>
  );
};

export default MemberWithBook;
