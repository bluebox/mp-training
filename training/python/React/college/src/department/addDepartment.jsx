import axios from 'axios';
import { useEffect, useState } from 'react';
import { useLocation, useNavigate } from "react-router-dom";


export default function AddDepartment(resources){

    const location = useLocation();
    const navigate = useNavigate();
    const [resource,setResource] = useState(resources);
    const [department, setDepartment] = useState({
        dept_id: '',
        dept_name: '',
    });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setDepartment((prevDepartment) => ({
      ...prevDepartment,
      [name]: value,
    }));
  };
  useEffect(() => {
    if (location.state && location.state.values && location.state.resource) {
        setDepartment(location.state.values);  
        setResource(location.state.resource);
    }
}, [location]);

  const handleSubmit = (e) => {
    e.preventDefault();
    if (location.state?.values) {
        axios.put("http://127.0.0.1:8000/college/"+resource+'/'+department.dept_id+'/',department);
        navigate("/departmentsdata");
    }
    else{
        axios.post("http://127.0.0.1:8000/college/departments/",department);
        console.log("post request");
        navigate("/departmentsdata");
    }
    console.log('Department added:', department);
    setDepartment({
        dept_id: '',
        dept_name: '', 
    });
  };

  return (
    <div>
      <h2>Add New Department</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <label htmlFor="dept_name">Department Name</label>
          <input
            type="text"
            id="dept_name"
            name="dept_name"
            value={department.dept_name}
            onChange={handleChange}
            required
          />
        </div>

        <div>
          <button type="submit">Add Department</button>
        </div>
      </form>
    </div>
  );

}