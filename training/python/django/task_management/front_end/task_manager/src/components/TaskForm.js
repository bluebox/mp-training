import React, { useState, useEffect } from "react";
import api from "../api/axios"
const TaskCreate = () => {
  const [projects, setProjects] = useState([]);
  const [data, setData] = useState([]);
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
        const response = await api.get("projects/");
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
      setFormData({...formData, due_date: null});
      const taskPayload = {
        ...formData,
        created_by: user.id,
      };

    try {
      const response = await api.post("tasks/create/",taskPayload)
      setData(response.data);
      alert("Task created sucessfully");
    } 
    catch (err) {
      alert("Error creating failed.");
    }
      
      setFormData({
        title: "",
        description: "",
        priority: "medium",
        status: "todo",
        due_date: "",
        project: "",
      });
      console.log(data);
    } catch (error) {
      console.error("Error creating task:", error);
    }
  };

  return (
    <div>
      <div className="card-body">
        <form onSubmit={handleSubmit}>
          <div>
            <label>Title</label>
            <input name="title" value={formData.title} onChange={handleChange} required/>
          </div>

          <div>
            <label>Description</label>
            <input name="description" value={formData.description} onChange={handleChange} />
          </div>

          <div>
            <label>Priority</label>
            <select name="priority" value={formData.priority} onChange={handleChange}>
              <option value="low">Low</option>
              <option value="medium">Medium</option>
              <option value="high">High</option>
            </select>
          </div>

          <div>
            <label>Status</label>
            <select name="status" value={formData.status} onChange={handleChange}>
              <option value="todo">To Do</option>
              <option value="in_progress">In Progress</option>
              <option value="done">Done</option>
            </select>
          </div>

          <div>
            <label>Due Date</label>
            <input type="date" name="due_date" value={formData.due_date} onChange={handleChange}/>
          </div>

          <div>
            <label>Project</label>
            <select name="project" value={formData.project} onChange={handleChange} required>
              <option value="">Select a project</option>
              {projects.map((project) => (
                <option key={project.id} value={project.id}>
                  {project.name}
                </option>
              ))}
            </select>
          </div>

          <button type="submit">Create Task</button>
        </form>
      </div>
    </div>
  );
};

export default TaskCreate;
