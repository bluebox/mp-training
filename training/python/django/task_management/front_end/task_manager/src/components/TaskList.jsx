import React, { useEffect, useState } from "react";
import api from '../api/axios';

const TaskList = () => {
  const [count, setCount] = useState(1);
  const [currentPage, setCurrentPage] = useState(1);
  const [isFilter, setIsFilter] = useState(false);
  const [filteredTasks, setFilteredTasks] = useState([]);
  const [status, setStatus] = useState("");
  const [priority, setPriority] = useState("");
  const [project, setProject] = useState("");
  const [created_by, setCreated_by] = useState("");
  
  
  useEffect(() => {
    const fetchTasks = async () => {
      try{  
        if(status===''&&priority===''&&project===''&&created_by===""){
          setIsFilter(false);
          setCurrentPage(1);
        }
        const response = isFilter? await api.get("tasks/all/?page="+currentPage+'&'+(status===""?"":"status="+status)+'&'+(priority===""?"":"priority="+priority)
        +'&'+(project===""?"":"project="+project)+'&'+(created_by===""?"":"created_by="+created_by)) :await api.get("tasks/all/?page="+currentPage);
        setFilteredTasks(response.data.results);
        if(currentPage===1){
          if(response.data.results.length){
            setCount(Math.ceil(response.data.count/response.data.results.length));
          }
          else{
            setCount(1);
          }
        }
      }
      catch{
        console.log("error in retrieving tasks")
      };
    }
    fetchTasks();
    // eslint-disable-next-line
  }, [currentPage]);

  const handleFilter = async () => {
    setCurrentPage(1);
    setIsFilter(true);
    const response = await api.get("tasks/all/?page="+currentPage+'&'+(status===""?"":"status="+status)+'&'+(priority===""?"":"priority="+priority)
                                      +'&'+(project===""?"":"project="+project)+'&'+(created_by===""?"":"created_by="+created_by));
    setFilteredTasks(response.data.results);
    if(currentPage===1){
      if(response.data.results.length){
        setCount(Math.ceil(response.data.count/response.data.results.length));
      }
      else{
        setCount(1);
      }
    }
  }

  const handleChange = (e) => {

    if(e.target.name === "status"){
      setStatus(e.target.value);
    }
    else if(e.target.name === "priority"){
      setPriority(e.target.value);
    }
    else if(e.target.name === "project"){
      setProject(e.target.value);
    }
    else{
      setCreated_by(e.target.value);
    }
  }

  const handleNextPage =() => {
    if(currentPage <= count){
      setCurrentPage(prevPage => prevPage + 1);
    }
  };

  const handlePrevPage =() => {
    if(currentPage >= 1){
      setCurrentPage(prevPage => prevPage - 1);
    }
  };

  return (
    <div className="card mt-4">
      
      <div>
        <select name="status" value={status} onChange={handleChange}>
          <option name="status" value="">select status</option>
          <option name="status" value="todo" >todo</option>
          <option name="status" value="in_progress">in_progress</option>
          <option name="status" value="done">done</option>          
        </select>

        <select name="priority" value={priority} onChange={handleChange}>
          <option name="priority" value="">select priority</option>
          <option name="priority" value="low" >low</option>
          <option name="priority" value="medium">medium</option>
          <option name="priority" value="high">high</option>          
        </select>

        <input type="text" value={project} placeholder=" project" name="project" onChange={handleChange} />
        
        <input type="text" value={created_by} placeholder="created_by" name="created_by" onChange={handleChange} />

        <button onClick={handleFilter}>filter</button>
      </div>

      <div className="card-body">
        {filteredTasks.length === 0 ? (
          <p>No tasks available.</p>
        ) : (
          <table className="table" style={{textAlign:"center"}}>
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
              {filteredTasks.map((task) => (
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
          </table>)
          
        }
        <div>
          <button onClick={handlePrevPage} disabled={currentPage === 1}>Prev</button>
          <span>Page {currentPage} of {count}</span>
          <button onClick={handleNextPage} disabled={currentPage === count}>Next</button>
        </div>
      </div>
    </div>
  );
};

export default TaskList;
