import { useState, useEffect } from "react";
import { useParams, useNavigate } from "react-router-dom";

export default function UpdateAvailability() {
  const { id } = useParams();
  const navigate = useNavigate();

  // Initial form state
  const initialForm = {
    bookId: "",
    availability: "",
  };

  const [form, setForm] = useState(initialForm);
  const [availabilityStatuses, setAvailabilityStatuses] = useState([]);
  const [error, setError] = useState(null);

  // 🔹 Fetch enums from backend
  useEffect(() => {
    const fetchEnums = async () => {
      try {
        const res = await fetch("http://localhost:8080/api/enums");
        if (!res.ok) throw new Error("Failed to fetch enums");
        const data = await res.json();

        const enums = data.enums || {};
        const availData = enums.AvailabilityStatus || [];

        setAvailabilityStatuses(availData);

        // If no book loaded yet, set default availability from enums
        if (!form.availability && availData.length > 0) {
          setForm((f) => ({ ...f, availability: availData[0].name }));
        }
      } catch (err) {
        console.error(err);
        setError("Failed to fetch availability statuses");
      }
    };

    fetchEnums();
  }, []);

  // 🔹 Fetch book details if ID exists
  useEffect(() => {
    if (id) {
      fetch(`http://localhost:8080/api/books/${id}`)
        .then((res) => {
          if (!res.ok) throw new Error("Failed to fetch book details");
          return res.json();
        })
        .then((data) =>
          setForm({
            bookId: String(data.bookId),
            availability: data.availability || "",
          })
        )
        .catch((err) => setError(err.message));
    }
  }, [id]);

  // 🔹 Validation
  const validateForm = () => {
    if (!form.bookId.trim()) return "Book ID cannot be empty";
    if (!/^\d+$/.test(form.bookId)) return "Book ID must contain only numbers";
    if (Number(form.bookId) <= 0) return "Book ID must be greater than 0";
    if (!form.availability) return "Please select availability status";
    return null;
  };

  // 🔹 Submit
  const submit = async (e) => {
    e.preventDefault();
    setError(null);

    const validationError = validateForm();
    if (validationError) {
      setError(validationError);
      return;
    }

    const payload = {
      bookId: Number(form.bookId),
      availability: form.availability,
    };

    try {
      const res = await fetch(
        "http://localhost:8080/api/books/updateavailability",
        {
          method: "PUT",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify(payload),
        }
      );

      if (!res.ok) {
        const errData = await res.json();
        throw new Error(errData.message || "Update failed");
      }

      alert("Book availability updated successfully");
      navigate("/books");
    } catch (err) {
      setError(err.message || "Failed to update availability");
    }
  };

  return (
    <div>
      <h3>Update Book Availability</h3>
      {error && <div style={{ color: "red" }}>{error}</div>}

      <form onSubmit={submit}>
        <div>
          <label>Book ID:</label>
          <br />
          <input
            value={form.bookId}
            onChange={(e) => setForm({ ...form, bookId: e.target.value })}
          />
        </div>
        <br />

        <div>
          <label>Availability:</label>
          <br />
          <select
            value={form.availability}
            onChange={(e) =>
              setForm({ ...form, availability: e.target.value })
            }
          >
            {availabilityStatuses.map((a) => (
              <option key={a.name} value={a.name}>
                {a.name}
              </option>
            ))}
          </select>
        </div>
        <br />

        <button type="submit">Update</button>
        <button
          type="button"
          onClick={() => navigate(-1)}
          style={{ marginLeft: 8 }}
        >
          Back
        </button>
      </form>
    </div>
  );
}
