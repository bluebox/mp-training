import React from "react";

const MappingRequest = ({ mappingRequest }) => {
  return (
    <div>
      <tr>
        <td>{mappingRequest.mappingRequestId}</td>
        <td>{mappingRequest.productId}</td>
        <td>{mappingRequest.categoryId}</td>
        <td>{mappingRequest.requestedBy}</td>
        <td>{mappingRequest.status}</td>
        <td>{mappingRequest.approvedBy ?? "—"}</td>
        <td>{new Date(mappingRequest.createdAtDate).toLocaleString()}</td>
        <td>{new Date(mappingRequest.updatedAtDate).toLocaleString()}</td>
      </tr>
    </div>
  );
};

export default MappingRequest;
