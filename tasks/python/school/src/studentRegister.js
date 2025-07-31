import React, { useEffect, useState } from "react";
import customAXIOS from "./apis";
import { ALLCLASSES, ALLSTUDENTS, STUDENTS } from "./urls";
import { useNavigate } from "react-router-dom";
import { useLocation } from "react-router-dom";



function StudentRegister({ userId }) {
  const loc = useLocation();
  const userid = loc.state?.id
  const navigate = useNavigate();
  const [loadingState, setLoading] = useState(false)

  const [formData, setFormData] = useState({
    Name: "",
    Class: -1,
    attendance: "",
    is_class_representative: false,
    status: "S",
  });

  const [classes, setClasses] = useState([]);
  const [message, setMessage] = useState("");

  useEffect(() => {
    setLoading(true)
    customAXIOS(ALLCLASSES, null, "get", null, navigate)
      .then((res) =>{
        setClasses(res || [])
        console.log("class res:",res)
      })
      .catch((err) => console.error("Error loading classes", err))
      .finally(() => setLoading(false));
  }, []);

  useEffect(() => {
    if (userid) {
      setLoading(true)
      customAXIOS(ALLSTUDENTS, { id: userid }, 'get', null, navigate)
        .then((data) => {
          data = {
            Name: data.Name,
            Class: data.Class,
            attendance: data.attendance,
            is_class_representative: data.is_class_representative,
            status: data.status,
          }
          console.log("propogating data::::", data)
          setFormData(data)
        }).catch(err => {
          alert("Error has occured in fetching data")
          console.log("Error:", err)
        }).finally(() => {
          setLoading(false);
        })
    }
  }, [])

  const handleChange = (e) => {
    const { name, value, type, checked } = e.target;
    setFormData((prev) => ({
      ...prev,
      [name]: type === "checkbox" ? checked : value,
    }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    const payload = {
      ...formData,
      //   user: userId,
    };

    if (!userid) {
      customAXIOS(STUDENTS, null, "post", payload, navigate)
        .then((res) => {
          setMessage("Student registered successfully!");
          setFormData({
            Name: "",
            Class: "",
            attendance: "",
            is_class_representative: false,
            status: "S",
          });
          navigate("/studentRegister");
        })
        .catch((err) => {
          console.error(err);
          setMessage("Error creating student.");
        });
    } else {
      customAXIOS(STUDENTS, { id: userid }, "put", payload, navigate).then((res) => {
        setMessage("Student altered successfully!");
        setFormData({
          Name: "",
          Class: "",
          attendance: "",
          is_class_representative: false,
          status: "S",
        });
        navigate("/studentRegister");
      }).catch((err) => {
        console.err(err);
        setMessage("Error in altering student")
      })
    }
  };


  return loadingState ?(
      <p>loading..</p>
        
      ):

    <div >
      <h2>Register Student</h2>
      {message && <p>{message}</p>}
      <form onSubmit={handleSubmit}>
        <label>
          Name:
          <input type="text" name="Name" value={formData.Name} onChange={handleChange} required />
        </label>
        <br />

        <label>
          Class:
          <select name="Class" value={formData.Class} onChange={handleChange} required>
            <option value="">Select Class</option>
            {classes?.map((cls) => (
              <option key={cls.id} value={cls.id}>
                {/* {console.log(typeof(cls.id))} */}
                {cls.Class_id} - {cls.Section}
              </option>
            ))}
          </select>
        </label>
        <br />

        <label>
          Attendance:
          <input
            type="number"
            name="attendance"
            value={formData.attendance}
            onChange={handleChange}
            required
          />
        </label>
        <br />

        <label>
          Is Class Representative:
          <input
            type="checkbox"
            name="is_class_representative"
            checked={formData.is_class_representative}
            onChange={handleChange}
          />
        </label>
        <br />

        <label>
          Status:
          <select name="status" value={formData.status} onChange={handleChange}>
            <option value="S">Studying</option>
            <option value="D">Dropped</option>
          </select>
        </label>
        <br />

        <button type="submit" className="submit-buttons">Submit</button>
      </form>
    </div>
}

export default StudentRegister;
