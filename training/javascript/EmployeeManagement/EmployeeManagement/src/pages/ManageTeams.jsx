import React, { useState } from "react";
import axios from "axios";
import { useSelector } from "react-redux";

const ManageTeams = () => {
  const [empId, setEmpId] = useState("");
  const [teamId, setTeamId] = useState("");
  const [message, setMessage] = useState(null);
  const accessToken = useSelector((state) => state.auth.access);

  const handleUpdate = async (e) => {
    e.preventDefault();
    setMessage(null);

    try {
      const res = await axios.patch(
        "http://127.0.0.1:8000/manager/update-team/",
        {
          emp_id: empId,
          team_id: teamId,
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
          error.response?.data?.message || "Failed to update team ID.",
      });
    }
  };

  return (
    <div className="p-4 rounded-xl shadow-md bg-white max-w-lg mx-auto mt-8">
      <h2 className="text-xl font-bold mb-4 text-center">Update Employee Team</h2>
      <form onSubmit={handleUpdate} className="space-y-4">
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
          <label className="block font-semibold">New Team ID:</label>
          <input
            type="number"
            value={teamId}
            onChange={(e) => setTeamId(e.target.value)}
            required
            className="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring"
          />
        </div>
        <button
          type="submit"
          className="w-full bg-blue-600 text-white py-2 px-4 rounded-md hover:bg-blue-700"
        >
          Update Team
        </button>
      </form>

      {message && (
        <div
          className={`mt-4 text-center p-2 rounded-md ${
            message.type === "success"
              ? "bg-green-100 text-green-700"
              : "bg-red-100 text-red-700"
          }`}
        >
          {message.text}
        </div>
      )}
    </div>
  );
};

export default ManageTeams;
