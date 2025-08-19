import { useEffect, useState } from "react";
import { getCategories } from "../services/BookService";

function BookModal({
  show,
  onClose,
  onSave,
  formData,
  handleChange,
  editingBook,
  error,
}) {
  const [categories, setCategories] = useState([]);
  const [localForm, setLocalForm] = useState(formData);
  const [fieldError, setFieldError] = useState({ title: "", author: "" });

  useEffect(() => {
    if (show) {
      const fetchdata = async () => {
        const res = await getCategories();
        if (res.success) {
          setCategories(res.data);
        }
      };
      fetchdata();
      setLocalForm(formData);
    }
  }, [show, formData]);

  if (!show) return null;

  const titleRegex = /^[a-zA-Z0-9 :\-.'&/,?!+]{1,50}$/;
  const authorRegex = /^[a-zA-Z .'-]{1,50}$/;

  const titleAllowed = /[^a-zA-Z0-9 :\-.'&/,?!+]/g;
  const authorAllowed = /[^a-zA-Z .'-]/g;

  const handleValidationChange = (e) => {
    const { name, value } = e.target;

    if (name === "title") {
      setFieldError((prev) => ({
        ...prev,
        title: value && !titleRegex.test(value)
          ? "Title allows letters, numbers and : - . ' & / , ? ! + (max 50 chars)"
          : "",
      }));
    }

    if (name === "author") {
      setFieldError((prev) => ({
        ...prev,
        author: value && !authorRegex.test(value)
          ? "Author allows only letters, spaces, . ' - (max 50 chars)"
          : "",
      }));
    }

    let sanitized = value;
    if (name === "title") sanitized = sanitized.replace(titleAllowed, "");
    if (name === "author") sanitized = sanitized.replace(authorAllowed, "");

    if (name === "title" && sanitized.length > 50) sanitized = sanitized.slice(0, 50);
    if (name === "author" && sanitized.length > 50) sanitized = sanitized.slice(0, 50);

    setLocalForm((prev) => ({ ...prev, [name]: sanitized }));
  };

  const hasErrors = fieldError.title || fieldError.author;

  const handleSave = () => {
    handleChange(localForm);
    onSave(localForm);
  };

  return (
    <div className="modal show d-block" tabIndex="-1">
      <div className="modal-dialog">
        <div className="modal-content">
          <div className="modal-header">
            <h5 className="modal-title">
              {editingBook ? "Edit Book" : "Add Book"}
            </h5>
            <button type="button" className="btn-close" onClick={onClose}></button>
          </div>

          <div className="modal-body">
            {error && <div className="alert alert-danger">{error}</div>}

            <input
              type="text"
              name="title"
              className="form-control mb-1"
              placeholder="Title"
              value={localForm.title}
              onChange={handleValidationChange}
            />
            {fieldError.title && (
              <small className="text-danger">{fieldError.title}</small>
            )}

            <input
              type="text"
              name="author"
              className="form-control mb-1 mt-2"
              placeholder="Author"
              value={localForm.author}
              onChange={handleValidationChange}
            />
            {fieldError.author && (
              <small className="text-danger">{fieldError.author}</small>
            )}

            <select
              name="category"
              className="form-control mb-2 mt-2"
              value={localForm.category}
              onChange={(e) =>
                setLocalForm((prev) => ({ ...prev, category: e.target.value }))
              }
            >
              <option value="">Select Category</option>
              {categories.map((cat) => (
                <option key={cat} value={cat}>
                  {cat}
                </option>
              ))}
            </select>
          </div>

          <div className="modal-footer">
            <button className="btn btn-secondary" onClick={onClose}>
              Cancel
            </button>
            <button
              className="btn btn-success"
              onClick={handleSave}
              disabled={!!hasErrors}
            >
              {editingBook ? "Update" : "Save"}
            </button>
          </div>
        </div>
      </div>
    </div>
  );
}

export default BookModal;
