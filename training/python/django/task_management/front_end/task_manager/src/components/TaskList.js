import React, { useEffect, useState } from "react";
import api from '../api/axios';

const TaskList = () => {
  const [tasks, setTasks] = useState([]);
  const [count, setCount] = useState(1);
  const [currentPage, setCurrentPage] = useState(1);

  
  useEffect(() => {
    const fetchTasks = async () => {
      try{  
        const response = await api.get("tasks/all/?page="+currentPage);
        setTasks(response.data.results);
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
      <div className="card-header">All Tasks</div>
      <div className="card-body">
        {tasks.length === 0 ? (
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
