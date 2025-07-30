import React, { useEffect, useState } from "react";
import customAXIOS from "./apis";
import { CLASSES, TEACHERS } from "./urls";
import { useLocation, useNavigate } from "react-router-dom";

function CreateClasses() {
  const [teachers, setTeachers] = useState([]);
  const [data, setData] = useState({
    Class_id: "",
    Section: "",
    teacher: -1,
  });

  const { state } = useLocation();
  const Id = state?.Id;
  const navigator = useNavigate();

  // Fetch teachers + optionally class details (edit mode)
  useEffect(() => {
    customAXIOS(TEACHERS, null, "get", null, navigator)
      .then((res) => setTeachers(res))
      .catch((err) => alert("Failed to fetch teachers"));

    if (Id !== undefined) {
      customAXIOS(CLASSES + String(Id) + "/", null, "get", null, navigator)
        .then((cls) => {
          setData({
            Class_id: cls.Class_id,
            Section: cls.Section,
            teacher: cls.teacher,
          });
        })
        .catch((err) => alert("Failed to fetch class"));
    }
  }, [Id, navigator]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setData((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    if (Id === undefined) {
      customAXIOS(CLASSES, null, "post", data)
        .then(() => alert("Class created successfully"))
        .catch(() => alert("Error: class creation failed"));
    } else {
      customAXIOS(CLASSES + String(Id) + "/", null, "put", data)
        .then(() => alert("Class updated successfully"))
        .catch(() => alert("Error: class update failed"));
    }
    navigator("/classTeachers");
  };

  if (teachers.length === 0) return <p>Loading...</p>;

  return (
    <div>
      <h1>{Id === undefined ? "Create Class" : "Edit Class"}</h1>
      <form onSubmit={handleSubmit}>
        <label>
          Class Name
          <input
            type="number"
            name="Class_id"
            value={data.Class_id}
            onChange={handleChange}
            readOnly={Id !== undefined}
            required
          />
        </label>
        <label>
          Section
          <input
            type="text"
            name="Section"
            value={data.Section}
            onChange={handleChange}
            readOnly={Id !== undefined}
            required
          />
        </label>
        <label>
          Teacher
          <select
            name="teacher"
            value={data.teacher}
            onChange={handleChange}
          >
            <option value="">Select Teacher</option>
            {teachers.length>0 && teachers.map((teacher) => (
              <option key={teacher.user} value={teacher.user}>
                {teacher.Name}
              </option>
            ))}
          </select>
        </label>
        <button type="submit" className="submit-buttons">
          {Id === undefined ? "Submit" : "Update"}
        </button>
      </form>
    </div>
  );
}

export default CreateClasses;
