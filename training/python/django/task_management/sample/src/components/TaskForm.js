import React, { useState, useEffect } from "react";
import axios from "axios";

const TaskForm = ({ onTaskCreated }) => {
  const [projects, setProjects] = useState([]);
  const [formData, setFormData] = useState({
    title: "",
    description: "",
    priority: "medium",
    status: "todo",
    due_date: "",
    project: "",
  });

  const user = JSON.parse(localStorage.getItem("user"));

  useEffect(() => {
    const fetchProjects = async () => {
      try {
        const response = await axios.get("http://localhost:8000/api/projects/");
        setProjects(response.data);
      } catch (error) {
        console.error("Error fetching projects:", error);
      }
    };

    fetchProjects();
  }, []);

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const taskPayload = {
        ...formData,
        created_by: user.id,
      };

      const response = await axios.post(
        "http://localhost:8000/api/tasks/",
        taskPayload
      );
      onTaskCreated(response.data);
      setFormData({
        title: "",
        description: "",
        priority: "medium",
        status: "todo",
        due_date: "",
        project: "",
      });
    } catch (error) {
      console.error("Error creating task:", error);
    }
  };

  return (
    <div className="card mb-4">
      <div className="card-header">Create New Task</div>
      <div className="card-body">
        <form onSubmit={handleSubmit}>
          <div className="mb-3">
            <label>Title</label>
            <input
              name="title"
              className="form-control"
              value={formData.title}
              onChange={handleChange}
              required
            />
          </div>

          <div className="mb-3">
            <label>Description</label>
            <textarea
              name="description"
              className="form-control"
              value={formData.description}
              onChange={handleChange}
            />
          </div>

          <div className="mb-3">
            <label>Priority</label>
            <select
              name="priority"
              className="form-select"
              value={formData.priority}
              onChange={handleChange}
            >
              <option value="low">Low</option>
              <option value="medium">Medium</option>
              <option value="high">High</option>
            </select>
          </div>

          <div className="mb-3">
            <label>Status</label>
            <select
              name="status"
              className="form-select"
              value={formData.status}
              onChange={handleChange}
            >
              <option value="todo">To Do</option>
              <option value="in_progress">In Progress</option>
              <option value="done">Done</option>
            </select>
          </div>

          <div className="mb-3">
            <label>Due Date</label>
            <input
              type="date"
              name="due_date"
              className="form-control"
              value={formData.due_date}
              onChange={handleChange}
            />
          </div>

          <div className="mb-3">
            <label>Project</label>
            <select
              name="project"
              className="form-select"
              value={formData.project}
              onChange={handleChange}
              required
            >
              <option value="">Select a project</option>
              {projects.map((project) => (
                <option key={project.id} value={project.id}>
                  {project.name}
                </option>
              ))}
            </select>
          </div>

          <button type="submit" className="btn btn-primary">
            Create Task
          </button>
        </form>
      </div>
    </div>
  );
};

export default TaskForm;
