import React, { useEffect, useState } from "react";
import axios from "axios";

const AdminView = () => {
  const [tasks, setTasks] = useState([]);
  const [error, setError] = useState("");

  useEffect(() => {
    const user = JSON.parse(localStorage.getItem("user"));
    const access = localStorage.getItem("access");
    if (!user || user.role !== "admin") {
      setError("Access Denied");
      alert("access denied")
      return;
    }

    axios
      .get("http://localhost:8000/api/admin/tasks/", {
        headers: {
          Authorization: 'Bearer '+access,
        },
      })
      .then((res) => setTasks(res.data))
      .catch(() => setError("Failed to fetch admin tasks"));
  }, []);

  return (
    <div className="container mt-4">
      <h2>Admin Task View</h2>
      {error && <p className="text-danger">{error}</p>}
      {!error && tasks.length === 0 && <p>No tasks found.</p>}
      <div className="row">
        {tasks.map((task) => (
          <div className="col-md-4 mb-3" key={task.id}>
            <div className="card shadow-sm">
              <div className="card-body">
                <h3 className="card-title">{task.title}</h3>
                <p>{task.description}</p>
                <p>Assigned to: {task.project.team.name}</p>
                <p>Status: {task.status}</p>
                <p>Priority: {task.priority}</p>
              </div>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
};

export default AdminView;
