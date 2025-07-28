import React, { useEffect, useState } from "react";
import axios from "axios";

const Dashboard = () => {
  const [tasks, setTasks] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");
  const [user, setUser] = useState(null);

  useEffect(() => {
    const storedUser = JSON.parse(localStorage.getItem("user"));
    setUser(storedUser);
    fetchTasks(storedUser?.access);
  }, []);

  const fetchTasks = async (token) => {
    try {
      const res = await axios.get("http://localhost:8000/api/tasks/", {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      setTasks(res.data);
    } catch (err) {
      setError("Failed to fetch tasks");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="container mt-4">
      <h2 className="mb-4">Dashboard</h2>
      {user && (
        <p>
          Logged in as <strong>{user.username}</strong> ({user.role})
        </p>
      )}
      {loading && <p>Loading...</p>}
      {error && <p className="text-danger">{error}</p>}
      {!loading && !error && (
        <div className="row">
          {tasks.map((task) => (
            <div className="col-md-4 mb-3" key={task.id}>
              <div className="card shadow-sm">
                <div className="card-body">
                  <h5 className="card-title">{task.title}</h5>
                  <p className="card-text">{task.description}</p>
                  <p>
                    Status: <strong>{task.status}</strong>
                  </p>
                  <p>
                    Priority: <strong>{task.priority}</strong>
                  </p>
                  <p>Due Date: {task.due_date || "N/A"}</p>
                </div>
              </div>
            </div>
          ))}
        </div>
      )}
    </div>
  );
};

export default Dashboard;
