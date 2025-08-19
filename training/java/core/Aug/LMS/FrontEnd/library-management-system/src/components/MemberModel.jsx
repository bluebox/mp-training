import { useEffect, useState } from "react";
import { getGenderData } from "../services/memberService";

function MemberModal({
  show,
  onClose,
  onSave,
  formData,
  handleChange,
  editingMember,
  error,
}) {
  const [genderList, setGenderList] = useState([]);
  const [localForm, setLocalForm] = useState(formData);
  const [fieldError, setFieldError] = useState({
    name: "",
    email: "",
    mobile: "",
    address: "",
  });

  useEffect(() => {
    if (show) {
      const fetchdata = async () => {
        const res = await getGenderData();
        if (res.success) {
          setGenderList(res.data);
        }
      };
      fetchdata();
      setLocalForm(formData)
    }
  }, [show,formData]);

  if (!show) return null;
 
  const nameRegex = /^[a-zA-Z .-]{1,50}$/;
  const emailRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
  const mobileRegex = /^[0-9]{10}$/;
  const addressRegex = /^[A-Za-z0-9\s,./#-]{1,100}$/;

  const nameAllowed = /[^a-zA-Z .-]/g; 
  const emailAllowed = /[^a-zA-Z0-9._%+-@]/g;
  const mobileAllowed = /[^0-9]/g;
  const addressAllowed = /[^A-Za-z0-9\s,./#-]/g;

  const handleValidationChange = (e) => {
    const { name, value } = e.target;

    if (name === "name") {
      setFieldError((prev) => ({
        ...prev,
        name: value && !nameRegex.test(value)
          ? "Name allows only letters, space, dot, dash (1-50 chars)."
          : "",
      }));
    }

    if (name === "email") {
      setFieldError((prev) => ({
        ...prev,
        email: value && !emailRegex.test(value)
          ? "Enter a valid email (example: user@domain.com)."
          : "",
      }));
    }

    if (name === "mobile") {
      setFieldError((prev) => ({
        ...prev,
        mobile: value && !mobileRegex.test(value)
          ? "Mobile must be exactly 10 digits."
          : "",
      }));
    }

    if (name === "address") {
      setFieldError((prev) => ({
        ...prev,
        address: value && !addressRegex.test(value)
          ? "Address max 100 chars. Allowed: letters, numbers, , . / # -"
          : "",
      }));
    }

 let sanitized = value;

    if (name === "name") sanitized = sanitized.replace(nameAllowed, "");
    if (name === "email") sanitized = sanitized.replace(emailAllowed, "");
    if (name === "mobile") sanitized = sanitized.replace(mobileAllowed, "");
    if (name === "address") sanitized = sanitized.replace(addressAllowed, "");

    if (name === "name" && sanitized.length > 50) sanitized = sanitized.slice(0, 50);
    if (name === "email" && sanitized.length > 100) sanitized = sanitized.slice(0, 100);
    if (name === "mobile" && sanitized.length > 10) sanitized = sanitized.slice(0, 10);
    if (name === "address" && sanitized.length > 100) sanitized = sanitized.slice(0, 100);

    setLocalForm((prev) => ({ ...prev, [name]: sanitized }));
 
  };

  const hasErrors =
    fieldError.name || fieldError.email || fieldError.mobile || fieldError.address;

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
              {editingMember ? "Edit Member" : "Add Member"}
            </h5>
            <button type="button" className="btn-close" onClick={onClose}></button>
          </div>

          <div className="modal-body">
            {error && <div className="alert alert-danger">{error}</div>}

            <input
              type="text"
              name="name"
              className="form-control mb-1"
              placeholder="Name"
              value={localForm.name}
              onChange={handleValidationChange}
            />
            {fieldError.name && (
              <small className="text-danger">{fieldError.name}</small>
            )}

            <input
              type="text"
              name="email"
              className="form-control mb-1 mt-2"
              placeholder="Email"
              value={localForm.email}
              onChange={handleValidationChange}
            />
            {fieldError.email && (
              <small className="text-danger">{fieldError.email}</small>
            )}

            <input
              type="text"
              name="mobile"
              className="form-control mb-1 mt-2"
              placeholder="Mobile"
              value={localForm.mobile}
              onChange={handleValidationChange}
            />
            {fieldError.mobile && (
              <small className="text-danger">{fieldError.mobile}</small>
            )}

            <select
              name="gender"
              className="form-control mb-2 mt-2"
              value={localForm.gender}
             onChange={(e) =>
                setLocalForm((prev) => ({ ...prev, gender: e.target.value }))
              }
            >
              <option value="">Select Gender</option>
              {genderList.map((gender) => (
                <option key={gender} value={gender}>
                  {gender}
                </option>
              ))}
            </select>

            <input
              type="text"
              name="address"
              className="form-control mb-1 mt-2"
              placeholder="Address"
              value={localForm.address}
              onChange={handleValidationChange}
            />
            {fieldError.address && (
              <small className="text-danger">{fieldError.address}</small>
            )}
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
              {editingMember ? "Update" : "Save"}
            </button>
          </div>
        </div>
      </div>
    </div>
  );
}
export default MemberModal;
