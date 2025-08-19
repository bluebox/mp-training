import React, { useState } from "react";

function ReportsTable({columns, data, itemsPerPage = 5}) {
const [currentPage, setCurrentPage] = useState(1);

  const indexOfLast = currentPage * itemsPerPage;
  const indexOfFirst = indexOfLast - itemsPerPage;
  const currentItems = data.slice(indexOfFirst, indexOfLast);
  const totalPages = Math.ceil(data.length / itemsPerPage);

  return (
    <>
      <div className="d-flex justify-content-between mb-2">
        <span>
          Total Records: <strong>{data.length}</strong>
        </span>
        <span>
          Page {currentPage} of {totalPages || 1}
        </span>
      </div>

      <table className="table table-striped">
        <thead className="table-dark">
          <tr>
            <th>#</th>
            {columns.map((col) => (
              <th key={col.header}>{col.header}</th>
            ))}
             
          </tr>
        </thead>
        <tbody>
          {currentItems.length > 0 ? (
            currentItems.map((row, index) => (
              <tr key={row.id || index}>
                <td>{indexOfFirst + index + 1}</td>
                {columns.map((col) => (
                  <td key={col.accessor}>{row[col.accessor]}</td>
                ))}
              </tr>
            ))
          ) : (
            <tr>
              <td colSpan={columns.length + 2} className="text-center">
                No records available
              </td>
            </tr>
          )}
        </tbody>
      </table>

      <nav>
        <ul className="pagination justify-content-center">
          <li className={`page-item ${currentPage === 1 ? "disabled" : ""}`}>
            <button className="page-link" onClick={() => setCurrentPage(currentPage - 1)}>
              Previous
            </button>
          </li>
          {[...Array(totalPages).keys()].map((num) => (
            <li
              key={num + 1}
              className={`page-item ${currentPage === num + 1 ? "active" : ""}`}
            >
              <button className="page-link" onClick={() => setCurrentPage(num + 1)}>
                {num + 1}
              </button>
            </li>
          ))}
          <li className={`page-item ${currentPage === totalPages ? "disabled" : ""}`}>
            <button className="page-link" onClick={() => setCurrentPage(currentPage + 1)}>
              Next
            </button>
          </li>
        </ul>
      </nav>
    </>
  );
}

export default ReportsTable;