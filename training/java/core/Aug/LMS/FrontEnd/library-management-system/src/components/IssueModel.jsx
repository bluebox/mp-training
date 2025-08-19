import React, { useEffect, useState, useRef } from "react";
import { getAvailableBooks } from "../services/BookService";
import { getMembers } from "../services/memberService";

function IssueModal({ show, onClose, onSave, formData, handleChange, error }) {
  const [members, setMembers] = useState([]);
  const [books, setBooks] = useState([]);
  const [localForm, setLocalForm] = useState(formData);
  const [fieldError, setFieldError] = useState({
    bookId: "",
    memberId: "",
    issueDate: "",
  });

  const [bookSearch, setBookSearch] = useState("");
  const [memberSearch, setMemberSearch] = useState("");
  const [showBookDropdown, setShowBookDropdown] = useState(false);
  const [showMemberDropdown, setShowMemberDropdown] = useState(false);

  const bookRef = useRef();
  const memberRef = useRef();

  useEffect(() => {
    if (show) {
      fetchData();
      setLocalForm(formData);
      setBookSearch("");
      setMemberSearch("");
    }
  }, [show, formData]);

  const fetchData = async () => {
    const bookRes = await getAvailableBooks();
    if (bookRes.success) setBooks(bookRes.data);

    const memberRes = await getMembers();
    if (memberRes.success) setMembers(memberRes.data);
  };

  if (!show) return null;

  const validateField = (name, value) => {
    let errorMsg = "";
    if (name === "bookId" && !value) errorMsg = "Book is required";
    if (name === "memberId" && !value) errorMsg = "Member is required";
    if (name === "issueDate") {
      if (!value) errorMsg = "Issue Date is required";
      else if (!/^\d{4}-\d{2}-\d{2}$/.test(value))
        errorMsg = "Enter date in YYYY-MM-DD format";
    }
    setFieldError((prev) => ({ ...prev, [name]: errorMsg }));
  };

  const handleSaveClick = () => {
    validateField("bookId", localForm.bookId);
    validateField("memberId", localForm.memberId);
    validateField("issueDate", localForm.issueDate);

    if (!fieldError.bookId && !fieldError.memberId && !fieldError.issueDate) {
      handleChange(localForm);
      onSave(localForm);
    }
  };
  const handleValidationChange = (e) => { const { name, value } = e.target; setLocalForm((prev) => ({ ...prev, [name]: value })); validateField(name, value); };

  const filteredBooks = books.filter((b) =>
    b.title.toLowerCase().includes(bookSearch.toLowerCase())
  );
  const filteredMembers = members.filter((m) =>
    m.name.toLowerCase().includes(memberSearch.toLowerCase())
  );

  const selectBook = (book) => {
    if(book == null){
      setLocalForm((prev) => ({...prev,bookId:null}))
    }else{
      setLocalForm((prev) => ({ ...prev, bookId: book.id }));
      setBookSearch(book.title);
      setShowBookDropdown(false);
      validateField("bookId", book.id);
    }
  };

  const selectMember = (member) => {
    if(member == null){
      setLocalForm((prev) => ({ ...prev, memberId: null }));
    }else{
      setLocalForm((prev) => ({ ...prev, memberId: member.id }));
      setMemberSearch(member.name);
      setShowMemberDropdown(false);
      validateField("memberId", member.id);
    }
  };

  return (
    <div className="modal show d-block" tabIndex="-1">
      <div className="modal-dialog">
        <div className="modal-content">
          <div className="modal-header">
            <h5 className="modal-title">Issue Book</h5>
            <button type="button" className="btn-close" onClick={onClose}></button>
          </div>
          <div className="modal-body">
            {error && <div className="alert alert-danger">{error}</div>}

            <div className="position-relative" ref={bookRef}>
              <input
                type="text"
                className="form-control mb-1"
                placeholder="Search Book..."
                value={bookSearch}
                onFocus={() => setShowBookDropdown(true)}
                onBlur={()=> setShowBookDropdown(false)}
                onChange={(e) => {
                  setBookSearch(e.target.value);
                  setShowBookDropdown(true);
                  selectBook(null)
                }}
              />
              {showBookDropdown && filteredBooks.length > 0 && (
                <div className="border position-absolute w-100 bg-white z-1" style={{ maxHeight: "150px", overflowY: "auto" }}>
                  {filteredBooks.map((book) => (
                    <div
                      key={book.id}
                      className="p-2 hover-bg cursor-pointer"
                      onClick={() => selectBook(book)}
                    >
                      {book.title}
                    </div>
                  ))}
                </div>
              )}
            </div>
            {fieldError.bookId && (
              <small className="text-danger">{fieldError.bookId}</small>
            )}

            <div className="position-relative mt-2" ref={memberRef}>
              <input
                type="text"
                className="form-control mb-1"
                placeholder="Search Member..."
                value={memberSearch}
                onFocus={() => setShowMemberDropdown(true)}
                onBlur={()=> setShowMemberDropdown(false)}
                onChange={(e) => {
                  setMemberSearch(e.target.value);
                  setShowMemberDropdown(true);
                }}
              />
              {(showMemberDropdown && filteredMembers.length > 0) && (
                <div className="border position-absolute w-100 bg-white z-1" style={{ maxHeight: "150px", overflowY: "auto" }}>
                  {filteredMembers.map((member) => (
                    <div
                      key={member.id}
                      className="p-2 hover-bg cursor-pointer"
                      onClick={() => selectMember(member)}
                    >
                      {member.name}
                    </div>
                  ))}
                </div>
              )}
            </div>
            {fieldError.memberId && (
              <small className="text-danger">{fieldError.memberId}</small>
            )}

            <input
              type="date"
              name="issueDate"
              className="form-control mb-2 mt-2"
              value={localForm.issueDate}
              onChange={(e) =>
                handleValidationChange({ target: { name: "issueDate", value: e.target.value } })
              }
            />
            {fieldError.issueDate && (
              <small className="text-danger">{fieldError.issueDate}</small>
            )}
          </div>

          <div className="modal-footer">
            <button className="btn btn-secondary" onClick={onClose}>
              Cancel
            </button>
            <button
              className="btn btn-success"
              onClick={handleSaveClick}
              disabled={!!fieldError.bookId || !!fieldError.memberId || !!fieldError.issueDate}
            >
              Issue
            </button>
          </div>
        </div>
      </div>
    </div>
  );
}

export default IssueModal;
