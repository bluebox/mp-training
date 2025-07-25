import React from "react";
import "./Record.css";

const Record = ({ record, onEdit, onDelete }) => {
  return (
    <tr>
      <td>{record.name}</td>
      <td>{record.phone}</td>
      <td>{record.email}</td>
      <td>{record.department}</td>
      <td>{record.joinDate}</td>
      <td>
        <button onClick={() => onEdit(record.id)}>Edit</button>
        <button onClick={() => onDelete(record.id)}>Delete</button>
      </td>
    </tr>
  );
};

export default Record;



