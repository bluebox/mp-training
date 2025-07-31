import React, { useEffect, useState } from "react";
import api from "../api/axios";

const Dashboard = () => {
  const [tasks, setTasks] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");
  const user = JSON.parse(localStorage.getItem("user"));
  const [isEdit, setIsEdit] = useState(false);
  const [updateTask, setUpdateTask] = useState({
    id:"",
    title:"",
    description:"",
    status:"",
    priority:"",
    due_date:""
  })

  const handleChange = (e) => {
    setUpdateTask((prev) => ({
      ...prev,
      [e.target.name]: e.target.value
    }));
  };


  function handleEdit(taskData){
    setIsEdit(true);
    setUpdateTask(taskData);
  }

  const handleSave = async () => {
    try {
      api.put('tasks/'+updateTask.id+'/',updateTask);
    } catch (err) {
      console.log("Failed to update task",err);
    }
    setUpdateTask({
        id:"",
        title:"",
        description:"",
        status:"",
        priority:"",
        due_date:""
      });
      setIsEdit(false);
    window.location.reload();
  };


  useEffect(() => {
    
    try {
        var URL;
        if(user.role === 'member'){
          URL = "assigned/tasks/";
        }
        else if(user.role === 'lead'){
          URL = "lead/tasks/";
        }
        else{
          URL = "tasks/";
        }

        const fetchTasks = async () => {
        try{  
          const response = await api.get(URL)
          setTasks(response.data);
        }
        catch{
          console.log("error in retrieving tasks")
        };
      }
      fetchTasks();
      } catch (err) {
        setError("Failed to fetch tasks");
      } finally {
        setLoading(false);
      }
      // eslint-disable-next-line
  }, []);
  if(user.role === "admin"){
    return (
    <div>
      <h2>Dashboard</h2>
      {loading && <p>Loading...</p>}
      {error && <p>{error}</p>}
      {!loading && !error && (
        <table>
          <thead>
            <tr>
              <th>title</th>
              <th>description</th>
              <th>status</th>
              <th>priority</th>
              <th>due_date</th>
            </tr>
          </thead>
          <tbody>
            {tasks.map((task) => {
            const taskData = {
              id:  user.role === "member" ? task.task.id : task.id,
              title:  user.role === "member" ? task.task.title : task.title,
              description:  user.role === "member" ? task.task.description : task.description,
              status:  user.role === "member" ? task.task.status : task.status,
              priority:user.role === "member" ? task.task.priority : task.priority,
              due_date:user.role === "member" ? task.task.due_date : task.due_date
            }
            const taskId = taskData.id;

            return (
              <tr key={taskId}>
                
                <td>{isEdit && updateTask.id === taskData.id ? (
                  <input type="text" name="title" value={updateTask.title} onChange={handleChange}/>):
                  (taskData.title)}
                </td>


                <td>{isEdit && updateTask.id === taskData.id ? (
                  <input type="text" name="description" value={updateTask.description} onChange={handleChange}/>)
                  :taskData.description || "no description available"}
                </td>
                
                <td>
                  {isEdit && updateTask.id === taskData.id ? (
                    <select name="status" value={updateTask.status} onChange={handleChange}>
                      <option value="in_progress">in_progress</option>
                      <option value="todo">todo</option>
                      <option value="done">done</option>
                    </select>
                  ) : (taskData.status)}
                </td>


                <td>{isEdit && updateTask.id === taskData.id ? (
                  <select name="priority" value={updateTask.priority} onChange={handleChange}>
                      <option value="low">low</option>
                      <option value="medium">medium</option>
                      <option value="high">high</option>
                    </select>
                  )
                  :taskData.priority}
                </td>


                <td>{isEdit && updateTask.id === taskData.id ? (
                    <input type="date" name="due_date" value={updateTask.due_date} onChange={handleChange}/>)
                    :taskData.due_date || "N/A"}</td>
                <td>
                  {isEdit && updateTask.id === taskData.id ? (
                    <>
                      <button onClick={handleSave}>Save</button>
                      <button onClick={() => {
                              setIsEdit(false);
                              setUpdateTask({
                                    id:"",
                                    title:"",
                                    description:"",
                                    status:"",
                                    priority:"",
                                    due_date:""
                                  });
                      }}>Cancel</button>
                    </>
                  ) : (
                    <button onClick={() => handleEdit(taskData)}>Edit</button>
                  )}
                </td>
              </tr>
            );
          })}

          </tbody>
        </table>
      )}
    </div>
  );
  }
  return (
    <div>
      <h2>Dashboard</h2>
      {loading && <p>Loading...</p>}
      {error && <p>{error}</p>}
      {!loading && !error && (
        <table>
          <thead>
            <tr>
              <th>title</th>
              <th>description</th>
              <th>status</th>
              <th>priority</th>
              <th>due_date</th>
            </tr>
          </thead>
          <tbody>
            {tasks.map((task) => {
            const taskData = {
              id:  user.role === "member" ? task.task.id : task.id,
              title:  user.role === "member" ? task.task.title : task.title,
              description:  user.role === "member" ? task.task.description : task.description,
              status:  user.role === "member" ? task.task.status : task.status,
              priority:user.role === "member" ? task.task.priority : task.priority,
              due_date:user.role === "member" ? task.task.due_date : task.due_date
            }
            const taskId = taskData.id;

            return (
              <tr key={taskId}>
                <td>{taskData.title}</td>
                <td>{taskData.description || "no description available"}</td>
                <td>
                  {isEdit && updateTask.id === taskData.id ? (
                    <select name="status" value={updateTask.status} onChange={handleChange}>
                      <option value="in_progress">in_progress</option>
                      <option value="todo">todo</option>
                      <option value="done">done</option>
                    </select>
                  ) : (
                    taskData.status
                  )}
                </td>
                <td>{taskData.priority}</td>
                <td>{taskData.due_date || "N/A"}</td>
                <td>
                  {isEdit && updateTask.id === taskData.id ? (
                    <>
                      <button onClick={handleSave}>Save</button>
                      <button onClick={() => {
                              setIsEdit(false);
                              setUpdateTask({
                                    id:"",
                                    title:"",
                                    description:"",
                                    status:"",
                                    priority:"",
                                    due_date:""
                                  });
                      }}>Cancel</button>
                    </>
                  ) : (
                    <button onClick={() => handleEdit(taskData)}>Edit</button>
                  )}
                </td>
              </tr>
            );
          })}

          </tbody>
        </table>
      )}
    </div>
  );
}



export default Dashboard;
