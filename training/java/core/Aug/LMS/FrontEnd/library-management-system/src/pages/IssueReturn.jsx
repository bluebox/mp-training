import { useEffect, useState } from "react";
import { addIssue, returnBook } from "../services/issueService";
import { activeIssuedBooks } from "../services/reportService";
import Swal from "sweetalert2";
import IssueTable from "../components/IssueTable";
import IssueModal from "../components/IssueModel";

function IssueReturn() {
  const [issues, setIssues] = useState([]);
  const [filteredIssues, setFilteredIssues] = useState([]);
  const [showModal, setShowModal] = useState(false);
  const [formData, setFormData] = useState({ bookId: "", memberId: "", issueDate: "" });
  const [error, setError] = useState(null);
  const [searchTerm, setSearchTerm] = useState("");

  useEffect(() => {
    fetchIssues();
  }, []);

  useEffect(() => {
    if (searchTerm.trim() === "") {
      setFilteredIssues(issues);
    } else {
      const lower = searchTerm.toLowerCase();
      setFilteredIssues(
        issues.filter(
          (i) =>
            i.bookTitle.toLowerCase().includes(lower) ||
            i.memberName.toLowerCase().includes(lower)
        )
      );
    }
  }, [searchTerm, issues]);

  const fetchIssues = async () => {
    try {
      const res = await activeIssuedBooks();
      if (res.success) {
        setIssues(res.data);
      }
    } catch (err) {
      errorShow(err.response?.data?.message || "Failed to load issued books");
    }
  };

  const successShow = (message) => {
    Swal.fire({
      position: "top",
      icon: "success",
      text: message,
      timer: 1800,
      showConfirmButton: false,
      toast: true,
    });
  };

  const errorShow = (message) => {
    Swal.fire({
      position: "top",
      icon: "error",
      text: message,
      timer: 2000,
      showConfirmButton: false,
      toast: true,
    });
  };

  const openModal = () => {
    setFormData({ bookId: "", memberId: "", issueDate: new Date().toISOString().split("T")[0] });
    setError(null);
    setShowModal(true);
  };

  const closeModal = () => {
    setShowModal(false);
    setError(null);
  };

  const handleChange = (data) =>
    setFormData({ ...formData, ...data });

  const handleSave = async (data) => {
    try {
      if (!data.bookId || !data.memberId || !data.issueDate) {
        setError("All fields are required!");
        return;
      }

      const res = await addIssue(data);
      if (res.success) {
        successShow(res.message);
        fetchIssues();
        closeModal();
      } else {
        setError(res.message || "Something went wrong");
      }
    } catch (err) {
      setError(err.response?.data?.message || "Something went wrong");
    }
  };

  const handleReturn = async (issue) => {
    Swal.fire({
      title: "Are you sure?",
      text: "Do you want to return this book?",
      icon: "warning",
      showCancelButton: true,
      confirmButtonColor: "#3085d6",
      cancelButtonColor: "#d33",
      confirmButtonText: "Yes, return it!",
      cancelButtonText: "Cancel",
    }).then(async (result) => {
      if (result.isConfirmed) {
        try {
          issue = issue.row;
          const res = await returnBook({
            ...issue,
            returnDate: new Date().toISOString().split("T")[0],
          });
          if (res.success) {
            successShow(res.message);
            fetchIssues();
          }
        } catch (err) {
          errorShow(err.response?.data?.message || "Return failed");
        }
      }
    });
  };

  return (
    <div className="p-3">
      <h2>📖 Issue & Return Management</h2>

      <div className="d-flex justify-content-between mb-3">
        <button className="btn btn-primary" onClick={openModal}>
          ➕ Issue Book
        </button>
        <input
          type="text"
          className="form-control w-50"
          placeholder="Search by book title or member name..."
          value={searchTerm}
          onChange={(e) => setSearchTerm(e.target.value)}
        />
      </div>

      <IssueTable
        columns={[
          { key: "id", label: "ID" },
          { key: "bookId", label: "Book ID" },
          { key: "bookTitle", label: "Book Title" },
          { key: "memberId", label: "Member ID" },
          { key: "memberName", label: "Member Name" },
          { key: "issueDate", label: "Issue Date" },
        ]}
        data={filteredIssues}
        onReturn={handleReturn}
      />

      <IssueModal
        show={showModal}
        onClose={closeModal}
        onSave={handleSave}
        formData={formData}
        handleChange={handleChange}
        error={error}
      />
    </div>
  );
}

export default IssueReturn;
