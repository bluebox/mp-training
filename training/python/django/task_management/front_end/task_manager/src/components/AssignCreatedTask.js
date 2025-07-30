import { useEffect, useState } from "react";
import api from "../api/axios";


export default function AssignTask(){

    const [tasks, setTasks] = useState([])
    const [users, setUsers] = useState([])
    const [formData, setFormData] = useState({
        role: "",
        task: "",
        user: ""
    })
    const current_user_role = JSON.parse(localStorage.getItem("user")).role;

    useEffect(() => {
    const fetchTasks = async () => {
      try {
        const response = await api.get("unassigned/tasks/");
        setTasks(response.data);
      } catch (error) {
        console.error("Error fetching projects:", error);
      }
    };
    const fetchMembers = async () => {
        try {
            const response = await api.get("members/");
            setUsers(current_user_role==="admin"?response.data:response.data.members);
      } catch (error) {
        console.error("Error fetching members:", error);
      }
    };
    fetchMembers();
    fetchTasks();
    // eslint-disable-next-line
  }, []);

    const handleChange = (e) => {
         setFormData({ ...formData, [e.target.name]: e.target.value });
    };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
        const response = await api.post("tasks/assign/",formData);
        console.log(response.data);
        alert("Task assigned sucessfully");
    }
    catch (err) {
        alert("Error assigning failed.");
    }
      
      setFormData({
        role: "",
        task: "",
        user: ""
    });
  };

    return (<div>
        <div>
            <label>task</label>
            <select name="task" value={formData.task} onChange={handleChange} required>
              <option value="">Select a task</option>
              {tasks.map((task) => (
                <option key={task.id} value={task.id}>
                  {task.title}
                </option>
              ))}
            </select>
        </div>
        <div>
            <label>member</label>
            <select name="user" value={formData.user} onChange={handleChange} required>
            <option value="">Select a member</option>
            {Array.isArray(users) &&
                (current_user_role === 'lead'
                ? users.map((user) => (
                    <option key={user.user.id} value={user.user.id}>
                        {user.user.username}
                    </option>
                    ))
                : users.map((user) => (
                    <option key={user.id} value={user.id}>
                        {user.username}
                    </option>
                    )))}
            </select>

        </div>
        <div>
            <label>Role</label>
            <input name="role" value={formData.role} onChange={handleChange} required/>
        </div>

        <button type="submit" onClick={handleSubmit}>Create Task</button>
    </div>)
}
