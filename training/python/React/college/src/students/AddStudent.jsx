import axios from 'axios';
import { useEffect, useState } from 'react';
import { useLocation, useNavigate } from "react-router-dom";

export default function AddStudent({resources}){
    const location = useLocation();
    const navigate = useNavigate();
    const [resource,setResource] = useState(resources);
    const [student, setStudent] = useState({
        student_id: '',
        first_name: '',
        last_name: '',
        email: '',
        dept: '',
    });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setStudent((prevStudent) => ({
      ...prevStudent,
      [name]: value,
    }));
  };
  useEffect(() => {
    if (location.state && location.state.values && location.state.resource) {
        setStudent(location.state.values);  
        setResource(location.state.resource);
    }
}, [location]);

  const handleSubmit = (e) => {
    e.preventDefault();
    if (location.state?.values) {
        axios.put("http://127.0.0.1:8000/college/"+resource+'/'+student.student_id+'/',student);
        navigate("/studentsdata");
    }
    else{
        axios.post("http://127.0.0.1:8000/college/"+resource+'/',student);
        navigate("/studentsdata");
    }
    console.log('Student added:', student);
    setStudent({
      student_id: '',
      first_name: '',
      last_name: '',
      email: '',
      dept: '',
    });
  };

  return (
    <div>
      <h2>Add New Student</h2>
      <form onSubmit={handleSubmit}>

        <div>
          <label htmlFor="first_name">First Name</label>
          <input
            type="text"
            id="first_name"
            name="first_name"
            value={student.first_name}
            onChange={handleChange}
            required
          />
        </div>

        <div>
          <label htmlFor="last_name">Last Name</label>
          <input
            type="text"
            id="last_name"
            name="last_name"
            value={student.last_name}
            onChange={handleChange}
            required
          />
        </div>

        <div>
          <label htmlFor="email">Email</label>
          <input
            type="email"
            id="email"
            name="email"
            value={student.email}
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
            value={student.dept}
            onChange={handleChange}
            required
          />
        </div>

        <div>
          <button type="submit">Add Student</button>
        </div>
      </form>
    </div>
  );
};

