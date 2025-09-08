import { useState, useEffect } from "react";
import { useParams, useNavigate } from "react-router-dom";

const BASE_URL = "http://localhost:8080/api/members";
const ENUM_URL = "http://localhost:8080/api/enums";

export default function UpdateMember() {
  const { id } = useParams();
  const navigate = useNavigate();

  const initialForm = {
    memberId: "",
    name: "",
    email: "",
    mobile: "",
    gender: "",
    address: "",
    updatedBy: "",
  };

  const [form, setForm] = useState(initialForm);
  const [genders, setGenders] = useState([]);
  const [error, setError] = useState(null);

  // ✅ Fetch enums (all at once)
  useEffect(() => {
    const fetchEnums = async () => {
      try {
        const res = await fetch(ENUM_URL);
        if (!res.ok) throw new Error("Failed to fetch enums");

        const data = await res.json();
        console.log("Enums Response:", data);

        const enums = data.enums || {};
        const genderData = enums.Gender || [];

        setGenders(genderData);

        setForm((f) => ({
          ...f,
          gender: genderData.length > 0 ? genderData[0].name : "",
        }));
      } catch (err) {
        console.error(err);
        setError("Failed to fetch enum values");
      }
    };

    fetchEnums();
  }, []);

  // ✅ Fetch member details after enums are loaded
  useEffect(() => {
    if (id && genders.length > 0) {
      fetch(`${BASE_URL}/${id}`)
        .then((res) => {
          if (!res.ok) throw new Error("Failed to fetch member details");
          return res.json();
        })
        .then((data) =>
          setForm({
            memberId: String(data.memberId),
            name: data.name || "",
            email: data.email || "",
            mobile: data.mobile || "",
            gender: data.gender || (genders[0]?.name ?? ""),
            address: data.address || "",
            updatedBy: data.updatedBy || "",
          })
        )
        .catch((err) => setError(err.message));
    }
  }, [id, genders]);

  const validateForm = () => {
    if (!String(form.memberId).trim()) return "Member ID cannot be empty";
    const memberIdNum = Number(form.memberId);
    if (memberIdNum <= 0) return "Member ID must be greater than 0";
    if (!/^\d+$/.test(form.memberId)) return "Member ID must contain only numbers";
    if (!form.name.trim()) return "Name cannot be empty from UI validations";
    if (form.name.length > 35)
      return "Name cannot exceed 35 characters from UI validations";
    if (!form.email.trim()) return "Email cannot be empty from UI validations";
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailRegex.test(form.email))
      return "Invalid email format from UI validations";
    if (!form.mobile.trim()) return "Mobile cannot be empty from UI validations";
    const mobileRegex = /^\d{10}$/;
    if (!mobileRegex.test(form.mobile))
      return "Mobile must be 10 digits from UI validations";
    if (!form.address.trim()) return "Address cannot be empty from UI validations";
    if (form.address.length > 50)
      return "Address cannot exceed 50 characters from UI validations";
    if (!form.updatedBy.trim())
      return "Updator name cannot be empty from UI validations";
    if (form.updatedBy.length > 35)
      return "Updator name cannot exceed 35 characters from UI validations";
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
      const res = await fetch(`${BASE_URL}/update`, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(form),
      });

      if (!res.ok) {
        const errData = await res.json();
        throw new Error(errData.message || "Update failed");
      }

      alert("Member updated successfully");
      setForm({
        ...initialForm,
        gender: genders[0]?.name || "",
      });
      navigate("/members");
    } catch (err) {
      setError(err.message || "Failed to update member");
    }
  };

  return (
    <div>
      <h3>Update Member</h3>
      {error && <div style={{ color: "red", marginBottom: 12 }}>{error}</div>}
      <form onSubmit={submit}>
        <div>
          <label>Member ID:</label><br />
          <input value={form.memberId} disabled />
        </div><br />

        <div>
          <label>Name:</label><br />
          <input
            value={form.name}
            onChange={(e) => setForm({ ...form, name: e.target.value })}
          />
        </div><br />

        <div>
          <label>Email:</label><br />
          <input
            value={form.email}
            onChange={(e) => setForm({ ...form, email: e.target.value })}
          />
        </div><br />

        <div>
          <label>Mobile:</label><br />
          <input
            value={form.mobile}
            onChange={(e) => setForm({ ...form, mobile: e.target.value })}
          />
        </div><br />

        <div>
          <label>Gender:</label><br />
          <select
            value={form.gender}
            onChange={(e) => setForm({ ...form, gender: e.target.value })}
            disabled
          >
            {genders.map((g) => (
              <option key={g.name} value={g.name}>
                {g.name}
              </option>
            ))}
          </select>
        </div><br />

        <div>
          <label>Address:</label><br />
          <textarea
            value={form.address}
            onChange={(e) => setForm({ ...form, address: e.target.value })}
          />
        </div><br />

        <div>
          <label>Updated By:</label><br />
          <input
            value={form.updatedBy}
            onChange={(e) => setForm({ ...form, updatedBy: e.target.value })}
          />
        </div><br />

        <button type="submit">Update</button>
        <button
          type="button"
          onClick={() => navigate("/members")}
          style={{ marginLeft: 8 }}
        >
          Back
        </button>
      </form>
    </div>
  );
}
