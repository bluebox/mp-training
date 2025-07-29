import React, { useEffect, useState } from "react";
import { useSelector } from "react-redux";
import axiosInstance from "../api/axiosInstance";
import { selectUser } from "../features/feature/auth/authSlice";
import { CgTrash } from "react-icons/cg";

const Departments = () => {
  const user = useSelector(selectUser);
  const [departments, setDepartments] = useState([]);
  const [newDept, setNewDept] = useState("");

  const fetchDepartments = async () => {
    try {
      const res = await axiosInstance.get("/departments/");
      setDepartments(res.data.data);
    } catch (err) {
      console.error("Error fetching departments", err);
    }
  };

  const handleAdd = async () => {
    if (!newDept.trim()) return;
    try {
      const res = await axiosInstance.post("/departments/", { dept_name: newDept });
      fetchDepartments();
      setNewDept("");
    } catch (err) {
      alert(err.response?.data?.message || "Failed to add department");
    }
  };

  const handleDelete = async (id) => {
    try {
      await axiosInstance.delete("/departments/", { data: { id } });
      fetchDepartments();
    } catch (err) {
      alert("Could not delete department");
    }
  };

  useEffect(() => {
    fetchDepartments();
  }, []);

  return (
    <div className="p-4">
      {/* <h2 className="text-xl font-semibold mb-4">Departments</h2> */}

      {(user?.role === "ceo") && (
        <div className="mb-4 flex gap-2">
          <input
            type="text"
            placeholder="Enter dept code (e.g., FSD)"
            value={newDept}
            onChange={(e) => setNewDept(e.target.value.toUpperCase())}
            className="border px-2 py-1 rounded"
          />
          <button onClick={handleAdd} className="bg-blue-500 text-white px-3 py-1 rounded">
            Add
          </button>
        </div>
      )}

      <ul className="space-y-2">
        {departments.map((dept) => (
          <li key={dept.id} className="flex justify-between items-center border-b py-1">
            <span>{dept.dept_name}</span>
            {user?.role === "ceo" && (
              <button onClick={() => handleDelete(dept.id)} className="text-red-500">
                <CgTrash />
              </button>
            )}
          </li>
        ))}
      </ul>
    </div>
  );
};


export default Departments;

