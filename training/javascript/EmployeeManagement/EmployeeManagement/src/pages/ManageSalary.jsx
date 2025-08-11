import React, { useState } from "react";
import axios from "axios";
import { useSelector } from "react-redux";

const ManageSalary = () => {
  const [empId, setEmpId] = useState("");
  const [salary, setSalary] = useState("");
  const [message, setMessage] = useState(null);
  const accessToken = useSelector((state) => state.auth.access);
  const handleSalaryUpdate = async (e) => {
    e.preventDefault();
    try {
      const res = await axios.post(
        "http://127.0.0.1:8000/employee/update-salary/",
        {
          emp_id: empId,
          salary: salary,
        },
        {
          headers: {
            Authorization: `Bearer ${accessToken}`,
          },
        }
      );
      setMessage({ type: "success", text: res.data.message });
    } catch (error) {
      setMessage({
        type: "error",
        text:
          error.response?.data?.message ||
          "Failed to update salary. Please try again.",
      });
    }
  };

  return (
    <div className="p-4 rounded-xl shadow-md bg-white max-w-lg mx-auto mt-8">
      <h2 className="text-xl font-bold mb-4 text-center">Update Employee Salary</h2>
      <form onSubmit={handleSalaryUpdate} className="space-y-4">
        <div>
          <label className="block font-semibold">Employee ID:</label>
          <input
            type="text"
            value={empId}
            onChange={(e) => setEmpId(e.target.value)}
            required
            className="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring"
          />
        </div>
        <div>
          <label className="block font-semibold">New Salary:</label>
          <input
            type="number"
            value={salary}
            onChange={(e) => setSalary(e.target.value)}
            required
            className="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring"
          />
        </div>
        <button
          type="submit"
          className="w-full bg-blue-600 text-white py-2 px-4 rounded-md hover:bg-blue-700"
        >
          Update Salary
        </button>
      </form>

      {message && (
        <div
          className={`mt-4 text-center p-2 rounded-md ${
            message.type === "success" ? "bg-green-100 text-green-700" : "bg-red-100 text-red-700"
          }`}
        >
          {message.text}
        </div>
      )}
    </div>
  );
};

export default ManageSalary;
