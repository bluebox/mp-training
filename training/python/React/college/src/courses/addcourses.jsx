import axios from 'axios';
import { useEffect, useState } from 'react';
import { useLocation, useNavigate } from "react-router-dom";

export default function AddCourses(resources){

    const location = useLocation();
    const navigate = useNavigate();
    const [resource,setResource] = useState(resources);
    const [course, setCourse] = useState({
        course_id: '',
        course_name: '',
        credits: '',
        dept: '',
    });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setCourse((prevCourse) => ({
      ...prevCourse,
      [name]: value,
    }));
  };
  useEffect(() => {
    if (location.state && location.state.values && location.state.resource) {
        setCourse(location.state.values);  
        setResource(location.state.resource);
    }
}, [location]);

  const handleSubmit = (e) => {
    e.preventDefault();
    if (location.state?.values) {
        axios.put("http://127.0.0.1:8000/college/"+resource+'/'+course.course_id+'/',course);
        navigate("/coursesdata");
    }
    else{
        axios.post("http://127.0.0.1:8000/college/"+resource+'/',course);
        navigate("/coursesdata");
    }
    console.log('course added:', course);
    setCourse({
        course_id: '',
        course_name: '',
        credits: '',
        dept: '',
    });
  };

  return (
    <div>
      <h2>Add New course</h2>
      <form onSubmit={handleSubmit}>

        <div>
          <label htmlFor="course_name">Course Name: </label>
          <input
            type="text"
            id="course_name"
            name="course_name"
            value={course.course_name}
            onChange={handleChange}
            required
          />
        </div>

        <div>
          <label htmlFor="credits">credits</label>
          <input
            type='number'
            id="credits"
            name="credits"
            value={course.credits}
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
            value={course.dept}
            onChange={handleChange}
            required
          />
        </div>

        <div>
          <button type="submit">Add course</button>
        </div>
      </form>
    </div>
  );

}