import React, { useEffect, useState } from "react";
import { deleteMySubmissoin, fetchMySubmissions } from "../../service/mysubmissionsService";
import EditMySubmission from "./EditMySubmission";

function MySubmissions() {
  const [submissions, setSubmissions] = useState([]);
  const [loading, setLoading] = useState(true);
  const [isEditing, setIsEditing] = useState(false);
  const [isDeleting, setIsDeleting] = useState(false);
  const [editingSubmission, setEditingSubmission] = useState({});
  
  const [currentPage, setCurrentPage] = useState(1);
  const [itemsPerPage] = useState(15);
  

   var totalPages = Math.ceil(submissions.length / itemsPerPage);
    var startIndex = (currentPage - 1) * itemsPerPage;
    var endIndex = startIndex + itemsPerPage;
    var currentSubmissions = submissions.slice(startIndex, endIndex);

  useEffect(() => { 
    const fetchSubs = async () => {
      console.log("Trying to refetch the submissions")
      try {
        const res = await fetchMySubmissions();
        setSubmissions(res);
        totalPages = Math.ceil(submissions.length / itemsPerPage);
        startIndex = (currentPage - 1) * itemsPerPage;
        endIndex = startIndex + itemsPerPage;
        currentSubmissions = submissions.slice(startIndex, endIndex);

      } catch (error) {
        alert(error);
      } finally {
        setLoading(false);
      }
      setLoading(false);
    };

    if(!isEditing || !isDeleting){
      fetchSubs();
    }

  },[isEditing,isDeleting]);

  const onClose = () =>{
    setIsEditing(false);
  }

  const handleDeleteExpense =(expense)=>{
    console.log("trying to delete " + expense.id);
    setIsDeleting(true);

    deleteMySubmissoin(expense)
        .then((res)=> alert(res.message))
        .then(()=>{setIsDeleting(false)})
        .catch((e)=> alert("Could not delete that expense..."));
  }

 
  const handlePageChange = (pageNumber) => {
    setCurrentPage(pageNumber);
  };

  const handlePreviousPage = () => {
    if (currentPage > 1) {
      setCurrentPage(currentPage - 1);
    }
  };

  const handleNextPage = () => {
    if (currentPage < totalPages) {
      setCurrentPage(currentPage + 1);
    }
  };

  const getPageNumbers = () => {
    const pageNumbers = [];
    const maxVisiblePages = 5;
    
    if (totalPages <= maxVisiblePages) {
      for (let i = 1; i <= totalPages; i++) {
        pageNumbers.push(i);
      }
    } else {
      const startPage = Math.max(1, currentPage - 2);
      const endPage = Math.min(totalPages, startPage + maxVisiblePages - 1);
      
      for (let i = startPage; i <= endPage; i++) {
        pageNumbers.push(i);
      }
    }
    
    return pageNumbers;
  };

  return (
    <div className="container mt-3">
      {isEditing && <EditMySubmission currentSubmission={editingSubmission} onClose={onClose}/>}

      <h2>My Expense Submissions</h2>
      {loading ? (
        <p>Loading...</p>
      ) : submissions.length === 0 ? (
        <p>No submissions yet!</p>
      ) : (
        <>
          <table className="table table-striped">
            <thead className="table-dark">
              <tr>
                <th>Description</th>
                <th>Category</th>
                <th>Amount</th>
                <th>Date</th>
                <th>Status</th>
                <th>Remarks</th>
                <th>Actions</th>
                <th>ReceiptUrl</th>
              </tr>
            </thead>
            <tbody>
              {currentSubmissions.map(sub => (
                <tr key={sub.id}>
                  <td>{sub.description}</td>
                  <td>{sub.categoryName}</td>
                  <td>{sub.amount}</td>
                  <td>{sub.date}</td>
                  <td>
                    <span className={`badge ${
                          sub.status === 'APPROVED' ? 'bg-success' :
                          sub.status === 'REJECTED' ? 'bg-danger' :
                          'bg-warning'
                        }`}>
                      {sub.status}
                    </span>
                  </td>
                  <td>{sub.remarks || "---"}</td>
                  <td>
                    {sub.status === "PENDING" ? (
                      <>
                        <button className="btn btn-sm btn-secondary" onClick={()=>{setIsEditing(true); setEditingSubmission(sub)}}>Edit</button>
                        <button className="btn btn-sm btn-danger ms-2" onClick={()=>{handleDeleteExpense(sub)}}>Delete</button>
                      </>
                    ) : (
                      <span>-</span>
                    )}
                  </td>
                  <td>
                    {sub.receiptUrl==="" || sub.receiptUrl===null?(
                      <span>No Reciept</span>
                    ):(
                      <a target="_blank" href={sub.receiptUrl}>Reciept</a>
                    )}
                  </td>
                </tr>
              ))}
            </tbody>
          </table>

          {totalPages > 1 && (
            <nav aria-label="Submissions pagination">
              <ul className="pagination justify-content-center">
                <li className={`page-item ${currentPage === 1 ? 'disabled' : ''}`}>
                  <button 
                    className="page-link" 
                    onClick={handlePreviousPage}
                    disabled={currentPage === 1}
                  >
                    {"<"}
                  </button>
                </li>
                
                {getPageNumbers().map(pageNumber => (
                  <li 
                    key={pageNumber} 
                    className={`page-item ${currentPage === pageNumber ? 'active' : ''}`}
                  >
                    <button 
                      className="page-link" 
                      onClick={() => handlePageChange(pageNumber)}
                    >
                      {pageNumber}
                    </button>
                  </li>
                ))}
                
                <li className={`page-item ${currentPage === totalPages ? 'disabled' : ''}`}>
                  <button 
                    className="page-link" 
                    onClick={handleNextPage}
                    disabled={currentPage === totalPages}
                  >
                    {">"}
                  </button>
                </li>
              </ul>
              
              <div className="text-center mt-2">
                <small className="text-muted">
                  Showing {startIndex + 1} to {Math.min(endIndex, submissions.length)} of {submissions.length} submissions
                </small>
              </div>
            </nav>
          )}
        </>
      )}
    </div>
  );
};

export default MySubmissions;