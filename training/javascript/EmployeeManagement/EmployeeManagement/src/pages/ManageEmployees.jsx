import React, { useEffect, useState } from "react";
import { useSelector } from "react-redux";
import axiosInstance from "../api/axiosInstance";
import { selectUser } from "../features/auth/authSlice";
import { CgTrash } from "react-icons/cg";

const ManageEmployees = () => {
  const user = useSelector(selectUser);
  const [employees, setEmployees] = useState([]);
  const [form, setForm] = useState({
    emp_id: "",
    username: "",
    password: "",
    emp_name: "",
    dob: "",
    dept: "",
    role: "employee",
  });

  const fetchEmployees = async () => {
    try {
      const res = await axiosInstance.get("/ceo/employees/");
      setEmployees(res.data.results || []);
    } catch (err) {
      console.error("Error fetching employees", err);
    }
  };

  const handleAdd = async () => {
    try {
      await axiosInstance.post("/hr/create-employee/", form);
      fetchEmployees();
    } catch (err) {
      alert(err.response?.data?.message || "Failed to add employee");
    }
  };

  const handleDelete = async (emp_id) => {
    try {
      await axiosInstance.delete("/hr/delete-employee/", { data: { emp_id } });
      fetchEmployees();
    } catch (err) {
      alert("Failed to delete employee");
    }
  };

  useEffect(() => {
    fetchEmployees();
  }, []);

  const canManage = user?.role === "hr" || user?.role === "ceo";

  return (
    <div className="p-4">
      <h2 className="text-xl font-semibold mb-4">Manage Employees</h2>

      {canManage && (
        <div className="mb-4 grid grid-cols-2 gap-2">
          {Object.keys(form).map((key) => (
            <input
              key={key}
              type={key === "dob" ? "date" : "text"}
              placeholder={key}
              value={form[key]}
              onChange={(e) => setForm({ ...form, [key]: e.target.value })}
              className="border p-2 rounded"
            />
          ))}
          <button onClick={handleAdd} className="col-span-2 bg-green-600 text-white py-2 rounded">
            Add Employee
          </button>
        </div>
      )}

      <ul className="space-y-2">
        {employees.map((e) => (
          <li
            key={e.emp_id}
            className="flex justify-between items-center border-b py-2"
          >
            <span>
              {e.emp_id} - {e.emp_name} ({e.user})
            </span>
            {canManage && (
              <button
                onClick={() => handleDelete(e.emp_id)}
                className="text-red-500"
              >
                <CgTrash />
              </button>
            )}
          </li>
        ))}
      </ul>
    </div>
  );
};

export default ManageEmployees;
