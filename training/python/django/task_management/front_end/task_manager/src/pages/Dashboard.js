import React, { useEffect, useState } from "react";
import axios from "axios";

const Dashboard = () => {
  const [tasks, setTasks] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");
  const user = JSON.parse(localStorage.getItem("user"));


  useEffect(() => {
    const token = localStorage.getItem("access");
    
    try {
        var URL;
        if(user.role === 'member'){
          URL = "http://localhost:8000/api/assigned/tasks/";
        }
        else if(user.role === 'lead'){
          URL = "http://localhost:8000/api/lead/tasks/";
        }
        else{
          URL = "http://localhost:8000/api/tasks/";
        }

        axios.get(URL, {
          headers: {
            Authorization: ('Bearer '+token),
          },
        })
        .then(res=>{
          setTasks(res.data);
        })
      } catch (err) {
        setError("Failed to fetch tasks");
      } finally {
        setLoading(false);
      }
      // eslint-disable-next-line
  }, []);
  if(user.role === "member"){
    return (
      <div>
        <h2>Dashboard</h2>
        {loading && <p>Loading...</p>}
        {error && <p>{error}</p>}
        {!loading && !error && (
          <div className="row">
            {tasks.map((task) => (
              <div className="col-md-4 mb-3" key={task.id}>
                <div className="card shadow-sm">
                  <div className="card-body">
                    <h3 className="card-title">{task.task.title}</h3>
                    <p className="card-text">{task.task.description}</p>
                    <p>
                      Status: <strong>{task.task.status}</strong>
                    </p>
                    <p>
                      Priority: <strong>{task.task.priority}</strong>
                    </p>
                    <p>Due Date: {task.task.due_date || "N/A"}</p>
                  </div>
                </div>
              </div>
            ))}
          </div>
        )}
      </div>
    );
  }
  else{
    return (
      <div>
        <h2>Dashboard</h2>
        {loading && <p>Loading...</p>}
        {error && <p>{error}</p>}
        {!loading && !error && (
          <div className="row">
            {tasks.map((task) => (
              <div className="col-md-4 mb-3" key={task.id}>
                <div className="card shadow-sm">
                  <div className="card-body">
                    <h3 className="card-title">{task.title}</h3>
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
      </div>)
  }
};


export default Dashboard;
