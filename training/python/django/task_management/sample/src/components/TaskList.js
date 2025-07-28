import React, { useEffect, useState } from "react";
import axios from "axios";

const TaskList = () => {
  const [tasks, setTasks] = useState([]);
  const user = JSON.parse(localStorage.getItem("user"));

  useEffect(() => {
    const fetchTasks = async () => {
      try {
        const response = await axios.get("http://localhost:8000/api/tasks/");
        setTasks(response.data);
      } catch (error) {
        console.error("Error fetching tasks:", error);
      }
    };

    fetchTasks();
  }, []);

  return (
    <div className="card mt-4">
      <div className="card-header">All Tasks</div>
      <div className="card-body">
        {tasks.length === 0 ? (
          <p>No tasks available.</p>
        ) : (
          <table className="table table-bordered table-striped">
            <thead className="table-light">
              <tr>
                <th>Title</th>
                <th>Status</th>
                <th>Priority</th>
                <th>Due Date</th>
                <th>Project</th>
                <th>Created By</th>
              </tr>
            </thead>
            <tbody>
              {tasks.map((task) => (
                <tr key={task.id}>
                  <td>{task.title}</td>
                  <td>{task.status}</td>
                  <td>{task.priority}</td>
                  <td>{task.due_date || "N/A"}</td>
                  <td>{task.project_name || task.project}</td>
                  <td>{task.created_by_name || task.created_by}</td>
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
