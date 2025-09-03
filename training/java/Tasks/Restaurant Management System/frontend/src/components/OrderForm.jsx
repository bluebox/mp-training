import { useState, useEffect } from "react";
import FoodSelection from "./FoodSelection";
import api from "../config/axiosConfig";

export default function OrderForm() {
  const [formData, setFormData] = useState({ name: "", phone: "" });
  const [waiters, setWaiters] = useState([]);
  const [selectedWaiter, setSelectedWaiter] = useState("");
  const [isSubmited, setIsSubmited] = useState(false);
  const [errorName, setErrorName] = useState(false);
  const [errorPhone, setErrorPhone] = useState(false);

  useEffect(() => {
    api
      .get("/api/waiters/available")
      .then((res) => {
        setWaiters(res.data.data);
      })
      .catch((err) => {
        console.error("Error fetching waiters:", err);
        setWaiters([]);
      });
  }, []);

  const handleChange = (e) => {
    let value = e.target.value;

    if (e.target.name === "phone") {
      value = value.replace(/\D/g, "").substring(0, 10);
      setErrorPhone(!/^[6-9]\d{9}$/.test(value));
    } else if (e.target.name === "name") {
      value = value.replace(/[^a-zA-Z ]/g, "");
      setErrorName(!/[a-zA-Z]+/.test(value));
    }

    setFormData({ ...formData, [e.target.name]: value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (errorPhone || errorName || !selectedWaiter) return;
    setIsSubmited(true);
  };

  if (isSubmited) {
    return (
      <FoodSelection
        name={formData.name}
        phone={formData.phone}
        waiterId={selectedWaiter}
      />
    );
  }

  return (
    <div className="container mt-5">
      <div className="row justify-content-center">
        <div className="col-md-6">
          <div className="card shadow">
            <div className="card-body">
              <h3 className="card-title text-center mb-4">Create Order</h3>
              <form onSubmit={handleSubmit}>
                <div className="mb-3">
                  <label htmlFor="name" className="form-label">
                    Customer Name
                  </label>
                  <input
                    type="text"
                    className={`form-control ${errorName ? "is-invalid" : ""}`}
                    id="name"
                    name="name"
                    placeholder="Enter customer name"
                    value={formData.name}
                    onChange={handleChange}
                    required
                  />
                  {errorName && (
                    <div className="invalid-feedback">
                      Name should only have letters and spaces, with at least 1
                      letter
                    </div>
                  )}
                </div>

                <div className="mb-3">
                  <label htmlFor="phone" className="form-label">
                    Phone Number
                  </label>
                  <input
                    type="text"
                    className={`form-control ${errorPhone ? "is-invalid" : ""}`}
                    id="phone"
                    name="phone"
                    placeholder="Enter phone number"
                    value={formData.phone}
                    onChange={handleChange}
                    required
                  />
                  {errorPhone && (
                    <div className="invalid-feedback">
                      Phone number should start with 6-9 and be exactly 10
                      digits
                    </div>
                  )}
                </div>

                <div className="mb-3">
                  <label htmlFor="waiter" className="form-label">
                    Select Waiter
                  </label>
                  <select
                    className="form-select"
                    id="waiter"
                    value={selectedWaiter}
                    onChange={(e) => setSelectedWaiter(e.target.value)}
                    required
                  >
                    <option value="">Choose waiter</option>
                    {waiters.map((waiter) => (
                      <option key={waiter.waiterId} value={waiter.waiterId}>
                        {waiter.name}
                      </option>
                    ))}
                  </select>
                </div>

                <button type="submit" className="btn btn-success w-100">
                  Next
                </button>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}
