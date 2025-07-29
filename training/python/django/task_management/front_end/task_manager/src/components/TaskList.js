import React, { useEffect, useState } from "react";
import axios from "axios";

const TaskList = () => {
  const [tasks, setTasks] = useState([]);
  
  useEffect(() => {
    const access = localStorage.getItem("access");
      axios
      .get("http://localhost:8000/api/tasks/", {
        headers: {
          Authorization: 'Bearer '+access,
        },
      })
      .then((res) => setTasks(res.data))
      .catch(console.log("error in retrieving tasks"));
  }, []);

  return (
    <div className="card mt-4">
      <div className="card-header">All Tasks</div>
      <div className="card-body">
        {tasks.length === 0 ? (
          <p>No tasks available.</p>
        ) : (
          <table className="table">
            <thead>
              <tr>
                <th>Title</th>
                <th>Status</th>
                <th>Priority</th>
                <th>Due Date</th>
                <th>Project</th>
                <th>Created By</th>
                <th>Created At</th>
              </tr>
            </thead>
            <tbody>
              {tasks.map((task) => (
                <tr key={task.id}>
                  <td>{task.title}</td>
                  <td>{task.status}</td>
                  <td>{task.priority}</td>
                  <td>{task.due_date || "N/A"}</td>
                  <td>{task.project.name}</td>
                  <td>{task.created_by.username}</td>
                  <td>{task.created_at}</td>
                </tr>
              ))}
            </tbody>
          </table>
        )}
      </div>
    </div>
  );
};

export default TaskList;
