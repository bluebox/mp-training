import React from "react";

const CategoryRequest = ({ request, onApprove }) => {
  return (
    <tr>
      <td>{request.categoryRequestId}</td>
      <td>{request.categoryName}</td>
      <td>{request.requestedBy}</td>
      <td>{request.status}</td>
      <td>{request.approvedBy ?? "—"}</td>
      <td>{new Date(request.createdAtDate).toLocaleString()}</td>
      <td>{request.updatedAtDate ? new Date(request.updatedAtDate).toLocaleString() : "—"}</td>
      <td>
        {request.status === "PENDING" && (
          <button onClick={() => onApprove(request.categoryRequestId)}>Approve</button>
        )}
      </td>
    </tr>
  );
};

export default CategoryRequest;
