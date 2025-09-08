import { useState, useEffect } from "react";
import { useParams, useNavigate } from "react-router-dom";

const ENUM_URL = "http://localhost:8080/api/enums";
const BOOK_URL = "http://localhost:8080/api/books";

export default function UpdateBook() {
  const { id } = useParams();
  const navigate = useNavigate();

  const initialForm = {
    bookId: "",
    title: "",
    author: "",
    category: "",
    status: "",
    availability: "AVAILABLE",
  };

  const [form, setForm] = useState(initialForm);
  const [categories, setCategories] = useState([]);
  const [statuses, setStatuses] = useState([]);
  const [error, setError] = useState(null);

  // ✅ Fetch enums (single request)
  useEffect(() => {
    const fetchEnums = async () => {
      try {
        const res = await fetch(ENUM_URL);
        if (!res.ok) throw new Error("Failed to fetch enums");

        const data = await res.json();
        console.log("Enums Response:", data);

        const enums = data.enums || {};
        const catData = enums.Category || [];
        const statData = enums.Status || [];

        setCategories(catData);
        setStatuses(statData);

        setForm((f) => ({
          ...f,
          category: catData.length > 0 ? catData[0].name : "",
          status: statData.length > 0 ? statData[0].name : "",
        }));
      } catch (err) {
        console.error(err);
        setError(err.message);
      }
    };

    fetchEnums();
  }, []);

  // ✅ Fetch book details after enums are loaded
  useEffect(() => {
    if (id) {
      fetch(`${BOOK_URL}/${id}`)
        .then((res) => {
          if (!res.ok) throw new Error("Failed to fetch book details");
          return res.json();
        })
        .then((data) =>
          setForm({
            bookId: String(data.bookId),
            title: data.title,
            author: data.author,
            category: data.category || (categories[0]?.name ?? ""),
            status: data.status || (statuses[0]?.name ?? ""),
            availability: data.availability || "AVAILABLE",
          })
        )
        .catch((err) => setError(err.message));
    }
  }, [id, categories, statuses]);

  const validateForm = () => {
    if (!form.bookId.trim()) return "Book ID cannot be empty";
    const bookIdNum = Number(form.bookId);
    if (bookIdNum <= 0) return "Book ID must be greater than 0";
    if (!/^\d+$/.test(form.bookId)) return "Book ID must contain only numbers";
    if (!form.title.trim()) return "Title cannot be empty from UI validations";
    if (form.title.length > 35)
      return "Title cannot exceed 35 characters from UI validations";
    if (!form.author.trim())
      return "Author cannot be empty from UI validations";
    if (form.author.length > 35)
      return "Author cannot exceed 35 characters from UI validations";
    return null;
  };

  const submit = async (e) => {
    e.preventDefault();
    setError(null);

    const validationError = validateForm();
    if (validationError) {
      setError(validationError);
      return;
    }

    try {
      const res = await fetch(`${BOOK_URL}/updatebook`, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(form),
      });

      if (!res.ok) {
        const errData = await res.json();
        throw new Error(errData.message || "Update failed");
      }
      alert("Book updated successfully");
      setForm({
        ...initialForm,
        category: categories[0]?.name || "",
        status: statuses[0]?.name || "",
      });
      navigate("/books");
    } catch (err) {
      setError(err.message || "Failed to update book");
    }
  };

  return (
    <div>
      <h3>Update Book</h3>
      {error && <div style={{ color: "red" }}>{error}</div>}
      <form onSubmit={submit}>
        <div>
          <label>Book ID:</label><br />
          <input
            value={form.bookId}
            onChange={(e) => setForm({ ...form, bookId: e.target.value })}
            disabled
          />
        </div><br />
        <div>
          <label>Title:</label><br />
          <input
            value={form.title}
            onChange={(e) => setForm({ ...form, title: e.target.value })}
            disabled
          />
        </div><br />
        <div>
          <label>Author:</label><br />
          <input
            value={form.author}
            onChange={(e) => setForm({ ...form, author: e.target.value })}
            disabled
          />
        </div><br />
        <div>
          <label>Category:</label><br />
          <select
            value={form.category}
            onChange={(e) => setForm({ ...form, category: e.target.value })}
            disabled
          >
            {categories.map((c) => (
              <option key={c.name} value={c.name}>
                {c.name}
              </option>
            ))}
          </select>
        </div><br />
        <div>
          <label>Status:</label><br />
          <select
            value={form.status}
            onChange={(e) => setForm({ ...form, status: e.target.value })}
            disabled
          >
            {statuses.map((s) => (
              <option key={s.name} value={s.name}>
                {s.name}
              </option>
            ))}
          </select>
        </div><br />
        <button type="submit">Update</button>
        <button type="button" onClick={() => navigate(-1)} style={{ marginLeft: 8 }}>
          Back
        </button>
      </form>
    </div>
  );
}
