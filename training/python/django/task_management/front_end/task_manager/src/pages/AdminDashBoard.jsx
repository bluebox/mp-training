import React, { useEffect, useState } from "react";
import api from "../api/axios";

const AdminDashboard = () => {
  const [tasks, setTasks] = useState([]);
  const [isEdit, setIsEdit] = useState(false);
  const [count, setCount] = useState(1);
  const [currentPage, setCurrentPage] = useState(1);
  const [isFilter, setIsFilter] = useState(false);
  const [status, setStatus] = useState("");
  const [priority, setPriority] = useState("");
  const [title, setTitle] = useState("")


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
      const fetchTasks = async () => {
      try{  
        if(status==="" && priority === "" && title === ""){
          setIsFilter(false);
        }
        const response =isFilter? await api.get("tasks/all/?page="+currentPage+'&'+(status===""?"":"status="+status)+'&'+(priority===""?"":"priority="+priority)
                                      +'&'+(title===""?"":"title="+title)) :await api.get("tasks/all/?page="+currentPage);
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
      catch(err){
        console.log("error in retrieving tasks",err)
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

  
  const handleFilter = async () => {
    setCurrentPage(1);
    setIsFilter(true);
    const response = await api.get("tasks/all/?page="+currentPage+(status===""?"":"&status="+status)+(priority===""?"":"&priority="+priority)
                                      +(title===""?"":"&title="+title));
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

  const handleChangeFilter = (e) => {

    if(e.target.name === "status"){
      setStatus(e.target.value);
    }
    else if(e.target.name === "priority"){
      setPriority(e.target.value);
    }
    else{
      setTitle(e.target.value);
    }
  }


  return (
    <div>
      <div>
        <input type="text" value={title} placeholder="title" name="title" onChange={handleChangeFilter} />
        
        <select name="status" value={status} onChange={handleChangeFilter}>
          <option name="status" value="">select status</option>
          <option name="status" value="todo" >todo</option>
          <option name="status" value="in_progress">in_progress</option>
          <option name="status" value="done">done</option>          
        </select>

        <select name="priority" value={priority} onChange={handleChangeFilter}>
          <option name="priority" value="">select priority</option>
          <option name="priority" value="low" >low</option>
          <option name="priority" value="medium">medium</option>
          <option name="priority" value="high">high</option>          
        </select>


        <button onClick={handleFilter}>filter</button>
      </div>
      {(
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
            {tasks.length>0 && tasks.map((task) => {
            const taskData = {
              id: task.id,
              title: task.title,
              description: task.description,
              status: task.status,
              priority: task.priority,
              due_date: task.due_date
            }

            return (
              <tr key={taskData.id}>

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
                  ) : (
                    taskData.status
                  )}
                </td>
                <td>
                  {isEdit && updateTask.id === taskData.id ? (
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
      <div>
          <button onClick={handlePrevPage} disabled={currentPage === 1}>Prev</button>
          <span>Page {currentPage} of {count}</span>
          <button onClick={handleNextPage} disabled={currentPage === count}>Next</button>
        </div>
    </div>
  );
}



export default AdminDashboard;
