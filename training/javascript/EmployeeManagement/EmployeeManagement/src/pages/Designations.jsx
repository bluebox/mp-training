import React, { useEffect, useState } from "react";
import { useSelector } from "react-redux";
import axiosInstance from "../api/axiosInstance";
import { selectUser } from "../features/feature/auth/authSlice";
import { CgTrash } from "react-icons/cg";

const Designations = () => {
  const user = useSelector(selectUser);
  const [designations, setDesignations] = useState([]);
  const [newDesig, setNewDesig] = useState("");

  const fetchDesignations = async () => {
    try {
      const res = await axiosInstance.get("/designation/");
      setDesignations(res.data.data);
    } catch (err) {
      console.error("Error fetching designations", err);
    }
  };

  const handleAdd = async () => {
    if (!newDesig.trim()) return;
    try {
      await axiosInstance.post("/designation/", { designation: newDesig });
      fetchDesignations();
      setNewDesig("");
    } catch (err) {
      alert(err.response?.data?.message || "Failed to add designation");
    }
  };

  const handleDelete = async (title) => {
    try {
      await axiosInstance.delete("/designation/", { data: { designation: title } });
      fetchDesignations();
    } catch (err) {
      alert("Failed to delete designation");
    }
  };

  useEffect(() => {
    fetchDesignations();
  }, []);

  return (
    <div className="p-4">
      <h2 className="text-xl font-semibold mb-4">Designations</h2>

      {user?.role === "ceo" && (
        <div className="mb-4 flex gap-2">
          <input
            type="text"
            placeholder="Enter designation (e.g., ASE)"
            value={newDesig}
            onChange={(e) => setNewDesig(e.target.value.toUpperCase())}
            className="border px-2 py-1 rounded"
          />
          <button onClick={handleAdd} className="bg-green-600 text-white px-3 py-1 rounded">
            Add
          </button>
        </div>
      )}

      <ul className="space-y-2">
        {designations.map((d) => (
          <li key={d.id} className="flex justify-between items-center border-b py-1">
            <span>{d.designation}</span>
            {user?.role === "ceo" && (
              <button onClick={() => handleDelete(d.designation)} className="text-red-500">
                <CgTrash />
              </button>
            )}
          </li>
        ))}
      </ul>
    </div>
  );
};

export default Designations;
