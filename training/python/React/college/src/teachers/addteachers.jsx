import axios from 'axios';
import { useEffect, useState } from 'react';
import { useLocation, useNavigate } from "react-router-dom";

export default function AddTeachers(resources){

    const location = useLocation();
    const navigate = useNavigate();
    const [resource,setResource] = useState(resources);
    const [teacher, setTeacher] = useState({
        teacher_id: '',
        first_name: "",
        last_name: "",
        email: "",
        dept: ''
    });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setTeacher((prevTeacher) => ({
      ...prevTeacher,
      [name]: value,
    }));
  };
  useEffect(() => {
    if (location.state && location.state.values && location.state.resource) {
        setTeacher(location.state.values);  
        setResource(location.state.resource);
    }
}, [location]);

  const handleSubmit = (e) => {
    e.preventDefault();
    if (location.state?.values) {
        axios.put("http://127.0.0.1:8000/college/"+resource+'/'+teacher.teacher_id+'/',teacher);
        navigate("/teachersdata");
    }
    else{
        axios.post("http://127.0.0.1:8000/college/"+resource+'/',teacher);
        navigate("/teachersdata");
    }
    console.log('teacher added:', teacher);
    setTeacher({
        teacher_id: '',
        first_name: "",
        last_name: "",
        email: "",
        dept: ''
    });
  };

  return (
    <div>
      <h2>Add New teacher</h2>
      <form onSubmit={handleSubmit}>

        <div>
          <label htmlFor="first_name">First Name: </label>
          <input
            type="text"
            id="first_name"
            name="first_name"
            value={teacher.first_name}
            onChange={handleChange}
            required
          />
        </div>

        <div>
          <label htmlFor="last_name">last name: </label>
          <input
            type="text"
            id="last_name"
            name="last_name"
            value={teacher.last_name}
            onChange={handleChange}
            required
          />
        </div>

        <div>
          <label htmlFor="email">email</label>
          <input
            type='email'
            id="email"
            name="email"
            value={teacher.email}
            onChange={handleChange}
            required
          />
        </div>

        <div>
          <label htmlFor="dept">Department</label>
          <input
            type="text"
            id="dept"
            name="dept"
            value={teacher.dept}
            onChange={handleChange}
            required
          />
        </div>

        <div>
          <button type="submit">Add teacher</button>
        </div>
      </form>
    </div>
  );
}